package org.vetech.core.modules.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.eventusermodel.EventWorkbookBuilder.SheetRecordCollectingListener;
import org.apache.poi.hssf.eventusermodel.FormatTrackingHSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFEventFactory;
import org.apache.poi.hssf.eventusermodel.HSSFListener;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.apache.poi.hssf.eventusermodel.MissingRecordAwareHSSFListener;
import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.hssf.eventusermodel.dummyrecord.MissingCellDummyRecord;
import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.BOFRecord;
import org.apache.poi.hssf.record.BlankRecord;
import org.apache.poi.hssf.record.BoolErrRecord;
import org.apache.poi.hssf.record.BoundSheetRecord;
import org.apache.poi.hssf.record.EOFRecord;
import org.apache.poi.hssf.record.FormulaRecord;
import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.LabelSSTRecord;
import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.SSTRecord;
import org.apache.poi.hssf.record.StringRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * 抽象Excel2003读取器，通过实现HSSFListener监听器，采用事件驱动模式解析excel2003 中的内容，遇到特定事件才会触发，大大减少了内存的使用。
 * 章磊
 * 
 */
public class Excel2003Reader implements HSSFListener {
	private POIFSFileSystem fs;

	/** Should we output the formula, or the value it has? */
	private boolean outputFormulaValues = true;
	/** For parsing Formulas */
	private SheetRecordCollectingListener workbookBuildingListener;
	// excel2003工作薄
	private HSSFWorkbook stubWorkbook;

	// Records we pick up as we process
	private SSTRecord sstRecord;
	private FormatTrackingHSSFListener formatListener;

	// 表索引
	private int sheetIndex = -1;
	private BoundSheetRecord[] orderedBSRs;
	@SuppressWarnings("unchecked")
	private ArrayList boundSheetRecords = new ArrayList();

	// For handling formulas with string results
	private int nextRow;
	private int nextColumn;
	private boolean outputNextStringRecord;
	// 当前行
	private int curRow = 0;
	@SuppressWarnings("unused")
	private String sheetName;

	private IRowReader rowReader;

	private int titleRow = 0;
	
	private int startRow = 0;
	private List<String> titleRowlist = new ArrayList<String>();

	private List<Object> rowBeanlist = new ArrayList<Object>();
	private Class cls;
	private Object target;
	private int count = 1000;
	/**只读指定序号的sheet*/
	private String zdSheet;
	public String getZdSheet() {
		return zdSheet;
	}
	public void setZdSheet(String zdSheet) {
		this.zdSheet = zdSheet;
	}
	public void setTitleRow(int titleRow) {
		this.titleRow = titleRow;
	}
	public void setStartRow(int startRow){
		this.startRow = startRow;
	}
	public void setCls(Class cls) {
		this.cls = cls;
	}

	public void setRowReader(IRowReader rowReader) {
		this.rowReader = rowReader;
	}

	/**
	 * 遍历excel下所有的sheet
	 * 
	 * @throws IOException
	 */
	public void process(String fileName) throws Exception {
		this.fs = new POIFSFileSystem(new FileInputStream(fileName));
		MissingRecordAwareHSSFListener listener = new MissingRecordAwareHSSFListener(this);
		formatListener = new FormatTrackingHSSFListener(listener);
		HSSFEventFactory factory = new HSSFEventFactory();
		HSSFRequest request = new HSSFRequest();
		if (outputFormulaValues) {
			request.addListenerForAllRecords(formatListener);
		} else {
			workbookBuildingListener = new SheetRecordCollectingListener(formatListener);
			request.addListenerForAllRecords(workbookBuildingListener);
		}
		factory.processWorkbookEvents(request, fs);
	}

	/**
	 * HSSFListener 监听方法，处理 Record
	 */
	@SuppressWarnings("unchecked")
	public void processRecord(Record record) {
		int thisRow = -1;
		int thisColumn = -1;
		String thisStr = null;
		String value = null;
		if(StringUtils.isNotBlank(zdSheet) && !String.valueOf(sheetIndex).equals(zdSheet) 
				&& record.getSid()!=BoundSheetRecord.sid 
				&& record.getSid()!= BOFRecord.sid 
				&& record.getSid()!=SSTRecord.sid){
			return;
		}
		switch (record.getSid()) {
		case BoundSheetRecord.sid:
			boundSheetRecords.add(record);
			break;
		case BOFRecord.sid:
			BOFRecord br = (BOFRecord) record;
			if (br.getType() == BOFRecord.TYPE_WORKSHEET) {
				// 如果有需要，则建立子工作薄
				if (workbookBuildingListener != null && stubWorkbook == null) {
					stubWorkbook = workbookBuildingListener.getStubHSSFWorkbook();
				}

				sheetIndex++;
				if (orderedBSRs == null) {
					orderedBSRs = BoundSheetRecord.orderByBofPosition(boundSheetRecords);
				}
				sheetName = orderedBSRs[sheetIndex].getSheetname();
			}
			break;

		case SSTRecord.sid:
			sstRecord = (SSTRecord) record;
			break;

		case BlankRecord.sid:
			BlankRecord brec = (BlankRecord) record;
			thisRow = brec.getRow();
			thisColumn = brec.getColumn();
			thisStr = "";
			if (curRow > startRow && thisColumn<titleRowlist.size()) {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, thisStr);
				setValue(title, valueo);
			} else if(curRow==titleRow)  {
				titleRowlist.add(thisColumn, thisStr);
			}
			break;
		case BoolErrRecord.sid: // 单元格为布尔类型
			BoolErrRecord berec = (BoolErrRecord) record;
			thisRow = berec.getRow();
			thisColumn = berec.getColumn();
			thisStr = berec.getBooleanValue() + "";
			if (curRow > startRow && thisColumn<titleRowlist.size())  {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, thisStr);
				setValue(title, valueo);
			} else if(curRow==titleRow)  {
				titleRowlist.add(thisColumn, thisStr);
			}
			break;

		case FormulaRecord.sid: // 单元格为公式类型
			FormulaRecord frec = (FormulaRecord) record;
			thisRow = frec.getRow();
			thisColumn = frec.getColumn();
			if (outputFormulaValues) {
				if (Double.isNaN(frec.getValue())) {
					// Formula result is a string
					// This is stored in the next record
					outputNextStringRecord = true;
					nextRow = frec.getRow();
					nextColumn = frec.getColumn();
				} else {
					thisStr = formatListener.formatNumberDateCell(frec);
				}
			} else {
				thisStr = '"' + HSSFFormulaParser.toFormulaString(stubWorkbook, frec.getParsedExpression()) + '"';
			}
			if (curRow > startRow && thisColumn<titleRowlist.size())  {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, thisStr);
				setValue(title, valueo);
			} else if(curRow==titleRow)  {
				titleRowlist.add(thisColumn, thisStr);
			}
			break;
		case StringRecord.sid:// 单元格中公式的字符串
			if (outputNextStringRecord) {
				// String for formula
				StringRecord srec = (StringRecord) record;
				thisStr = srec.getString();
				thisRow = nextRow;
				thisColumn = nextColumn;
				outputNextStringRecord = false;
			}
			break;
		case LabelRecord.sid:
			LabelRecord lrec = (LabelRecord) record;
			curRow = thisRow = lrec.getRow();
			thisColumn = lrec.getColumn();
			value = lrec.getValue().trim();
			value = value.equals("") ? " " : value;
			if (curRow > startRow && thisColumn<titleRowlist.size()) {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, value);
				setValue(title, valueo);
			} else if(curRow==titleRow) {
				titleRowlist.add(thisColumn, value);
			}
			break;
		case LabelSSTRecord.sid: // 单元格为字符串类型
			LabelSSTRecord lsrec = (LabelSSTRecord) record;
			curRow = thisRow = lsrec.getRow();
			thisColumn = lsrec.getColumn();
			if (curRow > startRow && thisColumn<titleRowlist.size()) {
				String title = titleRowlist.get(thisColumn);

				if (sstRecord == null) {
					Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, " ");
					setValue(title, valueo);
				} else {
					value = sstRecord.getString(lsrec.getSSTIndex()).toString().trim();
					value = value.equals("") ? " " : value;
					Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, value);
					setValue(title, valueo);
				}
			} else if(curRow==titleRow)  {
				if (sstRecord == null) {
					titleRowlist.add(thisColumn, " ");
				} else {
					value = sstRecord.getString(lsrec.getSSTIndex()).toString().trim();
					value = value.equals("") ? " " : value;
					titleRowlist.add(thisColumn, value);
				}
			}
			
			break;
		case NumberRecord.sid: // 单元格为数字类型
			NumberRecord numrec = (NumberRecord) record;
			curRow = thisRow = numrec.getRow();
			thisColumn = numrec.getColumn();
			int formatIndex=formatListener.getFormatIndex(numrec);
			String formatString=formatListener.getFormatString(numrec);
			if (StringUtils.isNotBlank(formatString) && DateUtil.isADateFormat(formatIndex, formatString)) {//日期类型
				value = formatListener.formatNumberDateCell(numrec).trim(); 
			}else {
				value=String.valueOf(numrec.getValue());
			}
			value = value.equals("") ? " " : value;
			// 向容器加入列值
			if (curRow > startRow && thisColumn<titleRowlist.size())  {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, value);
				setValue(title, valueo);
			} else if(curRow==titleRow) {
				titleRowlist.add(thisColumn, value);
			}
			break;
		default:
			break;
		}
		// 空值的操作
		if (record instanceof MissingCellDummyRecord) {
			MissingCellDummyRecord mc = (MissingCellDummyRecord) record;
			curRow = thisRow = mc.getRow();
			thisColumn = mc.getColumn();
			if (curRow > startRow) {
				String title = titleRowlist.get(thisColumn);
				Object valueo = rowReader.getCol(sheetIndex, curRow, thisColumn, title, " ");
				setValue(title, valueo);
			} else if(curRow==titleRow)  {
				titleRowlist.add(thisColumn, " ");
			}
		}
		// 行结束时的操作
		if (record instanceof LastCellOfRowDummyRecord) {
			try {
				if (curRow > startRow){
					rowBeanlist.add(target);
				}
				if(cls==null){
					target = new HashMap();
				}else{
					target = cls.newInstance();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(rowBeanlist.size() == count){
			if(!rowBeanlist.isEmpty()){
				rowReader.getRows(sheetIndex, curRow, rowBeanlist);
			}
			rowBeanlist.clear();
		}else if(record instanceof EOFRecord){//最后一行
			if(!rowBeanlist.isEmpty()){
				rowReader.getRows(sheetIndex, curRow, rowBeanlist);
			}
			rowBeanlist.clear();
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

}
