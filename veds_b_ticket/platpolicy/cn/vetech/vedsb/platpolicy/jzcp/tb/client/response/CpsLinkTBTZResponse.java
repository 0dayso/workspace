package cn.vetech.vedsb.platpolicy.jzcp.tb.client.response;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.tb.bean.CpsLinkTbtz;
import cn.vetech.vedsb.platpolicy.jzcp.tb.client.CpsLinkRequest;

@XmlRootElement(name="response")
public class CpsLinkTBTZResponse extends CpsLinkRequest{
	
	private String status;  //1代表成功  -1其他为失败
	
	private String msg;  //失败原因 

	private List<CpsLinkTbtz> tzinfo;
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

	public List<CpsLinkTbtz> getTzinfo() {
		return tzinfo;
	}

	public void setTzinfo(List<CpsLinkTbtz> tzinfo) {
		this.tzinfo = tzinfo;
	}

}
