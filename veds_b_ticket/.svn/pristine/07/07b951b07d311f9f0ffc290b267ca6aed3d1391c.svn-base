package cn.vetech.vedsb.platpolicy.cpslink.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;
/**
 * 异地平台申请颓废票入参
 * @author vetech
 *
 */
@XmlRootElement(name = "request")
public class TicketRefundRequest extends DsRequest{

	private String orderNo;//订单编号
	private String refundType;//退废类型
	private String travelRange;//航程--多个用｜分隔，如：WUHPEK|PEKWUH
	private String ticketNo;//票号-多个使用｜分隔
	private String passenger;//乘机人名，多个使用｜分隔
	private String refundReason;//退废理由
	private String certificateUrl;//退废证明
	private String outOrderNo;//外部单号
	private String platCode;//平台标示
	private String xtpnrzt;//电商系统pnr状态
	/**10008 退票原因 1000803 其它退票情况 1000802 因航班取消延误，申请全退 1000801 客人自愿退票，按客规收取手续费 1000804 升舱换开，申请全退 1000805 名字错换开重出，申请全退
	 * 1000806 客人因病无法乘机，申请全退 1000807 申请退回票款差价
	 * 10007 废票原因 1000703 其它废票情况 1000701 当日作废，收取手续费
	 */
	private String refundReasondm;//退票理由代码
	private String pnrCjrZjh;//乘机人证件号码--多个使用｜分隔
	private String passengerType;//乘机人类型--多个使用｜分隔
	private String pnrno;
	private TptzcZh tptzcZh;//平台账号
	private String hbh;//航班号(鹏朋平台用到)-多个用|分隔
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
	public String getRefundReason() {
		return refundReason;
	}
	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	public String getCertificateUrl() {
		return certificateUrl;
	}
	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getXtpnrzt() {
		return xtpnrzt;
	}
	public void setXtpnrzt(String xtpnrzt) {
		this.xtpnrzt = xtpnrzt;
	}
	public String getRefundReasondm() {
		return refundReasondm;
	}
	public void setRefundReasondm(String refundReasondm) {
		this.refundReasondm = refundReasondm;
	}
	public String getPnrCjrZjh() {
		return pnrCjrZjh;
	}
	public void setPnrCjrZjh(String pnrCjrZjh) {
		this.pnrCjrZjh = pnrCjrZjh;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public TptzcZh getTptzcZh() {
		return tptzcZh;
	}
	public void setTptzcZh(TptzcZh tptzcZh) {
		this.tptzcZh = tptzcZh;
	}
	public String getPlatCode() {
		return platCode;
	}
	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	public String getHbh() {
		return hbh;
	}
	public void setHbh(String hbh) {
		this.hbh = hbh;
	}
}
