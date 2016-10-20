package cn.vetech.vedsb.platpolicy.jzcp.tb.client;
import javax.xml.bind.annotation.XmlElement;

public abstract class CpsLinkRequest {

	/**
	 * 商户编号
	 */
	private String businessNo;

	/**
	 * 操作人编号
	 */
	private String userid;

	/**
	 * 签名:systemid+account+operator+operateTime+key
	 */
	private String sign;

	/**
	 * 调用方的系统的编号
	 */
	private String systemId;

	/**
	 * 操作时间yyyy-mm-dd hh:mm:ss
	 */
	private String operateTime;
	
	/**
	 * 总公司
	 */
	private String compid;

	/**
	 * 接口服务名
	 */
	private String service;
	
	/**
	 * 请求来源
	 */
	private String qqly;

	@XmlElement(name = "sign")
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@XmlElement(name = "systemId")
	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	@XmlElement(name = "operateTime")
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@XmlElement(name = "service")
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@XmlElement(name = "businessNo")
	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	@XmlElement(name = "userId")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	@XmlElement(name = "compid")
	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getQqly() {
		return qqly;
	}

	public void setQqly(String qqly) {
		this.qqly = qqly;
	}

}
