package cn.vetech.vedsb.platpolicy.cpslink;

import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.platpolicy.PolicyItem;

public class PlatOrderParam {
	private String ddbh;
	private String platcode;
	private String policyId;
	private String autopay;//是否自动支付 1是，0否 
	private String isQzd;//是否全自动出票 1是 0否
	private String jsj;
	private String policytype;//政策类型：B2B,BSP
	private Shyhb yhb;
	private PolicyItem policyItem;
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getPlatcode() {
		return platcode;
	}
	public void setPlatcode(String platcode) {
		this.platcode = platcode;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getJsj() {
		return jsj;
	}
	public void setJsj(String jsj) {
		this.jsj = jsj;
	}
	public Shyhb getYhb() {
		return yhb;
	}
	public void setYhb(Shyhb yhb) {
		this.yhb = yhb;
	}
	public String getAutopay() {
		return autopay;
	}
	public void setAutopay(String autopay) {
		this.autopay = autopay;
	}
	public String getIsQzd() {
		return isQzd;
	}
	public void setIsQzd(String isQzd) {
		this.isQzd = isQzd;
	}
	public PolicyItem getPolicyItem() {
		return policyItem;
	}
	public void setPolicyItem(PolicyItem policyItem) {
		this.policyItem = policyItem;
	}
	public String getPolicytype() {
		return policytype;
	}
	public void setPolicytype(String policytype) {
		this.policytype = policytype;
	}
	
}
