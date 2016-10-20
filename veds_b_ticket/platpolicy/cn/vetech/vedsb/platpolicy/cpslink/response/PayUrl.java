package cn.vetech.vedsb.platpolicy.cpslink.response;

public class PayUrl {

	private String payment;//支付方式
	private String url;//支付链接
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
}
