package org.vetech.core.modules.excel;

import java.lang.reflect.Field;

import org.vetech.core.modules.utils.Reflections;
/**
 * 解析ExcelTitle注解获取内容
 * @author 章磊
 *
 */
public class ExcelFieldUtil {
	public static void setProperty(Object o,String title,Object value) throws Exception{
		Field[] fields = o.getClass().getDeclaredFields();
		for(Field field:fields){
			if(field.isAnnotationPresent(ExcelTitle.class)){
				ExcelTitle excelTitle = field.getAnnotation(ExcelTitle.class);
				String tmpTitle = excelTitle.value();
				if(title.equals(tmpTitle)){
					Reflections.makeAccessible(field);
					field.set(o, value);
				}
			}
			
		}
	}
}
