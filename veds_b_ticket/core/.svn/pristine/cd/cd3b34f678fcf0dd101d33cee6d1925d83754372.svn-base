package org.vetech.core.business.pid.api.xepnr;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

/**
 * ResultXml:1为xe成功，2为已经xe过了，其他为不成功 3 需要授权
 * @author 章磊
 *
 */
public class XePnr{
	public static final String XED="PNR CANCELLED"; 
	public static final String XYSQ="需要授权";//需要授权
	
	public static final String XE_SUCCESS = "1";//XE成功
	public static final String HAS_XE = "2";//已被XE
	public static final String NEED_APPLY = "3";//需要授权
	
	private XePnrParam xePnrParam;
	public XePnr(XePnrParam xePnrParam){
		this.xePnrParam = xePnrParam;
	}
	
	public String xePnr() throws EtermException{
		String data="";
		WebEtermService etermService = new WebEtermService(xePnrParam.getUrl());
		try{
			data=StringUtils.trimToEmpty(etermService.xepnr(xePnrParam.getPnrNo(),xePnrParam.getUserid()));
		}catch(Exception e){
			throw new EtermException("调用webservice接口XEPNR异常"+e.getMessage());
		}
		if(StringUtils.isNotBlank(data)){
			if(XED.equals(data)){
				return HAS_XE;
			}else if (XYSQ.equals(data)){
				return NEED_APPLY;
			}else {
				return data.replaceAll("[^\\s]+\\s+[^\\s]+\\s+[^\\s]+", "1");
			}
		}
		return data;
	}
}
