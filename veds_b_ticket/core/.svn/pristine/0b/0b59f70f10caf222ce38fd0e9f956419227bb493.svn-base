package org.vetech.core.business.pid.pidbean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PID接口返回PNR内容解析Bean
 * @author  gengxianyan
 * @version  [版本号, Apr 8, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */

@XmlRootElement(name = "P")
public class PidPnrnrBean {

	private String jkzt;	//	1 成功返回1，失败返回0
	private String pnrno;	//	pnr编码
	private String ispnrxe;	//	如果pnr已被取消，返回2，否则，返回1
	private String pnrnr;	//	pnr内容
	private String bigpnr;	// 	大编码
	private String other;	// 	office信息,PNR的类型(1为正常票 2为团队票),团队总人数,团队人数,团队的名称,会员卡号,预留时间,儿童数,大人数,婴儿数
	private String tktl;
	private String office;
	private String auth;
	private String timeofnoseat;//NO位时限
	private String hdxx;	//	航段数|顺序号,航班号,舱位,出发日期,出发城市,到达城市,状态位,出发时间,到达时间,飞机型号,实际承运人,航站楼,航段状态后的人数,子仓位
	private String cjrxx;	//	>人数|顺序号,乘机人姓名,证件号码,证件类型,乘机人类型,票价,建设费,燃油税,分号分隔的票号列表,XCNY数据项
	
	@XmlElement(name = "R")
	public String getJkzt() {
		return jkzt;
	}
	public void setJkzt(String jkzt) {
		this.jkzt = jkzt;
	}
	
	@XmlElement(name = "N")
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	
	@XmlElement(name = "Q")
	public String getPnrnr() {
		return pnrnr;
	}
	public void setPnrnr(String pnrnr) {
		this.pnrnr = pnrnr;
	}
	
	@XmlElement(name = "B")
	public String getBigpnr() {
		return bigpnr;
	}
	public void setBigpnr(String bigpnr) {
		this.bigpnr = bigpnr;
	}
	
	@XmlElement(name = "C")
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	@XmlElement(name = "TL")
	public String getTktl() {
		return tktl;
	}
	public void setTktl(String tktl) {
		this.tktl = tktl;
	}
	
	@XmlElement(name = "OFFICE")
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
	@XmlElement(name = "AUTH")
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	
	@XmlElement(name = "HD")
	public String getHdxx() {
		return hdxx;
	}
	public void setHdxx(String hdxx) {
		this.hdxx = hdxx;
	}
	
	@XmlElement(name = "CJR")
	public String getCjrxx() {
		return cjrxx;
	}
	public void setCjrxx(String cjrxx) {
		this.cjrxx = cjrxx;
	}
	
	@XmlElement(name = "T")
	public String getIspnrxe() {
		return ispnrxe;
	}
	public void setIspnrxe(String ispnrxe) {
		this.ispnrxe = ispnrxe;
	}
	@XmlElement(name = "TIMEOFNOSEAT")
	public String getTimeofnoseat() {
		return timeofnoseat;
	}
	public void setTimeofnoseat(String timeofnoseat) {
		this.timeofnoseat = timeofnoseat;
	}
	
}
