package org.vetech.core.business.pid.api.tknetrdx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 白屏改签指令响应结果参数
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
@XmlRootElement(name = "RESULT")
public class VeTkneTrdxResponse {

	/**
	 * INPUT节点
	 */
	private VeTkneTrdxInput input;

	/**
	 * 大于等于0的数表示成功，负数表示失败.
	 */
	private int status;
	
	/**
	 * 所有出错时的错误提示信息
	 */
	private String errorInfo;

	/**
	 * 当成功的时候，DESCRIPTION中的信息就是航信返回的最终执行结果；失败的时候，该信息指明了失败原因
	 */
	private VeTkneTrdxDescription description;

	@XmlElement(name = "INPUT")
	public VeTkneTrdxInput getInput() {
		return input;
	}

	public void setInput(VeTkneTrdxInput input) {
		this.input = input;
	}

	@XmlElement(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@XmlElement(name = "ERRORINFO")
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	@XmlElement(name = "DESCRIPTION")
	public VeTkneTrdxDescription getDescription() {
		return description;
	}

	public void setDescription(VeTkneTrdxDescription description) {
		this.description = description;
	}
}
