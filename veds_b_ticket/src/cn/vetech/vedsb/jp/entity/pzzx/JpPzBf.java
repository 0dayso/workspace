package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_PZ_BF")//票证库存
public class JpPzBf extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="no")
    private String bfNo;

	/**
	 * 报废时间
	 */
    private Date bfDatetime;
    /**
     * 用户编号
     */
    private String yhbh;
    /**
     * 部门编号
     */
    private String bmbh;
    /**
     * 商户编号
     */
    private String shbh;
    /**
     * 起始码
     */
    private String startno;
    /**
     * 终止码
     */
    private String endno;
    /**
     * 备注
     */
    private String bzbz;
    /**
     * 是否审核，0未审核 1已审核 2已作废
     */
    private String sfsh;
    /**
     * 票证状态
     */
    private String pztype;
    /**
     * 审核用户编号
     */
    private String shYhbh;
    /**
     * 审核部门编号
     */
    private String shBmbh;
    /**
     * 审核时间
     */
    private Date shDatetime;
    
    /**
     * OFFICEID
     */
    private String officeid;
    

    public String getOfficeid() {
		return officeid;
	}

	public void setOfficeid(String officeid) {
		this.officeid = officeid;
	}

	public String getBfNo() {
        return bfNo;
    }

    public void setBfNo(String bfNo) {
        this.bfNo = bfNo == null ? null : bfNo.trim();
    }

    public Date getBfDatetime() {
        return bfDatetime;
    }

    public void setBfDatetime(Date bfDatetime) {
        this.bfDatetime = bfDatetime;
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh == null ? null : yhbh.trim();
    }

    public String getBmbh() {
        return bmbh;
    }

    public void setBmbh(String bmbh) {
        this.bmbh = bmbh == null ? null : bmbh.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
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

    public String getBzbz() {
        return bzbz;
    }

    public void setBzbz(String bzbz) {
        this.bzbz = bzbz == null ? null : bzbz.trim();
    }

    public String getSfsh() {
        return sfsh;
    }

    public void setSfsh(String sfsh) {
        this.sfsh = sfsh == null ? null : sfsh.trim();
    }

    public String getPztype() {
		return pztype;
	}

	public void setPztype(String pztype) {
		this.pztype = pztype== null ? null : pztype.trim();
	}

	public String getShYhbh() {
        return shYhbh;
    }

    public void setShYhbh(String shYhbh) {
        this.shYhbh = shYhbh == null ? null : shYhbh.trim();
    }

    public String getShBmbh() {
        return shBmbh;
    }

    public void setShBmbh(String shBmbh) {
        this.shBmbh = shBmbh == null ? null : shBmbh.trim();
    }

    public Date getShDatetime() {
        return shDatetime;
    }

    public void setShDatetime(Date shDatetime) {
        this.shDatetime = shDatetime;
    }
}