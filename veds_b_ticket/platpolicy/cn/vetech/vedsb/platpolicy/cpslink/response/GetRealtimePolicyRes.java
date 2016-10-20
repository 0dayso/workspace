package cn.vetech.vedsb.platpolicy.cpslink.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cpslink.DsResponse;
/**
 * 异地平台返回的政策集合
 * @author vetech
 *
 */
@XmlRootElement(name = "response")
public class GetRealtimePolicyRes extends DsResponse{

	private List<PlatPolicyBean> platPolicyList;
	
	@XmlElementWrapper(name="platPolicyList")
	@XmlElement(name="platPolicyBean")
	public List<PlatPolicyBean> getPlatPolicyList() {
		return platPolicyList;
	}

	public void setPlatPolicyList(List<PlatPolicyBean> platPolicyList) {
		this.platPolicyList = platPolicyList;
	}
	
}
