package cn.vetech.vedsb.jp.entity.jpddgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

import cn.vetech.vedsb.jp.entity.jpjpgl.JpJp;

@Table(name="JP_KHDD_CJR")
public class JpKhddCjr  extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = -4291288453009555801L;

	@Id
	@GeneratedValue(generator="no")
    private String id;

    private String ddbh;

    private String shbh;

    private Short sxh;

    private String cjrlx;

    private String cjr;

    private String zjlx;

    private String zjhm;

    private String sjhm;

    private BigDecimal xsZdj;
    
    private BigDecimal xsPj;

    private Long xsJsf;

    private Long xsTax;

    private Short xsHyxfs;

    private Short xsYwxfs;

    private BigDecimal xsHyxlr;

    private BigDecimal xsYwxlr;

    private BigDecimal xsYjf;

    private BigDecimal xsJe;
    
    /**
     * 销售佣金
     */
    private BigDecimal xsYj;

    private Long cgZdj;

    private BigDecimal cgPj;

    private Long cgJsf;

    private Long cgTax;

    private String cpzt;

    private String tkno;

    private String xcdh;

    private String xb;

    private String gj;

    private String csrq;

    private String zjyxq;

    private String zjqfg;

    private String wbcjrid;
    
    /**
     * 能否改签,0 未改签 1 已改签完成  -1 已退票 -2 已废票 -3 改签中 
     */
    @Transient
    private String nfgq;
    
    /**
     * 每个人机票集合
     * @return
     */
    @Transient
    private List<JpJp> jpList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh;
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
        this.cjrlx = cjrlx;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
    }

    public BigDecimal getXsZdj() {
		return xsZdj;
	}

	public void setXsZdj(BigDecimal xsZdj) {
		this.xsZdj = xsZdj;
	}

	public BigDecimal getXsPj() {
        return xsPj;
    }

    public void setXsPj(BigDecimal xsPj) {
        this.xsPj = xsPj;
    }

    public Long getXsJsf() {
        return xsJsf;
    }

    public void setXsJsf(Long xsJsf) {
        this.xsJsf = xsJsf;
    }

    public Long getXsTax() {
        return xsTax;
    }

    public void setXsTax(Long xsTax) {
        this.xsTax = xsTax;
    }

    public Short getXsHyxfs() {
        return xsHyxfs;
    }

    public void setXsHyxfs(Short xsHyxfs) {
        this.xsHyxfs = xsHyxfs;
    }

    public Short getXsYwxfs() {
        return xsYwxfs;
    }

    public void setXsYwxfs(Short xsYwxfs) {
        this.xsYwxfs = xsYwxfs;
    }

    public BigDecimal getXsHyxlr() {
        return xsHyxlr;
    }

    public void setXsHyxlr(BigDecimal xsHyxlr) {
        this.xsHyxlr = xsHyxlr;
    }

    public BigDecimal getXsYwxlr() {
        return xsYwxlr;
    }

    public void setXsYwxlr(BigDecimal xsYwxlr) {
        this.xsYwxlr = xsYwxlr;
    }

    public BigDecimal getXsYjf() {
        return xsYjf;
    }

    public void setXsYjf(BigDecimal xsYjf) {
        this.xsYjf = xsYjf;
    }

    public BigDecimal getXsJe() {
        return xsJe;
    }

    public void setXsJe(BigDecimal xsJe) {
        this.xsJe = xsJe;
    }

    public Long getCgZdj() {
        return cgZdj;
    }

    public void setCgZdj(Long cgZdj) {
        this.cgZdj = cgZdj;
    }

    public BigDecimal getCgPj() {
        return cgPj;
    }

    public void setCgPj(BigDecimal cgPj) {
        this.cgPj = cgPj;
    }

    public Long getCgJsf() {
        return cgJsf;
    }

    public void setCgJsf(Long cgJsf) {
        this.cgJsf = cgJsf;
    }

    public Long getCgTax() {
        return cgTax;
    }

    public void setCgTax(Long cgTax) {
        this.cgTax = cgTax;
    }

    public String getCpzt() {
        return cpzt;
    }

    public void setCpzt(String cpzt) {
        this.cpzt = cpzt;
    }

    public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno;
    }

    public String getXcdh() {
        return xcdh;
    }

    public void setXcdh(String xcdh) {
        this.xcdh = xcdh;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getZjyxq() {
        return zjyxq;
    }

    public void setZjyxq(String zjyxq) {
        this.zjyxq = zjyxq;
    }

    public String getZjqfg() {
        return zjqfg;
    }

    public void setZjqfg(String zjqfg) {
        this.zjqfg = zjqfg;
    }

    public String getWbcjrid() {
        return wbcjrid;
    }

    public void setWbcjrid(String wbcjrid) {
        this.wbcjrid = wbcjrid;
    }

	public List<JpJp> getJpList() {
		return jpList;
	}

	public void setJpList(List<JpJp> jpList) {
		this.jpList = jpList;
	}

	public String getNfgq() {
		return nfgq;
	}

	public void setNfgq(String nfgq) {
		this.nfgq = nfgq;
	}

	public BigDecimal getXsYj() {
		return xsYj;
	}

	public void setXsYj(BigDecimal xsYj) {
		this.xsYj = xsYj;
	}
    
}