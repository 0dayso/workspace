package cn.vetech.vedsb.common.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.entity.BusinessId;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

/**
 * 商户部门分组表
 */
@Table(name = "SH_BMFZ")
public class Shbmfz extends AbstractPageEntity {

	private static final long serialVersionUID = -6138426371630805416L;

	@Id
	private String id; // 分组ID主键

	@NotBlank
	private String mc; // 组名称

	private String jp; // 简拼

	@BusinessId
	private String shbh; // 商户编号

	@NotBlank
	private String shbmid; // 所属部门

	private String ms; // 分组描述

	private String cjyh; // 创建用户

	private Date cjsj; // 创建日期

	@NotBlank
	private String sfyx; // 是否有效1有效

	private String xg_userid; // 最后修改人

	private Date xgsj; // 最后修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getShbmid() {
		return shbmid;
	}

	public void setShbmid(String shbmid) {
		this.shbmid = shbmid;
	}

	public String getMs() {
		return ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getCjyh() {
		return cjyh;
	}

	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
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
	
	public String getCjsjmc() {
		return VeDate.dateToStrLong(cjsj);
	}

}
