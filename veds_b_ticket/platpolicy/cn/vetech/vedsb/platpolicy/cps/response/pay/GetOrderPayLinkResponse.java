package cn.vetech.vedsb.platpolicy.cps.response.pay;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;


/**
 * 获取支付链接Bean
 * @author  shenjianxin
 * @version  [版本号, Sep 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@XmlRootElement(name = "response")
public class GetOrderPayLinkResponse extends CpsResponse {

	private String orderNo;//CPS订单编号
	private String url;//支付URL
	
	/**
	 * 支付方式  自动代扣用
	 */
	private String payment;
	/**
	 * 支付金额 自动代扣用
	 */
	private String paymentAmount;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
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
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

}