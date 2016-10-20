package cn.vetech.vedsb.platpolicy.jzcp.b2c.client.request;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.jzcp.b2c.client.VetpsPurchasRequest;

@XmlRootElement(name = "request")
public class OrderStatusRequest extends VetpsPurchasRequest {
    
    /**
     * 8000系统订单编码
     */
    private String asmsddbh;
    
    public String getAsmsddbh() {
        return asmsddbh;
    }
    
    public void setAsmsddbh(String asmsddbh) {
        this.asmsddbh = asmsddbh;
    }
    
}
