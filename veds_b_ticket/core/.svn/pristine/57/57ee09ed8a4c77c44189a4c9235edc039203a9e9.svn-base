package org.vetech.core.business.pid.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.vetech.core.modules.utils.Arith;

/**
 * Parse指令工具类
 * @author  gengxianyan
 * @version  [版本号, Apr 11, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class ParseCommandUtil {

	public static Document parseXml(String xml) throws Exception{
		try {
			Document doc = DocumentHelper.parseText(xml);
			return doc;
		}catch (Exception e) {
			System.out.println(xml);
			throw new Exception("共享传的不是xml"+e.getMessage());
		}
	}
	
	/**
	 * 1.分割换行付 2.80个字符换行 3.去掉空行
	 * 格式化AVH返回的文本数据
	 */
	public static String formatCommand(String eterm) {
		if (StringUtils.isBlank(eterm)) {
			return "";
		}
		String[] s = eterm.split("\\r|\\n");
		StringBuffer tmp = new StringBuffer();

		for (int i = 0; i < s.length; i++) {
			if (s[i].length() > 80) {
				for (int j = 0; j < s[i].length() / 80; j++) {
					String tmp80 = StringUtils.substring(s[i], 2 * j * 80,
							(2 * j + 1) * 80);
					String tmp80after = StringUtils.substring(s[i],
							(2 * j + 1) * 80, (2 * j + 2) * 80);
					if (StringUtils.isNotBlank(tmp80)) {
						tmp.append(StringUtils.replace(tmp80, "\r", "") + "\r");
					}
					if (StringUtils.isNotBlank(tmp80after)) {
						tmp.append(StringUtils.replace(tmp80after, "\r", "")
								+ "\r");
					}
				}
			} else {
				if (StringUtils.isNotBlank(StringUtils.replace(s[i], "\r", ""))) {
					tmp.append(s[i] + "\r");
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 解析ssr指令算出该指令对应试不是儿童票
	 * @param command
	 * @return
	 */
	public static String getage(String command){
		return getssrZjhm(command);
	}
	
	/**
	 * 通过指令得到证件号码
	 * @return
	 */
	public static String getssrZjhm(String command){
		if(StringUtils.isBlank(command)){
			return "";
		}
		
		String ifssr="SSR\\s+FOID[^\r]+";
		Pattern sub_p = Pattern.compile(ifssr,Pattern.CASE_INSENSITIVE);
		Matcher sub_m = sub_p.matcher(command);
		while(sub_m.find()){
			String ssrNI = "(NI)([A-Z0-9()-]+)(?:/P(\\d+))?";
			Pattern pattern = Pattern.compile(ssrNI,Pattern.CASE_INSENSITIVE);
			String str = sub_m.group(0);
			Matcher matcher = pattern.matcher(str);
			if(matcher.find()){
				int age = getAge(matcher.group(2));
				if(age >= 2 && age < 12){
					return "<font color='red'></br>温馨提示：当前证件类型为儿童</font></br>";
				}
				
			}
		}
		return "";
	}
	
	/**
	 * 根据身份证计算周岁
	 */
	public static int getAge(String IDCardNum) {
		if (StringUtils.isBlank(IDCardNum)) {
			return 0;
		}
		if (IDCardNum.length() != 15 && IDCardNum.length() != 18) {
			return 0;
		}
		Calendar cal1 = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
		// 如果身份证15位则将其转化为18位
		if (IDCardNum.length() == 15)
			IDCardNum = getIDCard_18bit(IDCardNum);

		cal1.set(Integer.parseInt(IDCardNum.substring(6, 10)), Integer.parseInt(IDCardNum.substring(10, 12)), Integer
				.parseInt(IDCardNum.substring(12, 14)));
		return getYearDiff(today, cal1);
	}
	
	@SuppressWarnings("static-access")
	public static int getYearDiff(Calendar cal, Calendar cal1) {
		int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
		int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
		return (int) Arith.round(Arith.div(NumberUtils.toDouble((y * 12 + m) + ""), 12.0), 0);
	}

	/**
	 * 功能：把15位身份证转换成18位
	 * 
	 * @param id
	 * @return newid or id
	 */
	public static final String getIDCard_18bit(String id) {
		// 若是15位，则转换成18位；否则直接返回ID
		if (15 == id.length()) {
			final int[] W = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
			final String[] A = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
			int i, j, s = 0;
			String newid;
			newid = id;
			newid = newid.substring(0, 6) + "19" + newid.substring(6, id.length());
			for (i = 0; i < newid.length(); i++) {

				j = Integer.parseInt(newid.substring(i, i + 1)) * W[i];
				s = s + j;
			}
			s = s % 11;
			newid = newid + A[s];

			return newid;
		} else {
			return id;
		}

	}
	
	public static String outXml(Document doc) throws IOException {
		StringWriter out = null;
		String sReturn = "";
//		XMLOutputter outputter = new XMLOutputter();
		out = new StringWriter();
//		outputter.output(doc, out);
		sReturn = out.toString();
		out.close();
		return sReturn;
	}
	
}
