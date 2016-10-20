package org.vetech.core.modules.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 电商机票系统使用 请勿随意修改
 * @author wangshengliang
 *
 */
public class Bean2Map {
	public static <K, V> Map<K, V> getMap(Object javaBean) {  
		return getMapKey(javaBean,true);
    }
	
	public static <K, V> Map<K, V> getMapKeyLowerCase(Object javaBean) {  
		return getMapKey(javaBean,false);
    }
	
	private static <K, V> Map<K, V> getMapKey(Object javaBean,boolean isUpper) { 
		  Map<K, V> ret = new HashMap<K, V>();  
	        try {  
	            Method[] methods = javaBean.getClass().getDeclaredMethods(); 
	            for (Method method : methods) {
	            	Type t = method.getGenericReturnType();
	            	String fieldType = t.toString();
	                if (method.getName().startsWith("get")) {  
	                    String field = method.getName(); 
	                    field = field.substring(field.indexOf("get") + 3);  
	                    field = field.toLowerCase().charAt(0) + field.substring(1);  
	                    Object value = method.invoke(javaBean, (Object[]) null); 
	                    if(value != null){
	                    	if("class java.util.Date".equals(fieldType)){
		                       	 SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy",Locale.US);
		                   		 Date d = sdf.parse(value.toString());
		                   		 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		                   		 value=sdf.format(d);
	                    	}
	                    	if(isUpper){
	                    		ret.put((K)underLineUpper(field),((V) value));
	                    	}else{
	                    		ret.put((K)field.toLowerCase(),((V) value));
	                    	}
	                    }
	                }  
	            }  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	        }  
	        return ret; 
	}
	/**
	 * 将驼峰命名改为下划线命名
	 * @param field
	 * @return
	 */
	private static String underLineUpper(String field) {
		String regex = "[A-Z]";
		Pattern pattern = Pattern.compile(regex);
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(field);
		while (m.find()) {
			m.appendReplacement(sb, "_" + m.group().toUpperCase());
		}
		m.appendTail(sb);
		field = sb.toString();
		return field;
	}
}
