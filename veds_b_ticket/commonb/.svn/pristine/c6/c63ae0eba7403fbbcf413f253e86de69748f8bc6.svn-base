package cn.vetech.vedsb.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;
/**
 * 操作日志entity
 */
public class BYdrzCzrz extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;         //ID主键，序列生成
	private String ywid;       //主表ID号
	private Date czsj;         //操作时间
	private String czr;        //操作人
	private String pt;         //平台A、B (默认A)
	private String czly;       //操作来源
	private String cznr;       //操作内容
	private String czip;       //操作IP
	private String czjqm;      //操作机器名
	private String ckfw;       //查看范围0表示A\B系统都可查看
	private String bzbz;       //备注
	private String shbh;       //商户编号
	@Transient
	private String bm;
	@Transient
	private String mx;
	@Transient
	private Date czsjs;
	@Transient
	private Date czsjz;
	
	@Transient
	public String getCzsjsStr(){
		return VeDate.dateToStrLong(this.czsjs);
	}
	@Transient
	public String getCzsjzStr(){
		return VeDate.dateToStrLong(this.czsjz);
	}
	public Date getCzsjs() {
		return czsjs;
	}
	public void setCzsjs(Date czsjs) {
		this.czsjs = czsjs;
	}
	public Date getCzsjz() {
		return czsjz;
	}
	public void setCzsjz(Date czsjz) {
		this.czsjz = czsjz;
	}
	@Transient
	public String getCzsjStr(){
		return VeDate.dateToStrLong(this.czsj);
	}

	public String getMx() {
		return mx;
	}
	public void setMx(String mx) {
		this.mx = mx;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	public String getCzr() {
		return czr;
	}
	public void setCzr(String czr) {
		this.czr = czr;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public String getCzly() {
		return czly;
	}
	public void setCzly(String czly) {
		this.czly = czly;
	}
	public String getCznr() {
		return cznr;
	}
	public void setCznr(String cznr) {
		this.cznr = cznr;
	}
	public String getCzip() {
		return czip;
	}
	public void setCzip(String czip) {
		this.czip = czip;
	}
	public String getCzjqm() {
		return czjqm;
	}
	public void setCzjqm(String czjqm) {
		this.czjqm = czjqm;
	}
	public String getCkfw() {
		return ckfw;
	}
	public void setCkfw(String ckfw) {
		this.ckfw = ckfw;
	}
	public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	
	
}
