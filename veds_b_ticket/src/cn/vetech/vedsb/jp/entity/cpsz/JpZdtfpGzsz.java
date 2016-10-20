package cn.vetech.vedsb.jp.entity.cpsz;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "JP_ZDTFP_GZSZ")
public class JpZdtfpGzsz extends AbstractPageEntity implements Serializable {
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="no")
	private String id;

	private String gzmc;

	private String tpfp;

	private String zt;

	private String tfpblbm;

	private String zdblyhbh;

	private String mm;

	private String tplx;

	private String cjrlx;

	private String pzlx;

	private String hkgs;

	private String cw;

	private String cfcity;

	private String ddcity;

	private String cfrqs;

	private String cfrqz;

	private Short tqtss;

	private Short tqtsz;

	private BigDecimal qfsjxz;

	private String yssjq;

	private String yssjz;

	private String shbh;

	private String czyhbh;

	private String czDatetime;

	private String syptbs;

	private String isdel;

	/**
	 * 是否检查客票状态
	 */
	private String isjckpzt;

	/**
	 * 是否航变
	 */
	private String ishb;

	/**
	 * 航变起飞前提前时间量提交退票
	 */
	private BigDecimal hbTqsj;

	/**
	 * 赌航变的最小退票费
	 */
	private BigDecimal hbZxtpf;

	/**
	 * 赌航变的最小利润率
	 */
	private BigDecimal hbZxlrl;

	/**
	 * 赌航变的最小利润值(金额)
	 */
	private BigDecimal hbZxlr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getGzmc() {
		return gzmc;
	}

	public void setGzmc(String gzmc) {
		this.gzmc = gzmc == null ? null : gzmc.trim();
	}

	public String getTpfp() {
		return tpfp;
	}

	public void setTpfp(String tpfp) {
		this.tpfp = tpfp == null ? null : tpfp.trim();
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt == null ? null : zt.trim();
	}

	public String getTfpblbm() {
		return tfpblbm;
	}

	public void setTfpblbm(String tfpblbm) {
		this.tfpblbm = tfpblbm == null ? null : tfpblbm.trim();
	}

	public String getZdblyhbh() {
		return zdblyhbh;
	}

	public void setZdblyhbh(String zdblyhbh) {
		this.zdblyhbh = zdblyhbh == null ? null : zdblyhbh.trim();
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm == null ? null : mm.trim();
	}

	public String getTplx() {
		return tplx;
	}

	public void setTplx(String tplx) {
		this.tplx = tplx == null ? null : tplx.trim();
	}

	public String getCjrlx() {
		return cjrlx;
	}

	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx == null ? null : cjrlx.trim();
	}

	public String getPzlx() {
		return pzlx;
	}

	public void setPzlx(String pzlx) {
		this.pzlx = pzlx == null ? null : pzlx.trim();
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs == null ? null : hkgs.trim();
	}

	public String getCw() {
		return cw;
	}

	public void setCw(String cw) {
		this.cw = cw == null ? null : cw.trim();
	}

	public String getCfcity() {
		return cfcity;
	}

	public void setCfcity(String cfcity) {
		this.cfcity = cfcity == null ? null : cfcity.trim();
	}

	public String getDdcity() {
		return ddcity;
	}

	public void setDdcity(String ddcity) {
		this.ddcity = ddcity == null ? null : ddcity.trim();
	}

	public String getCfrqs() {
		return cfrqs;
	}

	public void setCfrqs(String cfrqs) {
		this.cfrqs = cfrqs == null ? null : cfrqs.trim();
	}

	public String getCfrqz() {
		return cfrqz;
	}

	public void setCfrqz(String cfrqz) {
		this.cfrqz = cfrqz == null ? null : cfrqz.trim();
	}

	public Short getTqtss() {
		return tqtss;
	}

	public void setTqtss(Short tqtss) {
		this.tqtss = tqtss;
	}

	public Short getTqtsz() {
		return tqtsz;
	}

	public void setTqtsz(Short tqtsz) {
		this.tqtsz = tqtsz;
	}

	public BigDecimal getQfsjxz() {
		return qfsjxz;
	}

	public void setQfsjxz(BigDecimal qfsjxz) {
		this.qfsjxz = qfsjxz;
	}

	public String getYssjq() {
		return yssjq;
	}

	public void setYssjq(String yssjq) {
		this.yssjq = yssjq == null ? null : yssjq.trim();
	}

	public String getYssjz() {
		return yssjz;
	}

	public void setYssjz(String yssjz) {
		this.yssjz = yssjz == null ? null : yssjz.trim();
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh == null ? null : shbh.trim();
	}

	public String getCzyhbh() {
		return czyhbh;
	}

	public void setCzyhbh(String czyhbh) {
		this.czyhbh = czyhbh == null ? null : czyhbh.trim();
	}

	public String getCzDatetime() {
		return czDatetime;
	}

	public void setCzDatetime(String czDatetime) {
		this.czDatetime = czDatetime == null ? null : czDatetime.trim();
	}

	public String getSyptbs() {
		return syptbs;
	}

	public void setSyptbs(String syptbs) {
		this.syptbs = syptbs;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getIsjckpzt() {
		return isjckpzt;
	}

	public void setIsjckpzt(String isjckpzt) {
		this.isjckpzt = isjckpzt == null ? null : isjckpzt.trim();
	}

	public String getIshb() {
		return ishb;
	}

	public void setIshb(String ishb) {
		this.ishb = ishb == null ? null : ishb.trim();
	}

	public BigDecimal getHbTqsj() {
		return hbTqsj;
	}

	public void setHbTqsj(BigDecimal hbTqsj) {
		this.hbTqsj = hbTqsj;
	}

	public BigDecimal getHbZxtpf() {
		return hbZxtpf;
	}

	public void setHbZxtpf(BigDecimal hbZxtpf) {
		this.hbZxtpf = hbZxtpf;
	}

	public BigDecimal getHbZxlrl() {
		return hbZxlrl;
	}

	public void setHbZxlrl(BigDecimal hbZxlrl) {
		this.hbZxlrl = hbZxlrl;
	}

	public BigDecimal getHbZxlr() {
		return hbZxlr;
	}

	public void setHbZxlr(BigDecimal hbZxlr) {
		this.hbZxlr = hbZxlr;
	}
}