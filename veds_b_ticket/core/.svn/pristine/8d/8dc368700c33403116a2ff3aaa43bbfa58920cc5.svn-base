package org.vetech.core.business.pid.api.tknetrdx;

import java.util.List;

/**
 * 白屏改签返回参数
 * 
 * TicketChangeUtils.changeTicket()返回参数
 * 
 * @author lkh
 * @version [版本号, Nov 21, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class JpGqdBpResult {
	/**
	 * 大于0的数表示成功；等于0,负数表示失败
	 */
	private int status;

	/**
	 * 执行失败原因
	 */
	private String error;

	/**
	 * 不保留PNR时的改签状态 1表示改签都成功,0表示改签成功但解析PNR失败,-1表示改签失败
	 */
	private String pnrBookStatus;

	/**
	 * 预订新PNR时的详细信息
	 */
	private String pnrBookDetails;

	/**
	 * SP状态 1，成功；-1，失败
	 */
	private String pnrSpStatus;

	/**
	 * SP PNR时的详细信息
	 */
	private String pnrSpDetails;

	/**
	 * 保留PNR时的改签状态 1，改签成功；-1，改签失败
	 */
	private String pnrOldStatus;

	/**
	 * 处理旧PNR时的详细信息
	 */
	private String pnrOldDetails;
	
	/**
	 * PNRXE状态 1，XEPNR成功；-1，XEPNR失败
	 */
	private String pnrXeStatus;

	/**
	 * PNRXE的详细信息
	 */
	private String pnrXeDetails;
	
	/**
	 * TRDX返回的运价信息，以人为单位
	 */
	private List<VeTkneTrdxIndex> trdxIndexs;
	
	/**
	 *  TRDX返回的错误信息
	 */
	private String trdxError;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPnrBookStatus() {
		return pnrBookStatus;
	}

	public void setPnrBookStatus(String pnrBookStatus) {
		this.pnrBookStatus = pnrBookStatus;
	}
	
	public String getPnrSpStatus() {
		return pnrSpStatus;
	}

	public void setPnrSpStatus(String pnrSpStatus) {
		this.pnrSpStatus = pnrSpStatus;
	}

	public String getPnrOldStatus() {
		return pnrOldStatus;
	}

	public void setPnrOldStatus(String pnrOldStatus) {
		this.pnrOldStatus = pnrOldStatus;
	}

	public String getPnrXeStatus() {
		return pnrXeStatus;
	}

	public void setPnrXeStatus(String pnrXeStatus) {
		this.pnrXeStatus = pnrXeStatus;
	}

	public String getPnrBookDetails() {
		return pnrBookDetails;
	}

	public void setPnrBookDetails(String pnrBookDetails) {
		this.pnrBookDetails = pnrBookDetails;
	}

	public String getPnrSpDetails() {
		return pnrSpDetails;
	}

	public void setPnrSpDetails(String pnrSpDetails) {
		this.pnrSpDetails = pnrSpDetails;
	}

	public String getPnrOldDetails() {
		return pnrOldDetails;
	}

	public void setPnrOldDetails(String pnrOldDetails) {
		this.pnrOldDetails = pnrOldDetails;
	}

	public String getPnrXeDetails() {
		return pnrXeDetails;
	}

	public void setPnrXeDetails(String pnrXeDetails) {
		this.pnrXeDetails = pnrXeDetails;
	}

	public List<VeTkneTrdxIndex> getTrdxIndexs() {
		return trdxIndexs;
	}

	public void setTrdxIndexs(List<VeTkneTrdxIndex> trdxIndexs) {
		this.trdxIndexs = trdxIndexs;
	}

	public String getTrdxError() {
		return trdxError;
	}

	public void setTrdxError(String trdxError) {
		this.trdxError = trdxError;
	}
}
