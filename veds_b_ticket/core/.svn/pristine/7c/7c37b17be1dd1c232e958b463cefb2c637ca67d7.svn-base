package org.vetech.core.business.pid.api.rtpnr;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.exception.EtermException;

public class PnrRt {
	public Pnr pnrRt(PnrRtParam param) throws EtermException {
		valid(param);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String pnrno = param.getPnrno();
		String bh = param.getUserid();
		String office = param.getOfficeId();
		String etermdata = etermService.RTPNR(pnrno, bh, office);
		Pnr pnr = null;
		try {
			pnr = PNRParser.parser(etermdata);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pnr;
	}
	
	private void valid(PnrRtParam param) throws EtermException {
		if (StringUtils.isBlank(param.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
		
		if (param.getPnrno().startsWith("O")) {
			throw new EtermException("PNR:"+param.getPnrno()+"是假编码");
		}
		
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
	}
}
