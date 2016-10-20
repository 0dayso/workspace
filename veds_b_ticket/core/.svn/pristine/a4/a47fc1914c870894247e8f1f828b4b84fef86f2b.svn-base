package org.vetech.core.business.pid.api.trfd;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TRFD预览模式返回对象
 * @author wangshengliang
 *
 */
@XmlRootElement(name="TRFD")
public class TrfdPreviewResult{
	/**
	 * 操作类型
	 */
	private String operation;

	/**
	 * 1表示以预览模式调用接口，0表示命令提交模式
	 */
	private String preview;

	private String cmd;

	private String content;
	
	private String ticketNo;
	
	private String secondTicketNo;
	
	private List<TrfdTax>  taxs;
	
	private String couponNo1;
	
	private String couponNo2;
	
	private String couponNo3;
	
	private String couponNo4;
	
	private TrfdResult result;

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

	@XmlElement(name = "CMD")
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@XmlElement(name = "CONTENT")
	public String getContent() {
		return content;
	}

	
	public void setContent(String content) {
		this.content = content;
	}

	@XmlElement(name = "RESULT")
	public TrfdResult getResult() {
		return result;
	}

	public void setResult(TrfdResult result) {
		this.result = result;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getSecondTicketNo() {
		return secondTicketNo;
	}

	public void setSecondTicketNo(String secondTicketNo) {
		this.secondTicketNo = secondTicketNo;
	}

	public List<TrfdTax> getTaxs() {
		return taxs;
	}

	public void setTaxs(List<TrfdTax> taxs) {
		this.taxs = taxs;
	}

	public String getCouponNo1() {
		return couponNo1;
	}

	public void setCouponNo1(String couponNo1) {
		this.couponNo1 = couponNo1;
	}

	public String getCouponNo2() {
		return couponNo2;
	}

	public void setCouponNo2(String couponNo2) {
		this.couponNo2 = couponNo2;
	}

	public String getCouponNo3() {
		return couponNo3;
	}

	public void setCouponNo3(String couponNo3) {
		this.couponNo3 = couponNo3;
	}

	public String getCouponNo4() {
		return couponNo4;
	}

	public void setCouponNo4(String couponNo4) {
		this.couponNo4 = couponNo4;
	}
}
