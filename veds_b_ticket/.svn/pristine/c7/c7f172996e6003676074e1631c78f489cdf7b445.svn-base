package cn.vetech.vedsb.platpolicy.cpslink;

import org.vetech.core.modules.utils.XmlUtils;

public abstract class DsRequest {
	private String service;
	
	private String shbh;

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
}
