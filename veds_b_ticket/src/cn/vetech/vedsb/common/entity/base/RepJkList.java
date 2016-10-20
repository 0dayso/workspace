package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "REP_JK_LIST")
public class RepJkList extends AbstractPageEntity implements Serializable {

	private static final long serialVersionUID = -7702197502282226080L;

	private String id;

    private String cpbh;

    private String jklx;

    private String jkmc;

    private String zfdjm;

    private String zfgs;

    private String bbh;

    private String klx;

    private String by1;

    private String by2;

    private String by3;

    private String czuserid;

    private String zgs;

    private Date createtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCpbh() {
        return cpbh;
    }

    public void setCpbh(String cpbh) {
        this.cpbh = cpbh == null ? null : cpbh.trim();
    }

    public String getJklx() {
        return jklx;
    }

    public void setJklx(String jklx) {
        this.jklx = jklx == null ? null : jklx.trim();
    }

    public String getJkmc() {
        return jkmc;
    }

    public void setJkmc(String jkmc) {
        this.jkmc = jkmc == null ? null : jkmc.trim();
    }

    public String getZfdjm() {
        return zfdjm;
    }

    public void setZfdjm(String zfdjm) {
        this.zfdjm = zfdjm == null ? null : zfdjm.trim();
    }

    public String getZfgs() {
        return zfgs;
    }

    public void setZfgs(String zfgs) {
        this.zfgs = zfgs == null ? null : zfgs.trim();
    }

    public String getBbh() {
        return bbh;
    }

    public void setBbh(String bbh) {
        this.bbh = bbh == null ? null : bbh.trim();
    }

    public String getKlx() {
        return klx;
    }

    public void setKlx(String klx) {
        this.klx = klx == null ? null : klx.trim();
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

    public String getBy3() {
        return by3;
    }

    public void setBy3(String by3) {
        this.by3 = by3 == null ? null : by3.trim();
    }

    public String getCzuserid() {
        return czuserid;
    }

    public void setCzuserid(String czuserid) {
        this.czuserid = czuserid == null ? null : czuserid.trim();
    }

    public String getZgs() {
        return zgs;
    }

    public void setZgs(String zgs) {
        this.zgs = zgs == null ? null : zgs.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
