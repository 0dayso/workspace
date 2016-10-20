package cn.vetech.vedsb.platpolicy.cps.response.pay;

import javax.xml.bind.annotation.XmlElement;

/**
 * *	<Status>" +
	 *		<StatusCode>OK</StatusCode>" +
	 *		<StatusMessage></StatusMessage>" +
	 *		<StatusDesc>getShzhglList</StatusDesc>" +
	 *	</Status>
 * @author win7
 *
 */
public class CpsZhStatus {
	private String statusCode;
	private String statusMessage;
	private String statusDesc;
	
	@XmlElement(name="StatusCode")
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	@XmlElement(name="StatusMessage")
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	@XmlElement(name="StatusDesc")
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
