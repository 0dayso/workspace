package org.vetech.core.business.pid.api.detrxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TknoInfo")
public class TknoInfo {
	private String tkno;
	private String pnrNo;
	private String bigPnrNo;
	private String office;
	private String pjXsj;
	private String pjTax;
	private String pjJsf;
	private String pjTotal;
	private String ei;
	private String name;
	private String ni;
	private String foidtype;
	private String cpzd;
	private String spc;
	private String cphkgs;
	private String lxkp;
	private String ticketType;
	private String gp;
	
	@XmlElement(name="Tkno")
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	@XmlElement(name="PnrNo")
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	@XmlElement(name="BigPnrNo")
	public String getBigPnrNo() {
		return bigPnrNo;
	}
	public void setBigPnrNo(String bigPnrNo) {
		this.bigPnrNo = bigPnrNo;
	}
	
	@XmlElement(name="Office")
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	@XmlElement(name="PjXsj")
	public String getPjXsj() {
		return pjXsj;
	}
	public void setPjXsj(String pjXsj) {
		this.pjXsj = pjXsj;
	}
	@XmlElement(name="PjTax")
	public String getPjTax() {
		return pjTax;
	}
	public void setPjTax(String pjTax) {
		this.pjTax = pjTax;
	}
	@XmlElement(name="PjJsf")
	public String getPjJsf() {
		return pjJsf;
	}
	public void setPjJsf(String pjJsf) {
		this.pjJsf = pjJsf;
	}
	@XmlElement(name="PjTotal")
	public String getPjTotal() {
		return pjTotal;
	}
	public void setPjTotal(String pjTotal) {
		this.pjTotal = pjTotal;
	}
	@XmlElement(name="Ei")
	public String getEi() {
		return ei;
	}
	public void setEi(String ei) {
		this.ei = ei;
	}
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement(name="Ni")
	public String getNi() {
		return ni;
	}
	public void setNi(String ni) {
		this.ni = ni;
	}
	@XmlElement(name="FOIDTYPE")
	public String getFoidtype() {
		return foidtype;
	}
	public void setFoidtype(String foidtype) {
		this.foidtype = foidtype;
	}
	@XmlElement(name="Cpzd")
	public String getCpzd() {
		return cpzd;
	}
	public void setCpzd(String cpzd) {
		this.cpzd = cpzd;
	}
	@XmlElement(name="Spc")
	public String getSpc() {
		return spc;
	}
	public void setSpc(String spc) {
		this.spc = spc;
	}
	
	@XmlElement(name="Cphkgs")
	public String getCphkgs() {
		return cphkgs;
	}
	public void setCphkgs(String cphkgs) {
		this.cphkgs = cphkgs;
	}
	@XmlElement(name="Lxkp")
	public String getLxkp() {
		return lxkp;
	}
	public void setLxkp(String lxkp) {
		this.lxkp = lxkp;
	}
	@XmlElement(name="TicketType")
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	
	@XmlElement(name="GP")
	public String getGp() {
		return gp;
	}
	public void setGp(String gp) {
		this.gp = gp;
	}
	
}
