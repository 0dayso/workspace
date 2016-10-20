package org.vetech.core.business.pid.api.createXcd;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 
 * 行程单创建新接口 
 * @author  wangxuexing
 * @version  [版本号, 2016-0802]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreateXcd2Parser {
	private String data;
	private String state;
	private String errormassage="";
	private String itinerary="";
	
	public void parser(String data){
		this.data = data;
		if("".equals(data)){
			state="E";
			errormassage="连接错误，请重试！！！";
			return;
		}
		Document document = null;
		try {
			document = DocumentHelper.parseText(data);
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		if (document == null) {
			System.out.println("创建行程单类:解析xml错误"+data);
			return;
		}
		try {
			Element items = document.getRootElement();
			String flag = items.elementText("Flag");
			String tmp="";
			if("E".equals(flag)){
				Element e=items.element("Error");
				if(e!=null){
					tmp = e.elementText("ErrorReason");
				}else{
					tmp = items.elementText("ErrorReason");
				}
			}
			state = flag;
			errormassage = tmp;
			Element items1=items.element("TKTS");
			if(items1!=null){
				itinerary=items1.elementText("TKT");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("创建行程单类:解析xml错误"+data);
			return;
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getErrormassage() {
		return errormassage;
	}

	public void setErrormassage(String errormassage) {
		this.errormassage = errormassage;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}
}
