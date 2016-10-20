package org.vetech.core.business.pid.api.autoetdz;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * 
 * 国内票自动出票 BSPET自动出票 选择pat低价
 * 
 * @author houya
 * @version [版本号, May 29, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class EtdzEterm {

	EtdzParam etdzParam;
	
	private String url;

	private String etermdata;

	public EtdzEterm(EtdzParam etdzParam) {
		String ip=etdzParam.getIp();
		String port=etdzParam.getPort();
		if(ip.startsWith("http")){
			this.url = ip + ":" + port;
		}else {
			this.url = "http://" + ip + ":" + port;
		}
		//etdzParam.setPersons("1");
		etdzParam.setUrl(url);
		etdzParam.setIstest("0");
		etdzParam.setSelectlowprice(StringUtils.isBlank(etdzParam.getSelectlowprice()) ? "0" : etdzParam.getSelectlowprice());
		etdzParam.setOffice(StringUtils.trimToEmpty(etdzParam.getOffice()));
		this.etdzParam=etdzParam;
	}
	
	public String etdz() throws Exception {
		valid(etdzParam);
		try{
			WebEtermService service=new WebEtermService(url);
			String xml = etdzParam.toXml();
			etermdata = StringUtils.trimToEmpty(service.generalCmdProcess(xml));
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return etermdata;
	}
	
	private void valid(EtdzParam etdzParam) throws EtermException {
	    if (StringUtils.isBlank(etdzParam.getIp())) {
			throw new EtermException("IP不能为空，请检查配置！");
		}
		if (StringUtils.isBlank(etdzParam.getPort())) {
			throw new EtermException("端口不能为空，请检查配置！");
		}
		if (StringUtils.isBlank(etdzParam.getUserid())) {
			throw new EtermException("用户不能为空，请检查配置！");
		}
		if (StringUtils.isBlank(etdzParam.getPass())) {
			throw new EtermException("密码不能为空，请检查配置！");
		}
		if (StringUtils.isBlank(etdzParam.getDdbh())) {
			throw new EtermException("订单编号不能为空！");
		}
	}

}
