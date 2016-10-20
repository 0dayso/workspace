package org.vetech.core.business.pid.api.rtkt;

import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class RtKtParam extends Param {
	/**
	 * 票号格式 例如 123-12345678890或者1231234567890
	 */
	
    private String tkno;
    /**
     * 日期格式DDMMMYY   例如 09DEC16,可选
     */
    private String date;
    
    public String toXml() {
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VERTKT</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", super.getUserid()));
		xml.append(XmlUtils.xmlnode("OFFICE", super.getOfficeId()));
		xml.append(XmlUtils.xmlnode("TICKETNO", tkno));
		xml.append(XmlUtils.xmlnode("DATE", date));
		xml.append("</PARA>");
		xml.append("</INPUT>");
		return xml.toString();
	}
   
    
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
}
