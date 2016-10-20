package org.vetech.core.business.pid.api.rtkt;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "RTKT")
public class RtKtAgentFee {
	
	/**
	 * 代理费
	 */
	private String agentfee;

	@XmlElement(name = "AGENTFEE")
	public String getAgentfee() {
		return agentfee;
	}

	public void setAgentfee(String agentfee) {
		this.agentfee = agentfee;
	}

}
