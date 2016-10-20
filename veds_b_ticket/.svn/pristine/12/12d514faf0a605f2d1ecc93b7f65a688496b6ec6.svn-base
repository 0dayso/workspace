package cn.vetech.vedsb.platpolicy.cpslink.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;
/**
 * 退废票通知
 * @author vetech
 *
 */
@XmlRootElement(name = "request")
public class RefundNotifyRequest extends DsRequest{

	private String outOrderNo;//退废单号
	private String refundStatus;//状态--退款或拒单，对应状态三字码
	private String actualRefund;//实际退款--Double(12,2) N 退废单实际退款金额，不含退改签费用的金额
	private String reason;//拒单原因  退票失败原因（10001 ： 暂不能退废票 10002：咱不能退废票已解决 10003:拒绝退废票）
	private String remark;
	private String refundDatetime;//退款时间
	private String ticketNo;//票号--票号，多个使用｜分隔
	private String refundFee;//退票手续费--退票手续费或废票工本费，按票号返回，多个使用｜分隔
	private String scny;//账单价--Double(12,2) Y 按票号返回，多个使用｜分隔
	private String yq;//机建--Double(12,2) Y 按票号返回，多个使用｜分隔
	private String tax;//税费--Double(12,2) Y 按票号返回，多个使用｜分隔
	private String payment;//退款支付方式
	private String paymentCode;//退款支付方式代码
	private String noticeType;//通知类型(0:出票通知,1:退废票通知,2:支付通知)
	private String platcode;//平台标示
	private String orderNo;//平台订单号
	private String shbh;//商户编号
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getActualRefund() {
		return actualRefund;
	}
	public void setActualRefund(String actualRefund) {
		this.actualRefund = actualRefund;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRefundDatetime() {
		return refundDatetime;
	}
	public void setRefundDatetime(String refundDatetime) {
		this.refundDatetime = refundDatetime;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
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
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getNoticeType() {
		return noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	public String getPlatcode() {
		return platcode;
	}
	public void setPlatcode(String platcode) {
		this.platcode = platcode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
