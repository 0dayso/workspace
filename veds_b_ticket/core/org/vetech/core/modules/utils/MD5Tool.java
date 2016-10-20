package org.vetech.core.modules.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * md5加密工具
 * 
 * @author Administrator
 * 
 */
public class MD5Tool {
	/**
	 * 默认是utf-8编码
	 * 
	 * @param source
	 * @return
	 */
	public static String MD5Encode(String source) {
		return MD5Encode(source, "UTF-8");
	}
	public static String MD5Encode(String source, String encode) {
		if (source == null) {
			return null;
		}
		if (StringUtils.isBlank(encode)) {
			return null;
		}

		try {
			return DigestUtils.md5Hex(source.getBytes(encode));
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(MD5Encode("9090890opl", "utf-8"));
	}
}
