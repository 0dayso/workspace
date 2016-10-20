package cn.vetech.vedsb.platpolicy.cpslink;

import org.vetech.core.modules.utils.XmlUtils;

public abstract class DsResponse {
	public static final String SUCCESS="0";
	public static final String FAILL="-1";
	private String status=SUCCESS;
	private String msg;
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
	public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
}
