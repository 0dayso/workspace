package org.vetech.core.business.pid.api.devpay;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Element;
import org.vetech.core.modules.utils.ParseXml;

public class DevpayParser {
	
	public static Map<String, Object> parseDevpay(String parseinfo){
		try {
			ParseXml parsexml = new ParseXml(parseinfo);
			Element root = parsexml.getRoot();
			String status = root.elementText("STATUS");
			if ("0".equals(status)) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("status", status);
				Element description = root.element("DESCRIPTION");
				Element result = description.element("Result");
				String content = result.elementText("Content");
				paramMap.put("content", content);
				Element details = result.element("Details");
				String availablebalance = details.elementText("AvailableBalance");
				String freezebalance = details.elementText("FreezeBalance");
				paramMap.put("availablebalance", availablebalance);
				paramMap.put("freezebalance", freezebalance);
				return paramMap;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		//String xml = "<RESULT><STATUS>0</STATUS><DESCRIPTION><Result><Content>Available Balance:312406.12, Freeze Balance:0.00*QRPY/0/0/Available Balance:312406.12, Freeze Balance:0.00 </Content><Details><AvailableBalance>312406.12</AvailableBalance><FreezeBalance>0.00</FreezeBalance></Details></Result></DESCRIPTION></RESULT>";
		//Map<String, Object> map = DevpayParser.parseDevpay(xml);
	}
}
