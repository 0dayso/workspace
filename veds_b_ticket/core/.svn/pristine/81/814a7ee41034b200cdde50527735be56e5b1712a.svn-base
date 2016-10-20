package org.vetech.core.modules.exception;

public enum SystemErrorCode implements Code {
	SystemErrorCode("SYSTEM_ERROR", "系统异常%s"),
	ParamError("PARAM_ERROR", "参数错误%s")
	;
	/**
	 * 错误代码
	 */
	private String code;

	/**
	 * 错误信息
	 */
	private String message;

	/**
	 * 构造
	 * 
	 * @param code
	 *            错误代码
	 * @param message
	 *            错误信息
	 */
	private SystemErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
