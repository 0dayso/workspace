package org.vetech.core.modules.excel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 2007解析采用SAX解析方式 基于事件模型
 * 
 * @author 章磊
 * 
 */
public class Excel2007Reader extends DefaultHandler {
	enum xssfDataType {
		BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER,
	}
	// 共享字符串表
	private SharedStringsTable sst;
	private xssfDataType nextDataType;
	// 上一次的内容
	private StringBuffer lastContents;
	private boolean vIsOpen;
	private int sheetIndex = -1;
	// 当前行
	private int curRow = 0;
	// 当前列
	private int curCol = 0;
	private int lastColumnNumber = -1;
	private int titleRow = 0;
	private int startRow = 0;
	private List<String> titleRowlist = new ArrayList<String>();

	private List<Object> rowBeanlist = new ArrayList<Object>();

	private IRowReader rowReader;
	private Class cls;
	private Object target;
	private int count = 1000;
	private StylesTable stylesTable;
	private short formatIndex;
	private String formatString;
	private final DataFormatter formatter = new DataFormatter();
	public void setStartRow(int startRow){
		this.startRow = startRow;
	}
	public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}

	public void setRowReader(IRowReader rowReader) {
		this.rowReader = rowReader;
	}

	public void setCls(Class cls) {
		this.cls = cls;
	}

	/**
	 * 只遍历一个电子表格，其中sheetId为要遍历的sheet索引，从1开始，1-3
	 * 
	 * @param filename
	 * @param sheetId
	 * @throws Exception
	 */
	public void processOneSheet(String filename, int sheetId) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);

		// 根据 rId# 或 rSheet# 查找sheet
		InputStream sheet2 = r.getSheet("rId" + sheetId);
		sheetIndex++;
		this.lastContents = new StringBuffer();
		InputSource sheetSource = new InputSource(sheet2);
		parser.parse(sheetSource);
		sheet2.close();
	}

	/**
	 * 遍历工作簿中所有的电子表格
	 * 
	 * @param filename
	 * @throws Exception
	 */
	public void process(String filename) throws Exception {
		OPCPackage pkg = OPCPackage.open(filename);
		XSSFReader r = new XSSFReader(pkg);
		this.stylesTable = r.getStylesTable();
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);
		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			this.lastContents = new StringBuffer();
			InputStream sheet = sheets.next();
			InputSource sheetSource = new InputSource(sheet);
			parser.parse(sheetSource);
			sheet.close();
		}
	}

	public XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
		XMLReader parser = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		this.nextDataType = xssfDataType.NUMBER;
		parser.setContentHandler(this);
		return parser;
	}
	
	private int nameToColumn(String name) {
		int column = -1;
		for (int i = 0; i < name.length(); ++i) {
			int c = name.charAt(i);
			column = (column + 1) * 26 + c - 'A';
		}
		return column;
	}
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		if ("inlineStr".equals(name) || "v".equals(name)) {
			vIsOpen = true;
			// Clear contents cache
			lastContents.setLength(0);
		}
		// c => cell
		else if ("c".equals(name)) {
			// Get the cell reference
			String r = attributes.getValue("r");
			int firstDigit = -1;
			for (int c = 0; c < r.length(); ++c) {
				if (Character.isDigit(r.charAt(c))) {
					firstDigit = c;
					break;
				}
			}
			curCol = nameToColumn(r.substring(0, firstDigit));

			// Set up defaults.
			this.nextDataType = xssfDataType.NUMBER;
			this.formatIndex = -1;
			this.formatString = null;
			String cellType = attributes.getValue("t");
			String cellStyleStr = attributes.getValue("s");
			if ("b".equals(cellType))
				nextDataType = xssfDataType.BOOL;
			else if ("e".equals(cellType))
				nextDataType = xssfDataType.ERROR;
			else if ("inlineStr".equals(cellType))
				nextDataType = xssfDataType.INLINESTR;
			else if ("s".equals(cellType))
				nextDataType = xssfDataType.SSTINDEX;
			else if ("str".equals(cellType))
				nextDataType = xssfDataType.FORMULA;
			else if (cellStyleStr != null) {
				// It's a number, but almost certainly one
				// with a special style or format
				int styleIndex = Integer.parseInt(cellStyleStr);
				XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
				this.formatIndex = style.getDataFormat();
				this.formatString = style.getDataFormatString();
				if (this.formatString == null)
					this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
			}
		}
	}

	public void endElement(String uri, String localName, String name) throws SAXException {

		String thisStr = null;

		// v => contents of a cell
		if ("v".equals(name)) {
			// Process the value contents as required.
			// Do now, as characters() may be called more than once
			switch (nextDataType) {

			case BOOL:
				char first = lastContents.charAt(0);
				thisStr = first == '0' ? "FALSE" : "TRUE";
				break;

			case ERROR:
				thisStr = "ERROR:" + lastContents.toString();
				break;

			case FORMULA:
				// A formula could result in a string value,
				// so always add double-quote characters.
				thisStr = lastContents.toString();
				break;

			case INLINESTR:
				// TODO: have seen an example of this, so it's untested.
				XSSFRichTextString rtsi = new XSSFRichTextString(lastContents.toString());
				thisStr = rtsi.toString() ;
				break;

			case SSTINDEX:
				String sstIndex = lastContents.toString();
				try {
					int idx = Integer.parseInt(sstIndex);
					XSSFRichTextString rtss = new XSSFRichTextString(sst.getEntryAt(idx));
					thisStr =  rtss.toString();
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				break;

			case NUMBER:
				String n = lastContents.toString();
				if (this.formatString != null && DateUtil.isADateFormat(formatIndex, formatString))
					thisStr = formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString);
				else
					thisStr = n;
				break;

			default:
				thisStr = "(TODO: Unexpected type: " + nextDataType + ")";
				break;
			}

			if (lastColumnNumber == -1) {
				lastColumnNumber = 0;
			}
			//如果lastColumnNumber+1小于curCol说明单元格有空的情况
			for (int i = lastColumnNumber+1; i < curCol; ++i) {
				if(titleRow == curRow){
					titleRowlist.add("");
				}else if(curRow > startRow){
					String title = titleRowlist.get(i);
					Object valueo = rowReader.getCol(sheetIndex, curRow, i, title, "");
					setValue(title, valueo);
				}
			}


			if (curCol > -1) {
				lastColumnNumber = curCol;
			}
			if(titleRow == curRow){
				titleRowlist.add(thisStr);
			}else if(curRow > startRow){
				String title = titleRowlist.get(curCol);
				Object valueo = rowReader.getCol(sheetIndex, curRow, curCol, title, thisStr);
				setValue(title, valueo);
			}
		} else if ("row".equals(name)) {
			if (curRow > startRow){
				rowBeanlist.add(target);
			}
			if(cls==null){
				target = new HashMap();
			}else{
				try {
					target = cls.newInstance();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			curRow++;
			lastColumnNumber = -1;
		}else if(rowBeanlist.size() == count){
			if(!rowBeanlist.isEmpty()){
				rowReader.getRows(sheetIndex, curRow, rowBeanlist);
				rowBeanlist.clear();
			}
		}else if("worksheet".equals(name)){
			if(!rowBeanlist.isEmpty()){
				rowReader.getRows(sheetIndex, curRow, rowBeanlist);
				rowBeanlist.clear();
			}
		}

	}
	private void setValue(String title, Object valueo) {
		if (cls == null) {
			Map tmpMap = (Map) target;
			tmpMap.put(title, valueo);
		} else {
			try {
				ExcelFieldUtil.setProperty(target, title, valueo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (vIsOpen)
			lastContents.append(ch, start, length);
	}
	public static void main(String []args) throws Exception{
		long ss = System.currentTimeMillis();
		Excel2007Reader excel2007Reader = new Excel2007Reader();
		excel2007Reader.setRowReader(new RowReader());
		excel2007Reader.process("d:/wwww1.xlsx");
//		ExcelReaderUtil.readExcel(new RowReader(), "d:/wwww1.xlsx",null);
		System.out.println(System.currentTimeMillis()-ss);
	}
}
