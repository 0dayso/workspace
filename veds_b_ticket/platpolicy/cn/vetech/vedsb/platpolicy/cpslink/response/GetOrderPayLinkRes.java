package cn.vetech.vedsb.platpolicy.cpslink.response;

import java.util.List;
public class GetOrderPayLinkRes{

	private List<PayUrl> payLinkList;
	
	public List<PayUrl> getPayLinkList() {
		return payLinkList;
	}

	public void setPayLinkList(List<PayUrl> payLinkList) {
		this.payLinkList = payLinkList;
	}
}
