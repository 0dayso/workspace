package org.vetech.core.modules.excel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 
 * @author 章磊
 *
 */
public class ExcelReaderUtil {
	// excel2003扩展名
	public static final String EXCEL03_EXTENSION = ".xls";
	// excel2007扩展名
	public static final String EXCEL07_EXTENSION = ".xlsx";

	/**
	 * 读取Excel文件，可能是03也可能是07版本
	 * 
	 * @param excel03
	 * @param excel07
	 * @param zdSheet 指定sheet
	 * @param fileName
	 * @throws Exception
	 */
	public static void readExcel(IRowReader reader,int startRow, String fileName,Class cls) throws Exception {
		readExcel(reader,startRow,0,fileName,cls);
	}
	/**
	 * 读取Excel文件，可能是03也可能是07版本
	 * 
	 * @param excel03
	 * @param excel07
	 * @param fileName
	 * @throws Exception
	 */
	public static void readExcel(IRowReader reader,int startRow,int titleRow, String fileName,Class cls) throws Exception {
		readExcel(reader, startRow, titleRow, fileName, cls, null);
	}
	/**
	 * 读取Excel文件，可能是03也可能是07版本;
	 * @param zdsheet 指定sheet下标，传下标后则只读该下标的sheet;sheet下标从0开始
	 * @param excel03
	 * @param excel07
	 * @param fileName
	 * @throws Exception
	 */
	public static void readExcel(IRowReader reader,int startRow,int titleRow, String fileName,Class cls,String zdSheet) throws Exception {
		// 处理excel2003文件
		if (fileName.endsWith(EXCEL03_EXTENSION)) {
			Excel2003Reader excel03 = new Excel2003Reader();
			excel03.setRowReader(reader);
			excel03.setStartRow(startRow);
			excel03.setTitleRow(titleRow);
			excel03.setZdSheet(zdSheet);
			excel03.process(fileName);
			// 处理excel2007文件
		} else if (fileName.endsWith(EXCEL07_EXTENSION)) {
			Excel2007Reader excel07 = new Excel2007Reader();
			excel07.setRowReader(reader);
			excel07.setCls(cls);
			excel07.setStartRow(startRow);
			excel07.setTitleRow(titleRow);
			if(StringUtils.isNotBlank(zdSheet)){
				excel07.processOneSheet(fileName, NumberUtils.toInt(zdSheet)+1);
			}else {
				excel07.process(fileName);
			}
		} else {
			throw new Exception("文件格式错误，fileName的扩展名只能是xls或xlsx。");
		}
	}
	public static void main(String []args) throws Exception{
		long ss = System.currentTimeMillis();
		ExcelReaderUtil.readExcel(new RowReader(),0, 0,"D:/z/test.xlsx",null,"1");
		System.out.println(System.currentTimeMillis()-ss);
	}
}
