package org.vetech.core.business.pid.api.xeflight;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class XeFlightParam extends Param{
	private String pnrno;//不能为空,PNR编码
	
	/*
	 * 可选，如果指定了，XE将在属于该office的pid上执行
	 */
	private String office;
	
	private String allowxepnr;//在该标记为1的情况下，如果所有航段都需要被XE掉，那么就会XE掉整个PNR。
	
	/**航班号,出发城市,到达城市,出发时间，仓位|…</HD> 
	 * 指定航段信息，如果有多个航段，中间用’|’分隔开。
	 * 每个航段信息包括航班号,出发城市、到达城市、出发时间和仓位，
	 * 中间用‘,’分隔开,时间格式是YYYY-MM-DD。如果IGNOREHD节点的值为1，
	 * 那么该节点可以取消。*/
	private String hd;
	
	/**
	 * 可选,如果指定，那么在XE航段后，会同时预定上这个航段并封口。如果没有或为空，那么仅仅执行XE航段动作。
	 * */
	private String ss;

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getAllowxepnr() {
		return allowxepnr;
	}

	public void setAllowxepnr(String allowxepnr) {
		this.allowxepnr = allowxepnr;
	}

	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}
	
	public String toxml(){
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEXEFLIGHT</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		if (StringUtils.isNotBlank(office)) {
			xml.append(XmlUtils.xmlnode("OFFICE", office));
		}
		xml.append(XmlUtils.xmlnode("ALLOWXEPNR", "0"));//xe航段
		xml.append(XmlUtils.xmlnode("HD", hd));
		xml.append(XmlUtils.xmlnode("SS", ss));
		xml.append("</PARA>");
		xml.append("</INPUT>");
		
		return xml.toString();
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
}
