package cn.vetech.vedsb.utils.mail;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.commons.lang.math.NumberUtils;

import cn.vetech.vedsb.common.entity.base.VeEmailSz;

/**
 * 存放Email实体
 * 
 * @author zl
 * 
 */
public class EmailTask {
	private static LinkedList<Email> messages = new LinkedList<Email>();

	private static EmailTask et = null;

	private String host = "";

	private int port = 25;

	private String username = "";

	private String password = "";

	private String fromMail = "";

	private String fromName = "";

	private String replyTo = "";

	private VeEmailSz ve_email_sz;

	private EmailTask() {
		Collections.synchronizedList(messages);
		init();
	}

	private EmailTask(VeEmailSz ve_email_sz) {
		this.ve_email_sz = ve_email_sz;
		Collections.synchronizedList(messages);
		init();
	}

	public void init() {
		try {
			if (ve_email_sz != null) {
				host = ve_email_sz.getSendHost();
				username = ve_email_sz.getUsername();
				password = ve_email_sz.getPasssword();

				fromMail = ve_email_sz.getEmail();
				fromName = ve_email_sz.getSendName();
				
				port = NumberUtils.toInt(ve_email_sz.getSendPort(),25);//465

			} else {

				InputStream in = EmailTask.class.getResourceAsStream("/mail.properties");
				Properties mailProps = new Properties();
				mailProps.load(in);
				in.close();
				host = (String) mailProps.get("mail.smtp.host");
				username = (String) mailProps.get("mail.username");
				password = (String) mailProps.get("mail.password");

				fromMail = (String) mailProps.get("mail.fromEmail");
				fromName = (String) mailProps.get("mail.fromName");

				fromName = new String(fromName.getBytes("iso-8859-1"), "UTF-8");
				replyTo = (String) mailProps.get("mail.replyTo");
				
				port = NumberUtils.toInt("mail.smtp.socketFactory.port",25);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public static EmailTask getInstance() {
		if (et == null) {
			et = new EmailTask();
		}
		return et;
	}

	synchronized public static EmailTask getInstance(VeEmailSz ve_email_sz) {
		et = new EmailTask(ve_email_sz);
		return et;
	}

	/**
	 * 邮件队列中是否存在没有发送完的邮件
	 * 
	 * @return
	 */
	public static boolean isEmptyMailTask() {
		return messages.isEmpty();
	}

	public void send() {
		try {
			if (!isEmptyMailTask()) {
				Email email = messages.removeFirst();// 先取出来，避免业务调用中的死循环
				Mailer mail = new Mailer(host,port);
				mail.setNamePass(username, password);
				mail.setFrom(fromMail, fromName);
				mail.setReplyTo(replyTo);
				mail.setSubject(email.getSubject());
				mail.setBody(email.getBody(), email.isHtml());
				mail.setTo(email.getTo());
				mail.setNeedAuth(true);
				mail.setCopyTo(email.getCopyTo());
				mail.setReplyTo(email.getReplyTo());
				mail.addFileAffix(email.getFileAffix());
				mail.sendout(email.getSenddo());// 发送
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addMail(Email email) {
		messages.add(email);
	}

	public static void addMail(String toName, String toEmail, String subject, String body, SendSuccessDo Senddo) {

		Email email = new Email(toEmail, subject, body, true, Senddo);
		messages.add(email);
	}

	public static void addMail(String toEmail, String subject, String body, String CC, String fileAffix, String replyTo, SendSuccessDo Senddo) {

		Email email = new Email(toEmail, subject, body, true, Senddo);
		email.setFileAffix(fileAffix);
		email.setCopyTo(CC);
		email.setReplyTo(replyTo);
		messages.add(email);
	}

	public static void main(String[] args) {
		EmailTask et = getInstance();
		//String url = "http://localhost:8082/email/vip/test.jsp";
		String body = "<b>你好这是你订阅的内容</b><a href=\"http://localhost:8082/email/vip/test.jsp\">click</a><br>";
		try {
			body +="";// PayUtils.sendUrl(url);
		} catch (Exception e) {
			body = "<b>你好这是你订阅的内容</b><a herf=\"#\">click</a>";
			e.printStackTrace();
		}
		System.out.println(body);
		Email email = new Email("278016391@qq.com", "丝路机票订阅", body, true, null);
		// email.setFileAffix("D:/局部更新/asmsAndagent.zip");
		// email.setFromMail("zlei@vetech.cn");
		email.setReplyTo("zlj@vetech.cn");

		EmailTask.addMail(email);
		et.send();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public VeEmailSz getVe_email_sz() {
		return ve_email_sz;
	}

	public void setVe_email_sz(VeEmailSz ve_email_sz) {
		this.ve_email_sz = ve_email_sz;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}