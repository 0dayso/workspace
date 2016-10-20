package cn.vetech.vedsb.jp.entity.jpjpgl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_HD")
public class JpHd extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = -7997206778743538697L;

	/**
	 * 	ID号主键
	 */
	@Id
	@GeneratedValue(generator="no")
	private String id;

    private String tkno;

    private String shbh;

    private Short sxh;

    private String cfcity;

    private String ddcity;

    private String cfsj;

    private String ddsj;

    private String cfhzl;

    private String ddhzl;

    private String fjjx;

    private String xsHbh;

    private String xsCw;

    private String xsTgq;

    private String cgHbh;

    private String cgCw;

    private String cgTgq;
    
    private String ddhdid;
    
    /**
     * 退票明细id
     */
    private String tpmxid;
    
    @Transient
    private String kpzt;//客票状态
    
    /**
     * 能否改签,0 未改签 1 已改签完成  -1 已退票 -2 已废票 -3 改签中 
     */
    @Transient
    private String nfgq;
    @Transient
    private String cfcs;//出发城市 三字码对应的城市名
    @Transient
    private String ddcs;//到达城市 三字码对应的城市名
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTkno() {
        return tkno;
    }

    public void setTkno(String tkno) {
        this.tkno = tkno;
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

    public String getCfcity() {
        return cfcity;
    }

    public void setCfcity(String cfcity) {
        this.cfcity = cfcity;
    }

    public String getDdcity() {
        return ddcity;
    }

    public void setDdcity(String ddcity) {
        this.ddcity = ddcity;
    }

    public String getCfsj() {
        return cfsj;
    }

    public void setCfsj(String cfsj) {
        this.cfsj = cfsj;
    }

    public String getDdsj() {
        return ddsj;
    }

    public void setDdsj(String ddsj) {
        this.ddsj = ddsj;
    }

    public String getCfhzl() {
        return cfhzl;
    }

    public void setCfhzl(String cfhzl) {
        this.cfhzl = cfhzl;
    }

    public String getDdhzl() {
        return ddhzl;
    }

    public void setDdhzl(String ddhzl) {
        this.ddhzl = ddhzl;
    }

    public String getFjjx() {
        return fjjx;
    }

    public void setFjjx(String fjjx) {
        this.fjjx = fjjx;
    }

    public String getXsHbh() {
        return xsHbh;
    }

    public void setXsHbh(String xsHbh) {
        this.xsHbh = xsHbh;
    }

    public String getXsCw() {
        return xsCw;
    }

    public void setXsCw(String xsCw) {
        this.xsCw = xsCw;
    }

    public String getXsTgq() {
        return xsTgq;
    }

    public void setXsTgq(String xsTgq) {
        this.xsTgq = xsTgq;
    }

    public String getCgHbh() {
        return cgHbh;
    }

    public void setCgHbh(String cgHbh) {
        this.cgHbh = cgHbh;
    }

    public String getCgCw() {
        return cgCw;
    }

    public void setCgCw(String cgCw) {
        this.cgCw = cgCw;
    }

    public String getCgTgq() {
        return cgTgq;
    }

    public void setCgTgq(String cgTgq) {
        this.cgTgq = cgTgq;
    }

	public String getTpmxid() {
		return tpmxid;
	}

	public void setTpmxid(String tpmxid) {
		this.tpmxid = tpmxid;
	}

	public String getNfgq() {
		return nfgq;
	}

	public void setNfgq(String nfgq) {
		this.nfgq = nfgq;
	}

	public String getDdhdid() {
		return ddhdid;
	}

	public void setDdhdid(String ddhdid) {
		this.ddhdid = ddhdid;
	}

	public String getKpzt() {
		return kpzt;
	}

	public void setKpzt(String kpzt) {
		this.kpzt = kpzt;
	}

	public String getCfcs() {
		return cfcs;
	}

	public void setCfcs(String cfcs) {
		this.cfcs = cfcs;
	}

	public String getDdcs() {
		return ddcs;
	}

	public void setDdcs(String ddcs) {
		this.ddcs = ddcs;
	}
    
	
}