package org.vetech.core.modules.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlUtils {
	 
	private final static Logger logger = LoggerFactory.getLogger(XmlUtils.class);
	
	public static String xmlnode(String node, Object o) {
		if (o == null) {
			return "<" + node + "></" + node + ">";
		} else {
			if (o instanceof String) {
				String s = StringUtils.trimToEmpty((String) o);
				s = VeStr.toXmlFormat(s);
				s = VeStr.clearToXml(s);
				return "<" + node + ">" + s + "</" + node + ">";
			} else if (o instanceof Double) {// double类型最多6位小数
				Double d = (Double) o;
				DecimalFormat df = new DecimalFormat("#.######");
				String num = df.format(d);
				return "<" + node + ">" + num + "</" + node + ">";
			} else {
				return "<" + node + ">" + o + "</" + node + ">";
			}
		}
	}

	/**
	 * 根据对象生成XML
	 * 
	 * @param o
	 * @return
	 */
	public static String objectToXml(Object o) {
		StringBuffer xml = new StringBuffer();
		Class c = o.getClass();
		String parentNode = c.getSimpleName().toUpperCase();
		xml.append("<").append(parentNode).append(">");
		Field[] f = c.getDeclaredFields();
		for (Field one : f) {
			Class type = one.getType();
			if ("String".equals(type.getSimpleName())) {
				String name = one.getName();
				String value = null;
				try {
					value = BeanUtils.getProperty(o, name);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (value != null) {
					xml.append(XmlUtils.xmlnode(name.toUpperCase(), VeStr.formatHtml(value)));
				}
			}
		}
		xml.append("</").append(parentNode).append(">");
		return xml.toString();
	}

	/**
	 * List<Object>生成XML
	 * 
	 * @param l
	 * @return
	 */
	public static String listToXml(List<Object> l) {
		StringBuffer xml = new StringBuffer();
		if (l != null) {
			Iterator<Object> itr = l.iterator();
			while (itr.hasNext()) {
				Object o = itr.next();
				xml.append(objectToXml(o));
			}
		}

		return xml.toString();
	}

 
	/**
	 * XML转BEAN 需在传入CLASS对象中注解“@XmlRootElement(name = "ROOT节点名称")” （如XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name =
	 * "XXX")”）
	 * 
	 * @param xml
	 * @param rootClass
	 * @return [参数说明]
	 * 
	 * @return Object [返回类型说明]
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("unchecked")
	public static Object fromXml(String xml, Class rootClass) {
		
		Object obj = null;
		if(StringUtils.isBlank(xml)){
			return obj;
		}
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(rootClass);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			obj = unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error(" xml:="+xml+"转化类:"+rootClass.getName(),e);
		}
		return obj;
	}

	/**
	 * 将对象转换成XML 需在传入CLASS对象中注解“@XmlRootElement(name = "ROOT节点名称")”
	 * （如生成XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name = "XXX")”）
	 * 
	 * @param bean
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws XMLStreamException
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String toXml(Object bean) {
		if (bean == null) {
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(bean, writer);
		} catch (JAXBException e) {
			logger.error(bean+",转Xml错误",e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.getBuffer().toString();
	}
	
	/**
	 * 将对象转换成XML(无格式) 需在传入CLASS对象中注解“@XmlRootElement(name = "ROOT节点名称")”
	 * （如生成XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name = "XXX")”）
	 * 
	 * @param bean
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws XMLStreamException
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String toXmlNoFormat(Object bean){
		if (bean == null) {
			return null;
		}
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(bean, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
			logger.error(bean+",toXmlNoFormat转Xml错误",e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.getBuffer().toString();
	}

	/**
	 * 将对象转换成XML 带<?xml version="1.0" encoding="UTF-8" standalone="yes"?>格式 需在传入CLASS对象中注解“@XmlRootElement(name =
	 * "ROOT节点名称")” （如生成XML节点和BEAN属性不同，可以在BEAN属性get方法上注解“@XmlElement(name = "XXX")”）
	 * 
	 * @param bean
	 * @param encoding
	 *            编码 默认UTF-8
	 * @return [参数说明]
	 * 
	 * @return String [返回类型说明]
	 * @throws XMLStreamException
	 * @exception throws
	 *                [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String toXmlWithHead(Object bean, String encoding) {
		if (bean == null) {
			return null;
		}
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		StringWriter writer = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(bean.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			marshaller.marshal(bean, writer);
		} catch (JAXBException e) {
			logger.error(bean+",toXmlWithHead转Xml错误",e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return writer.getBuffer().toString();
	}
	 
	/**
	 * 得到xml指定节点的值
	 * 
	 * @param xml xml节点
	 * @param name 需要取的节点  区分大小写
	 * @return String [返回类型说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static String getNode(String xml,String name){
		String tmpName = "<"+name+">";
		int pos = StringUtils.indexOf(xml,tmpName)+tmpName.length();
		int end = StringUtils.indexOf(xml,"</"+name+">");
		if(pos>=end){
			return null;
		}
		return StringUtils.trim(StringUtils.substring(xml, pos,end));
	}
	
	public static org.dom4j.Document getDocument(String xml,String encoding) throws DocumentException, UnsupportedEncodingException{
		if (StringUtils.isBlank(encoding)) {
			encoding = "UTF-8";
		}
		SAXReader reader = new SAXReader();
		InputStream is=null;
		is = new ByteArrayInputStream(xml.getBytes(encoding));
		return reader.read(is);
	}
	
	/**
	 * @category 返回节点的字节点text值，节点为空或者值为空返回空字串
	 * @param Node:节点
	 * @param childName:字节点名称
	 */
	public static String getChildNodeText(Node node,String childName){
		if(node == null){
			return "";
		}
		Node childNode = node.selectSingleNode(childName);
		if(childNode == null){
			return "";
		}
		if(StringUtils.isBlank(childNode.getText())){
			return "";
		}
		return childNode.getText().trim();
		
	}
	
	public static String getXmlHead(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	}
	
	/**
	 * @category 把list集合转成xml
	 * @param list 要转换的list集合
	 * @param rootname list的根节点
	 * @param head 是否需要<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> 头
	 */
	@SuppressWarnings("unchecked")
	public static String toXml(List list,String rootname,boolean head){
		
		StringBuffer sb = null;
		if(head){
			sb = new StringBuffer(getXmlHead());
		}else{
			sb = new StringBuffer();
		}
		if(StringUtils.isNotBlank(rootname)){
			sb.append("<"+rootname+">");
		}
		
		for(Object o : list){
			sb.append(XmlUtils.toXml(o));
		}
		if(StringUtils.isNotBlank(rootname)){
			sb.append("</"+rootname+">");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param list
	 *            将list里面的map转换成xml,
	 * @param segmentTag
	 *            行标记
	 * @return xml
	 */
	public static String toXml(List<Map<String, String>> list, String segmentTag) {
		StringBuffer xml = new StringBuffer();
		if (list == null || list.isEmpty()) {
			xml.append("<").append(segmentTag).append(">");
			xml.append("</").append(segmentTag).append(">");
			return xml.toString();
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Map<String, String> m = (Map<String, String>) it.next();
			xml.append("<").append(segmentTag).append(">");
			xml.append(toXml(m, null));
			xml.append("</").append(segmentTag).append(">");
		}

		return xml.toString();
	}
	// 将Map里面的内容转换成xml
	public static String toXml(Map<String, String> m, String rootTag) {
		StringBuffer xml = new StringBuffer();
		if(null != m && !m.isEmpty()){
			Iterator it = m.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				xml.append("<").append(key).append(">").append(VeStr.toXmlFormat(StringUtils.trimToEmpty(m.get(key)))).append("</").append(key)
				.append(">");
			}
		}
		if (StringUtils.isNotBlank(rootTag)) {
			return "<" + rootTag + ">" + xml.toString() + "</" + rootTag + ">";
		}
		return xml.toString();
	}
	
	
	public static void main(String[] args) {

	}
}
