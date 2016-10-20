package org.vetech.core.business.pid.api;


/**
 * 公共参数
 * @author 章磊
 *
 */
public class Param {
	private String shbh;    //商户编号
	private String userid;	//用户编号
	private String password;	//密码
	private String url;	//pid的地址,http://localhost:8080
	private String officeId;	//pid的地址,http://localhost:8080
	
	//PID用户,shbh+userid
	public String getPidUser() {
		return userid;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	
}
