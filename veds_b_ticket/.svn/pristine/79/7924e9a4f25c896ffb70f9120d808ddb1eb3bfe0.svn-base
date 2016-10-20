package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.response;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Cjr;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.bean.Order;
import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasResponse;

@XmlRootElement(name="response")
public class B2cOrderInfoResponse extends VetpsPurchasResponse{
    private Order order;
    private List<Cjr> cjrs;
    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    
    @XmlElementWrapper(name="passengerInfos")
    @XmlElement(name="passengerInfo")
    public List<Cjr> getCjrs() {
        return cjrs;
    }
    public void setCjrs(List<Cjr> cjrs) {
        this.cjrs = cjrs;
    }
}
