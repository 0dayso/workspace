package cn.vetech.vedsb.platpolicy.taobao.response;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
/**
 * 向异地平台提交订单返回对象
 * @author vetech
 *
 */
@XmlRootElement(name = "response")
public class TBSubmitOrderRes extends DsResponse{

	private String orderNo;//平台订单编号
	private String orderStatus;//平台订单状态--1已支付，2自动代扣失败，3待支付
	private String zfsbyy;//自动代扣失败原因
	private String scny;//单个乘机人账单价
	private String yq;//单个乘机人机建
	private String tax;//单个乘机人税费
	private String paymentAmount;//订单应付金额
	private String paymentTimeLimit;//支付时限-如果是已确认待支付的订单，则返回此值，单位为分钟
	private String payurl;
	private String payment;//自动代扣支付方式
	
	public String getPayurl() {
		return payurl;
	}
	public void setPayurl(String payurl) {
		this.payurl = payurl;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getScny() {
		return scny;
	}
	public void setScny(String scny) {
		this.scny = scny;
	}
	public String getYq() {
		return yq;
	}
	public void setYq(String yq) {
		this.yq = yq;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getPaymentTimeLimit() {
		return paymentTimeLimit;
	}
	public void setPaymentTimeLimit(String paymentTimeLimit) {
		this.paymentTimeLimit = paymentTimeLimit;
	}
	public String getZfsbyy() {
		return zfsbyy;
	}
	public void setZfsbyy(String zfsbyy) {
		this.zfsbyy = zfsbyy;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}
}
