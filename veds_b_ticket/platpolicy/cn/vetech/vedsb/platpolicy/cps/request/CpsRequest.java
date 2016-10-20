package cn.vetech.vedsb.platpolicy.cps.request;

import javax.xml.bind.annotation.XmlElement;

/**
 * 接口业务参数类
 * 
 * @author zhanglei
 *
 */
public abstract class CpsRequest {

	/**
	 * 接口服务名
	 */
	private String service;

	/**
	 * 请求来源
	 */
	private String qqly;

	@XmlElement(name = "service")
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getQqly() {
		return qqly;
	}

	public void setQqly(String qqly) {
		this.qqly = qqly;
	}

}
