package org.vetech.core.business.pid.api.eterm;


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.api.avh.AvhParser;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.VeDate;

/**
 * 调用webservice执行指令，实现抽象执行接口
 * 黑屏接口
 * @author 章磊
 *
 */
public class CommandHandler{
	//private static VeShareWebServiceServiceClient client = new VeShareWebServiceServiceClient();
	//private static Service service = new Service();
	
	//protected VeShareWebServiceServiceSoap service;
	public String excute(String command,String userid,String pass,String url) throws EtermException{
		String tmp=url;
		if(StringUtils.isBlank(tmp)){
			return "IP和端口为空！";
		}
		String []tmps=tmp.split(":");
		if(tmps.length==3){
			if(StringUtils.isBlank(tmps[1]) || tmps[1].equals("//null")){
				return "请先填写正确的IP！";
			}
			if(StringUtils.isBlank(tmps[2]) || tmps[2].equals("null")){
				return "请选填写正确的端口！";
			}
		}else{
			return "IP和端口格式不对";
		}
		try{
			Service service = new Service();   
		    Call call=null;
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(url+"/VeShareWebService/VeShareWebService.dll?Handler=Default")); 
			//setOperationName设置你要调用的远程方法的路径
	        call.setOperationName(new QName("urn:VeShareWebServiceService","ProcessRequest"));   
	        //ParameterMode.IN是否是传入参数 XMLType.XSD_STRING传入的参数类型
	        call.addParameter("bstrCmd", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrUserName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrPassword", XMLType.XSD_STRING, ParameterMode.IN);
	        call.setUseSOAPAction(true);   
	        call.setTimeout(70000);
	        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
	        call.setSOAPActionURI("ProcessRequest");
	        System.out.println(VeDate.getStringDate()+"=="+userid+"-"+pass+":"+command);
	        String data=(String)call.invoke(new Object[]{command, userid, pass}); 
	        System.out.println(VeDate.getStringDate()+"=="+userid+":"+command+"返回");
			System.out.println("黑屏AVH调用接口返回数据:"+data);
			Document doc = null;
			try {
				doc = DocumentHelper.parseText(data);
			} catch (DocumentException e) {
				e.printStackTrace();
				throw new EtermException("解析航班数据失败");
			}
			Element items = doc.getRootElement();
			String returns  = items.elementText("RESULT");
			String commandr  = items.elementText("COMMAND");
			if("AVH".equals(commandr)){
				returns = items.element("RESULT").elementText("AVH");
			}
			returns = AvhParser.parseEterm(returns);
			returns = "<PRE>"+returns+"</PRE>";
			return returns;
		}catch (Exception e) {
			e.printStackTrace();
			throw new EtermException("连接异常！"+e.getCause().getMessage());
		}
	}
}
