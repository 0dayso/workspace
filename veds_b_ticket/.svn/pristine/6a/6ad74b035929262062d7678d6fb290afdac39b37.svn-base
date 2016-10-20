package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import javax.xml.bind.annotation.XmlElement;


/**
 * 根据PNR内容获取实时政策输出--政策Bean
 * @author  gengxy
 * @version  [版本号, Apr 25, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RealtimePolicy {
    
    private String id;// 政策ID【以PPOINT@PAY开头表示匹配上绑定支付方式的贴点规则】
    
    private String cplx;// 产品类型productType 1：普通产品、2：特价产品、3：特殊产品
    
    private Integer tszclx;// 特殊政策类型policyType 1：K位、2：免票、3：特殊
    
    private String zcmc;// 政策名称 policyName
    
    private Double pj;// 票价ticketPrice 特价特殊时返回，单位为元
    
    private Double zk;// 折扣discount 特价特殊时返回示例：80
    
    private Double fd;// 返点rebate 0.065000 ----
    
    private Double lq;// 留钱deductionAmount
    
    private Double xsfwf;// 销售服务费
    
    private String sfqyzd;// 是否启用追点 0不启用 1启用
    
    private Double zgfd;// 最高返点
    
    private Double zdfd;// 最低返点，例如：0.065000
    
    private Double fgfd;// 覆盖返点，例如：0.065000
    
    private String ifEnjoylowPrice;// 低票面是否享受 0不享受 1享受
    
    private String sfhbmcp;// 是否换编码ifChangePnr 是否换编码出票：1是，0否
    
    private String sfzdcp;// 是否自动出票ifAutoEtdz 1是，0否
    
    private String office;// officeId
    
    private String pzlx;// 出票类型ticketType 多个以“/”分隔，如BSPET/BPET
    
    private String sfxzbmd;// 是否限制白名单客户ifLimitWhiteList 1是，0否，为空时表示不限制
    
    private String xzbmdlx;//限制白名单类型，此属性和sfxzbmd联合使用 为1表示100%白名单验证 为2表示ASMS白名单验证 0或者空等同于不限制白名单
    
    private String zjlxxz;// 证件类型限制ifLimitCardType 0支持所有证件，1只支持身份证，为空时表示支持所有证件类型
    
    private String pmxsjxf;// 票面销售价是否相符ifAccordWithPar 1是，0否，为空时表示相符
    
    private String jgxx;// 价格选项priceOption 特价或特殊政策时才返回。1价格以PAT为准，0价格以政策为准
    
    private Integer dj_type;// 登机类型boardingType 免票产品才返回，0候补登机，1正常登机
    
    private String tgqgd;// 退改签规定gaiqianRetirement 特价或特殊产品时才返回
    
    private String tgqgdjc;// 退改签规定简称
    
    private String wbbz;// 销售说明salesRule

    private String fb_shbh;//发布商户编号
    
    private String fb_shmc;// 供应商名称saleBusinessName 实名时返回供应商名称，匿名时返回匿名

    private String fb_shdj;//发布商户供应等级
    
    private String fb_shqy;//发布商户供应区域
    
    private String bzjh;// 保障计划protection 保证计划编号，多个以“|”分隔
    
    private String bzjhMc;// 保障计划名称，多个以“|”分隔
    
    private String bzjhIcon;// 保障计划地址，多个以“|”分隔
    
    private String etdzDatetime;// 供应商出票时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String refundDatetime;// 供应商退票时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String voidDatetime;// 供应商废票时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String changeDatetime;// 供应商改签时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String matbackNote;//垫退说明
    
    private String otherParam;//DES加密信息，包括：贴点信息；系统参数编号，多个参数以“|”隔开，参数编号和参数值以“:”表示；
    //格式：贴点规则ID_贴点_贴钱;参数编号:参数编号值1|参数编号值2
    
    private String payment;//匹配贴点后绑定的支付方式代号，多个以/隔开；为空表示不限制 
    
    private String defPayMethod;//采购默认代扣方式   支付代号|支付名称 多个逗号隔开
    
    private String payCompMsg;//支付公司代号|支付公司名称|图片地址  多个逗号隔开
    
    private String receiveMethod;//供应收款方式
    
    private String passengerType;//政策适用乘机人类型 1成人 2儿童 3成人和儿童【CPS政策报表需要】
    
    private String rewRates;//政策原始返点【CPS政策报表需要】
    
    private String tgqgdStr;//退改签规定字符串【退票规则编号/改签规则编号/退改签基准(1按票面 2按全价F/C/Y 3按舱位公布运价)/计算退票时对应的舱位】
    
    private String programId;//方案ID【用于精准营销/新版特价100%白名单验证使用】

    @XmlElement(name = "defPayMethod")
    public String getDefPayMethod() {
		return defPayMethod;
	}

	public void setDefPayMethod(String defPayMethod) {
		this.defPayMethod = defPayMethod;
	}

	@XmlElement(name="policyId")
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement(name="productType")
    public String getCplx() {
        return this.cplx;
    }
    
    public void setCplx(String cplx) {
        this.cplx = cplx;
    }
    
    @XmlElement(name="policyType")
    public Integer getTszclx() {
        return this.tszclx;
    }
    
    public void setTszclx(Integer tszclx) {
        this.tszclx = tszclx;
    }
    
    @XmlElement(name="policyName")
    public String getZcmc() {
        return this.zcmc;
    }
    
    public void setZcmc(String zcmc) {
        this.zcmc = zcmc;
    }
    
    @XmlElement(name="ticketPrice")
    public Double getPj() {
        return this.pj;
    }
    
    public void setPj(Double pj) {
        this.pj = pj;
    }
    
    @XmlElement(name="discount")
    public Double getZk() {
        return this.zk;
    }
    
    public void setZk(Double zk) {
        this.zk = zk;
    }
    
    @XmlElement(name="rebate")
    public Double getFd() {
        return this.fd;
    }
    
    public void setFd(Double fd) {
        this.fd = fd;
    }
    
    @XmlElement(name="deductionAmount")
    public Double getLq() {
        return this.lq;
    }
    
    public void setLq(Double lq) {
        this.lq = lq;
    }
    
    @XmlElement(name="serviceFee")
    public Double getXsfwf() {
        return this.xsfwf;
    }
    
    public void setXsfwf(Double xsfwf) {
        this.xsfwf = xsfwf;
    }
    
    @XmlElement(name="ifChaseRebate")
    public String getSfqyzd() {
        return this.sfqyzd;
    }
    
    public void setSfqyzd(String sfqyzd) {
        this.sfqyzd = sfqyzd;
    }
    
    @XmlElement(name="highRebate")
    public Double getZgfd() {
        return this.zgfd;
    }
    
    public void setZgfd(Double zgfd) {
        this.zgfd = zgfd;
    }
    
    @XmlElement(name="lowRebate")
    public Double getZdfd() {
        return this.zdfd;
    }
    
    public void setZdfd(Double zdfd) {
        this.zdfd = zdfd;
    }
    
    @XmlElement(name="changeRebate")
    public Double getFgfd() {
        return this.fgfd;
    }
    
    public void setFgfd(Double fgfd) {
        this.fgfd = fgfd;
    }
    
    public String getIfEnjoylowPrice() {
        return this.ifEnjoylowPrice;
    }
    
    public void setIfEnjoylowPrice(String ifEnjoylowPrice) {
        this.ifEnjoylowPrice = ifEnjoylowPrice;
    }
    
    @XmlElement(name="ifChangePnr")
    public String getSfhbmcp() {
        return this.sfhbmcp;
    }
    
    public void setSfhbmcp(String sfhbmcp) {
        this.sfhbmcp = sfhbmcp;
    }
    
    @XmlElement(name="ifAutoEtdz")
    public String getSfzdcp() {
        return this.sfzdcp;
    }
    
    public void setSfzdcp(String sfzdcp) {
        this.sfzdcp = sfzdcp;
    }
    
    @XmlElement(name="officeId")
    public String getOffice() {
        return this.office;
    }
    
    public void setOffice(String office) {
        this.office = office;
    }
    
    @XmlElement(name="ticketType")
    public String getPzlx() {
        return this.pzlx;
    }
    
    public void setPzlx(String pzlx) {
        this.pzlx = pzlx;
    }
    
    @XmlElement(name="ifLimitWhiteList")
    public String getSfxzbmd() {
        return this.sfxzbmd;
    }
    
    public void setSfxzbmd(String sfxzbmd) {
        this.sfxzbmd = sfxzbmd;
    }
   
    @XmlElement(name="ifLimitCardType")
    public String getZjlxxz() {
        return this.zjlxxz;
    }
    
    public void setZjlxxz(String zjlxxz) {
        this.zjlxxz = zjlxxz;
    }
    
    @XmlElement(name="ifAccordWithPar")
    public String getPmxsjxf() {
        return this.pmxsjxf;
    }
    
    public void setPmxsjxf(String pmxsjxf) {
        this.pmxsjxf = pmxsjxf;
    }
    
    @XmlElement(name="priceOption")
    public String getJgxx() {
        return this.jgxx;
    }
    
    public void setJgxx(String jgxx) {
        this.jgxx = jgxx;
    }
    
    @XmlElement(name="boardingType")
    public Integer getDj_type() {
        return this.dj_type;
    }
    
    public void setDj_type(Integer dj_type) {
        this.dj_type = dj_type;
    }
    
    @XmlElement(name="note")
    public String getTgqgd() {
        return this.tgqgd;
    }
    
    public void setTgqgd(String tgqgd) {
        this.tgqgd = tgqgd;
    }
    
    @XmlElement(name="noteSimp")
    public String getTgqgdjc() {
        return this.tgqgdjc;
    }
    
    public void setTgqgdjc(String tgqgdjc) {
        this.tgqgdjc = tgqgdjc;
    }
    
    @XmlElement(name="salesRule")
    public String getWbbz() {
        return this.wbbz;
    }
    
    public void setWbbz(String wbbz) {
        this.wbbz = wbbz;
    }
    
    @XmlElement(name="saleBusinessName")
    public String getFb_shmc() {
        return this.fb_shmc;
    }
    
    public void setFb_shmc(String fb_shmc) {
        this.fb_shmc = fb_shmc;
    }
    
    @XmlElement(name="protection")
    public String getBzjh() {
        return this.bzjh;
    }
    
    public void setBzjh(String bzjh) {
        this.bzjh = bzjh;
    }
    
    @XmlElement(name="protectionName")
    public String getBzjhMc() {
        return this.bzjhMc;
    }
    
    public void setBzjhMc(String bzjhMc) {
        this.bzjhMc = bzjhMc;
    }
    
    @XmlElement(name="protectionIcon")
    public String getBzjhIcon() {
        return this.bzjhIcon;
    }
    
    public void setBzjhIcon(String bzjhIcon) {
        this.bzjhIcon = bzjhIcon;
    }
    
    @XmlElement(name="etdzDatetime")
    public String getEtdzDatetime() {
        return this.etdzDatetime;
    }
    
    public void setEtdzDatetime(String etdzDatetime) {
        this.etdzDatetime = etdzDatetime;
    }
    
    @XmlElement(name="refundDatetime")
    public String getRefundDatetime() {
        return this.refundDatetime;
    }
    
    public void setRefundDatetime(String refundDatetime) {
        this.refundDatetime = refundDatetime;
    }
    
    @XmlElement(name="voidDatetime")
    public String getVoidDatetime() {
        return this.voidDatetime;
    }
    
    public void setVoidDatetime(String voidDatetime) {
        this.voidDatetime = voidDatetime;
    }
    
    @XmlElement(name="changeDatetime")
    public String getChangeDatetime() {
        return this.changeDatetime;
    }
    
    public void setChangeDatetime(String changeDatetime) {
        this.changeDatetime = changeDatetime;
    }
    
    public String getOtherParam() {
        return this.otherParam;
    }
    
    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

    @XmlElement(name="saleBusinessNo")
    public String getFb_shbh() {
        return this.fb_shbh;
    }
    
    public void setFb_shbh(String fb_shbh) {
        this.fb_shbh = fb_shbh;
    }
    
    @XmlElement(name = "saleBusinessLevel")
    public String getFb_shdj() {
        return this.fb_shdj;
    }
    
    public void setFb_shdj(String fb_shdj) {
        this.fb_shdj = fb_shdj;
    }
    
    @XmlElement(name = "saleBusinessArea")
    public String getFb_shqy() {
        return this.fb_shqy;
    }
    
    public void setFb_shqy(String fb_shqy) {
        this.fb_shqy = fb_shqy;
    }
    
    /**
     * 政策类型进行转换
     * 1 普通政策 2 K位政策 3 免票政策 4 特殊政策 5 特价政策 6精准营销产品
     * @return [参数说明]
     */
    public String getZclx() {
        if ("1".equals(cplx)) {
            return "1";
        } else if ("2".equals(cplx)) {//老版特价
            return "5";
        } else if ("3".equals(cplx)) {// 特殊政策类型policyType 1：K位、2：免票、3：特殊
            if (tszclx == 1) {
                return "2";
            } else if (tszclx == 2) {
                return "3";
            } else if (tszclx == 3) {
                return "4";
            }
        }else if("4".equals(cplx)){//精准营销
            return "6";
        }else if("5".equals(cplx)){//新版特价
            return "5";
        }
        
        return "";
    }

    
    public String getPayment() {
        return this.payment;
    }

    
    public void setPayment(String payment) {
        this.payment = payment;
    }

	public String getPayCompMsg() {
		return payCompMsg;
	}

	public void setPayCompMsg(String payCompMsg) {
		this.payCompMsg = payCompMsg;
	}

	@XmlElement(name = "matbackNote")
    public String getMatbackNote() {
        return this.matbackNote;
    }
    
    public void setMatbackNote(String matbackNote) {
        this.matbackNote = matbackNote;
    }
    
    public String getReceiveMethod() {
        return this.receiveMethod;
    }
    
    public void setReceiveMethod(String receiveMethod) {
        this.receiveMethod = receiveMethod;
    }

    @XmlElement(name="limitWhiteLType")
    public String getXzbmdlx() {
        return this.xzbmdlx;
    }
    
    public void setXzbmdlx(String xzbmdlx) {
        this.xzbmdlx = xzbmdlx;
    }
    
    public String getPassengerType() {
        return this.passengerType;
    }
    
    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }
    
    public String getRewRates() {
        return this.rewRates;
    }
    
    public void setRewRates(String rewRates) {
        this.rewRates = rewRates;
    }

    @XmlElement(name = "noteStr")
    public String getTgqgdStr() {
        return this.tgqgdStr;
    }
    
    public void setTgqgdStr(String tgqgdStr) {
        this.tgqgdStr = tgqgdStr;
    }

    
    public String getProgramId() {
        return this.programId;
    }

    
    public void setProgramId(String programId) {
        this.programId = programId;
    }
}
