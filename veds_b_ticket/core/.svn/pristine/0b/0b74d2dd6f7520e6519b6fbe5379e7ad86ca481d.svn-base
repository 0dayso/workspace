package org.vetech.core.modules.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 流方式解析大的xml
 * @author zhanglei
 *
 */
public class ParseBigXml {
	private final Logger logger = LoggerFactory.getLogger(ParseBigXml.class);
	private InputStream is = null;
	private SAXReader reader = new SAXReader();

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
	public ParseBigXml(String xml) throws Exception {
		this.is = new ByteArrayInputStream(xml.getBytes("utf-8"));
	}

	/**
	 * 流方式解析大文本xml
	 * 
	 * @throws Exception
	 */
	public ParseBigXml(File xmlfile) throws Exception {
		is = new BufferedInputStream(new FileInputStream(xmlfile));
	}

	/**
	 * 流方式解析 字符串存储的xml 文件
	 * 
	 * @param is
	 * @param elementHandler
	 * @throws Exception
	 */
	public ParseBigXml(InputStream is) throws Exception {
		this.is = is;
	}

	/**
	 * 流方式 zip 格式压缩文件
	 * 
	 * @param zipFile
	 * @param elementHandler
	 * @throws Exception
	 */
	public ParseBigXml(ZipFile zipFile) throws Exception {
		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> entrys = (Enumeration<ZipEntry>) zipFile.entries();
		while (entrys.hasMoreElements()) {
			ZipEntry entry = entrys.nextElement();
			this.is = zipFile.getInputStream(entry);
			break;
		}
	}

	public void addHandler(String path, ElementHandler handler) {
		reader.addHandler(path, handler);

	}

	public void parse() throws Exception {
		reader.read(is);
	}

	public static void test() throws Exception {
		String fileName = "E:\\150831122000665zip_380.zip";
		ParseBigXml p = new ParseBigXml(new ZipFile(new File(fileName)));
		p.addHandler("/policyList/policy", new ElementHandler() {
			@Override
			public void onStart(ElementPath ep) {

			}

			@Override
			public void onEnd(ElementPath ep) {
				Element row = ep.getCurrent();
				// String table = ep.getPath().substring(6);

				System.out.println(row.elementText("id"));

				// List<Element> list = row.elements();
				// for (Element e : list) {
				// System.out.println(e.getQName().getName()+"："+e.getText());
				// }
				row.detach(); // 记得从内存中移去
			}
		});

		p.parse();

	}

	public static void main(String[] args) throws Exception {
		test();
	}

}
