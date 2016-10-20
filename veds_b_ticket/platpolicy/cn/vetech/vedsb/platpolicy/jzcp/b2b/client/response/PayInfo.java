package cn.vetech.vedsb.platpolicy.jzcp.b2b.client.response;

import java.io.Serializable;

/**
 * 返回给超级电商的信息
 * 
 * @author zhanglei
 *
 */
public class PayInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4117253410719480111L;

	/**
	 * T为成功 F为失败 E为本系统异常 H手动支付
	 */
	private String success;

	/**
	 * 航空公司支付串信息
	 */
	private String airurl;
	
	/**
	 * 航空公司支付表单
	 */
	private String airform;

	/**
	 * 支付的错误信息
	 */
	private String error;

	/**
	 * 交易流水号
	 */
	private String tradeno; //

	private String zflx; // 1为支付宝，2为财付通

	private double paytotal;// 支付金额 单位是元

	private String zfzh;// 支付账号
	
	private String zfxx7;//存储业务系统对应的支付科目
	/**
	 * 用户支付方式 // 支付宝 B余额 X信用额度 BX先余额后信用 预存卡：P；
	 */
	private String userpaytype;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getAirurl() {
		return airurl;
	}

	public void setAirurl(String airurl) {
		this.airurl = airurl;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}

	public double getPaytotal() {
		return paytotal;
	}

	public void setPaytotal(double paytotal) {
		this.paytotal = paytotal;
	}

	public String getZfzh() {
		return zfzh;
	}

	public void setZfzh(String zfzh) {
		this.zfzh = zfzh;
	}

	public String getUserpaytype() {
		return userpaytype;
	}

	public void setUserpaytype(String userpaytype) {
		this.userpaytype = userpaytype;
	}

	public String getError() {
		return error;
	}

	public String getZflx() {
		return zflx;
	}

	public void setZflx(String zflx) {
		this.zflx = zflx;
	}

	public String getZfxx7() {
		return zfxx7;
	}

	public void setZfxx7(String zfxx7) {
		this.zfxx7 = zfxx7;
	}

	public String getAirform() {
		return airform;
	}

	public void setAirform(String airform) {
		this.airform = airform;
	}

}
