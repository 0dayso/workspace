package cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bResponse;

@XmlRootElement(name = "response")
public class B2bStateResponse extends VetpsPurchasB2bResponse {
	private String businessNo;// 商户编号
	private String userid;// 操作人编号

	private int count;// 数量

	private List<StateInfo> stateList = new ArrayList<StateInfo>();

	public String getBusinessNo() {
		return businessNo;
	}

	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static void main(String[] args) {
		B2bStateResponse s = new B2bStateResponse();
		StateInfo bs = new StateInfo();
		bs.setAirways("MU");

		//B2bOrderResponse pr = new B2bOrderResponse();
		OrderInfo p = new OrderInfo();
		p.setOrderNo("adfdsfas");

		s.getStateList().add(bs);
		System.out.println(XmlUtils.toXml(s));
	}

	public List<StateInfo> getStateList() {
		return stateList;
	}

	public void setStateList(List<StateInfo> stateList) {
		this.stateList = stateList;
	}

}
