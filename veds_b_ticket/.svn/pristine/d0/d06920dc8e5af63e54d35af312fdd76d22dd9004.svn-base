package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.HashMap;
import java.util.Map;

/**
 * 写订单，用的是DBA的接口
 * 
 */
public class BookTicketChange {
	public final static String GQDTAG = "GQD";

	public final static String GQDMXTAG = "GQDMX";
	
	public final static String GQDHDTAG = "HD";

	public final static String ROOTTAG = "SQL";
	
	private TicketChangeBean tcBean;
	
	private Map<String,Object> param;

	private String p_xml;
	
	private String gqdh;
	
	private String errmsg;
	
	private Integer result;
	/**
	 * 
	 * 写订单初始化
	 * 
	 * @param orderBean
	 * @param tmp_service
	 */
	public BookTicketChange(TicketChangeBean tcBean) {
		this.tcBean = tcBean;
		initMap();
	}
	/**
	 * 组装写订单xml，格式按照DBA来
	 * 
	 */
	private void initMap() {
		param = new HashMap<String, Object>();
		param.put(GQDTAG, tcBean.getGqdMap());
		param.put(GQDMXTAG, tcBean.getGqdMxMapList());
		param.put(GQDHDTAG, tcBean.getHdMapList());
	}
	
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	public String getP_xml() {
		return p_xml;
	}
	public void setP_xml(String p_xml) {
		this.p_xml = p_xml;
	}
	public String getGqdh() {
		return gqdh;
	}
	public void setGqdh(String gqdh) {
		this.gqdh = gqdh;
	}
	public TicketChangeBean getTcBean() {
		return tcBean;
	}
	public void setTcBean(TicketChangeBean tcBean) {
		this.tcBean = tcBean;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	
}