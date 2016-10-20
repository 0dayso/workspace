package org.vetech.core.business.pid.api.pidgl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
@Component
public class UserService {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CsbCacheService cacheService;
/**
 * 新增用户
 * @param strCode		[用户编码]
 * @param strUserName	[用户名称]
 * @param strPassword	[用户密码]
 * @param strGroupId	[用户将进入PID组编号]
 * @return
 * @throws EtermException 
 * @throws EtermException 
 * 
 */
	public String addUser(String strCode,String strUserName,String strPassword,String strGroupId) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
		WebEtermService wes = new WebEtermService(url);
		String info = wes.addUser2(strCode,strUserName,strPassword,strGroupId);
		logger.info(info);
			if(!StringUtils.isBlank(info)){
				if(info.indexOf("OK")>-1){
					info = "";
				}
			}
		
		return info;
	}
	
	
	/**
	 * 修改用户密码
	 * @param strUser				[用户编码]
	 * @param strNewPassword		[用户密码]
	 * @return
	 * @throws EtermException 
	 * @throws EtermException
	 */
	public String an(String strUser, String strNewPassword) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
		WebEtermService wes = new WebEtermService(url);
		String info = wes.an(strUser,strNewPassword);
		logger.info(info);
		if(!StringUtils.isBlank(info)){
			if(info.indexOf("OK")>-1){
				info = "";
			}
		}
	
		return info;
	} 
	
/**
 * 修改用户所属组
 * @param strCode			[用户编码]
 * @param strOldGroupID		[用户原来所属PID组编号]
 * @param strNewGroupID		[用户将要进入PID组编号]
 * @return
 * @throws EtermException 
 * @throws EtermException
 */
	public String switchGroup(String strCode,String strOldGroupID,String strNewGroupID) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
			WebEtermService	wes = new WebEtermService(url);
			String info = wes.switchGroup(strCode, strOldGroupID, strNewGroupID);
			logger.info(info);
			if(!StringUtils.isBlank(info)){
				if(info.indexOf("OK")>-1){
					info = "";
				}
			}
		
			return info;
	}
	
	/**
	 * 修改用户名或用户口令
	 * @param strCode				[用户编码]
	 * @param strUserName			[用户名称]
	 * @param strPassword			[用户密码]
	 * @return
	 * @throws EtermException
	 */
	public String modifyUser(String strCode,String strUserName,String strPassword) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
		WebEtermService	wes = new WebEtermService(url);
		String info = wes.modifyUser(strCode, strUserName, strPassword);
		logger.info(info);
		if(!StringUtils.isBlank(info)){
			if(info.indexOf("OK")>-1){
				info = "";
			}
		}
	
		return info;
	}
	
	/**
	 * 根据用户编号查询用户是否存在
	 * @param strCode			[用户编码]
	 * @return
	 * @throws EtermException
	 */
	public String getUserById(String strCode) throws EtermException{
		String pidZ = "";
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
		WebEtermService	wes = new WebEtermService(url);
		String info = wes.generalCmdProcess("<INPUT><COMMAND>VERIGHTMGR</COMMAND><PARA><CHILDCMD>GetUserProperties</CHILDCMD><USER>WEB01</USER><USERBH>"+strCode+"</USERBH></PARA></INPUT>");
		String UserId = StringUtils.substringBetween(info, "<BH>", "</BH>");		
		if(!StringUtils.isBlank(UserId)){
			pidZ = StringUtils.substringBetween(info, "<GroupBH>", "</GroupBH>");
		}
		
		
		
		return pidZ;
	}
	
	/**
	 * 删除用户
	 * @param strCode			[用户编码]
	 * @return
	 * @throws EtermException
	 */
	public String delUser(String strCode) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2();
		WebEtermService	wes = new WebEtermService(url);
		String info = wes.delUser(strCode);
		logger.info(info);
		if(!StringUtils.isBlank(info)){
			if(info.indexOf("OK")>-1){
				info = "";
			}
		}
	
		return info;
	}
	

}
