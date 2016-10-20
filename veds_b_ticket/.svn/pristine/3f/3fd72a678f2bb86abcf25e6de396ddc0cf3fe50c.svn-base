package cn.vetech.vedsb.jp.entity.jpbcdgl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="jp_bcd")
public class JPBcd extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	
	private String shbh;
	
	private String wdpt;
	
	private String wdid;
	
	private String bclx;
	
	private String bcsm;
	
	private String ywlx;
	
	private String ywdh;
	
	private String wbdh;
	
	private String pnrNo;
	
	private String tkno;
	
	private Double bcje;
	
	private String skzt;
	
	private String skkm;
	
	private String bczt;
	
	private String bcShr;
	
	private Date bcShsj;
	
	private String bcBlr;
	
	private Date bcBlsj;
	
	private String cjyh;
	
	private Date cjsj;
	
	private String xgyh;
	
	private Date xgsj;
	
	private String shyh;
	
	private Date shsj;
	
	private String wcyh;
	
	private Date wcsj;

	private String xsdzzt;//销售对账状态：1已对账，且对比正确，0或空为未对比
	
	private String hc;//航程
	
	private String hkgs;//航司二字码
	
	private String faid;//方案id
	
	private String ddbh;//订单编号
	
	private String wdZclx;//政策类型
	
	private Date cpDatetime;//出票时间
	@Transient
	private String bclxname;

	public String getXsdzzt() {
		return xsdzzt;
	}

	public void setXsdzzt(String xsdzzt) {
		this.xsdzzt = xsdzzt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getWdpt() {
		return wdpt;
	}

	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}

	public String getWdid() {
		return wdid;
	}

	public void setWdid(String wdid) {
		this.wdid = wdid;
	}

	public String getBclx() {
		return bclx;
	}

	public void setBclx(String bclx) {
		this.bclx = bclx;
	}

	public String getBcsm() {
		return bcsm;
	}

	public void setBcsm(String bcsm) {
		this.bcsm = bcsm;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getYwdh() {
		return ywdh;
	}

	public void setYwdh(String ywdh) {
		this.ywdh = ywdh;
	}

	public String getWbdh() {
		return wbdh;
	}
	
	@Transient
	public String getWbdhString(){
		if(StringUtils.isNotBlank(this.wbdh)){
			String res = "";
			if(this.wbdh.length()>12){
				res = this.wbdh.substring(0, 12)+"...";
			}else{
				res = this.wbdh;
			}
			return res;
		}
		return null;
	}
	
	@Transient
	public String getbcsmString(){
		if(StringUtils.isNotBlank(this.bcsm)){
			String res="";
			if(this.bcsm.length()>10){
				res = this.bcsm.substring(0, 10)+"...";
			}else{
				res = this.bcsm;
			}
			return res;
		}
		return null;
	}
	
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno;
	}

	public String getSkzt() {
		return skzt;
	}

	public void setSkzt(String skzt) {
		this.skzt = skzt;
	}

	public String getSkkm() {
		return skkm;
	}

	public void setSkkm(String skkm) {
		this.skkm = skkm;
	}

	public String getBczt() {
		return bczt;
	}

	public void setBczt(String bczt) {
		this.bczt = bczt;
	}

	public String getBcShr() {
		return bcShr;
	}

	public void setBcShr(String bcShr) {
		this.bcShr = bcShr;
	}

	public String getBcBlr() {
		return bcBlr;
	}

	public void setBcBlr(String bcBlr) {
		this.bcBlr = bcBlr;
	}

	public String getCjyh() {
		return cjyh;
	}

	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}

	public String getXgyh() {
		return xgyh;
	}

	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}

	public String getShyh() {
		return shyh;
	}

	public void setShyh(String shyh) {
		this.shyh = shyh;
	}

	public String getWcyh() {
		return wcyh;
	}

	public void setWcyh(String wcyh) {
		this.wcyh = wcyh;
	}

	public Date getBcShsj() {
		return bcShsj;
	}

	public void setBcShsj(Date bcShsj) {
		this.bcShsj = bcShsj;
	}

	public Date getBcBlsj() {
		return bcBlsj;
	}

	public void setBcBlsj(Date bcBlsj) {
		this.bcBlsj = bcBlsj;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public Date getShsj() {
		return shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public Date getWcsj() {
		return wcsj;
	}

	public void setWcsj(Date wcsj) {
		this.wcsj = wcsj;
	}

	public Double getBcje() {
		return bcje;
	}

	public void setBcje(Double bcje) {
		this.bcje = bcje;
	}

	public String getBclxname() {
		return bclxname;
	}

	public void setBclxname(String bclxname) {
		this.bclxname = bclxname;
	}

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}

	public String getFaid() {
		return faid;
	}

	public void setFaid(String faid) {
		this.faid = faid;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public Date getCpDatetime() {
		return cpDatetime;
	}

	public void setCpDatetime(Date cpDatetime) {
		this.cpDatetime = cpDatetime;
	}

	public String getWdZclx() {
		return wdZclx;
	}

	public void setWdZclx(String wdZclx) {
		this.wdZclx = wdZclx;
	}
}
