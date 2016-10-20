package org.vetech.core.business.pid.api.pat;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

/**
 * Pat指令
 * @author wangxuexing
 */
public class Pat {
	private PatParam patParam;
	public Pat(PatParam patParam) throws EtermException{
		this.patParam = patParam;
		valid();
	}
	
	public String pat() throws EtermException{
		WebEtermService etermService = new WebEtermService(patParam.getUrl());
		String data;
		try {
			data = etermService.pat(patParam.getSs(), patParam.getPatCommand(), patParam.getUserid());
		} catch (Exception e) {
			throw new EtermException(patParam.getPatCommand() + "调用webservice异常"
					+ e.getMessage());
		}
		return PatParser.parsePatTopjNormal(data);
	}
	
	private void valid() throws EtermException{
		if (StringUtils.isBlank(patParam.getSs())) {
			throw new EtermException("Ss参数不能为空");
		}
		if (StringUtils.isBlank(patParam.getPatCommand())) {
			throw new EtermException("Pat命令不能为空");
		}
	}
}
