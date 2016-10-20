package cn.vetech.vedsb.pay.zfb;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;

public class ZfbUtil {
 
//	private static String file = "cn/vetech/framework/bookticket/b2bnew/pay/zfb/zfberror.properties";
//	public static String codeName(String code) {
//		code = StringUtils.trimToEmpty(code);
//		try {
//			GetProperties gp = new GetProperties();
//			String value = gp.getPro(file, code);
//
//			if (StringUtils.isBlank(value)) {
//				return code;
//			}
//
//			String errorinfo = (new String(value.getBytes("iso-8859-1"), "UTF-8"));
//			if (StringUtils.isBlank(errorinfo)) {
//				return code;
//			} else {
//				return "错误代码：" + code + "错误内容：" + errorinfo;
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return code;
//	}

	@SuppressWarnings("unchecked")
	public static String getContent(Map params, String keykey) {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		boolean first = true;
		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			String value = "";
			Object o = params.get(key);
			if (o instanceof String) {
				value = (String) o;
			} else if (o instanceof String[]) {
				value = ((String[]) o)[0];
			}
			if (value == null || value.trim().length() == 0) {
				continue;
			}
			if (first) {
				prestr = prestr + key + "=" + value;
				first = false;
			} else {
				prestr = prestr + "&" + key + "=" + value;
			}
		}
		return prestr + keykey;
	}

	public static String encodeBase64(String s) {
		if (s == null)
			return null;
		BASE64Encoder encode = new BASE64Encoder();
		byte[] b = null;
		try {
			b = s.getBytes("UTF-8");
			return encode.encode(b);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
