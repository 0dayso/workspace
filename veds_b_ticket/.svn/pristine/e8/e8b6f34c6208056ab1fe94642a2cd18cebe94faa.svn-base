package cn.vetech.vedsb.jp.entity.cpsz;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

import cn.vetech.vedsb.jp.entity.jptpgl.JaxbDateSerializer;

@Table(name = "JP_ZDTP_JK")
public class JpZdtpJk extends AbstractPageEntity {
	private static final long serialVersionUID = 1466479621937L;

	/** ID主键，序列生成 */
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/** 商户编号 */
	private String shbh;
	/** 退票单号 */
	private String tpdh;
	/** 是否符合自动退票条件1符合，0不符合 */
	private String zdtpSffh;
	/** 自动退票规则ID */
	private String gzid;
	/** 创建时间 */
	private Date cjsj;
	/** 退票状态 0 初始状态 1退票成功 2 退票失败 3 退票中 */
	private String tpzt;
	/** 退票类型：0自愿退票，1非自原退票 退票时为必填，废票不填 */
	private String tplx;
	/** 采购编码 */
	private String cgPnrNo;
	/** 采购大编码 */
	private String cgHkgsPnr;
	/** 航空公司二字码 */
	private String hkgs;
	/** 航程类型1.单程，2.往返，3.联程，4.缺口 */
	private String hclx;
	/** 国内国际 1国内，2国际 */
	private String gngj;
	/** 航程格式：WUHPEK|PEKWUH */
	private String hc;
	/** 舱位格式：V|R */
	private String cw;
	/** 航班号格式：CZ1234|CZ3541 */
	private String hbh;
	/** 航程起飞时间格式：2016-06-18 */
	private String qfsj;
	/** 订单乘机人多个使用英文逗号,分隔 */
	private String cjr;
	/** 乘机人数 */
	private Integer cjrs;
	/** 票号，一个人多张票用|分隔，多个人的票之间用逗号分隔 */
	private String tkno;
	/** 采购销售价 */
	private Double cgPj;
	/** 采购账单价 */
	private Double cgZdj;
	/** 采购机建 */
	private Double cgJsf;
	/** 采购税费 */
	private Double cgTax;
	/** 采购奖励金额 */
	private Double cgJlje;
	/** 采购代理费 */
	private Double cgDlf;
	/** 采购退票手续费 */
	private Double cgTpf;
	/** 采购退款金额 */
	private Double cgTkje;
	/** 采购实退金额 */
	private Double cgStje;
	/** 采购退款方式 */
	private String cgTkkm;
	/** 采购来源BSPET、BOP、BPET、OP */
	private String cgly;
	/** 采购平台标识 */
	private String cgPtbs;
	/** 退票航司订单号 */
	private String hkgsDdbh;
	/** 平台采购订单号 */
	private String cgDdbh;
	/** 自动退票员 */
	private String zdtpy;
	/** 自动退票员密码存值为密码明文 */
	private String zdtpymm;
	/** 说明，失败原因等 */
	private String sm;
	/** 最晚采购提交时间 */
	private Date zwcgtjsj;
	/** 退票完成时间 */
	private Date tpwcsj;
	/**
	 * 执行耗时(自动退票开始到退票结束)
	 */
	private Integer zxhs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh == null ? null : shbh.trim();
	}

	public String getTpdh() {
		return tpdh;
	}

	public void setTpdh(String tpdh) {
		this.tpdh = tpdh == null ? null : tpdh.trim();
	}

	public String getZdtpSffh() {
		return zdtpSffh;
	}

	public void setZdtpSffh(String zdtpSffh) {
		this.zdtpSffh = zdtpSffh == null ? null : zdtpSffh.trim();
	}

	public String getGzid() {
		return gzid;
	}

	public void setGzid(String gzid) {
		this.gzid = gzid == null ? null : gzid.trim();
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class) 
	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getTpzt() {
		return tpzt;
	}

	public void setTpzt(String tpzt) {
		this.tpzt = tpzt == null ? null : tpzt.trim();
	}

	public String getTplx() {
		return tplx;
	}

	public void setTplx(String tplx) {
		this.tplx = tplx == null ? null : tplx.trim();
	}

	public String getCgPnrNo() {
		return cgPnrNo;
	}

	public void setCgPnrNo(String cgPnrNo) {
		this.cgPnrNo = cgPnrNo == null ? null : cgPnrNo.trim();
	}

	public String getCgHkgsPnr() {
		return cgHkgsPnr;
	}

	public void setCgHkgsPnr(String cgHkgsPnr) {
		this.cgHkgsPnr = cgHkgsPnr == null ? null : cgHkgsPnr.trim();
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs == null ? null : hkgs.trim();
	}

	public String getHclx() {
		return hclx;
	}

	public void setHclx(String hclx) {
		this.hclx = hclx == null ? null : hclx.trim();
	}

	public String getGngj() {
		return gngj;
	}

	public void setGngj(String gngj) {
		this.gngj = gngj == null ? null : gngj.trim();
	}

	public String getHc() {
		return hc;
	}

	public void setHc(String hc) {
		this.hc = hc == null ? null : hc.trim();
	}

	public String getCw() {
		return cw;
	}

	public void setCw(String cw) {
		this.cw = cw == null ? null : cw.trim();
	}

	public String getHbh() {
		return hbh;
	}

	public void setHbh(String hbh) {
		this.hbh = hbh == null ? null : hbh.trim();
	}

	public String getQfsj() {
		return qfsj;
	}

	public void setQfsj(String qfsj) {
		this.qfsj = qfsj == null ? null : qfsj.trim();
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr == null ? null : cjr.trim();
	}

	public Integer getCjrs() {
		return cjrs;
	}

	public void setCjrs(Integer cjrs) {
		this.cjrs = cjrs;
	}

	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno == null ? null : tkno.trim();
	}

	public Double getCgPj() {
		return cgPj;
	}

	public void setCgPj(Double cgPj) {
		this.cgPj = cgPj;
	}

	public Double getCgJsf() {
		return cgJsf;
	}

	public void setCgJsf(Double cgJsf) {
		this.cgJsf = cgJsf;
	}

	public Double getCgZdj() {
		return cgZdj;
	}

	public void setCgZdj(Double cgZdj) {
		this.cgZdj = cgZdj;
	}

	public Double getCgTax() {
		return cgTax;
	}

	public void setCgTax(Double cgTax) {
		this.cgTax = cgTax;
	}

	public Double getCgJlje() {
		return cgJlje;
	}

	public void setCgJlje(Double cgJlje) {
		this.cgJlje = cgJlje;
	}

	public Double getCgDlf() {
		return cgDlf;
	}

	public void setCgDlf(Double cgDlf) {
		this.cgDlf = cgDlf;
	}

	public Double getCgTpf() {
		return cgTpf;
	}

	public void setCgTpf(Double cgTpf) {
		this.cgTpf = cgTpf;
	}

	public Double getCgTkje() {
		return cgTkje;
	}

	public void setCgTkje(Double cgTkje) {
		this.cgTkje = cgTkje;
	}

	public Double getCgStje() {
		return cgStje;
	}

	public void setCgStje(Double cgStje) {
		this.cgStje = cgStje;
	}

	public String getCgTkkm() {
		return cgTkkm;
	}

	public void setCgTkkm(String cgTkkm) {
		this.cgTkkm = cgTkkm;
	}

	public String getCgly() {
		return cgly;
	}

	public void setCgly(String cgly) {
		this.cgly = cgly == null ? null : cgly.trim();
	}

	public String getCgPtbs() {
		return cgPtbs;
	}

	public void setCgPtbs(String cgPtbs) {
		this.cgPtbs = cgPtbs == null ? null : cgPtbs.trim();
	}

	public String getHkgsDdbh() {
		return hkgsDdbh;
	}

	public void setHkgsDdbh(String hkgsDdbh) {
		this.hkgsDdbh = hkgsDdbh == null ? null : hkgsDdbh.trim();
	}

	public String getCgDdbh() {
		return cgDdbh;
	}

	public void setCgDdbh(String cgDdbh) {
		this.cgDdbh = cgDdbh == null ? null : cgDdbh.trim();
	}

	public String getZdtpy() {
		return zdtpy;
	}

	public void setZdtpy(String zdtpy) {
		this.zdtpy = zdtpy == null ? null : zdtpy.trim();
	}

	public String getZdtpymm() {
		return zdtpymm;
	}

	public void setZdtpymm(String zdtpymm) {
		this.zdtpymm = zdtpymm == null ? null : zdtpymm.trim();
	}

	public String getSm() {
		return sm;
	}

	public void setSm(String sm) {
		this.sm = sm == null ? null : sm.trim();
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class) 
	public Date getZwcgtjsj() {
		return zwcgtjsj;
	}

	public void setZwcgtjsj(Date zwcgtjsj) {
		this.zwcgtjsj = zwcgtjsj;
	}

	@XmlJavaTypeAdapter(JaxbDateSerializer.class) 
	public Date getTpwcsj() {
		return tpwcsj;
	}

	public void setTpwcsj(Date tpwcsj) {
		this.tpwcsj = tpwcsj;
	}

	public Integer getZxhs() {
        return zxhs;
    }

    public void setZxhs(Integer zxhs) {
        this.zxhs = zxhs;
    }	
}