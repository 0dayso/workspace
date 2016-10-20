package org.vetech.core.business.pid.api.pnrpat2;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class PNRPAT2 {
	/**
	 * 提取大编内容 shenjx 2012-3-17添加
	 * @throws EtermException 
	 */
	public Pnr PNRPAT2(PNRPAT2Param param) throws Exception {
		valid(param);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String pnrno = param.getPnrno();
		String pat = param.getPat();
		String bh = param.getPidUser();
		String office = param.getOffice();
		String etermdata = etermService.PNRPAT2(pnrno, pat, bh, office);
		if(StringUtils.isBlank(etermdata)){
			throw new EtermException("无法从PID主机获取信息,请检查网络连接是否正常或联系网络管理员");
		}
		return PNRPAT2Parser.parser(etermdata);
	}
	
	private void valid(PNRPAT2Param param) throws Exception {
		if (StringUtils.isBlank(param.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
		if (StringUtils.isBlank(param.getPat())) {
			throw new EtermException("PAT指令不能为空");
		}
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
	}
}
