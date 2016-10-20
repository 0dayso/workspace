package cn.vetech.vedsb.platpolicy.cps.request;

import org.apache.commons.lang3.StringUtils;

/**
 * 接口授权的账号资料
 * 
 * @author zhanglei
 *
 */
public class CpsAccount {
	/**
	 * 接口地址
	 */
	private String url;
	/**
	 * 商户编号
	 */
	private String businessNo;

	/**
	 * 操作人编号
	 */
	private String userid;

	/**
	 * 调用方的系统的编号
	 */
	private String systemId;

	/**
	 * 总公司
	 */
	private String compid;

	private String pwd;

	private String operateTime;

	private String sign;

	public String toXml() {
		return "<businessNo>" + StringUtils.trimToEmpty(businessNo) + "</businessNo>\r\n<compid>" + compid + "</compid>\r\n<userId>" + StringUtils.trimToEmpty(userid) + "</userId>\r\n<systemId>" + StringUtils.trimToEmpty(systemId)
				+ "</systemId>\r\n<operateTime>" + operateTime + "</operateTime>\r\n<sign>" + sign + "</sign>";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
