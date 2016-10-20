package cn.vetech.vedsb.pay.payUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.utils.LogUtil;

/**
 * 
 * @author houya
 */
public class B2bpayutil {
	/**
	 * 取得配置文件参数
	 * 
	 * @param key
	 * 
	 * @return String 参数值
	 */
	public static String getPayparam(String key) {
		InputStream in = null;
		try {
			// 实时读取
			File file = new File(B2bpayutil.class.getResource("/vetechpay.properties").getFile());
			in = new FileInputStream(file);
			Properties p = new Properties();
			p.load(in);
			String s = p.getProperty(key);
			if (StringUtils.isBlank(s)) {
				s = "";
			}
			s = new String(s.getBytes("iso-8859-1"), "UTF-8");
			p.clear();
			return StringUtils.trimToEmpty(s);
		} catch (Exception e) {
			LogUtil.getZfzhQy("zfzhqy").error(VeDate.getStringDate() + "key:" + key + " 读取支付配置资源文件出错" + e.getMessage());
			return "";

		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	/**
	 * 获取指定properties文件中的值
	 * @param file
	 * @param stype
	 * @return
	 */
	public static String getPro(String file, String stype) { 
		InputStream is = B2bpayutil.class.getResourceAsStream("/" + file);
		Properties dbProps = new Properties();
		try {
			dbProps.load(is);
			return dbProps.getProperty(stype);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
