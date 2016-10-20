package cn.vetech.vedsb.platpolicy.cps.response.pay;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author win7
 *		<Shzh>" +
 *			<Id>1212251902328269</Id>" +
 *			<Zhbh>1212251902323160</Zhbh>" +
 *			<Zhmc>默认账户</Zhmc>" +
 *			<Kyye>4974</Kyye>" +
 *			<Zhye>4974</Zhye>" +
 *			<Cpmc>机场服务,机票,旅游,火车票,保险,票证,班车,酒店,机票追位</Cpmc>" +
 *			<Cpbh>
 *				312012430, 312012401, 312012402, 312012403, 312012404,
 *				312012405, 312012406, 312012409, 312012410
 *			</Cpbh>
 *			<Djye>0</Djye>" +
 *			<Sfqy>0</Sfqy>" +
 *		</Shzh>" +
 */

public class CpsShzhData {
	private String id;
	private String zhbh;
	private String zhmc;
	//可用余额
	private String kyye;
	private String zhye;
	private String cpmc;
	private String cpbh;
	private String djye;
	private String sfqy;
	private String gg;
	//赠送账户余额
	private String zszhye;
	
	@XmlElement(name="Id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name="Zhbh")
	public String getZhbh() {
		return zhbh;
	}
	public void setZhbh(String zhbh) {
		this.zhbh = zhbh;
	}
	
	@XmlElement(name="Zhmc")
	public String getZhmc() {
		return zhmc;
	}
	public void setZhmc(String zhmc) {
		this.zhmc = zhmc;
	}
	
	@XmlElement(name="Kyye")
	public String getKyye() {
		return kyye;
	}
	public void setKyye(String kyye) {
		this.kyye = kyye;
	}
	
	@XmlElement(name="Zhye")
	public String getZhye() {
		return zhye;
	}
	public void setZhye(String zhye) {
		this.zhye = zhye;
	}
	
	@XmlElement(name="Cpmc")
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	
	@XmlElement(name="Cpbh")
	public String getCpbh() {
		return cpbh;
	}
	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}
	
	@XmlElement(name="Djye")
	public String getDjye() {
		return djye;
	}
	public void setDjye(String djye) {
		this.djye = djye;
	}
	
	@XmlElement(name="Sfqy")
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	
	@XmlElement(name="GG")
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	
	@XmlElement(name="ZSZHYE")
	public String getZszhye() {
		return zszhye;
	}
	public void setZszhye(String zszhye) {
		this.zszhye = zszhye;
	}
}
