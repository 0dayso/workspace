package org.vetech.core.business.pid.api.rtkt;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;
/**
 * RKTK 指令可以通过票号获取代理费
 * DETR 指令可以获取客票状态
 * @author wangshengliang
 *
 */
public class RtKt {

	/**
	 * 
	 <RESULT><STATUS>1</STATUS><DESCRIPTION><RESULT><CONTENT>    
   CHINA EASTERN AIRLINES                               08322705             
     Q/不得签转收费更改                  CANWUH       YAKE               
                                    26SEP16    JYXEZZ/1ESHA384                 
   湛佳成              RFND                             DEV-24             
                                                        44867      1FV I       
                                                                               
     CAN         CAN  MU  2547 Z 09OCT 2005  OKYZ14WHDL       09OCT09OCT20K  
                                                                               
     WUH         WUH      VOID                                               
                                                                               
     VOID                                       起飞前四十五分钟停办手续   +
                                                                               -   CNY  310.00A09OCT16CAN MU WUH310.00CNY310.00END                             
                                                                               
   CN    50.00                                                                 
   YQ   EXEMPTCASH(CNY)                                                        
   CNY  360.00                                                                 
                                            CNY310.00            5   50.00     
                     781                                                       
                                                                               
    
    </CONTENT><RTKT><AGENTFEE>5</AGENTFEE></RTKT></RESULT></DESCRIPTION></RESULT>
    
    <RESULT><STATUS>-1</STATUS><DESCRIPTION>RTKT处理失败(    
	NO TICKET FOUND,CHECK TKT NUMBER
	)</DESCRIPTION></RESULT>
    
	 */
	public RtKtResult rtKt(RtKtParam param) throws EtermException {
		valid(param);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String xml = param.toXml();
		String data = StringUtils.trimToEmpty(etermService.generalCmdProcess(xml));
		System.out.println("获取代理费:"+data);
		RtKtResult r = new RtKtResult();
		 if(data.contains("<STATUS>1</STATUS>")){
			r.setStatus("1");
			int start=data.indexOf("<DESCRIPTION>");
			start = start > -1 ? start + 13 : start;
			int end=data.indexOf("</DESCRIPTION>");
			String description=data.substring(start,end);
			Result result= (Result) XmlUtils.fromXml(description, Result.class);
			r.setResult(result);
		}else{
			r = (RtKtResult) XmlUtils.fromXml(data, RtKtResult.class);
		}
		return r;
	}
	
	private void valid(RtKtParam param) throws EtermException {
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
		if(StringUtils.isBlank(param.getTkno())){
			throw new EtermException("票号不能为空");
		}
		int length=param.getTkno().length();
		if (length != 13 && length != 14) {
			throw new EtermException("票号格式不正确");
		}
	}
	
	public static void main(String[] args) {
		RtKt  r=new RtKt();
		RtKtParam param  =new RtKtParam();
		param.setUserid("8635");
		param.setTkno("123-1234567890");
		param.setUrl("http://192.168.1.69:8088");
		try {
			System.out.println(r.rtKt(param));
		} catch (EtermException e) {
			e.printStackTrace();
		}
	}
}
