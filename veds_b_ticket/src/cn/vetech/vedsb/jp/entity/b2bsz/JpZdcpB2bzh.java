package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * B2B自动出票账号
 * @author wangshengliang
 *
 */
@Table(name="JP_ZDCP_B2BZH")
public class JpZdcpB2bzh extends AbstractPageEntity implements Serializable{
 
	private static final long serialVersionUID = 380418388218042919L;
	@Id
	@GeneratedValue(generator="no")
	private String id;

    private String shbh;

    private String yhbh;

    private String czdatetime;

    private String sm;

    private String hkgs;

    private String zh;

    private String mm;

    private String office;

    private String zfid;

    private String by1;

    private String by2;

    private Long by3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh == null ? null : yhbh.trim();
    }

    public String getCzdatetime() {
        return czdatetime;
    }

    public void setCzdatetime(String czdatetime) {
        this.czdatetime = czdatetime == null ? null : czdatetime.trim();
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm == null ? null : sm.trim();
    }

    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs == null ? null : hkgs.trim();
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh == null ? null : zh.trim();
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm == null ? null : mm.trim();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
    }

    public String getZfid() {
        return zfid;
    }

    public void setZfid(String zfid) {
        this.zfid = zfid == null ? null : zfid.trim();
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

    public Long getBy3() {
        return by3;
    }

    public void setBy3(Long by3) {
        this.by3 = by3;
    }
}