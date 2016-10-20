package org.vetech.core.business.pid.api.tknetrdx;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;
import org.vetech.core.modules.utils.XmlUtils;

public class TkneTrdx {
	
	public TkneTrdxResult tkneTrdx(VeTkneTrdxParam param) throws EtermException {
		TkneTrdxResult result = new TkneTrdxResult();
		String paramXml = param.toXml();
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String rtnXml = StringUtils.trimToEmpty(etermService.generalCmdProcess(paramXml));
		VeTkneTrdxResponse response = (VeTkneTrdxResponse)XmlUtils.fromXml(rtnXml, VeTkneTrdxResponse.class);
		try {
			BeanUtils.copyProperties(result, response);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
