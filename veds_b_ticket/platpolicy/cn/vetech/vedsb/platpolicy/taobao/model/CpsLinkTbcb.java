package cn.vetech.vedsb.platpolicy.taobao.model;


public class CpsLinkTbcb{

	private String ticket_price;  //票面价
	private String fee;  // 燃油
	private String tax;  //税
	private String cabin;  //舱位
	private String cabin_price;  //舱位价
	private String amount;  //座位数  A代表大于9
	private String productType;  //产品名称
	private String tuigaiqian;  //退改签规定

	
	public String getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(String ticket_price) {
		this.ticket_price = ticket_price;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getCabin_price() {
		return cabin_price;
	}
	public void setCabin_price(String cabin_price) {
		this.cabin_price = cabin_price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getTuigaiqian() {
		return tuigaiqian;
	}
	public void setTuigaiqian(String tuigaiqian) {
		this.tuigaiqian = tuigaiqian;
	}
	
}
