package cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2b.bean.B2bpolicyBean;
import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bResponse;

@XmlRootElement(name = "response")
public class B2bPolicyResponse extends VetpsPurchasB2bResponse{
	private String image;
	private String requireLogin;  //0或空表示不需要登录 1表示需要手工登录
	private String sbyy;
    private List<B2bpolicyBean> b2bPolicyBean = new ArrayList<B2bpolicyBean>();

    

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRequireLogin() {
		return requireLogin;
	}

	public void setRequireLogin(String requireLogin) {
		this.requireLogin = requireLogin;
	}

	public String getSbyy() {
		return sbyy;
	}

	public void setSbyy(String sbyy) {
		this.sbyy = sbyy;
	}

	public List<B2bpolicyBean> getB2bPolicyBean() {
		return b2bPolicyBean;
	}

	public void setB2bPolicyBean(List<B2bpolicyBean> policyBean) {
		b2bPolicyBean = policyBean;
	}
	
}
