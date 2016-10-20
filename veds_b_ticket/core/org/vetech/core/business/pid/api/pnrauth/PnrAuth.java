package org.vetech.core.business.pid.api.pnrauth;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class PnrAuth {
	private PnrAuthParam pnrAuthParam;
	public PnrAuth(PnrAuthParam pnrAuthParam){
		this.pnrAuthParam = pnrAuthParam;
	}

	public boolean pnrAuth() throws EtermException{
		valid();
		WebEtermService wes = new WebEtermService(pnrAuthParam.getUrl());
		return wes.pnrAuth(pnrAuthParam.getPnrno(), pnrAuthParam.getBstrOffice(), pnrAuthParam.getOfficeId(), pnrAuthParam.getBstrOfficesToDelete(), pnrAuthParam.getUserid());
	}
	
	private void valid() throws EtermException{
		if (StringUtils.isBlank(pnrAuthParam.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
	}
	
}
