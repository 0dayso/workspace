package cn.vetech.vedsb.utils;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ZwUtil {
	/**
	 * 获取map中的value,空则返回默认值
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getValueByMap(Map<String,String> params,String key,String defaultValue) {
		String val=params.get(key);
		return StringUtils.isNotBlank(val) ? val : defaultValue;
	}
}
