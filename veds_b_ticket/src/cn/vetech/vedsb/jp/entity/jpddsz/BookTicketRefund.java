package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.HashMap;
import java.util.Map;

/**
 * 写订单，用的是DBA的接口
 * 
 */
public class BookTicketRefund {
	private final String TFDTAG = "TFD";

	private final String TFDMXTAG = "TFDMX";
	
	private TicketRefundBean tfBean;

	private String ticketRefundXml;
	
	private String tpdh;
	
	private String errmsg;
	
	private Integer result;
	
	private Map<String,Object> param;

	/**
	 * 写订单初始化
	 * 
	 * @param orderBean
	 * @param tmp_service
	 */
	public BookTicketRefund(TicketRefundBean tfBean) {
		this.tfBean = tfBean;
		initMap();
	}
	/**
	 * 组装写订单xml，格式按照DBA来
	 * 
	 */
	private void initMap() {
		param=new HashMap<String, Object>();
		param.put(TFDTAG, tfBean.getTfdMap());
		param.put(TFDMXTAG, tfBean.getTfdMxMap());
	}
	public String getTicketRefundXml() {
		return ticketRefundXml;
	}
	public void setTicketRefundXml(String ticketRefundXml) {
		this.ticketRefundXml = ticketRefundXml;
	}
	public TicketRefundBean getTfBean() {
		return tfBean;
	}
	public void setTfBean(TicketRefundBean tfBean) {
		this.tfBean = tfBean;
	}
	public String getTpdh() {
		return tpdh;
	}
	public void setTpdh(String tpdh) {
		this.tpdh = tpdh;
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
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}


}