package cn.vetech.vedsb.platpolicy;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 通知业务处理结果
 * @author vetech
 *
 */
@XmlRootElement(name="response")
public class NoticeStatus {
	
	private String msg;//处理结果说明
	
	private int status=0;//处理结果0表示处理成功 -1表示失败
	
	/**本机IP**/
	private String hostAddress;
	
	/***本机名字**/
	private String hostName;
	
	/**
	 * 商户类型 1 采购 2 供应
	 */
	private String businessType;

	public void setMsg(int status,String msg) {
		this.msg = msg;
		this.status = status;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getHostAddress() {
		return hostAddress;
	}

	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
