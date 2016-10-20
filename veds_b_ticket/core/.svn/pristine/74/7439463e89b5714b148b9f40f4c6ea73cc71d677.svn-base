package org.vetech.core.business.pid.api.tknetrdx;

public class VeTkneTrdxResult {
	/**
	 * INPUT节点
	 */
	private VeTkneTrdxInput input;

	/**
	 * 大于0的数表示成功，为0或负数表示失败.
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

	public VeTkneTrdxInput getInput() {
		return input;
	}

	public void setInput(VeTkneTrdxInput input) {
		this.input = input;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public VeTkneTrdxDescription getDescription() {
		return description;
	}

	public void setDescription(VeTkneTrdxDescription description) {
		this.description = description;
	}
}
