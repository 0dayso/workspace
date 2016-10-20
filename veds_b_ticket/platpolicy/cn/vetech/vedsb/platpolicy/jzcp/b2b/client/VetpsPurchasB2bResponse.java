package cn.vetech.vedsb.platpolicy.jzcp.b2b.client;

public class VetpsPurchasB2bResponse {
	public static int SUCCESS=0;
	public static int FAIL=-1;
	private int status=SUCCESS;//0成功 -1失败
	private String errorCode;//失败时返回错误代码
	private String errorMessage;//失败时返回错误原因
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
