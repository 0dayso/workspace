package cn.vetech.vedsb.jp.entity.jpgqgl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_gqd_cjr")
public class JpGqdCjr extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = 6532299018124630558L;

	/**
	 * 	ID号主键
	 */
	@Id
	@GeneratedValue(generator="no")
    private String id;

	/**
	 * 改签单号
	 */
    private String gqdh;

    /**
     * 票号
     */
    private String tkno;

    /**
     * 商户编号
     */
    private String shbh;

    /**
     * 行程单号
     */
    private String xcdh;

    /**
     * 顺序号
     */
    private Short sxh;

    /**
     * 乘机人类型
     */
    private String cjrlx;

    /**
     * 乘机人
     */
    private String cjr;

    /**
     * 证件类型
     */
    private String zjlx;

    /**
     * 证件号码
     */
    private String zjhm;

    /**
     * 手机号码
     */
    private String sjhm;

    /**
     * 销售改签费用
     */
    private BigDecimal gqXsfy;

    /**
     * 采购改签费用
     */
    private BigDecimal gqCgfy;

    /**
     * 性别
     */
    private String xb;

    /**
     * 国籍
     */
    private String gj;

    /**
     * 出生日期
     */
    private String csrq;

    /**
     * 证件有效期
     */
    private String zjyxq;

    /**
     * 
     */
    private String zjqfg;

    /**
     * 改签后票号
     */
    private String gqTkno;
    
    /**
     * 改签采购来源
     */
    private String gqCgly;
    
    /**改签销售升舱费用*/
    private BigDecimal gqXsscfy;
    /**改签采购升舱费用*/
    private BigDecimal gqCgscfy;
    
    public BigDecimal getGqXsscfy() {
		return gqXsscfy;
	}

	public void setGqXsscfy(BigDecimal gqXsscfy) {
		this.gqXsscfy = gqXsscfy;
	}

	public BigDecimal getGqCgscfy() {
		return gqCgscfy;
	}

	public void setGqCgscfy(BigDecimal gqCgscfy) {
		this.gqCgscfy = gqCgscfy;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGqdh() {
        return gqdh;
    }

    public void setGqdh(String gqdh) {
        this.gqdh = gqdh == null ? null : gqdh.trim();
    }

    public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno == null ? null : tkno.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getXcdh() {
        return xcdh;
    }

    public void setXcdh(String xcdh) {
        this.xcdh = xcdh == null ? null : xcdh.trim();
    }

    public Short getSxh() {
        return sxh;
    }

    public void setSxh(Short sxh) {
        this.sxh = sxh;
    }

    public String getCjrlx() {
        return cjrlx;
    }

    public void setCjrlx(String cjrlx) {
        this.cjrlx = cjrlx == null ? null : cjrlx.trim();
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx == null ? null : zjlx.trim();
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm == null ? null : zjhm.trim();
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm == null ? null : sjhm.trim();
    }

    public BigDecimal getGqXsfy() {
        return gqXsfy;
    }

    public void setGqXsfy(BigDecimal gqXsfy) {
        this.gqXsfy = gqXsfy;
    }

    public BigDecimal getGqCgfy() {
        return gqCgfy;
    }

    public void setGqCgfy(BigDecimal gqCgfy) {
        this.gqCgfy = gqCgfy;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb == null ? null : xb.trim();
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj == null ? null : gj.trim();
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq == null ? null : csrq.trim();
    }

    public String getZjyxq() {
        return zjyxq;
    }

    public void setZjyxq(String zjyxq) {
        this.zjyxq = zjyxq == null ? null : zjyxq.trim();
    }

    public String getZjqfg() {
        return zjqfg;
    }

    public void setZjqfg(String zjqfg) {
        this.zjqfg = zjqfg == null ? null : zjqfg.trim();
    }

    public String getGqTkno() {
        return gqTkno;
    }

    public void setGqTkno(String gqTkno) {
        this.gqTkno = gqTkno == null ? null : gqTkno.trim();
    }

	public String getGqCgly() {
		return gqCgly;
	}

	public void setGqCgly(String gqCgly) {
		this.gqCgly = gqCgly;
	}
	
}