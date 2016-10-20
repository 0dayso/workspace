package org.vetech.core.business.pid.api.xepnr;

import org.vetech.core.business.pid.api.Param;

public class XePnrParam  extends Param{
	private String pnrNo;//PNR，必填

	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
}
