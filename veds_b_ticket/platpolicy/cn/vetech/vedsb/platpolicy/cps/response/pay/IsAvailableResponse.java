package cn.vetech.vedsb.platpolicy.cps.response.pay;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;

@XmlRootElement(name = "response")
public class IsAvailableResponse extends CpsResponse{
	private String xml;

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}
}
