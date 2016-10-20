package org.vetech.core.business.pid.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;

/**
 * VEPID调用PID底层实现类
 * 
 * @author gengxianyan
 * @version [版本号, Apr 8, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VepidWebService {

	private URL url;

	private Service service;

	private Call call;

	/**
	 * 构造函数
	 * 
	 * @param url
	 *            地址
	 * @throws Exception
	 *             异常
	 */
	public VepidWebService(String url) throws Exception {
		this.service = new Service();
		try {
			this.call = (Call) service.createCall();
			url = url.replace("http://", "");
			url = "http://"+url;
			this.url = new URL(url + "/veSWScn/veSWScn.dll?Handler=Default");
		} catch (ServiceException e) {
			throw new Exception("构造WebService：" + e.getMessage());
		} catch (MalformedURLException e) {
			throw new Exception("WebService连接异常：" + e.getMessage());
		}
	}

	/**
	 * avh航班查询
	 * 
	 * @param bstrHC
	 *            航程
	 * @param bstrCFDate
	 *            出发时间
	 * @param bstrHGKS
	 *            航空公司
	 * @param bstrIFZD
	 *            是否直达
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String avh(String bstrHC, String bstrCFDate, String bstrHGKS, String bstrIFZD, String strUser)
			throws Exception {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "AVH"));
			call.addParameter("bstrHC", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrCFDate", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrHGKS", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("bstrIFZD", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("AVH");
			String data = (String) call.invoke(new Object[] { bstrHC, bstrCFDate, bstrHGKS, bstrIFZD, strUser });
			return data;
		} catch (Exception e) {
			throw new Exception("avh接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * rtPnr 小配置提小编码
	 * 
	 * @param pnrno
	 *            pnr
	 * @param bh
	 *            编号
	 * @param office
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String rtPnr(String pnrno, String bh, String office) throws Exception {
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
			throw new Exception("rtPnr接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 解析PNR内容
	 * 
	 * @param pnrnr
	 *            pnr
	 * @param bh
	 *            编号
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String parsePnr(String pnrnr, String bh) throws Exception {
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
			throw new Exception("parsePnr接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 提取大编内容 rrtPnr
	 * 
	 * @param bigpnr
	 *            大编号
	 * @param hbh
	 *            编号
	 * @param cfdate
	 *            出发时间
	 * @param bh
	 *            编号
	 * @param office
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String rrtPnr(String bigpnr, String hbh, String cfdate, String bh, String office) throws Exception {
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
			throw new Exception("rrtPnr接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 大配置提小编码,并返回PAT内容 veRtxPat
	 * 
	 * @param user
	 *            用户
	 * @param pnrno
	 *            pnr
	 * @param pat
	 *            平台
	 * @param office
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String veRtxPat(String user, String pnrno, String pat, String office) throws Exception {
		try {
			String inputXml = "<INPUT><COMMAND>VERTXPAT</COMMAND><PARA><USER>" + user + "</USER><PNRNO>" + pnrno
					+ "</PNRNO><PAT>" + pat + "</PAT><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
			return GeneralCmdProcess(inputXml);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 提取大编内容 pnrPat2
	 * 
	 * @param pnrno
	 *            pnr
	 * @param pat
	 *            平台
	 * @param bh
	 *            编号
	 * @param office
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String pnrPat2(String pnrno, String pat, String bh, String office) throws Exception {
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
			throw new Exception("pnrPat2接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * AVHXML
	 * 
	 * @param bstrHC
	 *            航程
	 * @param bstrCFDate
	 *            出发时间
	 * @param bstrHGKS
	 *            航空公司
	 * @param bstrIFZD
	 *            是否直达
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String avhXml(String bstrHC, String bstrCFDate, String bstrHGKS, String bstrIFZD, String strUser)
			throws Exception {
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
			throw new Exception("avhXml接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 执行fd
	 * 
	 * @param bstrCityPair
	 *            城市
	 * @param bstrCFDate
	 *            出发时间
	 * @param bstrHGKS
	 *            航空公司
	 * @param bstrNeedParsed ？
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String fd(String bstrCityPair, String bstrCFDate, String bstrHGKS, String bstrNeedParsed, String strUser)
			throws Exception {
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
			String data = (String) call.invoke(new Object[] { bstrCityPair, bstrCFDate, bstrHGKS, bstrNeedParsed,
					strUser });
			return data;
		} catch (Exception e) {
			throw new Exception("fd接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 执行PAT
	 * 
	 * @param bstrSS ？
	 * @param bstrPAT
	 *            平台
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String pat(String bstrSS, String bstrPAT, String strUser) throws Exception {
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
			throw new Exception("pat接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * RTPNR并返回PAT价格内容
	 * 
	 * @param bstrPnrNO
	 *            pnr
	 * @param bstrPAT
	 *            平台
	 * @param strUser
	 *            用户
	 * @param bstrOffice
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String pnrPat(String bstrPnrNO, String bstrPAT, String strUser, String bstrOffice) throws Exception {
		try {
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
			throw new Exception("pnrPat接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * editPNR更改PNR内容
	 * 
	 * @param bstrPnrNO
	 *            pnr
	 * @param bstrCmds ?
	 * @param bstrNeedSealed ?
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String editPNR(String bstrPnrNO, String bstrCmds, String bstrNeedSealed, String strUser) throws Exception {
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
			throw new Exception("editPNR接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 执行NFD
	 * 
	 * @param bstrCityPair
	 *            城市
	 * @param bstrDate
	 *            日期
	 * @param bstrHGKS
	 *            航空公司
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String nfd(String bstrCityPair, String bstrDate, String bstrHGKS, String strUser) throws Exception {
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
			throw new Exception("nfd接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用FLP
	 * 
	 * @param bstrHBH ？
	 * @param bstrDate
	 *            日期
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String flp(String bstrHBH, String bstrDate, String strUser) throws Exception {
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
			throw new Exception("flp接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 取消PNR cancelPNR
	 * 
	 * @param bstrPnrNO
	 *            pnr
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String cancelPNR(String bstrPnrNO, String strUser) throws Exception {
		try {
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
			throw new Exception("cancelPNR接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 带OFFICE号XEPNR
	 * 
	 * @param strUser
	 *            用户编号
	 * @param pnrno
	 *            PNR编码
	 * @param office
	 *            OFFICE号
	 * @return 返回Xml
	 * @throws Exception
	 *             [参数说明]
	 */
	public String xepnrByOffice(String strUser, String pnrno, String office) throws Exception {
		String xml = "<INPUT><COMMAND>VEXEPNR2</COMMAND><PARA><USER>" + strUser + "</USER><PNRNO>" + pnrno
				+ "</PNRNO><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
		return commonProcess(xml);
	}

	/**
	 * 调用clrf
	 * 
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String clrf(String strUser) throws Exception {
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
			throw new Exception("clrf接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用tss
	 * 
	 * @param bstrTicketNO
	 *            票号
	 * @param strUser
	 *            用户
	 * @param strIsSuspended ？
	 * @param strOffice
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String tss(String bstrTicketNO, String strUser, String strIsSuspended, String strOffice) throws Exception {
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
			throw new Exception("tss接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用tksCan
	 * 
	 * @param strUser
	 *            用户
	 * @param strDate
	 *            日期
	 * @param strTickets
	 *            票号
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String tksCan(String strUser, String strDate, String strTickets) throws Exception {
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
			throw new Exception("tksCan接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用addUser
	 * 
	 * @param strCode
	 *            用户ID
	 * @param strUserName
	 *            用户名
	 * @param strPassword
	 *            密码
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String addUser(String strCode, String strUserName, String strPassword) throws Exception {
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
			throw new Exception("addUser接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用an
	 * 
	 * @param strUser
	 *            用户
	 * @param strNewPassword
	 *            密码
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String an(String strUser, String strNewPassword) throws Exception {
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
			throw new Exception("an接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用modifyUser
	 * 
	 * @param strCode
	 *            用户ID
	 * @param strUserName
	 *            用户名
	 * @param strPassword
	 *            密码
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String modifyUser(String strCode, String strUserName, String strPassword) throws Exception {
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
			throw new Exception("modifyUser接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用DelUser
	 * 
	 * @param strCode
	 *            用户ID
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String delUser(String strCode) throws Exception {
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
			throw new Exception("delUser接口返回异常：" + e.getMessage());

		}
	}

	/**
	 * 调用ONLYDETR
	 * 
	 * @param bstrDETR ？
	 * @param strUser
	 *            用户
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String onlyDetr(String bstrDETR, String strUser) throws Exception {
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
			throw new Exception("onlyDetr接口返回异常：" + e.getMessage());

		}

	}

	/**
	 * 调用DETRXML
	 * 
	 * @param bstrDETR
	 *            票号
	 * @param strUser
	 *            用户名
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String detrXML(String bstrDETR, String strUser) throws Exception {
		try {
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName("urn:veSWScnService", "DetrXML"));
			call.addParameter("bstrDETR", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("strUser", XMLType.XSD_STRING, ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setTimeout(30000);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setSOAPActionURI("detrXML");
			String data = (String) call.invoke(new Object[] { bstrDETR, strUser });
			return data;
		} catch (RemoteException e) {
			throw new Exception("detrXML接口返回异常：" + e.getMessage());

		}

	}

	/**
	 * 获取所有的组
	 * 
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String getAllGroups() throws Exception {
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
				throw new Exception("getAllGroups接口返回异常：" + e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * 获取所有的组及其用户
	 * 
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String getAllGroupsAndUsers() throws Exception {
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
			throw new Exception("getAllGroupsAndUsers接口返回异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 将用户加入到指定的PID组 addUser2
	 * 
	 * @param strCode
	 *            用户ID
	 * @param strUserName
	 *            用户名
	 * @param strPassword
	 *            密码
	 * @param strGroupId
	 *            组ID
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String addUser2(String strCode, String strUserName, String strPassword, String strGroupId) throws Exception {
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
			throw new Exception("addUser2接口返回异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 这个接口根据用户获取其所属的组的ID以及名称
	 * 
	 * @param strUser
	 *            用户编号
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String getGroupByUser(String strUser) throws Exception {
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
			throw new Exception("getGroupByUser接口返回异常：" + e.getMessage());
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
	 * @param strPnrNO
	 *            PNR编码
	 * @param bstrOffice
	 *            PNR所属的office,这个参数最好指定；如果为空，PID将根据用户的RMK指令去执行授权过程，可能会失败
	 * @param bstrTargetOffices
	 *            将被授权到的office列表，多个office之间用逗号‘,’分割开。当然，如果只有一个office号，逗号就不需要了
	 * @param strUser
	 *            用户ID，如果bstrOffice为空，那么共享根据该用户的RMK指令的权限来选择恰当的PID去执行指令
	 * @param bstrOfficesToDelete ??
	 * @return 结果
	 * @throws Exception
	 *             异常
	 * 
	 */
	public String pnrAuth(String strPnrNO, String bstrOffice, String bstrTargetOffices, String bstrOfficesToDelete,
			String strUser) throws Exception {
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
			data = (String) call.invoke(new Object[] { strPnrNO, bstrOffice, bstrTargetOffices, bstrOfficesToDelete,
					strUser });
		} catch (RemoteException e) {
			String error = e.getMessage();
			if (error.indexOf("SOAP Invalid Request") >= 0) {
				throw new Exception(allerror + "连接异常：" + e.getMessage() + "请注意PID或接口没有更新");
			}
			throw new Exception(allerror + "连接异常：" + e.getMessage());
		}
		
		return data;
	}

	/**
	 * 修改乘机人证件号码 
	 * 
	 * @param strPnrNO
	 *            ：PNR编码 
	 * @param bstrName
	 *            ：旅客姓名
	 *            如果该名字在乘机人中重复，如果bstrOldFOID为空(也就是没有指定旧证件号),那么共享会报错；如果重复，但是指定了旧证件号而且PNR中能够找到旧证件号，那么共享也会正确执行，去变更证件号码。 
	 * @param bstrOldFOID
	 *            ：旧证件号码，可以输入，也可以为空 
	 * @param bstrNewFOID
	 *            ：新证件号码 
	 * @param bstrType
	 *            ：证件号码类型，可输入“PP”或“NI”,该参数也可以为空，如果为空，共享会默认当‘NI’处理。当前共享只支持NI类型的证件号变更。 
	 * @param bstrOffice
	 *            ：可选，如果该参数被指定，共享将会找到任意一个属于该office号的pid去执行指令 
	 * @param strUser
	 *            :用户ID，共享根据该用户的SSR指令的权限来选择恰当的PID去执行指令
	 * @return 结果
	 */
	public String ssrFoid(String strPnrNO, String bstrName, String bstrOldFOID, String bstrNewFOID, String bstrType,
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
			data = (String) call.invoke(new Object[] { strPnrNO, bstrName, bstrOldFOID, bstrNewFOID, bstrType,
					bstrOffice, strUser });
		} catch (RemoteException e) {
			try {
				throw new Exception("ssrFoid接口返回异常：" + e.getMessage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * 调用detr2
	 * 
	 * @param bstrTicketNO
	 *            票号
	 * @param bstrUserName
	 *            用户名
	 * @param bstrOffice
	 *            office
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String detr2(String bstrTicketNO, String bstrUserName, String bstrOffice) throws Exception {
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
				throw new Exception("连接异常：" + e.getMessage() + "请注意PID或接口没有更新");
			}
			throw new Exception("detr2接口返回异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 通用接口，接受一个xml数据
	 * 
	 * @param bstrInputXML
	 *            传入XML格式：<INPUT><COMMAND>命令名</COMMAND><PARA>参数</PARA></INPUT>
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	private String GeneralCmdProcess(String bstrInputXML) throws Exception {
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
			throw new Exception("GeneralCmdProcess接口返回异常：" + e.getMessage());
		}
		return data;
	}

	/**
	 * 使用大配置RT编码
	 * 
	 * @param pnrno
	 *            小编码
	 * @param userid
	 *            用户ID
	 * @param office
	 *            大配置上OFFICE号
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String rtxPnr(String pnrno, String userid, String office) throws Exception {
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
			throw new Exception("rtxPnr接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 使用大配置提小编码
	 * 
	 * @param pnrno
	 *            小编码
	 * @param userid
	 *            用户ID
	 * @param office
	 *            大配置上OFFICE号 可以不传
	 * @param pat
	 *            PAT格式，例如：PAT:A，不传默认PAT:A
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String rtxPnrNew(String pnrno, String userid, String office, String pat) throws Exception {
		if (StringUtils.isEmpty(pat)) {
			pat = "PAT:A";
		}
		try {
			String xml = "<INPUT><COMMAND>VERTXPAT</COMMAND><PARA><USER>" + userid + "</USER><PNRNO>" + pnrno
					+ "</PNRNO><PAT>" + pat + "</PAT><OFFICE>" + office + "</OFFICE></PARA></INPUT>";
			return GeneralCmdProcess(xml);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * XE乘机人
	 * 
	 * @param xml
	 *            xml
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String xePassenger(String xml) throws Exception {
		return GeneralCmdProcess(xml);
	}

	/**
	 * 使用大配置RT编码 qte
	 * 
	 * @param hdxx
	 *            航段
	 * @param userid
	 *            用户ID
	 * @param qte ？
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String qte(String hdxx, String qte, String userid) throws Exception {
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
			throw new Exception("qte接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 使用大配置RT编码 book
	 * 
	 * @param command ？
	 * @param userid
	 *            用户ID
	 * @param office
	 *            大配置上OFFICE号
	 * @return 结果
	 * @throws Exception
	 *             异常
	 */
	public String book(String command, String userid, String office) throws Exception {
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
			String data = (String) call.invoke(new Object[] { command, userid, office });
			System.out.println("中文预订编码返回内容：" + data);
			if (StringUtils.isNotEmpty(data)) {
				data = data.trim();
				data = data.replaceAll("&", "&amp;");
			}

			return data;
		} catch (Exception e) {
			throw new Exception("book接口返回异常：" + e.getMessage());
		}
	}

	/**
	 * 流量查询
	 * 
	 * @param user
	 *            用户
	 * @param method
	 *            统计类型
	 * @param starttime
	 *            开始时间
	 * @param endtime
	 *            结束时间
	 * @throws Exception
	 *             异常
	 * @return 查询结果
	 */
	public String flowQuery(String user, String method, String starttime, String endtime) throws Exception {
		try {
			String xml = "<INPUT><COMMAND>VEFLOWQUERY</COMMAND><PARA><USER>" + user + "</USER><METHOD>" + method
					+ "</METHOD><STARTTIME>" + starttime + "</STARTTIME><ENDTIME>" + endtime
					+ "</ENDTIME></PARA></INPUT>";
			return GeneralCmdProcess(xml);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * <VT>
	 * 
	 * @param xml
	 *            入参
	 * @throws Exception
	 *             [参数说明]
	 * @return String [返回类型说明]
	 */
	public String vt(String xml) throws Exception {
		return this.GeneralCmdProcess(xml);
	}

	/**
	 * <TRFD>
	 * 
	 * @param xml
	 *            入参
	 * @throws Exception
	 *             [参数说明]
	 * @return String [返回类型说明]
	 */
	public String trfd(String xml) throws Exception {
		return this.GeneralCmdProcess(xml);
	}

	/**
	 * bsp自动出票
	 * 
	 * @param xml
	 *            入参
	 * @return 返回
	 * @throws Exception
	 *             异常
	 */
	public String veetdz2(String xml) throws Exception {
		return this.GeneralCmdProcess(xml);
	}



	/**
	 * 通用接口，接受一个xml数据
	 * 
	 * @param bstrInputXML
	 *            传入XML，格式：<INPUT><COMMAND>命令名</COMMAND><PARA>参数</PARA></INPUT>
	 * @return 返回Xml
	 * @throws Exception
	 *             异常
	 */
	public String commonProcess(String bstrInputXML) throws Exception {
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
			throw new Exception("连接异常：" + e.getMessage());
		}
		return data;
	}

	// public static void main(String[] args) {
	// try {
	// VepidWebService etermService = new VepidWebService("http://www.tutu.cn:8088");
	// // WebEtermService etermService = new WebEtermService("http://192.168.1.95:8088");
	// //
	// System.out.println(etermService.generalCmdProcess("<INPUT><COMMAND>VETICKETMGR</COMMAND><PARA><USER>KMYYADMIN</USER><CHILDCMD>FetchOfficeAndPrinters</CHILDCMD></PARA></INPUT>"));
	// System.out
	// .println(etermService
	// .GeneralCmdProcess("<INPUT><COMMAND>VETICKETMGR</COMMAND><PARA><USER>KMYYADMIN</USER><CHILDCMD>FetchTicketsData</CHILDCMD><OFFICE>KMG633</OFFICE><PRINTER>1</PRINTER><DATE>27SEP12</DATE><AGENT></AGENT><BYAGENT></BYAGENT></PARA></INPUT>"));
	// // WebEtermService etermService = new WebEtermService("http://www.tutu.cn:8088");
	// // etermService.pnrAuth("HPXBKR", "", "DLC446,DLC447,DLC448","", "XYJADMIN");
	// // System.out.println(etermService.aVH("WUHPEK", "19sep", "", "1", "KMYYADMIN"));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	
	/**
	 * 按照乘机人的证件号码获取票号
	 * 
	 * @param xml
	 *            入参
	 * @return 返回
	 * @throws Exception
	 *             异常
	 */
	public String detrNi(String xml) throws Exception {
		return this.GeneralCmdProcess(xml);
	}
	
	/**
	 * 回填票号做DETR验证
	 * 
	 * @param xml
	 * @return
	 * @throws Exception [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public String veDetrFinal(String xml) throws Exception {
		return this.GeneralCmdProcess(xml);
	}
}
