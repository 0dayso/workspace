package cn.vetech.vedsb.platpolicy.jzcp.b2b.client;

import java.io.Serializable;

public class VetpsPurchasB2bRequest implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -584345685160165922L;

	private String businessNo;//商户编号
    
    private String userid;//操作人编号
    
    private String operateTime;//操作时间
    
    private String systemId;//系统id
    
    private String sign;//签名信息（加密）systemid+account+operator+operateTime+key
    
    private String service;//服务名称
    
    public String getBusinessNo() {
        return businessNo;
    }
    
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }
    
    public String getOperateTime() {
        return operateTime;
    }
    
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
    
    public String getSystemId() {
        return systemId;
    }
    
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
    
    public String getSign() {
        return sign;
    }
    
    public void setSign(String sign) {
        this.sign = sign;
    }
    

    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getService() {
        return service;
    }
    
    public void setService(String service) {
        this.service = service;
    }
}
