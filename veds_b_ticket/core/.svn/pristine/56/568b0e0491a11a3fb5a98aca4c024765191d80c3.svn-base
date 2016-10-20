package org.vetech.core.business.pid.api.pnrpat3;

import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 支持带协议号的PAT，需要自动选择满足条件的价格，选择后将进行封口，
 * 
 * 实现将协议号做到编码中去,对于南航pnr，如果pnr中不存在对应的RMK项，要将RMK做进编码里面。
 * 
 * @author houya
 * @version [版本号, May 30, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VePnrPat3Command extends Command {
	private String pnrno;
	private String office;
	private String pat;
	private String scny;
	private String pidno;
	private String istest;
	private String persons;
	private String selectlowprice;

	// userid不能为空，如果OFFICE 参数传入为空，那么共享根据该用户的VEETDZ指令的权限确定来选择恰当的pid去进行执行指令。

	public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEPNRPAT3</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		xml.append(XmlUtils.xmlnode("PWD", getPass()));
		xml.append(XmlUtils.xmlnode("OFFICE", office));
		xml.append(XmlUtils.xmlnode("PIDNO", pidno));
		xml.append(XmlUtils.xmlnode("PAT", pat));
		xml.append(XmlUtils.xmlnode("PERSONS", persons));
		xml.append(XmlUtils.xmlnode("SELECTLOWPRICE", selectlowprice));
		xml.append(XmlUtils.xmlnode("SCNY", scny));
		xml.append(XmlUtils.xmlnode("ISTEST", istest));
		xml.append("</PARA>");
		xml.append("</INPUT>");
		return xml.toString();
	}

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getPat() {
		return pat;
	}

	public void setPat(String pat) {
		this.pat = pat;
	}

	public String getScny() {
		return scny;
	}

	public void setScny(String scny) {
		this.scny = scny;
	}

	public String getPidno() {
		return pidno;
	}

	public void setPidno(String pidno) {
		this.pidno = pidno;
	}

	public String getIstest() {
		return istest;
	}

	public void setIstest(String istest) {
		this.istest = istest;
	}

	public String getSelectlowprice() {
		return selectlowprice;
	}

	public void setSelectlowprice(String selectlowprice) {
		this.selectlowprice = selectlowprice;
	}

	public String getPersons() {
		return persons;
	}

	public void setPersons(String persons) {
		this.persons = persons;
	}

}
