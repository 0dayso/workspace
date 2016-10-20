package org.vetech.core.business.pid.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.VeDate;
import org.vetech.core.modules.utils.VeStr;

/**
 * 预定工具类
 * 
 * @author 章磊
 * 
 */
@SuppressWarnings({"unused","unchecked"})
public class BookUtil {
	private static String[] m_nMonth = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV",
			"DEC" };

	private static String[] mMonth = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

	// 判断空给默认值
	public static String defualtValue(String value, String defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

	public static boolean isBlank(String value) {
		if (StringUtils.isBlank(value) || "null".equals(value)) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param date
	 * @return 13SEP
	 */
	public static String dateCommandDay(String date) {
		String nmonth = date.substring(5, 7); // 获取月份：09
		String nDate = date.substring(8, 10); // 获取日期：13
		String nyear = date.substring(0, 4); // 获取年份
		if (StringUtils.isNotBlank(nyear)) {
			if (nyear.length() == 4)
				nyear = nyear.substring(2, 4); // 获取年份的后两位:07
			// 将月和日期转换成13SEP:13是日期,SEP是月份,m_nMonth是个的数组包含了日期字符串，
			// 该类的最上面
			String EnMonth = m_nMonth[Integer.parseInt(nmonth) - 1];
			// nAVHValue="AVH/XIYPEK/13SEP/07"
			return nDate + EnMonth;
		}
		return null;
	}

	/**
	 * 
	 * @param date
	 * @return 13SEP08
	 */
	public static String dateCommandTime(String date) {
		if (date == null || date.length() != 10 || !VeDate.isRightDate(date, "yyyy-MM-dd")) {
			return null;
		}
		String nmonth = date.substring(5, 7); // 获取月份：09
		String nDate = date.substring(8, 10); // 获取日期：13
		String nyear = date.substring(0, 4); // 获取年份
		if (StringUtils.isNotBlank(nyear)) {
			if (nyear.length() == 4)
				nyear = nyear.substring(2, 4); // 获取年份的后两位:07
			// 将月和日期转换成13SEP:13是日期,SEP是月份,m_nMonth是个的数组包含了日期字符串，
			// 该类的最上面
			String EnMonth = m_nMonth[Integer.parseInt(nmonth) - 1];
			// nAVHValue="AVH/XIYPEK/13SEP/07"
			return nDate + EnMonth + nyear;
		}
		return null;
	}
	
	/**
	 * 
	 * @param date
	 * @return SEP08
	 */
	public static String dateCommandYear(String date) {
		String nmonth = date.substring(5, 7); // 获取月份：09
		String nDate = date.substring(8, 10); // 获取日期：13
		String nyear = date.substring(0, 4); // 获取年份
		if (StringUtils.isNotBlank(nyear)) {
			if (nyear.length() == 4)
				nyear = nyear.substring(2, 4); // 获取年份的后两位:07
			// 将月和日期转换成13SEP:13是日期,SEP是月份,m_nMonth是个的数组包含了日期字符串，
			// 该类的最上面
			String EnMonth = m_nMonth[Integer.parseInt(nmonth) - 1];
			// nAVHValue="AVH/XIYPEK/13SEP/07"
			return EnMonth + nyear;
		}
		return null;
	}
	
	/**
	 * 将中文转换成拼音
	 * 
	 * @param zhongwen
	 * @return
	 */
	public static String mhPingying(String zhongwen) {
		zhongwen = StringUtils.trimToEmpty(zhongwen);
		String pinyin = "";
		if (StringUtils.isBlank(zhongwen)) {
			return "";
		}
		if (zhongwen.indexOf("/") > -1) {
			return zhongwen;
		}
		try {
			for (int i = 0; i < zhongwen.length(); i++) {
				char z = zhongwen.charAt(i);
				HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
				outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
				outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
				String pinyinArray = null;

				try {
					if (VeStr.isChinese(z + "")) {
						pinyinArray = PinyinHelper.toHanyuPinyinStringArray(z, outputFormat)[0];
					} else {
						pinyinArray = z + "";
					}

					if (i == 1) {
						pinyin = pinyin + "/" + pinyinArray;
					} else {
						pinyin = pinyin + pinyinArray;
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {

					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			return zhongwen;
		}
		return pinyin;
	}
	
	/**
	 * 如果起飞时间在当天的，订票时间在飞机起飞2小时以前的，预留2小时出票时限； 订票时间在飞机起飞2小时以内的，预留40分钟出票时限；如果订票时间在飞机起飞40分钟内不能进行订票
	 * 如果是非当天的，订票时限应设置为航班起飞前一天的23:59分
	 * 
	 * @param cfdate 
	 * @param cfsjp 
	 * @param bookType 
	 * @return [参数说明] 
	 */
	public String tkt2(String cfdate, String cfsjp, String bookType) {
		String orderDateTime = VeDate.getStringDate();
		// 当前时间向前进2小时
		String orderDateTime1 = VeDate.getPreMin(orderDateTime, 120);
		// 出发时间
		String cfsj = etermTime(getFormheadTodate(cfdate), StringUtils.substring(cfsjp, 0, 2) + ":" + StringUtils.substring(cfsjp, 2, 4));
		double fz = VeDate.getTwoMin(orderDateTime1, cfsj);
		String dayone = orderDateTime.substring(0, 10);
		String daytwo = cfsj.substring(0, 10);
		int ylsxfz = 0;
		String time = "";
		if (dayone.equals(daytwo)) {
			if (fz > 0) {
				ylsxfz = -40;
				time = cfsj;
			} else {
				time = StringUtils.substring(orderDateTime, 0, 16);
				ylsxfz = 120;
			}
		} else {
			String cfrq = VeDate.getFutureDay(daytwo, "yyyy-MM-dd", -1);
			time = cfrq + " 23:59:59";
			ylsxfz = 0;
		}
		String timeLimit = "";
		if ("1".equals(bookType)) {
			timeLimit = getTktlDate(time.substring(0, 10), StringUtils.substring(time, 11, 13) + ":"
					+ StringUtils.substring(time, 14, 16), ylsxfz);
		} else {
			timeLimit = getTktlTime(StringUtils.substring(time, 0, 10), StringUtils.substring(time, 11, 13)
					+ ":" + StringUtils.substring(time, 14, 16), ylsxfz) + "/XXXXXX";

		}
		return timeLimit;
	}
	

	/**
	 * 日期格式转换
	 * @param commandDate 10sep20
	 * @return [参数说明] 2020-09-10
	 */
	public static String getFormheadTodate(String commandDate) {
		commandDate = StringUtils.upperCase(commandDate);
		String datetime = VeDate.getStringDateShort();
		String now_month = datetime.substring(5, 7);
		String into_month = commandDate.substring(commandDate.length() - 3, commandDate.length());
		String into_month1 = "0";
		int year = NumberUtils.toInt(VeDate.getStringDateShort().substring(2, 4));
		if (StringUtils.isNotBlank(commandDate)) {
			// 3jan
			if (commandDate.length() == 4) {
				into_month1 = commandMonth(into_month);
				int into_month2 = NumberUtils.toInt(into_month1);
				int now_month2 = NumberUtils.toInt(now_month);
				if (now_month2 > into_month2) {
					year = year + 1;
				}
				return commandDate("0" + commandDate, year + "");
			}
			// 23jan
			if (commandDate.length() == 5) {
				into_month1 = commandMonth(into_month);
				int into_month2 = NumberUtils.toInt(into_month1);
				int now_month2 = NumberUtils.toInt(now_month);
				if (now_month2 > into_month2) {
					year = year + 1;
				}
				return commandDate(commandDate, year + "");
			}
			// 3jan10
			if (commandDate.length() == 6) {
				return commandDate("0" + StringUtils.substring(commandDate, 0, 4), StringUtils.substring(
						commandDate, 4, 6));
			}
			// 23jan10
			if (commandDate.length() == 7) {
				return commandDate(StringUtils.substring(commandDate, 0, 5), StringUtils.substring(
						commandDate, 5, 7));
			}
		}
		return commandDate;
	}
	
	/**
	 * 传入英文月份返回数字月份
	 * @param month 月
	 * @return [参数说明]
	 */
	public static String commandMonth(String month) {
		if (month.equals("JAN")) {
			month = "01";
		} else if (month.equals("FEB")) {
			month = "02";
		} else if (month.equals("MAR")) {
			month = "03";
		} else if (month.equals("APR")) {
			month = "04";
		} else if (month.equals("MAY")) {
			month = "05";
		} else if (month.equals("JUN")) {
			month = "06";
		} else if (month.equals("JUL")) {
			month = "07";
		} else if (month.equals("AUG")) {
			month = "08";
		} else if (month.equals("SEP")) {
			month = "09";
		} else if (month.equals("OCT")) {
			month = "10";
		} else if (month.equals("NOV")) {
			month = "11";
		} else if (month.equals("DEC")) {
			month = "12";
		}
		return month;
	}
	
	/**
	 * 转成指令日期格式
	 * @param date 13SEP
	 * @param year 99
	 * @return [参数说明]
	 */
	public static String commandDate(String date, String year) {
		String month = date.substring(2);
		String day = date.substring(0, 2);
		if (month.equals("JAN")) {
			month = "01";
		} else if (month.equals("FEB")) {
			month = "02";
		} else if (month.equals("MAR")) {
			month = "03";
		} else if (month.equals("APR")) {
			month = "04";
		} else if (month.equals("MAY")) {
			month = "05";
		} else if (month.equals("JUN")) {
			month = "06";
		} else if (month.equals("JUL")) {
			month = "07";
		} else if (month.equals("AUG")) {
			month = "08";
		} else if (month.equals("SEP")) {
			month = "09";
		} else if (month.equals("OCT")) {
			month = "10";
		} else if (month.equals("NOV")) {
			month = "11";
		} else if (month.equals("DEC")) {
			month = "12";
		}
		String y = VeDate.getStringDateShort();

		if (StringUtils.isBlank(year)) {
			return y.substring(0, 4) + "-" + month + "-" + day;
		} else {
			y = y.substring(0, 2);
			return y + year + "-" + month + "-" + day;
		}
	}
	
	/**
	 * 根据身份证号码获取出生日期
	 * @param cardNumber  身份证号码
	 * @return [参数说明]
	 */
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
	 * 返回tktl时间，包括IBE和eterm的 预留时限逻辑 余学锋 10:33:36 我觉得本质需求是这样的 余学锋 10:33:44 实际上只有两种需求：
	 * 1，如果从下单时间点向后推两个小时+30分钟超过了起飞时间的话，就从起飞时间点向前推40分钟作为预留时限 胜意客服七号 9:35:22
	 * 2，如果从下单时间点向后推两个小时+30分钟没有超过起飞时间的话，就从下单时间点向后推2个小时 余学锋 10:34:42 搞好了后，给云南HHT通知一声
	 * 
	 * @param cfdate 
	 * @param cfsjp 
	 * @param bookType 
	 * @return [参数说明] 
	 */
	public String tktl(String cfdate, String cfsjp, String bookType) {
		String orderDateTime = VeDate.getStringDate();
		String orderDateTime1 = VeDate.getPreMin(orderDateTime, 150);
		String cfsj = etermTime(getFormheadTodate(cfdate), StringUtils.substring(cfsjp, 0, 2) + ":"
				+ StringUtils.substring(cfsjp, 2, 4));
		double fz =VeDate.getTwoMin(orderDateTime1, cfsj);
		int ylsxfz = 0;
		String time = "";
		if (fz > 0) {
			ylsxfz = -40;
			time = cfsj;
		} else {
			time = StringUtils.substring(orderDateTime, 0, 16);
			ylsxfz = 120;
		}
		String timeLimit = "";
		if ("1".equals(bookType)) {
			timeLimit = getTktlDate(time.substring(0, 10), StringUtils.substring(time, 11, 13) + ":"
					+ StringUtils.substring(time, 14, 16), ylsxfz);
		} else {
			timeLimit = getTktlTime(StringUtils.substring(time, 0, 10), StringUtils.substring(time, 11, 13)
					+ ":" + StringUtils.substring(time, 14, 16), ylsxfz) + "/XXXXXX";

		}
		return timeLimit;
	}
	
	/**
	 * 获取预留时限日期
	 * @param date 2007-02-28
	 * @param time 1200
	 * @param fenzhong -130
	 * @return 2007-02-28 12:00:00
	 */
	public static String getTktlDate(String date, String time, int fenzhong) {
		return VeDate.getPreMin(date + " " + time + ":00", fenzhong);
	}
	
	/**
	 * 获取预留时限时间
	 * @param date  2007-02-28
	 * @param time 12:00
	 * @param fenzhong -130
	 * @return 1700/28FEB
	 */
	public static String getTktlTime(String date, String time, int fenzhong) {
		String tmp = VeDate.getPreMin(date + " " + time + ":00", fenzhong);
		String[] tms = tmp.substring(11, 16).split(":");
		return tms[0] + tms[1] + "/" + dateCommandTime(tmp.substring(0, 10)).substring(0, 5);
	}
	
	/**
	 * 排序
	 * @param orderName 排序字段
	 * @param list 排序集合
	 */
	public static void order(String orderName,List list){
		ArrayList sortFields = new ArrayList();
		Comparator mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.nullLowComparator(mycmp); 
		sortFields.add(new BeanComparator(orderName, mycmp));
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}
	
	/**
	 * 多个排序字段一起排序
	 * @param orderNames 排序字段数组
	 * @param list 需要排序集合
	 */
	public void orderMulti(String[] orderNames,List list){
		ArrayList sortFields = new ArrayList();
		Comparator mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.nullLowComparator(mycmp); 
		for(String name : orderNames){
			sortFields.add(new BeanComparator(name, mycmp));
		}
		ComparatorChain multiSort = new ComparatorChain(sortFields);
		Collections.sort(list, multiSort);
	}
	

	/**
	 * 由于航信返回的时间格式是hh:mm+1
	 * 计算方式是出发日期+1
	 * @param cfdate 
	 * @param etermTime 
	 * @return [参数说明]
	 */
	public static String etermTime(String cfdate,String etermTime){
		int pos=etermTime.indexOf("+");
		if(pos>-1){
			return VeDate.getNextDay(cfdate,StringUtils.substring(etermTime,pos+1,7))+" "+StringUtils.substring(etermTime,0,5)+":00";
		}else{
			return cfdate+" "+etermTime+":00";
		}
	}

	/**
	 * 判断是否为空
	 * @param arr 
	 * @param j 
	 * @return [参数说明]
	 */
	public static boolean isNotEmpty(String[] arr,int j){
		if(!ArrayUtils.isEmpty(arr) && j < arr.length){
			return true;
		}
		return false;
	}
	
}
