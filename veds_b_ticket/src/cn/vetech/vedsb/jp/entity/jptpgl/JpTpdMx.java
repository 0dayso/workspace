package cn.vetech.vedsb.jp.entity.jptpgl;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;


@XmlRootElement(name="TFDMX")
@Table(name="JP_TPD_MX")
public class JpTpdMx extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = -8557192226307869947L;

	@Id
	@GeneratedValue(generator="no")
    private String id;

    private String tpdh;

    private String tkno;

    private String shbh;

    private String xcdh;

    private Short sxh;

    private String cjrlx;

    private String cjr;

    private String zjlx;

    private String zjhm;

    private String sjhm;
    
    private String xsCw;

    private Long xsZdj;

    private BigDecimal xsPj;

    private Long xsJsf;

    private Long xsTax;

    private String xsTgq;

    private BigDecimal xsTpfl;

    private BigDecimal xsTpsxf;

    private BigDecimal xsTkje;
    
    private String cgCw;

    private Long cgZdj;

    private BigDecimal cgPj;

    private Long cgJsf;

    private Long cgTax;

    private String cgTgq;

    private BigDecimal cgTpfl;

    private BigDecimal cgTpf;

    private BigDecimal cgTkje;
    
    
    private BigDecimal cgStje;
    
    

    private String xb;

    private String gj;

    private String csrq;

    private String zjyxq;

    private String zjqfg;

    private String wbcjrid;

    private String wbsxh;
    
    private String ytpf;

    private String hclx;
    
    private String hx_trfd_ylnr;
    
    private String printno;
    

    /** 工作号*/
    private String workno;
    
    /** OFFICE号*/
    private String cpOfficeid;
    
    private String hkgs;
    
    /**销售佣金*/
    private BigDecimal xsYj;
    
    public BigDecimal getXsYj() {
		return xsYj;
	}

	public void setXsYj(BigDecimal xsYj) {
		this.xsYj = xsYj;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    @XmlElement(name="TPDH")
    public String getTpdh() {
        return tpdh;
    }

    public void setTpdh(String tpdh) {
        this.tpdh = tpdh == null ? null : tpdh.trim();
    }

    @XmlElement(name="TKNO")
    public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno == null ? null : tkno.trim();
    }

    @XmlElement(name="SHBH")
    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    @XmlElement(name="XCDH")
    public String getXcdh() {
        return xcdh;
    }

    public void setXcdh(String xcdh) {
        this.xcdh = xcdh == null ? null : xcdh.trim();
    }

    @XmlElement(name="SXH")
    public Short getSxh() {
        return sxh;
    }

    public void setSxh(Short sxh) {
        this.sxh = sxh;
    }

    @XmlElement(name="CJRLX")
    public String getCjrlx() {
        return cjrlx;
    }

    public void setCjrlx(String cjrlx) {
        this.cjrlx = cjrlx == null ? null : cjrlx.trim();
    }

    @XmlElement(name="CJR")
    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    @XmlElement(name="ZJLX")
    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx == null ? null : zjlx.trim();
    }

    @XmlElement(name="ZJHM")
    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm == null ? null : zjhm.trim();
    }

    @XmlElement(name="SJHM")
    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm == null ? null : sjhm.trim();
    }
    
    @XmlElement(name="XS_CW")
    public String getXsCw() {
        return xsCw;
    }

    public void setXsCw(String xsCw) {
        this.xsCw = xsCw == null ? null : xsCw.trim();
    }
    

    @XmlElement(name="XS_ZDJ")
    public Long getXsZdj() {
        return xsZdj;
    }

    public void setXsZdj(Long xsZdj) {
        this.xsZdj = xsZdj;
    }

    @XmlElement(name="XS_PJ")
    public BigDecimal getXsPj() {
        return xsPj;
    }

    public void setXsPj(BigDecimal xsPj) {
        this.xsPj = xsPj;
    }

    @XmlElement(name="XS_JSF")
    public Long getXsJsf() {
        return xsJsf;
    }

    public void setXsJsf(Long xsJsf) {
        this.xsJsf = xsJsf;
    }

    @XmlElement(name="XS_TAX")
    public Long getXsTax() {
        return xsTax;
    }

    public void setXsTax(Long xsTax) {
        this.xsTax = xsTax;
    }

    @XmlElement(name="XS_TGQ")
    public String getXsTgq() {
        return xsTgq;
    }

    public void setXsTgq(String xsTgq) {
        this.xsTgq = xsTgq == null ? null : xsTgq.trim();
    }

    @XmlElement(name="XS_TPFL")
    public BigDecimal getXsTpfl() {
        return xsTpfl;
    }

    public void setXsTpfl(BigDecimal xsTpfl) {
        this.xsTpfl = xsTpfl;
    }

    @XmlElement(name="XS_TPSXF")
    public BigDecimal getXsTpsxf() {
        return xsTpsxf;
    }

    public void setXsTpsxf(BigDecimal xsTpsxf) {
        this.xsTpsxf = xsTpsxf;
    }

    @XmlElement(name="XS_TKJE")
    public BigDecimal getXsTkje() {
        return xsTkje;
    }

    public void setXsTkje(BigDecimal xsTkje) {
        this.xsTkje = xsTkje;
    }

    @XmlElement(name="CG_CW")
    public String getCgCw() {
        return cgCw;
    }

    public void setCgCw(String cgCw) {
        this.cgCw = cgCw == null ? null : cgCw.trim();
    }
    
    @XmlElement(name="CG_ZDJ")
    public Long getCgZdj() {
        return cgZdj;
    }

    public void setCgZdj(Long cgZdj) {
        this.cgZdj = cgZdj;
    }

    @XmlElement(name="CG_PJ")
    public BigDecimal getCgPj() {
        return cgPj;
    }

    public void setCgPj(BigDecimal cgPj) {
        this.cgPj = cgPj;
    }

    @XmlElement(name="CG_JSF")
    public Long getCgJsf() {
        return cgJsf;
    }

    public void setCgJsf(Long cgJsf) {
        this.cgJsf = cgJsf;
    }

    @XmlElement(name="CG_TAX")
    public Long getCgTax() {
        return cgTax;
    }

    public void setCgTax(Long cgTax) {
        this.cgTax = cgTax;
    }

    @XmlElement(name="CG_TGQ")
    public String getCgTgq() {
        return cgTgq;
    }

    public void setCgTgq(String cgTgq) {
        this.cgTgq = cgTgq == null ? null : cgTgq.trim();
    }

    @XmlElement(name="CG_TPFL")
    public BigDecimal getCgTpfl() {
        return cgTpfl;
    }

    public void setCgTpfl(BigDecimal cgTpfl) {
        this.cgTpfl = cgTpfl;
    }

    @XmlElement(name="CG_TPF")
    public BigDecimal getCgTpf() {
        return cgTpf;
    }

    public void setCgTpf(BigDecimal cgTpf) {
        this.cgTpf = cgTpf;
    }

    @XmlElement(name="CG_TKJE")
    public BigDecimal getCgTkje() {
        return cgTkje;
    }

    public void setCgTkje(BigDecimal cgTkje) {
        this.cgTkje = cgTkje;
    }
    
    public BigDecimal getCgStje() {
		return cgStje;
	}

	public void setCgStje(BigDecimal cgStje) {
		this.cgStje = cgStje;
	}

	@XmlElement(name="XB")
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb == null ? null : xb.trim();
    }

    @XmlElement(name="GJ")
    public String getGj() {
        return gj;
    }

    public void setGj(String gj) {
        this.gj = gj == null ? null : gj.trim();
    }

    @XmlElement(name="CSRQ")
    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq == null ? null : csrq.trim();
    }

    @XmlElement(name="ZJYXQ")
    public String getZjyxq() {
        return zjyxq;
    }

    public void setZjyxq(String zjyxq) {
        this.zjyxq = zjyxq == null ? null : zjyxq.trim();
    }

    @XmlElement(name="ZJQFG")
    public String getZjqfg() {
        return zjqfg;
    }

    public void setZjqfg(String zjqfg) {
        this.zjqfg = zjqfg == null ? null : zjqfg.trim();
    }

    @XmlElement(name="WBCJRID")
    public String getWbcjrid() {
        return wbcjrid;
    }

    public void setWbcjrid(String wbcjrid) {
        this.wbcjrid = wbcjrid == null ? null : wbcjrid.trim();
    }

    @XmlElement(name="WBSXH")
	public String getWbsxh() {
		return wbsxh;
	}

	public void setWbsxh(String wbsxh) {
		this.wbsxh = wbsxh;
	}

	@XmlElement(name="YTPF")
	public String getYtpf() {
		return ytpf;
	}

	public void setYtpf(String ytpf) {
		this.ytpf = ytpf;
	}

	@XmlElement(name="HCLX")
	public String getHclx() {
		return hclx;
	}

	public void setHclx(String hclx) {
		this.hclx = hclx;
	}

	@XmlElement(name="HX_TRFD_YLNR")
	public String getHx_trfd_ylnr() {
		return hx_trfd_ylnr;
	}

	public void setHx_trfd_ylnr(String hx_trfd_ylnr) {
		this.hx_trfd_ylnr = hx_trfd_ylnr;
	}

	@XmlElement(name="PRINTNO")
	public String getPrintno() {
		return printno;
	}

	public void setPrintno(String printno) {
		this.printno = printno;
	}

	@XmlElement(name="WORKNO")
	public String getWorkno() {
		return workno;
	}

	public void setWorkno(String workno) {
		this.workno = workno;
	}

	@XmlElement(name="CP_OFFICEID")
	public String getCpOfficeid() {
		return cpOfficeid;
	}

	public void setCpOfficeid(String cpOfficeid) {
		this.cpOfficeid = cpOfficeid;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
}