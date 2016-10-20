package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "sms_mbfl")
public class SmsMbfl extends AbstractPageEntity implements Serializable {

	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	private String yycj;//应用场景
	private String mbbh;//模板编号
	private String lx;//类型1平台2商户
	private String shbh;//平台存CPS,商户存商户编号
	private String czuserid;//操作用户
	private Date czdatetime;//
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYycj() {
		return yycj;
	}
	public void setYycj(String yycj) {
		this.yycj = yycj;
	}
	public String getMbbh() {
		return mbbh;
	}
	public void setMbbh(String mbbh) {
		this.mbbh = mbbh;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getCzuserid() {
		return czuserid;
	}
	public void setCzuserid(String czuserid) {
		this.czuserid = czuserid;
	}
	public Date getCzdatetime() {
		return czdatetime;
	}
	public void setCzdatetime(Date czdatetime) {
		this.czdatetime = czdatetime;
	}
	
	
}
