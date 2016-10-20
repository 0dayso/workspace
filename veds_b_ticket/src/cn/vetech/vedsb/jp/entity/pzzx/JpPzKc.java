package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_PZ_KC")
public class JpPzKc extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = 4520454988042942246L;

	private String id;//主键id

    private String startno;//起始号码

    private String endno;//终止号码

    private String dqhm;//到期号码

    private int sl;//数量

    private String shbh;//商户编号

    private String bmbh;//部门编号

    private String czYhbh;//操作人

    private Date czDatetime;//操作时间

    private String pzzt;//票证状态

    private String by1;//备用

    private String by2;//

    private String xzbh;//

    private String inNo;//记录了jp_pz_in表里面的主键

    private String kcid;//库存id

    private String czBmbh;//操作部门

    private String pztype;//票证类型：机票，保险等
    
    private String officeid;//office号
    
    private int sysl;//剩余数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStartno() {
        return startno;
    }

    public void setStartno(String startno) {
        this.startno = startno == null ? null : startno.trim();
    }

    public String getEndno() {
        return endno;
    }

    public void setEndno(String endno) {
        this.endno = endno == null ? null : endno.trim();
    }

    public String getDqhm() {
        return dqhm;
    }

    public void setDqhm(String dqhm) {
        this.dqhm = dqhm == null ? null : dqhm.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh == null ? null : bmbh.trim();
    }

    public String getCzYhbh() {
        return czYhbh;
    }

    public void setCzYhbh(String czYhbh) {
        this.czYhbh = czYhbh == null ? null : czYhbh.trim();
    }

    public Date getCzDatetime() {
        return czDatetime;
    }

    public void setCzDatetime(Date czDatetime) {
        this.czDatetime = czDatetime;
    }

    public String getPzzt() {
        return pzzt;
    }

    public void setPzzt(String pzzt) {
        this.pzzt = pzzt == null ? null : pzzt.trim();
    }

    public String getBy1() {
        return by1;
    }

    public void setBy1(String by1) {
        this.by1 = by1 == null ? null : by1.trim();
    }

    public String getBy2() {
        return by2;
    }

    public void setBy2(String by2) {
        this.by2 = by2 == null ? null : by2.trim();
    }

    public String getXzbh() {
        return xzbh;
    }

    public void setXzbh(String xzbh) {
        this.xzbh = xzbh == null ? null : xzbh.trim();
    }

    public String getInNo() {
        return inNo;
    }

    public void setInNo(String inNo) {
        this.inNo = inNo == null ? null : inNo.trim();
    }

    public String getKcid() {
        return kcid;
    }

    public void setKcid(String kcid) {
        this.kcid = kcid == null ? null : kcid.trim();
    }

    public String getCzBmbh() {
        return czBmbh;
    }

    public void setCzBmbh(String czBmbh) {
        this.czBmbh = czBmbh == null ? null : czBmbh.trim();
    }

    public String getPztype() {
        return pztype;
    }

    public void setPztype(String pztype) {
        this.pztype = pztype == null ? null : pztype.trim();
    }

	public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

	public int getSl() {
		return sl;
	}

	public void setSl(int sl) {
		this.sl = sl;
	}

	public int getSysl() {
		return sysl;
	}

	public void setSysl(int sysl) {
		this.sysl = sysl;
	}
    
    
}