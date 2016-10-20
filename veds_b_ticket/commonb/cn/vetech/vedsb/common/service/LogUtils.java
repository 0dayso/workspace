package cn.vetech.vedsb.common.service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.modules.utils.Reflections;
import org.vetech.core.modules.utils.VeDate;

public class LogUtils {
	private static final Logger logger = LoggerFactory.getLogger(LogUtils.class);
	public static final String NEW_COLOR = "<font color=\"red\">";

	public static final String OLD_COLOR = "<font color=\"white\">";

	public static final String COLOR_END = "</font>";

	public static final String BR = "\n";
	/**
	 * 
	 * @param o 老数据
	 * @param n 新数据
	 * @throws Exception 
	 */
	public static String getCznr(Object o, Object n) throws Exception{
		try{
			List<Field> olist = Reflections.getDeclaredFields(o.getClass());
			List<Field> otmp = new ArrayList<Field>();
			for(Field field : olist){
				if(field.isAnnotationPresent(BYdlog.class)){
					otmp.add(field);
				}
			}
			olist = null;
			List<Field> nlist = Reflections.getDeclaredFields(n.getClass());
			List<Field> ntmp = new ArrayList<Field>();
			for(Field field : nlist){
				if(field.isAnnotationPresent(BYdlog.class)){
					ntmp.add(field);
				}
			}
			nlist= null;
			StringBuffer sbCznr = new StringBuffer();
			int count=0;
			for(Field nfield : ntmp){
				String newname = nfield.getName();
				BYdlog ydlog = nfield.getAnnotation(BYdlog.class);
				String name = ydlog.name();
				int logView = ydlog.logView();
				for(Field ofield : otmp){
					String oldname = ofield.getName();
					if(newname.equals(oldname)){
						Reflections.makeAccessible(nfield);
						Reflections.makeAccessible(ofield);
						Object newvalue=nfield.get(n);
						Object oldvalue=ofield.get(o);
						if (newvalue instanceof String) {
							if (!ObjectUtils.toString(newvalue).equals(ObjectUtils.toString(oldvalue))) {
								appendCznr(count, name, oldvalue, newvalue, logView, sbCznr);
								count++;
							}
						}else if (newvalue instanceof Integer) {
							if (toInt((Integer) newvalue) != toInt((Integer) oldvalue)) {
								appendCznr(count, name, oldvalue, newvalue, logView, sbCznr);
								count++;
							}
						}else if (newvalue instanceof Double) {
							if (toDouble((Double) newvalue) != toDouble((Double) oldvalue)) {
								appendCznr(count, name, oldvalue, newvalue, logView, sbCznr);
								count++;
							}
						}else if (newvalue instanceof BigDecimal) {
							if (toBigDecimal((BigDecimal) newvalue) != toBigDecimal((BigDecimal) oldvalue)) {
								appendCznr(count, name, oldvalue, newvalue, logView, sbCznr);
								count++;
							}
						}else if (newvalue instanceof Date) {
							if ((newvalue == null && oldvalue != null) || (newvalue != null && oldvalue == null)
									|| ((Date) newvalue).compareTo((Date) oldvalue) != 0) {
								appendCznr(count, name, oldvalue, newvalue, logView, sbCznr);
								count++;
							}
						}
						
						break;
					}
				}
			}
			return sbCznr.toString();
		}catch(Exception e){
			logger.error("对比异动日志错误",e);
			throw e;
		}
	}
	private static double toBigDecimal(BigDecimal in) {
		if (in == null) {
			return 0;
		}
		return in.doubleValue();
	}
	private static double toDouble(Double in) {
		if (in == null) {
			return 0;
		}
		return in.doubleValue();
	}
	private static int toInt(Integer in) {
		if (in == null) {
			return 0;
		}
		return in.intValue();
	}
	private static int appendCznr(int number, String name, Object ovalue, Object nvalue,
			int logVew, StringBuffer sbCznr) {
		if (number == 0) {
			processLogView(sbCznr,
					"&nbsp;&nbsp;&nbsp;&nbsp;>>" + name + "由" + OLD_COLOR + changeNull(ovalue)
							+ COLOR_END + "修改为" + NEW_COLOR + changeNull(nvalue) + COLOR_END + BR, logVew);
			return number;
		} else {
			processLogView(sbCznr, number + "、" + name + "由" + OLD_COLOR + changeNull(ovalue) + COLOR_END
					+ "修改为" + NEW_COLOR + changeNull(nvalue) + COLOR_END + BR, logVew);
			return ++number;
		}
	}
	private static void processLogView(StringBuffer sbCznr, String str, int logVew) {
		if (logVew == 1) {
			sbCznr.append("<A>").append(str).append("</A>");
		} else if (logVew == 2) {
			sbCznr.append("<B>").append(str).append("</B>");
		}else{
			sbCznr.append(str);
		}
	}
	/**
	 * <值处理，包括NULL值、枚举值及需要再查询的SQL值>
	 * 
	 * @param obj
	 *            值
	 * @param options
	 *            注解的枚举数组
	 * @param sql
	 *            重新通过SQL获取值的SQL
	 * 
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	private static Object changeNull(Object obj) {
		if (obj == null) {
			return "空";
		}else{
			if (obj instanceof Date) {
				return VeDate.formatToStr((Date)obj, "yyyy-MM-dd HH:mm:ss");
			}
		}
		return obj;
	}
	
}
