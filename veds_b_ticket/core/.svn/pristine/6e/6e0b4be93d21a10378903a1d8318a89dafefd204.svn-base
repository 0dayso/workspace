package org.vetech.core.business.pid.api.tknetrdx;

import javax.xml.bind.annotation.XmlElement;

/**
 * 白屏改签指令响应结果参数PNR_XXX节点
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxPnr {

	/**
	 * pnrBook对应 1表示改签都成功,0表示改签成功但解析PNR失败,-1表示改签失败
	 * 
	 * 其他对应 1，成功；-1，失败
	 */
	private String flag;

	/**
	 * 错误信息描述
	 */
	private String error;

	/**
	 * pnrXepnr 错误信息描述
	 */
	private String info;

	/**
	 * PNR的XML信息表述
	 */
	private VeTkneTrdxPnrXml pnrXml;
	
	/**
	 * SP PNR时表示为母编码或子编码
	 * 可为: MOTHER 或 CHILD
	 */
	private String type;

	@XmlElement(name = "FLAG")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@XmlElement(name = "ERROR")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@XmlElement(name = "PNRXML")
	public VeTkneTrdxPnrXml getPnrXml() {
		return pnrXml;
	}

	public void setPnrXml(VeTkneTrdxPnrXml pnrXml) {
		this.pnrXml = pnrXml;
	}

	@XmlElement(name = "INFO")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@XmlElement(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
