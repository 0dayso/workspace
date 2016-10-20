package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.beanutils.BeanUtils;
import org.vetech.core.modules.utils.XmlUtils;

/**
 * 
 * DETR接口返回XML子节点Segment
 * 
 * @author lkh
 * @version [版本号, 2013-8-29]
 * @see [相关类/方法]
 * @since [CPS]
 */
public class Segment {
	/**
	 * 出发城市
	 */
	private String cfcs;

	/**
	 * 到达城市
	 */
	private String ddcs;

	/**
	 * 航班号
	 */
	private String hbh;

	/**
	 * 仓位
	 */
	private String cw;

	/**
	 * 仓位级别
	 */
	private String cwjb;

	/**
	 * 出发日期 2015-05-02 17:05:00
	 */
	private String cfrq;

	/**
	 * 行李
	 */
	private String xl;

	/**
	 * 客票状态
	 */
	private String kpzt;

	/**
	 * 飞机机型
	 */
	private String fjjx;

	@XmlElement(name = "depCity")
	public String getCfcs() {
		return cfcs;
	}

	public void setCfcs(String cfcs) {
		this.cfcs = cfcs;
	}

	@XmlElement(name = "arrCity")
	public String getDdcs() {
		return ddcs;
	}

	public void setDdcs(String ddcs) {
		this.ddcs = ddcs;
	}

	@XmlElement(name = "flightNo")
	public String getHbh() {
		return hbh;
	}

	public void setHbh(String hbh) {
		this.hbh = hbh;
	}

	@XmlElement(name = "seatClass")
	public String getCw() {
		return cw;
	}

	public void setCw(String cw) {
		this.cw = cw;
	}

	@XmlElement(name = "seatClassGrade")
	public String getCwjb() {
		return cwjb;
	}

	public void setCwjb(String cwjb) {
		this.cwjb = cwjb;
	}

	@XmlElement(name = "depDatetime")
	public String getCfrq() {
		return cfrq;
	}

	public void setCfrq(String cfrq) {
		this.cfrq = cfrq;
	}

	@XmlElement(name = "baggage")
	public String getXl() {
		return xl;
	}

	public void setXl(String xl) {
		this.xl = xl;
	}

	@XmlElement(name = "ticketStatus")
	public String getKpzt() {
		return kpzt;
	}

	public void setKpzt(String kpzt) {
		this.kpzt = kpzt;
	}

	@XmlElement(name = "planeModel")
	public String getFjjx() {
		return fjjx;
	}

	public void setFjjx(String fjjx) {
		this.fjjx = fjjx;
	}
	
	public String toXmlString(){
		StringBuffer khddXml = new StringBuffer();
		Class<?> _class = this.getClass();
		Field[] fields = _class.getDeclaredFields();
		try{
			for (Field f : fields) {
				Class<?> c = f.getType();
				String typeName = c.getSimpleName();
				if ("String".equals(typeName)) {
					String name = f.getName();
					String value = BeanUtils.getProperty(this, name);
				    if (null != value) {
						khddXml.append(XmlUtils.xmlnode(name.substring(0,1).toUpperCase()+name.substring(1), value));
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return khddXml.toString();
	}
}
