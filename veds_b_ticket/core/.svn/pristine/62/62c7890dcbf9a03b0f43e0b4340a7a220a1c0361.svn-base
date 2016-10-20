package org.vetech.core.business.pid.api.devpay;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.Param;
import org.vetech.core.modules.utils.XmlUtils;

public class DevpayParam extends Param{
	private String childcmd;
	
	private String office;//可选
	public String getChildcmd() {
		return childcmd;
	}

	public void setChildcmd(String childcmd) {
		this.childcmd = childcmd;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}
	
	public String toxml(){
		StringBuffer xml = new StringBuffer("<INPUT>");
		xml.append("<COMMAND>VEDEVPAY</COMMAND>");
		xml.append("<PARA>");
		xml.append(XmlUtils.xmlnode("USER", getUserid()));
		if (StringUtils.isNotBlank(office)) {
			xml.append(XmlUtils.xmlnode("OFFICE", office));
		}
		xml.append(XmlUtils.xmlnode("CHILDCMD", childcmd));
		xml.append("</PARA>");
		xml.append("</INPUT>");
		
		return xml.toString();
	}
}
