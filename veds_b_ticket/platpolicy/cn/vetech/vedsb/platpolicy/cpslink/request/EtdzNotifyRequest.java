package cn.vetech.vedsb.platpolicy.cpslink.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;
/**
 * 出票通知
 * @author vetech
 *
 */
@XmlRootElement(name = "request")
public class EtdzNotifyRequest extends DsRequest{

	private String orderStatus;//订单状态-订单状态操作码(142或161成功)
	private String outOrderNo;//订单编号
	private String reason;//暂不能出票原因
	private String pnrNo;
	private String bigPnrNo;
	private String oldPnrNo;//换PNR出票前PNR
	private String oldBigPnrNo;// 换PNR出票前的大编码  
	private String payment;//支付方式--传入平台的支付方式的编号。如312013301
	private String paymentTransaction;//交易流水号
	private String paymentAmount;//支付金额
	private String ticketNo;//票号格示示例：999-1234567890|999-1234567891|999-1234567893|999-1234567894支持9人的票号
	private String travelRange;//航程--格式示例：WUHPEK|PEKCAN|WUHPEK|PEKCAN
	private String fromDatetime;//出发时间-格式示例：2013-9-14 11:00:00|2013-9-14 11:00:00
	private String passenger;//乘机人姓名--格式示例：乘机人名1｜乘机人名1
	private String cardId;//证件号码--421234021|3421324
	private String ticketType;//出票类型
	private String rebate;//航空公司返点
	private String deductionAmount;//留钱
	private String scny;//账单价
	private String yq;//机建
	private String tax;//税费
	private String continuousTicket;///连续客票--格式:4323564323/24|4323564325/26  
	private String noticeType;//通知类型(0:出票通知,1:退废票通知,2:支付通知)
	private String platcode;//平台标示
	private String shbh;//商户编号
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public String getBigPnrNo() {
		return bigPnrNo;
	}
	public void setBigPnrNo(String bigPnrNo) {
		this.bigPnrNo = bigPnrNo;
	}
	public String getOldPnrNo() {
		return oldPnrNo;
	}
	public void setOldPnrNo(String oldPnrNo) {
		this.oldPnrNo = oldPnrNo;
	}
	public String getOldBigPnrNo() {
		return oldBigPnrNo;
	}
	public void setOldBigPnrNo(String oldBigPnrNo) {
		this.oldBigPnrNo = oldBigPnrNo;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPaymentTransaction() {
		return paymentTransaction;
	}
	public void setPaymentTransaction(String paymentTransaction) {
		this.paymentTransaction = paymentTransaction;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getTravelRange() {
		return travelRange;
	}
	public void setTravelRange(String travelRange) {
		this.travelRange = travelRange;
	}
	public String getFromDatetime() {
		return fromDatetime;
	}
	public void setFromDatetime(String fromDatetime) {
		this.fromDatetime = fromDatetime;
	}
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getRebate() {
		return rebate;
	}
	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
	public String getDeductionAmount() {
		return deductionAmount;
	}
	public void setDeductionAmount(String deductionAmount) {
		this.deductionAmount = deductionAmount;
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
	public String getContinuousTicket() {
		return continuousTicket;
	}
	public void setContinuousTicket(String continuousTicket) {
		this.continuousTicket = continuousTicket;
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
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
}
