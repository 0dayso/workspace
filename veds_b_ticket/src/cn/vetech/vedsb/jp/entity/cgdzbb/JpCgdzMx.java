package cn.vetech.vedsb.jp.entity.cgdzbb;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "JP_CGDZ_MX")
public class JpCgdzMx extends AbstractPageEntity {
	private static final long serialVersionUID = -8059561478631967547L;
	@Id
	private String id;
	private String zbid;// 主表ID jp_cgdz表id
	private String shbh;
	private String jglx;// 结果类型 0待比对1金额正确2金额不正确3当日漏单4当日多单  9异常单子
	private String ddlx;// 订单类型0异常单 1正常单2退票单3改签单4补差单
	private String drly;// 导入来源
	private Date drsj;// 导入时间
	private String drr;// 导入人
	private Date ywrq;// 业务日期
	private String pnrno;// 编码
	private String bigpnrno;// 大编码
	private String tkno;// 票号
	private String bdfs;// 比对方式，1按票，2按单
	private String cgDdbh;// 采购订单号
	
	private Double cgPmj;// 采购票面价
	private Double cgJsf;// 采购机建
	private Double cgTax;// 采购税费
	private Double cgXj;// 采购小计
	
	private Double cgCgj;// 采购价
	private Double cgDlf;// 采购代理费
	private Double cgSxf;// 采购手续费
	
	private Double cgTpf;//退票费
	private Double cgTpfl;
	
	private Double cgSfje;// 采购实付金额
	
	
	private Double cgZflsh;// 采购支付流水号
	private String cgZfspmc;// 采购支付商品名称
	private String cgZfsj;// 采购支付时间
	private String cgSrje;// 采购收入金额
	private String cgZcje;// 采购支出金额
	private String cgYwlx;// 采购业务类型
	private String cgBz;// 采购备注
	private String cgly;// 采购来源 d
	private String ddbh;// 订单编号 d
	
	private Date cprq;// 出票日期 d
	private String wbdh;// 外部订单号 d
	private Double cgje;// 采购金额d
	private String jesfzq;// 金额是否正确 1正确2不正确d
	private Date cpDzrq;// 出票对账日期 d
	private String cpDzr;// 出票对账人 存对账人工号加姓名 d
	private String bdSfybd;// 是否已补单 0未补1已补只有今日漏单的才可能出现已补的情况。d
	private Date bdBdrq;// 补单对账日期 d
	private String bdBdr;// 补单对账人 存对账人工号加姓名 d
	private String by1;
	private String by2;
	private String by3;
	private String by4;
	private String by5;
	private String by6;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZbid() {
		return zbid;
	}

	public void setZbid(String zbid) {
		this.zbid = zbid;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getJglx() {
		return jglx;
	}

	public void setJglx(String jglx) {
		this.jglx = jglx;
	}

	public String getDdlx() {
		return ddlx;
	}

	public void setDdlx(String ddlx) {
		this.ddlx = ddlx;
	}

	public String getDrly() {
		return drly;
	}

	public void setDrly(String drly) {
		this.drly = drly;
	}


	public Date getDrsj() {
		return drsj;
	}

	public void setDrsj(Date drsj) {
		this.drsj = drsj;
	}

	public String getDrr() {
		return drr;
	}

	public void setDrr(String drr) {
		this.drr = drr;
	}

	public Date getYwrq() {
		return ywrq;
	}

	public void setYwrq(Date ywrq) {
		this.ywrq = ywrq;
	}

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno;
	}

	public String getBigpnrno() {
		return bigpnrno;
	}

	public void setBigpnrno(String bigpnrno) {
		this.bigpnrno = bigpnrno;
	}

	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno;
	}

	public String getBdfs() {
		return bdfs;
	}

	public void setBdfs(String bdfs) {
		this.bdfs = bdfs;
	}

	public String getCgDdbh() {
		return cgDdbh;
	}

	public void setCgDdbh(String cgDdbh) {
		this.cgDdbh = cgDdbh;
	}

	public Double getCgPmj() {
		return cgPmj;
	}

	public void setCgPmj(Double cgPmj) {
		this.cgPmj = cgPmj;
	}

	public Double getCgJsf() {
		return cgJsf;
	}

	public void setCgJsf(Double cgJsf) {
		this.cgJsf = cgJsf;
	}

	public Double getCgTax() {
		return cgTax;
	}

	public void setCgTax(Double cgTax) {
		this.cgTax = cgTax;
	}

	public Double getCgXj() {
		return cgXj;
	}

	public void setCgXj(Double cgXj) {
		this.cgXj = cgXj;
	}

	public Double getCgCgj() {
		return cgCgj;
	}

	public void setCgCgj(Double cgCgj) {
		this.cgCgj = cgCgj;
	}

	public Double getCgDlf() {
		return cgDlf;
	}

	public void setCgDlf(Double cgDlf) {
		this.cgDlf = cgDlf;
	}

	public Double getCgSxf() {
		return cgSxf;
	}

	public void setCgSxf(Double cgSxf) {
		this.cgSxf = cgSxf;
	}

	public Double getCgSfje() {
		return cgSfje;
	}

	public void setCgSfje(Double cgSfje) {
		this.cgSfje = cgSfje;
	}

	public Double getCgZflsh() {
		return cgZflsh;
	}

	public void setCgZflsh(Double cgZflsh) {
		this.cgZflsh = cgZflsh;
	}

	public String getCgZfspmc() {
		return cgZfspmc;
	}

	public void setCgZfspmc(String cgZfspmc) {
		this.cgZfspmc = cgZfspmc;
	}

	public String getCgZfsj() {
		return cgZfsj;
	}

	public void setCgZfsj(String cgZfsj) {
		this.cgZfsj = cgZfsj;
	}

	public String getCgSrje() {
		return cgSrje;
	}

	public void setCgSrje(String cgSrje) {
		this.cgSrje = cgSrje;
	}

	public String getCgZcje() {
		return cgZcje;
	}

	public void setCgZcje(String cgZcje) {
		this.cgZcje = cgZcje;
	}

	public String getCgYwlx() {
		return cgYwlx;
	}

	public void setCgYwlx(String cgYwlx) {
		this.cgYwlx = cgYwlx;
	}

	public String getCgBz() {
		return cgBz;
	}

	public void setCgBz(String cgBz) {
		this.cgBz = cgBz;
	}

	public String getCgly() {
		return cgly;
	}

	public void setCgly(String cgly) {
		this.cgly = cgly;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

 
	public Date getCprq() {
		return cprq;
	}

	public void setCprq(Date cprq) {
		this.cprq = cprq;
	}

	public String getWbdh() {
		return wbdh;
	}

	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}

	public Double getCgje() {
		return cgje;
	}

	public void setCgje(Double cgje) {
		this.cgje = cgje;
	}

	public String getJesfzq() {
		return jesfzq;
	}

	public void setJesfzq(String jesfzq) {
		this.jesfzq = jesfzq;
	}

	public Date getCpDzrq() {
		return cpDzrq;
	}

	public void setCpDzrq(Date cpDzrq) {
		this.cpDzrq = cpDzrq;
	}

	public String getCpDzr() {
		return cpDzr;
	}

	public void setCpDzr(String cpDzr) {
		this.cpDzr = cpDzr;
	}

	public String getBdSfybd() {
		return bdSfybd;
	}

	public void setBdSfybd(String bdSfybd) {
		this.bdSfybd = bdSfybd;
	}

	public Date getBdBdrq() {
		return bdBdrq;
	}

	public void setBdBdrq(Date bdBdrq) {
		this.bdBdrq = bdBdrq;
	}

	public String getBdBdr() {
		return bdBdr;
	}

	public void setBdBdr(String bdBdr) {
		this.bdBdr = bdBdr;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	public String getBy4() {
		return by4;
	}

	public void setBy4(String by4) {
		this.by4 = by4;
	}

	public String getBy5() {
		return by5;
	}

	public void setBy5(String by5) {
		this.by5 = by5;
	}

	public String getBy6() {
		return by6;
	}

	public void setBy6(String by6) {
		this.by6 = by6;
	}

	public Double getCgTpf() {
		return cgTpf;
	}

	public void setCgTpf(Double cgTpf) {
		this.cgTpf = cgTpf;
	}

	public Double getCgTpfl() {
		return cgTpfl;
	}

	public void setCgTpfl(Double cgTpfl) {
		this.cgTpfl = cgTpfl;
	}
}
