package cn.vetech.vedsb.platpolicy.cps.request.pay;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * 采购商支付通知
 * 采购商在CPS支付成功，通知给电商采购商将订单改为已支付
 */
@XmlRootElement(name="request")
public class PayNotifyRequest {

	/**
	 * 订单类型  String(1)  Y  
	 */ 
	private String orderType;

	/**
	 * 订单编号  String(20)  Y  CPS的订单编号  
	 */ 
	private String orderNo;

	/**
	 * 交易流水号  String(60)  Y  采购支付时的交易流水号  
	 */ 
	private String paymentTransaction;

	/**
	 * 支付方式  String(10)  Y  传入CPS的支付方式的编号。如312013301，ASMS里设置对应关系进行匹配。  
	 */ 
	private String payment;

	/**
	 * 支付金额  String(20)  Y  
	 */ 
	private String paymentAmount;

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPaymentTransaction() {
		return paymentTransaction;
	}

	public void setPaymentTransaction(String paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
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
/*
<Response>
   <status>支付状态</status>
   <businessNo>商户号</businessNo>
   <orderType>订单类型</orderType>
   <orderNo>订单编号</orderNo>
   <paymentTransaction>交易流水号</paymentTransaction>
   <payment>支付方式</payment>
   <paymentAmount>支付金额</paymentAmount>

</Response>
*/
