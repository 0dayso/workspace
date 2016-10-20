package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasRequest;

@XmlRootElement(name="request")
public class SegmentRequest extends VetpsPurchasRequest{
    private String aircom;                   //航空公司
    private String deptCity;                 //出发城市
    private String arrCity;                  //到达城市
    private String flightDate;               //起飞日期
    private String flag;                     //1表示获取航空公司跳转地址
    private String flightNo;                 //航班号
    private String ddbh;                     //订单编号
    
    /////////////////////////////////////get&&set/////////////////////////////////////
    public String getAircom() {
        return aircom;
    }
    public void setAircom(String aircom) {
        this.aircom = aircom;
    }
    public String getDeptCity() {
        return deptCity;
    }
    public void setDeptCity(String deptCity) {
        this.deptCity = deptCity;
    }
    public String getArrCity() {
        return arrCity;
    }
    public void setArrCity(String arrCity) {
        this.arrCity = arrCity;
    }
    public String getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
}
