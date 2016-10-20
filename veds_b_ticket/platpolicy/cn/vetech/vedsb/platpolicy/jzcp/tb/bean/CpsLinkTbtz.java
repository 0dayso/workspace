package cn.vetech.vedsb.platpolicy.jzcp.tb.bean;


public class CpsLinkTbtz{
	private String ddbh;  // ASMS订单编号
	private String ptddbh;  //平台订单编号
	private String ddzt;  //订单状态
	private String cjr;  //乘机人
	private String ph;  //票号
	private String paidPrice; //淘宝支付金额
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getPtddbh() {
		return ptddbh;
	}
	public void setPtddbh(String ptddbh) {
		this.ptddbh = ptddbh;
	}
	public String getDdzt() {
		return ddzt;
	}
	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getPh() {
		return ph;
	}
	public void setPh(String ph) {
		this.ph = ph;
	}
	public String getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(String paidPrice) {
		this.paidPrice = paidPrice;
	}
	
}
