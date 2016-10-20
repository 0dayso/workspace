/**
*@Title:       
*@Description: 
*@Author:      
*@Version:     1.1
*@Date:        2015-04-09
*@Modify:      
*/

package cn.vetech.vedsb.jp.entity.airway;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "B_AIR_PRICE")
public class Bairprice extends AbstractPageEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator="no")

    @NotBlank
	private String id;
	
    @NotBlank
	private String cfcity;        //出发城市
    @Formula("(select mc from B_CITY where nbbh=entity.cfcity)")
    private String cfcitymc;
    
    @NotBlank
	private String ddcity;        //到达城市
    @Formula("(select mc from B_CITY where nbbh=entity.ddcity)")
    private String ddcitymc;
    
    @NotBlank
	private String ycj;        //运价
    @NotBlank
	private String jsf;        //建设费
    @NotBlank
	private String rys;        //燃油费
	private String bzyxsj;
	
	private String qfjc;        //起飞机场
	@Formula("(select jcmc from B_CITY where nbbh=entity.qfjc)")
    private String qfjcmc;
	
	private String ddjc;        //到达机场
	@Formula("(select jcmc from B_CITY where nbbh=entity.ddjc)")
    private String ddjcmc;
	
	private String bzbz;
	private String by1;
	private String by2;
    @NotBlank
	private String jsf1;        //建设费1
	private String lc;        //里程
	private String czdatetime;
	
	private String hkgs;        //航空公司
	@Formula("(select shortname from B_AIRWAY where ezdh=entity.hkgs)")
    private String hkgsmc;
	
	private String yxq;        //有效期
	private String zhxgdatetime;        //最后修改时间

	
	public String getQfjcmc() {
		return qfjcmc;
	}
	public void setQfjcmc(String qfjcmc) {
		this.qfjcmc = qfjcmc;
	}
	public String getDdjcmc() {
		return ddjcmc;
	}
	public void setDdjcmc(String ddjcmc) {
		this.ddjcmc = ddjcmc;
	}
	public String getCfcitymc() {
		return cfcitymc;
	}
	public void setCfcitymc(String cfcitymc) {
		this.cfcitymc = cfcitymc;
	}
	public String getDdcitymc() {
		return ddcitymc;
	}
	public void setDdcitymc(String ddcitymc) {
		this.ddcitymc = ddcitymc;
	}
	public String getHkgsmc() {
		return hkgsmc;
	}
	public void setHkgsmc(String hkgsmc) {
		this.hkgsmc = hkgsmc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCfcity() {
		return cfcity;
	}
	public void setCfcity(String cfcity) {
		this.cfcity = cfcity;
	}
	public String getDdcity() {
		return ddcity;
	}
	public void setDdcity(String ddcity) {
		this.ddcity = ddcity;
	}
	public String getYcj() {
		return ycj;
	}
	public void setYcj(String ycj) {
		this.ycj = ycj;
	}
	public String getJsf() {
		return jsf;
	}
	public void setJsf(String jsf) {
		this.jsf = jsf;
	}
	public String getRys() {
		return rys;
	}
	public void setRys(String rys) {
		this.rys = rys;
	}
	public String getBzyxsj() {
		return bzyxsj;
	}
	public void setBzyxsj(String bzyxsj) {
		this.bzyxsj = bzyxsj;
	}
	public String getQfjc() {
		return qfjc;
	}
	public void setQfjc(String qfjc) {
		this.qfjc = qfjc;
	}
	public String getDdjc() {
		return ddjc;
	}
	public void setDdjc(String ddjc) {
		this.ddjc = ddjc;
	}
	public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}
	public String getBy1() {
		return by1;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}
	public String getBy2() {
		return by2;
	}
	public void setBy2(String by2) {
		this.by2 = by2;
	}
	public String getJsf1() {
		return jsf1;
	}
	public void setJsf1(String jsf1) {
		this.jsf1 = jsf1;
	}
	public String getLc() {
		return lc;
	}
	public void setLc(String lc) {
		this.lc = lc;
	}
	public String getCzdatetime() {
		return czdatetime;
	}
	public void setCzdatetime(String czdatetime) {
		this.czdatetime = czdatetime;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getYxq() {
		return yxq;
	}
	public void setYxq(String yxq) {
		this.yxq = yxq;
	}
	public String getZhxgdatetime() {
		return zhxgdatetime;
	}
	public void setZhxgdatetime(String zhxgdatetime) {
		this.zhxgdatetime = zhxgdatetime;
	}

}
