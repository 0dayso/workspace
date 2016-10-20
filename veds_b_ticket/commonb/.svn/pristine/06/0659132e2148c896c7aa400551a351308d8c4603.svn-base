package cn.vetech.vedsb.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.service.BYdlog;

@Table(name = "SH_CSB")
public class Shcsb extends   AbstractPageEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(generator="no")
	private String id;        //主键
    @NotBlank
	private String bh;        //编号
    @NotBlank
    @BYdlog(name="参数值1",logView=1)
	private String csz1;        //参数值1
    @BYdlog(name="参数值2",logView=1)
	private String csz2;        //参数值2
    @NotBlank
    @BYdlog(name="参数值描述",logView=1)
	private String csms;        //参数描述
    @NotBlank
    @BYdlog(name="参数类型编号",logView=1)
	private String cslxbh;        //参数类型编号
    @NotBlank
    @BYdlog(name="参数类型名称",logView=1)
	private String cslxmc;        //参数类型名称
    @NotBlank
	private String shbh;        //所属商户
    @NotBlank
	private String sfxs;        //是否显示，1显示，0不显示
    @NotBlank
	private String cj_userid;        //创建人
    
    @Formula("(select xm from SH_YHB where bh=entity.cj_userid)")
    private String cj_usermc;
    
    @NotNull
	private Date cjsj;        //创建时间
    
	private String xg_userid;        //最后修改人
	@Formula("(select xm from SH_YHB where bh=entity.xg_userid)")
    private String xg_usermc;
	
	private Date xgsj;        //最后修改时间
	
	@Transient
	@BYdlog(name="是否显示",logView=1)
	private String sfxss;
	
	public String getSfxss() {
		if("0".equals(sfxs)){
			return "否";
		}else{
			return "是";
		}
	}
	public void setSfxss(String sfxss) {
		this.sfxss = sfxss;
	}
	@Transient
	public String getCjsjStr(){
		return VeDate.dateToStrLong(this.cjsj);
	}
	@Transient
	public String getXgsjStr(){
		return VeDate.dateToStrLong(this.xgsj);
	}
	
	public String getXg_usermc() {
		return xg_usermc;
	}
	public void setXg_usermc(String xg_usermc) {
		this.xg_usermc = xg_usermc;
	}
	public String getCj_usermc() {
		return cj_usermc;
	}
	public void setCj_usermc(String cj_usermc) {
		this.cj_usermc = cj_usermc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBh() {
		return bh;
	}
	public void setBh(String bh) {
		this.bh = bh;
	}
	public String getCsz1() {
		return csz1;
	}
	public void setCsz1(String csz1) {
		this.csz1 = csz1;
	}
	public String getCsz2() {
		return csz2;
	}
	public void setCsz2(String csz2) {
		this.csz2 = csz2;
	}
	public String getCsms() {
		return csms;
	}
	public void setCsms(String csms) {
		this.csms = csms;
	}
	public String getCslxbh() {
		return cslxbh;
	}
	public void setCslxbh(String cslxbh) {
		this.cslxbh = cslxbh;
	}
	public String getCslxmc() {
		return cslxmc;
	}
	public void setCslxmc(String cslxmc) {
		this.cslxmc = cslxmc;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getSfxs() {
		return sfxs;
	}
	public void setSfxs(String sfxs) {
		this.sfxs = sfxs;
	}
	public String getCj_userid() {
		return cj_userid;
	}
	public void setCj_userid(String cj_userid) {
		this.cj_userid = cj_userid;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getXg_userid() {
		return xg_userid;
	}
	public void setXg_userid(String xg_userid) {
		this.xg_userid = xg_userid;
	}
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

}
