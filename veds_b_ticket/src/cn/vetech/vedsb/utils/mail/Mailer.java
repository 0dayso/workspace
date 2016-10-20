package cn.vetech.vedsb.utils.mail;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.utils.FileOperate;


public class Mailer {

	private MimeMessage mimeMsg; // MIME邮件对象

	private Session session; // 邮件会话对象

	private Properties props=new Properties(); // 系统属性

	private String username = ""; // smtp验证用户名与密码

	private String password = "";

	private Multipart mp; // Multipart对象,邮件内容,标题,附件等內容均添加到其中后再生成MimeMessage对象

	private boolean isDebug = true;

	public Mailer(String smtp, int port) {
		setSmtpHost(smtp, port);// 指定邮件服务
		createMimeMessage();
	}

	public void setSmtpHost(String hostName, int port) {
		props.put("mail.smtp.host", hostName); // 设置SMTP主机
		props.put("mail.smtp.connectiontimeout", "15000");
		props.put("mail.smtp.timeout", "15000");
		if (port == 465) {
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			props.put("mail.smtp.socketFactory.port", "465");
		}

	}

	public boolean createMimeMessage() {
		try {
			if (isDebug)
				System.out.println("准备获取邮件会话对象!");
			session = Session.getDefaultInstance(props, null);
		} catch (Exception e) {
			System.err.println("获取邮件会话对象出错!" + e);
			return false;
		}
		if (isDebug)
			System.out.println("准备创建MIME邮件对象!");
		try {
			mimeMsg = new MimeMessage(session);
			mp = new MimeMultipart();

			return true;
		} catch (Exception e) {
			System.err.println("创建MIME邮件对象失败!" + e);
			return false;
		}
	}

	public void setNeedAuth(boolean need) {
		if (isDebug)
			System.out.println("设置SMTP身份验证：mail.smtp.auth = " + need);
		if (need) {
			props.put("mail.smtp.auth", "true");
		} else {
			props.put("mail.smtp.auth", "false");
		}
	}

	public void setNamePass(String name, String pass) {
		username = name;
		password = pass;
	}

	public boolean setSubject(String mailSubject) {
		if (isDebug)
			System.out.println("设置邮件主题!");
		try {
			mimeMsg.setSubject(mailSubject, "GBK");
			return true;
		} catch (Exception e) {
			System.err.println("设置邮件主题出错!");
			return false;
		}
	}

	public boolean setBody(String mailBody, boolean b_html) {
		try {
			BodyPart bp = new MimeBodyPart();
			if (b_html)
				bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=GBK>" + mailBody, "text/html;charset=GBK");
			else
				bp.setText(mailBody);
			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("设置邮件正文出错!" + e);
			return false;
		}
	}

	public boolean addFileAffix(String filename) {
		if (isDebug)
			System.out.println("增加邮件附件!" + filename);
		if (filename == null || "".equals(filename)) {
			return false;
		}
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			// 处理附件标题乱码的问题
			bp.setFileName(encodeFileAffixName(filename));

			mp.addBodyPart(bp);

			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件!" + filename + "出错!" + e);
			return false;
		}
	}

	// 处理附件的标题是中文乱码的问题
	private String encodeFileAffixName(String filename) {
		String displayname = splitPath(filename)[2];
		Pattern p1 = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m1 = p1.matcher(displayname);
		if (m1.find()) {
			try {
				displayname = MimeUtility.encodeText(displayname, "utf-8", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String fileExt = FileOperate.getFileExt(filename);
		return displayname + "." + fileExt;
	}

	private String[] splitPath(String filepath) {

		if (StringUtils.isNotBlank(filepath) && filepath.indexOf("\\") > -1) {// 考虑从tomcat中取的附件地址，带\\
			filepath = StringUtils.replace(filepath, "\\", "/");
		}

		String str[] = filepath.split("/");
		String arr[] = new String[3];
		arr[0] = str[str.length - 1];
		arr[1] = "";
		arr[2] = "";
		for (int i = 0; i < (str.length - 1); i++) {
			if (str[i] != null && !str[i].equals("")) {
				arr[1] += "/" + str[i];
			}
		}
		arr[2] = arr[0].substring(0, arr[0].lastIndexOf("."));
		return arr;
	}

	public boolean setFrom(String from) {
		if (isDebug)
			System.out.println("设置发信人:" + from);
		try {
			mimeMsg.setFrom(new InternetAddress(from));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发件邮箱 和发件人姓名
	 * 
	 * @param from
	 * @param fromName
	 * @return
	 */
	public boolean setFrom(String from, String fromName) {
		if (isDebug)
			System.out.println("设置发信人:" + from);
		try {
			mimeMsg.setFrom(new InternetAddress(from, fromName));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setTo(String to) {
		if (isDebug)
			System.out.println("设置收信人:" + to);
		if (to == null || "".equals(to))
			return false;

		try {
			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addTo(String to) {
		if (to == null || "".equals(to))
			return false;

		try {
			mimeMsg.addRecipients(Message.RecipientType.TO, to);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean setCopyTo(String copyto) {
		if (copyto == null || "".equals(copyto))
			return false;
		try {
			mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean setReplyTo(String replyTo) {
		if (replyTo == null || "".equals(replyTo))
			return false;
		try {
			mimeMsg.setReplyTo((Address[]) InternetAddress.parse(replyTo));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean addCopyTo(String to) {
		if (to == null || "".equals(to))
			return false;

		try {
			mimeMsg.addRecipients(Message.RecipientType.CC, to);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void sendout(SendSuccessDo Senddo) throws Exception {

		mimeMsg.setContent(mp);
		mimeMsg.saveChanges();
		if (isDebug)
			System.out.println("正在发送邮件......");

		Session mailSession = Session.getInstance(props, null);
		Transport transport = mailSession.getTransport("smtp");
		transport.connect((String) props.get("mail.smtp.host"), username, password);
		// transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
		transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());// 抄送、密送的地址一起发送邮件

		// transport.send(mimeMsg);
		System.out.println("邮件发送成功！");
		transport.close();
		if (Senddo != null) {
			Senddo.doSomething();
		}

	}
}
