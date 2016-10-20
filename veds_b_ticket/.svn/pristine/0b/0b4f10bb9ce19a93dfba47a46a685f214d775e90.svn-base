package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.StringUtils;


/**
 * 政策实时接口--政策Bean
 * 
 * @author shenjianxin
 * @version [版本号, 2013-9-13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PolicyBean {
    
    private String id;// 政策ID
    
    private String cplx;// 产品类型productType 1：普通产品、2：特价产品、3：特殊产品
    
    private Integer tszclx;// 特殊政策类型policyType 1：K位、2：免票、3：特殊
    
    private String hclx;// 航程类型travelType 1单程，2往返，3联程，4缺口
    
    private String cjrlx;// 乘机人类型passengerType 1成人，2儿童，3成人和儿童
    
    private String hkgs;// 航空公司airways
    
    private String zcmc;// 政策名称 policyName
    
    private String dyc_cfrqs;// 乘机日始flightDateBegin YYYY-MM-DD
    
    private String dyc_cfrqz;// 乘机日止flightDateEnd YYYY-MM-DD
    
    private String cfcity;// 出发城市fromCity 三字码集合，---表示所有，多个使用/分隔，例WUH/PEK
    
    private String ddcity;// 到达城市toCity 三字码集合，---表示所有，多个使用/分隔，例WUH/PEK
    
    private String zzcity;// 中转城市transitCity 联程和缺口程时返回，三字码集合，---表示所有，多个使用/分隔，例WUH/PEK
    
    private String hc;//适用航程 多个/隔开【目前用于精准营销/新版特价】
    
    private String bsyhc;// 不适用航程 多个/隔开
    
    private String dyc_hbsfsy;// 航班号是否适用ifSuitableForFlight 1适用 0不适用
    
    private String dyc_hbh;// 适用航班号forFlightNo ---表示所有，多个使用/分隔，例CA1234/CA2345
    
    private String dyc_hbsfsy_gxhb;// 共享航班是否适用ifSuitableFoShareFlight 1适用  0不适用，为空时表示不适用
    
    private Double dyc_zjzk;// 第一程直减折扣
    
    private Double dec_zjzk;// 第二程直减折扣
    
    private String dyc_pjzkxz_lx;// 折扣票价限制类型limitType 0不限1票价2折扣，为空时表示不限
    
    private Double dyc_pjzkxz_sx;// 折扣/票价限制范围始limitBegin 折扣/票价限制上限如：900
    
    private Double dyc_pjzkxz_xx;// 折扣/票价限制范围止limitEnd 折扣/票价折扣限制下限如：1000
    
    private String dyc_sycw;// 舱位seatClass 多个用/分隔，如Y/B/M/M1
    
    private String dec_cfrqs;// 返程乘机日始backFlightDateBegin YYYY-MM-DD
    
    private String dec_cfrqz;// 返程乘机日止backFlightDateEnd YYYY-MM-DD
    
    private String dec_hbsfsy;// 返程航班号是否适用ifSuitableForBackFlight 1适用 0不适用
    
    private String dec_hbh;// 返程适用航班号backForFlightNo ---表示所有，多个使用/分隔，例CA1234/CA2345
    
    private String dec_hbsfsy_gxhb;// 返程共享航班是否适用ifSuitableForBackShareFlight 1适用 0不适用，为空时表示不适用
    
    private String dec_pjzkxz_lx;// 折扣票价限制类型backLimitType 0不限1票价2折扣，为空时表示不限
    
    private Double dec_pjzkxz_sx;// 折扣/票价限制范围始backLimitBegin 折扣/票价限制上限如：900
    
    private Double dec_pjzkxz_xx;// 折扣/票价限制范围止backLimitEnd 折扣/票价折扣限制下限如：1000
    
    private String dec_sycw;// 返程舱位backSeatClass 多个用/分隔，如Y/B/M/M1 ???
    
    private Double pj;// 票价ticketPrice 特价特殊时返回，单位为元
    
    private Double cgj;// 采购价 特价特殊时返回，单位为元
    
    private Double zk;// 折扣discount 特价特殊时返回示例：80
    
    private Double fd;// 返点rebate 0.065000
    
    private Double lq;// 留钱deductionAmount
    
    private Double xsfwf;// 销售服务费
    
    private String sfqyzd;// 是否启用追点 0否 1是
    
    private Double zdfd;// 最低返点
    
    private Double zgfd;// 最高返点
    
    private Double fgfd;// 覆盖返点
    
    private String sfhbmcp;// 是否换编码ifChangePnr 是否换编码出票：1是，0否
    
    private String sfxyh;// 三方协议号protocolNum
    
    private String sfzdcp;// 是否自动出票ifAutoEtdz 1是，0否
    
    private String office;// officeId
    
    private String pzlx;// 出票类型ticketType 多个以“/”分隔，如BSPET/BPET
    
    private String sfxzbmd;// 是否限制白名单客户ifLimitWhiteList 1是，0否，为空时表示不限制
    
    private String xzbmdlx;//限制白名单类型，此属性和sfxzbmd联合使用 为1表示100%白名单验证 为2表示ASMS白名单验证 0或者空等同于不限制白名单
    
    private String zjlxxz;// 证件类型限制ifLimitCardType 0支持所有证件，1只支持身份证，为空时表示支持所有证件类型
    
    private String pmxsjxf;// 票面销售价是否相符ifAccordWithPar 1是，0否，为空时表示相符
    
    private String jgxx;// 价格选项priceOption 特价或特殊政策时才返回。1价格以PAT为准，0价格以政策为准
    
    private String k_type;// K位类型kType 特殊产品中K位政策才返回，1：见舱K，2：K低点折扣。见舱K（舱位在舱位节点中返回）
    
    private String kddzk;// K低点折扣kLowDiscount -5，表示K低5个点的舱位
    
    private Integer dj_type;// 登机类型boardingType 免票产品才返回，0候补登机，1正常登机
    
    private Integer sf_auto_pnr;// 是否自动生成PNR ifCreatePnr 特殊产品－特殊才返回，1，自动生成；0不自动生成
    
    private Integer sf_av;// 是否与舱位座位数有关ifRelatedToSeat 特殊产品－特殊才返回，1与座位数有关，0与座位数无关
    
    private Integer sf_zwsxz;// 有无座位数限制ifLimitSeat 特殊产品－特殊才返回，1有座位数限制，0无座位数限制
    
    private Integer sy_zws;// 剩座位数
    
    private Integer sf_auto_check;// 是否自动确认ifAutoCofirm 特殊产品－特殊才返回，1自动确认，0不自动确认
    
    private String tgqgd;// 退改签规定gaiqianRetirement 特价或特殊产品时才返回
    
    private String tgqfs;//退改签方式 1是自定义 2是以航司为准
    
    private String tgqgdjc;// 退改签规定简称
    
    private String wbbz;// 销售说明salesRule
    
    private String fb_shbh;// 发布商户编号
    
    private String fb_shmc;// 供应商名称saleBusinessName 实名时返回供应商名称，匿名时返回匿名 该字段用CPS来验证匿名，不要随便更改赋值，会造成业务失效
    
    private String bzjh;// 保障计划protection 保证计划编号，多个以“|”分隔
    
    private String bzjhMc;// 保障计划名称，多个以“|”分隔
    
    private String bzjhIcon;// 保障计划地址，多个以“|”分隔
    
    private String etdzDatetime;// 供应商出票时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String refundDatetime;// 供应商退票时间  工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String voidDatetime;// 供应商废票时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String changeDatetime;// 供应商改签时间 工作日时间和非工作日时间，格式：08:00-22:00/09:00-18:00
    
    private String ifBlacklist;// 是否黑名单 0不是黑名单，1是黑名单，2严格控制黑名单，返回为空时，表示不是黑名单
    
    private String otherParam;// 系统参数编号，多个参数以“|”隔开，参数编号和参数值以“:”表示
    
    private String fb_shdj;// 发布商户供应等级
    
    private String fb_shqy;// 发布商户供应区域
    
    private String tqcptss;// 提前出票天数
    
    private String tqcplx;// 提前出票类型 0表示天数 1表示小时
    
    private String tqcptsz;// 提前出票天数
    
    private String tqcpxss;// 提前出票小时始
    
    private String tqcpxsz;// 提前出票小时止
    
    private String tgqgdfl;// 退改签规定分类
    
    private String ifEnjoylowPrice;// 低票面是否享受 0不享受 1享受
    
    private String depTimeLimit; // 特价政策 字符串类型 航班时刻限制0800-1000|1100-1300|1500-1800
    
    private String cabinDiscountLimits;//K位 舱位折扣限制始 ,K低点才返回
    
    private String cabinDiscountLimitz;//K位 舱位折扣限制止，K低点才返回
    
    private String mpfs;// 免票方式 1，按票价销售 2，按最低公布运价折扣低 多少折 销售。
    
    private String cwzkdz;// 免票的舱位折扣低折，就是比最低公布运价舱位折扣低 多少折 销售。
    
    private int bxfs;//政策上绑定的保险份数
    
    private String dyc_sybq;//第一程适用班期【七天特价使用】
    
    private String dtsm;//垫退说明
    
    private String cabType;//舱位类型【目前仅用于航司C站使用】
    
    private String defPayment;//采购默认代扣方式   支付代号|支付名称
    
    private double couponPrice;//航司C站优惠金额
    
    private String couponCode;//航司C站优惠代码
    
    private String couponProName;//航司C站优惠产品名称【产品类型名称】
    
    private String couponNote;//航司C站优惠产品说明【产品使用限制条件】
    
    private String zjfs;//直减方式 0基于Y舱直减   1基于舱位价格直减
    
    private String faid;//方案ID【用于精准营销/新版特价100%白名单验证使用】
    
    private String tgqgdStr;//退改签规定字符串【退票规则编号/改签规则编号/退改签基准(1按票面 2按全价F/C/Y 3按舱位公布运价)/计算退票时对应的舱位】
    
    public String getZclx() {// 1 普通政策 2 K位政策 3 免票政策 4 特殊政策 5 特价政策
        if ("1".equals(cplx)) {
            return "1";
        } else if ("2".equals(cplx)) {
            return "5";
        } else if ("3".equals(cplx)) {
            // 特殊政策类型policyType 1：K位、2：免票、3：特殊
            if (tszclx == 1) {
                return "2";
            } else if (tszclx == 2) {
                return "3";
            } else if (tszclx == 3) {
                return "4";
            }
        }
        return "";
    }

    @XmlElement(name="policyId")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name="productType")
    public String getCplx() {
        return cplx;
    }

    public void setCplx(String cplx) {
        this.cplx = cplx;
    }

    @XmlElement(name="policyType")
    public Integer getTszclx() {
        return tszclx;
    }

    public void setTszclx(Integer tszclx) {
        this.tszclx = tszclx;
    }

    @XmlElement(name="travelType")
    public String getHclx() {
        return hclx;
    }

    public void setHclx(String hclx) {
        this.hclx = hclx;
    }

    @XmlElement(name="passengerType")
    public String getCjrlx() {
        return cjrlx;
    }

    public void setCjrlx(String cjrlx) {
        this.cjrlx = cjrlx;
    }

    @XmlElement(name="airways")
    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs;
    }

    @XmlElement(name="policyName")
    public String getZcmc() {
        return zcmc;
    }

    public void setZcmc(String zcmc) {
        this.zcmc = zcmc;
    }

    @XmlElement(name="flightDateBegin")
    public String getDyc_cfrqs() {
        return dyc_cfrqs;
    }

    public void setDyc_cfrqs(String dyc_cfrqs) {
        this.dyc_cfrqs = dyc_cfrqs;
    }

    @XmlElement(name="flightDateEnd")
    public String getDyc_cfrqz() {
        return dyc_cfrqz;
    }

    public void setDyc_cfrqz(String dyc_cfrqz) {
        this.dyc_cfrqz = dyc_cfrqz;
    }

    @XmlElement(name="fromCity")
    public String getCfcity() {
        return cfcity;
    }

    public void setCfcity(String cfcity) {
        this.cfcity = cfcity;
    }

    @XmlElement(name="toCity")
    public String getDdcity() {
        return ddcity;
    }

    public void setDdcity(String ddcity) {
        this.ddcity = ddcity;
    }

    @XmlElement(name="transitCity")
    public String getZzcity() {
        return zzcity;
    }

    public void setZzcity(String zzcity) {
        this.zzcity = zzcity;
    }

    @XmlElement(name="ifSuitableForFlight")
    public String getDyc_hbsfsy() {
        return dyc_hbsfsy;
    }

    public void setDyc_hbsfsy(String dyc_hbsfsy) {
        this.dyc_hbsfsy = dyc_hbsfsy;
    }

    @XmlElement(name="forFlightNo")
    public String getDyc_hbh() {
        return dyc_hbh;
    }

    public void setDyc_hbh(String dyc_hbh) {
        this.dyc_hbh = dyc_hbh;
    }

    @XmlElement(name="ifSuitableFoShareFlight")
    public String getDyc_hbsfsy_gxhb() {
        return dyc_hbsfsy_gxhb;
    }

    public void setDyc_hbsfsy_gxhb(String dyc_hbsfsy_gxhb) {
        this.dyc_hbsfsy_gxhb = dyc_hbsfsy_gxhb;
    }

    @XmlElement(name="limitType")
    public String getDyc_pjzkxz_lx() {
        return dyc_pjzkxz_lx;
    }

    public void setDyc_pjzkxz_lx(String dyc_pjzkxz_lx) {
        this.dyc_pjzkxz_lx = dyc_pjzkxz_lx;
    }

    @XmlElement(name="limitEnd")
    public Double getDyc_pjzkxz_sx() {
        return dyc_pjzkxz_sx;
    }

    public void setDyc_pjzkxz_sx(Double dyc_pjzkxz_sx) {
        this.dyc_pjzkxz_sx = dyc_pjzkxz_sx;
    }

    @XmlElement(name="limitBegin")
    public Double getDyc_pjzkxz_xx() {
        return dyc_pjzkxz_xx;
    }

    public void setDyc_pjzkxz_xx(Double dyc_pjzkxz_xx) {
        this.dyc_pjzkxz_xx = dyc_pjzkxz_xx;
    }

    @XmlElement(name="seatClass")
    public String getDyc_sycw() {
        return dyc_sycw;
    }

    public void setDyc_sycw(String dyc_sycw) {
        this.dyc_sycw = dyc_sycw;
    }

    @XmlElement(name="backFlightDateBegin")
    public String getDec_cfrqs() {
        return dec_cfrqs;
    }

    public void setDec_cfrqs(String dec_cfrqs) {
        this.dec_cfrqs = dec_cfrqs;
    }
    
    @XmlElement(name="backFlightDateEnd")
    public String getDec_cfrqz() {
        return dec_cfrqz;
    }

    public void setDec_cfrqz(String dec_cfrqz) {
        this.dec_cfrqz = dec_cfrqz;
    }

    @XmlElement(name="ifSuitableForBackFlight")
    public String getDec_hbsfsy() {
        return dec_hbsfsy;
    }

    public void setDec_hbsfsy(String dec_hbsfsy) {
        this.dec_hbsfsy = dec_hbsfsy;
    }

    @XmlElement(name="backForFlightNo")
    public String getDec_hbh() {
        return dec_hbh;
    }

    public void setDec_hbh(String dec_hbh) {
        this.dec_hbh = dec_hbh;
    }

    @XmlElement(name="ifSuitableForBackShareFlight")
    public String getDec_hbsfsy_gxhb() {
        return dec_hbsfsy_gxhb;
    }

    public void setDec_hbsfsy_gxhb(String dec_hbsfsy_gxhb) {
        this.dec_hbsfsy_gxhb = dec_hbsfsy_gxhb;
    }

    @XmlElement(name="backLimitType")
    public String getDec_pjzkxz_lx() {
        return dec_pjzkxz_lx;
    }

    public void setDec_pjzkxz_lx(String dec_pjzkxz_lx) {
        this.dec_pjzkxz_lx = dec_pjzkxz_lx;
    }

    @XmlElement(name="backLimitEnd")
    public Double getDec_pjzkxz_sx() {
        return dec_pjzkxz_sx;
    }

    public void setDec_pjzkxz_sx(Double dec_pjzkxz_sx) {
        this.dec_pjzkxz_sx = dec_pjzkxz_sx;
    }

    @XmlElement(name="backLimitBegin")
    public Double getDec_pjzkxz_xx() {
        return dec_pjzkxz_xx;
    }

    public void setDec_pjzkxz_xx(Double dec_pjzkxz_xx) {
        this.dec_pjzkxz_xx = dec_pjzkxz_xx;
    }

    @XmlElement(name="backSeatClass")
    public String getDec_sycw() {
        return dec_sycw;
    }

    public void setDec_sycw(String dec_sycw) {
        this.dec_sycw = dec_sycw;
    }

    @XmlElement(name="ticketPrice")
    public Double getPj() {
        return pj;
    }

    public void setPj(Double pj) {
        this.pj = pj;
    }
    
    @XmlElement(name="discount")
    public Double getZk() {
        return null == zk ? 0 : zk;
    }

    public void setZk(Double zk) {
        this.zk = zk;
    }

    @XmlElement(name="rebate")
    public Double getFd() {
        return fd;
    }

    public void setFd(Double fd) {
        this.fd = fd;
    }

    @XmlElement(name="deductionAmount")
    public Double getLq() {
        return lq;
    }

    public void setLq(Double lq) {
        this.lq = lq;
    }

    @XmlElement(name="ifChangePnr")
    public String getSfhbmcp() {
        return sfhbmcp;
    }

    public void setSfhbmcp(String sfhbmcp) {
        this.sfhbmcp = sfhbmcp;
    }

    @XmlElement(name="protocolNum")
    public String getSfxyh() {
        return sfxyh;
    }

    public void setSfxyh(String sfxyh) {
        this.sfxyh = sfxyh;
    }

    @XmlElement(name="ifAutoEtdz")
    public String getSfzdcp() {
        return sfzdcp;
    }

    public void setSfzdcp(String sfzdcp) {
        this.sfzdcp = sfzdcp;
    }

    @XmlElement(name="officeId")
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @XmlElement(name="ticketType")
    public String getPzlx() {
        return pzlx;
    }

    public void setPzlx(String pzlx) {
        this.pzlx = pzlx;
    }

    @XmlElement(name="ifLimitWhiteList")
    public String getSfxzbmd() {
        return sfxzbmd;
    }

    public void setSfxzbmd(String sfxzbmd) {
        this.sfxzbmd = sfxzbmd;
    }

    @XmlElement(name="ifLimitCardType")
    public String getZjlxxz() {
        return zjlxxz;
    }

    public void setZjlxxz(String zjlxxz) {
        this.zjlxxz = zjlxxz;
    }

    @XmlElement(name="ifAccordWithPar")
    public String getPmxsjxf() {
        return pmxsjxf;
    }

    public void setPmxsjxf(String pmxsjxf) {
        this.pmxsjxf = pmxsjxf;
    }

    @XmlElement(name="priceOption")
    public String getJgxx() {
        return jgxx;
    }

    public void setJgxx(String jgxx) {
        this.jgxx = jgxx;
    }

    @XmlElement(name="kType")
    public String getK_type() {
        return k_type;
    }

    public void setK_type(String k_type) {
        this.k_type = k_type;
    }

    @XmlElement(name="kLowDiscount")
    public String getKddzk() {
        return kddzk;
    }

    public void setKddzk(String kddzk) {
        this.kddzk = kddzk;
    }

    @XmlElement(name="boardingType")
    public Integer getDj_type() {
        return dj_type;
    }

    public void setDj_type(Integer dj_type) {
        this.dj_type = dj_type;
    }

    @XmlElement(name="ifCreatePnr")
    public Integer getSf_auto_pnr() {
        return sf_auto_pnr;
    }

    public void setSf_auto_pnr(Integer sf_auto_pnr) {
        this.sf_auto_pnr = sf_auto_pnr;
    }

    @XmlElement(name = "ifRelatedToSeat")
    public Integer getSf_av() {
        return null == sf_av ? 0 : sf_av.intValue();
    }

    public void setSf_av(Integer sf_av) {
        this.sf_av = sf_av;
    }

    @XmlElement(name="ifLimitSeat")
    public Integer getSf_zwsxz() {
        return sf_zwsxz;
    }

    public void setSf_zwsxz(Integer sf_zwsxz) {
        this.sf_zwsxz = sf_zwsxz;
    }

    @XmlElement(name="seatLeft")
    public Integer getSy_zws() {
        return sy_zws;
    }

    public void setSy_zws(Integer sy_zws) {
        this.sy_zws = sy_zws;
    }

    @XmlElement(name="ifAutoCofirm")
    public Integer getSf_auto_check() {
        return sf_auto_check;
    }

    public void setSf_auto_check(Integer sf_auto_check) {
        this.sf_auto_check = sf_auto_check;
    }

    @XmlElement(name="gaiqianRetirement")
    public String getTgqgd() {
        return tgqgd;
    }

    public void setTgqgd(String tgqgd) {
        this.tgqgd = tgqgd;
    }

    @XmlElement(name="salesRule")
    public String getWbbz() {
        return wbbz;
    }

    public void setWbbz(String wbbz) {
        this.wbbz = wbbz;
    }

    @XmlElement(name="saleBusinessName")
    public String getFb_shmc() {
        return fb_shmc;
    }

    public void setFb_shmc(String fb_shmc) {
        this.fb_shmc = fb_shmc;
    }

    @XmlElement(name="protection")
    public String getBzjh() {
        return bzjh;
    }

    public void setBzjh(String bzjh) {
        this.bzjh = bzjh;
    }

    @XmlElement(name="etdzDatetime")
    public String getEtdzDatetime() {
        return etdzDatetime;
    }

    public void setEtdzDatetime(String etdzDatetime) {
        this.etdzDatetime = etdzDatetime;
    }

    @XmlElement(name="refundDatetime")
    public String getRefundDatetime() {
        return refundDatetime;
    }

    public void setRefundDatetime(String refundDatetime) {
        this.refundDatetime = refundDatetime;
    }

    @XmlElement(name="voidDatetime")
    public String getVoidDatetime() {
        return voidDatetime;
    }

    public void setVoidDatetime(String voidDatetime) {
        this.voidDatetime = voidDatetime;
    }

    @XmlElement(name="changeDatetime")
    public String getChangeDatetime() {
        return changeDatetime;
    }

    public void setChangeDatetime(String changeDatetime) {
        this.changeDatetime = changeDatetime;
    }

    @XmlElement(name="ifBlacklist")
    public String getIfBlacklist() {
        return ifBlacklist;
    }

    public void setIfBlacklist(String ifBlacklist) {
        this.ifBlacklist = ifBlacklist;
    }

    @XmlElement(name="saleBusinessId")
    public String getFb_shbh() {
        return fb_shbh;
    }

    public void setFb_shbh(String fb_shbh) {
        this.fb_shbh = fb_shbh;
    }

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam;
    }

    @XmlElement(name="supplyLevel")
    public String getFb_shdj() {
        return fb_shdj;
    }

    public void setFb_shdj(String fb_shdj) {
        this.fb_shdj = fb_shdj;
    }

    @XmlElement(name="supplyRegional")
    public String getFb_shqy() {
        return fb_shqy;
    }

    public void setFb_shqy(String fb_shqy) {
        this.fb_shqy = fb_shqy;
    }

    @XmlElement(name="protectionName")
    public String getBzjhMc() {
        return bzjhMc;
    }

    public void setBzjhMc(String bzjhMc) {
        this.bzjhMc = bzjhMc;
    }

    @XmlElement(name="protectionIcon")
    public String getBzjhIcon() {
        return bzjhIcon;
    }

    public void setBzjhIcon(String bzjhIcon) {
        this.bzjhIcon = bzjhIcon;
    }
    
    @XmlElement(name = "tgqgdfl")
    public String getTgqgdfl() {
        return tgqgdfl;
    }
    
    public void setTgqgdfl(String tgqgdfl) {
        this.tgqgdfl = tgqgdfl;
    }
    
    @XmlElement(name = "mpfs")
    public String getMpfs() {
        return mpfs;
    }
    
    public void setMpfs(String mpfs) {
        this.mpfs = mpfs;
    }
    
    @XmlElement(name = "cwzkdz")
    public String getCwzkdz() {
        return cwzkdz;
    }
    
    public void setCwzkdz(String cwzkdz) {
        this.cwzkdz = cwzkdz;
    }
    
    @XmlElement(name = "depTimeLimit")
    public String getDepTimeLimit() {
        return StringUtils.replace(depTimeLimit, ":", "");
    }
    
    public void setDepTimeLimit(String depTimeLimit) {
        this.depTimeLimit = depTimeLimit;
    }
    
    @XmlElement(name = "cabinDiscountLimits")
    public String getCabinDiscountLimits() {
        return cabinDiscountLimits;
    }
    
    public void setCabinDiscountLimits(String cabinDiscountLimits) {
        this.cabinDiscountLimits = cabinDiscountLimits;
    }
    
    @XmlElement(name = "cabinDiscountLimitz")
    public String getCabinDiscountLimitz() {
        return cabinDiscountLimitz;
    }
    
    public void setCabinDiscountLimitz(String cabinDiscountLimitz) {
        this.cabinDiscountLimitz = cabinDiscountLimitz;
    }
    
    @XmlElement(name = "advanceTType")
    public String getTqcplx() {
        return tqcplx;
    }
    
    public void setTqcplx(String tqcplx) {
        this.tqcplx = tqcplx;
    }
    
    @XmlElement(name = "advanceTHour")
    public String getTqcpxss() {
        return tqcpxss;
    }
    
    public void setTqcpxss(String tqcpxss) {
        this.tqcpxss = tqcpxss;
    }
    
    @XmlElement(name = "advanceTHourE")
    public String getTqcpxsz() {
        return tqcpxsz;
    }
    
    public void setTqcpxsz(String tqcpxsz) {
        this.tqcpxsz = tqcpxsz;
    }
    
    public String getIfEnjoylowPrice() {
        return ifEnjoylowPrice;
    }
    
    public void setIfEnjoylowPrice(String ifEnjoylowPrice) {
        this.ifEnjoylowPrice = ifEnjoylowPrice;
    }
    
    @XmlElement(name = "ifChaseRebate")
    public String getSfqyzd() {
        return sfqyzd;
    }
    
    public void setSfqyzd(String sfqyzd) {
        this.sfqyzd = sfqyzd;
    }
    
    @XmlElement(name = "lowRebate")
    public Double getZdfd() {
        return zdfd;
    }
    
    public void setZdfd(Double zdfd) {
        this.zdfd = zdfd;
    }
    
    @XmlElement(name = "changeRebate")
    public Double getFgfd() {
        return fgfd;
    }
    
    public void setFgfd(Double fgfd) {
        this.fgfd = fgfd;
    }
    
    @XmlElement(name = "highRebate")
    public Double getZgfd() {
        return zgfd;
    }
    
    public void setZgfd(Double zgfd) {
        this.zgfd = zgfd;
    }
    
    @XmlElement(name = "subDiscount")
    public Double getDyc_zjzk() {
        return dyc_zjzk;
    }
    
    public void setDyc_zjzk(Double dyc_zjzk) {
        this.dyc_zjzk = dyc_zjzk;
    }
    
    @XmlElement(name = "backSubDiscount")
    public Double getDec_zjzk() {
        return dec_zjzk;
    }
    
    public void setDec_zjzk(Double dec_zjzk) {
        this.dec_zjzk = dec_zjzk;
    }
    
    @XmlElement(name = "noteSimp")
    public String getTgqgdjc() {
        return this.tgqgdjc;
    }
    
    public void setTgqgdjc(String tgqgdjc) {
        this.tgqgdjc = tgqgdjc;
    }
    
    @XmlElement(name = "serviceFee")
    public Double getXsfwf() {
        return xsfwf;
    }
    
    public void setXsfwf(Double xsfwf) {
        this.xsfwf = xsfwf;
    }
    
    @XmlElement(name = "advanceTDay")
    public String getTqcptss() {
        return tqcptss;
    }
    
    public void setTqcptss(String tqcptss) {
        this.tqcptss = tqcptss;
    }
    
    @XmlElement(name = "advanceTDayE")
    public String getTqcptsz() {
        return tqcptsz;
    }
    
    public void setTqcptsz(String tqcptsz) {
        this.tqcptsz = tqcptsz;
    }
    
    @XmlElement(name = "noSuitableForLeg")
    public String getBsyhc() {
        return this.bsyhc;
    }
    
    public void setBsyhc(String bsyhc) {
        this.bsyhc = bsyhc;
    }
    
    public Double getCgj() {
        return this.cgj;
    }
    
    public void setCgj(Double cgj) {
        this.cgj = cgj;
    }
    
	public String getTgqfs() {
		return tgqfs;
	}

	public void setTgqfs(String tgqfs) {
		this.tgqfs = tgqfs;
	}

	public int getBxfs() {
		return bxfs;
	}

	public void setBxfs(int bxfs) {
		this.bxfs = bxfs;
	}

    
    public String getDyc_sybq() {
        return this.dyc_sybq;
    }

    
    public void setDyc_sybq(String dyc_sybq) {
        this.dyc_sybq = dyc_sybq;
    }

	@XmlElement(name="dtsm")
    public String getDtsm() {
        return this.dtsm;
    }
    
    public void setDtsm(String dtsm) {
        this.dtsm = dtsm;
    }
    
    public String getCabType() {
        return this.cabType;
    }
    
    public void setCabType(String cabType) {
        this.cabType = cabType;
    }
    
    @XmlElement(name = "defPayMethod")
    public String getDefPayment() {
        return this.defPayment;
    }
    
    public void setDefPayment(String defPayment) {
        this.defPayment = defPayment;
    }

    @XmlElement(name = "couponPrice")
	public double getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(double couponPrice) {
		this.couponPrice = couponPrice;
	}

	@XmlElement(name = "couponCode")
	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	@XmlElement(name = "couponProName")
	public String getCouponProName() {
		return couponProName;
	}

	public void setCouponProName(String couponProName) {
		this.couponProName = couponProName;
	}

	@XmlElement(name = "couponNote")
	public String getCouponNote() {
		return couponNote;
	}

	public void setCouponNote(String couponNote) {
		this.couponNote = couponNote;
	}

	public String getZjfs() {
		return zjfs;
	}

	public void setZjfs(String zjfs) {
		this.zjfs = zjfs;
	}

    @XmlElement(name="limitWhiteLType")
    public String getXzbmdlx() {
        return this.xzbmdlx;
    }
    
    public void setXzbmdlx(String xzbmdlx) {
        this.xzbmdlx = xzbmdlx;
    }
    
    public String getTgqgdStr() {
        return this.tgqgdStr;
    }
    
    public void setTgqgdStr(String tgqgdStr) {
        this.tgqgdStr = tgqgdStr;
    }

    @XmlElement(name = "leg")
    public String getHc() {
        return this.hc;
    }

    public void setHc(String hc) {
        this.hc = hc;
    }

    @XmlElement(name="programId")
    public String getFaid() {
        return this.faid;
    }
    
    public void setFaid(String faid) {
        this.faid = faid;
    }
}
