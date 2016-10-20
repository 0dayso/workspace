package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import javax.xml.bind.annotation.XmlRootElement;

import cn.vetech.vedsb.platpolicy.cps.response.CpsResponse;

/**
 * 退废单申请接口响应参数
 * 
 * @author lkh
 * @version [版本号, Sep 11, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
@XmlRootElement(name = "response")
public class TicketRefundResponse extends CpsResponse{

	private String status;

	private String errorMessage;

	private String errorCode;

	private String refundNo;

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
