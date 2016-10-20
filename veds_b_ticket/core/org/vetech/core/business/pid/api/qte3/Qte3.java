package org.vetech.core.business.pid.api.qte3;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.exception.EtermException;

public class Qte3 {

	public Qte3Result qte3(Qte3Param param) throws EtermException {
		valid(param);
		String bstrInputXML = param.toXml();
		System.out.println(bstrInputXML);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String data = etermService.QTE3(param.toXml(), param.getUserid());
		System.out.println(data);
		return Qte3Result.parseQte(data);
	}

	private void valid(Qte3Param param) throws EtermException {
		if (StringUtils.isBlank(param.getPnrno())) {
			throw new EtermException("PNR不能为空");
		}
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
		if (StringUtils.isBlank(param.getHkgs())) {
			throw new EtermException("航空公司不能为空");
		}
	}
	public static void main(String[] args) {
		Qte3Param param=new Qte3Param();
		param.setHkgs("KE");
		param.setPnrno("KFFD4Z");
		param.setUrl("http://192.168.1.69:8088");
		param.setUserid("8635");
		Qte3 qte3=new Qte3();
		try {
			Qte3Result qteResult =  qte3.qte3(param);
			System.out.println("PJ:"+qteResult.getPj()+"|X:"+qteResult.getTax()+"|T:"+qteResult.getTotal()+"|C:"+qteResult.getCommission());
		} catch (EtermException e) {
			e.printStackTrace();
		}
	}
}
