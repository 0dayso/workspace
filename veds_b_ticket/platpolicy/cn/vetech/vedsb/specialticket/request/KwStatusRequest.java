package cn.vetech.vedsb.specialticket.request;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="request")
public class KwStatusRequest extends KwRequest{
	private String sqdh;//多个逗号拼接，所有则不填
	private String zwsj;
	public String getSqdh() {
		return sqdh;
	}
	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}
	public String getZwsj() {
		return zwsj;
	}
	public void setZwsj(String zwsj) {
		this.zwsj = zwsj;
	}
}
