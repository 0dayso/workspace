package cn.vetech.vedsb.utils.bankdb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.WebUtils;

public class BankDbUtil {
	/**
	 * 处理金额字段
	 * @param o
	 * @return
	 */
	public static Double obj2Double(Object o) {
		if (o == null) {
			return 0.0;
		}
		double ddd = 0.0;
		if (o instanceof Integer) {
			Integer i = (Integer) o;
			ddd = i.doubleValue();
		} else if (o instanceof Double) {
			Double d = (Double) o;
			ddd = d.doubleValue();
		} else if (o instanceof BigDecimal) {
			BigDecimal b = (BigDecimal) o;
			ddd = b.doubleValue();
		} else if (o instanceof Float) {
			Float f = (Float) o;
			ddd = f.doubleValue();
		} else if (o instanceof Number) {
			Number n = (Number) o;
			ddd = n.doubleValue();
		} else if (o instanceof String){
			String v=StringUtils.trimToEmpty(objToString(o));
			v = v.replace(",", "");
			ddd=NumberUtils.toDouble(v,0);
		}else {
			ddd = 0.0;
		}
		return Arith.round(ddd, 2);
	}
	/**
	 * 两个小数字符串相加
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static double addDouble(Object o1,Object o2)throws Exception{
		try {
			return Arith.add(obj2Double(o1), obj2Double(o2));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("金额列有文本内容，请检查数据。");
		}
	}
	/**
	 * 指定字段排序  顺序
	 * @param dataList
	 * @param field
	 */
	@SuppressWarnings("unchecked")
	public static void toSortData(List dataList,String field) throws Exception{
		Comparator mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.nullLowComparator(mycmp);
		ArrayList sortFields = new ArrayList();
		sortFields.add(new BeanComparator(field,mycmp));//升序
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(dataList, multiSort);
	}
	public static String objToString(Object abc) {
		return abc==null ? null : abc.toString();
	}
	/**
	 * 根据正则表达式获取字符串
	 * @param infoold
	 * @param reg
	 * @return
	 */
	public static String getStrByReg(String infoold, String reg) {
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher mp = p.matcher(infoold);
		String value = "";
		if (mp.find()) {
			value = mp.group(1);
		}
		return value;
	}
	/**
	 * 合并文字,以“,”隔开
	 * @param hb_bank
	 * @param one
	 * @param key
	 */
	public static void hbwz(Map<String, Object> hb_bank,Map<String, Object> one,String key){
		String hbwz=objToString(hb_bank.get(key));
		hbwz=StringUtils.trimToEmpty(hbwz);
		String onewz=objToString(one.get(key));
		onewz=StringUtils.trimToEmpty(onewz);
		if(StringUtils.isNotBlank(hbwz) && StringUtils.isNotBlank(onewz)){
			if(!hbwz.contains(onewz)){
				hb_bank.put(key, hbwz+","+onewz);
			}
		}else if(StringUtils.isNotBlank(onewz)){
			hb_bank.put(key,onewz);
		}
	}
	/**
	 * 合并金额
	 * @param hb_bank
	 * @param one
	 * @param key
	 */
	public static void hbje(Map<String, Object> hb_bank,Map<String, Object> one,String key)throws Exception{
		try{
			hb_bank.put(key,addDouble(hb_bank.get(key), one.get(key)));
		}catch (Exception e) {
			throw new Exception(key+"列被设为数字金额列，此列有数据为文本，请检查数据");
		}
	}
	/**
	 * 
	 * @param sourcePath 模板路径
	 * @param headNo 表头行数
	 * @param fileName
	 * @param dcList
	 * @return
	 * @throws Exception
	 */
	public static String exportExcelByModel(String sourcePath,int headNo,String fileName,List<Map<String,Object>> dcList) throws Exception {
		//模板文件路径
		String sourceFilePath = WebUtils.getRootPath(sourcePath);
		File srcFile = new File(sourceFilePath);
		//导出文件
		String destPath = "/updownFiles/export/"+ VeDate.getStringDateShort().replace("-", "");
		destPath += "/" + fileName;
		File destpathFile = new File(WebUtils.getRootPath(destPath));
		FileOutputStream os=null;
		try {
			File parFile=destpathFile.getParentFile();
			if (!parFile.exists()) {
				destpathFile.mkdirs();
			}
			destpathFile.createNewFile();
			FileUtils.copyFile(srcFile, destpathFile);
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(srcFile));
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			HSSFCell cell = null;
			int rownum = headNo; // 添加的起始行
			HSSFRow headRow = sheet.getRow(headNo-1);
			for (int i = 0; i < dcList.size(); i++) {
				row = sheet.createRow(rownum);
				for (int j = 0; j < headRow.getLastCellNum(); j++) {
					HSSFCell btCell=headRow.getCell(j);
					String bt=btCell.getStringCellValue();
					if(StringUtils.isNotBlank(bt)){
						myCreateCell(j, dcList.get(i).get(bt), row, cell);
					}
				}
				rownum++;
			}
			os = new FileOutputStream(destpathFile);
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("生成导入失败数据的 excel文件时报错！");
		}finally{
			if(os != null){
				os.close();
			}
		}
		return destPath;
	}
	/**
	 * 按供应订单号合并
	 * @param detaillist
	 * @param premap
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> hbWbdh(List detaillist) throws Exception{
		List<Map<String, Object>> newList=new ArrayList<Map<String, Object>>();
		/* 合并前先排序 */
		toSortData(detaillist, "WBDH");
		Map premap = new HashMap();
		String prewbdh = null;
		String wbdh = null;
		for (int i = 0; i < detaillist.size(); i++) {
			Map m = (Map) detaillist.get(i);
			prewbdh = StringUtils.trimToEmpty(objToString( premap.get("WBDH"))); // 上次的PNR  第一次为空
			wbdh = StringUtils.trimToEmpty(objToString( m.get("WBDH"))); // 本次的PNR
			if(StringUtils.isNotBlank(wbdh) && StringUtils.isNotBlank(prewbdh) && !"0".equals(prewbdh) && wbdh.equals(prewbdh)){
				hbwz(premap, m, "DDBH");
				hbwz(premap, m, "TKNOS");
				hbwz(premap, m, "CJRS");
				hbje(premap, m, "ZFJE");
			} else {
				premap = m;
				newList.add(m);
			}
		}
		return newList;
	}
	/**
	 * 采购对账按pnr合并
	 * @param detaillist
	 * @param premap
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> hbPnr(List detaillist) throws Exception{
		List<Map<String, Object>> newList=new ArrayList<Map<String, Object>>();
		/* 合并前先排序 */
		toSortData(detaillist, "PNRNO");
		Map premap = new HashMap();
		String prePnr = null;
		String pnrno = null;
		for (int i = 0; i < detaillist.size(); i++) {
			Map m = (Map) detaillist.get(i);
			prePnr = StringUtils.trimToEmpty(objToString( premap.get("PNRNO"))); // 上次的PNR  第一次为空
			pnrno = StringUtils.trimToEmpty(objToString( m.get("PNRNO"))); // 本次的PNR
			if(StringUtils.isNotBlank(pnrno) && StringUtils.isNotBlank(prePnr) && !"0".equals(prePnr) && pnrno.equals(prePnr)){
				hbwz(premap, m, "TKNO");
				hbwz(premap, m, "CJR");
				hbwz(premap, m, "HC");
				hbwz(premap, m, "DDBH");
				hbje(premap, m, "CGJE");
				String tkno=objToString(premap.get("TKNO"));
				premap.put("TKCOUNT", tkno.split(",").length);
			} else {
				premap = m;
				newList.add(m);
			}
		}
		return newList;
	}
	private static void myCreateCell(int cellnum, Object value, HSSFRow row, HSSFCell cell) {
	    if(value==null){
	    	cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_BLANK);
	    	cell.setCellValue("");  
	    }else{
			if(NumberUtils.isNumber(String.valueOf(value))){
				cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_NUMERIC);
		    	cell.setCellValue(NumberUtils.toDouble(String.valueOf(value))); 
			}else {
				cell=row.createCell(cellnum,HSSFCell.CELL_TYPE_STRING);
		    	cell.setCellValue(String.valueOf(value)); 
			}
		}
	}  
}
