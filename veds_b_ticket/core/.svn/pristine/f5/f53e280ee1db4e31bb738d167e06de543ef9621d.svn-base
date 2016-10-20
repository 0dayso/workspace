package org.vetech.core.business.pid.api.rtpnr;

import javax.servlet.http.HttpServletRequest;

import org.vetech.core.business.pid.api.Param;

public class PnrRtParam extends Param {
	private String pnrno;
	/**
	 * 大编码
	 */
	private String bigpnrno;
	/**
	 * 大编或小编内容
	 */
	private String pnrnr;
	/**
	 * 航班号
	 */
	private String hbh;
	/**
	 * 起飞日期
	 */
	private String cfdate;
	private String ip;
	private String port;
	/**
	 * 用户编号
	 */
	private String bh;
	private String office;
	/**
	 * PAT指令
	 */
	private String pat;
	/**
	 * 调用哪种RT接口  1:ParsePnr 2:RRT 3:RTXPNR 4:PNRPAT2  其他:RT  或者接口名称
	 */
	private String methodType;
	private HttpServletRequest request;
	
	/**
	 * 新版接口，通过传入一个Xml进行接口调用
	 */
	private String inputXml;
	
	/*******返回参数*******/
	/**
	 * 当PID返回信息中R节点为0时(PNR返回失败),Pnr对象是否需要直接抛出异常, 1:需要,直接抛出异常 其他值或空：返回null,同时将错误信息记录到returnnullType；
	 * 程序异常直接抛出
	 */
	private String ifthrowexception; 
	/**
	 * PNR返回失败时的分类  1.需要授权 2 NOPNR  0或空 其他
	 */
	private String returnnullType; 
	/**
	 * PNR返回失败时PNR的内容
	 */
	private String returnnullContent; 
	 
	@Override
	public String toString() {
		StringBuffer string=new StringBuffer("传入的参数pnrno["+pnrno+"]");
		string.append(",bigpnrno["+bigpnrno+"]");
		string.append(",pnrnr["+pnrnr+"]");
		string.append(",hbh["+hbh+"]");
		string.append(",ip["+ip+"]");
		string.append(",port["+port+"]");
		string.append(",bh["+bh+"]");
		string.append(",office["+office+"]");
		string.append(",pat["+pat+"]");
		string.append(",methodType["+methodType+"]");
		return string.toString();
	}
	
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public String getPnrnr() {
		return pnrnr;
	}
	public void setPnrnr(String pnrnr) {
		this.pnrnr = pnrnr;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getBigpnrno() {
		return bigpnrno;
	}
	public void setBigpnrno(String bigpnrno) {
		this.bigpnrno = bigpnrno;
	}
	public String getHbh() {
		return hbh;
	}
	public void setHbh(String hbh) {
		this.hbh = hbh;
	}
	public String getCfdate() {
		return cfdate;
	}
	public void setCfdate(String cfdate) {
		this.cfdate = cfdate;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	
	
	public String getReturnnullType() {
		return returnnullType;
	}
	
	public void setReturnnullType(String returnnullType) {
		this.returnnullType = returnnullType;
	}
	
	public String getReturnnullContent() {
		return returnnullContent;
	}
	
	public void setReturnnullContent(String returnnullContent) {
		this.returnnullContent = returnnullContent;
	}
	
	public String getIfthrowexception() {
		return ifthrowexception;
	}
	
	public void setIfthrowexception(String ifthrowexception) {
		this.ifthrowexception = ifthrowexception;
	}
	
	public String getInputXml() {
		return inputXml;
	}
	
	public void setInputXml(String inputXml) {
		this.inputXml = inputXml;
	}
}
