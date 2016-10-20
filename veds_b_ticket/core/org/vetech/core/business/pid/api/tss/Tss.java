package org.vetech.core.business.pid.api.tss;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;

public class Tss {
   public TssResult tss(TssParam tssParam) throws EtermException{
	   valid(tssParam);
	   WebEtermService etermService = new WebEtermService(tssParam.getUrl());
	   String data= etermService.tss(tssParam.getTicketno(), tssParam.getUserid(), tssParam.getIsSuspended(), tssParam.getOfficeId());
	   System.out.println(data);
	   return (TssResult) XmlUtils.fromXml(data, TssResult.class);
   }
   
   private void valid(TssParam tssParam) throws EtermException {
		if (StringUtils.isBlank(tssParam.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
		if (StringUtils.isBlank(tssParam.getIsSuspended())) {
			throw new EtermException("是否挂起不能为空");
		}
		if (StringUtils.isBlank(tssParam.getTicketno())) {
			throw new EtermException("票号不能为空");
		}
		tssParam.setTicketno(tssParam.getTicketno().replace("-", ""));
	}
  
}
