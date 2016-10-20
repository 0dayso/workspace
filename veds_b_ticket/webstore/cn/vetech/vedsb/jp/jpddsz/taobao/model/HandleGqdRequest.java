package cn.vetech.vedsb.jp.jpddsz.taobao.model;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.request.CpsRequest;

@XmlRootElement(name="request")
public class HandleGqdRequest extends CpsRequest{
	private String username;
	private String zgs;
	private String changeNo;//改签单号
	private String passenger;//多个用|分隔  和票号顺序保持一致
	private String travelRange;//多个用|分隔  和票号顺序保持一致
	private String ticketNo;//多个用|分隔  

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getZgs() {
		return zgs;
	}
	public void setZgs(String zgs) {
		this.zgs = zgs;
	}
	public String getPassenger() {
		return passenger;
	}
	public void setPassenger(String passenger) {
		this.passenger = passenger;
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
	public String getChangeNo() {
		return changeNo;
	}
	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}
}
