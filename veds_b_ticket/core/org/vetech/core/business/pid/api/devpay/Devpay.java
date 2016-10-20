package org.vetech.core.business.pid.api.devpay;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class Devpay {
	private static Logger logger = LoggerFactory.getLogger(Devpay.class);
	
	public String devpay(DevpayParam param) throws EtermException{
		String data = "";
		valid(param);
		String InputXML = param.toxml();
		logger.error("查询bop余额入参："+InputXML);
		//System.out.println(InputXML);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		try {
			data=StringUtils.trimToEmpty(etermService.generalCmdProcess(InputXML));
			logger.error("查询bop余额回参:"+data);
		} catch (Exception e) {
			throw new EtermException("调用webservice接口查询bop余额异常"+e.getMessage());
		}
		if(StringUtils.isBlank(data)){
			throw new EtermException("调用PID接口返回空");
		}
		return data;
	}
	
	private void valid(DevpayParam param) throws EtermException {
		if (StringUtils.isBlank(param.getUserid())){
			throw new EtermException("用户编号不能为空");
		}
	}
	public static void main(String[] args) {
//		StringBuffer xml = new StringBuffer("<INPUT>");
//		xml.append("<COMMAND>VEDEVPAY</COMMAND>");
//		xml.append("<PARA>");
//		xml.append(XmlUtils.xmlnode("USER", "8635"));
//		xml.append(XmlUtils.xmlnode("OFFICE", "WUH404"));
//		xml.append(XmlUtils.xmlnode("CHILDCMD", "IPAY"));
//		xml.append("</PARA>");
//		xml.append("</INPUT>");
//		try {
//			WebEtermService etermService = new WebEtermService("http://192.168.1.69:8088");
//			System.out.println(etermService.generalCmdProcess(xml.toString()));
//		} catch (EtermException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
}
