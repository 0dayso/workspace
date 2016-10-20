package org.vetech.core.business.pid.api;

import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.business.pid.pidbean.PidResult;


/**
 * 接口声明静态常量类
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public abstract class CommandAbstract {
	
	public static final String ETERMAVH = "ETERMAVH"; 	// 实例化ETERM avh
	public static final String ETERMFD = "ETERMFD"; 	// 实例化ETERM fd

	public static final String IBEAVH = "IBEAVH"; 		// 实例化航信IBE avh
	public static final String IBEFD = "IBEFD"; 		// 实例化航信IBEFD fd


	public static final String KYIBEAVH = "KYIBEAVH"; 	// 实例化西安凯亚IBEFD avh
	public static final String KYIBEFD = "KYIBEFD"; 	// 实例化西安凯亚IBEFD fd

	public static final String HLIBEAVH = "HLIBEAVH"; 	// 华旅AVH接口
	public static final String HLIBEFD = "HLIBEFD"; 	// 华旅FD接口

	public static final String KUXUNAVH = "KUXUNAVH"; 	// 实例化酷讯
	public static final String KUXUNAVHSEARCH = "KUXUNAVHSEARCH"; // 实例化酷讯查询接口
	
	public static final String KAIXUNAVH = "KAIXUNAVH"; //凯讯AVH接口
	public static final String KAIXUNFD = "KAIXUNFD"; 	//凯讯FD接口

	public static final String YLKYAVHSEARCH = "YLKYAVHSEARCH"; // 实例化云南凯亚查询接口

	public static final String SSLAVHSEARCH = "SSLAVHSEARCH"; 	// 实例化330票价查询查询接口

	public static final String QUNARAVHSEARCH = "QUNARAVHSEARCH"; // 实例化去哪儿查询接口
	
	public static final String CKGKYIBEAVH = "CKGKYIBEAVH"; 	//重庆凯亚IBEAVH接口
	public static final String CKGKYIBEFD = "CKGKYIBEFD"; 	//重庆凯亚IBEFD接口

	public static final String PAT = "PAT";
	public static final String ETERMNFD = "ETERMNFD"; 	// 实例化ETERM NFD
	public static final String ETERMXEPNR = "ETERMXEPNR"; // 实例化ETERM XEPNR
	
	public static final String TSKAVH = "TSKAVH";			//TSK国际票查询接口
	public static final String FEIGOUAVH = "FEIGOUAVH";		//飞狗国际票查询接口
	
	public static final String AVHNAME = "AVH"; 	// AVH对象名称
	public static final String FDNAME = "FD"; 		// FD对象名称


	private Command command; // 指令对象

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public abstract void add(CommandAbstract command);

	public abstract void delete(String commandName);

	public abstract CommandAbstract get(String commandName);

	public abstract PidResult excute() throws Exception;

}
