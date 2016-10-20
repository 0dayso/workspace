package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * 平台贴点Bean
 * 
 * @author zhangxiaoyu
 * @version [版本号, 2014-9-9]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class PayForProfigBean {
    
    /**
     * 规则ID
     */
    private String id;
    
    /**
     * 航空公司
     */
    private String arrCode;
    
    /**
     * 政策类型
     */
    private String policyType;
    
    /**
     * 出发城市
     */
    private String fromCity;
    
    /**
     * 抵达城市
     */
    private String toCity;
    
    /**
     * 舱位代码
     */
    private String cabinCode;
    
    /**
     * 贴点方式：0限制区间 ；1不限制区间
     */
    private String payType;
    
    /**
     * 是否限制返点范围 0限制 ；1不限制
     */
    private String ifRestrictRange;
    
    /**
     * 返点差额区间 fdces-fdcez
     */
    private String rateSection;
    
    /**
     * 单人最高返点
     */
    private String singleUpperLimit;
    
    /**
     * 规则最高返点限制
     */
    private String totalUpperLimit;
    
    /**
     * 已贴点金额
     */
    private Double useTatal;
    
    /**
     * 贴点明细 fds-fdz|tdfs|td|tq
     */
    private List<String> detaileds;
    
    /**
     * 是否指定供应商 0：指定；1：不指定
     */
    private String supplierLimitCode;
    
    /**
     * 指定的供应商区域编号，多个使用','分割，所有使用'---'
     */
    private String supplierLimitArea;
    
    /**
     * 指定的供应商等级编号，多个使用'/'分割，所有使用'---'
     */
    private String supplierLimitLevel;
    
    /**
     * 指定的供应商编号
     */
    private String supplierNo;
    
    /**
     * 实名匿名适用 0实名匿名都适用，1实名适用，2匿名适用
     */
    private String isRealNameMatch;
    
    private String payment;// 匹配贴点后绑定的支付方式代号，多个以/隔开；为空表示不限制
    
    private double zgtd;// 默认最高贴点
    
    /**
     * 按返点贴:CPS政策在贴点后比其它平台最优政策高几个点；可正可负，正表示高，负表示低
     * 采购净价贴:CPS政策在贴钱后比其它平台最优政策采购净价低多少钱；可正可负，正表示低，负表示高
     */
    private double fgtz;// 覆盖贴值
    
    /**
     * 支付方式最高返点 格式 支付方式：最高返点,支付方式：最高返点... (支持多个)
     */
    private String zffs_zgfd;
    /**
     * 支付方式覆盖返点 格式 支付方式：覆盖返点,支付方式：覆盖返点... (支持多个)
     */
    private String zffs_fgfd;
    
    private String bsyhc;// 不适用航程
    
    @XmlElement(name="defPasteP")
    public double getZgtd() {
        return this.zgtd;
    }
    
    public void setZgtd(double zgtd) {
        this.zgtd = zgtd;
    }
    
    @XmlElement(name="addPasteP")
    public double getFgtz() {
        return this.fgtz;
    }
    
    public void setFgtz(double fgtz) {
        this.fgtz = fgtz;
    }
    
    @XmlElement(name="payPasteP")
    public String getZffs_zgfd() {
        return this.zffs_zgfd;
    }
    
    public void setZffs_zgfd(String zffs_zgfd) {
        this.zffs_zgfd = zffs_zgfd;
    }
    
    public String getSupplierLimitCode() {
        return supplierLimitCode;
    }
    
    public void setSupplierLimitCode(String supplierLimitCode) {
        this.supplierLimitCode = supplierLimitCode;
    }
    
    public String getSupplierLimitArea() {
        return supplierLimitArea;
    }
    
    public void setSupplierLimitArea(String supplierLimitArea) {
        this.supplierLimitArea = supplierLimitArea;
    }
    
    public String getSupplierLimitLevel() {
        return supplierLimitLevel;
    }
    
    public void setSupplierLimitLevel(String supplierLimitLevel) {
        this.supplierLimitLevel = supplierLimitLevel;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
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
    
    public String getIfRestrictRange() {
        return ifRestrictRange;
    }
    
    public void setIfRestrictRange(String ifRestrictRange) {
        this.ifRestrictRange = ifRestrictRange;
    }
    
    public String getArrCode() {
        return arrCode;
    }
    
    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }
    
    public String getPolicyType() {
        return policyType;
    }
    
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
    
    public String getCabinCode() {
        return cabinCode;
    }
    
    public void setCabinCode(String cabinCode) {
        this.cabinCode = cabinCode;
    }
    
    public String getPayType() {
        return payType;
    }
    
    public void setPayType(String payType) {
        this.payType = payType;
    }
    
    public String getRateSection() {
        return rateSection;
    }
    
    public void setRateSection(String rateSection) {
        this.rateSection = rateSection;
    }
    
    public String getSingleUpperLimit() {
        return singleUpperLimit;
    }
    
    public void setSingleUpperLimit(String singleUpperLimit) {
        this.singleUpperLimit = singleUpperLimit;
    }
    
    public String getTotalUpperLimit() {
        return totalUpperLimit;
    }
    
    public void setTotalUpperLimit(String totalUpperLimit) {
        this.totalUpperLimit = totalUpperLimit;
    }
    
    public Double getUseTatal() {
        return useTatal;
    }
    
    public void setUseTatal(Double useTatal) {
        this.useTatal = useTatal;
    }
    
    @XmlElementWrapper(name = "detaileds")
    @XmlElement(name = "detailed")
    public List<String> getDetaileds() {
        return detaileds;
    }
    
    public void setDetaileds(List<String> detaileds) {
        this.detaileds = detaileds;
    }
    
    public String getSupplierNo() {
        return supplierNo;
    }
    
    public void setSupplierNo(String supplierNo) {
        this.supplierNo = supplierNo;
    }
    
    public String getIsRealNameMatch() {
        return isRealNameMatch;
    }
    
    public void setIsRealNameMatch(String isRealNameMatch) {
        this.isRealNameMatch = isRealNameMatch;
    }
    
    public String getPayment() {
        return this.payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }
    
    @XmlElement(name="notAppLeg")
    public String getBsyhc() {
        return this.bsyhc;
    }
    
    public void setBsyhc(String bsyhc) {
        this.bsyhc = bsyhc;
    }
    @XmlElement(name="payAddPasteP")
	public String getZffs_fgfd() {
		return zffs_fgfd;
	}

	public void setZffs_fgfd(String zffs_fgfd) {
		this.zffs_fgfd = zffs_fgfd;
	}
    
}
