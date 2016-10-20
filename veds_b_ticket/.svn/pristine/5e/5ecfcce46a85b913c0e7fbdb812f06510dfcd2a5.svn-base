package cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2b.client.VetpsPurchasB2bResponse;

@XmlRootElement(name = "response")
public class B2bOrderResponse  extends VetpsPurchasB2bResponse{

	private String b2bstate;// b2b出票状态 -1下单没有成功出现异常 10 下单成功 返回订单编号和金额 20 支付成功 返回支付方式和支付金额 21 支付失败 30 出票成功 返回票号

	private OrderInfo orderInfo;

	private StateInfo stateInfo;

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}

	public StateInfo getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(StateInfo stateInfo) {
		this.stateInfo = stateInfo;
	}

	public String getB2bstate() {
		return b2bstate;
	}

	public void setB2bstate(String b2bstate) {
		this.b2bstate = b2bstate;
	}

}
