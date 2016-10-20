package cn.vetech.vedsb.platpolicy.cps.response.ticket;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.beanutils.BeanUtils;
import org.vetech.core.modules.utils.XmlUtils;


/**
 * DETR接口返回XML子节点TknoInfo
 * @author  gengxy
 * @version  [版本号, May 9, 2016]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TknoInfo {

	/**
	 * 13位票号
	 */
	private String tkno;

	/**
	 * PNR编码
	 */
	private String pnrNo;

	/**
	 * PNR大编码
	 */
	private String bigPnrNo;

	/**
	 * office号,从detr,s指令结果中获取
	 */
	private String office;

	/**
	 * EI项
	 */
	private String ei;

	/**
	 * 旅客名称
	 */
	private String name;

	/**
	 * 身份证,先从detr,s取,如果没有,从detr,f中取
	 */
	private String ni;

	/**
	 * 出票时间/地点,从detr，s中取
	 */
	private String cpzd;

	/**
	 * 售票处信息，从detr，s中取
	 */
	private String spc;

	/**
	 * 出票航空公司，从detr，s中取
	 */
	private String cphkgs;

	/**
	 * 连续客票
	 */
	private String lxkp;

	@XmlElement(name = "tkno")
	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno;
	}

	@XmlElement(name = "pnrNo")
	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	@XmlElement(name = "bigPnrNo")
	public String getBigPnrNo() {
		return bigPnrNo;
	}

	public void setBigPnrNo(String bigPnrNo) {
		this.bigPnrNo = bigPnrNo;
	}

	@XmlElement(name = "office")
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@XmlElement(name = "ei")
	public String getEi() {
		return ei;
	}

	public void setEi(String ei) {
		this.ei = ei;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "ni")
	public String getNi() {
		return ni;
	}

	public void setNi(String ni) {
		this.ni = ni;
	}

	@XmlElement(name = "cpzd")
	public String getCpzd() {
		return cpzd;
	}

	public void setCpzd(String cpzd) {
		this.cpzd = cpzd;
	}

	@XmlElement(name = "spc")
	public String getSpc() {
		return spc;
	}

	public void setSpc(String spc) {
		this.spc = spc;
	}

	@XmlElement(name = "cphkgs")
	public String getCphkgs() {
		return cphkgs;
	}

	public void setCphkgs(String cphkgs) {
		this.cphkgs = cphkgs;
	}

	@XmlElement(name = "lxkp")
	public String getLxkp() {
		return lxkp;
	}

	public void setLxkp(String lxkp) {
		this.lxkp = lxkp;
	}
	
	public String toXmlString(){
		StringBuffer khddXml = new StringBuffer();
		Class<?> _class = this.getClass();
		Field[] fields = _class.getDeclaredFields();
		try{
			for (Field f : fields) {
				Class<?> c = f.getType();
				String typeName = c.getSimpleName();
				if ("String".equals(typeName)) {
					String name = f.getName();
					String value = BeanUtils.getProperty(this, name);
				    if (null != value) {
						khddXml.append(XmlUtils.xmlnode(name.substring(0,1).toUpperCase()+name.substring(1), value));
					}
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return khddXml.toString();
	}

}
