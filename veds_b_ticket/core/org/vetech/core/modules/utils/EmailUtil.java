package org.vetech.core.modules.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件消息发送
 * 
 * 邮件查询地址 http://192.168.103.99:81/emailnotel/emailnotel/viewlist
 * 
 * 如果有修改发送地址，请在系统加载的时候运行
 * 
 * <bean class="org.vetech.core.modules.utils.EmailUtil">
 * 
 * <property name="emailurl" value="http://192.168.103.99:81/openapi/email/add"></property>
 * 
 * </bean>
 * 
 * @author zhanglei
 *
 */
public class EmailUtil {
	private final static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	// 外界可以修改
	private static String EMAILURL = "http://192.168.103.99:81/openapi/email/add";

	public void setEmailurl(String emailurl) {
		EMAILURL = emailurl;
	}

	/**
	 * 
	 * @param email_type邮件类型，使用前需要找产品经理确认
	 * @param email
	 *            接受email地址，多个用逗号分隔
	 * @param subject
	 *            邮件主题
	 * @param content正文内容，目前不支持附件
	 */
	public static void sendEmail(String email_type, String email, String subject, String content) {
		if (StringUtils.isBlank(EMAILURL)) {
			return;
		}
		final Map<String, String> params = new HashMap<String, String>();
		params.put("email_type", email_type);
		params.put("email", email);
		params.put("subject", subject);
		params.put("content", content);
		new Thread(new Runnable() {
			public void run() {
				send(params);
			}
		}).start();
	}

	public static Map<String, String> sendEmailTb(String email_type, String email, String subject, String content) {
		if (StringUtils.isBlank(EMAILURL)) {
			return null;
		}
		final Map<String, String> params = new HashMap<String, String>();
		params.put("email_type", email_type);
		params.put("email", email);
		params.put("subject", subject);
		params.put("content", content);
		String rtn = send(params);

		String[] Status = StringUtils.substringsBetween(rtn, "<Status>", "</Status>");
		String[] Id = StringUtils.substringsBetween(rtn, "<Id>", "</Id>");
		String[] Msg = StringUtils.substringsBetween(rtn, "<Msg>", "</Msg>");

		Map<String, String> rtnmap = new HashMap<String, String>();

		rtnmap.put("Status", (Status != null && Status.length == 1) ? Status[0] : "");
		rtnmap.put("Id", (Id != null && Id.length == 1) ? Id[0] : "");
		rtnmap.put("Msg", (Msg != null && Msg.length == 1) ? Msg[0] : "");
		return rtnmap;
	}

	// <Response><Status>1</Status><Id>16081410480564487216</Id> <Msg>成功</Msg></Response>
	private static String send(final Map<String, String> params) {
		try {
			logger.info("邮件发送" + params.get("email") + "," + params.get("subject"));
			return HttpClientUtil.doPost(EMAILURL, params, "UTF-8");
		} catch (Exception e) {
			logger.error("邮件发送失败" + params.get("email") + "," + params.get("subject"), e);
			return "<Response><Status>-1</Status><Id></Id> <Msg>" + e.getMessage() + "</Msg></Response>";
		}

	}

	public static void main(String[] args) {
		sendEmail("1000", "8401@vetech.cn,zl_fh@sohu.com", "邮件主题测试", "邮件内容");

	}
}
