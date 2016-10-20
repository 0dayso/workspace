package org.vetech.core.modules.utils;

import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class ParserHtml {
	public Document doc = null;

	public void Parser(String html) {
		this.doc = Jsoup.parse(html);
	}

	public String getDocumentByTitle() {
		String text = "";
		try {
			Elements title = this.doc.select("title");
			for (Element link : title)
				text = text + link.text() + "~";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public String getDocumentByTable() {
		String text = "";
		try {
			Elements links = this.doc.select("table");
			for (Element link : links)
				text = text + link.text() + "|";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public String getDocumentByDiv() {
		String text = "";
		try {
			Elements divs = this.doc.select("div[class=post]");
			for (Element link : divs)
				text = text + link.text() + ",";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public String getDocument(String node) {
		String text = "";
		try {
			Elements divs = this.doc.select(node);
			for (Element link : divs)
				text = text + link.text() + "、";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return text;
	}

	public String clean(String html) {
		String safe = Jsoup.clean(html, Whitelist.none());
		return safe;
	}

	public String getValueByid(String idvalue) {
		Element e = doc.getElementById(idvalue);
		if (e != null) {
			return e.attr("value");
		}
		return null;
	}

	public Element getElementById(String id) {
		return doc.getElementById(id);
	}

	public Element getElementByName(String namevalue) {
		return doc.getElementsByAttributeValue("name", namevalue).first();
	}

	public String getForm2Map(String formname, Map<String, String[]> m) {
		Elements forms = doc.getElementsByTag("form");
		if (forms == null || forms.size() < 1) {
			return null;
		}
		if (StringUtils.isNotBlank(formname)) {
			forms = forms.select("[name=" + formname + "]");
		}
		if (forms == null || forms.size() < 1) {
			return null;
		}
		Element forme = forms.first();
		String action = forme.attr("action");
		Elements es = forme.select("input");
		for (int i = 0; i < es.size(); i++) {
			Element e = es.get(i);
			if (StringUtils.isBlank(e.attr("disabled"))) {
				String name = e.attr("name");
				String value = e.attr("value");
				String[] ss = m.get(name);
				if (ss == null) {
					ss = new String[0];
				}
				ss = (String[]) ArrayUtils.add(ss, value);
				m.put(name, ss);
			}
		}
		return action;
	}

	public String getForm2Map_byId(String formId, Map<String, String[]> m) {
		Elements forms = doc.getElementsByTag("form");
		if (forms == null || forms.size() < 1) {
			return null;
		}
		if (StringUtils.isNotBlank(formId)) {
			forms = forms.select("[id=" + formId + "]");
		}
		if (forms == null || forms.size() < 1) {
			return null;
		}
		Element forme = forms.first();
		String action = forme.attr("action");
		Elements es = forme.select("input");
		for (int i = 0; i < es.size(); i++) {
			Element e = es.get(i);
			if (StringUtils.isBlank(e.attr("disabled"))) {
				String name = e.attr("name");
				String value = e.attr("value");
				String[] ss = m.get(name);
				if (ss == null) {
					ss = new String[0];
				}
				ss = (String[]) ArrayUtils.add(ss, value);
				m.put(name, ss);
			}
		}
		return action;
	}

	/**
	 * 把url中有中文转为url编码
	 * 
	 * @param url
	 * @param encode
	 * @param form
	 * @return
	 */
	public static String url2Form(String url, String encode, Map<String, String> form) {
		return null;
	}



	public static void main(String[] args) {
		String url = "https://mapi.alipay.com/gateway.do?body=信用支付&notify_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxySServlet&out_trade_no=6948161608148000&partner=2088301094248164&payment_type=1&return_url=http://epay.airchina.com.cn/CaPay/B2CBankRes_apayxyCServlet&seller_email=cralipay@airchina.com&service=create_direct_pay_by_user&sign=4cc9ce227226e7263c0b35cd1e0b74e3&sign_type=MD5&subject=国航电子客票&total_fee=820.0";
		System.out.println(VeStr.urlStrEncode(url, "GBK"));
	}
}