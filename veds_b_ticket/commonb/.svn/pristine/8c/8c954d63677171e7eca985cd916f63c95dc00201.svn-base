package cn.vetech.vedsb.common.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.entity.BusinessId;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "SH_BM")
public class Shbm extends AbstractPageEntity {

	private static final long serialVersionUID = -981197056581649802L;

	@Id
	private String id; // 部门ID主键

	@BusinessId
	private String shbh; // 商户编号

	private String mc; // 部门名称

	private String jp; // 简拼

	private String lxr; // 联系人

	private String dh; // 联系电话

	private String cz; // 传真

	private String dz; // 地址

	@NotBlank
	private String sfyx; // 是否有效1有效

	private String bzbz; // 备注

	private String cjyh; // 创建用户自动创建固定为0000

	private Date cjsj; // 创建日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
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

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
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

}
