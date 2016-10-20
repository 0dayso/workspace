package cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class OrderInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3016948996696774139L;
	private String outOrderNo;// 下单编号 8000订单号
	private String airwaysOrderNo;// 官网订单编号 航司订单号
	private String orderNo;// C站系统订单编号

	private String state; //状态 0 等待支付 1 出票完成 2 待出票 5客票挂起 -1 订单取消  

	private String airwaysAccount; // 航司账号
	private String userid; // 用户编号

	private Double totalPrice;// 总票面价 不包括税费的 票面价格
	private Double totalTax;// 总税费
	private Double agencyMoney;// 代理费金额
	private Double paymentMoney;// 支付金额
	
	public boolean isTicketOk() {
		if (CollectionUtils.isNotEmpty(passengerInfos)) {
			if (StringUtils.isNotBlank(passengerInfos.get(0).getTicketNo())) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 支付信息
	 */
	private PayInfo payInfo;

	// 乘机人信息
	private List<PassengerInfo> passengerInfos;

	public String getOutOrderNo() {
		return outOrderNo;
	}

	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}

	public String getAirwaysOrderNo() {
		return airwaysOrderNo;
	}

	public void setAirwaysOrderNo(String airwaysOrderNo) {
		this.airwaysOrderNo = airwaysOrderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAirwaysAccount() {
		return airwaysAccount;
	}

	public void setAirwaysAccount(String airwaysAccount) {
		this.airwaysAccount = airwaysAccount;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<PassengerInfo> getPassengerInfos() {
		return passengerInfos;
	}

	public void setPassengerInfos(List<PassengerInfo> passengerInfos) {
		this.passengerInfos = passengerInfos;
	}

	public PayInfo getPayInfo() {
		return payInfo;
	}

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}

	public Double getAgencyMoney() {
		return agencyMoney;
	}

	public void setAgencyMoney(Double agencyMoney) {
		this.agencyMoney = agencyMoney;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public Double getPaymentMoney() {
		return paymentMoney;
	}

	public void setPaymentMoney(Double paymentMoney) {
		this.paymentMoney = paymentMoney;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
