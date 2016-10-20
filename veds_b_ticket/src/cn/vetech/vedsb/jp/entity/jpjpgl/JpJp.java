package cn.vetech.vedsb.jp.entity.jpjpgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_JP")
public class JpJp extends AbstractPageEntity implements Serializable{
	private static final long serialVersionUID = 2303442152009008658L;
	@Id
	@GeneratedValue(generator="no")
	/**ID号主键*/
	private String id; 
	/**票号13位票号*/
	private String tkno; 
	/**行程单号*/
	private String xcdh; 
	/**订单编号*/
	private String ddbh; 
	/**国内国际1国内，0国际*/
	private String gngj; 
	/**商户编号*/
	private String shbh; 
	/**方案ID*/
	private String faid; 
	/**网店平台*/
	private String wdpt; 
	/**网店ID号*/
	private String wdid; 
	/**网店产品类型普通、包机等*/
	private String wdZclx; 
	/**网店订单类型金牌服务等*/
	private String wdDdlx; 
	/**网店政策代码*/
	private String wdZcdm; 
	/**政策渠道Z自有，P平台，G官网等*/
	private String zcQd; 
	/**政策来源如果是P，存平台编号，G存航司二字码*/
	private String zcLy; 
	/**是否比价政策0否，1是*/
	private String zcSfbj; 
	/**政策投放ID*/
	private String zcTfid; 
	/**政策返点*/
	private BigDecimal zcFd; 
	/**政策留点*/
	private BigDecimal zcLd; 
	/**政策留钱*/
	private BigDecimal zcLq; 
	/**销售PNR_NO*/
	private String xsPnrNo; 
	/**销售航司大编码*/
	private String xsHkgsPnr; 
	/**航程类型*/
	private String hclx; 
	/**航程*/
	private String hc; 
	/**出发日期*/
	private Date cfrq; 
	/**出发时间*/
	private String cfsj; 
	/**CJRID号*/
	private String cjrid; 
	/**乘机人类型1成人2儿童3婴儿*/
	private String cjrlx; 
	/**乘机人*/
	private String cjr; 
	/**证件类型*/
	private String zjlx; 
	/**证件号码*/
	private String zjhm; 
	/**手机号码*/
	private String sjhm; 
	/**销售航班号*/
	private String xsHbh; 
	/**销售舱位*/
	private String xsCw; 
	/**销售账单价*/
	private Integer xsZdj; 
	/**销售价*/
	private BigDecimal xsPj; 
	/**销售机建*/
	private Integer xsJsf; 
	/**销售税费*/
	private Integer xsTax; 
	/**销售航意险份数*/
	private Integer xsHyxfs; 
	/**销售延误险份数*/
	private Integer xsYwxfs; 
	/**销售航意险利润*/
	private BigDecimal xsHyxlr; 
	/**销售延误险利润*/
	private BigDecimal xsYwxlr; 
	/**销售邮寄费*/
	private BigDecimal xsYjf; 
	/**销售金额*/
	private BigDecimal xsJe; 
	/**销售退改签*/
	private String xsTgq; 
	/**采购PNR_NO*/
	private String cgPnrNo; 
	/**采购航司大编码*/
	private String cgHkgsPnr; 
	/**采购航班号*/
	private String cgHbh; 
	/**采购舱位*/
	private String cgCw; 
	/**采购账单价*/
	private Integer cgZdj; 
	/**采购价*/
	private BigDecimal cgPj; 
	/**采购机建*/
	private Integer cgJsf; 
	/**采购税费*/
	private Integer cgTax; 
	/**采购退改签*/
	private String cgTgq; 
	/**采购来源*/
	private String cgly; 
	/**采购单位*/
	private String cgdw; 
	/**采购状态*/
	private String cgzt; 
	/**采购订单编号*/
	private String cgDdbh; 
	/**采购科目*/
	private String cgkm; 
	/**机票状态1正常2退票3废票*/
	private String jpzt; 
	/**出票时间*/
	private Date cpDatetime; 
	/**出票员*/
	private String cpYhbh; 
	/**出票部门*/
	private String cpBmbh; 
	/**连续客票*/
	private String lxkp; 
	/**打票机号*/
	private String printno; 
	/**工作号*/
	private String workno; 
	/**出票OFFICE号*/
	private String cpOfficeid; 
	/**采购代理费率*/
	private BigDecimal cgDlfl; 
	/**采购代理费*/
	private BigDecimal cgDlf; 
	/**航司二字码*/
	private String hkgs; 
	/**收款状态0未收款，1已收款*/
	private String skzt; 
	/**收款科目*/
	private String skkm; 
	/**外部订单编号*/
	private String wbdh; 
	/**EI项*/
	private String ei; 
	/**采购支付流水号*/
	private String cgZflsh;

	/**
     * 开始日期
     */
    @Transient
    private String ksrq;
    /**
     * 结束日期
     */
    @Transient
    private String jsrq;
    /**
     * 机票航段集合
     */
    @Transient
    private List<JpHd> hdList;
    
    /**
     * 出票OFFCIE所在城市全拼，航协号
     */
    @Transient
	private String cpzd;
    
    /**
     * 能否改签,0 未改签 1 已改签完成  -1 已退票 -2 已废票 -3 改签中 
     */
    @Transient
    private String nfgq;
    /**
	 * 能否改签-未改签
	 */
	public static final String NFGQ_WGQ = "0";
	/**
	 * 能否改签-已改签
	 */
	public static final String NFGQ_YGQ = "1";
	/**
	 * 能否改签-已退废
	 */
	public static final String NFGQ_YTF = "-1";
	/**
	 * 能否改签-改签中
	 */
	public static final String NFGQ_GQZ = "-2";
	
	/**
	 * 能否改签-未退废
	 */
	public static final String NFGQ_WTF = "2";
	
	/**
	 * 退票标志默认0 (3为分航段退)
	 */
	private String tpFlag;
	
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
	public String getXcdh() {
		return xcdh;
	}
	public void setXcdh(String xcdh) {
		this.xcdh = xcdh;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getGngj() {
		return gngj;
	}
	public void setGngj(String gngj) {
		this.gngj = gngj;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getFaid() {
		return faid;
	}
	public void setFaid(String faid) {
		this.faid = faid;
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
	public String getWdZclx() {
		return wdZclx;
	}
	public void setWdZclx(String wdZclx) {
		this.wdZclx = wdZclx;
	}
	public String getWdDdlx() {
		return wdDdlx;
	}
	public void setWdDdlx(String wdDdlx) {
		this.wdDdlx = wdDdlx;
	}
	public String getWdZcdm() {
		return wdZcdm;
	}
	public void setWdZcdm(String wdZcdm) {
		this.wdZcdm = wdZcdm;
	}
	public String getZcQd() {
		return zcQd;
	}
	public void setZcQd(String zcQd) {
		this.zcQd = zcQd;
	}
	public String getZcLy() {
		return zcLy;
	}
	public void setZcLy(String zcLy) {
		this.zcLy = zcLy;
	}
	public String getZcSfbj() {
		return zcSfbj;
	}
	public void setZcSfbj(String zcSfbj) {
		this.zcSfbj = zcSfbj;
	}
	public String getZcTfid() {
		return zcTfid;
	}
	public void setZcTfid(String zcTfid) {
		this.zcTfid = zcTfid;
	}
	public BigDecimal getZcFd() {
		return zcFd;
	}
	public void setZcFd(BigDecimal zcFd) {
		this.zcFd = zcFd;
	}
	public BigDecimal getZcLd() {
		return zcLd;
	}
	public void setZcLd(BigDecimal zcLd) {
		this.zcLd = zcLd;
	}
	public BigDecimal getZcLq() {
		return zcLq;
	}
	public void setZcLq(BigDecimal zcLq) {
		this.zcLq = zcLq;
	}
	public String getXsPnrNo() {
		return xsPnrNo;
	}
	public void setXsPnrNo(String xsPnrNo) {
		this.xsPnrNo = xsPnrNo;
	}
	public String getXsHkgsPnr() {
		return xsHkgsPnr;
	}
	public void setXsHkgsPnr(String xsHkgsPnr) {
		this.xsHkgsPnr = xsHkgsPnr;
	}
	public String getHclx() {
		return hclx;
	}
	public void setHclx(String hclx) {
		this.hclx = hclx;
	}
	public String getHc() {
		return hc;
	}
	public void setHc(String hc) {
		this.hc = hc;
	}
	public Date getCfrq() {
		return cfrq;
	}
	public void setCfrq(Date cfrq) {
		this.cfrq = cfrq;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCjrid() {
		return cjrid;
	}
	public void setCjrid(String cjrid) {
		this.cjrid = cjrid;
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
	public Integer getXsZdj() {
		return xsZdj;
	}
	public void setXsZdj(Integer xsZdj) {
		this.xsZdj = xsZdj;
	}
	public BigDecimal getXsPj() {
		return xsPj;
	}
	public void setXsPj(BigDecimal xsPj) {
		this.xsPj = xsPj;
	}
	public Integer getXsJsf() {
		return xsJsf;
	}
	public void setXsJsf(Integer xsJsf) {
		this.xsJsf = xsJsf;
	}
	public Integer getXsTax() {
		return xsTax;
	}
	public void setXsTax(Integer xsTax) {
		this.xsTax = xsTax;
	}
	public Integer getXsHyxfs() {
		return xsHyxfs;
	}
	public void setXsHyxfs(Integer xsHyxfs) {
		this.xsHyxfs = xsHyxfs;
	}
	public Integer getXsYwxfs() {
		return xsYwxfs;
	}
	public void setXsYwxfs(Integer xsYwxfs) {
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
	public String getXsTgq() {
		return xsTgq;
	}
	public void setXsTgq(String xsTgq) {
		this.xsTgq = xsTgq;
	}
	public String getCgPnrNo() {
		return cgPnrNo;
	}
	public void setCgPnrNo(String cgPnrNo) {
		this.cgPnrNo = cgPnrNo;
	}
	public String getCgHkgsPnr() {
		return cgHkgsPnr;
	}
	public void setCgHkgsPnr(String cgHkgsPnr) {
		this.cgHkgsPnr = cgHkgsPnr;
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
	public Integer getCgZdj() {
		return cgZdj;
	}
	public void setCgZdj(Integer cgZdj) {
		this.cgZdj = cgZdj;
	}
	public BigDecimal getCgPj() {
		return cgPj;
	}
	public void setCgPj(BigDecimal cgPj) {
		this.cgPj = cgPj;
	}
	public Integer getCgJsf() {
		return cgJsf;
	}
	public void setCgJsf(Integer cgJsf) {
		this.cgJsf = cgJsf;
	}
	public Integer getCgTax() {
		return cgTax;
	}
	public void setCgTax(Integer cgTax) {
		this.cgTax = cgTax;
	}
	public String getCgTgq() {
		return cgTgq;
	}
	public void setCgTgq(String cgTgq) {
		this.cgTgq = cgTgq;
	}
	public String getCgly() {
		return cgly;
	}
	public void setCgly(String cgly) {
		this.cgly = cgly;
	}
	public String getCgdw() {
		return cgdw;
	}
	public void setCgdw(String cgdw) {
		this.cgdw = cgdw;
	}
	public String getCgzt() {
		return cgzt;
	}
	public void setCgzt(String cgzt) {
		this.cgzt = cgzt;
	}
	public String getCgDdbh() {
		return cgDdbh;
	}
	public void setCgDdbh(String cgDdbh) {
		this.cgDdbh = cgDdbh;
	}
	public String getCgkm() {
		return cgkm;
	}
	public void setCgkm(String cgkm) {
		this.cgkm = cgkm;
	}
	public String getJpzt() {
		return jpzt;
	}
	public void setJpzt(String jpzt) {
		this.jpzt = jpzt;
	}
	public Date getCpDatetime() {
		return cpDatetime;
	}
	public void setCpDatetime(Date cpDatetime) {
		this.cpDatetime = cpDatetime;
	}
	public String getCpYhbh() {
		return cpYhbh;
	}
	public void setCpYhbh(String cpYhbh) {
		this.cpYhbh = cpYhbh;
	}
	public String getCpBmbh() {
		return cpBmbh;
	}
	public void setCpBmbh(String cpBmbh) {
		this.cpBmbh = cpBmbh;
	}
	public String getLxkp() {
		return lxkp;
	}
	public void setLxkp(String lxkp) {
		this.lxkp = lxkp;
	}
	public String getPrintno() {
		return printno;
	}
	public void setPrintno(String printno) {
		this.printno = printno;
	}
	public String getWorkno() {
		return workno;
	}
	public void setWorkno(String workno) {
		this.workno = workno;
	}
	public String getCpOfficeid() {
		return cpOfficeid;
	}
	public void setCpOfficeid(String cpOfficeid) {
		this.cpOfficeid = cpOfficeid;
	}
	public BigDecimal getCgDlfl() {
		return cgDlfl;
	}
	public void setCgDlfl(BigDecimal cgDlfl) {
		this.cgDlfl = cgDlfl;
	}
	public BigDecimal getCgDlf() {
		return cgDlf;
	}
	public void setCgDlf(BigDecimal cgDlf) {
		this.cgDlf = cgDlf;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
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
	public String getWbdh() {
		return wbdh;
	}
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}
	public String getEi() {
		return ei;
	}
	public void setEi(String ei) {
		this.ei = ei;
	}
	public String getCgZflsh() {
		return cgZflsh;
	}
	public void setCgZflsh(String cgZflsh) {
		this.cgZflsh = cgZflsh;
	}
	public String getKsrq() {
		return ksrq;
	}
	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}
	public String getJsrq() {
		return jsrq;
	}
	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}
	public List<JpHd> getHdList() {
		return hdList;
	}
	public void setHdList(List<JpHd> hdList) {
		this.hdList = hdList;
	}
	public String getNfgq() {
		return nfgq;
	}
	public void setNfgq(String nfgq) {
		this.nfgq = nfgq;
	}
	public String getCpzd() {
		return cpzd;
	}
	public void setCpzd(String cpzd) {
		this.cpzd = cpzd;
	}
	public String getTpFlag() {
		return tpFlag;
	}
	public void setTpFlag(String tpFlag) {
		this.tpFlag = tpFlag;
	}
	
	
	
}