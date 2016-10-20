package org.vetech.core.business.pid.pidbean;

import java.util.List;

public class ParseResult {
	private String cfdate;
	private String afterParseStr;
	private List<TicketFlight> flightList;
	
	private String flightNos;//航班号，多个以空格隔开(不带*)
	private String cabins;// 所有航班的舱位，以舱位二字码拼接，多个以空格隔开（去重复）
	private String hkgss;
	
	
	public String getHkgss() {
		return hkgss;
	}

	public void setHkgss(String hkgss) {
		this.hkgss = hkgss;
	}

	public String getAfterParseStr() {
		return afterParseStr;
	}

	public String getCfdate() {
		return cfdate;
	}

	public void setCfdate(String cfdate) {
		this.cfdate = cfdate;
	}



	public List<TicketFlight> getFlightList() {
		return flightList;
	}



	public void setFlightList(List<TicketFlight> flightList) {
		this.flightList = flightList;
	}



	public void setAfterParseStr(String afterParseStr) {
		this.afterParseStr = afterParseStr;
	}

	public String getFlightNos() {
		return flightNos;
	}

	public void setFlightNos(String flightNos) {
		this.flightNos = flightNos;
	}

	public String getCabins() {
		return cabins;
	}

	public void setCabins(String cabins) {
		this.cabins = cabins;
	}
}