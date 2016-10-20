package org.vetech.core.business.pid.pidbean;

import java.util.List;

/**
 * 解析PNR内容---乘机人信息Bean
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class PnrPassengerBean {

	private String id;
	private String sxh;
	private String cjrlx;//1成人 2儿童 3婴儿
	private String cjrxm;
	private String zjlx;
	private String zjhm;
	private String pj;
	private String jsf;
	private String tax;
	private String sjhm;
	
	private String tkno;//乘机人第一张票号
	private List<String> tknoList; //乘机人票号集合
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getCjrlx() {
		return cjrlx;
	}
	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx;
	}
	public String getCjrxm() {
		return cjrxm;
	}
	public void setCjrxm(String cjrxm) {
		this.cjrxm = cjrxm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getPj() {
		return pj;
	}
	public void setPj(String pj) {
		this.pj = pj;
	}
	public String getJsf() {
		return jsf;
	}
	public void setJsf(String jsf) {
		this.jsf = jsf;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getZjlx() {
		return zjlx;
	}
	public void setZjlx(String zjlx) {
		this.zjlx = zjlx;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	public List<String> getTknoList() {
		return tknoList;
	}
	public void setTknoList(List<String> tknoList) {
		this.tknoList = tknoList;
	}
}
