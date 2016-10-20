package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * excel导入验证  必填验证
 * @author vetech
 *
 */
public class ValidationUtil {
	
	/**
	 * 验证舱位，多个用/隔开,---表示全部
	 * 正确格式如：F 或 F/Y 或 ---
	 * @param cws
	 * @return
	 */
	public static boolean isCws(String cws) {
		if(StringUtils.isBlank(cws)){
			return false;
		}
		if("---".equals(cws)){
			return true;
		}
		String[] cwArr = cws.split("/",-1);
		for(String t_cw:cwArr){
			if(StringUtils.isBlank(t_cw)){
				return false;
			}
			if(!isCw(t_cw)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证舱位
	 * @param t_cw
	 * @return
	 */
	private static boolean isCw(String t_cw) {
		Pattern pattern = Pattern.compile("^[A-Z]{1}[0-9]{0,1}$"); 
		   Matcher isCw = pattern.matcher(t_cw);
		   if(!isCw.matches() ){
		       return false; 
		   } 
		return true; 
	}
	
	/**
	 * 是否为数字，不包含负数，小数
	 * @param ldStr
	 * @return
	 */
	public static boolean isNumericLoose(String ldStr) {
		Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(ldStr);
		   if(!isNum.matches() ){
		       return false; 
		   } 
		return true; 
	}
}
