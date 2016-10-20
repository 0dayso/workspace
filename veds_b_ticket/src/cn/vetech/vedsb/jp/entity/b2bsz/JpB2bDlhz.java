package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_B2B_DLHZ")
public class JpB2bDlhz extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 3591864465659892557L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**航空公司*/
	private String hkgs;
	/**线路名称*/
	private String xlmc;
	/**备注*/
	private String bzbz;
	/**网店*/
	private String wz;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}

	public String getXlmc() {
		return xlmc;
	}

	public void setXlmc(String xlmc) {
		this.xlmc = xlmc;
	}

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}

	public String getWz() {
		return wz;
	}

	public void setWz(String wz) {
		this.wz = wz;
	}

	public String getCzuserid() {
		return czuserid;
	}

	public void setCzuserid(String czuserid) {
		this.czuserid = czuserid;
	}

	public String getCzdatetime() {
		return czdatetime;
	}

	public void setCzdatetime(String czdatetime) {
		this.czdatetime = czdatetime;
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

	public Double getBy3() {
		return by3;
	}

	public void setBy3(Double by3) {
		this.by3 = by3;
	}

	/**操作用户*/
	private String czuserid;
	/**操作时间*/
	private String czdatetime;
	
	private String by1;
	
	private String by2;
	
	private Double by3;
	
	
}
