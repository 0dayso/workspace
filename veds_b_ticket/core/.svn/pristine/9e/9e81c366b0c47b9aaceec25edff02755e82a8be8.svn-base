package org.vetech.core.business.pid.api.tknetrdx;

import javax.xml.bind.annotation.XmlElement;

/**
 * 白屏改签指令响应结果参数INPUT节点
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxInput {

	/**
	 * 原始PNR编码
	 */
	private String pnrno;

	/**
	 * TKNE或者TRDX
	 */
	private String type;

	/**
	 * 原PNR是否有效 0或1
	 */
	private String pnrValid;

	/**
	 * PNR是否被保留 0或1
	 */
	private String pnrRemain;

	@XmlElement(name = "PNRNO")
	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	@XmlElement(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "PNRVALID")
	public String getPnrValid() {
		return pnrValid;
	}

	public void setPnrValid(String pnrValid) {
		this.pnrValid = pnrValid;
	}
	
	@XmlElement(name = "PNRREMAIN")
	public String getPnrRemain() {
		return pnrRemain;
	}

	public void setPnrRemain(String pnrRemain) {
		this.pnrRemain = pnrRemain;
	}

}
