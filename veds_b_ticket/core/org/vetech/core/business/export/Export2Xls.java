package org.vetech.core.business.export;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.ObjectContext;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.taglibs.standard.functions.Functions;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.vetech.core.business.tag.Function;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.FileOperate;
import org.vetech.core.modules.utils.Reflections;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

class Export2XlsData{
	/** 分页导出时每次导出的默认条数 */
	int pecount = 5000;

	/** 下次要写的行数 */
	int nextRow = 0;

	/** 标题行数 */
	int titleRows = 0;
	
	/** display:column标签title对应的单元格用于取样式 */
	Map<String, HSSFCell> titleCell;

	/** 合并的行数 */
	int mergedRows = 0;

	/** display:column标签title与property或expfield的映射 */
	Map<String, String> properties;

	/**
	 * 写标题
	 * 
	 * @param sheet
	 * @param displayTagExpField
	 *            [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	void writeHead(HSSFSheet sheet, String[] displayTagExpField) {
		HSSFWorkbook wb = sheet.getWorkbook();
		HSSFCellStyle cs = wb.createCellStyle();
		cs.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		cs.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		cs.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		cs.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		cs.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		int length = displayTagExpField.length;

		List<List<String>> title = new ArrayList<List<String>>();
		properties = new LinkedHashMap<String, String>();
		CellRangeAddress cra = null;
		do {
			List<String> l = new ArrayList<String>();
			int index = title.size();
			String lastVal = "";
			int lastValCount = 0;
			for (int i = 0; i < length; i++) {
				String one = displayTagExpField[i];
				int lastIndex = one.lastIndexOf(",");
				String val = one.substring(lastIndex + 1, one.length()).replaceAll(" ", "");
				if (index == 0) {
					properties.put(one.substring(0, lastIndex), val);
					if (MapUtils.isNotEmpty(titleCell)) {
						HSSFCell cell = titleCell.get(val);
						if (cell != null) {
							HSSFSheet mbHS = cell.getSheet();
							sheet.setColumnWidth(i, mbHS.getColumnWidth(cell.getColumnIndex()));
						}
					}
				}
				String[] v = val.split("_");
				int len = v.length;
				if (len > titleRows) {
					titleRows = len;
				}
				if (index < len) {
					val = v[index];
				} else {
					val = "";
					cra = new CellRangeAddress(index - 1, len, i, i);
					sheet.addMergedRegion(cra);
				}
				if (StringUtils.isNotBlank(lastVal) && lastVal.equals(val)) {
					lastValCount++;
					if (i + 1 == length) {
						cra = new CellRangeAddress(index, index, i - lastValCount, i);
						sheet.addMergedRegion(cra);
					}
				} else {
					if (lastValCount > 0) {
						cra = new CellRangeAddress(index, index, i - lastValCount - 1, i - 1);
						sheet.addMergedRegion(cra);
					}
					lastValCount = 0;
				}
				lastVal = val;
				l.add(val);
			}
			title.add(l);
			HSSFRow row = sheet.createRow(nextRow++);
			for (int i = 0; i < l.size(); i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(cs);
				cell.setCellValue(l.get(i));
			}
		} while (titleRows != title.size());
	}
	/**
	 * 写行记录
	 * 
	 * @param row
	 * @param bean
	 * @param start
	 *            从第几列开始写
	 * 
	 * @return void [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	int writeRow(HSSFRow row, Object bean) {
		HSSFRow _row = null;
		Set<String> set = properties.keySet();
		HSSFSheet sheet = row.getSheet();
		Iterator<String> it = set.iterator();
		int i = 0;
		while (it.hasNext()) {
			String one = (String) it.next();
			Object val = null;
			if ("_rowNum".equals(one)) { //处理序号
				val = nextRow - titleRows - mergedRows;
			} else {
				try {
					String[] res = one.split("\\[\\*\\].");	//处理一对多的跨行显示
					if (res.length > 1) {
						List fvs = (List) Reflections.getFieldValue(bean, res[0]);
						val = Reflections.getFieldValue(fvs.get(0), res[1]);
						for (int k = 1; k < fvs.size(); k++) {
							Object _val = Export2Xls.evaluateVal(fvs.get(k), res[1]);
							_row = sheet.getRow(row.getRowNum() + k);
							if (_row == null) {
								_row = sheet.createRow(row.getRowNum() + k);
							}
							HSSFCell cell = _row.createCell(i);
							this.setCellValue(cell, _val);
							if (MapUtils.isNotEmpty(titleCell)) {
								HSSFCell hs = titleCell.get(properties.get(one));
								if (hs != null) {
									cell.setCellStyle(hs.getCellStyle());
								}
							}
						}
					} else {
						val = Export2Xls.evaluateVal(bean, one);
					}
				} catch (Exception e) {
					val = null;
				}
			}
			HSSFCell cell = row.createCell(i++);
			this.setCellValue(cell, val);
			if (MapUtils.isNotEmpty(titleCell)) {
				HSSFCell hs = titleCell.get(properties.get(one));
				if (hs != null) {
					cell.setCellStyle(hs.getCellStyle());
				}
			}
		}
		if (_row != null) {
			mergedRows = _row.getRowNum() - row.getRowNum();
			return _row.getRowNum();
		} else {
			return row.getRowNum();
		}
	}

	/**
	 * 按数据类型写入数据到cell
	 * 
	 * @param cell
	 *            excel 单元格
	 * 
	 * @param obj
	 *            写入cell的数据
	 * 
	 */
	private void setCellValue(HSSFCell cell, Object obj) {
		if (obj instanceof String) {
			cell.getCellStyle().setWrapText(false);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue((String) obj);
		} else if (obj instanceof Double) {
			cell.getCellStyle().setWrapText(false);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Double) obj);
		} else if (obj instanceof Integer) {
			cell.getCellStyle().setWrapText(false);
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue((Integer) obj);
		} else if (obj instanceof Date) {
			cell.getCellStyle().setWrapText(false);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(VeDate.formatToStr((Date) obj, "yyyy-MM-dd HH:mm:ss"));
		} else {
			try {
				cell.getCellStyle().setWrapText(false);
				cell.setCellValue((String) obj);
			} catch (Exception e) {
				cell.setCellValue("NULL");
			}
		}
	}
	/**
	 * 读取一行的单元格样式
	 * 
	 * @param row
	 * @return [参数说明]
	 * 
	 * @return List<HSSFCellStyle> [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	Map<String, HSSFCell> readCell(HSSFRow row, Map<String, String> properties) {
		Map<String, HSSFCell> styleMap = new HashMap<String, HSSFCell>();
		short num = row.getLastCellNum();
		for (int i = 0; i < num; i++) {
			HSSFCell cell = row.getCell(i);
			String cellVal = cell.getStringCellValue().replaceAll(" ", "");
			if (cellVal.startsWith("*")) {	//单元格模板后缀匹配 用法示例见Avh_rmhxAction类的export方法对应模板
				cellVal = cellVal.replaceFirst("\\*", "");
				if (MapUtils.isNotEmpty(properties)) {
					Iterator<String> it = properties.keySet().iterator();
					while (it.hasNext()) {
						String key = it.next();
						String val = properties.get(key);
						if (val.endsWith(cellVal)) {
							styleMap.put(val, cell);
						}
					}
				}
			}
			styleMap.put(cellVal, cell);
		}
		return styleMap;
	}

}
/**
 * Excel模板导出
 * 
 * @author heer
 * @version [版本号, Aug 19, 2014]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
@Component
public class Export2Xls {
//	@Autowired
//	private Export_taskDao export_taskDao;
	private static final Log log = LogFactory.getLog(Export2Xls.class);

	

	/** 定时导出任务的所需参数文件存放路径 */
	private static final String exportParam = "/updownFiles/exportParam";
	
	/** 生成的文件存放在应用目录下的相对路径 */
	public static String tempPath = "/updownFiles/export/";
	
	private static JexlEngine engine;
	private static ThreadPoolTaskExecutor taskExecutor;
	private Export2Xls() {}
	
	static {
		engine = new JexlEngine();
		Map<String, Object> funcs = new HashMap<String, Object>();
		funcs.put("vfn", new Function());
		funcs.put("fn", new Functions());
		engine.setFunctions(funcs);
		taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(3);
		taskExecutor.setKeepAliveSeconds(200);
		taskExecutor.setMaxPoolSize(5);
		taskExecutor.setQueueCapacity(25);
	}

	/**
	 * 立即导出
	 * 
	 * @param exportPage
	 * @param expField
	 * @param sheetName
	 * @param templateFile
	 * @return
	 * @throws Exception [参数说明]
	 * 
	 * @return String 导出后的文件路径
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public  String export(ExportPage exportPage, String expField, String sheetName, String templateFile) throws Exception {
		Export2XlsData export2XlsData = new Export2XlsData();
		String[] exField = expField.split(";");
		if (ArrayUtils.isNotEmpty(exField)) {
			String[] xh = exField[0].split(",");
			if ("序号".equals(xh[1])) {
				xh[0] = "_rowNum";
				exField[0] = "_rowNum" + ",序号";
			}
		}
		FileOutputStream fos = null;
		HSSFWorkbook wb = null;
		String descFile = "";
		try {
			descFile = templateCopy(templateFile, descFile);
			wb = getHSSFWorkbook(descFile);
			fos = new FileOutputStream(descFile);
			HSSFSheet sheet = null;
			if (StringUtils.isBlank(templateFile)) {
				int active = wb.getActiveSheetIndex();
				sheet = wb.getSheetAt(active);
				wb.setSheetName(active, sheetName);
			} else {
				sheet = wb.createSheet(sheetName);
			}
			export2XlsData.writeHead(sheet, exField);
			HSSFSheet sheetMB = wb.getSheet(sheetName + "_MB");
			if (sheetMB != null) {
				export2XlsData.titleCell = export2XlsData.readCell(sheetMB.getRow(0), export2XlsData.properties);
			}
			Collection<?> data = null;
			AbstractPageEntity bean = exportPage.t;
			bean.setCount(export2XlsData.pecount + 1);
			data = exportPage.getCollection(bean);
			while (true) {
				if (CollectionUtils.isNotEmpty(data)) {
					Iterator<?> itr = data.iterator();
					while (itr.hasNext()) {
						Object one = itr.next();
						exportPage.beforeExport(one);
						export2XlsData.nextRow = export2XlsData.writeRow(sheet.createRow(export2XlsData.nextRow++), one) + 1;
					}
				} else {
					break;
				}
				if (data.size() != bean.getCount()) {
					break;
				}
				int pCount = bean.getCount();
				if (pCount != export2XlsData.pecount) { // 第二次getCollection调用
					bean.setStart(bean.getStart() + pCount);
					bean.setCount(export2XlsData.pecount);
					data = exportPage.getCollection(bean);
					if (data.size() > bean.getCount()) { // 如果结果集数大于常量pecount则表示为不分页调用,跳出循环
						break;
					}
				} else {
					bean.setStart(bean.getStart() + pCount);
					data = exportPage.getCollection(bean);
				}
			}
			if (sheetMB != null) {
				int sheetIndex = wb.getSheetIndex(sheetMB.getSheetName());
				wb.removeSheetAt(sheetIndex);
			}
		} catch (Exception e) {
			log.error("根据模板写xls文件异常", e);
			throw e;
		} finally {
			try {
				wb.write(fos);
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return descFile;
	}
//	/**
//	 * 异步导出
//	 * @param yhbh
//	 * @param pt
//	 * @param exportPage
//	 * @param expField
//	 * @param sheetName
//	 * @param templateFile
//	 * @return
//	 */
//	public String exportTask(String yhbh,String pt,  final ExportPage exportPage, 
//			final String expField, final String sheetName, final String templateFile) {
//		String taskID = _exportTask(yhbh,pt, exportPage, expField, sheetName, templateFile);
//		return "export_task";
//	}
//	
//	/**
//	 * 立即任务导出
//	 * 
//	 * @param user	用户对象A平台为Ve_user,B平台为Sh_yhb
//	 * @param exportPage
//	 * @param expField
//	 * @param sheetName
//	 * @param templateFile
//	 * @return
//	 * @throws Exception [参数说明]
//	 * 
//	 * @return String 任务ID
//	 * @exception throws [违例类型] [违例说明]
//	 * @see [类、类#方法、类#成员]
//	 */
//	private  String _exportTask(String yhbh,String pt, final ExportPage exportPage, 
//			final String expField, final String sheetName, final String templateFile) {
//		Export_task export_task = new Export_task();
//		export_task.setPt(pt);
//		export_task.setBh(yhbh);
//		export_task.setStatus("0");
//		export_task.setLx("1");
//		export_task.setCreatetime(new Date());
//		export_task.setId(VeDate.getNo(2));
//		final String taskID = export_task.getId();
//		try {
//			export_taskDao.insert(export_task);
//		} catch (Exception e) {
//			export_task.setId("");
//		}
//		taskExecutor.execute(new Runnable(){
//			@Override
//			public void run() {
//				Export_task task = new Export_task();
//				try {
//					task.setStatus("1");
//					task.setId(taskID);
//					task.setTimes(1);
//					task.setStarttime(new Date());
//					export_taskDao.updateByPrimaryKey(task);
//					task.setTimes(null);
//					String dlFile = export(exportPage, expField, sheetName, templateFile);
//					task.setEndtime(new Date());
//					task.setExportfile(dlFile);
//					task.setStatus("2");
//				} catch (Exception e) {
//					task.setStatus("3");
//					task.setFailedtimes(1);
//					e.printStackTrace();
//				} finally {
//					export_taskDao.updateByPrimaryKey(task);
//				}
//			}
//		});
//		return export_task.getId();
//	}
//	
//	/**
//	 * 定时任务导出
//	 * 
//	 * @param user	用户对象A平台为Ve_user,B平台为Sh_yhb
//	 * @param exportPage
//	 * @param expField	
//	 * @param sheetName
//	 * @param templateFile	模板文件地址
//	 * @return
//	 * @throws Exception [参数说明]
//	 * 
//	 * @return String [返回类型说明]
//	 * @exception throws [违例类型] [违例说明]
//	 * @see [类、类#方法、类#成员]
//	 */
//	public String addExportTask(String yhbh,String pt,  ExportPage<?> exportPage, String expField, String sheetName, String templateFile) throws Exception {
//		Export_task export_task = new Export_task();
//		export_task.setStatus("0");
//		export_task.setPt(pt);
//		export_task.setBh(yhbh);
//		List<Object> param = new ArrayList<Object>();
//		param.add(exportPage);
//		param.add(expField);
//		param.add(sheetName);
//		param.add(templateFile);
//		String id = VeDate.getNo(2);
//		export_task.setId(id);
//		export_task.setLx("2");
//		export_task.setCreatetime(new Date());
//		String path = WebUtils.getRootPath(exportParam);
//		String paramFile = path + id + ".exportParams";
//		FileOutputStream fos = null;
//		ObjectOutputStream oos = null;
//		try {
//			fos = new FileOutputStream(paramFile);
//			oos = new ObjectOutputStream(fos);
//			oos.writeObject(param);
//			export_task.setParamfile(exportParam + id + ".exportParam");
//			export_taskDao.insert(export_task);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			try {
//				if (oos != null) {
//					oos.close();
//				}
//				if (fos != null) {
//					fos.close();
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return id;
//	}

	/**
	 * 模板文件复制
	 * 
	 * @throws IOException
	 */
	private static String templateCopy(String templateFile, String descFile) throws IOException {
		if (StringUtils.isBlank(descFile)) {
			String path = tempPath + VeDate.getStringDateShort().replace("-", "") + "/";
			String file = VeDate.getNo(6) + ".xls";
			descFile = WebUtils.getRootPath(path + file);
		}
		if (StringUtils.isBlank(templateFile)) {
			templateFile = WebUtils.getRootPath("/WEB-INF/classes/org/vetech/core/business/export/templateFile.xls");
		}
		File _templateFile = new File(templateFile);
		File _descFile = new File(descFile);
		if (_templateFile.isFile()) {
			File parFile = _descFile.getParentFile();
			if (!parFile.exists()) {
				parFile.mkdirs();
			}
			_descFile.createNewFile();
			try{
				FileOperate.copyFile(templateFile, descFile);
				return descFile;
			}catch(Exception ex){
				throw new IOException("模板文件复制异常");
			}
		} else {
			throw new IOException("模板文件路径错误");
		}
	}

	

	

	private static HSSFWorkbook getHSSFWorkbook(String newXlsFile) throws IOException {
		FileInputStream newfos = null;
		POIFSFileSystem fs = null;
		HSSFWorkbook wb = null;
		try {
			newfos = new FileInputStream(new File(newXlsFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		try {
			fs = new POIFSFileSystem(newfos);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (newfos != null) {
				newfos.close();
			}
		}
		return wb;
	}

	
	
	/**
	 * 根据属性或key取值 支持jexl表达式语言
	 * 示例见 jp_cjbl_report_page.jsp文件display:column标签的expfield属性 
	 * @param bean
	 * @param property
	 * @return [参数说明]
	 * 
	 * @return Object [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static Object evaluateVal(Object bean, String property) {
		Object val = null;
		if (bean instanceof Map) {
			String str = null;
			if (property.indexOf("{") > -1 && property.indexOf("}") > -1) {
				JexlContext context = new MapContext((Map) bean);
				str = property;
				while (property.indexOf("{") > -1 && property.indexOf("}") > -1) {
					int start = property.indexOf("{");
					int end = property.indexOf("}");
					String sub = property.substring(start, end + 1);
					Expression expression = engine.createExpression(sub.substring(1,sub.length() - 1));
					property = property.replace(sub, "");
					str = str.replace(sub, expression.evaluate(context).toString());
				}
			} else {
				str = MapUtils.getString((Map) bean, property);
			}
			val = str;
		} else {
			if (property.indexOf("{") > -1 && property.indexOf("}") > -1) {
				JexlContext context = new ObjectContext<Object>(engine, bean);
				String _val = property;
				while (property.indexOf("{") > -1 && property.indexOf("}") > -1) {
					int start = property.indexOf("{");
					int end = property.indexOf("}");
					String sub = property.substring(start, end + 1);
					Expression expression = engine.createExpression(sub.substring(1, sub.length() - 1));
					property = property.replace(sub, "");
					_val = _val.replace(sub, expression.evaluate(context).toString());
				}
				val = _val;
			} else {
				val = Reflections.getFieldValue(bean, property);
			}
		}
		return val;
	}
}
