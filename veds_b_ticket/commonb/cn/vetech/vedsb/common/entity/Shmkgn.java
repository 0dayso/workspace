package cn.vetech.vedsb.common.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.entity.BusinessId;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "SH_MKGN")
public class Shmkgn extends AbstractPageEntity {

	private static final long serialVersionUID = 5056974101101731476L;

	@Id
	@NotBlank
	private String bh; // 编号主键

	private String gnmc; // 功能名称

	@BusinessId
	@NotBlank
	private String mkbh; // 模块编号主键

	private String gnurl; // 功能URL

	private String bzbz; // 备注

	private String sfsf; // 是否收费0否，1是

	@NotBlank
	private String cj_userid; // 创建人

	private Date cjsj; // 创建时间

	private String xg_userid; // 最后修改人

	private Date xgsj; // 最后修改时间
	
	@Transient
	private String lx="2";    //游离字段类型
	@Transient
	private String iskq="0";//1开启，0未开启
	
	private String sfxsqx;//是否显示权限
	
	private String sfxsbzxx;//是否显示帮助信息
	
	public String getSfxsqx() {
		return sfxsqx;
	}
	public void setSfxsqx(String sfxsqx) {
		this.sfxsqx = sfxsqx;
	}
	public String getSfxsbzxx() {
		return sfxsbzxx;
	}
	public void setSfxsbzxx(String sfxsbzxx) {
		this.sfxsbzxx = sfxsbzxx;
	}
	public String getIskq() {
		return iskq;
	}
	public void setIskq(String iskq) {
		this.iskq = iskq;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getGnmc() {
		return gnmc;
	}

	public void setGnmc(String gnmc) {
		this.gnmc = gnmc;
	}

	public String getMkbh() {
		return mkbh;
	}

	public void setMkbh(String mkbh) {
		this.mkbh = mkbh;
	}

	public String getGnurl() {
		return gnurl;
	}

	public void setGnurl(String gnurl) {
		this.gnurl = gnurl;
	}

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
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
