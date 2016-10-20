package org.vetech.core.business.pid.api.parse;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.vetech.core.business.pid.pidbean.Command;
import org.vetech.core.business.pid.pidbean.ParseParam;
import org.vetech.core.business.pid.util.ParseCommandUtil;

/**
 * FD指令解析类
 * @author  gengxianyan
 * @version  [版本号, Apr 11, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@SuppressWarnings("unchecked")
public class ParseFD implements Parse{
	
	public ParseResult parse(ParseParam param,Command command){
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
		if(result==null){
			parseResult.setAfterParseStr("");
			return parseResult;
		}
		String fd  = result.elementText("FD");
		List l=result.elements();
		if(l!=null && l.size()==2){
			/*try{
				Element e=(Element)l.get(1);
		//		触发其他
				Event event=new Event();
				event.setName("FD");
				String xml=CommandHelp.outXml(doc);
				xml=xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");
				event.setContent(StringUtils.trimToEmpty(xml));
				Context context = Context.getInstance(tmp_service, event);
				context.notifya();
			}catch (Exception e) {
				e.printStackTrace();
			}*/
			
		}
		parseResult.setAfterParseStr(_command + "|" + _return + "|" + format(ParseCommandUtil.formatCommand(fd)));
		return parseResult;
	}
	private String format(String s){
		
		return "<PRE>"+s+"</PRE>";
	}
}
