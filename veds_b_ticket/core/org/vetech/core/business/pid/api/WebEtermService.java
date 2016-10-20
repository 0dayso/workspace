package org.vetech.core.business.pid.api;


import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.business.pid.util.Pidlog;
import org.vetech.core.modules.utils.VeDate;

/**
 * http://192.168.1.33/veSWScn/veSWScn.dll?Handler=GenveSWScnWSDL
 * http://192.168.1.233/veSWScn/veSWScn.dll?Handler=Default
 * 
 * @author 章磊
 * 
 */
public class WebEtermService {
	private java.net.URL url;

	private Service service;

	private Call call;

	public WebEtermService(String url) throws EtermException {
		this.service = new Service();
		if(StringUtils.isBlank(url)){
			throw new EtermException("【调用PID配置url为空");
		}
		url = url.replace("http://", "");
		String url_ = "http://"+url + "/veSWScn/veSWScn.dll?Handler=Default";
		try {
			this.call = (Call) service.createCall();
			this.call.setTimeout(240000);
			this.url = new java.net.URL(url_);
		} catch (ServiceException e) {
			throw new EtermException("构造websverice" + e.getMessage());
		} catch (MalformedURLException e) {
			throw new EtermException("【调用PID配置" + url_ + "】url连接异常：" + e.getMessage());
		}
	}

	public String aVH(String bstrHC, String bstrCFDate, String bstrHGKS, String bstrIFZD, String strUser)
			throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AVH"));
			call.addParameter("bstrHC", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrCFDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrHGKS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrIFZD", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(120000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AVH");
			String data = (String) call.invoke(new Object[] { bstrHC, bstrCFDate, bstrHGKS, bstrIFZD, strUser });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}
	
	/**
	 * AVH白屏查询接口升级版
	 * @param travel 城市对 不能为空
	 * @param depDate 起飞日期可以为空或者.或者日期DDMMMYY类型的格式。如果为空或.就表示是当天,其中DDMMMYY中的YY可以省略
	 * @param airways 航空公司二字码 可选
	 * @param isDirect 是否直达 可选 1直达
	 * @param depTime 起飞时间 可选
	 * @param ifgxhb  是否支持O选项，用来过滤共享航班0或1 0不过滤 1过滤 可选 
	 * @param userid
	 * @param asxml 是否以XML的方式返回数据，0或1 不能为空,如果值为0，将转换成AVH完成请求，如果为1将调用AVHXML接口完成请求。 
	 * @return
	 * @throws EtermException [参数说明]
	 */
	public String VEAVH2(String travel, String depDate, String airways, String isDirect,String depTime,String ifgxhb, String userid,String asxml) throws EtermException {
	    StringBuffer xml = new StringBuffer("<INPUT><COMMAND>VEAVH2</COMMAND><PARA><USER>" + userid + "</USER><CITYPAIR>" + travel + "</CITYPAIR><DATE>" + depDate + "</DATE>");
	    
	    if(StringUtils.isNotBlank(airways)){
	        xml.append("<AIRLINE>" + airways + "</AIRLINE>");
	    }
	    
	    if(StringUtils.isNotBlank(isDirect)){
	        xml.append("<DIRECT>" + isDirect + "</DIRECT>");
	    }
	    
	    if(StringUtils.isNotBlank(depTime)){
	        xml.append("<STARTTIME>"+depTime+"</STARTTIME>");
	    }
	    
	    if(StringUtils.isNotBlank(ifgxhb)){
	        xml.append("<OOPTION>" + ifgxhb + "</OOPTION>");
	    }
	    
	    xml.append("<ASXML>" + asxml + "</ASXML>");
	    
	    xml.append("</PARA></INPUT>");
	    
	    return generalCmdProcess(xml.toString());
	}

	public String RTPNR(String pnrno, String bh, String office) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "RtPnr"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("RtPnr");
			String data = (String) call.invoke(new Object[] { pnrno, bh, office });
			return data;
		} catch (Exception e) {
			throw new EtermException(
					"【调用PID配置用户：" + bh + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
	}

	/**
	 * 解析PNR shenjx 2012-3-17添加
	 */
	public String PARSEPNR(String pnrnr, String bh) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "ParsePnr"));
			call.addParameter("bstrPNR", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("ParsePnr");
			String data = (String) call.invoke(new Object[] { pnrnr, bh });
			return data;
		} catch (Exception e) {
			throw new EtermException(
					"【调用PID配置用户：" + bh + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
	}

	/**
	 * 提取大编内容 shenjx 2012-3-17添加
	 */
	public String RRTPNR(String bigpnr, String hbh, String cfdate, String bh, String office) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "RRTPNR"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrFlight", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("RRTPNR");
			String data = (String) call.invoke(new Object[] { bigpnr, hbh, cfdate, bh, office });
			return data;
		} catch (Exception e) {
			throw new EtermException(
					"【调用PID配置用户：" + bh + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
	}

	/**
	 * 提取大编内容 shenjx 2012-3-17添加
	 */
	public String PNRPAT2(String pnrno, String pat, String bh, String office) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "PNRPAT2"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrPAT", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("PNRPAT2");
			String data = (String) call.invoke(new Object[] { pnrno, pat, bh, office });
			return data;
		} catch (Exception e) {
			throw new EtermException(
					"【调用PID配置用户：" + bh + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
	}

	public String AVHXML(String bstrHC, String bstrCFDate, String bstrHGKS, String bstrIFZD, String strUser)
			throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AVHXML"));
			call.addParameter("bstrHC", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrCFDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrHGKS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrIFZD", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AVHXML");
			String data = (String) call.invoke(new Object[] { bstrHC, bstrCFDate, bstrHGKS, bstrIFZD, strUser });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String fd(String bstrCityPair, String bstrCFDate, String bstrHGKS, String bstrNeedParsed, String strUser)
			throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "FD"));
			call.addParameter("bstrCityPair", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrCFDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrHGKS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrNeedParsed", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("FD");
			String data = (String) call
					.invoke(new Object[] { bstrCityPair, bstrCFDate, bstrHGKS, bstrNeedParsed, strUser });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String pat(String bstrSS, String bstrPAT, String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "PAT"));
			call.addParameter("bstrSS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrPAT", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("PAT");
			String data = (String) call.invoke(new Object[] { bstrSS, bstrPAT, strUser });

			if (data != null) {
				data = data.trim();
				data = data.replaceAll("&", "&amp;");
			}
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String pnrpat(String bstrPnrNO, String bstrPAT, String strUser, String bstrOffice) throws EtermException {
		try {
			try {
				call = (Call) service.createCall();
			} catch (ServiceException e) {
				throw new EtermException("构造websverice" + e.getMessage());
			}

			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "PNRPAT"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrPAT", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("PNRPAT");
			Object[] c = { bstrPnrNO, bstrPAT, strUser, bstrOffice };
			String data = (String) call.invoke(c);

			if (data != null) {
				data = data.trim();
				data = data.replaceAll("&", "&amp;");
			}
			return data;
		} catch (RemoteException e) {
			e.getMessage();
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String editPNR(String bstrPnrNO, String bstrCmds, String bstrNeedSealed, String strUser)
			throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "EditPNR"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrCmds", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrNeedSealed", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("EditPNR");
			String data = (String) call.invoke(new Object[] { bstrPnrNO, bstrCmds, bstrNeedSealed, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String nfd(String bstrCityPair, String bstrDate, String bstrHGKS, String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "NFD"));
			call.addParameter("bstrCityPair", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrHGKS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(7200000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("NFD");
			String data = (String) call.invoke(new Object[] { bstrCityPair, bstrDate, bstrHGKS, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String flp(String bstrHBH, String bstrDate, String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "FLP"));
			call.addParameter("bstrHBH", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("FLP");
			String data = (String) call.invoke(new Object[] { bstrHBH, bstrDate, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
	}

	public String xepnr(String bstrPnrNO, String strUser) throws EtermException {
		try {
			// 外面只会构造一次，但是如果多次调用的话就不行，还是要进来一次构造一次
			try {
				call = (Call) service.createCall();
			} catch (ServiceException e) {
				throw new EtermException("构造websverice" + e.getMessage());
			}
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "CancelPNR"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("CancelPNR");
			String data = (String) call.invoke(new Object[] { bstrPnrNO, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}
	}
	
	
	public String xepassenger(String bstrInputXML) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService","GeneralCmdProcess"));
			call.addParameter("bstrInputXML", XMLType.XSD_STRING,ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(120000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GeneralCmdProcess");
			String data = (String) call.invoke(new Object[] { bstrInputXML });
			return data;
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:"+ url.getPort() + "】连接异常：" + e.getMessage());
		}
	}
	
	
	public String trfd(String bstrInputXML) throws EtermException {
		String data = null;
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName("urn:veSWScnService", "GeneralCmdProcess"));
		call.addParameter("bstrInputXML", XMLType.XSD_STRING, ParameterMode.IN);
		call.setUseSOAPAction(true);
		call.setTimeout(120000);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		call.setSOAPActionURI("GeneralCmdProcess");
		try {
			data = (String) call.invoke(new Object[] { bstrInputXML });
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
		return data;
	}

	public String detrF(String bstrDETR,String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "ONLYDETR"));
			call.addParameter("bstrDETR", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("ONLYDETR");
			String data = (String) call
					.invoke(new Object[] { bstrDETR, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:"
					+ url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());

		}
	}
	
	public String clrf(String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "CLRF"));
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("CLRF");
			String data = (String) call.invoke(new Object[] { strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}
	}

	public String tss(String bstrTicketNO, String strUser, String strIsSuspended, String strOffice)
			throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "TSS"));
			call.addParameter("bstrTicketNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strIsSuspended", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("TSS");
			String data = (String) call.invoke(new Object[] { bstrTicketNO, strUser, strIsSuspended, strOffice });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()+ "】连接异常：" + e.getMessage());
		}
	}

	public String tkscan(String strUser, String strDate, String strTickets) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "TKScan"));
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strTickets", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("TKScan");
			String data = (String) call.invoke(new Object[] { strUser, strDate, strTickets });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}
	}

	public String adduser(String strCode, String strUserName, String strPassword) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AddUser"));
			call.addParameter("strCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUserName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strPassword", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AddUser");
			String data = (String) call.invoke(new Object[] { strCode, strUserName, strPassword });
			return data;
		} catch (RemoteException e) {
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());

		}
	}

	public String an(String strUser, String strNewPassword) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AN"));
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strNewPassword", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AN");
			String data = (String) call.invoke(new Object[] { strUser, strNewPassword });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}
	}

	public String modifyUser(String strCode, String strUserName, String strPassword) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "ModifyUser"));
			call.addParameter("strCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUserName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strPassword", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("ModifyUser");
			String data = (String) call.invoke(new Object[] { strCode, strUserName, strPassword });
			return data;
		} catch (RemoteException e) {
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());

		}
	}

	public String delUser(String strCode) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "DelUser"));
			call.addParameter("strCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("DelUser");
			String data = (String) call.invoke(new Object[] { strCode });
			return data;
		} catch (RemoteException e) {
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());

		}
	}

	public String onlyDetr(String bstrDETR, String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "ONLYDETR"));
			call.addParameter("bstrDETR", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("ONLYDETR");
			String data = (String) call.invoke(new Object[] { bstrDETR, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}

	}

	/**
	 * bstrDETR 直接输入票号 strUser 用户名
	 * 
	 * @param bstrDETR
	 * @param strUser
	 * @return
	 * @throws EtermException
	 */
	public String detrXML(String bstrDETR, String strUser) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "DetrXML"));
			call.addParameter("bstrDETR", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("DetrXML");
			String data = (String) call.invoke(new Object[] { bstrDETR, strUser });
			return data;
		} catch (RemoteException e) {
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());

		}

	}

	// 获取所有的组
	public String getAllGroups() {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "GetAllGroups"));
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GetAllGroups");
			data = (String) call.invoke(new Object[] {});
		} catch (RemoteException e) {
			try {
				throw new EtermException(
						"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
			} catch (EtermException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	// 获取所有的组及其用户
	public String getAllGroupsAndUsers() {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "GetAllGroupsAndUsers"));
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GetAllGroupsAndUsers");
			data = (String) call.invoke(new Object[] {});
		} catch (RemoteException e) {
			try {
				throw new EtermException(
						"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
			} catch (EtermException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	// 将用户加入到指定的PID组
	public String addUser2(String strCode, String strUserName, String strPassword, String strGroupId) throws EtermException {
		String data = "";
		// 外面只会构造一次，但是如果多次调用的话就不行，还是要进来一次构造一次
		try {
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AddUser2"));
			call.addParameter("strCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUserName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strPassword", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strGroupID", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AddUser2");
			data = (String) call.invoke(new Object[] { strCode, strUserName, strPassword, strGroupId });
		} catch (Exception e) {
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 这个接口根据用户获取其所属的组的ID以及名称
	 * 
	 * @param strUser
	 *            用户编号
	 */
	public String getGroupByUser(String strUser) {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "GetGroupByUser"));
			call.addParameter("strUserCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GetGroupByUser");
			data = (String) call.invoke(new Object[] { strUser });
		} catch (RemoteException e) {
			try {
				throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
						+ "】连接异常：" + e.getMessage());
			} catch (EtermException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}
	
	public String getUsersByGroup(String strGroupID) {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "GetUsersByGroup"));
			call.addParameter("strGroupID", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GetUsersByGroup");
			data = (String) call.invoke(new Object[] { strGroupID });
		} catch (RemoteException e) {
			try {
				throw new EtermException("【调用PID配置用户：" + strGroupID + "，IP:" + url.getHost() + "，端口:" + url.getPort()
						+ "】连接异常：" + e.getMessage());
			} catch (EtermException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * pnr授权 航信将逐渐取消代理人提取其它OFFICE的PNR的权限 可由PNR的责任组OFFICE，对单个PNR做授权。操作指令如下：
	 * 
	 * 余学锋 11:26:09 3.1.36 PNRAUTH 接口定义：[id(38)] HRESULT PnrAuth([in]BSTR strPnrNO, [in]BSTR bstrOffice, [in]BSTR
	 * bstrTargetOffices, [in]BSTR strUser, [out, retval]BSTR *bstrOutput); 传入参数说明： u strPnrNO：PNR编码 u
	 * bstrOffice：PNR所属的office,这个参数最好指定；如果为空，PID将根据用户的RMK指令去执行授权过程，可能会失败。 u
	 * bstrTargetOffices：将被授权到的office列表，多个office之间用逗号‘,’分割开。当然，如果只有一个office号，逗号就不需要了。 u
	 * strUser:用户ID，如果bstrOffice为空，那么共享根据该用户的RMK指令的权限来选择恰当的PID去执行指令。 返回： <PNRAUTH> <FLAG>成功是为1;失败时是0</FLAG>
	 * <CONTENT>成功时为航信返回的封口结果或已经授权提示；失败时是相关错误提示信息</CONTENT>
	 * 
	 * @parma strPnrNO：PNR编码
	 * @param bstrOffice：PNR所属的office,这个参数最好指定；如果为空，PID将根据用户的RMK指令去执行授权过程，可能会失败
	 * @parma bstrTargetOffices：将被授权到的office列表，多个office之间用逗号‘,’分割开。当然，如果只有一个office号，逗号就不需要了
	 * @param strUser:用户ID，如果bstrOffice为空，那么共享根据该用户的RMK指令的权限来选择恰当的PID去执行指令
	 * 
	 */
	// 先进行/T授权，如果提示需要授权，表示代理人没有权限执行/T指令，那么去掉/T再进行授权。代理人没有权限，可以找航信开通相应权限
	public boolean pnrAuth(String strPnrNO, String bstrOffice, String bstrTargetOffices, String bstrOfficesToDelete,
			String strUser) throws EtermException {
		String toffice = StringUtils.trimToEmpty(bstrTargetOffices);
		if (toffice.indexOf(",") < 0) {
			// 再次授权功能 houya 20150908
			toffice = toffice + "/T";
		}
		boolean isok = false;
		try {
			isok = pnrAuth1(strPnrNO, bstrOffice, toffice, bstrOfficesToDelete, strUser);
		} catch (EtermException e) {
			String content = StringUtils.trimToEmpty(e.getMessage());
			if (content.indexOf("航信返回：需要授权") >= 0) {
				System.out.println(strPnrNO + "/T授权报错" + content + ",去掉/T再次授权");

				this.service = new Service();
				try {
					this.call = (Call) service.createCall();
				} catch (ServiceException e1) {
					throw new EtermException("授权初始化失败" + e1.getMessage());
				}
				this.call.setTimeout(240000);

				isok = pnrAuth1(strPnrNO, bstrOffice, bstrTargetOffices, bstrOfficesToDelete, strUser);
			} else {
				throw e;
			}
		}
		return isok;
	}

	public boolean pnrAuth1(String strPnrNO, String bstrOffice, String bstrTargetOffices, String bstrOfficesToDelete,
			String strUser) throws EtermException {

		String allerror = "用户" + strUser + "对PNR:" + strPnrNO + "授予OFFICE号:" + bstrTargetOffices + "权限失败";
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "PnrAuth"));
			call.addParameter("strPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrTargetOffices", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOfficesToDelete", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("PnrAuth");
			data = (String) call
					.invoke(new Object[] { strPnrNO, bstrOffice, bstrTargetOffices, bstrOfficesToDelete, strUser });
		} catch (RemoteException e) {
			String error = e.getMessage();
			if (error.indexOf("SOAP Invalid Request") >= 0) {
				throw new EtermException(allerror + "连接异常：" + e.getMessage() + "请注意PID或接口没有更新");
			}
			throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
					+ "】连接异常：" + e.getMessage());
		}
		Document db = null;
		try {
			db = DocumentHelper.parseText(data);
		} catch (Exception e) {
			throw new EtermException(allerror + "解析授权返回的内容出错：" + e.getMessage() + "返回内容是：" + data);
		}
		Element r = db.getRootElement();
		String flag = r.elementText("FLAG");
		if ("1".equals(flag)) {
			return true;
		}
		throw new EtermException(allerror + "航信返回：" + r.elementText("CONTENT"));
	}

	/**
	 * 修改乘机人证件号码  strPnrNO：PNR编码  bstrName：旅客姓名
	 * 如果该名字在乘机人中重复，如果bstrOldFOID为空(也就是没有指定旧证件号),那么共享会报错；如果重复，但是指定了旧证件号而且PNR中能够找到旧证件号，那么共享也会正确执行，去变更证件号码。 
	 * bstrOldFOID：旧证件号码，可以输入，也可以为空  bstrNewFOID：新证件号码 
	 * bstrType：证件号码类型，可输入“PP”或“NI”,该参数也可以为空，如果为空，共享会默认当‘NI’处理。当前共享只支持NI类型的证件号变更。 
	 * bstrOffice：可选，如果该参数被指定，共享将会找到任意一个属于该office号的pid去执行指令  strUser:用户ID，共享根据该用户的SSR指令的权限来选择恰当的PID去执行指令
	 */
	public String ssrFOID(String strPnrNO, String bstrName, String bstrOldFOID, String bstrNewFOID, String bstrType,
			String bstrOffice, String strUser) {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "SSRFOID"));

			call.addParameter("strPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOldFOID", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrNewFOID", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrType", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);

			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("SSRFOID");
			data = (String) call.invoke(
					new Object[] { strPnrNO, bstrName, bstrOldFOID, bstrNewFOID, bstrType, bstrOffice, strUser });
		} catch (RemoteException e) {
			try {
				throw new EtermException("【调用PID配置用户：" + strUser + "，IP:" + url.getHost() + "，端口:" + url.getPort()
						+ "】连接异常：" + e.getMessage());
			} catch (EtermException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	public String Detr2(String bstrTicketNO, String bstrUserName, String bstrOffice) throws EtermException {
		String data = "";
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "Detr2"));
			call.addParameter("bstrTicketNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrUserName", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("Detr2");
			data = (String) call.invoke(new Object[] { bstrTicketNO, bstrUserName, bstrOffice });
		} catch (RemoteException e) {
			String error = e.getMessage();
			if (error.indexOf("SOAP Invalid Request") >= 0) {
				throw new EtermException("连接异常：" + e.getMessage() + "请注意PID或接口没有更新");
			}
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 通用接口，接受一个xml数据
	 * 
	 * @param bstrInputXML
	 *            传入XML，格式：<INPUT><COMMAND>命令名</COMMAND><PARA>参数</PARA></INPUT>
	 * 
	 * @return
	 * @throws EtermException
	 *             [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String generalCmdProcess(String bstrInputXML) throws EtermException {
		String data = null;
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName("urn:veSWScnService", "GeneralCmdProcess"));
		call.addParameter("bstrInputXML", XMLType.XSD_STRING, ParameterMode.IN);
		call.setUseSOAPAction(true);
		call.setTimeout(120000);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		call.setSOAPActionURI("GeneralCmdProcess");
		try {
			data = (String) call.invoke(new Object[] { bstrInputXML });
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
		return data;
	}
	public String VERightMGR(String bstrInputXML) throws EtermException {
		String data = null;
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName("urn:veSWScnService", "VERightMGR"));
		call.addParameter("bstrInputXML", XMLType.XSD_STRING, ParameterMode.IN);
		call.setUseSOAPAction(true);
		call.setTimeout(120000);
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
		call.setSOAPActionURI("VERightMGR");
		try {
			data = (String) call.invoke(new Object[] { bstrInputXML });
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new EtermException(
					"【调用PID配置IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 使用大配置RT编码
	 * 
	 * @param pnrno 小编码
	 * @param userid 用户ID
	 * @param office 大配置上OFFICE号
	 */
	public String RTXPNR(String pnrno, String userid, String office) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "RtxPnr"));
			call.addParameter("bstrPnrNO", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("RtxPnr");
			String data = (String) call.invoke(new Object[] { pnrno, userid, office });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + userid + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());
		}
	}

	/**
	 * 使用大配置RT编码
	 * 
	 * @param pnrno 小编码
	 * @param userid 用户ID
	 * @param office 大配置上OFFICE号
	 */
	public String QTE(String hdxx, String qte, String userid) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "QTE"));
			call.addParameter("bstrSS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrQTE", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("QTE");
			String data = (String) call.invoke(new Object[] { hdxx, qte, userid });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + userid + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());
		}
	}

	public String QTE3(String qte3Xml, String userid) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "GeneralCmdProcess"));
			call.addParameter("bstrInputXML", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("GeneralCmdProcess");
			String data = (String) call.invoke(new Object[] { qte3Xml });
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			throw new EtermException("【调用PID配置用户：" + userid + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());
		}

	}

	/**
	 * 使用大配置RT编码
	 * 
	 * @param pnrno 小编码
	 * @param userid 用户ID
	 * @param office 大配置上OFFICE号
	 */
	public String Book(String ydxx, String userid, String office) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "Book"));
			call.addParameter("bstrCmds", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrOffice", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("Book");
			String data = (String) call.invoke(new Object[] { ydxx, userid, office });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + userid + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());
		}
	}
	/**
	 * 使用大配置RT编码
	 * 
	 * @param pnrno 小编码
	 * @param userid 用户ID
	 * @param office 大配置上OFFICE号
	 */
	public String switchGroup(String strCode, String strOldGroupID, String strNewGroupID) throws EtermException {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "SwitchGroup"));
			call.addParameter("strCode", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strOldGroupID", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strNewGroupID", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("SwitchGroup");
			String data = (String) call.invoke(new Object[] { strCode, strOldGroupID, strNewGroupID });
			return data;
		} catch (Exception e) {
			throw new EtermException("【调用PID配置用户：" + strCode + "，IP:" + url.getHost() + "，端口:" + url.getPort() + "】连接异常："
					+ e.getMessage());
		}
	}
	public String xepnrByOffice(String strUser, String pnrno, String office) throws EtermException {
		String xml = "<INPUT><COMMAND>VEXEPNR2</COMMAND><PARA><USER>" + strUser + "</USER><PNRNO>" + pnrno
				+ "</PNRNO><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
		return generalCmdProcess(xml);
	}

	public String patByOffice(String strUser, String ss, String pat, String office) throws EtermException {
		String xml = "<INPUT><COMMAND>VEPAT2</COMMAND><PARA><USER>" + strUser + "</USER><SS>" + ss + "</SS><PAT>" + pat
				+ "</PAT><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
		return generalCmdProcess(xml);
	}
//	public String patByOffice(String strUser, String ss, String pat, String office) throws EtermException {
//		String xml = "<INPUT><COMMAND>VEPAT2</COMMAND><PARA><USER>" + strUser + "</USER><SS>" + ss + "</SS><PAT>" + pat
//				+ "</PAT><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
//		return generalCmdProcess(xml);
//	}
	
	/**
	 *  params:
	 * 	bstrTicketNO：13位票号
	 * 	bstrXCDH：行程单号
	 * 	strUser：用户ID，共享根据该用户的PRINV指令的权限确定来选择恰当的pid去执行指令
	 * 	bstrOffice：可选，如果该参数被指定，共享将会找到任意一个属于该office号的pid去执行指令（忽略PRINV指令权限），
	 * 					否则，将按照PRINV指令的权限确定pid
	 * 	bstrNeedParsed:是否需要对行程单数据进行解码。值为1表示需要解码；0表示不需要解码
	 *  bstrTested值为1，表示将以测试方式调用该接口；bstrTested值为0，表示将正常调用接口进行行程单创建
	 * 
	 * 
	 */
	public String createXcd2(Object[] o) throws RemoteException{
		Pidlog pid_log=new Pidlog();
		pid_log.setInputtime(VeDate.getNowDateShort());
		String method = "CreateXCD2";   
		call.setTargetEndpointAddress(url);   
        call.setOperationName(new QName("urn:veSWScnService",method));   
        call.addParameter("bstrTicketNO", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("bstrXCDH", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("bstrOffice",XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("bstrNeedParsed",XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("bstrTested",XMLType.XSD_STRING, ParameterMode.IN);
        call.setUseSOAPAction(true);   
        call.setTimeout(new Integer(60000)); //call.setTimeout(new Integer(EtermStatus.timeout));  
        call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);    
        call.setSOAPActionURI(method);   
        return (String)call.invoke(o);
	}

	public static void main(String[] args) {
		try {

			/*URL url = new URL("http://192.168.1.69:8088/veSWScn/veSWScn.dll?Handler=Default");
			System.out.println(url.getPath());
			System.out.println(url.getHost() + ":" + url.getPort());*/
			
//	WebEtermService etermService = new WebEtermService("http://192.168.1.69:8088");
//			String ret =etermService.get();
//			System.out.println(StringUtils.substringBefore(StringUtils.substringAfter(ret, "<GROUPS>"), "</GROUPS>"));
//			System.out.println(ret.substring(ret.indexOf("<GROUPS>")+8,ret.indexOf("</GROUPS>")));
//			System.out.println(ret);
//			String xml="<INPUT><COMMAND>VERIGHTMGR</COMMAND><PARA><CHILDCMD>GETGROUPPROPERTIES</CHILDCMD><USER>8402</USER><BH>69</BH></PARA></INPUT>";
//			String xml="<INPUT><COMMAND>VERIGHTMGR</COMMAND><PARA><CHILDCMD>ModifyOrAddGroup</CHILDCMD><USER>8402</USER><EDITEDTYPE>ADDED</EDITEDTYPE><GROUPCODE>XIAAAA</GROUPCODE><GroupName>测试11111111</GroupName><CanEtermMsg>1</CanEtermMsg><PIDZID>8</PIDZID><PIDID>0</PIDID></PARA></INPUT>";
//			String data = etermService.generalCmdProcess(xml);
//			String etermmsg=data.substring(data.indexOf("<CanEtermMsg>")+13, data.indexOf("</CanEtermMsg>"));
//			System.out.println(data);
//			String avhXmlString  = etermService.VEAVH2("WUHDLC", "29mar16", "", "1", "", "0", "8472", "0");
//			System.out.println("AVHXML:"+avhXmlString);
			// 
			// WebEtermService etermService = new WebEtermService("http://192.168.1.95:8088");
			// System.out.println(etermService.generalCmdProcess("<INPUT><COMMAND>VETICKETMGR</COMMAND><PARA><USER>KMYYADMIN</USER><CHILDCMD>FetchOfficeAndPrinters</CHILDCMD></PARA></INPUT>"));
			// System.out.println(etermService.generalCmdProcess("<INPUT><COMMAND>VETICKETMGR</COMMAND><PARA><USER>KMYYADMIN</USER><CHILDCMD>FetchTicketsData</CHILDCMD><OFFICE>KMG633</OFFICE><PRINTER>1</PRINTER><DATE>27SEP12</DATE><AGENT></AGENT><BYAGENT></BYAGENT></PARA></INPUT>"));
			// WebEtermService etermService = new WebEtermService("http://www.tutu.cn:8088");
			// etermService.pnrAuth("HPXBKR", "", "DLC446,DLC447,DLC448","", "XYJADMIN");
			// System.out.println(etermService.aVH("WUHPEK", "19sep", "", "1", "KMYYADMIN"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
