package org.vetech.core.business.pid.pidbean;

import java.util.List;

/**
 * 解析PNR内容
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@SuppressWarnings("serial")
public class PnrnrBean extends PidResult{

	private String pnrno;
	private String bigpnr;
	private String office;
	private String pnrzt;
	private String pnrlx;//PNR的类型(1为正常票 2为团队票)
	private String pnrnr;//pnr内容
	private String timeofnoseat;////NO位时限
	
	private String crCount;
	private String etCount;
	private String yeCount;
	
	private String teamName;//团队名称
	private String teamRsCount;//团队人数
	
	private String pnryltime;//保留时限
	private String auth;//PNR授权信息
	
	private String patnr;//PAT内容
	
	private List<PnrSegmentBean> segmentList;//航段集合
	private List<PnrPassengerBean> passengerList;//乘机人集合
	
	
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	public String getBigpnr() {
		return bigpnr;
	}
	public void setBigpnr(String bigpnr) {
		this.bigpnr = bigpnr;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getPnrzt() {
		return pnrzt;
	}
	public void setPnrzt(String pnrzt) {
		this.pnrzt = pnrzt;
	}
	public String getCrCount() {
		return crCount;
	}
	public void setCrCount(String crCount) {
		this.crCount = crCount;
	}
	public String getEtCount() {
		return etCount;
	}
	public void setEtCount(String etCount) {
		this.etCount = etCount;
	}
	public String getYeCount() {
		return yeCount;
	}
	public void setYeCount(String yeCount) {
		this.yeCount = yeCount;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamRsCount() {
		return teamRsCount;
	}
	public void setTeamRsCount(String teamRsCount) {
		this.teamRsCount = teamRsCount;
	}
	public String getPnryltime() {
		return pnryltime;
	}
	public void setPnryltime(String pnryltime) {
		this.pnryltime = pnryltime;
	}
	public List<PnrSegmentBean> getSegmentList() {
		return segmentList;
	}
	public void setSegmentList(List<PnrSegmentBean> segmentList) {
		this.segmentList = segmentList;
	}
	public List<PnrPassengerBean> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<PnrPassengerBean> passengerList) {
		this.passengerList = passengerList;
	}
	public String getPnrlx() {
		return pnrlx;
	}
	public void setPnrlx(String pnrlx) {
		this.pnrlx = pnrlx;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPnrnr() {
		return pnrnr;
	}
	public void setPnrnr(String pnrnr) {
		this.pnrnr = pnrnr;
	}
	public String getPatnr() {
		return patnr;
	}
	public void setPatnr(String patnr) {
		this.patnr = patnr;
	}
	public String getTimeofnoseat() {
		return timeofnoseat;
	}
	public void setTimeofnoseat(String timeofnoseat) {
		this.timeofnoseat = timeofnoseat;
	}
}
