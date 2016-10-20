package org.vetech.core.business.pid.api.pnrpat2;

import org.vetech.core.business.pid.api.Param;

public class PNRPAT2Param extends Param{
	private String pnrno;
	private String pat;
	private String office;
	public String getPnrno() {
		return pnrno;
	}
	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
}
