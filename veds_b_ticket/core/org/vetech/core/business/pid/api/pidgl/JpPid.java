package org.vetech.core.business.pid.api.pidgl;


import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * <PID配置管理实体类>
 * <功能详细描述>
 * 
 * @author  win7
 * @version  [版本号, Apr 21, 2016]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class JpPid {
	@XmlElement(name="NO")
	private String no;//配置编号(从0开始)
	@XmlElement(name="LocalIP")
	private String localip;//本地IP
	@XmlElement(name="LocalPort")
	private String localport;//本地端口
	@XmlElement(name="ServerIP")
	private String serverip;//远程IP地址
	@XmlElement(name="ServerPort")
	private String serverport;//远程端口
	@XmlElement(name="SI")
	private String si;//SI信息
	@XmlElement(name="AutoLink")
	private String autolink;//是否自动连接,0或1
	@XmlElement(name="AutoLogin")
	private String autologin;//是否自动登陆,0或1
	@XmlElement(name="SaflyTransmited")
	private String saflytransmited;//是否安全传输,0或1
	@XmlElement(name="UserName")
	private String username;//用户名
	@XmlElement(name="Password")
	private String password;//口令
	@XmlElement(name="PIDZID")
	private String pidzid;//PID分组ID
	@XmlElement(name="PIDID")
	private String pidid;//PID在组中的ID
	private String sspidz;
	@XmlElement(name="Remark")
	private String remark;//备注
	@XmlElement(name="WebPID")
	private String webpid;//是否是WEB pid,0或1
	@XmlElement(name="WebPidType")
	private String webpidtype;//是web还是web2
	@XmlElement(name="IPE")
	private String ipe;//是IPE吗? 0或1
	@XmlElement(name="BigPID")
	private String bigpid;//是大配置吗? 0或1
	@XmlElement(name="FalsePID")
	private String falsepid;//是胜意配置? 0或1
	@XmlElement(name="InputCheckCode")
	private String inputcheckcode;//需要手工输入验证码吗? 0或1
	@XmlElement(name="CanTSL")
	private String cantsl;//能导票吗? 0或1
	@XmlElement(name="CanPRINV")
	private String canprinv;//是否允许打印行程单?0或1
	@XmlElement(name="HasTransactionWait")
	private String hastransactionwait;//设置指令间隔吗? 0或1
	@XmlElement(name="CmdSendingInterval")
	private String cmdsendinginterval;//指令间隔时间,以毫秒作为单位
	@XmlElement(name="FlowLimit")
	private String flowlimit;//该配置能使用的最大流量,默认值是0，表示没有限制
	@XmlElement(name="PrintersCount")
	private String printerscount;//描述了该配置上设置了多少台打印机
	@XmlElement(name="NotAllowedAirlines")
	private String notallowedairlines;//不支持航班查询的航空公司逗号分隔列表,可以为空
	@XmlElement(name="PIDConnectionState")
	private String pidconnectionstate;//pid的连接状态，包括连接、断开、在连接中…
	
	@XmlElement(name="AuthenticationType")
	private String authenticationtype;
	
	@XmlElement(name="Office")
	private String office ;
	private String printno;
	
	@XmlElement(name="Stop")
	private String stop;
	
	private String sfmr;
	
	
	public String getSfmr() {
		return sfmr;
	}
	public void setSfmr(String sfmr) {
		this.sfmr = sfmr;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public String getPrintno() {
		return printno;
	}
	public void setPrintno(String printno) {
		this.printno = printno;
	}
	public String getAuthenticationtype() {
		return authenticationtype;
	}
	public void setAuthenticationtype(String authenticationtype) {
		this.authenticationtype = authenticationtype;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getSspidz() {
		return sspidz;
	}
	public void setSspidz(String sspidz) {
		this.sspidz = sspidz;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getLocalip() {
		return localip;
	}
	public void setLocalip(String localip) {
		this.localip = localip;
	}
	public String getLocalport() {
		return localport;
	}
	public void setLocalport(String localport) {
		this.localport = localport;
	}
	public String getServerip() {
		return serverip;
	}
	public void setServerip(String serverip) {
		this.serverip = serverip;
	}
	public String getServerport() {
		return serverport;
	}
	public void setServerport(String serverport) {
		this.serverport = serverport;
	}
	public String getSi() {
		return si;
	}
	public void setSi(String si) {
		this.si = si;
	}
	public String getAutolink() {
		return autolink;
	}
	public void setAutolink(String autolink) {
		this.autolink = autolink;
	}
	public String getAutologin() {
		return autologin;
	}
	public void setAutologin(String autologin) {
		this.autologin = autologin;
	}
	public String getSaflytransmited() {
		return saflytransmited;
	}
	public void setSaflytransmited(String saflytransmited) {
		this.saflytransmited = saflytransmited;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPidzid() {
		return pidzid;
	}
	public void setPidzid(String pidzid) {
		this.pidzid = pidzid;
	}
	public String getPidid() {
		return pidid;
	}
	public void setPidid(String pidid) {
		this.pidid = pidid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWebpid() {
		return webpid;
	}
	public void setWebpid(String webpid) {
		this.webpid = webpid;
	}
	public String getWebpidtype() {
		return webpidtype;
	}
	public void setWebpidtype(String webpidtype) {
		this.webpidtype = webpidtype;
	}
	public String getIpe() {
		return ipe;
	}
	public void setIpe(String ipe) {
		this.ipe = ipe;
	}
	public String getBigpid() {
		return bigpid;
	}
	public void setBigpid(String bigpid) {
		this.bigpid = bigpid;
	}
	public String getFalsepid() {
		return falsepid;
	}
	public void setFalsepid(String falsepid) {
		this.falsepid = falsepid;
	}
	public String getInputcheckcode() {
		return inputcheckcode;
	}
	public void setInputcheckcode(String inputcheckcode) {
		this.inputcheckcode = inputcheckcode;
	}
	public String getCantsl() {
		return cantsl;
	}
	public void setCantsl(String cantsl) {
		this.cantsl = cantsl;
	}
	public String getCanprinv() {
		return canprinv;
	}
	public void setCanprinv(String canprinv) {
		this.canprinv = canprinv;
	}
	public String getHastransactionwait() {
		return hastransactionwait;
	}
	public void setHastransactionwait(String hastransactionwait) {
		this.hastransactionwait = hastransactionwait;
	}
	public String getCmdsendinginterval() {
		return cmdsendinginterval;
	}
	public void setCmdsendinginterval(String cmdsendinginterval) {
		this.cmdsendinginterval = cmdsendinginterval;
	}
	public String getFlowlimit() {
		return flowlimit;
	}
	public void setFlowlimit(String flowlimit) {
		this.flowlimit = flowlimit;
	}
	public String getPrinterscount() {
		return printerscount;
	}
	public void setPrinterscount(String printerscount) {
		this.printerscount = printerscount;
	}
	public String getNotallowedairlines() {
		return notallowedairlines;
	}
	public void setNotallowedairlines(String notallowedairlines) {
		this.notallowedairlines = notallowedairlines;
	}
	public String getPidconnectionstate() {
		return pidconnectionstate;
	}
	public void setPidconnectionstate(String pidconnectionstate) {
		this.pidconnectionstate = pidconnectionstate;
	}
	
	
}

	
