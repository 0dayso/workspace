package cn.vetech.vedsb.platpolicy.cps;

import cn.vetech.vedsb.common.entity.Shyhb;

public class CpsSearchParam {
	private String ddbh;
	private Shyhb yhb;
	private String policyType;//政策类型1 普通政策 2 K位政策 3 免票政策 4 其它政策 5 特价政策
	private String policyId;
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public Shyhb getYhb() {
		return yhb;
	}
	public void setYhb(Shyhb yhb) {
		this.yhb = yhb;
	}
	public String getPolicyType() {
		return policyType;
	}
	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
}
