package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Passenger;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasRequest;

@XmlRootElement(name="request")
public class B2cOrderRequest extends VetpsPurchasRequest{
	private String outOrderNo;//外部订单编号
	private String source;//1-官网 2-淘宝 3-携程
	private String loginType;//登录方式
	private String airwaysAccount;//航司账号
	private String airwaysPwd;//航司密码（加密）
	private String airways;//航空公司
	private String flightNo;//航班号
	private String fromCity;//起飞城市
	private String toCity;//到达城市
	private String fromDatetime;//起飞时间 
	private String toDatetime;//到达时间
	private String seatClass;//舱位
	private String salePrice;//销售价格
	private String payment;//支付平台
	private String payAccount;//支付账号
	private byte[] payInfo;//支付信息(加密)
	private String autopay;//自动支付 0 自动1手动
	private String linker;//联系人
	private String linkMobile;//联系人电话
	private String linkMail;//联系人邮箱
	private String businessIp;//商户ip+端口号(用于回填票号)
	private String pnr;
	
	private String compid;
	private String user2;
	private String user1;
	private String pwd1;
	private String url1;
	private String cplxSetting;//客户设置的对应航司的产品类型
	private String cplx;
	private String userphone;//操作员手机号，便于接受短信
	private String coupon;//优惠代码
	private List<Passenger> passengers;
	private String hkgspnr;//大编码
	
	public String getHkgspnr() {
		return hkgspnr;
	}
	public void setHkgspnr(String hkgspnr) {
		this.hkgspnr = hkgspnr;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}
	
	public String getOutOrderNo() {
		return outOrderNo;
	}
	public void setOutOrderNo(String outOrderNo) {
		this.outOrderNo = outOrderNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAirwaysAccount() {
		return airwaysAccount;
	}
	public void setAirwaysAccount(String airwaysAccount) {
		this.airwaysAccount = airwaysAccount;
	}
	public String getAirwaysPwd() {
		return airwaysPwd;
	}
	public void setAirwaysPwd(String airwaysPwd) {
		this.airwaysPwd = airwaysPwd;
	}
	public String getAirways() {
		return airways;
	}
	public void setAirways(String airways) {
		this.airways = airways;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getFromCity() {
		return fromCity;
	}
	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	public String getToCity() {
		return toCity;
	}
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	public String getFromDatetime() {
		return fromDatetime;
	}
	public void setFromDatetime(String fromDatetime) {
		this.fromDatetime = fromDatetime;
	}
	public String getToDatetime() {
		return toDatetime;
	}
	public void setToDatetime(String toDatetime) {
		this.toDatetime = toDatetime;
	}
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public String getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public byte[] getPayInfo() {
		return payInfo;
	}
	public void setPayInfo(byte[] payInfo) {
		this.payInfo = payInfo;
	}
	public String getAutopay() {
		return autopay;
	}
	public void setAutopay(String autopay) {
		this.autopay = autopay;
	}
	public String getLinker() {
		return linker;
	}
	public void setLinker(String linker) {
		this.linker = linker;
	}
	public String getLinkMobile() {
		return linkMobile;
	}
	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}
	@XmlElementWrapper(name="passengerInfos")
	@XmlElement(name="passengerInfo")
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public String getLinkMail() {
		return linkMail;
	}
	public void setLinkMail(String linkMail) {
		this.linkMail = linkMail;
	}
	public String getBusinessIp() {
		return businessIp;
	}
	public void setBusinessIp(String businessIp) {
		this.businessIp = businessIp;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
    public String getPnr() {
        return pnr;
    }
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
    public String getCompid() {
        return compid;
    }
    public void setCompid(String compid) {
        this.compid = compid;
    }
    public String getUser2() {
        return user2;
    }
    public void setUser2(String user2) {
        this.user2 = user2;
    }
    public String getUser1() {
        return user1;
    }
    public void setUser1(String user1) {
        this.user1 = user1;
    }
    public String getPwd1() {
        return pwd1;
    }
    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }
    public String getUrl1() {
        return url1;
    }
    public void setUrl1(String url1) {
        this.url1 = url1;
    }
    public String getCplxSetting() {
        return cplxSetting;
    }
    public void setCplxSetting(String cplxSetting) {
        this.cplxSetting = cplxSetting;
    }
    public String getCplx() {
        return cplx;
    }
    public void setCplx(String cplx) {
        this.cplx = cplx;
    }
}
