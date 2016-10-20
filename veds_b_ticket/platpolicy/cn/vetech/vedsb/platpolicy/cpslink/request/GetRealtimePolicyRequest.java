package cn.vetech.vedsb.platpolicy.cpslink.request;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsRequest;

/**
 * 匹配异地平台政策统一入参
 * @author vetech
 *
 */
@XmlRootElement(name = "request")
public class GetRealtimePolicyRequest extends DsRequest{
	
	private List<TptzcZh> ptzczhList;
	private String fromDatetime;//起飞日期---YYYY-MM-DD
	private String fromCity;//出发城市--三字码
	private String toCity;//到达城市--三字码
	private String transitCity;//中转城市--联程或缺口程时传入--三字码
	private String passengerType;//乘机人类型--1成人，2儿童
	private String travelType;//航程类型--1单程，2往返，3联程，4缺口
	private String backFlightDate;//返程乘机日--往返、联程、缺口时传入，格式：YYYY-MM-DD
	private String airways;//航空公司二字码
	private String flightNo;//航班号与航程对应，往返联程缺口时传两个，格式示例：CA2345|CA123
	private String seatClass;//舱位与航程对应，往返联程缺口时传两个，格式示例：Y|B
	private String ticketPrice;//票价位为元，可不传，如不传入则表示不做匹配，如传入在普通产品时，判断是否开启票价限制，如有票价限制则按此条件过滤。
	private String pnrContent;//PNR内容
	private String patContent;//pat内容
	private String isSpecial;//政策类型0返回普通政策1返回特殊政策 3是都返回
	private String cfsj;//起飞时间HH:mm
	private String ddsj;//到达时间HH:mm
	private String scny;//单人账单价
	private String jsry;//单人机建燃油费
	private String adultno;//乘客人数
	private String passenger;//乘机人姓名多人用@分隔
	private String cardId;//乘机人证件号码 多人用@分隔
	private String pnrNo;
	private String outOrderNo;//订单编号
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
	public String getTransitCity() {
		return transitCity;
	}
	public void setTransitCity(String transitCity) {
		this.transitCity = transitCity;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getTravelType() {
		return travelType;
	}
	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
	public String getBackFlightDate() {
		return backFlightDate;
	}
	public void setBackFlightDate(String backFlightDate) {
		this.backFlightDate = backFlightDate;
	}
	public String getAirways() {
		return airways;
	}
	public void setAirways(String airways) {
		this.airways = airways;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getPnrContent() {
		return pnrContent;
	}
	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}
	public String getPatContent() {
		return patContent;
	}
	public void setPatContent(String patContent) {
		this.patContent = patContent;
	}
	public String getIsSpecial() {
		return isSpecial;
	}
	public void setIsSpecial(String isSpecial) {
		this.isSpecial = isSpecial;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getDdsj() {
		return ddsj;
	}
	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
	}
	public String getJsry() {
		return jsry;
	}
	public void setJsry(String jsry) {
		this.jsry = jsry;
	}
	public String getAdultno() {
		return adultno;
	}
	public void setAdultno(String adultno) {
		this.adultno = adultno;
	}
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	@XmlElementWrapper(name="ptzczhlist")
	@XmlElement(name="ptzczh")
	public List<TptzcZh> getPtzczhList() {
		return ptzczhList;
	}
	public void setPtzczhList(List<TptzcZh> ptzczhList) {
		this.ptzczhList = ptzczhList;
	}
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
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
	public String getScny() {
		return scny;
	}
	public void setScny(String scny) {
		this.scny = scny;
	}
}
