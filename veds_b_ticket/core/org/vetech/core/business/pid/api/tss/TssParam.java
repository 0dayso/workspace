package org.vetech.core.business.pid.api.tss;

import org.vetech.core.business.pid.api.Param;

public class TssParam extends Param{
	/**
	 * 格式 xxx-xxxxxxxxxx
	 */
	private String ticketno;
	/**
	 * 1挂起  0解挂
	 */
	private String isSuspended;
	
	public String getTicketno() {
		return ticketno;
	}
	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}
	public String getIsSuspended() {
		return isSuspended;
	}
	public void setIsSuspended(String isSuspended) {
		this.isSuspended = isSuspended;
	}
}
