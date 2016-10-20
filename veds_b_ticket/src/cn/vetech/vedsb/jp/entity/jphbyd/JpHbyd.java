package cn.vetech.vedsb.jp.entity.jphbyd;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_hbyd")
public class JpHbyd extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = -4261839476833048012L;
	@Id
	@GeneratedValue(generator="no")
    private String id;
	//出发日期  原航班出发日期
    private String cfrq;
    //航班号
    private String hbh;
    //创建时间
    private String cjtime;
    //创建人
    private String yhbh;
    //创建部门
    private String bmbh;
    //创建公司
    private String shbh;
    //航班异动原因
    private String ydyy;
    //预计起飞时间
    private String yjCfsj;
    //预计到达时间
    private String yjDdsj;
    //备用1 保护到的航班
    private String by1;
    //备用2 原航班到达时间
    private String by2;
    //备用3
    private String by3;
    //出发时间
    private String cfsj;
    //航程
    private String hc;
    //航空公司
    private String hkgs;
    //操作来源
    private String czly;
    //航变类型：1提前,2延误，3取消
    private String zt;
    //延误时长
    private BigDecimal ywsc;

    //航变类型：1提前,2延误，3取消
    private String hblx;
    
    //审核人
    private String shYhbh;
    
    //审核部门
    private String shBmbh;
    
	//界面查询条件中的游离字段
    @Transient
    private String rqtj,kssj,jssj,wdpt,alldd,ycldd,cfcity,ddcity,ddzt,sfyfdx,clzt,cfcitymc,ddcitymc;
    
    public String getCfcitymc() {
		return cfcitymc;
	}

	public void setCfcitymc(String cfcitymc) {
		this.cfcitymc = cfcitymc;
	}

	public String getDdcitymc() {
		return ddcitymc;
	}

	public void setDdcitymc(String ddcitymc) {
		this.ddcitymc = ddcitymc;
	}

	public String getClzt() {
		return clzt;
	}

	public void setClzt(String clzt) {
		this.clzt = clzt;
	}

	public String getDdzt() {
		return ddzt;
	}

	public void setDdzt(String ddzt) {
		this.ddzt = ddzt;
	}

	public String getSfyfdx() {
		return sfyfdx;
	}

	public void setSfyfdx(String sfyfdx) {
		this.sfyfdx = sfyfdx;
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

	public String getRqtj() {
		return rqtj;
	}

	public void setRqtj(String rqtj) {
		this.rqtj = rqtj;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getWdpt() {
		return wdpt;
	}

	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}

	public String getAlldd() {
		return alldd == null ? "0" : alldd;
	}

	public void setAlldd(String alldd) {
		this.alldd = alldd;
	}

	public String getYcldd() {
		return ycldd == null ? "0" : ycldd;
	}

	public void setYcldd(String ycldd) {
		this.ycldd = ycldd;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCfrq() {
        return cfrq;
    }

    public void setCfrq(String cfrq) {
        this.cfrq = cfrq == null ? null : cfrq.trim();
    }

    public String getHbh() {
        return hbh;
    }

    public void setHbh(String hbh) {
        this.hbh = hbh == null ? null : hbh.trim();
    }

	public String getCjtime() {
		return cjtime;
	}

	public void setCjtime(String cjtime) {
		this.cjtime = cjtime;
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

	public String getHblx() {
		return hblx;
	}

	public void setHblx(String hblx) {
		this.hblx = hblx == null ? null : hblx.trim();
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

	public String getYdyy() {
        return ydyy;
    }

    public void setYdyy(String ydyy) {
        this.ydyy = ydyy == null ? null : ydyy.trim();
    }

    public String getYjCfsj() {
        return yjCfsj;
    }

    public void setYjCfsj(String yjCfsj) {
        this.yjCfsj = yjCfsj == null ? null : yjCfsj.trim();
    }

    public String getYjDdsj() {
        return yjDdsj;
    }

    public void setYjDdsj(String yjDdsj) {
        this.yjDdsj = yjDdsj == null ? null : yjDdsj.trim();
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

    public String getCfsj() {
        return cfsj;
    }

    public void setCfsj(String cfsj) {
        this.cfsj = cfsj == null ? null : cfsj.trim();
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc == null ? null : hc.trim();
    }

    public String getHkgs() {
        return hkgs;
    }

    public void setHkgs(String hkgs) {
        this.hkgs = hkgs == null ? null : hkgs.trim();
    }

    public String getCzly() {
        return czly;
    }

    public void setCzly(String czly) {
        this.czly = czly == null ? null : czly.trim();
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }

    public BigDecimal getYwsc() {
        return ywsc;
    }

    public void setYwsc(BigDecimal ywsc) {
        this.ywsc = ywsc;
    }
	
}
