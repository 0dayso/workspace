package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_XCD")
public class JpXcd  extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private String xcdNo;

    private String shbh;

    private String officeid;

	private String tkno;

    private String pnrno;

    private String hkgs;

    private String pzzt;

    private String pztype;

    private Date rkDatetime;

    private String rkBmbh;

    private String rkYhbh;

    private String outBmbh;

    private String outYhbh;

    private Date outDatetime;

    private String jbBmbh;

    private String jbYhbh;

    private Date jbDatetime;

    private Short printNum;

    private Date tfDatetime;

    private String tfBmbh;

    private String tfYhbh;

    private Date ckDatetime;

    private String ckYhbh;

    private String ckBmbh;

    private String jkfhsj;
    
    public String getOfficeid() {
		return officeid;
	}
    public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

    public String getXcdNo() {
        return xcdNo;
    }

    public void setXcdNo(String xcdNo) {
        this.xcdNo = xcdNo == null ? null : xcdNo.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno == null ? null : tkno.trim();
    }

    public String getPnrno() {
        return pnrno;
    }

    public void setPnrno(String pnrno) {
        this.pnrno = pnrno == null ? null : pnrno.trim();
    }

    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs == null ? null : hkgs.trim();
    }

    public String getPzzt() {
        return pzzt;
    }

    public void setPzzt(String pzzt) {
        this.pzzt = pzzt == null ? null : pzzt.trim();
    }

    public String getPztype() {
        return pztype;
    }

    public void setPztype(String pztype) {
        this.pztype = pztype == null ? null : pztype.trim();
    }

    public Date getRkDatetime() {
		return rkDatetime;
	}

	public void setRkDatetime(Date rkDatetime) {
		this.rkDatetime = rkDatetime;
	}

	public Date getOutDatetime() {
		return outDatetime;
	}

	public void setOutDatetime(Date outDatetime) {
		this.outDatetime = outDatetime;
	}

	public Date getTfDatetime() {
		return tfDatetime;
	}

	public void setTfDatetime(Date tfDatetime) {
		this.tfDatetime = tfDatetime;
	}

	public Date getCkDatetime() {
		return ckDatetime;
	}

	public void setCkDatetime(Date ckDatetime) {
		this.ckDatetime = ckDatetime;
	}

	public String getRkBmbh() {
        return rkBmbh;
    }

    public void setRkBmbh(String rkBmbh) {
        this.rkBmbh = rkBmbh == null ? null : rkBmbh.trim();
    }

    public String getRkYhbh() {
        return rkYhbh;
    }

    public void setRkYhbh(String rkYhbh) {
        this.rkYhbh = rkYhbh == null ? null : rkYhbh.trim();
    }

    public String getOutBmbh() {
        return outBmbh;
    }

    public void setOutBmbh(String outBmbh) {
        this.outBmbh = outBmbh == null ? null : outBmbh.trim();
    }

    public String getOutYhbh() {
        return outYhbh;
    }

    public void setOutYhbh(String outYhbh) {
        this.outYhbh = outYhbh == null ? null : outYhbh.trim();
    }

    public String getJbBmbh() {
        return jbBmbh;
    }

    public void setJbBmbh(String jbBmbh) {
        this.jbBmbh = jbBmbh == null ? null : jbBmbh.trim();
    }

    public String getJbYhbh() {
        return jbYhbh;
    }

    public void setJbYhbh(String jbYhbh) {
        this.jbYhbh = jbYhbh == null ? null : jbYhbh.trim();
    }

    public Date getJbDatetime() {
		return jbDatetime;
	}
	public void setJbDatetime(Date jbDatetime) {
		this.jbDatetime = jbDatetime;
	}
	public Short getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Short printNum) {
        this.printNum = printNum;
    }

    public String getTfBmbh() {
        return tfBmbh;
    }

    public void setTfBmbh(String tfBmbh) {
        this.tfBmbh = tfBmbh == null ? null : tfBmbh.trim();
    }

    public String getTfYhbh() {
        return tfYhbh;
    }

    public void setTfYhbh(String tfYhbh) {
        this.tfYhbh = tfYhbh == null ? null : tfYhbh.trim();
    }

    public String getCkYhbh() {
        return ckYhbh;
    }

    public void setCkYhbh(String ckYhbh) {
        this.ckYhbh = ckYhbh == null ? null : ckYhbh.trim();
    }

    public String getCkBmbh() {
        return ckBmbh;
    }

    public void setCkBmbh(String ckBmbh) {
        this.ckBmbh = ckBmbh == null ? null : ckBmbh.trim();
    }

    public String getJkfhsj() {
        return jkfhsj;
    }

    public void setJkfhsj(String jkfhsj) {
        this.jkfhsj = jkfhsj == null ? null : jkfhsj.trim();
    }
}