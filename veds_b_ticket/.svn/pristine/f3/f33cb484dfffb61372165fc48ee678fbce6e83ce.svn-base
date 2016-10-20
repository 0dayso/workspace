package cn.vetech.vedsb.platpolicy.cps;

import cn.vetech.vedsb.common.entity.Shyhb;

public class CpsOrderParam {
	private String ddbh;
	private Shyhb yhb;
	private String autopay;//是否自动支付 1是，0否 
	private String policytype;//B2B,BSP
	private String zclx;//政策类型1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策
	private String policyId;
	private String policyJsj;//上级结算价
	/**
	 * 首先取订单上的采购价,如果有免票、特殊政策或以政策为准(政策为准是看政策对象中的jgxx节点)，取政策上面的价格,有PAT价格，则取PAT价格
	 */
	private String pjCgj;//采购价这里需要存储多人的账单价，底层会除以人数
	private String isQzd;//是否全自动出票 1是 0否
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public Shyhb getYhb() {
		return yhb;
	}
	public String getPolicytype() {
		return policytype;
	}
	public void setPolicytype(String policytype) {
		this.policytype = policytype;
	}
	public String getZclx() {
		return zclx;
	}
	public void setZclx(String zclx) {
		this.zclx = zclx;
	}
	public void setYhb(Shyhb yhb) {
		this.yhb = yhb;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyJsj() {
		return policyJsj;
	}
	public void setPolicyJsj(String policyJsj) {
		this.policyJsj = policyJsj;
	}
	public String getPjCgj() {
		return pjCgj;
	}
	public void setPjCgj(String pjCgj) {
		this.pjCgj = pjCgj;
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
}
