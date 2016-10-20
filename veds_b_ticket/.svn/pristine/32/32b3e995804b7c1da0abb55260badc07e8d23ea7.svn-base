package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.concurrent.Callable;

import org.apache.commons.lang.math.NumberUtils;
import org.vetech.core.business.pid.api.patbyoffice.PatByOffice;
import org.vetech.core.business.pid.api.patbyoffice.PatByOfficeParam;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.business.pid.pidbean.AvCabinModel;

public class PatCallable implements Callable<Double>{
	private AvCabinModel cabin;
	private JpPz jpPz;
	private String cjrlx;
	private String hc;
	private String qfrq;
	private String pidYh;
	public PatCallable(AvCabinModel cabin,JpPz jpPz,String cjrlx,String hc,String qfrq,String pidYh){
		this.cabin=cabin;
		this.jpPz=jpPz;
		this.cjrlx=cjrlx;
		this.hc=hc;
		this.qfrq=qfrq;
		this.pidYh=pidYh;
	}
	@Override
	public Double call() throws Exception {
		try {
			PatByOfficeParam param=new PatByOfficeParam();
			param.setCjrlx(cjrlx);
			param.setCw(cabin.getCw());
			param.setHbh(cabin.getHbh());
			param.setHc(hc);
			param.setOffice(jpPz.getOfficeid());
			param.setQfrq(qfrq);
			param.setUrl(jpPz.getPzIp()+":"+jpPz.getPzPort());
			param.setUserid(pidYh);
			String patNr=PatByOffice.patByOffice(param);
			String patJg=PatByOffice.parsePatTopjNormal(patNr);
			String pj=patJg.split("_")[0];
			double zdj=NumberUtils.toDouble(pj,0);
			if(zdj==0){
				return null;
			}
			return zdj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
