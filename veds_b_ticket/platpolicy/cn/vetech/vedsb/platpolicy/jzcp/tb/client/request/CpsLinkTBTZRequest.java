package cn.vetech.vedsb.platpolicy.jzcp.tb.client.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.tb.client.CpsLinkRequest;

@XmlRootElement(name="request")
public class CpsLinkTBTZRequest extends CpsLinkRequest{
	private String asmsddbhs; //多个订单编号 用逗号分隔
	private String tzsj; //查询时间 yyyy-MM-dd
	private String appkey;//淘宝代购appeky
	
	public String getAsmsddbhs() {
		return asmsddbhs;
	}
	public void setAsmsddbhs(String asmsddbhs) {
		this.asmsddbhs = asmsddbhs;
	}
	public String getTzsj() {
		return tzsj;
	}
	public void setTzsj(String tzsj) {
		this.tzsj = tzsj;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	
}
