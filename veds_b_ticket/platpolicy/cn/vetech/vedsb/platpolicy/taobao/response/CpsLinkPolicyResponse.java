package cn.vetech.vedsb.platpolicy.taobao.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.taobao.model.CpsLinkTbcb;

@XmlRootElement(name="response")
public class CpsLinkPolicyResponse{
	private String status;  //1代表成功  -1其他为失败
	
	private String msg;  //失败原因 
	
	private String airawys;  //航空公司二字码
	private String depAirport; //出发城市
	private String arrAirport; //到达城市
	private String travelRange;  //航程
	private String fromDatetime;  //起飞时间
	private String toDatetime;  //到达时间
	private String flightNo;  // 航班号
	
	private List<CpsLinkTbcb> tbcp_info;//淘宝舱位

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAirawys() {
		return airawys;
	}

	public void setAirawys(String airawys) {
		this.airawys = airawys;
	}

	public String getDepAirport() {
		return depAirport;
	}

	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}

	public String getArrAirport() {
		return arrAirport;
	}

	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
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
	
	public String getToDatetime() {
		return toDatetime;
	}

	public void setToDatetime(String toDatetime) {
		this.toDatetime = toDatetime;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public List<CpsLinkTbcb> getTbcp_info() {
		return tbcp_info;
	}

	public void setTbcp_info(List<CpsLinkTbcb> tbcp_info) {
		this.tbcp_info = tbcp_info;
	}
	
}
