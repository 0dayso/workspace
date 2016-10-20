package org.vetech.core.business.pid.api.pidgl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;


@Component
public class PidglService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CsbCacheService cacheService;
	/**
	 * <根据用户id和no查询指定某一个配置>
	 * 
	 * @param userid
	 * @param no
	 * @return
	 * @throws Exception [参数说明]
	 * 
	 * @return JpPid [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public JpPid getByNo(String userid,String no) throws Exception{
		try{
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>QueryPIDinfo</CHILDCMD><PIDNO>"+no+"</PIDNO></PARA></INPUT>";	
			String ret = etermService.generalCmdProcess(xml);
			ret = proccessXml(ret);
			logger.debug(ret);//打印xml
			if (StringUtils.isNotBlank(ret)) {
				Document db = null; 
				try {
					db = DocumentHelper.parseText(ret);
				} catch (DocumentException e) {
					logger.error("解析PID配置数据失败",e);
					throw e;
				}
				Element root = db.getRootElement();
				Element description = root.element("DESCRIPTION");
				Element element = description.element("PIDItem");
				JpPid jppid = new JpPid();
				converToJpPid(element,jppid);
                return jppid;
			}else {
				throw new EtermException("没有PID配置数据");
			}
		}catch (Exception e) {
			logger.error("根据pid编号获取配置信息错误",e);
			throw e;
		}
	}
	
	/**
	 * <处理返回的xml字符串>
	 * 
	 * @param ret
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private String proccessXml(String ret){
		ret = ret.replace("&lt;", "<");
		ret = ret.replace("&gt;", ">");
		ret = ret.replace("&quot;", "\"");
		return ret;
	}
	
	/**
	 * <解析xml节点 得到数据 放入entity中>
	 * 
	 * @param element
	 * @param jppid [参数说明]
	 * 
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	private void converToJpPid(Element element,JpPid jppid ){
		jppid.setNo(element.attributeValue("NO"));
        jppid.setLocalip(element.elementTextTrim("LocalIP"));
        jppid.setLocalport(element.elementTextTrim("LocalPort"));
        jppid.setServerip(element.elementTextTrim("ServerIP"));
        jppid.setServerport(element.elementTextTrim("ServerPort"));
        jppid.setSi(element.elementTextTrim("SI"));
        jppid.setAutolink(element.elementTextTrim("AutoLink"));
        jppid.setAutologin(element.elementTextTrim("AutoLogin"));
        jppid.setSaflytransmited(element.elementTextTrim("SaflyTransmited"));
        jppid.setUsername(element.elementTextTrim("UserName"));
        jppid.setPassword(element.elementTextTrim("Password"));
        jppid.setPidzid(element.elementTextTrim("PIDZID"));
        jppid.setPidid(element.elementTextTrim("PIDID"));
        String remark = element.elementTextTrim("Remark");
        String remarks[] = remark.split(",");
        if(StringUtils.isNotBlank(remark) && remarks.length>1){
	        jppid.setRemark(remarks[0]);
	        jppid.setSfmr(remarks[1]);
        }else{
        	jppid.setRemark(remark);
        	jppid.setSfmr("0");
        }
        jppid.setWebpid(element.elementTextTrim("WebPID"));
        jppid.setWebpidtype(element.elementTextTrim("WebPidType"));
        jppid.setIpe(element.elementTextTrim("IPE"));
        jppid.setBigpid(element.elementTextTrim("BigPID"));
        jppid.setFalsepid(element.elementTextTrim("FalsePID"));
        jppid.setInputcheckcode(element.elementTextTrim("InputCheckCode"));
        jppid.setCantsl(element.elementTextTrim("CanTSL"));
        jppid.setCanprinv(element.elementTextTrim("CanPRINV"));
        jppid.setHastransactionwait(element.elementTextTrim("HasTransactionWait"));
        jppid.setCmdsendinginterval(element.elementTextTrim("CmdSendingInterval"));
        jppid.setFlowlimit(element.elementTextTrim("FlowLimit"));
        jppid.setPrinterscount(element.elementTextTrim("PrintersCount"));
        jppid.setNotallowedairlines(element.elementTextTrim("NotAllowedAirlines"));
        jppid.setPidconnectionstate(element.elementTextTrim("PIDConnectionState"));
        jppid.setAuthenticationtype(element.elementTextTrim("AuthenticationType"));
        jppid.setOffice(element.elementTextTrim("Office"));
        jppid.setStop(element.elementTextTrim("Stop"));
        Element printerE = element.element("Printers");
        if(printerE!=null){
	        List<Element> printers=printerE.elements("Printer");
	        if(printers!=null && !printers.isEmpty()){
	        	jppid.setPrintno(printers.get(0).elementTextTrim("PrinterNO"));
	        }
        }
        
	}
	
	/**
	 * <查询所有配置>
	 * 
	 * @param userid
	 * @return
	 * @throws Exception [参数说明]
	 * 
	 * @return List<JpPid> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<JpPid> queryList(String shbh) throws Exception{
		try{
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>WEB01</USER><CHILDCMD>QueryPIDinfo</CHILDCMD><PIDNO>-1</PIDNO></PARA></INPUT>";	
			String ret = etermService.generalCmdProcess(xml);
			ret = proccessXml(ret);
			logger.debug(ret);
			if (StringUtils.isNotBlank(ret)) {
				Document db = null;
				try {
					db = DocumentHelper.parseText(ret);
				} catch (DocumentException e) {
					logger.error("解析PID配置数据失败",e);
					throw e;
				}
				Element root = db.getRootElement();
				Element description = root.element("DESCRIPTION");
				Element pIDItems = description.element("PIDItems");
				List<Element> list = pIDItems.elements("PIDItem");
				List<JpPid> jpPids = new ArrayList<JpPid>();
				for(int i=0;i<list.size();i++){
					Element element = list.get(i);
					JpPid jppid = new JpPid();
					converToJpPid(element,jppid);
					if(shbh.equals(jppid.getRemark())){
						jpPids.add(jppid);
					}
				}
                return jpPids;
			}else {
				throw new EtermException("没有PID配置数据");
			}
		}catch (Exception e) {
			logger.error("获取所有配置信息错误",e);
			throw e;
		}
	}
	public void stop(String no) throws EtermException{
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>WEB01</USER><CHILDCMD>ModifyPID</CHILDCMD><PIDNO>"+no+"</PIDNO><Stop>0</Stop></PARA></INPUT>";
					String str = etermService.generalCmdProcess(xml);
					logger.debug(str);
			if(str.indexOf("<STATUS>0</STATUS>")==-1){
				throw new EtermException(str);
			}
		} catch (EtermException e) {
			logger.error("新增pid配置失败",e);
			throw e;
		}
	}
	public void start(String no) throws EtermException{
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>WEB01</USER><CHILDCMD>ModifyPID</CHILDCMD><PIDNO>"+no+"</PIDNO><Stop>1</Stop></PARA></INPUT>";
					String str = etermService.generalCmdProcess(xml);
					logger.debug(str);
			if(str.indexOf("<STATUS>0</STATUS>")==-1){
				throw new EtermException(str);
			}
		} catch (EtermException e) {
			logger.error("新增pid配置失败",e);
			throw e;
		}
	}
	/**
	 * <新增pid配置>
	 * 
	 * @return [参数说明]
	 * 
	 * @return boolean [返回类型说明]
	 * @throws EtermException 
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void insertPid(String userid,JpPid t) throws EtermException{
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>AddPID</CHILDCMD>" ;
			if(StringUtils.isNotBlank(t.getPrintno())){
				xml=xml+"<PRINTERS>"+t.getPrintno()+",,,,"+"</PRINTERS>";
			}
			xml=xml+"<AuthenticationType>"+t.getAuthenticationtype()+"</AuthenticationType>"+
			"<Office>"+t.getOffice()+"</Office>"+
			"<Stop>"+t.getStop()+"</Stop>"+
			"<LocalIP>"+StringUtils.trimToEmpty(t.getLocalip())+"</LocalIP><LocalPort>"+StringUtils.trimToEmpty(t.getLocalport())+"</LocalPort><ServerIP>"+StringUtils.trimToEmpty(t.getServerip())+"</ServerIP>" +
			"<ServerPort>"+StringUtils.trimToEmpty(t.getServerport())+"</ServerPort><SI>"+StringUtils.trimToEmpty(t.getSi())+"</SI><AutoLink>"+StringUtils.trimToEmpty(t.getAutolink())+"</AutoLink>" +
			"<AutoLogin>"+StringUtils.trimToEmpty(t.getAutologin())+"</AutoLogin><SaflyTransmited>"+StringUtils.trimToEmpty(t.getSaflytransmited())+"</SaflyTransmited>" +
			"<UserName>"+StringUtils.trimToEmpty(t.getUsername())+"</UserName><Password>"+StringUtils.trimToEmpty(t.getPassword())+"</Password>" +
			"<PIDZID>"+StringUtils.trimToEmpty(t.getPidzid())+"</PIDZID><PIDID>"+StringUtils.trimToEmpty(t.getPidid())+"</PIDID><Remark>"+StringUtils.trimToEmpty(t.getRemark())+","+StringUtils.trimToEmpty(t.getSfmr())+"</Remark>" +
			"<WebPID>"+StringUtils.trimToEmpty(t.getWebpid())+"</WebPID><WebPidType>"+StringUtils.trimToEmpty(t.getWebpidtype())+"</WebPidType><IPE>"+StringUtils.trimToEmpty(t.getIpe())+"</IPE>" +
			"<BigPID>"+StringUtils.trimToEmpty(t.getBigpid())+"</BigPID><FalsePID>"+StringUtils.trimToEmpty(t.getFalsepid())+"</FalsePID><InputCheckCode>"+StringUtils.trimToEmpty(t.getInputcheckcode())+"</InputCheckCode>" +
			"<CanTSL>"+StringUtils.trimToEmpty(t.getCantsl())+"</CanTSL><CanPRINV>"+StringUtils.trimToEmpty(t.getCanprinv())+"</CanPRINV><HasTransactionWait>"+StringUtils.trimToEmpty(t.getHastransactionwait())+"</HasTransactionWait>" +
			"<CmdSendingInterval>"+StringUtils.trimToEmpty(t.getCmdsendinginterval())+"</CmdSendingInterval><FlowLimit>"+StringUtils.trimToEmpty(t.getFlowlimit())+"</FlowLimit><NotAllowedAirlines>"+StringUtils.trimToEmpty(t.getNotallowedairlines())+"</NotAllowedAirlines>" +
			"<PrintersCount>"+StringUtils.trimToEmpty(t.getPrinterscount())+"</PrintersCount></PARA></INPUT>";
			String str = etermService.generalCmdProcess(xml);
			logger.debug("新增pid返回"+userid+":"+str);
			if(str.indexOf("<STATUS>0</STATUS>")==-1){
				throw new EtermException(str);
			}
		} catch (EtermException e) {
			logger.error("新增pid配置失败",e);
			throw e;
		}
		
	}
	
	public void updatePid(String userid,JpPid t){
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>ModifyPID</CHILDCMD><PIDNO>"+StringUtils.trimToEmpty(t.getNo())+"</PIDNO>";
					if(StringUtils.isNotBlank(t.getPrintno())){
						xml=xml+"<PRINTERS>"+t.getPrintno()+",,,,"+"</PRINTERS>";
					}
					
					xml=xml+"<AuthenticationType>"+t.getAuthenticationtype()+"</AuthenticationType>"+
					"<Office>"+t.getOffice()+"</Office>"+
					"<Stop>"+t.getStop()+"</Stop>"+
					"<LocalIP>"+StringUtils.trimToEmpty(t.getLocalip())+"</LocalIP><LocalPort>"+StringUtils.trimToEmpty(t.getLocalport())+"</LocalPort><ServerIP>"+StringUtils.trimToEmpty(t.getServerip())+"</ServerIP>" +
					"<ServerPort>"+StringUtils.trimToEmpty(t.getServerport())+"</ServerPort><SI>"+StringUtils.trimToEmpty(t.getSi())+"</SI><AutoLink>"+StringUtils.trimToEmpty(t.getAutolink())+"</AutoLink>" +
					"<AutoLogin>"+StringUtils.trimToEmpty(t.getAutologin())+"</AutoLogin><SaflyTransmited>"+StringUtils.trimToEmpty(t.getSaflytransmited())+"</SaflyTransmited>" +
					"<UserName>"+StringUtils.trimToEmpty(t.getUsername())+"</UserName><Password>"+StringUtils.trimToEmpty(t.getPassword())+"</Password>" +
					"<PIDZID>"+StringUtils.trimToEmpty(t.getPidzid())+"</PIDZID><PIDID>"+StringUtils.trimToEmpty(t.getPidid())+"</PIDID><Remark>"+StringUtils.trimToEmpty(t.getRemark())+","+StringUtils.trimToEmpty(t.getSfmr())+"</Remark>" +
					"<WebPID>"+StringUtils.trimToEmpty(t.getWebpid())+"</WebPID><WebPidType>"+StringUtils.trimToEmpty(t.getWebpidtype())+"</WebPidType><IPE>"+StringUtils.trimToEmpty(t.getIpe())+"</IPE>" +
					"<BigPID>"+StringUtils.trimToEmpty(t.getBigpid())+"</BigPID><FalsePID>"+StringUtils.trimToEmpty(t.getFalsepid())+"</FalsePID><InputCheckCode>"+StringUtils.trimToEmpty(t.getInputcheckcode())+"</InputCheckCode>" +
					"<CanTSL>"+StringUtils.trimToEmpty(t.getCantsl())+"</CanTSL><CanPRINV>"+StringUtils.trimToEmpty(t.getCanprinv())+"</CanPRINV><HasTransactionWait>"+StringUtils.trimToEmpty(t.getHastransactionwait())+"</HasTransactionWait>" +
					"<CmdSendingInterval>"+StringUtils.trimToEmpty(t.getCmdsendinginterval())+"</CmdSendingInterval><FlowLimit>"+StringUtils.trimToEmpty(t.getFlowlimit())+"</FlowLimit><NotAllowedAirlines>"+StringUtils.trimToEmpty(t.getNotallowedairlines())+"</NotAllowedAirlines>" +
					"<PrintersCount>"+StringUtils.trimToEmpty(t.getPrinterscount())+"</PrintersCount></PARA></INPUT>";
					String str = etermService.generalCmdProcess(xml);
					logger.debug("更新pid配置返回"+userid+":"+str);
		} catch (EtermException e) {
			logger.error("更新pid配置失败", e);
		}
		
	}
	
	public void deletePid(String userid,String no) throws EtermException{
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>DeletePID</CHILDCMD><PIDNO>"+no+"</PIDNO></PARA></INPUT>";	
			String str = etermService.generalCmdProcess(xml);
			logger.debug("删除pid配置返回"+userid+":"+str);
		} catch (EtermException e) {
			logger.error("删除pid配置失败", e);
		}
	}
	
	public void connect(String userid,String no){
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>ConnectPID</CHILDCMD><PIDNO>"+no+"</PIDNO></PARA></INPUT>";	
			String str = etermService.generalCmdProcess(xml);
			logger.debug("连接pid返回"+userid+":"+no+"="+str);
//			if(StringUtils.isBlank(str)){
//				return false;
//			}else if(str.indexOf("连接成功")>-1){
//				return true;
//			}else{
//				return false;
//			}
		} catch (EtermException e) {
			logger.error("连接pid错误",e);
		}
	}
	
	public void disconnect(String userid,String no){
		try {
			VecsbCache vecsbCache = cacheService.get("2012");
			String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService etermService = new WebEtermService(url);
			String xml = "<INPUT><COMMAND>VEPIDMGR</COMMAND><PARA><USER>"+userid+"</USER><CHILDCMD>DisconnectPID</CHILDCMD><PIDNO>"+no+"</PIDNO></PARA></INPUT>";	
			String str = etermService.generalCmdProcess(xml);
			logger.debug("断开pid返回"+userid+":"+no+"="+str);
		} catch (EtermException e) {
			logger.error("断开pid失败",e);
		}
	}
}
