package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 采购退款通知参数
 * 
 * @author
 */
@XmlRootElement(name = "request")
public class TicketRefundNoticeRequest{

	/**
	 * 订单编号 String(20) Y 正常订单编号
	 */
	private String orderNo;

	/**
	 * 退废单号 String(20) Y 退废单号
	 */
	private String refundNo;

	/**
	 * 状态 退款或拒单，对应状态三字码
	 */
	private String refundStatus;

	/**
	 * 实际退款 Double(12,2) N 退废单实际退款金额，不含退改签费用的金额
	 */
	private String actualRefund;

	/**
	 * 实退改签费用 Double(12,2) N 实退改签费用
	 */
	private String actualChangeFee;

	/**
	 * 拒单原因 String(200) N 拒单时，传入原因
	 */
	private String reason;

	/**
	 * 退款时间 String(20) N 退款时间 yyyy-mm-dd hh:mm:ss
	 */
	private String refundDatetime;

	/**
	 * 票号 String(200) Y 票号，多个使用｜分隔
	 */
	private String ticketNo;

	/**
	 * 退票手续费 Double(12,2) Y 退票手续费或废票工本费，按票号返回，多个使用｜分隔
	 */
	private String refundFee;

	/**
	 * 账单价 Double(12,2) Y 按票号返回，多个使用｜分隔
	 */
	private String scny;

	/**
	 * 机建 Double(12,2) Y 按票号返回，多个使用｜分隔
	 */
	private String yq;

	/**
	 * 税费 Double(12,2) Y 按票号返回，多个使用｜分隔
	 */
	private String tax;

	/**
	 * 实退改签费用明细 Double(12,2) N 实退改签费用明细，按票号返回，多个使用｜分隔
	 */
	private String actualChangeFeeDetail;

	/**
	 * 改签单号 按票号返回，多个使用｜分隔，一张票多个改签单用,分隔
	 */
	private String changeNoDetail;
	
	/**
     * 退款支付方式
     */
    private String payment;

    /**
     * 退款支付方式代码
     */
    private String paymentCode;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
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

	public String getActualChangeFee() {
		return actualChangeFee;
	}

	public void setActualChangeFee(String actualChangeFee) {
		this.actualChangeFee = actualChangeFee;
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

	public String getActualChangeFeeDetail() {
		return actualChangeFeeDetail;
	}

	public void setActualChangeFeeDetail(String actualChangeFeeDetail) {
		this.actualChangeFeeDetail = actualChangeFeeDetail;
	}

	public String getChangeNoDetail() {
		return changeNoDetail;
	}

	public void setChangeNoDetail(String changeNoDetail) {
		this.changeNoDetail = changeNoDetail;
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
	
}
