package org.vetech.core.business.pid.pidbean;

import java.io.Serializable;

/**
 * PID接口返回数据JavaBean
 * 
 * @author gengxianyan
 * @version [版本号, Apr 8, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class PidResult implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 4676566302250027410L;

	public static final String SUCCESS = "0";

	public static final String FAILURE = "-1";

	private String jkzt;// 接口状态 0：成功 其他：表示失败

	private String errorMsg;// 错误信息

	private String resultXml = "";// 接口返回原始Xml

	public String getResultXml() {
		return resultXml;
	}

	public void setResultXml(String resultXml) {
		this.resultXml = resultXml;
	}

	public String getJkzt() {
		return jkzt;
	}

	public void setJkzt(String jkzt) {
		this.jkzt = jkzt;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
