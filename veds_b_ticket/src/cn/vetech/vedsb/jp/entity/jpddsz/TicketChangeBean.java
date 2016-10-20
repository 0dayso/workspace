package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.List;
import java.util.Map;

public class TicketChangeBean {
	private List<Map<String, Object>> gqdMxMapList;
	private List<Map<String, Object>> hdMapList;
	private Map<String, Object> gqdMap;
	public List<Map<String, Object>> getGqdMxMapList() {
		return gqdMxMapList;
	}
	public void setGqdMxMapList(List<Map<String, Object>> gqdMxMapList) {
		this.gqdMxMapList = gqdMxMapList;
	}
	public List<Map<String, Object>> getHdMapList() {
		return hdMapList;
	}
	public void setHdMapList(List<Map<String, Object>> hdMapList) {
		this.hdMapList = hdMapList;
	}
	public Map<String, Object> getGqdMap() {
		return gqdMap;
	}
	public void setGqdMap(Map<String, Object> gqdMap) {
		this.gqdMap = gqdMap;
	}
	
	
}
