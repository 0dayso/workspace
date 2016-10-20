package org.vetech.core.business.pid.api.pnrauth;

import org.vetech.core.business.pid.api.Param;

public class PnrAuthParam extends Param{
	private String pnrno;
	
	private String bstrOffice;
	
	private String bstrOfficesToDelete;

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getBstrOffice() {
		return bstrOffice;
	}

	public void setBstrOffice(String bstrOffice) {
		this.bstrOffice = bstrOffice;
	}

	public String getBstrOfficesToDelete() {
		return bstrOfficesToDelete;
	}

	public void setBstrOfficesToDelete(String bstrOfficesToDelete) {
		this.bstrOfficesToDelete = bstrOfficesToDelete;
	}
	
	
}
