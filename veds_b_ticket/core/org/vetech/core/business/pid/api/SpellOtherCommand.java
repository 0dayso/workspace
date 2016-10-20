package org.vetech.core.business.pid.api;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ObjectUtils;
import org.vetech.core.business.pid.pidbean.CommandBean;

/**
 * 处理其他指令
 * 
 * @author gengxianyan
 * @version [版本号, Apr 25, 2012]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class SpellOtherCommand {
	
	/**
	 * 乘机人手机号码写入CTCT项
	 * @param bean 参数传递对象
	 * @param seat 指令封装对象
	 * @throws Exception [参数说明]
	 */
	public static void addCTCT(CommandBean bean,Seat seat) throws Exception{
		//值为0，表示不把手机号码写入CTCT项，值为1：表示把乘机人手机号码写入CT项，值为2：表示把分销商联系电话写入CT项。
		String cs7738 = "1";//Function.acsb("7738", bean.getZgscompid(), "", "");
		
		if("0".equals(cs7738)) return;
		
		String hkgs = StringUtils.replace(bean.getHbh()[0], "*", "").substring(0, 2);
		String param1 = hkgs + " CTCT"; 
		
		
		if("2".equals(cs7738)){
			if(StringUtils.isNotEmpty(bean.getCt_phoneno())){
				String sjhm = bean.getCt_phoneno();
				
				seat.addOSI(param1, sjhm);
			}
		}else if("1".equals(cs7738)){
			//每个乘机人的手机号码不为空时，都必须写入CTCT项
			if(!ArrayUtils.isEmpty(bean.getSjhm())){//成人
				for(int i=0;i<bean.getSjhm().length;i++){
					if(StringUtils.isNotEmpty(bean.getSjhm()[i])){
						String sjhm = bean.getSjhm()[i];
						
						seat.addOSI(param1, sjhm);
					}
				}
			}
			if(!ArrayUtils.isEmpty(bean.getEtsjhm())){//儿童
				for(int i=0;i<bean.getEtsjhm().length;i++){
					if(StringUtils.isNotEmpty(bean.getEtsjhm()[i])){
						String sjhm = bean.getEtsjhm()[i];
						
						seat.addOSI(param1, sjhm);
					}
				}
			}
		}
	}
	
	/**
	 * 添加餐食偏好组
	 * @param command 参数传递对象
	 * @param seat 指令封装对象
	 * @param i 序号
	 * @param p 乘机人序号
	 */
	public static void addCsph(CommandBean command,Seat seat,int i,String p){
		if (!ObjectUtils.isEmpty(command.getCrcsph()) && command.getCrcsph().length > i
				&& StringUtils.isNotBlank(command.getCrcsph()[i])) {
			String ssrCs = command.getCrcsph()[i] + " " + command.getHkgs()[0] + " HK1 " + command.getCfcity()[0]
			             + command.getDdcity()[0] + " " + command.getAirNo()[0] + " " + command.getCw()[0] + command.getCfdate()[0] + "/" + p;
			seat.addSSR(ssrCs);
		}
	}
	
	/**
	 * 添加座位偏好组
	 * @param command 参数传递对象
	 * @param seat 指令封装对象
	 * @param i 序号
	 * @param p 乘机人序号
	 */
	public static void addZwph(CommandBean command,Seat seat,int i,String p){
		if (!ObjectUtils.isEmpty(command.getCrzwph()) && command.getCrzwph().length > i
				&& StringUtils.isNotBlank(command.getCrzwph()[i])) {
			String ssrZw = command.getCrzwph()[i] + " " + command.getHkgs() + " HK1 " + command.getCfcity()[0]
			                                                                                                + command.getDdcity()[0] + " " + command.getAirNo()[0] + " " + command.getCw()[0] + command.getCfdate()[0] + "/" + p;
			seat.addSSR(ssrZw);
		}
	}
	
	/**
	 * 生成的新儿童PNR关联成人PNR编码指令
	 * @param command 参数传递对象
	 * @param seat 指令封装对象
	 */
	public static void addSSR_OTHS(CommandBean command,Seat seat){
		
		String hkgs = command.getHkgs()[0];
		String pnrno = command.getYpnrno();
		
		if(StringUtils.isNotBlank(hkgs) && StringUtils.isNotBlank(pnrno)){
			try {
				seat.addSSR_OTHS(hkgs, pnrno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 添加三方协议号
	 * (预订时，只有南航才进行指令拼装，其他航在PAT的时候，由PID自动追加)
	 * @param command 指令参数封装对象
	 * @param seat 指令封装对象
	 * @throws Exception [参数说明]
	 */
	public static void addSfxyh(CommandBean command,Seat seat) throws Exception{
		if(MapUtils.isNotEmpty(command.getOtherMap())){
			String sfxyh = command.getOtherMap().get("SFXYH");
			if(StringUtils.isNotEmpty(sfxyh)){
				int ifXyh = 0;
				for(String hkgs : command.getHkgs()){
					if("CZ".equals(hkgs)) ifXyh ++;
				}
				if(ifXyh == command.getHkgs().length){
					seat.rmkic("IC", "CZ/"+sfxyh);
				}
			}
		}
	}
	
}
