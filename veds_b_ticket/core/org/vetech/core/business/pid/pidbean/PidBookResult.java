package org.vetech.core.business.pid.pidbean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 预订PNR返回Bean
 * 预订PNR后接口返回XML解析对应的Bean
 * @author  gengxianyan
 * @version  [版本号, Aug 28, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@XmlRootElement(name = "P")
public class PidBookResult {

	private String flag;// 1成功 0失败
	
	private String pnrno;// PNR编码
	
	private String pnrlr;// PNR内容
	
	private String pnrzt;// PNR状态
	
	private String office;// 预订PNR的OFFICE号
	
	private String auth;// 授权的OFFICE号
	
	private String big_pnrno;// PNR大编码

	private PidBookResultChild childBean;

	@XmlElement(name = "R")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@XmlElement(name = "N")
	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	@XmlElement(name = "Q")
	public String getPnrlr() {
		return pnrlr;
	}

	public void setPnrlr(String pnrlr) {
		this.pnrlr = pnrlr;
	}

	@XmlElement(name = "Z")
	public String getPnrzt() {
		return pnrzt;
	}

	public void setPnrzt(String pnrzt) {
		this.pnrzt = pnrzt;
	}

	@XmlElement(name = "OFFICE")
	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	@XmlElement(name = "AUTH")
	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@XmlElement(name = "B")
	public String getBig_pnrno() {
		return big_pnrno;
	}

	public void setBig_pnrno(String big_pnrno) {
		this.big_pnrno = big_pnrno;
	}

	@XmlElement(name = "PNRXML")
	public PidBookResultChild getChildBean() {
		return childBean;
	}

	public void setChildBean(PidBookResultChild childBean) {
		this.childBean = childBean;
	}
	
}
