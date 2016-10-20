package cn.vetech.vedsb.jp.jpddsz.taobao.model;


/**
 * 供应处理改签单通知参数
 * 
 * @author chenjie
 * @version [版本号, Oct 26, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class TicketChangeDealGy {
	/**
	 * 订单编号  String(20)  N  淘宝订单编号  
	 */ 
	private String orderNo;

	/**
	 * 改签单号  String(20)  Y  
	 */ 
	private String changeNo;

	/**
	 * 改签类型  String(1)  N  1改期、2升舱  
	 */ 
	private String changeType;

	/**
	 * 改签单状态  String(1)  Y  待确认、已支付待办理，传状态值  
	 */ 
	private String changeStatus;

	/**
	 * 改签理由  String(200)  N  传改签原因代号  
	 */ 
	private String changeReason;

	/**
	 * 乘机人名  String(400)  N  格示示例：乘机人名1｜乘机人名2  
	 */ 
	private String passenger;

	/**
	 * 航程  String(60)  N  格示示例：WUHPEK|PEKSHA  
	 */ 
	private String travelRange;

	/**
	 * 票号  String(200)  N  格示示例：999-1234567890|999-1234567891  
	 */ 
	private String ticketNo;

	/**
	 * 改期后出发时间  String(20)  N  格示示例：2013-01-01  12:00|2013-01-03  13:00  
	 */ 
	private String fromDatetime;

	/**
	 * 改期后到达时间  String(20)  N  格示示例：2013-01-01  12:00|2013-01-03  13:00  
	 */ 
	private String toDatetime;

	/**
	 * 改签后航班号  String(20)  N  格示示例：CA1232|CA2345  
	 */ 
	private String newFlightNo;

	/**
	 * 改签后舱位  String(20)  N  格示示例：Y|Y  
	 */ 
	private String newSeatClass;

	/**
	 * 机型  String(30)  N  格式示例：738｜319  
	 */ 
	private String planeType;

	/**
	 * 出发航站楼  String(30)  N  格式示例：T2|A  
	 */ 
	private String tofromterminal;

	/**
	 * 到达航站楼  String(30)  N  格式示例：A|T2  
	 */ 
	private String terminal;

	/**
	 * 退改签规定  String(4000)  N  
	 */ 
	private String gaiqianRetirement;

	/**
	 * 支付方式  String(10)  N  传入CPS的支付方式的编号。如312013301，ASMS里设置对应关系进行匹配。  
	 */ 
	private String payment;

	/**
	 * 交易流水号  String(60)  N  
	 */ 
	private String paymentTransaction;
	
	private String ideType;//证件类型
	
	private String ideNo;//证件号码
	
	private String n_ideType;//新证件类型
	
	private String n_ideNo;//新证件号码

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getChangeNo() {
		return changeNo;
	}

	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeStatus() {
		return changeStatus;
	}

	public void setChangeStatus(String changeStatus) {
		this.changeStatus = changeStatus;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getTravelRange() {
		return travelRange;
	}

	public void setTravelRange(String travelRange) {
		this.travelRange = travelRange;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getFromDatetime() {
		return fromDatetime;
	}

	public void setFromDatetime(String fromDatetime) {
		this.fromDatetime = fromDatetime;
	}

	public String getToDatetime() {
		return toDatetime;
	}

	public void setToDatetime(String toDatetime) {
		this.toDatetime = toDatetime;
	}

	public String getNewFlightNo() {
		return newFlightNo;
	}

	public void setNewFlightNo(String newFlightNo) {
		this.newFlightNo = newFlightNo;
	}

	public String getNewSeatClass() {
		return newSeatClass;
	}

	public void setNewSeatClass(String newSeatClass) {
		this.newSeatClass = newSeatClass;
	}

	public String getPlaneType() {
		return planeType;
	}

	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}

	public String getTofromterminal() {
		return tofromterminal;
	}

	public void setTofromterminal(String tofromterminal) {
		this.tofromterminal = tofromterminal;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getGaiqianRetirement() {
		return gaiqianRetirement;
	}

	public void setGaiqianRetirement(String gaiqianRetirement) {
		this.gaiqianRetirement = gaiqianRetirement;
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

	public String getIdeType() {
		return ideType;
	}

	public void setIdeType(String ideType) {
		this.ideType = ideType;
	}

	public String getIdeNo() {
		return ideNo;
	}

	public void setIdeNo(String ideNo) {
		this.ideNo = ideNo;
	}

	public String getN_ideType() {
		return n_ideType;
	}

	public void setN_ideType(String type) {
		n_ideType = type;
	}

	public String getN_ideNo() {
		return n_ideNo;
	}

	public void setN_ideNo(String no) {
		n_ideNo = no;
	}
}
