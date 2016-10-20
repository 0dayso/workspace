package org.vetech.core.business.pid.pidbean;

import java.util.Map;

public class ParseParam {

	private String returnXml;
	
	private Map<String, String> jkData;

	public Map<String, String> getJkData() {
		return jkData;
	}

	public void setJkData(Map<String, String> jkData) {
		this.jkData = jkData;
	}

	public String getReturnXml() {
		return returnXml;
	}

	public void setReturnXml(String returnXml) {
		this.returnXml = returnXml;
	}
}
