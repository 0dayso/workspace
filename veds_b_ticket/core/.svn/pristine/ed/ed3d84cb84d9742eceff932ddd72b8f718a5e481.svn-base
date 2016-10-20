package org.vetech.core.business.pid.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.modules.utils.Arith;
import org.vetech.core.modules.utils.VeDate;

public class PidCommonUtil {

	// 通过10sep20 转换为2020-09-10
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
				return BookUtil.commandDate("0" + commandDate, year + "");
			}
			// 23jan
			if (commandDate.length() == 5) {
				into_month1 = commandMonth(into_month);
				int into_month2 = NumberUtils.toInt(into_month1);
				int now_month2 = NumberUtils.toInt(now_month);
				if (now_month2 > into_month2) {
					year = year + 1;
				}
				return BookUtil.commandDate(commandDate, year + "");
			}
			// 3jan10
			if (commandDate.length() == 6) {
				return BookUtil.commandDate("0" + StringUtils.substring(commandDate, 0, 4), StringUtils.substring(
						commandDate, 4, 6));
			}
			// 23jan10
			if (commandDate.length() == 7) {
				return BookUtil.commandDate(StringUtils.substring(commandDate, 0, 5), StringUtils.substring(
						commandDate, 5, 7));
			}
		}
		return commandDate;
	}
	
	
	/**
	 * <功能详细描述> 传入英文月份返回数字月份
	 * 
	 * @param date
	 * @param year
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
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
	
	 //4舍5入
	public static String getNum(String sou,String num){
	    double dd = Double.parseDouble(sou) ;
	    double tt = Double.parseDouble(num) ;
	    double rr = Arith.mul(dd,tt);
	    String news = rr + "";
	    int k=0;
	    byte[] tempbyte=news.getBytes();
	    for(int i=0 ; i < news.length()  ; i++){
	      if(tempbyte[i] == 46) 
	    	  k = i;
	    }
	    String s_l;
	     s_l = news.substring(0,k ) ;
	    
	     if(Integer.parseInt(s_l.substring(s_l.length() - 1 , s_l.length()))>4){
	    	 s_l = Integer.parseInt(s_l.substring(0,s_l.length()-1)+"0") + 10 +"";
	     }else
	    	 s_l = s_l.substring(0,s_l.length()-1)+"0";
	    return s_l;
	  }
	
}
