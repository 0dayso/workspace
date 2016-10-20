package cn.vetech.vedsb.jp.entity.b2bsz;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * B2B登录账号
 * @author wangshengliang
 *
 */
@Table(name="JP_B2B_DLZH")
public class JpB2bDlzh  extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = 3591864465659892557L;
	@Id
	@GeneratedValue(generator="no")
	private String id;

    private String hkgs;

    private String zh;

    private String mm;

    private String yhbh;

    private String shbh;

    private String cjtime;

    private String office;

    private String by1;

    private String by2;

    private String by3;

    private String bca;

    private String dlfs;

    private String sfsy;
    
    @Transient
    private int pageNum;
    
    @Transient
    private int pageSize;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getYhbh() {
        return yhbh;
    }

    public void setYhbh(String yhbh) {
        this.yhbh = yhbh == null ? null : yhbh.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getCjtime() {
        return cjtime;
    }

    public void setCjtime(String cjtime) {
        this.cjtime = cjtime == null ? null : cjtime.trim();
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office == null ? null : office.trim();
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

    public String getBca() {
        return bca;
    }

    public void setBca(String bca) {
        this.bca = bca == null ? null : bca.trim();
    }

    public String getDlfs() {
        return dlfs;
    }

    public void setDlfs(String dlfs) {
        this.dlfs = dlfs == null ? null : dlfs.trim();
    }

    public String getSfsy() {
        return sfsy;
    }

    public void setSfsy(String sfsy) {
        this.sfsy = sfsy == null ? null : sfsy.trim();
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}