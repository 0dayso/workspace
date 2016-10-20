package org.vetech.core.business.tag;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.Lunar;
import org.vetech.core.modules.utils.Money;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

/**
 * VFN标签
 */
public class Function {

	/**
	 * 将当前时间转换为指定格式 如果是yyyyMMdd，注意字母y不能大写。
	 */
	public static String userDate(String sformat) {
		return VeDate.getUserDate(sformat);
	}

	public static String getTime() {
		return new Date().getTime() + "";
	}

	/**
	 * 获取当前时间yyyy-mm-dd
	 */
	public static String dateShort() {
		return VeDate.getStringDateShort();
	}

	/**
	 * 获取当前日期
	 * 
	 * 的前后推迟的天数，为负数表示前移，正数表示后推
	 */
	public static String dateShortPre(Integer day) {
		day = day == null ? 0 : day;
		String date = VeDate.getStringDateShort();
		return VeDate.getPreDay(date, day);
	}

	/**
	 * 获取当前时间yyyy-mm-dd HH:MM:SS
	 */
	public static String longDate() {
		return VeDate.getStringDate();
	}

	/**
	 * 获取当前时间HH:mm
	 * 
	 * @return
	 */
	public static String timeShort() {
		return VeDate.getTimeShort();
	}

	/**
	 * 日期前推或后推天数,其中day表示天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getPreDay(String date, Integer day) {
		return VeDate.getPreDay(date, day);
	}

	/**
	 * 时间前推后推多少分钟
	 */
	public static String getPreMin(String datetime, Integer min) {
		return VeDate.getPreMin(datetime, min);
	}

	/**
	 * 得到二个日期间的间隔天数
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public static int twoDay(String sj1, String sj2) {
		return VeDate.getTwoDay(sj1, sj2);
	}

	/**
	 * 2个日期间隔的秒钟数
	 * 
	 * @param sj1
	 * @param sj2
	 * @return
	 */
	public int twoSec(String sj1, String sj2) {
		sj1 = VeStr.KsrqString(sj1);
		sj2 = VeStr.KsrqString(sj2);
		return VeDate.getTwoSec(sj1, sj2);
	}

	/**
	 * 返回短格式星期：如周一
	 * 
	 * @param date
	 * @return
	 */
	public static String weekz(String date) {
		return VeDate.getWeekz(date);
	}

	public static String weekXq(String sdate) {
		return VeDate.getWeekXq(sdate);
	}

	public static String procStr(String source) {
		return VeStr.procStr(source);
	}

	/**
	 * 根据日期 获取农历 日
	 * 
	 * @param dateStr
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String lunarCalendar(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (!VeDate.isDate(dateStr)) {
			return null;
		}
		Date date = VeDate.strToDate(dateStr);
		Lunar lunar = new Lunar(date);
		if (StringUtils.isNotBlank(lunar.getLunarDayString())) {
			return lunar.getLunarDayString();
		} else {
			return "";
		}
	}

	/**
	 * <获取农历节气>
	 * 
	 * @param dateStr
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String lunarTermString(String dateStr) {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		if (!VeDate.isDate(dateStr)) {
			return null;
		}
		Date date = VeDate.strToDate(dateStr);
		Lunar lunar = new Lunar(date);
		if (StringUtils.isNotBlank(lunar.getTermString())) {
			return lunar.getTermString();
		} else {
			return "";
		}
	}

	/**
	 * 格式化数字和日期
	 * 
	 * @param d
	 * @param pattern
	 * @return
	 */
	public static String format(Object d, String pattern) {
		if (d == null) {
			return "";
		}
		Number n = null;
		if (d instanceof String) {
			n = NumberUtils.toDouble((String) d);
			return MessageFormat.format("{0,number," + pattern + "}", n);
		} else if (d instanceof Number) {
			return MessageFormat.format("{0,number," + pattern + "}", d);
		} else if (d instanceof Date) {
			if (StringUtils.isBlank(pattern)) {
				pattern = "yyyy-MM-dd HH:mm:ss";
			}
			return VeDate.formatToStr((Date) d, pattern);
		}
		return "";
	}

	/**
	 * 获取map中的值
	 */
	public static String mapValue(Map<String, String> datas, String key) {
		return datas.get(key);
	}

	/**
	 * 将对象转换json数据
	 */
	public static String toJSON(Object bean) {
		return JSONArray.fromObject(bean).toString();
	}

	public static String urie(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return VeStr.urlStrEncode(str, "UTF-8");
	}

	public static String urie(String str, String encode) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		return VeStr.urlStrEncode(str, encode);
	}

	public static String urlencoder(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		try {
			return java.net.URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String urid(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String uri = "";
		try {
			uri = java.net.URLDecoder.decode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return uri;
	}

	public static String toHtml(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String html = VeStr.toHtml(str);
		return html;
	}

	public static String unHtml(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		}
		String text = VeStr.unHtml(str);

		return text;
	}

	/**
	 * 取一个i长的随机数
	 * 
	 * @param i
	 * @return
	 */
	public static String Random(Integer i) {
		Random jjj = new Random();

		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 将人民币数值转换成大写
	 * 
	 * @param input
	 * @return
	 */
	public static String numtochinese(String input) {
		if (StringUtils.isBlank(input)) {
			return "0";
		}
		double d = new Double(input);
		String result = "";
		result = Money.getChnmoney(Math.abs(d) + "");
		if (d < 0) {
			result = "负" + result;
		}
		return result;
	}

	/**
	 * 将传入的字符串转换成Double型
	 * 
	 * @param input
	 * @return
	 */
	public static Double toNumber(String input) {
		// result =0;
		Double result = NumberUtils.toDouble(input, 0);
		return result;
	}

	/**
	 * 返回两个字符串中相同的字数在较短的字符串中的比例
	 * 
	 */
	public static double getSimilarity(String str1, String str2) {
		return VeStr.getSimilarity(str1, str2);
	}

	/**
	 * 保留小数位四舍五入，小数位不足补0
	 * 
	 * @param number
	 *            四舍五入的对像
	 * @param cacle
	 *            保留小数位
	 * @return
	 */
	public static String round(String number, String cacle) {
		if (!NumberUtils.isNumber(number) || !NumberUtils.isNumber(cacle)) {
			return "0";
		}
		if (NumberUtils.toDouble(number) == 0) {
			return "0";
		}
		if (NumberUtils.toInt(cacle) < 0) {
			return "0";
		}
		BigDecimal b = new BigDecimal(number);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, NumberUtils.toInt(cacle), BigDecimal.ROUND_HALF_UP) + "";
	}

	/**
	 * 保留小数位并且进行舍入操作，小数位不足补0
	 * 
	 * @param number
	 *            四舍五入的对像
	 * @param cacle
	 *            保留小数位
	 * @param method
	 *            舍入方式 eg：为4 表示4舍5入
	 * @return
	 */
	public static String round2(String number, String cacle, String method) {
		if (!NumberUtils.isNumber(number) || !NumberUtils.isNumber(cacle)) {
			return "0";
		}
		if (NumberUtils.toDouble(number) == 0) {
			return "0";
		}
		if (NumberUtils.toInt(cacle) < 0) {
			return "0";
		}
		int srfs = 4;

		if (NumberUtils.toInt(method) > 0) {
			srfs = NumberUtils.toInt(method);
		}

		number = Function.round(number, "5");
		int icacle = NumberUtils.toInt(cacle);
		double dnumber = NumberUtils.toDouble(number);

		double x = Math.pow(10, icacle);
		dnumber = Arith.mul(dnumber, x);
		int point = String.valueOf(dnumber).indexOf(".");
		if (point > -1) {
			String xs = String.valueOf(dnumber).substring(point + 1, point + 2);
			if (NumberUtils.toInt(xs) > srfs) {
				dnumber = Math.ceil(dnumber);
			} else {
				dnumber = Math.floor(dnumber);
			}
		}
		String val = String.valueOf(Arith.div(dnumber, x));
		// System.out.println(val);
		val = val.replaceAll("\\.{1}0{1,}$", "");
		return val;
	}

	/**
	 * 根据传入日期返回星期（英文）
	 */
	public static String getWeekEn(String sdate) {
		String s = VeDate.getWeekEn(sdate);
		return s;
	}

	/**
	 * 保证一次产生10000个随机数内无重复 平均一秒钟能产生250个随机数
	 */
	public static String getNo(String no) {
		if (!NumberUtils.isNumber(no) || Double.parseDouble(no) == 0) {
			return VeDate.getNo(6);
		} else if (Double.parseDouble(no) < 0) {
			return VeDate.getNo(6);
		} else {
			return VeDate.getNo(Integer.parseInt(no));
		}
	}

	/**
	 * add by zwm 20110308 for:判断图片是否存在
	 * 
	 * @param pic
	 *            图片路径
	 */
	public static boolean picExists(String pic) {
		if (StringUtils.isBlank(pic)) {
			return false;
		}
		String path = Function.class.getResource("/").getPath().toString();
		path = path.substring(0, path.indexOf("/WEB-INF/classes/"));
		String picpath = path + pic;
		File file = new File(picpath);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 阿拉伯数字转中文，只支持1-9
	 * 
	 * @param num
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String numToChinese(int num) {
		String str = null;
		switch (num) {
		case 1:
			str = "一";
			break;
		case 2:
			str = "二";
			break;
		case 3:
			str = "三";
			break;
		case 4:
			str = "四";
			break;
		case 5:
			str = "五";
			break;
		case 6:
			str = "六";
			break;
		case 7:
			str = "七";
			break;
		case 8:
			str = "八";
			break;
		case 9:
			str = "九";
			break;
		case 10:
			str = "十";
			break;
		default:
			break;
		}

		return str;
	}

	public static String getBirthDay(String cardNumber) {
		return VeStr.getBirthDateFromCard(cardNumber);
	}

	/**
	 * 将数字转换成大写英文字母 1==A 2==B 以此类推
	 */
	public static String convert4No(Integer no) {
		char tmp = (char) ("0".hashCode() + no + 16);
		return String.valueOf(tmp);
	}

	/**
	 * 将英文字母转换成数字 A或a==1 B或b==2 以此类推
	 */
	public static String convert4Word(String word) {
		char tmp = (char) (word.hashCode() - 16);
		return String.valueOf(tmp);
	}

	/**
	 * 去除头部与尾部的分隔符
	 * 
	 * @param str
	 * @return
	 */
	public static String formatStartAndEnd(String str, String separator) {
		if (StringUtils.isBlank(str))
			return "";
		if (StringUtils.isBlank(separator))
			return str;
		int length = separator.length();
		while (StringUtils.startsWith(str, separator)) {
			str = StringUtils.substring(str, length, str.length());
		}
		while (StringUtils.endsWith(str, separator)) {
			str = StringUtils.substring(str, 0, str.length() - length);
		}
		return StringUtils.trimToEmpty(str);
	}

	/**
	 * 截取字符串，并用span标签展示title
	 * 
	 * @param source
	 * @param len
	 * @return
	 */
	public static String cut(String source, Integer len) {
		source = StringUtils.trimToEmpty(source);
		if (StringUtils.isNotBlank(source)) {
			if (source.length() > len + 2) {
				return "<span title='" + VeStr.replace(source) + "'>" + source.substring(0, len) + "...</span>";
			}
		}
		return source;
	}

	/**
	 * 通过反射获取定义在DictEnum 中的dataMap
	 * 
	 * @param yw
	 * @return
	 */
	public static Collection<?> dictList(String yw) {
		try {
			Class<?> cls = Class.forName("cn.vetech.vedsb.utils.DictEnum$" + yw);
			Field[] fields = cls.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (Modifier.isStatic(field.getModifiers())) {
					if ("dataMap".equals(field.getName())) {
						Map<String, ?> dataMap = (Map<String, ?>) field.get(null);
						if (dataMap != null) {
							return dataMap.values();
						}
						return null;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(toJSON(dictList("XSTFPZT")));

	}

}
