package cn.vetech.vedsb.jp.entity.jphbyd;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="B_HKGS_HBGZ")
public class BHkgsHbgz extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	//航空公司(---标识所有适用，可多选/分隔)
	private String hkgs;
	//规则类型(1提前 2延误)
	private String lx;
	//时长(延长/提前)
	private int sc;
	//修改人
	private String xguserid;
	//修改时间
	private Date xgDatetime;
	//备注
	private String bzbz;
	//商户编号
	private String shbh;
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
		this.hkgs = hkgs == null ? null : hkgs.trim();
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx == null ? null : lx.trim();
	}
	public int getSc() {
		return sc;
	}
	public void setSc(int sc) {
		this.sc = sc;
	}
	public String getXguserid() {
		return xguserid;
	}
	public void setXguserid(String xguserid) {
		this.xguserid = xguserid;
	}
	public Date getXgDatetime() {
		return xgDatetime;
	}
	public void setXgDatetime(Date xgDatetime) {
		this.xgDatetime = xgDatetime;
	}
	public String getBzbz() {
		return bzbz ;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz == null ? null : bzbz.trim();
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	
	
}
