package org.vetech.core.business.pid.api.detrxml;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;

public class DetrXml {
	private DetrXmlParam detrXmlParam;
	public DetrXml(DetrXmlParam detrXmlParam){
		this.detrXmlParam = detrXmlParam;
	}
	
	public DetrResult detr() throws EtermException {
		valid();
		WebEtermService etermService = new WebEtermService(detrXmlParam.getUrl());
		String data=etermService.detrXML(detrXmlParam.getTkno(), detrXmlParam.getUserid());
		return (DetrResult) XmlUtils.fromXml(data,DetrResult.class);
	}
	
	private void valid() throws EtermException {
		if (StringUtils.isBlank(detrXmlParam.getTkno())) {
			throw new EtermException("票号不能为空");
		}
		if (detrXmlParam.getTkno().length()<13) {
			throw new EtermException("票号不合法");
		}
		detrXmlParam.setTkno(detrXmlParam.getTkno().replace("-", ""));
		
	}
	
	public static void main(String[] args) {
		try {
			WebEtermService etermService = new WebEtermService("http://192.168.110.127:8088");
			String data=etermService.detrXML("7319135688126", "16072810380935");
			System.out.println(data);
		} catch (EtermException e) {
			e.printStackTrace();
		}
	}
}
