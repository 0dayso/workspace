package org.vetech.core.business.pid.pidbean;

import java.util.Map;

/**
 * ASMS中文预订ActionForm
 * @author  gengxianyan
 * @version  [版本号, Apr 24, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@SuppressWarnings("serial")
public class AsmsBookForm extends BookForm {
    
    //航段
    private String[] sxh;//存放对应生成订单序号
    
    private String[] hdxh;
    
    private String[] hclx;
    
    private String[] cfdate;
    
    private String[] hbh;
    
    private String[] cfcity;
    
    private String[] ddcity;
    
    private String[] cfsj;
    
    private String[] ddsj;
    
    private String[] cw;
    
    private String[] zk;
    
    private String[] pj;
    
    private String[] cgj;//国际票预订
    
    private String[] ypj;
    
    private String[] jsf;
    
    private String[] tax;
    
    private String[] validtax;
    
    private String[] qtfy;
    
    private String[] zcid;
    
    private String[] jx;
    
    private String[] hzl;
    
    private String[] ycj;
    
    private String[] pj_zgbzj;//最高标准价
    
    private String[] xyh;//三方协议号
    
    private String[] khfx;//客户返现
    
    //匹配平台政策所需属性
    private String[] fd;
    
    private String[] plat;
    
    private String[] platid;
    
    private String[] oldfd;
    
    private String[] tgqgd;
    
    //乘机人段
    private String[] cjrlx;
    
    private String[] gjcjrlx;//国际票使用乘机人类型
    
    private String[] cjrlxdh;
    
    private String[] cjrxm;
    
    private String[] zjhm;
    
    private String[] csrq;// 出生日期
    
    private String[] sjhm;
    
    private String[] bx;
    
    private String[] zsbx;
    
    private String[] dzhbdt;
    
    private String[] empids;
    
    private String zwyq;//值机座位要求
    
    //国际新加信息
    private String[] cjrxmEn;//乘机人英文姓名
    
    private String[] zjyxq;//证件有效期 2015-01-01
    
    private String[] gj;//国籍 国家三字码
    
    private String[] zjqfg;//证件签发国 国家三字码
    
    private String[] zjlx;//证件类型
    
    private String[] sex;//性别 M
    
    private String[] cyhkgs;//承运方航空公司
    
    private String[] gjOther;//国籍 国家二字码
    
    private String[] zjqfgOther;//证件签发国 国家二字码
    
    private String[] zjlxOther;//证件号码另一种形式
    
    private String[] sexOther;//性别另一种形式 MALE
    
    private String[] dddate;//到达日期
    
    private String ct_nxr;//联系人
    
    private String ct_nxrdh;//联系电话
    
    private String email;//电子邮件
    
    private String yltimecs;//控制预留时间方式参数
    
    //临时字段【暂不整理】
    private String rs;
    
    private String ifSgd;//是否手动订单
    
    private String searchType;
    
    private String hyid;
    
    private String hykh;
    
    private String ifwfyh;//是否往返优惠标示
    
//    private FormFile file;
    
    private String pnrno; // 加婴儿的时候需要传PNR
    
    private String bx_xsj;
    
    private String ct_mobilno;//联系手机
    
    private String dp_dpyid;
    
    private String hyfx; // 来电弹屏时，会员分销标示:1分销
    
    //ASMS平台预订时使用
    private String zl_type; //操作指令类型	[操作指令类型 如RMK]
    
    private String zl_content; //指令内容	[添加指令 指令项 如ic]
    
    private String[] zl_value; //指令		[添加指令所需参数]
    
    private String tdmc;//9人以上自动成团团队名称
    
    private String method;//生成编码的方式
    
    private String czfrom;//1 黑屏操作
    
    //VIP预定时使用
    private String[] clkid;
    
    private String sfzj;//是否值机 1为值机  0或空为不值机
    
    private String pnr_hcglgj; //国内国际订单 0国际 1国内
    
//    //缓存数据，缓存价格等信息
//    private GJHxCacheBean cacheBean;
//    
//    //缓存数据，缓存价格、航线等信息[新版国际票使用]
//    private AirRoute airRoute;
    
    private Map<String,String> policyIdMap;//政策ID集合，key值为乘机人类型  0：成人 1：儿童 2：婴儿
    
//    public GJHxCacheBean getCacheBean() {
//        return cacheBean;
//    }
//    
//    public void setCacheBean(GJHxCacheBean cacheBean) {
//        this.cacheBean = cacheBean;
//    }
    
    public String[] getClkid() {
        return clkid;
    }
    
    public void setClkid(String[] clkid) {
        this.clkid = clkid;
    }
    
    //=======VIP时用到==start=======
    //航段部分
    private String[] matchStatus;
    
    private String[] matchVal;
    
    private String[] matchDesc;
    
    private String[] wbyy;
    
    private String[] wbyybh;
    
    private String[] pj_min;
    
    private String spgzid;//审批规则ID
    
    public String[] getPj_min() {
        return pj_min;
    }
    
    public void setPj_min(String[] pj_min) {
        this.pj_min = pj_min;
    }
    
    //乘机人部分
    private String[] jjrsjhm;
    
    //符合航班信息
    private String fit_Total_Cnt;//所有段累加 符合航班的数量
    
    private String[] fit_cw_book_pj;
    
    private String[] fit_cfsj;
    
    private String[] fit_ddsj;
    
    private String[] fit_cfcity;
    
    private String[] fit_ddcity;
    
    private String[] fit_hkgs;
    
    private String[] fit_hbh;
    
    private String[] fit_cw_book_cw;
    
    private String[] fit_cw_book_tgq;
    
    //=======VIP时用到==start=======
    
    //分销平台预订使用
    private String ps_lx;//配送类型
    
    private String sp_cost;//送票费
    
    private String cp_cpfs;//出票方式
    
    private String ps_dz;//配送地址
    
    private String ps_city;//配送城市
    
    private String ps_bz;//配送备注
    
    /**
     * Q标示第一程，H标示第二程，QH标示单去单回，M标示非单程 Eterm标示黑屏双击预订
     */
    private String cacheFlag;
    
    public String[] getCfdate() {
        return cfdate;
    }
    
    public void setCfdate(String[] cfdate) {
        this.cfdate = cfdate;
    }
    
    public String[] getHbh() {
        return hbh;
    }
    
    public void setHbh(String[] hbh) {
        this.hbh = hbh;
    }
    
    public String[] getCfcity() {
        return cfcity;
    }
    
    public void setCfcity(String[] cfcity) {
        this.cfcity = cfcity;
    }
    
    public String[] getDdcity() {
        return ddcity;
    }
    
    public void setDdcity(String[] ddcity) {
        this.ddcity = ddcity;
    }
    
    public String[] getCfsj() {
        return cfsj;
    }
    
    public void setCfsj(String[] cfsj) {
        this.cfsj = cfsj;
    }
    
    public String[] getDdsj() {
        return ddsj;
    }
    
    public void setDdsj(String[] ddsj) {
        this.ddsj = ddsj;
    }
    
    public String[] getCw() {
        return cw;
    }
    
    public void setCw(String[] cw) {
        this.cw = cw;
    }
    
    public String[] getZk() {
        return zk;
    }
    
    public void setZk(String[] zk) {
        this.zk = zk;
    }
    
    public String[] getPj() {
        return pj;
    }
    
    public void setPj(String[] pj) {
        this.pj = pj;
    }
    
    public String[] getYpj() {
        return ypj;
    }
    
    public void setYpj(String[] ypj) {
        this.ypj = ypj;
    }
    
    public String[] getJsf() {
        return jsf;
    }
    
    public void setJsf(String[] jsf) {
        this.jsf = jsf;
    }
    
    public String[] getTax() {
        return tax;
    }
    
    public void setTax(String[] tax) {
        this.tax = tax;
    }
    
    public String[] getQtfy() {
        return qtfy;
    }
    
    public void setQtfy(String[] qtfy) {
        this.qtfy = qtfy;
    }
    
    public String[] getZcid() {
        return zcid;
    }
    
    public void setZcid(String[] zcid) {
        this.zcid = zcid;
    }
    
    public String[] getJx() {
        return jx;
    }
    
    public void setJx(String[] jx) {
        this.jx = jx;
    }
    
    public String[] getYcj() {
        return ycj;
    }
    
    public void setYcj(String[] ycj) {
        this.ycj = ycj;
    }
    
    public String[] getPj_zgbzj() {
        return pj_zgbzj;
    }
    
    public void setPj_zgbzj(String[] pj_zgbzj) {
        this.pj_zgbzj = pj_zgbzj;
    }
    
    public String[] getCjrlx() {
        return cjrlx;
    }
    
    public void setCjrlx(String[] cjrlx) {
        this.cjrlx = cjrlx;
    }
    
    public String[] getCjrxm() {
        return cjrxm;
    }
    
    public void setCjrxm(String[] cjrxm) {
        this.cjrxm = cjrxm;
    }
    
    public String[] getZjhm() {
        return zjhm;
    }
    
    public void setZjhm(String[] zjhm) {
        this.zjhm = zjhm;
    }
    
    public String[] getCsrq() {
        return csrq;
    }
    
    public void setCsrq(String[] csrq) {
        this.csrq = csrq;
    }
    
    public String[] getSjhm() {
        return sjhm;
    }
    
    public void setSjhm(String[] sjhm) {
        this.sjhm = sjhm;
    }
    
    public String[] getBx() {
        return bx;
    }
    
    public void setBx(String[] bx) {
        this.bx = bx;
    }
    
    public String getHyid() {
        return hyid;
    }
    
    public void setHyid(String hyid) {
        this.hyid = hyid;
    }
    
    public String getHykh() {
        return hykh;
    }
    
    public void setHykh(String hykh) {
        this.hykh = hykh;
    }
    
    public String getRs() {
        return rs;
    }
    
    public void setRs(String rs) {
        this.rs = rs;
    }
    
    public String getSearchType() {
        return searchType;
    }
    
    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    
    public String getPnrno() {
        return pnrno;
    }
    
    public void setPnrno(String pnrno) {
        this.pnrno = pnrno;
    }
    
    public String getBx_xsj() {
        return bx_xsj;
    }
    
    public void setBx_xsj(String bx_xsj) {
        this.bx_xsj = bx_xsj;
    }
    
    public String getCt_mobilno() {
        return ct_mobilno;
    }
    
    public void setCt_mobilno(String ct_mobilno) {
        this.ct_mobilno = ct_mobilno;
    }
    
    public String getDp_dpyid() {
        return dp_dpyid;
    }
    
    public void setDp_dpyid(String dp_dpyid) {
        this.dp_dpyid = dp_dpyid;
    }
    
    public String getHyfx() {
        return hyfx;
    }
    
    public void setHyfx(String hyfx) {
        this.hyfx = hyfx;
    }
    
    public String getZl_type() {
        return zl_type;
    }
    
    public void setZl_type(String zl_type) {
        this.zl_type = zl_type;
    }
    
    public String getZl_content() {
        return zl_content;
    }
    
    public void setZl_content(String zl_content) {
        this.zl_content = zl_content;
    }
    
    public String[] getZl_value() {
        return zl_value;
    }
    
    public void setZl_value(String[] zl_value) {
        this.zl_value = zl_value;
    }
    
    public String[] getSxh() {
        return sxh;
    }
    
    public void setSxh(String[] sxh) {
        this.sxh = sxh;
    }
    
    public String getIfSgd() {
        return ifSgd;
    }
    
    public void setIfSgd(String ifSgd) {
        this.ifSgd = ifSgd;
    }
    
    public String[] getHclx() {
        return hclx;
    }
    
    public void setHclx(String[] hclx) {
        this.hclx = hclx;
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public String getIfwfyh() {
        return ifwfyh;
    }
    
    public void setIfwfyh(String ifwfyh) {
        this.ifwfyh = ifwfyh;
    }
    
//    public FormFile getFile() {
//        return file;
//    }
//    
//    public void setFile(FormFile file) {
//        this.file = file;
//    }
    
    public String[] getFd() {
        return fd;
    }
    
    public void setFd(String[] fd) {
        this.fd = fd;
    }
    
    public String[] getPlat() {
        return plat;
    }
    
    public void setPlat(String[] plat) {
        this.plat = plat;
    }
    
    public String[] getPlatid() {
        return platid;
    }
    
    public void setPlatid(String[] platid) {
        this.platid = platid;
    }
    
    public String[] getOldfd() {
        return oldfd;
    }
    
    public void setOldfd(String[] oldfd) {
        this.oldfd = oldfd;
    }
    
    public String[] getTgqgd() {
        return tgqgd;
    }
    
    public void setTgqgd(String[] tgqgd) {
        this.tgqgd = tgqgd;
    }
    
    public String[] getXyh() {
        return xyh;
    }
    
    public void setXyh(String[] xyh) {
        this.xyh = xyh;
    }
    
    public String getTdmc() {
        return tdmc;
    }
    
    public void setTdmc(String tdmc) {
        this.tdmc = tdmc;
    }
    
    public String[] getZjyxq() {
        return zjyxq;
    }
    
    public void setZjyxq(String[] zjyxq) {
        this.zjyxq = zjyxq;
    }
    
    public String[] getGj() {
        return gj;
    }
    
    public void setGj(String[] gj) {
        this.gj = gj;
    }
    
    public String[] getZjqfg() {
        return zjqfg;
    }
    
    public void setZjqfg(String[] zjqfg) {
        this.zjqfg = zjqfg;
    }
    
    public String[] getZjlx() {
        return zjlx;
    }
    
    public void setZjlx(String[] zjlx) {
        this.zjlx = zjlx;
    }
    
    public String[] getSex() {
        return sex;
    }
    
    public void setSex(String[] sex) {
        this.sex = sex;
    }
    
    public String[] getCyhkgs() {
        return cyhkgs;
    }
    
    public void setCyhkgs(String[] cyhkgs) {
        this.cyhkgs = cyhkgs;
    }
    
    public String[] getCjrxmEn() {
        return cjrxmEn;
    }
    
    public void setCjrxmEn(String[] cjrxmEn) {
        this.cjrxmEn = cjrxmEn;
    }
    
    public String[] getGjOther() {
        return gjOther;
    }
    
    public void setGjOther(String[] gjOther) {
        this.gjOther = gjOther;
    }
    
    public String[] getZjqfgOther() {
        return zjqfgOther;
    }
    
    public void setZjqfgOther(String[] zjqfgOther) {
        this.zjqfgOther = zjqfgOther;
    }
    
    public String[] getZjlxOther() {
        return zjlxOther;
    }
    
    public void setZjlxOther(String[] zjlxOther) {
        this.zjlxOther = zjlxOther;
    }
    
    public String[] getSexOther() {
        return sexOther;
    }
    
    public void setSexOther(String[] sexOther) {
        this.sexOther = sexOther;
    }
    
    public String[] getCjrlxdh() {
        return cjrlxdh;
    }
    
    public void setCjrlxdh(String[] cjrlxdh) {
        this.cjrlxdh = cjrlxdh;
    }
    
    public String getCt_nxr() {
        return ct_nxr;
    }
    
    public void setCt_nxr(String ct_nxr) {
        this.ct_nxr = ct_nxr;
    }
    
    public String getCt_nxrdh() {
        return ct_nxrdh;
    }
    
    public void setCt_nxrdh(String ct_nxrdh) {
        this.ct_nxrdh = ct_nxrdh;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPs_lx() {
        return ps_lx;
    }
    
    public void setPs_lx(String ps_lx) {
        this.ps_lx = ps_lx;
    }
    
    public String getSp_cost() {
        return sp_cost;
    }
    
    public void setSp_cost(String sp_cost) {
        this.sp_cost = sp_cost;
    }
    
    public String getCp_cpfs() {
        return cp_cpfs;
    }
    
    public void setCp_cpfs(String cp_cpfs) {
        this.cp_cpfs = cp_cpfs;
    }
    
    public String getPs_dz() {
        return ps_dz;
    }
    
    public void setPs_dz(String ps_dz) {
        this.ps_dz = ps_dz;
    }
    
    public String getPs_city() {
        return ps_city;
    }
    
    public void setPs_city(String ps_city) {
        this.ps_city = ps_city;
    }
    
    public String getPs_bz() {
        return ps_bz;
    }
    
    public void setPs_bz(String ps_bz) {
        this.ps_bz = ps_bz;
    }
    
    public String[] getCgj() {
        return cgj;
    }
    
    public void setCgj(String[] cgj) {
        this.cgj = cgj;
    }
    
    public String getYltimecs() {
        return yltimecs;
    }
    
    public void setYltimecs(String yltimecs) {
        this.yltimecs = yltimecs;
    }
    
    public String[] getKhfx() {
        return khfx;
    }
    
    public void setKhfx(String[] khfx) {
        this.khfx = khfx;
    }
    
    public String[] getMatchStatus() {
        return matchStatus;
    }
    
    public void setMatchStatus(String[] matchStatus) {
        this.matchStatus = matchStatus;
    }
    
    public String[] getMatchVal() {
        return matchVal;
    }
    
    public void setMatchVal(String[] matchVal) {
        this.matchVal = matchVal;
    }
    
    public String[] getMatchDesc() {
        return matchDesc;
    }
    
    public void setMatchDesc(String[] matchDesc) {
        this.matchDesc = matchDesc;
    }
    
    public String[] getWbyy() {
        return wbyy;
    }
    
    public void setWbyy(String[] wbyy) {
        this.wbyy = wbyy;
    }
    
    public String[] getWbyybh() {
        return wbyybh;
    }
    
    public void setWbyybh(String[] wbyybh) {
        this.wbyybh = wbyybh;
    }
    
    public String[] getJjrsjhm() {
        return jjrsjhm;
    }
    
    public void setJjrsjhm(String[] jjrsjhm) {
        this.jjrsjhm = jjrsjhm;
    }
    
    public String[] getHdxh() {
        return hdxh;
    }
    
    public void setHdxh(String[] hdxh) {
        this.hdxh = hdxh;
    }
    
    public String[] getFit_cw_book_pj() {
        return fit_cw_book_pj;
    }
    
    public void setFit_cw_book_pj(String[] fit_cw_book_pj) {
        this.fit_cw_book_pj = fit_cw_book_pj;
    }
    
    public String[] getFit_cfsj() {
        return fit_cfsj;
    }
    
    public void setFit_cfsj(String[] fit_cfsj) {
        this.fit_cfsj = fit_cfsj;
    }
    
    public String[] getFit_ddsj() {
        return fit_ddsj;
    }
    
    public void setFit_ddsj(String[] fit_ddsj) {
        this.fit_ddsj = fit_ddsj;
    }
    
    public String[] getFit_cfcity() {
        return fit_cfcity;
    }
    
    public void setFit_cfcity(String[] fit_cfcity) {
        this.fit_cfcity = fit_cfcity;
    }
    
    public String[] getFit_ddcity() {
        return fit_ddcity;
    }
    
    public void setFit_ddcity(String[] fit_ddcity) {
        this.fit_ddcity = fit_ddcity;
    }
    
    public String[] getFit_hkgs() {
        return fit_hkgs;
    }
    
    public void setFit_hkgs(String[] fit_hkgs) {
        this.fit_hkgs = fit_hkgs;
    }
    
    public String[] getFit_hbh() {
        return fit_hbh;
    }
    
    public void setFit_hbh(String[] fit_hbh) {
        this.fit_hbh = fit_hbh;
    }
    
    public String[] getFit_cw_book_cw() {
        return fit_cw_book_cw;
    }
    
    public void setFit_cw_book_cw(String[] fit_cw_book_cw) {
        this.fit_cw_book_cw = fit_cw_book_cw;
    }
    
    public String[] getFit_cw_book_tgq() {
        return fit_cw_book_tgq;
    }
    
    public void setFit_cw_book_tgq(String[] fit_cw_book_tgq) {
        this.fit_cw_book_tgq = fit_cw_book_tgq;
    }
    
    public String getFit_Total_Cnt() {
        return fit_Total_Cnt;
    }
    
    public void setFit_Total_Cnt(String fit_Total_Cnt) {
        this.fit_Total_Cnt = fit_Total_Cnt;
    }
    
    public String getCzfrom() {
        return czfrom;
    }
    
    public void setCzfrom(String czfrom) {
        this.czfrom = czfrom;
    }
    
    public String getCacheFlag() {
        return cacheFlag;
    }
    
    public void setCacheFlag(String cacheFlag) {
        this.cacheFlag = cacheFlag;
    }
    
    public String[] getZsbx() {
        return zsbx;
    }
    
    public void setZsbx(String[] zsbx) {
        this.zsbx = zsbx;
    }
    
    public String[] getDddate() {
        return dddate;
    }
    
    public void setDddate(String[] dddate) {
        this.dddate = dddate;
    }
    
    public String getSpgzid() {
        return spgzid;
    }
    
    public void setSpgzid(String spgzid) {
        this.spgzid = spgzid;
    }
    
    public String[] getValidtax() {
        return validtax;
    }
    
    public void setValidtax(String[] validtax) {
        this.validtax = validtax;
    }
    
    public String getSfzj() {
        return sfzj;
    }
    
    public void setSfzj(String sfzj) {
        this.sfzj = sfzj;
    }
    
    public String getZwyq() {
        return zwyq;
    }
    
    public void setZwyq(String zwyq) {
        this.zwyq = zwyq;
    }
    
    public String[] getDzhbdt() {
        return dzhbdt;
    }
    
    public void setDzhbdt(String[] dzhbdt) {
        this.dzhbdt = dzhbdt;
    }
    
    public String[] getEmpids() {
        return empids;
    }
    
    public void setEmpids(String[] empids) {
        this.empids = empids;
    }
    
    public String getPnr_hcglgj() {
        return pnr_hcglgj;
    }
    
    public void setPnr_hcglgj(String pnr_hcglgj) {
        this.pnr_hcglgj = pnr_hcglgj;
    }

//	public AirRoute getAirRoute() {
//		return airRoute;
//	}
//
//	public void setAirRoute(AirRoute airRoute) {
//		this.airRoute = airRoute;
//	}

	public Map<String, String> getPolicyIdMap() {
		return policyIdMap;
	}

	public void setPolicyIdMap(Map<String, String> policyIdMap) {
		this.policyIdMap = policyIdMap;
	}

	public String[] getHzl() {
		return hzl;
	}

	public void setHzl(String[] hzl) {
		this.hzl = hzl;
	}

	public String[] getGjcjrlx() {
		return gjcjrlx;
	}

	public void setGjcjrlx(String[] gjcjrlx) {
		this.gjcjrlx = gjcjrlx;
	}

}