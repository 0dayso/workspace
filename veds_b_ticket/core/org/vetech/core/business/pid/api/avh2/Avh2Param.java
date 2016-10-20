package org.vetech.core.business.pid.api.avh2;

public class Avh2Param {
	private String url;
	private String depDate;	//出发日期，格式是yyyy-mm-dd，必填
	private String cfcs;  	//出发城市,必填
	private String ddcs;	//到达城市,必填
	private String hkgs;	//航空公司,不必填
	private String isDirect;	//是否直达,1直达，0非直达，必填
	private String depTime;//出发时刻
	private String ifgxhb;//用来过滤共享航班0或1 0不过滤 1过滤 可选
	private String userid;
	public String getDepDate() {
		return depDate;
	}
	public void setDepDate(String depDate) {
		this.depDate = depDate;
	}
	public String getCfcs() {
		return cfcs;
	}
	public void setCfcs(String cfcs) {
		this.cfcs = cfcs;
	}
	public String getDdcs() {
		return ddcs;
	}
	public void setDdcs(String ddcs) {
		this.ddcs = ddcs;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getIsDirect() {
		return isDirect;
	}
	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public String getIfgxhb() {
		return ifgxhb;
	}
	public void setIfgxhb(String ifgxhb) {
		this.ifgxhb = ifgxhb;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
