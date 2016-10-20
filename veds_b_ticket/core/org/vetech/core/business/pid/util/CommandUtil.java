package org.vetech.core.business.pid.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.Seat;
import org.vetech.core.business.pid.pidbean.CommandBean;

/**
 * 指令工具类
 * 
 * @author  gengxianyan
 * @version  [版本号, May 21, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class CommandUtil {
	
	private Seat seat;
	
	public CommandUtil(){}
	
	public CommandUtil(Seat seat){
		this.seat = seat;
	}

	/**
	 * 批次组装SS指令
	 */
	public void addAirSeg(CommandBean command) throws Exception {
		String[] cfdate = command.getCfdate();
		String preddcity = "";
		String[] saCommand = new String[cfdate.length];
		int count = 0;
		for (int i = 0; i < cfdate.length; i++) {
			String hbh = command.getHbh()[i];
			hbh = hbh.replace("*", "");
			seat.addAirSeg(hbh, command.getCw()[i], command.getCfcity()[i], command.getDdcity()[i], command
					.getRs(), command.getCfdate()[i], command.getIfnoseat()[i]);
			if(StringUtils.isNotEmpty(command.getIfqk())){//支持缺口程
				if(StringUtils.isNotEmpty(preddcity) && !preddcity.equals(command.getCfcity()[i])){
					saCommand[count] = preddcity + command.getCfcity()[i];
					count ++ ;
				}
			}
			preddcity = command.getDdcity()[i];
		}
		
		for(String sa : saCommand){
			if(StringUtils.isNotEmpty(sa)) seat.addSA(sa);
		}
	}

	/**
	 *  批次组装SS指令【婴儿】
	 */
	public void addInfoAirSeg(CommandBean command) throws Exception {
		String[] cfdate = command.getCfdate();
		for (int i = 0; i < cfdate.length; i++) {
			Map<String, String> other = command.getOtherMap();
			String hbh = command.getHbh()[i];
			hbh = hbh.replace("*", "");
			seat.addInfoAirSeg(command.getHkgs()[i], command.getCfcity()[i], command.getDdcity()[i], hbh,
					command.getCw()[i], cfdate[i], other.get("YEXM"), other.get("YECSRQ"), other.get("YEP"));
		}
	}
	/**
	 * 国际儿童添加姓名段
	 * @param cjrxm
	 * @param hkgs 航空公司
	 * @param sexCode
	 * @param birthday ddMMYY
		国际：
		CZ S7:姓名
		CI:姓名 + MISS/MSTR
		LH：姓名+（ddmmmyy）
		NX:姓名+CHDDOB(ddMMMyy)
		PR：姓名+MIST/MSTR
		AC OZ SQ KE NH GA JL UL BR：姓名+空格+MISS/MSTR
		其他：姓名+空格+CHD
	 * @throws Exception 
	 */
	public void addChildGj(String cjrxm,String hkgs,String sexCode,String birthday) throws Exception{
		/*if(cjrxm.endsWith(" "+sexCode)){
			cjrxm = StringUtils.substring(cjrxm, 0,cjrxm.indexOf(" "+sexCode));
		}
		
		if("CZ,S7".contains(hkgs)){
			seat.addChildGj(cjrxm, "");
		}else if("CI".equals(hkgs)){
			seat.addChildGj(cjrxm, sexCode);
		}else if("LH".equals(hkgs)){
			seat.addChildGj(cjrxm, "("+birthday+")");
		}else if("NX".equals(hkgs)){
			seat.addChildGj(cjrxm, "CHDDOB("+birthday+")");
		}else if("PR".equals(hkgs)){
			if("MISS".equals(sexCode)){
				sexCode = "MIST";
			}
			seat.addChildGj(cjrxm, sexCode);
		}else if("AC,OZ,SQ,KE,NH,GA,JL,UL,BR".contains(hkgs)){
			seat.addChildGj(cjrxm+" ", sexCode);
		}else{
			seat.addChildGj(cjrxm+" ", "CHD");
		}*/
		seat.addChildGj(cjrxm, "");
	}
	
	/**
	 * 添加护照证件项【国际】
		NX LH：姓名
		CI ：姓名+MISS/MSTR
		AC OZ SQ KE NH GA JL UL BR：姓名+空格+MISS/MSTR
		PR：姓名+MIST/MSTR
		CZ：姓名
		其他：姓名+空格+CHD
	 */
	public void addSSR_DOCSGJ(String hkgs,String zjlx,String fzNational,String zjhm,String gj,String csrq,String sex,String zjyxq,String firstName,String lastName,String midName,String p) throws Exception{
		/*if("NX,LH".contains(hkgs)){
		}else if("CI".contains(hkgs)){
		}else if("AC,OZ,SQ,KE,NH,GA,JL,UL,BR".contains(hkgs)){
			lastName += " ";
		}else if("PR".contains(hkgs)){
			if("MISS".equals(sex)){
				sex = "MIST";
			}
		}else{
			lastName += "";
		}*/
		seat.addSSR_DOCS(hkgs, zjlx, fzNational, zjhm, gj, csrq, sex, zjyxq, firstName, lastName, midName, p);
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}
}
