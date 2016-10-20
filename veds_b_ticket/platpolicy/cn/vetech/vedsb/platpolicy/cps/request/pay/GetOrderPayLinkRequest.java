package cn.vetech.vedsb.platpolicy.cps.request.pay;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;

/**
 * 获取支付链接Bean
 * @author
 * 
 */
@XmlRootElement(name = "request")
public class GetOrderPayLinkRequest extends CpsRequest {

	private String orderNo;//CPS的订单编号
	private String payment;//CPS的订单编号
	private String orderType;//订单类型1正常，2改签  4酒店
	private String orderAmt;//订单支付金额
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

}
