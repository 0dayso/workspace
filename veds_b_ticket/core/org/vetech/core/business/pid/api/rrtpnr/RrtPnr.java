package org.vetech.core.business.pid.api.rrtpnr;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.business.pid.api.WebEtermService;
import org.vetech.core.business.pid.api.pnrpat2.Pnr;
import org.vetech.core.business.pid.api.rtpnr.PNRParser;
import org.vetech.core.business.pid.exception.EtermException;
/**
 * 通过大编码提取PNR内容
 * @author wangshengliang
 *
 */


public class RrtPnr {
	/**
		<P>
			<R>1</R>
			<N>JQ5TFJ</N>
			<T>2</T>
			<Q>   *THIS PNR WAS ENTIRELY CANCELLED*
		006     HDQCA 9983 0403 13AUG /RLC5 6/QUEUED FOR MANUAL HANDLING/NONACTIONABLE          M   
		     X1.刘少杰(001) X2.闫东(001) JQ5TFJ 
		001 X3.  MU2542 Y   TU25OCT  WUHCAN DL2   0940 1125          E T2-- 
		       NN(001)  DK(001)  HK(001)  NO(004)  DL(005)  
		001 X4.SHA/T SHA/T0571-88136060/ASLAN TRAVEL(SHANGHAI)CO.,LTD /JIANGLING
		         ABCDEFG
		001 X5.BY OPT 16080913503850 2016/08/10 1726A   
		001 X6.TL/2300/10AUG16/SHA384   
		001 X7.SSR FOID MU XX1 NI420983199204200039/P1  
		       HK(001)   XX(005)                                                       +001 X8.SSR FOID MU XX1 NI421125199212180094/P2                                 -       HK(001)   XX(005)
		004 X9.SSR OTHS 1E CNL DUE TO TL
		003X10.SSR ADTK 1E BY SHA13AUG16/1726 OR CXL MU2542 Y25OCT  
		001X11.OSI MU CTCT15527548542   
		001X12.OSI MU CTCT15527546746   
		002X13.RMK CA/NFVPC6
		001 14.SHA384                                                                  +001     SHA384 44867 0926 10AUG16                                              -002     HDQCA 9983 0926 10AUG16 /RLC1   
		003     HDQCA 9983 0926 10AUG16 /2  
		001/004 SSR FQTV MU XX1 WUHCAN 2542 Y25OCT MU613012026401   
		       HK(001)   XX(004)
		001/004 SSR FQTV MU XX1 WUHCAN 2542 Y25OCT MU643012161413   
		       HK(001)   XX(004)
		004     HDQCA 9983 1502 10AUG16 /5  
		005     SHA384 44867 0403 13AUG 
		006     HDQCA 9983 0403 13AUG /RLC5 6/QUEUED FOR MANUAL HANDLING/NONACTIONABLE          M   
			</Q>
			<B>NFVPC6</B>
			<C>SHA384,1,,,,,2016-08-10 23:00:00,0,2,0</C>
			<TL/>
			<OFFICE>SHA384</OFFICE>
			<AUTH/>
			<CTCT>15527546746</CTCT>
			<TIMEOFNOSEAT>2016-08-13 17:26:00</TIMEOFNOSEAT>
			<HD>1|0,MU2542,Y,25OCT,WUH,CAN,DL,2016-10-25 09:40:00,2016-10-25 11:25:00,738,,T2,2,</HD>
			<CJR>2|0,刘少杰,420983199204200039,NI,1,,,,,,,,,,,,|1,闫东,421125199212180094,NI,1,,,,,,,,,,,,</CJR>
		</P>
	 */
	public Pnr rrtPnr(RrtPnrParam param) throws EtermException {
		valid(param);
		WebEtermService etermService = new WebEtermService(param.getUrl());
		String data = etermService.RRTPNR(param.getBigpnrno(), param.getHbh(), param.getCfdate(), param.getUserid(), param.getOfficeId());
		try {
			if(StringUtils.isNotBlank(data)){
				return PNRParser.parser(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void valid(RrtPnrParam param) throws EtermException {
		if (StringUtils.isBlank(param.getBigpnrno())) {
			throw new EtermException("大编码不能为空");
		}
		
		if (StringUtils.isBlank(param.getUserid())) {
			throw new EtermException("用户编号不能为空");
		}
		
		if(StringUtils.isBlank(param.getHbh())){
			param.setHbh("CZ2312");
		}
		
		if(StringUtils.isBlank(param.getCfdate())){
			param.setCfdate("+");
		}
	}
	
	public static void main(String[] args) {
		RrtPnr rrt=new RrtPnr();
		RrtPnrParam param=new RrtPnrParam();
		param.setBigpnrno("NFVPC6");
		param.setUserid("16072810380935");
		param.setUrl("http://192.168.110.127:8088");
		try {
			System.out.println(rrt.rrtPnr(param));
		} catch (EtermException e) {
			e.printStackTrace();
		}
	}
}
