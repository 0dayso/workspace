package cn.vetech.vedsb.jp.service.procedures;

import java.io.File;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.SpringPropertyResourceReader;
import org.vetech.core.modules.utils.VeStr;
import org.vetech.core.modules.utils.XmlUtils;

@Service
public class ParamMap {
	@Autowired
	private SpringPropertyResourceReader propertyResourceReader;

	public Map<String, Object> xmlToJson(Map<String, Object> param) {
		String driver = StringUtils.trimToEmpty(propertyResourceReader.getProperty("jp.jdbc.driver"));
		String p_xml = VeStr.getValue(param, "p_xml");// 用于复杂xml直接拼接好后转json
		// Oracle数据传xml
		if ("oracle.jdbc.driver.OracleDriver".equals(driver)) {
			if (StringUtils.isBlank(p_xml)) {
				StringBuffer xml=new StringBuffer();
				mapToXml(param, xml);
				param.put("p_xml",XmlUtils.xmlnode("SQL", xml));
			}
			return param;
		} else {// 传Json
			 //如果是postgresql 则进行类型
			JdbcType.CURSOR.TYPE_CODE = Types.REF;
			JdbcType.CLOB.TYPE_CODE = Types.VARCHAR;
			if (StringUtils.isBlank(p_xml)) {
				Map<String, Object> m = param;
				JSONObject jsonObject = JSONObject.fromObject(m);
				param.put("p_xml", "{\"SQL\":" + transToUpperObject(jsonObject.toString()) + "}");
			} else {
				StringBuffer sqlxmlbuf = new StringBuffer();
				sqlxmlbuf.append("<SQL>").append(p_xml).append("</SQL>");
				p_xml = new XMLSerializer().read(sqlxmlbuf.toString()).toString().replaceAll("\\[]", "\"\"");
				param.put("p_xml", p_xml);
			}
		}
		return param;
	}
    
	//json小写转大写
	public static String transToUpperObject(String json) {
		//正则替换
		String regex = "(\\{|\")([a-zA-Z0-9]|_)+\":";
		Pattern pattern = Pattern.compile(regex);
		StringBuffer sb = new StringBuffer();
		Matcher m = pattern.matcher(json);
		while (m.find()) {
			m.appendReplacement(sb, m.group().toUpperCase());
		}
		m.appendTail(sb);
		return sb.toString();
	}
	

	//Map转Json包含value是List
	private static void mapToXml(Map map, StringBuffer xml) {
		Set set = map.keySet();
		for (Iterator it = set.iterator(); it.hasNext();) {
			String key = (String) it.next();
			Object value = map.get(key);
			if (null == value)
				value = "";
			if (value.getClass().getName().equals("java.util.ArrayList")) {
				ArrayList list = (ArrayList) map.get(key);
				for (int i = 0; i < list.size(); i++) {
					xml.append("<").append(key).append(">");
					mapToXml((Map) list.get(i), xml);
					xml.append("</").append(key).append(">");
				}
			} else {
				if (value instanceof HashMap) {
					xml.append("<").append(key).append(">");
					mapToXml((HashMap) value, xml);
					xml.append("</").append(key).append(">");
				} else {
					xml.append(XmlUtils.xmlnode(key, value));
				}
			}
		}
	}
	 
	 public static void main(String[] args) throws Exception {
		String p_xml = "<ETDZRESULT><FLAG>0000</FLAG><ERRTXT>ETDZ成功</ERRTXT><P>刘六金</P><TN>7319135009338</TN><ACNY>810.00</ACNY><OFFICEID>SHA384</OFFICEID><PRINTERID/><PID>31947</PID><DFTRMKINFO>1</DFTRMKINFO><TSL/></ETDZRESULT>";
		p_xml = FileUtils.readFileToString(new File("e:/qinfo.txt"));
		Map<String, Object> param = new HashMap<String, Object>();
		StringBuffer sqlxmlbuf = new StringBuffer();
		sqlxmlbuf.append("<SQL>").append(p_xml).append("</SQL>");
		XMLSerializer  	xmlser=new XMLSerializer();
		xmlser.setSkipWhitespace(false);
		p_xml = xmlser.read(sqlxmlbuf.toString()).toString();//.replaceAll("\\[]", "\"\"");
		System.out.println(p_xml);
		param.put("p_xml", p_xml);
	}
}
