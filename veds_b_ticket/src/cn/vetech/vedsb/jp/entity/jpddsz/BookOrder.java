package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.utils.VeStr;

/**
 * 写订单，用的是DBA的接口
 * 
 */
public class BookOrder {
	public final static String CJRTAG = "CJRDATA";

	public final static String HDTAG = "HD";
                 
	public final static String KHDDTAG = "KHDD";
                 
	public final static String ROOTTAG = "SQL";
                 
	public final static String RETURNDATA = "RETURNDATA";
                 
	public final static String returnS = "DDBH";
	
	private OrderBean orderBean;

	private String orderXml;
	
	private String ddbh;
	
	private String errmsg;
	
	private Integer result;
	
	private Map<String,Object> param;

	public BookOrder() {}
	
	/**
	 * 写订单初始化
	 * 
	 * @param orderBean
	 * @param tmp_service
	 */
	public BookOrder(OrderBean orderBean) {
		this.orderBean = orderBean;
		initXml();
	}
	/**
	 * 组装写订单xml，格式按照DBA来
	 * 
	 */
	private void initXml() {
		//String khddXml = XmlUtils.toXml(orderBean.getKhddMap(), KHDDTAG);
		//String cjrXml = XmlUtils.toXml(orderBean.getCjrList(), CJRTAG);
		//String hdXml = XmlUtils.toXml(orderBean.getHdList(), HDTAG);
		//this.orderXml = BookOrder.getInsertXml(khddXml, cjrXml, hdXml);
		param=new HashMap<String, Object>();
		String cpcs=VeStr.getValue(orderBean.getKhddMap(), "CPCS");
		if(StringUtils.isBlank(cpcs) && orderBean.getKhddMap() == null){
			orderBean.getKhddMap().put("CPCS", "0");
		}
		param.put(KHDDTAG, orderBean.getKhddMap());
		param.put(CJRTAG, orderBean.getCjrList());
		param.put(HDTAG, orderBean.getHdList());
	}
	
	/**
	 * 根据订单，乘机人及航段XML拼接调用订单入库XML
	 * @param khddXml
	 * @param cjrXml
	 * @param hdXml
	 * @return
	 */
	public static String getInsertXml(String khddXml, String cjrXml, String hdXml){
		StringBuffer xml = new StringBuffer(512);
		xml.append("<").append(ROOTTAG).append(">").append(khddXml).append(cjrXml).append(hdXml).append("<").append(RETURNDATA).append(">")
		.append(returnS).append("</").append(RETURNDATA).append(">").append("</").append(ROOTTAG).append(">");
		return xml.toString();
	}
	/**
	 * 
	 * @return 写订单xml
	 */
	public String getOrderXml() {
		return orderXml;
	}
	public void setOrderXml(String orderXml) {
		this.orderXml = orderXml;
	}
	public OrderBean getOrderBean() {
		return orderBean;
	}
	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
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