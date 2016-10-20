package cn.vetech.vedsb.jp.entity.pzzx;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_PZ_IN")
public class JpPzIn extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = -5304485497651225469L;

	private String inNo;//主键id

    private Date inDatetime;//插入时间

    private String yhbh;//用户编号

    private String bmbh;//部门编号

    private String shbh;//商户编号

    private String startno;//起始号码

    private String endno;//结束号码

    private String bzbz;//备注

    private String officeid;//office号

    private String sfsh;//是否审核：0：未审核  1：已审核  2：已作废

    private String pztype;//票证类型：机票，保险等

    private Long rksl;//入库数量

    private Long ffsl;//发放数量

    private String shYhbh;//审核用户编号

    private String shBmbh;//审核部门编号

    private Date shDatetime;//审核时间

    public String getInNo() {
        return inNo;
    }

    public void setInNo(String inNo) {
        this.inNo = inNo == null ? null : inNo.trim();
    }

    public Date getInDatetime() {
        return inDatetime;
    }

    public void setInDatetime(Date inDatetime) {
        this.inDatetime = inDatetime;
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

    public String getOfficeid() {
        return officeid;
    }

    public void setOfficeid(String officeid) {
        this.officeid = officeid == null ? null : officeid.trim();
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
        this.pztype = pztype == null ? null : pztype.trim();
    }

    public Long getRksl() {
        return rksl;
    }

    public void setRksl(Long rksl) {
        this.rksl = rksl;
    }

    public Long getFfsl() {
        return ffsl;
    }

    public void setFfsl(Long ffsl) {
        this.ffsl = ffsl;
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