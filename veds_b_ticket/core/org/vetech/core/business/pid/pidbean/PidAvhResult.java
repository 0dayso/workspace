package org.vetech.core.business.pid.pidbean;

import java.util.List;
import java.util.Map;

/**
 * AVH查询结果Bean
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class PidAvhResult extends PidResult {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 3747537170287786489L;

	/**
	 * 指令集合
	 */
	private Map<String, String> commandMap;
	
	/**
	 * 航班集合
	 */
	private List<TicketFlight> flightList;//
	
	/**
	 * 航班号，多个以空格隔开(不带*)
	 */
	private String flightNos;//
	/**
	 * 所有航班的舱位，以舱位二字码拼接，多个以空格隔开（去重复）
	 */
	private String cabins;// 
	/**
	 * 航空公司字符串
	 */
	private String hkgss;
	
	

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

	public String getHkgss() {
		return hkgss;
	}

	public void setHkgss(String hkgss) {
		this.hkgss = hkgss;
	}

	public Map<String, String> getCommandMap() {
		return commandMap;
	}

	public void setCommandMap(Map<String, String> commandMap) {
		this.commandMap = commandMap;
	}

	public List<TicketFlight> getFlightList() {
		return flightList;
	}

	public void setFlightList(List<TicketFlight> flightList) {
		this.flightList = flightList;
	}
}
