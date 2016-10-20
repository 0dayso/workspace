package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasResponse;

@XmlRootElement(name="response")
public class B2cCheckPendingOrderResponse extends VetpsPurchasResponse{
	private int codeFlag;//是否有待输验证码,1有，0没有
	private int payFlag;//是否有待手动支付，1有，0没有
	public int getCodeFlag() {
		return codeFlag;
	}
	public void setCodeFlag(int codeFlag) {
		this.codeFlag = codeFlag;
	}
	public int getPayFlag() {
		return payFlag;
	}
	public void setPayFlag(int payFlag) {
		this.payFlag = payFlag;
	}
}
