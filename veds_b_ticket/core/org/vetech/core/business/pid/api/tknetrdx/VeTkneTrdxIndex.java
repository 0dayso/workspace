package org.vetech.core.business.pid.api.tknetrdx;

import javax.xml.bind.annotation.XmlElement;

/**
 * 白屏改签指令响应结果运价参数TRDXINDEX节点
 * 
 * @author xiaolan
 * @version [版本号, Mar 26, 2014]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class VeTkneTrdxIndex {
	/**
	 * 乘机人对应的序号
	 */
	private String index;
	
	/**
	 * 乘机人名字
	 */
	private String person;
	
	/**
	 * 乘机人身份证号码
	 */
	private String foid;
	
	/**
	 * 乘机人运价
	 */
	private String price;

	@XmlElement(name = "INDEX")
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@XmlElement(name = "PERSON")
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@XmlElement(name = "FOID")
	public String getFoid() {
		return foid;
	}

	public void setFoid(String foid) {
		this.foid = foid;
	}

	@XmlElement(name = "PRICE")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
}
