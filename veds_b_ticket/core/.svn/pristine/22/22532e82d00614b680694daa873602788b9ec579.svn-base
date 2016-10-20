package org.vetech.core.modules.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RTX消息发送
 * 
 * @author zhanglei
 *
 */
public class RtxUtil {
	private final static Logger logger = LoggerFactory.getLogger(RtxUtil.class);
	private static String RTXURL = "http://192.168.103.99:81/openapi/rtx/add";

	public void setRtxurl(String rtxurl) {
		RTXURL = rtxurl;
	}

	public static void sendRtx(String userid, String content) {
		sendRtx("1000", userid, content);
	}

	/**
	 * @param rtx_type
	 *            rtx的类型，发送前找产品经理确认
	 * @param userid
	 *            RTX工号，多个用逗号隔开，将建立一个群
	 * @param content
	 *            内容
	 */
	public static void sendRtx(String rtx_type, String userid, String content) {
		if (StringUtils.isBlank(userid) || StringUtils.isBlank(content)) {
			return;
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("receivers", userid);
		params.put("content", content);
		params.put("rtx_type", rtx_type);
		try {
			HttpClientUtil.doPost(RTXURL, params, "UTF-8");
		} catch (Exception e) {
			logger.error("RTX发送失败" + userid, e);
		}
	}

	public static void main(String[] args) {
		sendRtx("1000", "8401,8686", "http://192.168.207.67:8888/project.html各项目经理每日可打开此地址检查项目执行基准是否有偏差。");
	}
}
