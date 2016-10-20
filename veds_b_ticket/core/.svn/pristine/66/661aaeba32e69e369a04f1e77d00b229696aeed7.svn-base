package org.vetech.core.business.pid.pidbean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PID接口返回PNR内容和PAT内容解析Bean
 * @author  gengxianyan
 * @version  [版本号, Apr 8, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
@XmlRootElement(name = "PNRPAT2")
public class PidPnrnrPatBean {

	private String pat;
	private PidPnrnrBean pnrBean;
	
	@XmlElement(name = "PAT")
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	
	@XmlElement(name = "P")
	public PidPnrnrBean getPnrBean() {
		return pnrBean;
	}
	public void setPnrBean(PidPnrnrBean pnrBean) {
		this.pnrBean = pnrBean;
	}
}
