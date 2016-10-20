package org.vetech.core.modules.mybatis.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.VeDate;

/**
 * hibernate分页用的条件
 * 
 * @author 章磊
 * 
 */
public class SearchFilter {

	public enum Operator {
		EQ, LIKE, GT, LT, GTE, LTE, NOT, NOTNULL, ISNULL
	}

	public String fieldName;
	public String searchName;
	public Object value;
	public Operator operator;

	public SearchFilter(String fieldName, String searchName, Operator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
		this.searchName = searchName;
	}

	/**
	 * searchParams中key的格式为OPERATOR_FIELDNAME
	 */
	public static Map<String, SearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 过滤掉空值
			String key = entry.getKey();
			Object value = entry.getValue();
			// 拆分operator与filedAttribute
			String[] names = StringUtils.split(key, "_");
			if ("orderBy".equals(key)) {
				continue;
			}
			if (names.length < 3) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = key.substring(names[0].length()+names[1].length()+2);
			Operator operator = null;
			if(names[1].lastIndexOf("date")>-1){
				operator =Operator.valueOf(names[1].replace("date", ""));
			}else if(names[1].lastIndexOf("dats")>-1){
				operator =Operator.valueOf(names[1].replace("dats", ""));
			}else if(names[1].lastIndexOf("datd")>-1){
				operator =Operator.valueOf(names[1].replace("datd", ""));
			}else{
				operator = Operator.valueOf(names[1]);
			}
			
			// 创建searchFilter
			SearchFilter filter = new SearchFilter(filedName, key, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
	/**
	 * 参数类型处理
	 * @param searchParams
	 * @return
	 */
	public static Map<String, Object> type(Map<String, Object> searchParams) {
		Map<String, Object> tmp = new HashMap<String, Object>();
		for (Entry<String, Object> entry : searchParams.entrySet()) {
			String key = entry.getKey();
			if ("orderBy".equals(key)) {
				continue;
			}
			Object value = entry.getValue();
			String[] names = StringUtils.split(key, "_");
			if(names[1].lastIndexOf("date")>-1){
				if (VeDate.isDate(value + "")) {
					tmp.put(key, VeDate.strToDate(value+""));
				}
			}else if(names[1].lastIndexOf("dats")>-1){
				if (VeDate.isDate(value + " 00:00:00")) {
					tmp.put(key, VeDate.strToDateLong(value+" 00:00:00"));
				}
			}else if(names[1].lastIndexOf("datd")>-1){
				if (VeDate.isDate(value + " 23:59:59")) {
					tmp.put(key, VeDate.strToDateLong(value+" 23:59:59"));
				}
			}else{
				tmp.put(key,value);
			}
		}
		return tmp;

	}
}
