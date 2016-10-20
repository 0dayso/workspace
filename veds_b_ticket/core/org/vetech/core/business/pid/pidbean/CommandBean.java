package org.vetech.core.business.pid.pidbean;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 拼装指令公共JavaBean
 * @author  gengxianyan
 * @version  [版本号, Apr 25, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class CommandBean {

	//配置标示
	private String bookType;
	private String gngj;//国内国际预订标示 1国籍 其他国内
	private String dpyid;//订票员ID
	
	//航段
	private String[] hclx;
	private String[] hkgs;
	private String[] hbh;
	private String[] cfcity;
	private String[] ddcity;
	private String[] cfdate;
	private String[] cfsj;
	private String[] cw;
	private String[] zcid;
	private String[] ifnoseat;//是否无座位并订票为当天标示
	
	//乘机人段
	private String rs;
	private String[] cjrlx; 
	private String[] cjrxm;
	private String[] zjhm;
	private String[] csrq;//出生日期
	private String[] sjhm;
	private String[] etsjhm;//儿童手机号码
	
	private Map<String,String> otherMap;//存放其他字段，便于扩充
	
	private String ifqk;		//是否支持缺口程
	
	private String jtnum;		//加团人数
	private String jtname;		//团队名称
	
	private String[] hkgslck;	//里程卡
	private String[] crzwph;	//成人座位偏好 VIP+
	private String[] crcsph;	//成人餐食偏好 VIP+
	private String[] etzwph;	//et 座位偏好 VIP+
	private String[] etcsph;	//et餐食偏好 VIP+
	
	private String ct_phoneno;//联系人手机号码
	private String yl_timetype;//预留时限类型 1/2
	
	private String ypnrno;//原PNR编码，例如：儿童关联成人PNR编码，此处传入成人PNR编码
	

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

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

	public String[] getCfsj() {
		return cfsj;
	}

	public void setCfsj(String[] cfsj) {
		this.cfsj = cfsj;
	}

	public String[] getCw() {
		return cw;
	}

	public void setCw(String[] cw) {
		this.cw = cw;
	}

	public String[] getZcid() {
		return zcid;
	}

	public void setZcid(String[] zcid) {
		this.zcid = zcid;
	}

	public String[] getIfnoseat() {
		return ifnoseat;
	}

	public void setIfnoseat(String[] ifnoseat) {
		this.ifnoseat = ifnoseat;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
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

	public String[] getHkgs() {
		return hkgs;
	}

	public void setHkgs(String[] hkgs) {
		this.hkgs = hkgs;
	}

	public String[] getHclx() {
		return hclx;
	}

	public void setHclx(String[] hclx) {
		this.hclx = hclx;
	}

	public Map<String, String> getOtherMap() {
		return otherMap;
	}

	public void setOtherMap(Map<String, String> otherMap) {
		this.otherMap = otherMap;
	}

	public String getIfqk() {
		return ifqk;
	}

	public void setIfqk(String ifqk) {
		this.ifqk = ifqk;
	}

	public String getGngj() {
		return gngj;
	}

	public void setGngj(String gngj) {
		this.gngj = gngj;
	}

	public String getJtnum() {
		return jtnum;
	}

	public void setJtnum(String jtnum) {
		this.jtnum = jtnum;
	}

	public String getJtname() {
		return jtname;
	}

	public void setJtname(String jtname) {
		this.jtname = jtname;
	}

	public String[] getHkgslck() {
		return hkgslck;
	}

	public void setHkgslck(String[] hkgslck) {
		this.hkgslck = hkgslck;
	}

	public String[] getCrzwph() {
		return crzwph;
	}

	public void setCrzwph(String[] crzwph) {
		this.crzwph = crzwph;
	}

	public String[] getCrcsph() {
		return crcsph;
	}

	public void setCrcsph(String[] crcsph) {
		this.crcsph = crcsph;
	}

	public String[] getEtzwph() {
		return etzwph;
	}

	public void setEtzwph(String[] etzwph) {
		this.etzwph = etzwph;
	}

	public String[] getEtcsph() {
		return etcsph;
	}

	public void setEtcsph(String[] etcsph) {
		this.etcsph = etcsph;
	}

	public String getCt_phoneno() {
		return ct_phoneno;
	}

	public void setCt_phoneno(String ct_phoneno) {
		this.ct_phoneno = ct_phoneno;
	}
	
	/**去程航班号 去掉航空公司前缀 例如HBH是MU2501  此处取2501 VIP+ */
	public String[] getAirNo() {
		String[] hbh = getHbh();
		String[] airnos = new String[hbh.length];
		for(int i=0;i<hbh.length;i++){
			if(StringUtils.isNotBlank(hbh[i])){
				String airno = hbh[i].replace("*","");
				airnos[i] = airno.substring(2);
			}
			
		}
		return airnos;
	}

	public String getYl_timetype() {
		return yl_timetype;
	}

	public void setYl_timetype(String yl_timetype) {
		this.yl_timetype = yl_timetype;
	}

	public String getDpyid() {
		return dpyid;
	}

	public void setDpyid(String dpyid) {
		this.dpyid = dpyid;
	}

	public String[] getSjhm() {
		return sjhm;
	}

	public void setSjhm(String[] sjhm) {
		this.sjhm = sjhm;
	}

	public String[] getEtsjhm() {
		return etsjhm;
	}

	public void setEtsjhm(String[] etsjhm) {
		this.etsjhm = etsjhm;
	}

	public String getYpnrno() {
		return ypnrno;
	}

	public void setYpnrno(String ypnrno) {
		this.ypnrno = ypnrno;
	}
}
