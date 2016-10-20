package org.vetech.core.business.pid.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.business.pid.api.parse.Parse;

/**
 * 解析工厂根据，共享返回的数据，来动态生成解析对象来处理
 * @author 章磊
 *
 */
public class CommandParseFactory {
	private static Map<String,String> handlertable = new HashMap<String,String>();
	
	static{
		
		handlertable.put("DEFAULT", "cn.vetech.pid.parse.ParseDefault");
		
		handlertable.put("AVH", "cn.vetech.pid.parse.ParseAVh");
		
		handlertable.put("AV", "cn.vetech.pid.parse.ParseAv");
		
		handlertable.put("RT", "cn.vetech.pid.parse.ParseRT");
		handlertable.put("FD", "cn.vetech.pid.parse.ParseFD");
		
		handlertable.put("NFD", "cn.vetech.pid.parse.ParseNFD");
		
		handlertable.put("AVHFD", "cn.vetech.pid.parse.ParseAvhAndFd");
	}
	
	public static Parse getCommandHandler(String parsecommand) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String className = handlertable.get(parsecommand);
		
		if(StringUtils.isBlank(className)){
			className=(String)handlertable.get("DEFAULT");
		}
		
		return (Parse) Class.forName(className).newInstance();
	}
}
