package cn.vetech.vedsb.utils.mail;

public class Email {
	private String to;// 目的地址

	private String subject;// 邮件标题

	private String body;// 邮件内容

	boolean isHtml;// 是否html格式

	private String fileAffix;// 附件

	private String copyTo;// 抄送

	private String replyTo;// 回复

	private SendSuccessDo Senddo;

	public Email(String to, String subject, String body, boolean isHtml, SendSuccessDo Senddo) {
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.isHtml = isHtml;
	}

	public final String getBody() {
		return body;
	}

	public final void setBody(String body) {
		this.body = body;
	}

	public final boolean isHtml() {
		return isHtml;
	}

	public final void setHtml(boolean isHtml) {
		this.isHtml = isHtml;
	}

	public final String getSubject() {
		return subject;
	}

	public final void setSubject(String subject) {
		this.subject = subject;
	}

	public final String getTo() {
		return to;
	}

	public final void setTo(String to) {
		this.to = to;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public String getFileAffix() {
		return fileAffix;
	}

	public void setFileAffix(String fileAffix) {
		this.fileAffix = fileAffix;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public SendSuccessDo getSenddo() {
		return Senddo;
	}

	public void setSenddo(SendSuccessDo senddo) {
		Senddo = senddo;
	}

}
