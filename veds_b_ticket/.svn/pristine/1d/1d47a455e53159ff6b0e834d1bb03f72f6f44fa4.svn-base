package cn.vetech.vedsb.platpolicy.cps.response.ticket;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.utils.XmlUtils;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;


/**
 * 根据PNR内容获取实时政策接口输出Bean
 * @author  gengxy
 * @version  [版本号, Apr 25, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@XmlRootElement(name = "response")
public class GetRealtimePolicyRes extends CpsResponse {
    
    private List<RealtimePolicy> policyList;//政策集合
    
    private List<MarketSerBean> serviceProduct;//服务类 精准营销产品
    
    private List<PayForProfigBean> tdList;//贴点规则集合

    @XmlElementWrapper(name = "policys")
    @XmlElement(name="policy")
    public List<RealtimePolicy> getPolicyList() {
        return this.policyList;
    }
    
    public void setPolicyList(List<RealtimePolicy> policyList) {
        this.policyList = policyList;
    }
    
    @XmlElementWrapper(name="payProfigs")
    @XmlElement(name="payProfig")
    public List<PayForProfigBean> getTdList() {
        return this.tdList;
    }
    
    public void setTdList(List<PayForProfigBean> tdList) {
        this.tdList = tdList;
    }
    
    @XmlElementWrapper(name="serviceProducts")
    @XmlElement(name="serviceProduct")
    public List<MarketSerBean> getServiceProduct() {
        return this.serviceProduct;
    }

    
    public void setServiceProduct(List<MarketSerBean> serviceProduct) {
        this.serviceProduct = serviceProduct;
    }
    public String toString() {
		return XmlUtils.toXmlWithHead(this, "UTF-8");
	}
}
