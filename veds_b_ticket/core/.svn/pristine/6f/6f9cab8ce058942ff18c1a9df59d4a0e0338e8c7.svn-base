package org.vetech.core.business.pid.api.editpnr;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.Param;
import org.vetech.core.business.pid.util.BookUtil;

public class EditPnrParam  extends Param{
	private String pnrno;
	
	private String bstrNeedSealed = "1";

	private String ss = "";

	private String foid = "";

	private String fqtv = "";

	private String dpr = "";

	private String ye = "";

	private String inft = "";// SSR INFT CZ NN1/PEKCAN CZ3102 Y12JUL TEST/BABY(必须是拼音) 07DEC07/P1

	private String tlTime = "";

	private String gn = "";

	private String rmkic = "";

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getFoid() {
		return foid;
	}

	public void setFoid(String foid) {
		this.foid = foid;
	}

	public String getFqtv() {
		return fqtv;
	}

	public void setFqtv(String fqtv) {
		this.fqtv = fqtv;
	}

	public String getDpr() {
		return dpr;
	}

	public void setDpr(String dpr) {
		this.dpr = dpr;
	}

	public String getYe() {
		return ye;
	}

	public void setYe(String ye) {
		this.ye = ye;
	}

	public String getInft() {
		return inft;
	}

	public void setInft(String inft) {
		this.inft = inft;
	}

	public String getTlTime() {
		return tlTime;
	}

	public void setTlTime(String tlTime) {
		this.tlTime = tlTime;
	}

	public String getGn() {
		return gn;
	}

	public void setGn(String gn) {
		this.gn = gn;
	}

	public String getRmkic() {
		return rmkic;
	}

	public void setRmkic(String rmkic) {
		this.rmkic = rmkic;
	}
	

	/**
	 * 添加成人信息
	 */
	public void addAdult(String cjrxm) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "NM1" + cjrxm;
		} else {
			dpr = dpr + "1" + cjrxm;
		}

	}
	

	/**
	 * xe乘机人
	 */
	public void xeCjr(int sxh) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "XEP" + sxh;
		} else {
			dpr = dpr + "\rXEP" + sxh;
		}
	}

	/**
	 * xe团队乘机人
	 */
	public void xeGroupCjr(int sxh) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "XEG" + sxh;
		} else {
			dpr = dpr + "XEG" + sxh;
		}
	}

	/**
	 * xe其它数据项是xe
	 */
	public void xeQt(int sxh) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "XE" + sxh;
		} else {
			dpr = dpr + "XE" + sxh;
		}
	}

	/**
	 * 生成指令
	 * 
	 * @return 指令
	 */
	public String command() {
		return ss + dpr + gn + "\r" + foid + fqtv + ye + inft + rmkic + tlTime + "\r@";
	}

	public String cjrinfo() {
		return dpr + "\r" + foid + fqtv + ye + inft + tlTime + "\r@";
	}

	/**
	 * 添加儿童信息
	 */
	public void addChild(String etxm) throws Exception {
		if (StringUtils.isBlank(dpr)) {
			dpr = "NM1" + etxm + " CHD";
		} else {
			dpr = dpr + "1" + etxm + " CHD";
		}
	}

	/**
	 * 添加婴儿信息(国际)
	 * kh_khddhdbList,cjrPY（乘机人拼音）,yedate(年月),yedate2（年月日）yedate3（月日）,p
	 */
	public void addInfant(List<Map<String, Object>> kh_khddhdbList,String cjrPY, String yedate, String yedate2,String p) throws Exception {
		ye = ye + "XN:IN/" + cjrPY + " INF(" + yedate + ")/" + p + "\r";
		for(Map m:kh_khddhdbList){
			/**
			 * 	SSR INFT MU NN1/PVGLHR 551 E25JUL WU/HUA 27MAY14/P7   
				MU  航空公司 NN  固定
				PVGLHR 551  航班号  E 舱位 
				25JUL 航班日期     WU/HUA  婴儿姓名  27MAY14 出生日月年  P7跟随第7个人  
			 */
			String hbh_j = (String)m.get("HBH");//航班号
			String hkgs_j = hbh_j.substring(0, 2);	//航空公司
			String hbh_num_j = hbh_j.substring(2, hbh_j.length());//航班号的数字
			String cw = (String)m.get("CW");	//舱位
			String qfsj = BookUtil.dateCommandDay((String)m.get("CFSJ"));//出发时间（起飞时间）
			String cfcity = (String)m.get("CFCITY");//出发城市
			String ddcity = (String)m.get("DDCITY");//抵达城市
			ye = ye+"SSR INFT "+ hkgs_j + " NN1/" + cfcity + ddcity + " " + hbh_num_j + " " + cw + qfsj + " " + cjrPY + " " + yedate2 + "/" + p + "\r";
		}
		
	}
	/**
	 * 添加婴儿信息
	 */
	public void addInfant(String csrq, String crxm, String yexm) throws Exception {
		ye = ye + "XN:IN/" + yexm + " INF(" + csrq + ")/" + crxm + "\r";
	}

	/**
	 * 添加婴儿信息
	 * 
	 * @param ifINF
	 *            标示婴儿姓名后面是否加INF
	 */
	public void addInfant(String csrq, String crxm, String yexm, String ifINF) throws Exception {
		if (StringUtils.isEmpty(ifINF)) {
			ye = ye + "XN:IN/" + yexm + "INF(" + csrq + ")/" + crxm + "\r";
		} else {
			ye = ye + "XN:IN/" + yexm + "(" + csrq + ")/" + crxm + "\r";
		}
	}

	/**
	 * 在封口前 加入RMK 指令 param1为指令 param2为指令数据 RMK:IC CZ/1203100
	 */
	public void rmkic(String param1, String param2) {
		rmkic = "rmk " + param1 + "" + param2 + "\r";
	}

	/**
	 * 添加证件项
	 */
	public void addSSR_FOID(String hkgs, String zjlx, String zjhm, String cjrxm) throws Exception {
		foid = foid + "SSR FOID " + hkgs + " HK" + "/" + zjlx + zjhm + "/" + cjrxm + "\r";
	}
	
	/**
	 * 国际航段国外住址信息
	 * @param hkgs 航空公司
	 * @param cjrs 乘机人总人数
	 * @param dzlx 地址类型 (D目的地、R居住地)
	 * @param gj 国家代码(USA CN)
	 * @param xxdz (地址信息 )
	 * @param city 城市 (New York / Beijing)
	 * @param sf 省份 (US CN)
	 * @param zipCode 邮编
	 * @param ye 婴儿标示 如果是婴儿 则传“I”
	 * @param p
	 * @throws Exception
	 */
	public void addSSR_DOCA(String hkgs,String dzlx,String gj,String xxdz,String city,String sf,String zipCode,String ye,String p){
		foid = foid + "SSR DOCA " + hkgs + " HK1 " + dzlx + "/" + gj + "/" + xxdz + "/" + city + "/" + sf + "/" + zipCode+ "/" + (StringUtils.isNotBlank(ye) ? (ye + "/") : "") + p + "\r";
	}
	
	/**
	 * 添加证件项(国际票)
	 * SSR DOCS KE 			HK1 		P	   /CN	  /G40119537/CN/29SEP58/M/02JUN20	/XU			/DETIAN						  /H 		/P2
	 * SSR:DOCS 航空公司代码 Action-Code1 证件类型/发证国家/证件号码/国籍/出生日期/性别/证件有效期限/SURNAME(姓)/FIRST-NAME(名)/MID-NAME(中间名)/持有人标识H/P1
	 */
	public void addSSR_DOCS(String hkgs, String hzqfg,String zjhm,String gj,String csrq,String crxb,String zjyxrq,String crxm,String p) throws Exception{
		foid = foid + "SSR DOCS " + hkgs + " HK1 P/" + hzqfg + "/" + zjhm + "/" + gj + "/" + csrq + "/" + crxb + "/" + zjyxrq + "/" + crxm + "/H/" + p + "\r";
	}
	/**
	 * 添加常旅客项
	 */
	public void addSSR_FQTV(String hkgs, String clk, String cjrxm) throws Exception {
		fqtv = fqtv + "SSR FQTV " + hkgs + " HK" + "/" + hkgs + clk + "/" + cjrxm + "\r";
	}

	/**
	 * 添加航班项
	 */
	public void addAirSeg(String hbh, String cw, String cfcity, String ddcity, String rs, String cfsj) throws Exception {
		ss = ss + "SS:" + hbh + "/" + cw.charAt(0) + "/" + cfsj + "/" + cfcity + ddcity + "/" + rs + "\r";
	}

	/**
	 * 设置留票时间
	 */
	public void setTimelimit(String time) throws Exception {
		tlTime = "TKTL/" + time;
	}

	/**
	 * 
	 */
	public void setTimelimitSimple(String time) throws Exception {
		tlTime = "tm" + time;
	}

	/**
	 * 删除留票时间
	 */
	public void delTimelimit(String tlno) throws Exception {
		tlTime = tlno + "/T";
	}

	/**
	 * 删除留票时间,带票号
	 */
	public void delTimelimit(String tlno, String ticketNo) throws Exception {
		tlTime = tlno + "/T/" + ticketNo;
	}

	/**
	 * 添加婴儿航班项
	 */
	public void gn(String num, String group) {
		gn = "gn:" + num + "/" + group + "\r";
	}

	public void addInfoAirSeg(String hkgs, String cfcity, String ddcity, String hbh, String cw, String cfdate,
			String xm, String csrq, String p) throws Exception {
		hbh = hbh.replace("*", "");
		inft = inft + "SSR INFT " + hkgs + " NN1/" + cfcity + ddcity + " " + hbh + " " + cw.charAt(0) + cfdate + " "
				+ xm + " " + csrq + "/" + p + "\r";
	}

	public String getBstrNeedSealed() {
		return bstrNeedSealed;
	}

	public void setBstrNeedSealed(String bstrNeedSealed) {
		if ("0".equals(bstrNeedSealed) || "1".equals(bstrNeedSealed)) {
			this.bstrNeedSealed = bstrNeedSealed;
		} else {
			this.bstrNeedSealed = "1";
		}
	}
}
