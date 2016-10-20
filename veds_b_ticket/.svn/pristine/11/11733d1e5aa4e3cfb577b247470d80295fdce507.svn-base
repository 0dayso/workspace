package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "ve_csb")
public class Vecsb extends AbstractPageEntity implements Serializable {
    
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
    private String bh;//参数编码
    private String csms;//参数描述
    private String csz1;
    private String csz2;
    private String cslxbh;//参数类型编号
    private String xg_userid;//
    private String cj_userid;//创建人
    private String sfxs;//是否显示，1显示，0不显示
    private String compid;//所属公司
    private String cslxmc;//参数类型名称
    private Date cjsj;
    private Date xgsj;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBh() {
        return bh;
    }

    public void setBh(String bh) {
        this.bh = bh == null ? null : bh.trim();
    }


    public String getCsms() {
        return csms;
    }

    public void setCsms(String csms) {
        this.csms = csms == null ? null : csms.trim();
    }

    public String getCsz1() {
        return csz1;
    }

    public void setCsz1(String csz1) {
        this.csz1 = csz1 == null ? null : csz1.trim();
    }

    public String getCsz2() {
        return csz2;
    }

    public void setCsz2(String csz2) {
        this.csz2 = csz2 == null ? null : csz2.trim();
    }

	public String getCslxbh() {
		return cslxbh;
	}

	public void setCslxbh(String cslxbh) {
		this.cslxbh = cslxbh;
	}

	public String getXg_userid() {
		return xg_userid;
	}

	public void setXg_userid(String xg_userid) {
		this.xg_userid = xg_userid;
	}

	public String getCj_userid() {
		return cj_userid;
	}

	public void setCj_userid(String cj_userid) {
		this.cj_userid = cj_userid;
	}

	public String getSfxs() {
		return sfxs;
	}

	public void setSfxs(String sfxs) {
		this.sfxs = sfxs;
	}

	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getCslxmc() {
		return cslxmc;
	}

	public void setCslxmc(String cslxmc) {
		this.cslxmc = cslxmc;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

}