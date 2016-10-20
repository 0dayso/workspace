package org.vetech.core.business.pid.util;


import org.vetech.core.business.pid.api.BuilderCommand;
import org.vetech.core.business.pid.api.BuilderDetectorComposite;
import org.vetech.core.business.pid.api.CommandAbstract;
import org.vetech.core.business.pid.pidbean.PidAvhBean;
import org.vetech.core.business.pid.pidbean.PidAvhResult;

/**
 * 机票查询工具类
 * @author  gengxianyan
 * @version  [版本号, Apr 17, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class BookSearchUtil {
	/**
	 * 航班查询调用底层实现封装函数
	 * @param avhBean 
	 * @return PidAvhResult
	 * @throws PidException [参数说明]
	 */
	public PidAvhResult excute(PidAvhBean avhBean) throws Exception{
		BuilderCommand builderCommand=new BuilderDetectorComposite(avhBean);
		builderCommand.builderCommandHandler();
		CommandAbstract commandAbstract = builderCommand.getCommandHandler();
		return (PidAvhResult)commandAbstract.excute();//声明返回结果集
	}
	
	
	/**
	 * 根据标识获取查询方式
	 * @param bookType 
	 * @return String[] 0 AVH接口类 1 FD接口类
	 */
	public String[] searchMethod(int bookType){
		String[] result = new String[2];
		switch (bookType) {
		case 0:
			result[0] = CommandAbstract.ETERMAVH;
			result[1] = CommandAbstract.ETERMFD;
			break;
		case 1:
			result[0] = CommandAbstract.IBEAVH;
			result[1] = CommandAbstract.IBEFD;
			break;
		case 2:
			result[0] = CommandAbstract.KYIBEAVH;
			result[1] = CommandAbstract.KYIBEFD;
			break;
		case 3:
			result[0] = CommandAbstract.KUXUNAVHSEARCH;
			result[1] = CommandAbstract.ETERMFD;
			break;
		case 4:
			result[0] = CommandAbstract.HLIBEAVH;
			result[1] = CommandAbstract.HLIBEFD;
			break;
		case 5:
			result[0] = CommandAbstract.YLKYAVHSEARCH;
			result[1] = CommandAbstract.ETERMFD;
			break;
		case 6:
			result[0] = CommandAbstract.SSLAVHSEARCH;
			result[1] = CommandAbstract.ETERMFD;
			break;
		case 8:
			result[0] = CommandAbstract.CKGKYIBEAVH;
			result[1] = CommandAbstract.CKGKYIBEFD;
			break;
		default:
			result[0] = CommandAbstract.ETERMAVH;
			result[1] = CommandAbstract.ETERMFD;
			break;
		}
		return result;
	}
	
	/**
	 * 获取时间差
	 * @param begin 
	 * @return [参数说明]
	 */
	public static String getTimeCha(long begin){
		long end = System.currentTimeMillis();
		 return String.valueOf(end - begin);
	}
//	
//	/**
//	 * 获取调用PID的URL
//	 * @return [参数说明]
//	 */
//	public static String getVepidUrl(JpPz jppz){
//		return jppz.getPzIp()+":"+jppz.getPzPort();
//	}
	
//	/**
//	 * 获取平台参数表
//	 * @param csbh 
//	 * @param ydhm 
//	 * @return 参数值 
//	 */
//	public static String getCsValue(String csbh,String ydhm){
//		
//		if(StringUtils.isBlank(ydhm)) ydhm = "YDHM";
//		
//		Ve_cspzService ve_cspzService = (Ve_cspzService)SpringContextUtil.getBean("ve_cspzService",Ve_cspzService.class);
//		
//		Ve_cspz ve_cspz = ve_cspzService.getById(csbh);
//		if(ve_cspz != null){
//			if("YDHM".equals(ydhm)){
//				return ve_cspz.getYdhm();
//			}
//			if("YW".equals(ydhm)){
//				return ve_cspz.getYw();
//			}
//		}
//		return null;
//	}
	
//	/**
//	 * 获取平台参数表
//	 * @param csbh 
//	 * @param ydhm 
//	 * @param defaultVal 
//	 * @return 参数值
//	 */
//	public static String getCsValue(String csbh,String ydhm,String defaultVal){
//		
//		if(StringUtils.isBlank(ydhm)) ydhm = "YDHM";
//		
//		Ve_cspzService ve_cspzService = (Ve_cspzService)SpringContextUtil.getBean("ve_cspzService",Ve_cspzService.class);
//		
//		Ve_cspz ve_cspz = null;
//        try {
//            ve_cspz = ve_cspzService.getById(csbh);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//		if(ve_cspz != null){
//			if("YDHM".equals(ydhm)){
//				return ObjectUtils.toString(ve_cspz.getYdhm(),defaultVal);
//			}
//			if("YW".equals(ydhm)){
//				return ObjectUtils.toString(ve_cspz.getYw(),defaultVal);
//			}
//		}
//		return defaultVal;
//	}
	
//	/**
//	 * 获取商户参数表
//	 * @param csbh 参数编号，取自于Sh_cp_csBean中维护的
//	 * @param shzh 商户账号，不传默认取当前登录商户
//	 * @param csz  所取参数值，不传默认取第一个参数值
//	 * @return 参数值
//	 */
//	public static String getShCsValue(String csbh,String shzh,String csz){
//		
//		if(StringUtils.isBlank(csbh)){
//			return null;
//		}
//		
//		if(StringUtils.isBlank(shzh)){
//			Sh_yhb sh_yhb = AirsUtil.getBuser();
//			shzh = sh_yhb.getShzh();
//		}
//		
//		if(StringUtils.isBlank(csz)){
//			csz = "1";
//		}
//		
//		Sh_cp_csService sh_cp_csService = (Sh_cp_csService)SpringContextUtil.getBean("sh_cp_csService",Sh_cp_csService.class);
//		
//		try {
//			Sh_cp_cs csBean = sh_cp_csService.find(shzh, csbh);
//			
//			if(csBean == null){
//				return null;
//			}
//			
//			if("1".equals(csz)){
//				return StringUtils.trimToEmpty(csBean.getCs_value1());
//			}else if("2".equals(csz)){
//				return StringUtils.trimToEmpty(csBean.getCs_value2());
//			}else if("3".equals(csz)){
//				return StringUtils.trimToEmpty(csBean.getCs_value3());
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
//	/**
//	 * 根据商户编号获取 自动K位的PID URL地址
//	 * 
//	 * 如果商户没有设置此参数返回空字符串
//	 * @param shbh 
//	 * @return http://192.168.1.97:8088格式字符
//	 */
//	public static String getAutoK_PidUrl(String shbh){
//	    
//		Sh_cp_csService sh_cp_csService = (Sh_cp_csService)SpringContextUtil.getBean("sh_cp_csService",Sh_cp_csService.class);
//		return sh_cp_csService.getVepidUrl(shbh);
//	}
	

//	/**
//	 * 根据商户编号获取 自动K位 PID账户、OFFICE号
//	 * 
//	 * 返回Map<String,String>
//	 * 其中KEY=ACCOUNT(账户),OFFICE(OFFICE号)
//	 * @param shbh 
//	 * @return Map<String,String> 
//	 */
//	public static Map<String,String> getAutoK_AccountOffice(String shbh){
//
//		Sh_cp_csService sh_cp_csService = (Sh_cp_csService)SpringContextUtil.getBean("sh_cp_csService",Sh_cp_csService.class);
//		return sh_cp_csService.getVepidAccountOffice(shbh);
//	}
	
//	/**
//	 * 判断两段航班时间差是否超过设置的间隔时间
//	 * @param depDateSec 第二段起飞日期，格式：2013-07-12
//	 * @param depTime 第二段起飞时间，格式：12:00
//	 * 
//	 * @param depDateFir 第一段起飞日期，格式：2013-07-12
//	 * @param arrTime 第一段到达时间，格式：01:00 + 1 或者 21:00
//	 * 
//	 * @param intervalTime 间隔时间，单位分钟，不传自动取参数
//	 * @return true：表示间隔时间大于参数设置值 false：表示间隔时间小于设置参数值
//	 */
//	public static boolean isOverIntervalTime(String depDateSec,String depTime,String depDateFir,String arrTime,String intervalTime){
//		
//		if(StringUtils.isEmpty(depDateSec) || StringUtils.isEmpty(depDateFir) || StringUtils.isEmpty(depTime) || StringUtils.isEmpty(arrTime)) return false;
//		
//		if(StringUtils.isEmpty(intervalTime)){
//			intervalTime = BookSearchUtil.getCsValue("1032", "","90");//间隔时间
//		}
//		
//		String datetimeFir = "";//第一程到达日期时间格式：2013-05-12 12:00:00
//		if(StringUtils.indexOf(arrTime, "+") != -1){
//			depDateFir = VeDate.getNextDay(depDateFir, "1");
//			
//			arrTime = StringUtils.substring(arrTime, 0, arrTime.indexOf("+"));
//		}
//		datetimeFir = depDateFir + " " + arrTime + ":00";
//		
//		String datetimeSec = depDateSec + " " + depTime + ":00";//第二程起飞日期时间格式：2013-05-12 12:00:00
//		
//		//两个时间差
//		if(NumberUtils.toInt(VeDate.getTwoMil(datetimeSec, datetimeFir)) < NumberUtils.toInt(intervalTime,90)){
//			return false;
//		}
//		
//		return true;
//	}
	
//	/**
//	 * 测试
//	 * @param args [参数说明]
//	 */
//	public static void main(String[] args) {
//		boolean isOver = isOverIntervalTime("2013-07-03", "02:30", "2013-07-02", "01:10+1", "70");
//		System.out.println(isOver == true);
//	}
	
}
