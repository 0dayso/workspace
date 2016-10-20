package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_PZ_TH")//票证退回
public class JpPzTh extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = -334432961401329738L;
	@Id
	@GeneratedValue(generator="no")
	private String thNo;
	/**
	 * 退回时间
	 */
    private Date thDatetime;
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
     * 票证类型
     */
    private String pztype;
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

    private String pzpc;
    /**
     * 票证分类
     */
    private String pzfl;
    /**
     * 审核数量
     */
    private Long shsl;
    /**
     * 审核用户编号
     */
    private String shYhbh;
    /**
     * 审核部门编号
     */
    private String shBmbh;
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

	public String getThNo() {
        return thNo;
    }

    public void setThNo(String thNo) {
        this.thNo = thNo == null ? null : thNo.trim();
    }

    public Date getThDatetime() {
        return thDatetime;
    }

    public void setThDatetime(Date thDatetime) {
        this.thDatetime = thDatetime;
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

    public String getPztype() {
        return pztype;
    }

    public void setPztype(String pztype) {
        this.pztype = pztype == null ? null : pztype.trim();
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

    public String getPzpc() {
        return pzpc;
    }

    public void setPzpc(String pzpc) {
        this.pzpc = pzpc == null ? null : pzpc.trim();
    }

    public String getPzfl() {
        return pzfl;
    }

    public void setPzfl(String pzfl) {
        this.pzfl = pzfl == null ? null : pzfl.trim();
    }

    public Long getShsl() {
        return shsl;
    }

    public void setShsl(Long shsl) {
        this.shsl = shsl;
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
}