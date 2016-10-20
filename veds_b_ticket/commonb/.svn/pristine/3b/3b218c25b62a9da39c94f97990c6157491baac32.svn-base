package cn.vetech.vedsb.common.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 商户表操作日志
 */
@Table(name = "SH_SHB_CZRZ")
public class Shshbczrz extends AbstractPageEntity {

	private static final long serialVersionUID = 5617208777403269347L;

	@Id
	@GeneratedValue(generator = "no")
	@NotBlank
	private String id; // ID号主键

	@NotBlank
	private String shbh; // 商户编号

	@NotBlank
	private Date czsj; // 操作时间

	@NotBlank
	private String czr; // 操作人姓名

	@NotBlank
	private String czly; // 操作来源

	@NotBlank
	private String cznr; // 操作内容

	@NotBlank
	private String czip; // 操作IP

	private String deptid; // 操作人所在部门编号

	private String keyid; // 日志标记--可作为查询时的唯一标记

	private String tablename; // 操作表名

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

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid = keyid;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

}
