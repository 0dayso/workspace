package org.vetech.core.business.pid.api.trfd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TRFD提交模式返回对象
 * @author wangshengliang
 *
 */
@XmlRootElement(name = "TRFD")
public class TrfdSubmitResult{
	
	/**
	 * 操作类型
	 */
	private String operation;

	/**
	 * 1 表示以预览模式调用接口，0 表示命令提交模式
	 */
	private String preview;
	/**
	 * 成功标识，0 表示失败，1 表示成功
	 */
	private String sucessed;

	/**
	 * 命令序列中第一条指令
	 */
	private String cmd1;

	/**
	 * 第一条指令结果
	 */
	private String result1;
	
	
	private TrfdResult result1xml;

	/**
	 * 命令序列中第二条指令
	 */
	private String cmd2;

	
	/**
	 * 第二条指令结果
	 */
	private String result2;

	
	/**
	 * 退票单号
	 */
	private String refundNo;

	/**
	 *  最后一个返回信息
	 */
	private String result;
	
	
	@XmlElement(name = "OPERATION")
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@XmlElement(name = "PREVIEW")
	public String getPreview() {
		return preview;
	}

	public void setPreview(String preview) {
		this.preview = preview;
	}

	@XmlElement(name = "SUCESSED")
	public String getSucessed() {
		return sucessed;
	}

	public void setSucessed(String sucessed) {
		this.sucessed = sucessed;
	}

	@XmlElement(name = "CMD1")
	public String getCmd1() {
		return cmd1;
	}

	public void setCmd1(String cmd1) {
		this.cmd1 = cmd1;
	}

	@XmlElement(name = "RESULT1")
	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	@XmlElement(name = "CMD2")
	public String getCmd2() {
		return cmd2;
	}

	public void setCmd2(String cmd2) {
		this.cmd2 = cmd2;
	}

	@XmlElement(name = "RESULT2")
	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	@XmlElement(name = "REFUNDNO")
	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	@XmlElement(name = "RESULT")
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@XmlElement(name="RESULT1XML")
	public TrfdResult getResult1xml() {
		return result1xml;
	}

	public void setResult1xml(TrfdResult result1xml) {
		this.result1xml = result1xml;
	}
	
}
