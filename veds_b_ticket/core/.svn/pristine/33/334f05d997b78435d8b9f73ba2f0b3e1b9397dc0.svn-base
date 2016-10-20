package org.vetech.core.modules.utils;

/**
 * @author vesoft
 *
 */
import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URLEncoder;
import java.sql.Clob;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class VeStr {
	/**
	 * 是否全部是汉字
	 */
	public static boolean isChinese(String s) {
		Pattern p1 = Pattern.compile("^[\u4e00-\u9fa5]+$");
		Matcher m1 = p1.matcher(s);
		return m1.find();
	}

	/**
	 * 是否汉字
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		return (c + "").matches("[\u4E00-\u9FA5]");
	}

	/**
	 * 判断字符串是否全数字
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isDigit(String validString) {
		return NumberUtils.isDigits(validString);
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isDouble(String validString) {
		return NumberUtils.isNumber(validString);
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isChar(String validString) {
		if (validString == null)
			return false;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if ((tempbyte[i] < 48) || ((tempbyte[i] > 57) & (tempbyte[i] < 65)) || (tempbyte[i] > 122) || ((tempbyte[i] > 90) & (tempbyte[i] < 97))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param validString
	 * @return
	 */
	public static boolean isLetter(String validString) {
		if (validString == null)
			return false;
		byte[] tempbyte = validString.getBytes();
		for (int i = 0; i < validString.length(); i++) {
			if ((tempbyte[i] < 65) || (tempbyte[i] > 122) || ((tempbyte[i] > 90) & (tempbyte[i] < 97))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String Replace(String source, String oldString, String newString) {
		if (source == null)
			return null;
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource) {
			output.append(source.substring(posStart));
		}
		return output.toString();
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtml(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = Replace(s, "\r\n", "\n");
		// s = Replace(s,"\"","'");
		s = Replace(s, "\\n", "<br>");
		s = Replace(s, "\n", "<br>");
		s = Replace(s, "  ", "&nbsp;&nbsp;");
		// s = Replace(s,"","'");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = clearToXml(s);
		return s;
	}

	/**
	 * 替换特殊字符，防止对JS传参造成影响
	 * 
	 * @param s
	 * @return
	 */
	public static String replace(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "'", "&#39;");
		s = clearToXml(s);
		return s;
	}

	public static String clearToXml(String str) {
		char[] a = new char[str.length()];
		for (int index = 0; index < str.length(); index++) {
			a[index] = str.charAt(index);
			if ((int) a[index] < 32) {
				a[index] = 32;
			}
		}
		return String.valueOf(a);
	}

	/**
	 * 格式化页面输入值存入数据库 可用作XML格式化
	 * 
	 * @param s
	 * @return
	 */
	public static String formatHtml(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		s = s.trim();
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		return s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String toHtml1(String s) {
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\n", "");
		s = Replace(s, "  ", "&nbsp;&nbsp;");
		// s = Replace(s,"","'");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\\", "&#92;");
		return s;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String unHtml(String s) {
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "<br/>", "\n");
		s = Replace(s, "<br />", "\n");
		s = Replace(s, "<br >", "\n");
		s = Replace(s, "</br>", "\n");
		s = Replace(s, "<BR>", "\n");
		s = Replace(s, "<BR/>", "\n");
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&gt;", ">");
		return s;
	}

	/**
	 * 去掉html语法 <>中间的内容<br>
	 * <b></b> <span></span> <a href=""></a>
	 * 
	 * @param html
	 * @return
	 */
	public static String clearHtml(String html) {
		if (StringUtils.isBlank(html)) {
			return "";
		}

		return html.replaceAll("<[^>]+>", "");
	}

	/*
	 * 替换XML中不能有的特殊字符
	 */
	public static String toXmlFormat(String s) {
		if (StringUtils.isBlank(s)) {
			return s;
		}
		// &lt; < 小于号
		// &gt; > 大于号
		// &amp; & 和,与
		// &apos; ' 单引号
		// &quot; " 双引号
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "'", "&apos;");
		s = Replace(s, "\"", "&quot;");
		return s;
	}

	/**
	 * 把number格式化为正常的字符串，去除科学计数法
	 * 
	 * @param n
	 * @return
	 */
	public static String formatNumber(Number n) {
		if (n == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("#.######");
		String num = df.format(n);
		return num;
	}

	/**
	 * 补齐开始日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param ksrq
	 * @return String
	 */
	public static String KsrqString(String ksrq) {
		String newKsrq = "";
		// 形参字符串长度
		Integer Stringlength = ksrq.trim().length();
		switch (Stringlength) {
		case 10:
			newKsrq = ksrq + " 00:00:00";
			break;
		case 13:
			newKsrq = ksrq + ":00:00";
			break;
		case 16:
			newKsrq = ksrq + ":00";
			break;
		default:
			newKsrq = ksrq;
			break;
		}
		return newKsrq;
	}

	/**
	 * 补齐结束日期到秒.使其格式为YYYY-MM-DD HH24:MI:SS
	 * 
	 * @param ksrq
	 * @return String
	 */
	public static String JsrqString(String jsrq) {
		String newJsrq = "";
		// 形参字符串长度
		Integer Stringlength = jsrq.trim().length();
		switch (Stringlength) {
		case 10:
			newJsrq = jsrq + " 23:59:59";
			break;
		case 13:
			newJsrq = jsrq + ":59:59";
			break;
		case 16:
			newJsrq = jsrq + ":59";
			break;
		default:
			newJsrq = jsrq;
			break;
		}
		return newJsrq;
	}

	/**
	 * Description 将数字金额转换为中文金额
	 */
	public static String DoNumberCurrencyToChineseCurrency(BigDecimal bigdMoneyNumber) {
		// 中文金额单位数组
		String[] straChineseUnit = new String[] { "分", "角", "圆", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟" };
		// 中文数字字符数组
		String[] straChineseNumber = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String strChineseCurrency = "";
		// 零数位标记
		boolean bZero = true;
		// 中文金额单位下标
		int ChineseUnitIndex = 0;
		try {
			if (bigdMoneyNumber.intValue() == 0)
				return "零圆整";
			// 处理小数部分，四舍五入
			double doubMoneyNumber = Math.round(bigdMoneyNumber.doubleValue() * 100);
			// 是否负数
			boolean bNegative = doubMoneyNumber < 0;
			// 取绝对值
			doubMoneyNumber = Math.abs(doubMoneyNumber);
			// 循环处理转换操作
			while (doubMoneyNumber > 0) {
				// 整的处理(无小数位)
				if (ChineseUnitIndex == 2 && strChineseCurrency.length() == 0)
					strChineseCurrency = strChineseCurrency + "整";
				// 非零数位的处理
				if (doubMoneyNumber % 10 > 0) {
					strChineseCurrency = straChineseNumber[(int) doubMoneyNumber % 10] + straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
					bZero = false;
				}
				// 零数位的处理
				else {
					// 元的处理(个位)
					if (ChineseUnitIndex == 2) {
						// 段中有数字
						if (doubMoneyNumber > 0) {
							strChineseCurrency = straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
							bZero = true;
						}
					}
					// 万、亿数位的处理
					else if (ChineseUnitIndex == 6 || ChineseUnitIndex == 10) {
						// 段中有数字
						if (doubMoneyNumber % 1000 > 0)
							strChineseCurrency = straChineseUnit[ChineseUnitIndex] + strChineseCurrency;
					}
					// 前一数位非零的处理
					if (!bZero)
						strChineseCurrency = straChineseNumber[0] + strChineseCurrency;

					bZero = true;
				}
				doubMoneyNumber = Math.floor(doubMoneyNumber / 10);
				ChineseUnitIndex++;
			}
			// 负数的处理
			if (bNegative)
				strChineseCurrency = "负" + strChineseCurrency;
		} catch (Exception e) {
			e.printStackTrace();
			return "金额超出限制,请检查金额是否正确!";
		}
		return strChineseCurrency;
	}

	/**
	 * 处理特殊字符串。 用于过滤字符串在javascript中报错的情况 把" ' \ 替换为中文符号
	 * 
	 * @param source
	 * @return
	 */
	public static String procStr(String source) {
		if (StringUtils.isBlank(source)) {
			return source;
		}
		try {
			source = source.replaceAll("\n", "");
			source = source.replaceAll("\r", "");
			source = source.replaceAll("\"", "“");
			source = source.replaceAll("'", "‘");
			source = source.replace("\\", "＼");
			source = source.replace(";", "；");
		} catch (Exception e) {
			e.printStackTrace();
			return source;
		}
		return source.trim();
	}

	/**
	 * 处理Bean所有属性为String的首尾空格和单引号
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void replaceStringBeginEndSpace(Object bean) throws IllegalAccessException, InvocationTargetException {
		if (bean == null) {
			return;
		}
		Class c = bean.getClass();
		Field field[] = c.getDeclaredFields();
		for (int j = 0; j < field.length; j++) {
			if ("java.lang.String".equals(field[j].getType().getName())) {
				String name = field[j].getName();
				String value = "";
				try {
					value = BeanUtils.getProperty(bean, name);
				} catch (NoSuchMethodException e) {
				}
				if (StringUtils.isNotEmpty(value)) {
					value = procStr(value);
					BeanUtils.setProperty(bean, name, value);
				}
			}
		}
	}

	/**
	 * 去掉首尾空格和'单引号
	 * 
	 * @param s
	 * @return
	 */
	public static String filterStr(String s) {
		if (StringUtils.isNotBlank(s)) {
			s = s.replace("'", "");
		}
		return StringUtils.trim(s);
	}

	public static String formatXmlTag(String s) {
		if (StringUtils.isBlank(s)) {
			return "";
		}
		String p = "<[^>]+>";
		Pattern p1 = Pattern.compile(p);
		Matcher m1 = p1.matcher(s);
		while (m1.find()) {
			String old = m1.group(0);
			String tmp = old.replace("\r", "").replace("\n", "");
			s = s.replace(old, tmp);
		}
		return s;

	}

	/**
	 * 获得左边正确的汉字的位置
	 * 
	 * @param source
	 *            原始字符串
	 * @param maxByteLen
	 *            截取的字节数
	 * @param flag
	 *            表示处理汉字的方式。1表示遇到半个汉字时补全，-1表示遇到半个汉字时舍
	 */
	public static int leftindex(String source, int maxByteLen, int flag) {
		if (source == null || maxByteLen <= 0) {
			return 0;
		}
		byte[] bStr = source.getBytes();
		if (maxByteLen >= bStr.length)
			return bStr.length;
		String cStr = new String(bStr, maxByteLen - 1, 2);
		if (cStr.length() == 1 && source.contains(cStr)) {
			maxByteLen += flag;
		}
		return maxByteLen;
	}

	/**
	 * 返回两个字符串中相同的字数在较短的字符串中的比例
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static double getSimilarity(String str1, String str2) {
		int length1 = str1.length();
		int length2 = str2.length();
		if (length1 == 0 || length2 == 0) {
			return 0;
		}
		int k = 0;
		for (int i = 0; i < length1; i++) {
			for (int j = 0; j < length2; j++) {
				if (str1.substring(i, i + 1).equals(str2.substring(j, j + 1))) {
					k++;
					break;
				}
			}
		}
		if (length1 >= length2) {
			return k / (double) length2;
		} else {
			return k / (double) length1;
		}
	}

	public static String proctel(String tel) {
		tel = StringUtils.trimToEmpty(tel);
		if (StringUtils.isNotBlank(tel)) {
			// tel = tel.replaceAll("-", "");
			tel = tel.replaceAll("/", "");
			tel = tel.replaceAll("^\\D*", "");// 开头的非数字替换为空
			tel = tel.replaceAll("\\D*$", "");// 结尾的非数字替换为空
			tel = tel.replaceAll("(\\D^-)+", ",");// 中间的非数字替换,
		}
		return tel;
	}

	public static String getBirthDateFromCard(String cardNumber) {
		if (StringUtils.isBlank(cardNumber)) {
			return "";
		}
		String card = cardNumber.trim();
		String year = "";
		String month = "";
		String day = "";
		if (card.length() == 18) { // 处理18位身份证
			year = card.substring(6, 10);
			month = card.substring(10, 12);
			day = card.substring(12, 14);
		} else if (card.length() == 15) { // 处理非18位身份证
			year = card.substring(6, 8);
			month = card.substring(8, 10);
			day = card.substring(10, 12);
			year = "19" + year;
		} else {
			return "";
		}
		if (month.length() == 1) {
			month = "0" + month; // 补足两位
		}
		if (day.length() == 1) {
			day = "0" + day; // 补足两位
		}
		return year + "-" + month + "-" + day;
	}

	/**
	 * 通过身份证号码获取性别 女返回0 男返回1 其他不合法的返回空
	 * 
	 * 
	 * @param cardNumber
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getGenderFromCard(String cardNumber) {
		if (StringUtils.isBlank(cardNumber)) {
			return "";
		}
		String card = StringUtils.trimToEmpty(cardNumber);
		String xb = "";
		if (card.length() == 18) { // 处理18位身份证
			xb = StringUtils.substring(card, 16, 17);
		} else if (card.length() == 15) { // 处理非18位身份证
			xb = StringUtils.substring(card, 14, 15);
		} else {
			return "";
		}
		if (!NumberUtils.isDigits(xb)) {
			return "";
		}
		// 奇数是男
		if (NumberUtils.toInt(xb) % 2 == 0) {
			return "0";
		} else {
			return "1";
		}
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

		cal1.set(Integer.parseInt(IDCardNum.substring(6, 10)), Integer.parseInt(IDCardNum.substring(10, 12)), Integer.parseInt(IDCardNum.substring(12, 14)));
		return getYearDiff(today, cal1);
	}

	public static int getYearDiff(Calendar cal, Calendar cal1) {
		int m = (cal.get(cal.MONTH)) - (cal1.get(cal1.MONTH));
		int y = (cal.get(cal.YEAR)) - (cal1.get(cal1.YEAR));
		System.out.println((int) Arith.round(Arith.div(NumberUtils.toDouble((y * 12 + m) + ""), 12.0), 0));
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

	/**
	 * 得到服务器的网卡IP地址
	 * 
	 * @return
	 */
	public static List<String> getLocalhost() {
		List list = new ArrayList();
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				Enumeration<?> e2 = ni.getInetAddresses();
				while (e2.hasMoreElements()) {
					String ip = "";
					InetAddress ia = (InetAddress) e2.nextElement();
					if (ia instanceof Inet6Address)
						continue;
					ip = ia.getHostAddress();
					list.add(ip);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 判断IP是否是内网IP true是内网 false是外网
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean isNwIP(String ip) {
		List l = getLocalhost();
		if (l.contains(ip)) {
			return true;
		}
		return false;
	}

	/**
	 * 汉字转码，全角汉字转换成半角汉字
	 * 
	 * @param QJstr
	 * @return
	 */
	public static final String SBCchange(String QJstr) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[2] == -1) {
				b[3] = (byte) (b[3] + 32);
				b[2] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr;
	}

	/**
	 * 从城市的list map中取一个编号相同的map
	 * 
	 * @param dlmc
	 *            城市名称获取MAP
	 * @param city
	 *            城市list 一般是在application中的
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map acity(String csmc, List<Map> city) {
		if (city == null || city.size() < 1) {
			return null;
		}

		for (int i = 0; i < city.size(); i++) {
			Map m = city.get(i);
			if (csmc.equals((String) m.get("CSMC"))) {
				return m;
			}
		}
		return null;

	}

	/**
	 * 汉字替换为空
	 * 
	 * @param source
	 * @param des
	 * @return
	 */
	public static String replaceHz(String source, String des) {
		if (StringUtils.isNotBlank(source)) {
			String regEx = "[\\W]";
			Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
			Matcher mat = pat.matcher(source);
			String s = mat.replaceAll(des);
			return s;
		}
		return "";
	}

	/**
	 * 截取字符串的字节数
	 * 
	 * @param str
	 * @param len
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String substrByte(String str, int len) {
		if (str == null) {
			return "";
		}
		// 数据库GBK编码的，把java中字符串转为GBK，这样每个汉字只会占用2byte。
		byte[] strByteGBK = null;
		try {
			strByteGBK = str.getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(strByteGBK == null){
			return "";
		}
		int strLen = strByteGBK.length;
		if (len >= strLen || len < 1) {
			return str;
		}
		byte[] strByte = str.getBytes();
		String sub = new String(strByte, 0, len);
		// 如果截取了半个字符，在原始字符中肯定不存在，那么可以替换掉
		if (str.indexOf(sub.charAt(sub.length() - 1)) < 0) {
			sub = sub.substring(0, sub.length() - 1);
		}
		return sub;
	}

	/**
	 * clob转string
	 * 
	 * @param clob
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String clobToString(Clob clob) throws Exception {
		StringBuffer sb = new StringBuffer();
		Reader rd = null;
		BufferedReader bfrd = null;

		try {
			if (clob != null) {
				rd = clob.getCharacterStream();
				bfrd = new BufferedReader(rd);

				if (bfrd != null) {
					String s = null;
					while ((s = bfrd.readLine()) != null) {
						sb.append(s);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bfrd != null) {
				bfrd.close();
			}
			if (rd != null) {
				rd.close();
			}
		}

		return sb.toString();
	}

	/**
	 * 按照javascript的escape函数转换字符
	 * 
	 * @param src
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j) || "*+-./@_".contains(j + ""))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16).toUpperCase());
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16).toUpperCase());
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	public static String getValue(Map map, String name) {
		Object o = map.get(name);
		return o == null ? "" : StringUtils.trimToEmpty(String.valueOf(o));
	}

	static String[] emb = new String[] { "9", "6", "5", "7", "4", "2", "8", "1", "0", "3" };
	static int[] dmb = new int[] { 8, 7, 5, 9, 4, 2, 1, 3, 6, 0 };

	// 0 1 2 3 4 5 6 7 8 9
	public static String getEncode(String mobile) {
		String sub = StringUtils.substring(mobile, 0, 7);
		int xh = NumberUtils.toInt(StringUtils.substring(mobile, 6, 7));
		String kk = "";
		for (int i = 0; i < 4; i++) {
			int num = NumberUtils.toInt(StringUtils.substring(mobile, 7, 8 + i));
			int b = (xh + num + i) % 10;
			kk += emb[b];
		}
		return sub + kk;
	}

	public static String getDecode(String mobile) {
		String sub = StringUtils.substring(mobile, 0, 7);
		int xh = NumberUtils.toInt(StringUtils.substring(mobile, 6, 7));
		String kk = "";
		for (int i = 0; i < 4; i++) {
			int num = NumberUtils.toInt(StringUtils.substring(mobile, 7 + i, 8 + i));

			// int b = (xh + x) % 10;
			// System.out.println(num);
			int x = 10 + dmb[num] - xh - i;
			if (x >= 10) {
				x = x % 10;
			}
			kk += x;

		}
		return sub + kk;
	}

	/**
	 * 把字符串排序并去掉重复
	 * 
	 * @param source
	 * @param split
	 * @return
	 */
	public static String sortString(String source, String split) {
		if (StringUtils.isBlank(source)) {
			return "";
		}
		String[] elements = source.split(split);
		if (elements == null || elements.length < 1)
			return "";
		Set<String> set = new TreeSet<String>();
		for (String e : elements) {
			set.add(e);
		}
		return StringUtils.join(set, split);
	}

	/**
	 * 2个字符串取交集 从sum中移除不存在于 all中的字符
	 * 
	 * @param all
	 * @param sum
	 * @param split
	 * @return
	 */
	public static String strRetainAll(String all, String sum, String split) {
		if (StringUtils.isBlank(sum) || StringUtils.isBlank(all)) {
			return "";
		}
		String[] alls = all.split(split);
		String[] sums = sum.split(split);
		Set<String> allset = new TreeSet<String>();
		for (String e : alls) {
			allset.add(e);
		}
		Set<String> sumset = new TreeSet<String>();
		for (String e : sums) {
			sumset.add(e);
		}
		sumset.retainAll(allset);
		return StringUtils.join(sumset, split);
	}

	/**
	 * 等分数组
	 * 
	 * @param data
	 *            [] 需要等分的数组
	 * @param count
	 *            每个等分的大小
	 * @return<T> T[]
	 */
	public static <T> List<T[]> partArray(T[] data, int count) {
		int size = data.length;
		List<T[]> list = new ArrayList<T[]>();
		if (count >= size) {
			list.add(data);
			return list;
		}
		for (int from = 0; from < size; from += count) {
			int to = Math.min(from + count, size);
			T[] a = Arrays.copyOfRange(data, from, to);
			list.add(a);
		}
		return list;
	}

	/**
	 * 将list按blockSize大小等分
	 * 
	 * @param list
	 * @param blockSize
	 * @return
	 */
	public static <T> List<List<T>> partList(List<T> list, int blockSize) {
		List<List<T>> lists = new ArrayList<List<T>>();
		if (list != null && blockSize > 0) {
			int listSize = list.size();
			if (listSize <= blockSize) {
				lists.add(list);
				return lists;
			}
			int batchSize = listSize / blockSize;
			int remain = listSize % blockSize;
			for (int i = 0; i < batchSize; i++) {
				int fromIndex = i * blockSize;
				int toIndex = fromIndex + blockSize;
				lists.add(list.subList(fromIndex, toIndex));
			}
			if (remain > 0) {
				lists.add(list.subList(listSize - remain, listSize));
			}
		}
		return lists;
	}

	/**
	 * 返回数组的下标对象，如果超过下标则返回空
	 * 
	 * @param t
	 * @param index
	 * @return
	 */
	public static <T> T getArray(T[] t, int index) {
		if (t == null || t.length < index + 1) {
			return null;
		}
		return t[index];
	}

	/**
	 * 把URL中的中文转为url编码
	 * 
	 * @param url
	 * @param encode
	 * @return
	 */
	public static String urlStrEncode(String url, String encode) {
		Matcher matcher = Pattern.compile("[\\u4e00-\\u9fa5]").matcher(url);
		while (matcher.find()) {
			String tmp = matcher.group();
			try {
				url = url.replaceAll(tmp, URLEncoder.encode(tmp, encode));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	/**
	 * 将url参数转换成map
	 * 
	 * @param param
	 *            aa=11&bb=22&cc=33
	 * @return
	 */
	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<String, String>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

	/**
	 * 将map转换成url
	 * 
	 * @param map
	 * @return
	 */
	public static String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	/**
	 * 从url中获取一个name对应的值
	 * 
	 * @param url
	 * @param p
	 * @return
	 */
	public static String getParam(String url, String p) {
		if (url == null) {
			return "";
		}
		String tmp = url.endsWith("&") ? url : url + "&";
		p = p.toUpperCase();
		String search = "&" + p + "=";
		int pindex = tmp.toUpperCase().indexOf(search);
		if (pindex < 0) {
			search = "?" + p + "=";
			pindex = tmp.toUpperCase().indexOf(search);
		}
		if (pindex < 0) {
			search = p + "=";
			pindex = tmp.toUpperCase().indexOf(search);
		}
		if (pindex >= 0) {
			String pp = tmp.substring(pindex + search.length());
			pp = pp.substring(0, pp.indexOf("&"));
			return pp;
		} else {
			return "";
		}
	}

	public static void main(String[] args) {
		// 原始字符串
		String s = "我ZWR爱JAVA";
		System.out.println("原始字符串：" + s);
		try {
			System.out.println("截取前1位：" + substrByte(s, 1));
			// System.out.println("截取前2位：" + substrByte(s, 2));
			System.out.println("截取前4位：" + substrByte(s, 4));
			// System.out.println("截取前6位：" + substrByte(s, 6));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
