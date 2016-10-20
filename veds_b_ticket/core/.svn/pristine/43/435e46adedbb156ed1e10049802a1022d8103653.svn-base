package org.vetech.core.business.pid.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/***
 * 
 * 根据avh,avhFD标识反射到实现java类,大量反射会造成性能下降,可以用静太方法,简化处理流程，快速执行,wwm待优化
 * 
 * @author  wangwenming
 * @version  [版本号, Dec 30, 2010]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommandFactory {
	private static Map<String,String> handlertable = new HashMap<String,String>();
	
	static{
		
		handlertable.put(CommandAbstract.ETERMAVH, "cn.vetech.pid.vepid.command.WebAvh");
		handlertable.put(CommandAbstract.ETERMFD, "cn.vetech.pid.vepid.command.WebFD");
		
		handlertable.put(CommandAbstract.IBEAVH, "cn.vetech.framework.bookticket.core.command.IbeAvh");
		handlertable.put(CommandAbstract.IBEFD, "cn.vetech.framework.bookticket.core.command.IbeFD");
		
		handlertable.put(CommandAbstract.KYIBEAVH, "cn.vetech.framework.bookticket.core.command.XIYKyAvh");
		handlertable.put(CommandAbstract.KYIBEFD, "cn.vetech.framework.bookticket.core.command.XIYKyFd");
		
		handlertable.put(CommandAbstract.HLIBEAVH, "cn.vetech.framework.bookticket.core.command.HlAvh");
		handlertable.put(CommandAbstract.HLIBEFD, "cn.vetech.framework.bookticket.core.command.HlFD");
		
		handlertable.put(CommandAbstract.ETERMNFD, "cn.vetech.framework.bookticket.core.command.WebNFD");
		
		handlertable.put(CommandAbstract.KUXUNAVH, "cn.vetech.framework.booksearch.kuxun.KuXunAvh");
		handlertable.put(CommandAbstract.KUXUNAVHSEARCH, "cn.vetech.framework.booksearch.kuxun.KuXunAvhSearch");
		
		handlertable.put(CommandAbstract.KAIXUNAVH, "cn.vetech.framework.booksearch.kaixun.KaiXunAvh");
		handlertable.put(CommandAbstract.KAIXUNFD, "cn.vetech.framework.booksearch.kaixun.KaiXunFD");
		
		handlertable.put(CommandAbstract.YLKYAVHSEARCH, "cn.vetech.framework.bookticket.core.command.YlkyAvh");
		
		handlertable.put(CommandAbstract.SSLAVHSEARCH, "cn.vetech.framework.bookticket.core.command.WebPj330");
		
		handlertable.put(CommandAbstract.QUNARAVHSEARCH, "cn.vetech.framework.booksearch.qunar.QunarAvhSearch");
		
		handlertable.put(CommandAbstract.CKGKYIBEAVH, "cn.vetech.framework.booksearch.ckgky.CKGKyAvh");
		handlertable.put(CommandAbstract.CKGKYIBEFD, "cn.vetech.framework.booksearch.ckgky.CKGKyFd");
		
		handlertable.put(CommandAbstract.TSKAVH, "cn.vetech.framework.bookticket.searchgj.command.tsk.TSKWebAvh");
		handlertable.put(CommandAbstract.FEIGOUAVH, "cn.vetech.framework.bookticket.searchgj.command.feigou.FeiGouWebAvh");
		
	}
	
	public static CommandAbstract getCommand(String command) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String className = handlertable.get(command);
		if(StringUtils.isEmpty(className))
			return null;
		return (CommandAbstract) Class.forName(className).newInstance();
	}
}
