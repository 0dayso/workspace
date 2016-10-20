package org.vetech.core.business.pid.api.qte3;

import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class Qte3Param  extends Param {

	private String hkgs;
	private String pnrno;
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	
	/**
	 * 根据传入数据生成接口需要的XML
	 */
	public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEQTE3</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		xml.append(XmlUtils.xmlnode("QTE", "QTE:/"+hkgs));
		xml.append(XmlUtils.xmlnode("PNRNO", pnrno));
		xml.append(XmlUtils.xmlnode("CHILDCMDS", "LOWESTPRICE"));//ALLPRICES、LOWESTPRICE
		xml.append("</PARA>");
		xml.append("</INPUT>");
		return xml.toString();
	}

}
