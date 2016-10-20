package org.vetech.core.business.pid.api.tknetrdx;

import javax.xml.bind.annotation.XmlElement;

/**
 * 白屏改签指令响应结果参数PNRXML节点
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxPnrXml {

	private String pnrno;

	private String pnrContent;

	private String bigPnrno;

	private String c;

	private String tl;

	private String office;

	private String auth;

	private String hd;

	private String cjr;

	@XmlElement(name = "PNRNO")
	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	@XmlElement(name = "PNRCONTENT")
	public String getPnrContent() {
		return pnrContent;
	}

	public void setPnrContent(String pnrContent) {
		this.pnrContent = pnrContent;
	}

	@XmlElement(name = "BIGPNRNO")
	public String getBigPnrno() {
		return bigPnrno;
	}

	public void setBigPnrno(String bigPnrno) {
		this.bigPnrno = bigPnrno;
	}

	@XmlElement(name = "C")
	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	@XmlElement(name = "TL")
	public String getTl() {
		return tl;
	}

	public void setTl(String tl) {
		this.tl = tl;
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
	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	@XmlElement(name = "CJR")
	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	// <PNRXML><PNRNO>JMBCNQ</PNRNO><PNRCONTENT>1.李克辉 JMBCNQ
	// 2. CZ6585 Y TU17DEC WUHPEK RR1 1940 2145 E --T2
	// 3.CGO/T CGO/T 0371-68266666/CGO HONG XU AIR SERVICE CO.,LTD/LI WAN HAI
	// ABCDEFG
	// 4.BY OPT XYJADMIN 2013/11/20 1048A
	// 5.T/7812364532123/P1
	// 6.SSR FOID CZ HK1 NI430781198204221036/P1
	// 7.OSI CZ CTCT13148732433
	// 8.RMK CA/NC6TMJ
	// 9.CGO231</PNRCONTENT><BIGPNRNO>NC6TMJ</BIGPNRNO><C>CGO231,1,,,,,,0,1,0</C><TL></TL><OFFICE>CGO231</OFFICE><AUTH></AUTH>
	// <HD>1|0,CZ6585,Y,17DEC,WUH,PEK,RR,2013-12-17 19:40:00,2013-12-17 21:45:00,738,,-T2,1,</HD>
	// <CJR>1|0,李克辉,430781198204221036,NI,1,,,,,,,,</CJR></PNRXML>
}
