package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
// comment on table "VEDS"."VE_EMAIL_SZ" is '邮箱设置表';
@Table(name = "VE_EMAIL_SZ")
public class VeEmailSz extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = -7938959246053725292L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator="no")
	private String id;
	
	/**
	 * 邮箱类别 0 平台邮箱，1商户邮箱
	 */
	private String emailType;
	
	/**
	 * 商户id，当是商户邮箱，需要存值
	 */
	private String khid;
	
	/**
	 * 发送服务器
	 */
	private String sendHost;
	
	/**
	 * 发送服务器端口
	 */
	private String sendPort;
	
	/**
	 * 发件人
	 */
	private String sendName;
	/**
	 * 发送邮箱
	 */
	private String email;
	/**
	 * 邮箱登录名
	 */
	private String username;
	/**
	 * 邮箱密码
	 */
	private String passsword;
	
	/**
	 * 是否需要smtp 验证1需要0不需要
	 */
	private String needSmtp;
	
	/**
	 * 回复邮箱
	 */
	private String replyto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType =  emailType == null ? null : emailType.trim();
	}

	public String getKhid() {
		return khid;
	}

	public void setKhid(String khid) {
		this.khid =  khid == null ? null : khid.trim();
	}

	public String getSendHost() {
		return sendHost;
	}

	public void setSendHost(String sendHost) {
		this.sendHost =  sendHost == null ? null : sendHost.trim();
	}

	public String getSendPort() {
		return sendPort;
	}

	public void setSendPort(String sendPort) {
		this.sendPort =  sendPort == null ? null : sendPort.trim();
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName =  sendName == null ? null : sendName.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email =  email == null ? null : email.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username =  username == null ? null : username.trim();
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword =   passsword == null ? null : passsword.trim();
	}

	public String getNeedSmtp() {
		return needSmtp;
	}

	public void setNeedSmtp(String needSmtp) {
		this.needSmtp =  needSmtp == null ? null : needSmtp.trim();
	}

	public String getReplyto() {
		return replyto;
	}

	public void setReplyto(String replyto) {
		this.replyto =  replyto == null ? null : replyto.trim();
	}
}
