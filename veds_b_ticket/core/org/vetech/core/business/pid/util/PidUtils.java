package org.vetech.core.business.pid.util;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.VeDate;

public class PidUtils {
	private static String[] m_nMonth = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	private static String[] mMonth = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };

	/**
	 * 
	 * @param date yyyy-mm-dd
	 * @return 13SEP08
	 */
	public static String dateToCommandDate(String date) {
		if (date == null || date.length() != 10 || !VeDate.isDate(date, "yyyy-MM-dd")) {
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
	
}
