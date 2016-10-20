package org.vetech.core.modules.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 王欣 xml解析工具类 提供HotelResponse解析xml的方式的公开解决方案
 */
public class ParseXml {
	private final Logger logger = LoggerFactory.getLogger(ParseXml.class);
	private InputStream is = null;

	public void close() {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("关闭xml流失败", e);
			}
		}
	}
	/**
	 * 解析xml,初始化根节点
	 * 
	 * @param xml
	 * @throws DocumentException
	 */
	public ParseXml(String xml,String encode) throws Exception {
		SAXReader reader = new SAXReader();
		is = new ByteArrayInputStream(xml.getBytes(encode));
		try {
			this.root = reader.read(is).getRootElement();
		} catch (Exception e) {
			logger.error("xml解析错误，xml字符串是:=" + StringUtils.substring(xml, 0, 1000), e);
			throw e;
		}
	}

	/**
	 * 解析xml,初始化根节点
	 * 
	 * @param xml
	 * @throws DocumentException
	 */
	public ParseXml(String xml) throws Exception {
		SAXReader reader = new SAXReader();
		is = new ByteArrayInputStream(xml.getBytes("utf-8"));
		try {
			this.root = reader.read(is).getRootElement();
		} catch (Exception e) {
			logger.error("xml解析错误，xml字符串是:=" + StringUtils.substring(xml, 0, 1000), e);
			throw e;
		}
	}

	/**
	 * 解析xml,初始化根节点
	 * 
	 * @param xml
	 * @throws DocumentException
	 * @throws Exception
	 */
	public ParseXml(File xmlfile) throws Exception {
		SAXReader reader = new SAXReader();
		is = new FileInputStream(xmlfile);
		try {
			this.root = reader.read(is).getRootElement();
		} catch (Exception e) {
			logger.error("xml解析错误，xml文件是:=" + FileUtils.readFileToString(xmlfile), e);
			throw e;
		}
	}

	/**
	 * 解析xml,初始化根节点
	 * 
	 * @param xml
	 * @throws DocumentException
	 * @throws Exception
	 */
	public ParseXml(InputStream is) throws Exception {
		SAXReader reader = new SAXReader();
		try {
			this.root = reader.read(is).getRootElement();
		} catch (Exception e) {
			logger.error("xml流解析错误");
			throw e;
		}
	}

	/**
	 * xml文档的根节点
	 */
	private Element root;

	/**
	 * 获取指定节点的内容
	 * 
	 * @param nodeName
	 *            节点序列
	 * @return 指定节点的trim()后的内容
	 */
	public String textTrim(String nodeName) {
		Element e = ele(nodeName);
		return null != e ? e.getTextTrim() : "";
	}

	/**
	 * 在指定节点下面添加一个节点
	 * 
	 * @param name
	 *            节点名称
	 * @param text
	 *            节点内容
	 * @param ele
	 *            新增节点添加到的节点对象
	 */
	public void addNode(String name, String text, String nodeName) {
		List<Element> ele = get(nodeName);
		for (int i = 0; i < ele.size(); i++) {
			addNode(name, text, ele.get(i));
		}
	}

	/**
	 * 在指定的节点上面添加一个节点
	 * 
	 * @param name
	 *            添加的节点名称
	 * @param text
	 *            节点内容
	 * @param ele
	 *            新节点添加到的目标节点
	 */
	private void addNode(String name, String text, Element ele) {
		Element newe = ele.addElement(name);
		newe.setText(text);
	}

	/**
	 * 在指定节点下面添加一个节点
	 * 
	 * @param name
	 *            节点名称
	 * @param text
	 *            节点内容
	 */
	public void addNode(String name, String text) {
		addNode(name, text, root);
	}

	/**
	 * 获取指定节点的内容
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param e
	 *            起始节点
	 * @return 指定节点的trim()后的内容
	 */
	public String textTrim(String nodeName, Element e) {
		Element ele = ele(nodeName, e);
		return null != ele ? ele.getTextTrim() : "";
	}

	/**
	 * 重指定的xml节点中取属性值
	 * 
	 * @param attributeName
	 *            需要取的属性的名称
	 * @param e
	 *            取属性的源对象
	 */
	public String attr(String attributeName, Element e) {
		if (null == e) {
			return "";
		}
		return e.attributeValue(attributeName);
	}

	/**
	 * 重指定的xml节点中取属性值
	 * 
	 * @param attributeName
	 *            需要取的属性的名称
	 * @param e
	 *            取属性的源对象
	 */
	public String attr(String attributeName) {
		return this.root.attributeValue(attributeName);
	}

	/**
	 * 获取指定节点树尾部几点的xml结构树
	 * 
	 * @param nodeName
	 *            需要获得的节点名称序列
	 */
	public String xml(String nodeName) {
		return xml(nodeName, root);
	}

	/**
	 * 获取指定节点树尾部几点的xml结构树
	 * 
	 * @param nodeName
	 *            需要获得的节点名称序列
	 * @param e
	 *            获取数据的起始节点
	 */
	public String xml(String nodeName, Element e) {
		Element ele = this.ele(nodeName, e);
		if (null != ele) {
			return ele.asXML();
		}
		return "";
	}

	/**
	 * 将根节点转换成string类型的xml
	 */
	public String xml() {
		return root.asXML();
	}

	/**
	 * 获得root节点的文本
	 * 
	 * @return
	 */
	public String getText() {
		return root.getText();
	}

	/**
	 * 获取指定节点的内容
	 * 
	 * @param nodeName
	 *            节点序列
	 * @return 指定节点的的内容
	 */
	public String text(String nodeName) {
		Element e = ele(nodeName);
		return null != e ? e.getText() : "";
	}

	/**
	 * 获取指定节点的内容
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param e
	 *            起始节点
	 * @return 指定节点的的内容
	 */
	public String text(String nodeName, Element e) {
		Element ele = ele(nodeName);
		return null != e ? ele.getText() : "";
	}

	/**
	 * 获得结果集中0号索引处的Element元素
	 * 
	 * @param nodeName
	 *            节点序列
	 * @return 满足条件的Element对象，不存在则返回null
	 */
	public Element ele(String nodeName) {
		return ele(nodeName, root);
	}

	/**
	 * 获得结果集中0号索引处的Element元素
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param e
	 *            起始节点
	 * @return 满足条件的Element对象，不存在则返回null
	 */
	public Element ele(String nodeName, Element e) {
		return ele(nodeName, e, 0);
	}

	/**
	 * 获得结果集中指定索引处的Element元素
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param idx
	 *            索引
	 * @return 满足条件的Element对象，不存在则返回null
	 */
	public Element ele(String nodeName, int idx) {
		return ele(nodeName, root, idx);
	}

	/**
	 * 获得结果集中指定索引处的Element元素
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param e
	 *            起始节点
	 * @param idx
	 *            索引
	 * @return 满足条件的Element对象，不存在则返回null
	 */
	public Element ele(String nodeName, Element e, int idx) {
		List<Element> eles = get(nodeName, e);
		if (eles.size() == 0 || eles.size() <= idx) {
			return null;
		}
		return eles.get(idx);
	}

	/**
	 * 以根几点为起始节点获得指定的节点对象，多层节点名称之间用空格分开
	 * 
	 * @param nodeName
	 *            节点序列 如需要获取根几点下 codes<code的值则 nodeName="codes code"
	 */
	public List<Element> get(String nodeName) {
		return get(nodeName, root);
	}

	/**
	 * 移除指定名称的节点
	 * 
	 * @param nodeName
	 *            节点序列
	 */
	public void remove(String nodeName) {
		remove(nodeName, root);
	}

	/**
	 * 移除指定名称的节点
	 * 
	 * @param nodeName
	 *            节点序列
	 * @param ele
	 *            移除节点的开始节点
	 */
	public void remove(String nodeName, Element ele) {
		List<Element> eles = get(nodeName, ele);
		for (Element e : eles) {
			e.getParent().remove(e);
		}
	}

	/**
	 * 取指定节点下面nodeName序列中定义的最后一个节点的值
	 * 
	 * @param nodeName
	 *            节点名称序列
	 * @param e
	 *            取值相对节点
	 * @param isCihld
	 *            true则为取所有子节点
	 */
	@SuppressWarnings("unchecked")
	public List<Element> get(String nodeName, Element e) {
		List<Element> results = new ArrayList<Element>();
		if (null != e && StringUtils.isNotBlank(nodeName) && nodeName.trim().indexOf(" ") == -1) {
			results.addAll(e.elements(nodeName.trim()));
		} else if (null != e && StringUtils.isNotBlank(nodeName)) {
			String name = nodeName.substring(0, nodeName.indexOf(" "));
			List<Element> eles = e.elements(name.trim());
			for (Element ele : eles) {
				results.addAll(this.get(nodeName.substring(name.length()).trim(), ele));
			}
		}
		return results;
	}

	public static void main(String[] args) throws Exception {
		String xml = FileUtils.readFileToString(new File("d:/temp/cx.html"));
		ParseXml px = new ParseXml(xml);
		System.out.println(px.xml());

	}

	public Element getRoot() {
		return root;
	}
}