package org.vetech.core.business.pid.api;

import java.util.Map;

import org.vetech.core.business.pid.api.parse.Parse;
import org.vetech.core.business.pid.pidbean.ParseParam;
import org.vetech.core.business.pid.pidbean.ParseResult;
import org.vetech.core.business.pid.pidbean.PidAvhBean;
import org.vetech.core.business.pid.pidbean.PidAvhResult;
import org.vetech.core.business.pid.pidbean.PidFdBean;
import org.vetech.core.business.pid.pidbean.PidResult;
import org.vetech.core.business.pid.util.BookSearchUtil;

/**
 * 装饰组合
 * 多种指令获取的数据进行组合，调用专用解析类进行解析
 */
public class DetectorCommandComposite extends CommandAbstract {

	
//	@Autowired
//	private PidCommonService service;
	private CommandAbstract commandAbstract;
	
	protected String serverTime; // 共享时间
	protected String endprocessTime; // 解析以及匹配时间

	@Override
	public PidResult excute() throws Exception {
		// 声明返回结果集
		PidAvhResult pidResult = new PidAvhResult();
		try {
			long ks = System.currentTimeMillis();
			
			// 执行CommandComposite的execute()方法,在BuilderDetectorComposite里添加的avh和avhFD命令发送webservice请求,取得返回结果
			PidAvhResult result = (PidAvhResult)commandAbstract.excute();// 组合对象没有返回值 实现类为 commandAbstract 执行调用多个命令接口
			
			serverTime = BookSearchUtil.getTimeCha(ks);
			long js = System.currentTimeMillis();
			
			System.out.println("调用PID耗时：" + (js - ks));
			
			PidAvhBean avhBean = (PidAvhBean)commandAbstract.getCommand();

			// 获得组合对象执行指令返回的数据集合
			// key 为 指令名称，value 为 执行指令后返回的xml
			Map<String, String> commandMap = result.getCommandMap();
			
			// 组合对象执行完后会得到这组合对象的名称,AVH+FD
			String name = avhBean.getName();

			// 动态生成解析对象
			// 实例化解析类,AVHFD,类为ParseAVHandFDComposite.java
			Parse parse = CommandParseFactory.getCommandHandler(name);
			
			// 进行政策匹配,返回解析后集合数据
			//long pp1 = System.currentTimeMillis();
			
			ParseParam param = new ParseParam();
			param.setJkData(commandMap);
			ParseResult parseResult = null;//FIXME parse.parse(param, avhBean);
			
			//long pp2 = System.currentTimeMillis();
			endprocessTime = BookSearchUtil.getTimeCha(js);
			
			System.out.println("航班解析耗时：" + endprocessTime);
			
			if (parseResult == null) {
				return null;
			}

			// FD价格写表
			long ks3 = System.currentTimeMillis();
			PidFdBean pidFdBean = new PidFdBean();
			pidFdBean.setName("FD");
			pidFdBean.setContent(commandMap.get("FD"));
			FdFactory factory = FdFactory.getInstance(pidFdBean);
			factory.notifya();// 更新运价
			long js3 = System.currentTimeMillis();
			
			pidResult.setFlightList(parseResult.getFlightList());
			pidResult.setResultXml(parseResult.getAfterParseStr());
			
			pidResult.setFlightNos(parseResult.getFlightNos());
			pidResult.setCabins(parseResult.getCabins());
			pidResult.setHkgss(parseResult.getHkgss());
			
			System.out.println("同步FD运价耗时：" + (js3 - ks3));
			//Log.printlog(avhBean.getHc()+" "+avhBean.getDate() + "白屏查询 【PID处理耗时:" + (serverTime) + "，政策匹配解析耗时："+(pp2 - pp1)+"】");
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			PidAvhBean avhBean = (PidAvhBean)commandAbstract.getCommand();
			// 计算流量
			if (avhBean != null) {
				// 如果AVH是IBE就计算,并且执行成功
				if (CommandAbstract.IBEAVH.equals(avhBean.getSearchType()) && avhBean.isAvhSuccess()) {
					//service.countSyll(avhBean.getUserid(), "AVH", "1");
				}
				// 如果FD是IBE就计算
				if (CommandAbstract.IBEFD.equals(avhBean.getFdType()) && avhBean.isFdSuccess()) {
					//service.countSyll(avhBean.getUserid(), "FD", "1");
				}
			}
		}
		return pidResult;
	}

	@Override
	public CommandAbstract get(String commandName) {

		return null;
	}

	public CommandAbstract getCommandAbstract() {
		return commandAbstract;
	}

	public void setCommandAbstract(CommandAbstract commandAbstract) {
		this.commandAbstract = commandAbstract;
	}
	
	public DetectorCommandComposite() {
	}

	@Override
	public void add(CommandAbstract command) {

	}

	@Override
	public void delete(String commandName) {

	}
}
