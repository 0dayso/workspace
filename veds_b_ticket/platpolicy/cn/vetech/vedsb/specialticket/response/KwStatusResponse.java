package cn.vetech.vedsb.specialticket.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class KwStatusResponse extends KwResponse{
	private String datetime;
	
	private List<KwOrder> orders;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	@XmlElement(name="order")
	public List<KwOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<KwOrder> orders) {
		this.orders = orders;
	}
	
}
