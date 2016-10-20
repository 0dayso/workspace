package cn.vetech.vedsb.utils.zdcp;

import org.apache.commons.lang.StringUtils;

public class AutoCpException extends RuntimeException{
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1342289958957291110L;

	private int code;
	
	private AutoCpErrorCode errorcode;
	
	public AutoCpErrorCode getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(AutoCpErrorCode errorcode) {
		this.errorcode = errorcode;
	}

	public AutoCpException(String message,AutoCpErrorCode errorcode) {
		super(StringUtils.trimToEmpty(message));
		this.errorcode = errorcode;
	}

	public AutoCpException(Exception e,AutoCpErrorCode errorcode) {
		super(e);
		this.errorcode = errorcode;
	}
	/**
	 * -1 保留
	 * 
	 * -9 网络通讯异常
	 * 
	 * -10 PID提取大编码错误
	 * 
	 * -15 登陆错误
	 * 
	 * -20 提取政策
	 * 
	 * -25 不符合最小利润 
	 * 
	 * -30 入库错误  30 入库成功
	 * 
	 * -35 支付错误  35 支付成功
	 *  
	 * -40 转机票  0 转机票成功
	 * 
	 * -50 校验自动出票表失败
	 */
	public AutoCpException(int code, String message,AutoCpErrorCode errorcode) {
		super(StringUtils.trimToEmpty(message));
		this.code = code;
		this.errorcode = errorcode;
	}
	public AutoCpException(int code, String message) {
		super(StringUtils.trimToEmpty(message));
		this.code = code;
	}
	public AutoCpException(int code, Exception e,AutoCpErrorCode errorcode) {
		super(e);
		this.code = code;
		this.errorcode = errorcode;
	}
	public AutoCpException(String message){
		super(StringUtils.trimToEmpty(message));
	}
	public int getCode() {
		return code;
	}
}
