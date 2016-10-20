package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;


/**
 * 生成订单接口返回Bean
 * @author
 * 
 */
@XmlRootElement(name = "response")
public class CreateOrderResponse extends CpsResponse {

	private String orderNo;//CPS订单编号
	private String orderStatus;//CPS订单状态，已确认待支付，待确认，待确认订单不能直接支付
	private String scny;//单个乘机人
	private String yq;//单个乘机人
	private String tax;//单个乘机人
	private String paymentAmount;//订单应付金额
	private String officeId;//供应商的出票office号 对应政策中的OFFICE号 
	private String paymentTimeLimit;//支付时限，如果是已确认待支付的订单，则返回此值，单位为分钟
	
	private String promotionId;
	private String promotionType;
	private String promotionValue;//促销金额 ，与促销活动ID一一对应，格示示例：1|1
	
	private String errMsgTip;//失败提示
	
	private String payurl;//支付链接
	/**
	 * 支付方式  自动代扣用
	 */
	private String payment;
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
	public String getOfficeId() {
		return officeId;
	}
	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	public String getPaymentTimeLimit() {
		return paymentTimeLimit;
	}
	public void setPaymentTimeLimit(String paymentTimeLimit) {
		this.paymentTimeLimit = paymentTimeLimit;
	}
	public String getPromotionId() {
		return promotionId;
	}
	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}
	public String getPromotionType() {
		return promotionType;
	}
	public void setPromotionType(String promotionType) {
		this.promotionType = promotionType;
	}
	public String getPromotionValue() {
		return promotionValue;
	}
	public void setPromotionValue(String promotionValue) {
		this.promotionValue = promotionValue;
	}
	public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
	public String getErrMsgTip() {
		return errMsgTip;
	}
	public void setErrMsgTip(String errMsgTip) {
		this.errMsgTip = errMsgTip;
	}
	public String getPayurl() {
		return payurl;
	}
	public void setPayurl(String payurl) {
		this.payurl = payurl;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
}