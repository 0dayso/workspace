package cn.vetech.vedsb.platpolicy.cpslink.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
/**
 * 向异地平台提交订单返回对象
 * @author vetech
 *
 */
@XmlRootElement(name = "response")
public class SubmitOrderRes extends DsResponse{

	private String orderNo;//平台呢订单编号
	private String orderStatus;//平台订单状态--1已支付，2自动代扣失败，3待支付
	private String zfsbyy;//自动代扣失败原因
	private String ifChangePnr;//是否换编码
	private String scny;//单个乘机人账单价
	private String yq;//单个乘机人机建
	private String tax;//单个乘机人税费
	private String paymentAmount;//订单应付金额
	private String officeId;//供应商的出票office号
	private String paymentTimeLimit;//支付时限-如果是已确认待支付的订单，则返回此值，单位为分钟
//	private String promotionId;//促销id
//	private String promotionType;//促销类型
//	private String promotionValue;//促销金额
	private String linkOrderNo;//link订单号
	private List<PayUrl> payLinkList;
	private String payment;//自动代扣支付方式
	@XmlElementWrapper(name = "payUrlList")
	@XmlElement(name="payUrl")
	public List<PayUrl> getPayLinkList() {
		return payLinkList;
	}

	public void setPayLinkList(List<PayUrl> payLinkList) {
		this.payLinkList = payLinkList;
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
//	public String getPromotionId() {
//		return promotionId;
//	}
//	public void setPromotionId(String promotionId) {
//		this.promotionId = promotionId;
//	}
//	public String getPromotionType() {
//		return promotionType;
//	}
//	public void setPromotionType(String promotionType) {
//		this.promotionType = promotionType;
//	}
//	public String getPromotionValue() {
//		return promotionValue;
//	}
//	public void setPromotionValue(String promotionValue) {
//		this.promotionValue = promotionValue;
//	}
	public String getLinkOrderNo() {
		return linkOrderNo;
	}
	public void setLinkOrderNo(String linkOrderNo) {
		this.linkOrderNo = linkOrderNo;
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

	public String getIfChangePnr() {
		return ifChangePnr;
	}

	public void setIfChangePnr(String ifChangePnr) {
		this.ifChangePnr = ifChangePnr;
	}
}
