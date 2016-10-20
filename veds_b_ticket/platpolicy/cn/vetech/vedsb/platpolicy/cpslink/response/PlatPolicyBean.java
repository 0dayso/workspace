package cn.vetech.vedsb.platpolicy.cpslink.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class PlatPolicyBean {
	private String platCode;//平台标识
	private String status="0";//0查询成功，-1查询失败
	private String msg;
	private List<RealtimePolicy> policyList;//政策集合
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@XmlElementWrapper(name = "policys")
	@XmlElement(name="policy")
	public List<RealtimePolicy> getPolicyList() {
		return policyList;
	}

	public void setPolicyList(List<RealtimePolicy> policyList) {
		this.policyList = policyList;
	}

	public String getPlatCode() {
		return platCode;
	}

	public void setPlatCode(String platCode) {
		this.platCode = platCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
