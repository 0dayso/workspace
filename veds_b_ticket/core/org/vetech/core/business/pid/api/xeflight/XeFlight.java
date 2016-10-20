package org.vetech.core.business.pid.api.xeflight;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class XeFlight {
	private static Logger logger = LoggerFactory.getLogger(XeFlight.class);
//	private static final String XESUCCESS = "0";//仅仅XE航段成功
//	
//	private static final String GQSUCCESS = "1";//改期成功
//	
//	private static final String XEPNR = "2";//xepnr
//	
//	private static final String ERRORPARAM = "-1";//传入参数错误,或不符合执行环境
//	
//	private static final String ZXERROR = "-2";//执行过程中发生错误
//	
//	private static final String XEPNRERROR = "-3";//xepnr错误
	
	private XeFlightParam XeFlightParam;
	
	public XeFlight(XeFlightParam XeFlightParam){
		this.XeFlightParam = XeFlightParam;
	}
	
	public String xeFlight(XeFlightParam param) throws EtermException{
		String data = "";
		valid(param);
		String InputXML = param.toxml();
		logger.error("补位入参："+InputXML);
		//System.out.println(InputXML);
		WebEtermService etermService = new WebEtermService(XeFlightParam.getUrl());
		try {
			data=StringUtils.trimToEmpty(etermService.generalCmdProcess(InputXML));
			logger.error("补位回参:"+data);
		} catch (Exception e) {
			throw new EtermException("调用webservice接口XEPNR异常"+e.getMessage());
		}
		if(StringUtils.isBlank(data)){
			throw new EtermException("调用PID接口返回空");
		}
		return data;
	}
	
	private void valid(XeFlightParam param) throws EtermException {
		if (StringUtils.isBlank(param.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
		if (StringUtils.isBlank(param.getUserid())){
			throw new EtermException("用户编号不能为空");
		}
	}
	
	public static void main(String[] args) throws EtermException {
		XeFlightParam param = new XeFlightParam();
		param.setUserid("8635");
		param.setPnrno("HSH28M");
		//param.setOffice("SZX001");
		String s= "*MU3931,"+"PVG,"+"XMN,"+"2016-10-29,"+"K";
		param.setHd(s);
		param.setSs("SS MU3931/K/29OCT16/PVGXMN/1");
		param.setUrl("http://192.168.1.69:8088");
		XeFlight v = new XeFlight(param);
		String data = v.xeFlight(param);
		System.out.println(data);
	}
}
