package cn.vetech.vedsb.jp.entity.jpztjk;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_kpzt")
public class Jpkpzt extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = -2639425500663821479L;
	//票号
	private String tkno;
	//机票状态
    private String status;
    //记录原机票的出票时间
    private String cpDate;
    //pnr编号
    private String pnrNo;
    //订单编号
    private String ddbh;
    //采购来源
    private String cgly;
    //0为未处理，1为已处理
    private String clZt;
    //处理时间
    private String clDatetime;
    //处理人
    private String clUserid;
    //处理部门
    private String clDeptid;
    //处理公司
    private String clCompid;
    //处理备注
    private String clBz;
    //商户编号
    private String shbh;
    //第一次提取时间,用来查询哪一天提取到的OPEN票
    private String dqDatetime;
    //OPEN被处理后,提取REFUND状态的时间
    private String zhDatetime;
    //最后变成REFUND状态的时间
    private String zhStatus;
    //1表示未在本地退票,0或空表示已在本地退票,客票状态含有REFUND时要判断写入
    private String bdwt;
    //改签后出票时间
    private String gqDate;

    //界面查询sql条件字段
    @Transient
    private String kssj,jssj,cprs,cprz;
    
    public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getCprs() {
		return cprs;
	}

	public void setCprs(String cprs) {
		this.cprs = cprs;
	}

	public String getCprz() {
		return cprz;
	}

	public void setCprz(String cprz) {
		this.cprz = cprz;
	}

	public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno == null ? null : tkno.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCpDate() {
        return cpDate;
    }

    public void setCpDate(String cpDate) {
        this.cpDate = cpDate == null ? null : cpDate.trim();
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo == null ? null : pnrNo.trim();
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh == null ? null : ddbh.trim();
    }

    public String getCgly() {
        return cgly;
    }

    public void setCgly(String cgly) {
        this.cgly = cgly == null ? null : cgly.trim();
    }

    public String getClZt() {
        return clZt;
    }

    public void setClZt(String clZt) {
        this.clZt = clZt == null ? null : clZt.trim();
    }

    public String getClDatetime() {
        return clDatetime;
    }

    public void setClDatetime(String clDatetime) {
        this.clDatetime = clDatetime == null ? null : clDatetime.trim();
    }

    public String getClUserid() {
        return clUserid;
    }

    public void setClUserid(String clUserid) {
        this.clUserid = clUserid == null ? null : clUserid.trim();
    }

    public String getClDeptid() {
        return clDeptid;
    }

    public void setClDeptid(String clDeptid) {
        this.clDeptid = clDeptid == null ? null : clDeptid.trim();
    }

    public String getClCompid() {
        return clCompid;
    }

    public void setClCompid(String clCompid) {
        this.clCompid = clCompid == null ? null : clCompid.trim();
    }

    public String getClBz() {
        return clBz;
    }

    public void setClBz(String clBz) {
        this.clBz = clBz == null ? null : clBz.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getDqDatetime() {
        return dqDatetime;
    }

    public void setDqDatetime(String dqDatetime) {
        this.dqDatetime = dqDatetime == null ? null : dqDatetime.trim();
    }

    public String getZhDatetime() {
        return zhDatetime;
    }

    public void setZhDatetime(String zhDatetime) {
        this.zhDatetime = zhDatetime == null ? null : zhDatetime.trim();
    }

    public String getZhStatus() {
        return zhStatus;
    }

    public void setZhStatus(String zhStatus) {
        this.zhStatus = zhStatus == null ? null : zhStatus.trim();
    }

    public String getBdwt() {
        return bdwt;
    }

    public void setBdwt(String bdwt) {
        this.bdwt = bdwt == null ? null : bdwt.trim();
    }

    public String getGqDate() {
        return gqDate;
    }

    public void setGqDate(String gqDate) {
        this.gqDate = gqDate == null ? null : gqDate.trim();
    }

}
