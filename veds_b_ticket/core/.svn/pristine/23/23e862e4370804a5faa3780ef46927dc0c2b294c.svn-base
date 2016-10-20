package org.vetech.core.business.pid.api.pidgl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.CsbCacheService;
import org.vetech.core.business.cache.VecsbCache;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;

@Service
public class YhzServiceImpl {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CsbCacheService cacheService;
	/**
	 * 修改和新增用戶組
	 * @param yhz
	 * @param method
	 * @param userid
	 */
	public String ModifyOrAddGroup(Yhz yhz,String method,String userid,String shybh) throws EtermException{
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2(); 
		Boolean flag = false;
		WebEtermService etermService = new WebEtermService(url);
		StringBuffer xml = new StringBuffer("<INPUT><COMMAND>VERIGHTMGR</COMMAND><PARA><CHILDCMD>ModifyOrAddGroup</CHILDCMD><USER>" + userid + "</USER>");
		xml.append(XmlUtils.xmlnode("EDITEDTYPE", method));
		xml.append(XmlUtils.xmlnode("GroupName", yhz.getYhzmc()));
		if (method.equals("ADDED")) {
			xml.append(XmlUtils.xmlnode("GROUPCODE", shybh + yhz.getYhzbh()));
		} else {
			xml.append(XmlUtils.xmlnode("GROUPCODE", yhz.getYhzbh()));
		}
		xml.append(XmlUtils.xmlnode("CanEtermMsg", yhz.getEtermmsg()));
		xml.append(XmlUtils.xmlnode("PIDZID", yhz.getPidzbh()));
		xml.append(XmlUtils.xmlnode("PIDID", yhz.getPidbh()));
		xml.append("</PARA></INPUT>");
		String info = etermService.generalCmdProcess(xml.toString());
		logger.info(info);
		if (StringUtils.isNotBlank(info)) {
			if (info.indexOf("成功") > -1) {
				return null;
			} else {
				String msg = info.substring(info.indexOf("<DESCRIPTION>") + 13, info.indexOf("</DESCRIPTION>"));
				return msg;
			}
		} else {
			return "系统异常！";
		}
	}
	/**
	 * 获取商户编号所有用户组名称
	 * @param shbh
	 * @return
	 */
	public List getAllGroups(String shbh,String userid,boolean isMx){
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2(); 
		List<Yhz> list=new ArrayList();
		try{
			WebEtermService etermService = new WebEtermService(url);
			String data=etermService.getAllGroups();
			if (StringUtils.isNotBlank(data)) {
				String yhzxx=data.substring(data.indexOf("<GROUPS>")+8,data.indexOf("</GROUPS>"));
				String[] arr1=yhzxx.split(",");
				for(String str:arr1){
					String[] arr2=str.split(":");
					if(arr2[0].startsWith(shbh)){
						if(isMx){
							list.add(GetGroupPropertiesByYhzbh(arr2[0],userid));
						}else{
							Yhz yhz = new Yhz();
							yhz.setYhzbh(arr2[0]);
							yhz.setYhzmc(arr2[1]);
							list.add(yhz);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("获取用户组信息失败！",e);
		}
		return list;
	}
	/**
	 * 根据用户组编号获取该用户组详细信息
	 * @param yhzbh
	 * @param userid
	 * @return
	 */
	public Yhz GetGroupPropertiesByYhzbh(String yhzbh,String userid){
		VecsbCache vecsbCache = cacheService.get("2012");
		String url = "http://"+vecsbCache.getCsz1()+":"+vecsbCache.getCsz2(); 
		Yhz yhz=new Yhz();
		try{
			WebEtermService etermService = new WebEtermService(url);
			String xml="<INPUT><COMMAND>VERIGHTMGR</COMMAND><PARA><CHILDCMD>GETGROUPPROPERTIES</CHILDCMD><USER>"+userid+"</USER><BH>"+yhzbh+"</BH></PARA></INPUT>";
			String data=etermService.generalCmdProcess(xml);
			yhz.setYhzbh(yhzbh);
			String yhzmc = StringUtils.substring(data, data.indexOf("<GroupName>")+11, data.indexOf("</GroupName>"));
			yhz.setYhzmc(yhzmc);
			String etermmsg = StringUtils.substring(data, data.indexOf("<CanEtermMsg>")+13, data.indexOf("</CanEtermMsg>"));
			yhz.setEtermmsg(etermmsg);
			String pidzbh = StringUtils.substring(data, data.indexOf("<PIDZID>")+8, data.indexOf("</PIDZID>"));
			yhz.setPidzbh(pidzbh);
			String pidbh = StringUtils.substring(data, data.indexOf("<PIDID>")+7, data.indexOf("</PIDID>"));
			yhz.setPidbh(pidbh);
		}catch(Exception e){
			logger.error("获取用户组信息失败！",e);
		}
		return yhz;
	}
}
