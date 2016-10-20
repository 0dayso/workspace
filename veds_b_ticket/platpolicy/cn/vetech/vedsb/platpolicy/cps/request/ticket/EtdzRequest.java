package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 电商采购接收cps出票成功的通知
 * <功能详细描述>
 * @author
 * @version
 * @see [相关类/方法]
 * @since [CPS]
 */
@XmlRootElement(name="request")
public class EtdzRequest{
	
	/**
	 * 订单状态	String(10)	Y	 订单状态操作码
	 *  142或161出票成功
	 */
	private String orderStatus;

	/**
	 * 订单编号  String(20)  Y  CPS的订单编号  
	 */ 
	private String orderNo;

	/**
	 * 暂不能出票/拒单原因  String(200)  N  暂不能出票或拒单时，要返回原因。  
	 */ 
	private String reason;

	/**
	 * PNR  String(10)  N  订单的PNR  
	 */ 
	private String pnrNo;

	/**
	 * 大编码  String(10)  N  订单的大编码  
	 */ 
	private String bigPnrNo;

	/**
	 * 原PNR  String(10)  N  换PNR出票前PNR  
	 */ 
	private String oldPnrNO;

	/**
	 * 原大编码  String(10)  N  换PNR出票前的大编码  
	 */ 
	private String oldBigPnrNo;

	/**
	 * 支付方式  String(10)  N  传入CPS的支付方式的编号。如312013301，ASMS里设置对应关系进行匹配。  
	 */ 
	private String payment;

	/**
	 * 交易流水号  String(60)  N  
	 */ 
	private String paymentTransaction;

	/**
	 * 支付金额  String(20)  N  
	 */ 
	private String paymentAmount;

	/**
	 * 票号  String(200)  N  格示示例：999-1234567890|999-1234567891|999-1234567893|999-1234567894支持9人的票号。
	 */ 
	private String ticketNo;

	/**
	 * 航程  String(60)  N  格式示例：WUHPEK|PEKCAN|WUHPEK|PEKCAN  
	 */ 
	private String travelRange;
	
	/**
	 * 出发时间  String(200)  N  格式示例：2013-9-14 11:00:00|2013-9-14 11:00:00|2013-9-14 15:00:00|2013-9-14 15:00:00 
	 */ 
	private String fromDatetime;

	/**
	 * 乘机人姓名  String(600)  N  格式示例：乘机人名1｜乘机人名1｜乘机人名2｜乘机人名2  
	 */ 
	private String passenger;

	/**
	 * 证件号码  String(300)  N  格式示例：421234021|3421324|421234021|3421324  
	 */ 
	private String cardId;
	
	/**
	 * 出票类型  String(10)  N  
	 */ 
	private String ticketType;
	
	/**
	 * 航空公司返点  String(10)  N    0.065
	 */ 
	private String rebate;
	
	/**
	 * 留钱  String(10)  N    10
	 */ 
	private String deductionAmount;
	
	/**
	 * 账单价	String(200)	N	格式示例：1000|1000|1000
	 */ 
	private String scny;
	/**
	 * 机建	String(200)	N	格式示例：50|50|50
	 */ 
	private String yq;
	/**
	 * 税费	String(200)	N	格式示例：150|150|150
	 */ 
	private String tax;
	
	/**
	 * 连续客票  String(200)  N   格式:4323564323/24|4323564325/26  
	 */ 
	private String continuousTicket;
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getOldPnrNO() {
		return oldPnrNO;
	}

	public void setOldPnrNO(String oldPnrNO) {
		this.oldPnrNO = oldPnrNO;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFromDatetime() {
		return fromDatetime;
	}

	public void setFromDatetime(String fromDatetime) {
		this.fromDatetime = fromDatetime;
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

}
/*
<Request>
   <status>出票状态</status>
   <businessNo>商户号</businessNo>
   <orderNo>订单编号</orderNo>
   <reason>暂不能出票/拒单原因</reason>
   <pnrNo>PNR</pnrNo>
   <bigPnrNo>大编码</bigPnrNo>
   <oldPnrNO>原PNR</oldPnrNO>
   <oldBigPnrNo>原大编码</oldBigPnrNo>
   <payment>支付方式</payment>
   <paymentTransaction>交易流水号</paymentTransaction>
   <paymentAmount>支付金额</paymentAmount>
   <ticketNo>票号</ticketNo>
   <travelRange>航程</travelRange>
   <passenger>乘机人姓名</passenger>
   <cardId>证件号码</cardId>

</Request>
*/

