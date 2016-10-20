package cn.vetech.vedsb.pay.cft;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml工具类
 * @author miklchen
 *
 */
public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public static Map doXMLParse(String strxml) throws IOException, DocumentException {
		strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");
		
		System.out.println("strxml=="+strxml);
		if(null == strxml || "".equals(strxml)) {
			return null;
		}
		Map m = new HashMap();
		InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
		
		SAXReader reader = new SAXReader();
		Document db = reader.read(in);
		Element root = db.getRootElement();
		List list = root.elements();
		
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build(in);
//		Element root = doc.getRootElement();
//		List list = root.getChildren();
		Iterator it = list.iterator();
		while(it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.elements();
//			List children = e.getChildren();
			if(children.isEmpty()) {
				v = e.getStringValue();
//				v = e.getTextNormalize();
			} else {
				v = XMLUtil.getChildrenText(children);
			}
			
			m.put(k, v);
		}
		
		//关闭流
		in.close();
		
		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * @param children
	 * @return String
	 */
	public static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				//String value = e.getTextNormalize();
				String value = e.getStringValue();
				List list = e.elements();
				sb.append("<" + name + ">");
				if(!list.isEmpty()) {
					sb.append(XMLUtil.getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * 获取xml编码字符集
	 * @param strxml
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static String getXMLEncoding(String strxml) throws DocumentException, IOException {
		InputStream in = HttpClientUtil.String2Inputstream(strxml);
		SAXReader reader = new SAXReader();
		Document db = reader.read(in);
//		SAXBuilder builder = new SAXBuilder();
//		Document doc = builder.build(in);
		in.close();
//		return (String)doc.getProperty("encoding");
		return (String)db.selectObject("encoding");
	}
	
	
}
