package org.vetech.core.business.pid.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.vetech.core.business.pid.api.CommandAbstract;
import org.vetech.core.business.pid.pidbean.PidResult;
import org.vetech.core.modules.utils.VeDate;

/**
 * VEPID接口统一调用类
 * 
 * @author gengxianyan
 * @version [版本号, Apr 8, 2013]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VepidWebHandler extends CommandAbstract {

	private VepidWebService service;

	private String url; // 接口调用IP和端口 http://192.168.1.69:8088
	private String jkName; // 接口名称
	private Object[] jkParam; // 接口调用传入参数数组
	private String plat; // 平台标示 A或者B

	private Logger logger = Logger.getLogger(VepidWebService.class);

	/**
	 * PID接口调用初始化
	 * 
	 * @param url 存放访问IP和端口URL(http://192.168.1.69:8088)
	 * @param jkName 调用接口方法名【通过VepidConstants类里面可以查找】
	 * @param jkParam 调用接口传递参数数组【跟接口方法所需参数一一对应】
	 * @param plat 平台标示【A或者B】
	 */
	public VepidWebHandler(String url, String jkName, Object[] jkParam, String plat) {
		url = url.replace("http://", "");
		url = "http://"+url;
		this.url = url;
		this.jkName = jkName;
		this.jkParam = jkParam;
		this.plat = plat;
	}

	/**
	 * 调用底层接口
	 * @return PidResult 接口返回封装对象
	 */
	public PidResult excute() {

		String validResult = validation();
		if (StringUtils.isNotBlank(validResult)) {
			return errorReturn("1", validResult);
		}

		try {
			service = new VepidWebService(url);
		} catch (Exception e) {
			e.printStackTrace();
			return errorReturn("1", validResult);
		}

		String resultXml = "";

		Method currMethod = null;
		Method[] methods = service.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (jkName.equals(method.getName())) {
				currMethod = method;
				break;
			}
		}

		if (currMethod == null) {
			return errorReturn("1", "调用的接口方法" + jkName + "不存在，请核实");
		}

		int paramCount = currMethod.getParameterTypes().length;
		int jkParamCount = jkParam == null ? 0 : jkParam.length;

		if (paramCount != jkParamCount) {
			return errorReturn("1", "调用的接口方法" + jkName + "的参数个数不对，参数正确的为：" + paramCount + "个，请核实");
		}

		PidResult result = new PidResult();
		long ks = System.currentTimeMillis();
		Pidlog pidlog = null;
		try {
			pidlog = beforeExcuteLog();
			resultXml = ObjectUtils.toString(currMethod.invoke(service, jkParam));
			result.setJkzt("0");
			result.setResultXml(resultXml);
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
	            StringWriter sw = new StringWriter();
	            PrintWriter pw = new PrintWriter(sw);
	            e.printStackTrace(pw);
	            resultXml =  sw.toString();
	        } catch (Exception e2) {
	            
	        }
			
			return errorReturn("2", resultXml);
		} finally {
			long js = System.currentTimeMillis();
			afterExcuteLog(pidlog, (js - ks), resultXml);
		}

		return result;
	}

	/**
	 * 调用前验证
	 * @return 验证不通过，则返回异常提示信息
	 */
	private String validation() {
		String validResult = "";
		if (StringUtils.isBlank(url)) {
			validResult = "IP和端口为空！";
		}
		String[] tmps = url.split(":");
		if (tmps.length == 3) {
			if (StringUtils.isBlank(tmps[1]) || tmps[1].equals("//null")) {
				validResult = "请先填写正确的IP！";
			}
			if (StringUtils.isBlank(tmps[2]) || tmps[2].equals("null")) {
				validResult = "请选填写正确的端口！";
			}
		} else {
			validResult = "IP和端口格式不对，该地址为" + url;
		}

		if (StringUtils.isBlank(jkName)) {
			validResult = "调用的接口方法名不能为空";
		}

		return validResult;
	}

	/**
	 * 调用前纪录日志
	 * @return 初始化日志记录对象
	 */
	private Pidlog beforeExcuteLog() {
		try {
			printLog(spellParam());
			return initPidlog();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * 调用后纪录日志
	 * @param pidlog 日志封装对象
	 * @param time 当前时间
	 * @param resultXml 接口输出内容
	 */
	private void afterExcuteLog(Pidlog pidlog, long time, String resultXml) {
	    if(null == pidlog || VepidConstants.PARSEPNR.equals(pidlog.getJkname())) return ;//ParsePNR接口不记录日志
		try {
			printLog("调用接口返回内容：" + resultXml);
			pidlog.setOutput(resultXml);
			pidlog.setOutputtime(new Date());
//			QueueUtil.putLog("pidlogservice", pidlog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 打印输出日志
	 * @param info 日志内容
	 */
	private void printLog(String info) {
		String logInfo = "";
		try {
			if ("B".equals(plat)) {
				logInfo = "调用时间：" + VeDate.getStringDate()
						+ "，调用接口名：" + jkName;
			} else {
				logInfo = "调用时间：" + VeDate.getStringDate()
						+ "，调用接口名：" + jkName;
			}

			logInfo += "；" + info;

			logger.info(logInfo);
		} catch (Exception e) {
		}
	}

	/**
	 * 日志封装对象初始化
	 * @return [参数说明]
	 */
	private Pidlog initPidlog() {
		String czuserid = "";
		String czdeptid = "";
		String czshbh = "";
		try {
//			if ("B".equals(plat)) {
//				Sh_yhb sh_yhb = AirsUtil.getBuser();
//				if (sh_yhb != null) {
//					czuserid = sh_yhb.getYhbh();
//					czdeptid = sh_yhb.getShzh();
//					czshbh = sh_yhb.getShzh();
//				}
//			} else if ("A".equals(plat)) {
//				Ve_user ve_user = AirsUtil.getAuser();
//				if (ve_user != null) {
//					czuserid = ve_user.getBh();
//					czdeptid = ve_user.getDeptid();
//					czshbh = ve_user.getCompid();
//				}
//			}
//			if (StringUtils.isBlank(czuserid)) {
//				czuserid = "无";
//				czdeptid = "无";
//				czshbh = "无";
//			}
		} catch (Exception e) {
			// throw new ServiceException(e);
		}

		Pidlog log = new Pidlog();
		String id = VeDate.getNo(6);
		log.setId(id);
		log.setPlat(plat);
		log.setUrl(url);
		log.setJkname(jkName);
		log.setInput(spellParam());
		log.setCzuserid(czuserid);
		log.setCzdeptid(czdeptid);
		log.setCzshbh(czshbh);
		log.setInputtime(new Date());
		log.setJktype(PidConstants.VEPID);
		return log;
	}

	/**
	 * 调用异常，则直接返回
	 * @param jkzt 接口调用状态
     * @param message 异常提示信息
	 * @return 接口返回对象
	 */
	private PidResult errorReturn(String jkzt, String message) {
		PidResult result = new PidResult();
		result.setJkzt(jkzt);
		result.setErrorMsg(message);
		return result;
	}

	/**
	 * 拼装传入参数
	 * @return [参数说明]
	 */
	private String spellParam() {
		String param = "";
		for (Object object : jkParam) {
			if (StringUtils.isNotBlank(param))
				param += "，";

			param += ObjectUtils.toString(object);
		}
		return "调用接口传递参数：【" + param + "】";
	}

	/**
	 * 测试入口
	 * @param args [参数说明]
	 */
	public static void main(String[] args) {
		try {
			List<Object> list = new ArrayList<Object>();
			list.add("WUHPEK");
			list.add("17APR13");
			list.add("CA");
			list.add("");
			list.add("FXCSHI");
			Object[] objects = list.toArray(new Object[0]);
			VepidWebHandler handler = new VepidWebHandler("http://192.168.1.69:8088", VepidConstants.AVH, objects, "B");
			PidResult result = handler.excute();
			System.out.println(result.getResultXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加指令
	 * @param command 指令对象
	 */
	@Override
	public void add(CommandAbstract command) {
		// TODO Auto-generated method stub

	}

	/**
     * 删除指令
     * @param commandName 指令名称
     */
	@Override
	public void delete(String commandName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据指令名称获取指令执行对象
	 * @param commandName 指令名称
	 * @return 指令执行对象
	 */
	@Override
	public CommandAbstract get(String commandName) {
		// TODO Auto-generated method stub
		return null;
	}

}
