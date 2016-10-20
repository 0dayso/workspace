package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.PassengerInfo;

@XmlRootElement(name="request")
public class TicketNoRequest {
    private String outOrderNo;                       //8000订单号
    private String airwaysOrderNo;                   //航司订单号
    private String orderNo;                          //C站订单号
    private String salePrice;                        //销售价
    private String airwaysAccount;                   //航司账号
    private String userid;                           //用户编号
    private String payment;                          //航司支付方式
	private String seatClass;                        //舱位
	private String payAccount;                       //自动支付账号信息
	private String paymentAmount;                    //支付金额
    private List<PassengerInfo> passengerInfos;      //乘机人信息
    private String buyPaymentTransaction;            //支付流水号
    private String office;                           //office号
    
    
	public void valid() throws Exception{
		if(StringUtils.isBlank(outOrderNo)){
			throw new Exception("下单编号不能为空");
		}
		if(StringUtils.isBlank(airwaysOrderNo)){
			throw new Exception("航司单号不能为空");
		}
		if(StringUtils.isBlank(orderNo)){
			throw new Exception("代购订单号不能为空");
		}
		if(StringUtils.isBlank(salePrice)){
			throw new Exception("销售价不能为空");
		}
		if(StringUtils.isBlank(airwaysAccount)){
			throw new Exception("航司号不能为空");
		}
		if(StringUtils.isBlank(userid)){
			throw new Exception("用户编号不能为空");
		}
		if(StringUtils.isBlank(payment)){
			throw new Exception("航司支付方式不能为空");
		}
		if(StringUtils.isBlank(seatClass)){
			throw new Exception("舱位不能为空");
		}
		if(StringUtils.isBlank(paymentAmount)){
			throw new Exception("支付金额");
		}
		if(passengerInfos==null || passengerInfos.isEmpty()){
			throw new Exception("乘机人信息不能为空");
		}
		for (PassengerInfo passengerInfo : passengerInfos) {
			if(StringUtils.isBlank(passengerInfo.getPassenger())){
				throw new Exception("乘机人姓名不能为空");
			}
			if(StringUtils.isBlank(passengerInfo.getTicketNo())){
				throw new Exception("票号不能为空");
			}
			if(StringUtils.isBlank(passengerInfo.getCardId())){
				throw new Exception("证件号码不能为空");
			}
		}
	}
    /////////////////////////////////////////////get&&set/////////////////////////////////////////////
    public String getOutOrderNo() {
        return outOrderNo;
    }
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getAirwaysAccount() {
        return airwaysAccount;
    }
    public void setAirwaysAccount(String airwaysAccount) {
        this.airwaysAccount = airwaysAccount;
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
    public String getSalePrice() {
        return salePrice;
    }
    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }
    @XmlElement(name="passengerInfo")
    public List<PassengerInfo> getPassengerInfos() {
        return passengerInfos;
    }
    public void setPassengerInfos(List<PassengerInfo> passengerInfos) {
        this.passengerInfos = passengerInfos;
    }
    public String getPayment() {
        return payment;
    }
    public void setPayment(String payment) {
        this.payment = payment;
    }
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public String getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
    public String getBuyPaymentTransaction() {
        return buyPaymentTransaction;
    }
    public void setBuyPaymentTransaction(String buyPaymentTransaction) {
        this.buyPaymentTransaction = buyPaymentTransaction;
    }
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
}
