package org.vetech.core.business.pid.pidbean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * 机票航班查询--舱位信息Bean
 * 
 * @author gengxianyan
 * @version [版本号, Dec 24, 2012]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class TicketCabin implements Serializable {
    
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1546852735629139925L;
    
    /**
     * 舱位
     */
    private String cabin; //
    
    /**
     * 舱位名称
     */
    private String cabName; //
    
    /**
     * 舱位类型 0 经济舱 1 单程特价舱 2 往返特价舱 3 联程特价舱 4 单程往返特价舱 5 单程联程特价舱 6 头等舱 7 公务舱 8
     * 往返联程特价舱 9 非销售舱位
     */
    private String cabType;
    
    /**
     * 是否公布运价 1公布运价 0非公布运价
     */
    private String isPubTar; //
    
    /**
     * 座位数
     */
    private String seatNum; //
    
    /**
     * 折扣
     */
    private double discount; //
    
    /**
     * 返点
     */
    private String rewRates; //
    
    /**
     * 留钱
     */
    private String stayMoney; //
    
    /**
     * 利润
     */
    private String profit; //
    
    /**
     * 账单价
     */
    private int billPrice; //
    
    /**
     * 采购净价
     */
    private double buyPrice; //
    
    /**
     * 多段用账单价
     */
    private int morebillPrice; //
    
    /**
     * 多段用采购净价
     */
    private double morebuyPrice; //
    
    /**
     * 支付金额[1个成人，包括机建税费]
     */
    private double payPrice; //
    
    /**
     * 政策ID
     */
    private String policyId; //
    
    /**
     * 政策类型 TJZC/特价 PTZC/普通 TSZC_QT/特殊 TSZC_KW/K位 TSZC_MP/免票 NFDTJ/NFD特价   JZYXCP/精准营销价格类产品
     */
    private String policyType; //
    
    /**
     * 是否其他三方平台数据 不为空为“是” 为空表示本地
     */
    private String isOtherPlat;// 
    
    /**
     * 退改签规定
     */
    private String note; //
    
    /**
     * 备注【对外销售说明】
     */
    private String forRemark; //
    
    /**
     * 票证类型
     */
    private String ticketType; //
    
    /**
     * 价格选项 1价格以PAT为准，2价格以政策为准,默认为1
     */
    private String priceRule; //
    
    /**
     * 是否限制白名单 0不限制 1限制
     */
    private String sfxzbmd;
    
    private String xzbmdlx;//限制白名单类型，此属性和sfxzbmd联合使用 为1表示100%白名单验证 为2表示ASMS白名单验证 0或者空等同于不限制白名单
    
    /**
     * 证件类型限制 0支持所有证件,1只支持身份证
     */
    private String zjlxxz;
    
    /**
     * 产品成交次数
     */
    private String dealCount; //
    
    /**
     * 适用的乘机人类型 1成人 2儿童 3成人和儿童
     */
    private String appPassengerType; //
    
    /**
     * 是否换编码出票
     */
    private String exchangePnr;//
    
    /**
     * 票面销售价是否相符
     */
    private String billSaleMatch;//
    
    /**
     * 供应商是否被采购商加入黑名单 true为是，false为否
     */
    private boolean inBlackList = false;//
    
    /**
     * 保证计划编号，多个以“/”隔开
     */
    private String assurePlan;//
    
    /**
     * 保证计划名称，多个以“/”隔开
     */
    private String assurePlanName;//
    
    /**
     * 机票产品Office号
     */
    private String office;
    
    /**
     * 成功交易率[C站和携程代购]
     */
    private String successRate;//
    
    /**
     * 是否自有产品
     */
    private String selfProduct;//
    
    /**
     * 是否匿名 不为空表示匿名 为空表示实名
     */
    private String isAnonymous;//
    
    /**
     * 供应商留点(小数点后4位)
     */
    private double rewRates_apply;//
    
    /**
     * 供应商留钱
     */
    private double stayMoney_apply;//
    
    /**
     * 平台留点
     */
    private double rewRates_plat;//
    
    /**
     * 平台留钱
     */
    private double stayMoney_plat;//
    
    /**
     * 平台贴点
     */
    private double payRates_plat;
    
    /**
     * 平台贴钱
     */
    private double payMoney_plat;
    
    /**
     * 贴点规则ID
     */
    private String payRates_id;
    
    /**
     * 销售服务费
     */
    private double xsfwf;
    
    /**
     * 剩余座位数
     */
    private String remainSeatNum; //
    
    /**
     * 总座位数
     */
    private String totalSeatNum; //
    
    /**
     * 团编
     */
    private String tpnr; //
    
    /**
     * 退改签规定分类
     */
    private String tgqgdfl; // 0,可以退改签 1,退改费用高 2,不可退改签
    
    private boolean isnoPjSpecia = false;// false表示该舱位没有匹配上 多舱位特价，为 true 则
                                            // 不应该过滤掉该舱位
    
    private String ifEnjoylowPrice;// 低票面是否享受 0不享受 1享受
    
    /**
     * 2014.11.12 加入 价格信息加密验证字段
     */
    private String sortMap;//
    
    /**
     * 是否启用追点 1启用 0不启用
     */
    private String sfqyzd;//
    
    private double zgfd;
    
    private double fgfd;
    
    private double zdfd;
    
    private TicketCabin secondPriceCabin;// 相同政策相同舱位，采购价第二高的舱位。追点适用
    
    private String payment;// 匹配贴点后绑定的支付方式 多个以/隔开 空表示不限制
    
    private String dtsm;// 垫退说明
    
    private String dtsm_icon;// 垫退说明对应的图片地址
    
    private String isAutoConfirm;//是否自动确认 
    
    public String getDtsm() {
        return dtsm;
    }
    
    public void setDtsm(String dtsm) {
        this.dtsm = dtsm;
    }
    
    public String getDtsm_icon() {
        return dtsm_icon;
    }
    
    public void setDtsm_icon(String dtsm_icon) {
        this.dtsm_icon = dtsm_icon;
    }
    
    public TicketCabin getSecondPriceCabin() {
        return secondPriceCabin;
    }
    
    public void setSecondPriceCabin(TicketCabin secondPriceCabin) {
        this.secondPriceCabin = secondPriceCabin;
    }
    
    public double getZgfd() {
        return zgfd;
    }
    
    public void setZgfd(double zgfd) {
        this.zgfd = zgfd;
    }
    
    public double getFgfd() {
        return fgfd;
    }
    
    public void setFgfd(double fgfd) {
        this.fgfd = fgfd;
    }
    
    public double getZdfd() {
        return zdfd;
    }
    
    public void setZdfd(double zdfd) {
        this.zdfd = zdfd;
    }
    
    public String getSfqyzd() {
        return sfqyzd;
    }
    
    public void setSfqyzd(String sfqyzd) {
        this.sfqyzd = sfqyzd;
    }
    
    public boolean isIsnoPjSpecia() {
        return isnoPjSpecia;
    }
    
    public void setIsnoPjSpecia(boolean isnoPjSpecia) {
        this.isnoPjSpecia = isnoPjSpecia;
    }
    
    public String getTgqgdfl() {
        return tgqgdfl;
    }
    
    public void setTgqgdfl(String tgqgdfl) {
        this.tgqgdfl = tgqgdfl;
    }
    
    public String getCabin() {
        return cabin;
    }
    
    public void setCabin(String cabin) {
        this.cabin = cabin;
    }
    
    public String getCabName() {
        return cabName;
    }
    
    public void setCabName(String cabName) {
        this.cabName = cabName;
    }
    
    public String getCabType() {
        return cabType;
    }
    
    public void setCabType(String cabType) {
        this.cabType = cabType;
    }
    
    public String getIsPubTar() {
        return isPubTar;
    }
    
    public void setIsPubTar(String isPubTar) {
        this.isPubTar = isPubTar;
    }
    
    public String getSeatNum() {
        return seatNum;
    }
    
    /**
     * 获取座位数
     * 
     * @return [参数说明]
     */
    public String getSeatNumC() {
        if (StringUtils.isNotBlank(seatNum)) {
            if (!"123456789A".contains(seatNum)) {
                seatNum = "0";
            }
        } else {
            seatNum = "0";
        }
        return seatNum;
    }
    
    public void setSeatNum(String seatNum) {
        this.seatNum = seatNum;
    }
    
    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
    public String getRewRates() {
        return rewRates;
    }
    
    public void setRewRates(String rewRates) {
        this.rewRates = rewRates;
    }
    
    public String getStayMoney() {
        return stayMoney;
    }
    
    public void setStayMoney(String stayMoney) {
        this.stayMoney = stayMoney;
    }
    
    public String getProfit() {
        return profit;
    }
    
    public void setProfit(String profit) {
        this.profit = profit;
    }
    
    public int getBillPrice() {
        return billPrice;
    }
    
    public void setBillPrice(int billPrice) {
        this.billPrice = billPrice;
    }
    
    public double getBuyPrice() {
        return buyPrice;
    }
    
    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }
    
    public String getPolicyId() {
        return policyId;
    }
    
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }
    
    public String getPolicyType() {
        return policyType;
    }
    
    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
    
    public String getForRemark() {
        return forRemark;
    }
    
    public void setForRemark(String forRemark) {
        this.forRemark = forRemark;
    }
    
    public String getExchangePnr() {
        return exchangePnr;
    }
    
    public void setExchangePnr(String exchangePnr) {
        this.exchangePnr = exchangePnr;
    }
    
    public String getBillSaleMatch() {
        return billSaleMatch;
    }
    
    public void setBillSaleMatch(String billSaleMatch) {
        this.billSaleMatch = billSaleMatch;
    }
    
    public String getAssurePlan() {
        return assurePlan;
    }
    
    public void setAssurePlan(String assurePlan) {
        this.assurePlan = assurePlan;
    }
    
    public String getAssurePlanName() {
        return assurePlanName;
    }
    
    public void setAssurePlanName(String assurePlanName) {
        this.assurePlanName = assurePlanName;
    }
    
    public String getSuccessRate() {
        return successRate;
    }
    
    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }
    
    public String getSelfProduct() {
        return selfProduct;
    }
    
    public void setSelfProduct(String selfProduct) {
        this.selfProduct = selfProduct;
    }
    
    public String getRemainSeatNum() {
        return remainSeatNum;
    }
    
    public void setRemainSeatNum(String remainSeatNum) {
        this.remainSeatNum = remainSeatNum;
    }
    
    public String getTotalSeatNum() {
        return totalSeatNum;
    }
    
    public void setTotalSeatNum(String totalSeatNum) {
        this.totalSeatNum = totalSeatNum;
    }
    
    public String getTpnr() {
        return tpnr;
    }
    
    public void setTpnr(String tpnr) {
        this.tpnr = tpnr;
    }
    
    public double getPayRates_plat() {
        return payRates_plat;
    }
    
    public void setPayRates_plat(double payRates_plat) {
        this.payRates_plat = payRates_plat;
    }
    
    public double getPayMoney_plat() {
        return payMoney_plat;
    }
    
    public void setPayMoney_plat(double payMoney_plat) {
        this.payMoney_plat = payMoney_plat;
    }
    
    public String getPayRates_id() {
        return payRates_id;
    }
    
    public void setPayRates_id(String payRates_id) {
        this.payRates_id = payRates_id;
    }
    
    public double getRewRates_apply() {
        return rewRates_apply;
    }
    
    public void setRewRates_apply(double rewRates_apply) {
        this.rewRates_apply = rewRates_apply;
    }
    
    public double getStayMoney_apply() {
        return stayMoney_apply;
    }
    
    public void setStayMoney_apply(double stayMoney_apply) {
        this.stayMoney_apply = stayMoney_apply;
    }
    
    public double getRewRates_plat() {
        return rewRates_plat;
    }
    
    public void setRewRates_plat(double rewRates_plat) {
        this.rewRates_plat = rewRates_plat;
    }
    
    public double getStayMoney_plat() {
        return stayMoney_plat;
    }
    
    public void setStayMoney_plat(double stayMoney_plat) {
        this.stayMoney_plat = stayMoney_plat;
    }
    
    public double getPayPrice() {
        return payPrice;
    }
    
    public void setPayPrice(double payPrice) {
        this.payPrice = payPrice;
    }
    
    public String getTicketType() {
        return ticketType;
    }
    
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
    
    /**
     * @return the morebillPrice
     */
    public int getMorebillPrice() {
        return morebillPrice;
    }
    
    /**
     * @param morebillPrice the morebillPrice to set
     */
    public void setMorebillPrice(int morebillPrice) {
        this.morebillPrice = morebillPrice;
    }
    
    /**
     * @return the morebuyPrice
     */
    public double getMorebuyPrice() {
        return morebuyPrice;
    }
    
    /**
     * @param morebuyPrice the morebuyPrice to set
     */
    public void setMorebuyPrice(double morebuyPrice) {
        this.morebuyPrice = morebuyPrice;
    }
    
    public String getIsOtherPlat() {
        return isOtherPlat;
    }
    
    public void setIsOtherPlat(String isOtherPlat) {
        this.isOtherPlat = isOtherPlat;
    }
    
    /**
     * 实名匿名 为空表示实名 不为空表示匿名
     * 
     * @return 实名匿名
     */
    public String getIsAnonymous() {
        if ("0".equals(isAnonymous) || "2".equals(isAnonymous)) {
            isAnonymous = "2";
        } else {
            isAnonymous = "";
        }
        return isAnonymous;
    }
    
    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }
    
    public String getAppPassengerType() {
        return appPassengerType;
    }
    
    public void setAppPassengerType(String appPassengerType) {
        this.appPassengerType = appPassengerType;
    }
    
    public String getPriceRule() {
        return priceRule;
    }
    
    public void setPriceRule(String priceRule) {
        this.priceRule = priceRule;
    }
    
    public boolean isInBlackList() {
        return inBlackList;
    }
    
    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }
    
    /**
     * 获取成交次数
     * 
     * @return [参数说明]
     */
    public String getDealCount() {
        if (StringUtils.isEmpty(dealCount)) {
            return "0";
        }
        return dealCount;
    }
    
    public void setDealCount(String dealCount) {
        this.dealCount = dealCount;
    }
    
    public String getSfxzbmd() {
        return sfxzbmd;
    }
    
    public void setSfxzbmd(String sfxzbmd) {
        this.sfxzbmd = sfxzbmd;
    }
    
    public String getZjlxxz() {
        return zjlxxz;
    }
    
    public void setZjlxxz(String zjlxxz) {
        this.zjlxxz = zjlxxz;
    }
    
    public String getOffice() {
        return office;
    }
    
    public void setOffice(String office) {
        this.office = office;
    }
    
    /**
     * 判断单程是否需要进行PAT
     * 
     * @return [参数说明]
     */
    public boolean getSingleIsNeedPat() {
        
        boolean isNeedPat = true;
        
        if (this.getBillPrice() > 0)
            isNeedPat = false;
        
        if (isNeedPat && "0".equals(this.getSeatNumC()))
            isNeedPat = false;
        
        if (isNeedPat && !"0".equals(this.getIsPubTar()))
            isNeedPat = false;
        
        if (isNeedPat && !StringUtils.contains("145", this.getCabType()))
            isNeedPat = false;
        
        return isNeedPat;
        
    }
    
    public String getIfEnjoylowPrice() {
        return ifEnjoylowPrice;
    }
    
    public void setIfEnjoylowPrice(String ifEnjoylowPrice) {
        this.ifEnjoylowPrice = ifEnjoylowPrice;
    }
    
    public String getSortMap() {
        return sortMap;
    }
    
    public void setSortMap(String sortMap) {
        this.sortMap = sortMap;
    }
    
    public double getXsfwf() {
        return xsfwf;
    }
    
    public void setXsfwf(double xsfwf) {
        this.xsfwf = xsfwf;
    }
    
    public String getPayment() {
        return this.payment;
    }
    
    public void setPayment(String payment) {
        this.payment = payment;
    }

    
    public String getIsAutoConfirm() {
        return this.isAutoConfirm;
    }

    
    public void setIsAutoConfirm(String isAutoConfirm) {
        this.isAutoConfirm = isAutoConfirm;
    }

    public String getXzbmdlx() {
        return this.xzbmdlx;
    }

    
    public void setXzbmdlx(String xzbmdlx) {
        this.xzbmdlx = xzbmdlx;
    }
}
