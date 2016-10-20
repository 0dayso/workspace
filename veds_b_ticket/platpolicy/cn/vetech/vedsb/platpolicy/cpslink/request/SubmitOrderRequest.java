package cn.vetech.vedsb.platpolicy.cpslink.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;
/**
 * 异地平台下单入参
 * @author vetech
 *
 */
@XmlRootElement(name = "request")
public class SubmitOrderRequest extends DsRequest{
	
	private String pnrNo;
	private String bigPnrNo;//航空公司大编码
	private String policyId;//政策id
	private String travelType;//航程类型-- 1单程、2往返、3联程、4缺口
	private String travelRange;//航程--WUHPEK|PEKCAN|WUHPEK
	private String flightNo;//航班号--航班号多个以|分隔
	private String planeType;//机型
	private String seatClass;//舱位--舱位多个以|分隔
	private String subSeatClass;//子舱位
//	private String discount;//折扣
	private String fromDatetime;//起飞时间格式示例：2013-08-30 09:30
	private String toDatetime;//到达时间
	private String tofromterminal;//出发航班楼
	private String terminal;//到达航站楼
	private String passenger;//乘机人--乘机人名1｜乘机人名1
	private String passengerType;//乘机人类型
	private String cardType;//证件类型
	private String cardId;//证件号码
	private String passengerMobile;//手机号
	private String scny;//账单价
	private String tax;//税费
	private String yq;//机建
	private String settlementPrice;//结算价--不写值，只参与比较之用
	private String ifChangeOrder;//是否升航换开订单--1是升舱换开，0不是升舱换开
	private String notifyAddress;//通知地址
	//private String promotionId;//促销活动ID
	//private String promotionType;//促销活动类型--1平台返利 ，与促销活动ID一一对应，格示示例：1|1
	//private String ifNfdSpecial;//是否是nfd特价1为NFD全国特价，其他不是
	private String patContent;//pat原始类容
	private String pnrContent;//pnr内容
	//private String tgqgd;//退改签规定
	private String outOrderNo;//订单编号
	private String linker;//联系人
	private String linkerEmail;//联系人email
	private String linkMobile;//联系人手机
	private String platCode;//平台标示
	private String autopay;//是否自动代扣 0否，1是
	private String fromCity;//出发城市
	private String toCity;//到达城市
	private String fromcitymc;//出发城市名称
	private String tocitymc;//到达城市名称
	private String passengerId;//乘机人id
	private String csrq;//出生日期--百拓下单多个乘机人出生日期用|分开
	private String xb;//性别--百拓下单多个乘机人性别用|分开
	private String pnrauthOffice;//PNR授权Office--百拓,8000翼下单需要
	private String TicketAuthOffice;//票号授权Office--百拓下单需要
	private TptzcZh tptzcZh;//平台账号信息
	private String pnrState;//pnr状态
	private String zfsbyy;//自动代扣失败原因
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
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getTravelRange() {
		return travelRange;
	}
	public void setTravelRange(String travelRange) {
		this.travelRange = travelRange;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getSubSeatClass() {
		return subSeatClass;
	}
	public void setSubSeatClass(String subSeatClass) {
		this.subSeatClass = subSeatClass;
	}
//	public String getDiscount() {
//		return discount;
//	}
//	public void setDiscount(String discount) {
//		this.discount = discount;
//	}
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
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getPassengerMobile() {
		return passengerMobile;
	}
	public void setPassengerMobile(String passengerMobile) {
		this.passengerMobile = passengerMobile;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getYq() {
		return yq;
	}
	public void setYq(String yq) {
		this.yq = yq;
	}
	public String getSettlementPrice() {
		return settlementPrice;
	}
	public void setSettlementPrice(String settlementPrice) {
		this.settlementPrice = settlementPrice;
	}
	public String getIfChangeOrder() {
		return ifChangeOrder;
	}
	public void setIfChangeOrder(String ifChangeOrder) {
		this.ifChangeOrder = ifChangeOrder;
	}
	public String getNotifyAddress() {
		return notifyAddress;
	}
	public void setNotifyAddress(String notifyAddress) {
		this.notifyAddress = notifyAddress;
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
//	public String getIfNfdSpecial() {
//		return ifNfdSpecial;
//	}
//	public void setIfNfdSpecial(String ifNfdSpecial) {
//		this.ifNfdSpecial = ifNfdSpecial;
//	}
	public String getPatContent() {
		return patContent;
	}
	public void setPatContent(String patContent) {
		this.patContent = patContent;
	}
	public String getPnrContent() {
		return pnrContent;
	}
	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}
//	public String getTgqgd() {
//		return tgqgd;
//	}
//	public void setTgqgd(String tgqgd) {
//		this.tgqgd = tgqgd;
//	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getLinker() {
		return linker;
	}
	public void setLinker(String linker) {
		this.linker = linker;
	}
	public String getLinkerEmail() {
		return linkerEmail;
	}
	public void setLinkerEmail(String linkerEmail) {
		this.linkerEmail = linkerEmail;
	}
	public String getLinkMobile() {
		return linkMobile;
	}
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getPnrauthOffice() {
		return pnrauthOffice;
	}
	public void setPnrauthOffice(String pnrauthOffice) {
		this.pnrauthOffice = pnrauthOffice;
	}
	public String getTicketAuthOffice() {
		return TicketAuthOffice;
	}
	public void setTicketAuthOffice(String ticketAuthOffice) {
		TicketAuthOffice = ticketAuthOffice;
	}
	public String getPlatCode() {
		return platCode;
	}
	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}
	public TptzcZh getTptzcZh() {
		return tptzcZh;
	}
	public void setTptzcZh(TptzcZh tptzcZh) {
		this.tptzcZh = tptzcZh;
	}
	public String getAutopay() {
		return autopay;
	}
	public void setAutopay(String autopay) {
		this.autopay = autopay;
	}
	public String getPnrState() {
		return pnrState;
	}
	public void setPnrState(String pnrState) {
		this.pnrState = pnrState;
	}
	public String getPassengerId() {
		return passengerId;
	}
	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}
	public String getZfsbyy() {
		return zfsbyy;
	}
	public void setZfsbyy(String zfsbyy) {
		this.zfsbyy = zfsbyy;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getFromcitymc() {
		return fromcitymc;
	}
	public void setFromcitymc(String fromcitymc) {
		this.fromcitymc = fromcitymc;
	}
	public String getTocitymc() {
		return tocitymc;
	}
	public void setTocitymc(String tocitymc) {
		this.tocitymc = tocitymc;
	}
	public String getScny() {
		return scny;
	}
	public void setScny(String scny) {
		this.scny = scny;
	}
}
