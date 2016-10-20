package cn.vetech.vedsb.platpolicy.cps.request.ticket;

import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;


/**
 * 根据PNR内容获取实时政策接口请求Bean(调取cps接口请求bean)
 * @author vetech
 * @version
 * 
 * 
 */
@XmlRootElement(name = "request")
public class GetRealtimePolicyReq extends CpsRequest {

    private String travelType;// 航程类型 1单程，2往返，3联程，4缺口
    
    private String fromCity;// 出发城市
    
    private String toCity;// 到达城市
    
    private String transitCity;// 中转城市
    
    private String flightDate;// 起飞日期 YYYY-MM-DD
    
    private String backFlightDate;// 返程乘机日 往返、联程、缺口时传入，格式：YYYY-MM-DD
    
    private String airways;// 航空公司
    
    private String flightNo;// 航班号，多个以|分隔
    
    private String seatClass;// 舱位，多个以|分隔
    
    private String ticketPrice;// 票价
    
    private String discount;// 折扣
    
    private String passengerType;// 乘机人类型 1成人，2儿童
    
    private String pnrContent;//PNR内容
    
    private String patContent;//PAT内容
    
    private String channel;//访问渠道
    
    public String getTravelType() {
        return this.travelType;
    }
    
    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }
    
    public String getFromCity() {
        return this.fromCity;
    }
    
    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }
    
    public String getToCity() {
        return this.toCity;
    }
    
    public void setToCity(String toCity) {
        this.toCity = toCity;
    }
    
    public String getTransitCity() {
        return this.transitCity;
    }
    
    public void setTransitCity(String transitCity) {
        this.transitCity = transitCity;
    }
    
    public String getFlightDate() {
        return this.flightDate;
    }
    
    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
    
    public String getBackFlightDate() {
        return this.backFlightDate;
    }
    
    public void setBackFlightDate(String backFlightDate) {
        this.backFlightDate = backFlightDate;
    }
    
    public String getAirways() {
        return this.airways;
    }
    
    public void setAirways(String airways) {
        this.airways = airways;
    }
    
    public String getFlightNo() {
        return this.flightNo;
    }
    
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }
    
    public String getSeatClass() {
        return this.seatClass;
    }
    
    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }
    
    public String getTicketPrice() {
        return this.ticketPrice;
    }
    
    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public String getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    
    public String getPassengerType() {
        return this.passengerType;
    }
    
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }
    
    public String getPnrContent() {
        return this.pnrContent;
    }
    
    public void setPnrContent(String pnrContent) {
        this.pnrContent = pnrContent;
    }
    
    public String getPatContent() {
        return this.patContent;
    }
    
    public void setPatContent(String patContent) {
        this.patContent = patContent;
    }

    
    public String getChannel() {
        return this.channel;
    }

    
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
}
