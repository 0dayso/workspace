package cn.vetech.vedsb.platpolicy.cps.request.pay;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *xml.append(XmlUtils.xmlnode("CPBH", cpbh));// c站
 *xml.append(XmlUtils.xmlnode("GNGJ", "1"));// 国内
 *xml.append(XmlUtils.xmlnode("COUNT", adtnum));
 *xml.append(XmlUtils.xmlnode("PRICE", ""));
 *xml.append(XmlUtils.xmlnode("TOTAL", ""));
 *xml.append(XmlUtils.xmlnode("YWPZ", "0"));// 无配置
 * @author win7
 */
@XmlRootElement(name="SHZHGL")
public class IsAvailableData {
	private String cpbh;
	
	private String gngj;
	
	private String count;
	
	private String price;
	
	private String total;
	
	private String ywpz;

	public String getCpbh() {
		return cpbh;
	}

	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}

	public String getGngj() {
		return gngj;
	}

	public void setGngj(String gngj) {
		this.gngj = gngj;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getYwpz() {
		return ywpz;
	}

	public void setYwpz(String ywpz) {
		this.ywpz = ywpz;
	}
}
