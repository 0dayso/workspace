package org.vetech.core.business.pid.api.tknetrdx;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * 白屏改签指令响应结果参数DESCRIPTION节点
 * 
 * @author lkh
 * @version [版本号, Nov 14, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxDescription {

	/**
	 * 跟参数检查相关或不能归结到操作逻辑的错误
	 */
	private String error;

	/**
	 * 新预定出来的PNR信息
	 */
	private VeTkneTrdxPnr pnrBook;

	/**
	 * SP出来的PNR信息
	 */
	private List<VeTkneTrdxPnr> pnrSps;

	/**
	 * 老PNR的信息
	 */
	private VeTkneTrdxPnr pnrOld;

	/**
	 * XEPNR的相关信息
	 */
	private VeTkneTrdxPnr pnrXepnr;
	
	/**
	 * TRDX运价信息
	 */
	private VeTkneTrdxPrice trdxPrice;
	
	/**
	 * TRDX时的错误信息
	 */
	private String trdxError;

	@XmlElement(name = "PNR_BOOK")
	public VeTkneTrdxPnr getPnrBook() {
		return pnrBook;
	}

	public void setPnrBook(VeTkneTrdxPnr pnrBook) {
		this.pnrBook = pnrBook;
	}

	@XmlElement(name = "PNR_SP")
	public List<VeTkneTrdxPnr> getPnrSps() {
		return pnrSps;
	}

	public void setPnrSps(List<VeTkneTrdxPnr> pnrSps) {
		this.pnrSps = pnrSps;
	}

	@XmlElement(name = "PNR_OLD")
	public VeTkneTrdxPnr getPnrOld() {
		return pnrOld;
	}

	public void setPnrOld(VeTkneTrdxPnr pnrOld) {
		this.pnrOld = pnrOld;
	}

	@XmlElement(name = "PNR_XEPNR")
	public VeTkneTrdxPnr getPnrXepnr() {
		return pnrXepnr;
	}

	public void setPnrXepnr(VeTkneTrdxPnr pnrXepnr) {
		this.pnrXepnr = pnrXepnr;
	}

	@XmlElement(name = "ERROR")
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@XmlElement(name = "TRDXPRICE")
	public VeTkneTrdxPrice getTrdxPrice() {
		return trdxPrice;
	}

	public void setTrdxPrice(VeTkneTrdxPrice trdxPrice) {
		this.trdxPrice = trdxPrice;
	}

	@XmlElement(name = "TRDXERRORINFO")
	public String getTrdxError() {
		return trdxError;
	}

	public void setTrdxError(String trdxError) {
		this.trdxError = trdxError;
	}
	
	
}
