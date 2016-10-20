package org.vetech.core.business.pid.api.parse;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.business.pid.pidbean.ParseParam;
import org.vetech.core.business.pid.util.ParseCommandUtil;
/**
 * 默认解析策略
 * @author 章磊
 *
 */
public class ParseDefault implements Parse{

	public ParseResult parse(ParseParam param,Command command) {
		ParseResult parseResult=new ParseResult();
		
		Document doc = null;
		try {
			doc = ParseCommandUtil.parseXml(param.getReturnXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(doc == null){
			parseResult.setAfterParseStr("");
			return parseResult;
		}
		
		Element items = doc.getRootElement();
		Element result = items.element("RESULT");
		String _command = items.elementText("COMMAND");
		String _return  = items.elementText("RETURN");
		if(StringUtils.isBlank(_return)){
			parseResult.setAfterParseStr("");
			return parseResult;
		}
		
		//ssr,计算是否为儿童
		String isEt = ParseCommandUtil.getage(command.getCommand());
		
		parseResult.setAfterParseStr(_command + "|" + _return + "|" + isEt + format(ParseCommandUtil.formatCommand(result.getText())));
		return parseResult;
	}
	private String format(String s){
		
		return "<PRE>"+s+"</PRE>";
	}

}
