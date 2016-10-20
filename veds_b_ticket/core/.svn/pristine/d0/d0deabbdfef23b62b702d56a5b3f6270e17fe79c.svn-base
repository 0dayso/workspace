package org.vetech.core.business.pid.api.detrf;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class DetrF {
	public String detrF(DetrFParam param) throws EtermException {
		valid(param);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String data = etermService.detrF(param.getCommand(), param.getUserid());
		return data;
	}

	private void valid(DetrFParam param) throws EtermException {
		if (StringUtils.isBlank(param.getCommand())) {
			throw new EtermException("Detr指令不能为空");
		}
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
	}
}
