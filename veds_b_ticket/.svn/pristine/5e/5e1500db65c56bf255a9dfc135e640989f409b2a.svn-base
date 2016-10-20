package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;

/**
 * CPS采购退废申请请求参数
 * @author wangshengliang
 *
 */
@XmlRootElement(name = "request")
public class TicketRefundCpsRequest extends CpsRequest {

	/**
	 * 订单编号 String(20) Y CPS的订单编号
	 */
	private String orderNo;

	/**
	 * 退废类型 String(1) Y 1退票，2废票
	 */
	private String refundType;

	/**
	 * 航程 String(60) Y 航程，多个用｜分隔，如：WUHPEK|PEKWUH
	 */
	private String travelRange;

	/**
	 * 票号 String(200) Y 票号，多个使用｜分隔
	 */
	private String ticketNo;

	/**
	 * 乘机人 String(600) Y 乘机人名，多个使用｜分隔
	 */
	private String passenger;

	/**
	 * 是否委托平台取消座位 String(1) Y 1、系统帮我取消，或请平台客服帮我取消座位 2、请保留座位 3、座位已取消
	 */
	private String isCancelSeat;

	/**
	 * 退废票理由 String(10) Y 退废理由代号，取字数据字典
	 */
	private String refundReason;
	
	/**
	 * 其他原因
	 */
	private String otherReason;
	
	/**
	 * 退废证明路径
	 */
	private String certificateUrl;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRefundType() {
		return refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
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

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getIsCancelSeat() {
		return isCancelSeat;
	}

	public void setIsCancelSeat(String isCancelSeat) {
		this.isCancelSeat = isCancelSeat;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}
	
	
}
