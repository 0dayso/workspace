package cn.vetech.vedsb.jp.entity.jptpgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_TPD")
public class JpTpd extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 8259449693063297525L;
	
	//销售退票状态 0已申请，1已审核，2已办理，9已取消 
	/**
	 * 销售退票状态  0 已申请
	 */
	public static final String XS_TPZT_YSQ = "0";
	/**
	 * 销售退票状态 1 已审核
	 */
	public static final String XS_TPZT_YSH = "1";
	/**
	 * 销售退票状态 2 已办理
	 */
	public static final String XS_TPZT_YBL = "2";
	/**
	 * 销售退票状态 9 已取消
	 */
	public static final String XS_TPZT_YQX = "9";
	
	
	//采购退票状态  0待提交，1提交中，2已提交，3已办理，4已拒单，9已取消 
	/**
	 * 采购退票状态  0 待提交
	 */
	public static final String CG_TPZT_DTJ="0";
	/**
	 * 采购退票状态 1 提交中
	 */
	public static final String CG_TPZT_TJZ="1";
	/**
	 * 采购退票状态 2已提交
	 */
	public static final String CG_TPZT_YTJ="2";
	/**
	 * 采购退票状态 3 已办理
	 */
	public static final String CG_TPZT_YBL="3";
	/**
	 * 采购退票状态 4 已拒单
	 */
	public static final String CG_TPZT_YJD="4";
	/**
	 * 采购退票状态 9 已取消 
	 */
	public static final String CG_TPZT_YQX="9";


	//自动取消座位状态 0未开启自动取消 1自动取消成功 2自动取消失败

	/**
	 * 自动取消座位状态  0 未开启自动取消 
	 */
	public static final String CG_ZDQXZT_WKQ="0";
	
	/**
	 * 自动取消座位状态  1 自动取消成功
	 */
	public static final String CG_ZDQXZT_QXCG="1";
	
	/**
	 * 自动取消座位状态 2 自动取消失败
	 */
	public static final String CG_ZDQXZT_QXSB="2";
	
	@Id
	@GeneratedValue(generator="no")
	/**
	 * 退票单号
	 */
    private String tpdh;
	
	/**
	 * 订单编号
	 */
    private String ddbh;

    /**
	 * 商户编号
	 */
    private String shbh;

    /**
	 * 国内国际1国内，0国际
	 */
    private String gngj;

    /**
     * 方案ID
     */
    private String faid;

    /**
     * 网店平台
     */
    private String wdpt;
    
    /**
     * 网店ID号
     */
    private String wdid;
    
    /**
     * 网店产品类型普通、包机等
     */
    private String wdZclx;
    
    /**
     * 网店订单类型金牌服务等
     */
    private String wdDdlx;
    
    /**
     * 网店政策代码
     */
    private String wdZcdm;
    
    /**
     * 政策渠道Z自有，P平台，G官网等
     */
    private String zcQd;
    
    /**
     * 政策来源如果是P，存平台编号，G存航司二字码
     */
    private String zcLy;
    
    /**
     * 外部退单编号
     */
    private String wbdh;
    
    /**
     * 外部订单编号
     */
    private String wbddbh;
    
    /**
     * 收款科目
     */
    private String skkm;
    
    /**
     * 联系人
     */
    private String nxr;
    
    /**
     * 联系手机
     */
    private String nxsj;
    
    /**
     * 联系电话
     */
    private String nxdh;
    
    /**
     * 导单时间
     */
    private Date ddsj;
    
    /**
     * 导单用户
     */
    private String ddyh;
    
    /**
     * 导单部门
     */
    private String ddbm;
    
    /**
     * 销售PNR_NO
     */
    private String xsPnrNo;
    
    /**
     * 销售PNR状态
     */
    private String xsPnrZt;
    
    /**
     * 销售航司大编码
     */
    private String xsHkgsPnr;
    
    /**
     * 航程
     */
    private String hc;
    
    /**
     * 出发日期
     */
    private Date cfrq;
    
    /**
     * 出发时间
     */
    private String cfsj;
    
    /**
     * 乘机人数
     */
    private Short cjrs;
    
    /**
     * 乘机人
     */
    private String cjr;
    
    /**
     * 销售航班号
     */
    private String xsHbh;
    
    /**
     * 销售账单价
     */
    private Long xsZdj;
    
    /**
     * 销售价
     */
    private BigDecimal xsPj;
    
    /**
     * 销售机建
     */
    private Long xsJsf;
    
    /**
     * 销售税费
     */
    private Long xsTax;
    
    /**
     * 销售退改签
     */
    private String xsTgq;
    
    /**
     * 销售退票费率
     */
    private BigDecimal xsTpfl;
    
    /**
     * 销售退票手续
     */
    private BigDecimal xsTpsxf;
    
    /**
     * 销售退款金额
     */
    private BigDecimal xsTkje;
    
    /**
     * 销售退款科目
     */
    private String xsTkkm;
    
    /**
     * 销售退票状态 0已申请，1已审核，2已办理，9已取消
     */
    private String xsTpzt;
    
    /**
     * 销售审核人
     */
    private String xsShr;
    
    /**
     * 销售审核时间
     */
    private Date xsShsj;
    
    /**
     * 销售办理人
     */
    private String xsBlr;
    
    /**
     * 销售办理时间
     */
    private Date xsBlsj;
    
    /**
     * 销售是否自愿退票1自愿退票，0非自愿退票
     */
    private String xsSfzytp;
    
    /**
     * 销售退票理由
     */
    private String xsTply;
    
    /**
     * 销售退票证明存文件路径
     */
    private String xsTpzm;
    
    /**
     * 销售退票备注
     */
    private String xsTpbz;
    
    /**
     * 销售自动取消状态
     */
    private String xsZdqxzt;
    
    /**
     * 销售自动取消失败原因
     */
    private String xsZdqxsbyy;
    
    /**
     * 销售自动退票状态
     */
    private String xsZdtpzt;
    
    /**
     * 销售自动出票失败原因
     */
    private String xsZdtpsbyy;
    
    /**
     * 采购来源 数据字典 10014
     */  
    private String cgly;
    
    /**
     * 采购单位
     */  
    private String cgdw;
    
    /**
     * 采购PNR_NO
     */
    private String cgPnrNo;
    
    /**
     * 采购航司大编码
     */
    private String cgHkgsPnr;
    
    /**
     * 采购航班号
     */
    private String cgHbh;
    
    /**
     * 采购账单价
     */
    private Long cgZdj;
    
    /**
     * 采购价
     */
    private BigDecimal cgPj;
    
    /**
     * 采购机建
     */
    private Long cgJsf;
    
    /**
     * 采购税费
     */
    private Long cgTax;
    
    /**
     * 采购退改签
     */
    private String cgTgq;
    
    /**
     * 采购退票费率
     */
    private BigDecimal cgTpfl;
    
    /**
     * 采购退票费
     */
    private BigDecimal cgTpf;
    
    /**
     * 采购退款金额
     */
    private BigDecimal cgTkje;
    
    /**
     * 采购实退金额
     */
    private BigDecimal cgStje;
    
    /**
     * 采购退款科目
     */
    private String cgTkkm;
    
    /**
     * 采购最晚办理时间
     */
    private Date cgZwblsj;
    
    /**
     * 采购退票状态 0待提交，1提交中，2已提交，3已办理，4已拒单，9已取消
     */
    private String cgTpzt;
    
    /**
     * 采购提交人
     */
    private String cgTjr;
    
    /**
     * 采购提交时间
     */
    private Date cgTjsj;
    
    /**
     * 采购办理人
     */
    private String cgBlr;
    
    /**
     * 采购办理时间
     */
    private Date cgBlsj;
    
    /**
     * 采购办理类型1退票，2废票
     */
    private String cgBllx;
    
    /**
     * 采购是否自愿退票1自愿退票，0非自愿退票
     */
    private String cgSfzytp;
    
    /**
     * 采购退票理由
     */
    private String cgTply;
    
    /**
     * 采购退票证明存文件路径
     */
    private String cgTpzm;
    
    /**
     * 采购退票备注
     */
    private String cgTpbz;
    
    /**
     * 采购自动取消状态
     */
    private String cgZdqxzt;
    
    /**
     * 采购自动取消失败原因
     */
    private String cgZdqxsbyy;
    
    /**
     * 采购自动退票状态
     */
    private String cgZdtpzt;
    
    /**
     * 采购自动出票失败原因
     */
    private String cgZdtpsbyy;
    
    /**
     * 是否主订单空或0是，1否
     */
    private String sfzdd;
    
    /**
     * 主订单编号
     */
    private String zddbh;
    
    /**
     * 主订单类型来自数据字典
     */
    private String zddlx;
    
    /**
     * 到账审核状态
     */
    private String dzshZt;
    
    /**
     * 到账审核人
     */
    private String dzshYhbh;
    
    /**
     * 到账审核部门
     */
    private String dzshBmbh;
    
    /**
     * 到账审核时间
     */
    private Date dzshDatetime;
    
    /**
     * 业务审核状态
     */
    private String ywshZt;
    
    /**
     * 业务审核人
     */
    private String ywshYhbh;
    
    /**
     * 业务审核部门
     */
    private String ywshBmbh;
    
    /**
     * 业务审核时间
     */
    private Date ywshDatetime;
    
    /** 
     * 采购预计回款时间
     */
    private Date cgYjhksj;
    
    /** 
     * 采购预计回款时长（采购提交时从jp_tphksj_sz中取）
     */
    private Double cgYjhksc;
    
    /** 
     * 采购实际回款时间
     */
    private Date cgSjhksj;

    
    /**
     * 修改来源
     */
    private String xgly;

    
    /**
     * 最后修改时间
     */
    private Date xgsj;
    
    /**
     * 最后修改用户
     */
    private String xgyh;
    
    /**
     * 销售对账状态：1已对账，且对比正确，0或空为未对比
     */    
    private String xsdzzt;
    
    /**
     * 连续客票状态 0非连续客票,1连续客票部分退,2连续客票全退
     */
    private String lxkpzt;
    
    
    /**
     * 是否取消座位：1保留座位，1不保留
     */   
    private String sfqxzw;
    
    private String cgTpdh;//采购退票单号
    
    private String cgQtly;//采购其他理由(自定义)
    
    private String xsQtly;//销售其他理由(自定义)
    
    
    /**销售佣金*/
    private BigDecimal xsYj;
    /**
     * 外部订单状态，目前仅存淘宝外部退票单状态
     * 淘宝
     * 申请单状态（1初始 2接受 3确认 4失败 5买家处理 6卖家处理 7等待小二回填 8退款成功）
     */
    private String wbtpzt; 
    
    
    /**
     * 采购退票凭证提交状态，0或空未提交，1已提交
     */
    private String cgTppzTjzt;

    /**
     * 采购退票凭证提交时间
     */
    private Date cgTppzTjsj;

    /**
     * 采购退票凭证提交用户
     */
    private String cgTppzTjyh;
    
    /**
     * 采购订单编号(用于淘宝退票使用)
     */
    private String cgDdbh;
    
    public String getTpdh() {
        return tpdh;
    }

    public void setTpdh(String tpdh) {
        this.tpdh = tpdh == null ? null : tpdh.trim();
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh == null ? null : ddbh.trim();
    }

  
    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
    }

    public String getGngj() {
        return gngj;
    }

    public void setGngj(String gngj) {
        this.gngj = gngj == null ? null : gngj.trim();
    }

    public String getFaid() {
        return faid;
    }

    public void setFaid(String faid) {
        this.faid = faid == null ? null : faid.trim();
    }

    public String getWdpt() {
        return wdpt;
    }

    public void setWdpt(String wdpt) {
        this.wdpt = wdpt == null ? null : wdpt.trim();
    }

    public String getWdid() {
        return wdid;
    }

    public void setWdid(String wdid) {
        this.wdid = wdid == null ? null : wdid.trim();
    }

    public String getWdZclx() {
        return wdZclx;
    }

    public void setWdZclx(String wdZclx) {
        this.wdZclx = wdZclx == null ? null : wdZclx.trim();
    }

    public String getWdDdlx() {
        return wdDdlx;
    }

    public void setWdDdlx(String wdDdlx) {
        this.wdDdlx = wdDdlx == null ? null : wdDdlx.trim();
    }

    public String getWdZcdm() {
        return wdZcdm;
    }

    public void setWdZcdm(String wdZcdm) {
        this.wdZcdm = wdZcdm == null ? null : wdZcdm.trim();
    }

    public String getZcQd() {
        return zcQd;
    }

    public void setZcQd(String zcQd) {
        this.zcQd = zcQd == null ? null : zcQd.trim();
    }

    public String getZcLy() {
        return zcLy;
    }

    public void setZcLy(String zcLy) {
        this.zcLy = zcLy == null ? null : zcLy.trim();
    }

    public String getWbdh() {
        return wbdh;
    }

    public void setWbdh(String wbdh) {
        this.wbdh = wbdh == null ? null : wbdh.trim();
    }

    public String getSkkm() {
        return skkm;
    }

    public void setSkkm(String skkm) {
        this.skkm = skkm == null ? null : skkm.trim();
    }

    public String getNxr() {
        return nxr;
    }

    public void setNxr(String nxr) {
        this.nxr = nxr == null ? null : nxr.trim();
    }

    public String getNxsj() {
        return nxsj;
    }

    public void setNxsj(String nxsj) {
        this.nxsj = nxsj == null ? null : nxsj.trim();
    }

    public String getNxdh() {
        return nxdh;
    }

    public void setNxdh(String nxdh) {
        this.nxdh = nxdh == null ? null : nxdh.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getDdsj() {
        return ddsj;
    }

    public void setDdsj(Date ddsj) {
        this.ddsj = ddsj;
    }

    public String getDdyh() {
        return ddyh;
    }

    public void setDdyh(String ddyh) {
        this.ddyh = ddyh == null ? null : ddyh.trim();
    }

    public String getDdbm() {
        return ddbm;
    }

    public void setDdbm(String ddbm) {
        this.ddbm = ddbm == null ? null : ddbm.trim();
    }

    public String getXsPnrNo() {
        return xsPnrNo;
    }

    public void setXsPnrNo(String xsPnrNo) {
        this.xsPnrNo = xsPnrNo == null ? null : xsPnrNo.trim();
    }

    public String getXsPnrZt() {
        return xsPnrZt;
    }

    public void setXsPnrZt(String xsPnrZt) {
        this.xsPnrZt = xsPnrZt == null ? null : xsPnrZt.trim();
    }
    
    public String getXsHkgsPnr() {
        return xsHkgsPnr;
    }

    public void setXsHkgsPnr(String xsHkgsPnr) {
        this.xsHkgsPnr = xsHkgsPnr == null ? null : xsHkgsPnr.trim();
    }

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc == null ? null : hc.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
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
        this.cfsj = cfsj == null ? null : cfsj.trim();
    }

    public Short getCjrs() {
        return cjrs;
    }

    public void setCjrs(Short cjrs) {
        this.cjrs = cjrs;
    }

    public String getCjr() {
        return cjr;
    }

    public void setCjr(String cjr) {
        this.cjr = cjr == null ? null : cjr.trim();
    }

    public String getXsHbh() {
        return xsHbh;
    }

    public void setXsHbh(String xsHbh) {
        this.xsHbh = xsHbh == null ? null : xsHbh.trim();
    }
   
    public Long getXsZdj() {
        return xsZdj;
    }

    public void setXsZdj(Long xsZdj) {
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

    public String getXsTgq() {
        return xsTgq;
    }

    public void setXsTgq(String xsTgq) {
        this.xsTgq = xsTgq == null ? null : xsTgq.trim();
    }

    public BigDecimal getXsTpfl() {
        return xsTpfl;
    }

    public void setXsTpfl(BigDecimal xsTpfl) {
        this.xsTpfl = xsTpfl;
    }

    public BigDecimal getXsTpsxf() {
        return xsTpsxf;
    }

    public void setXsTpsxf(BigDecimal xsTpsxf) {
        this.xsTpsxf = xsTpsxf;
    }

    public BigDecimal getXsTkje() {
        return xsTkje;
    }

    public void setXsTkje(BigDecimal xsTkje) {
        this.xsTkje = xsTkje;
    }

    public String getXsTkkm() {
        return xsTkkm;
    }

    public void setXsTkkm(String xsTkkm) {
        this.xsTkkm = xsTkkm == null ? null : xsTkkm.trim();
    }

    public String getXsTpzt() {
        return xsTpzt;
    }

    public void setXsTpzt(String xsTpzt) {
        this.xsTpzt = xsTpzt == null ? null : xsTpzt.trim();
    }

    public String getXsShr() {
        return xsShr;
    }

    public void setXsShr(String xsShr) {
        this.xsShr = xsShr == null ? null : xsShr.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getXsShsj() {
        return xsShsj;
    }

    public void setXsShsj(Date xsShsj) {
        this.xsShsj = xsShsj;
    }

    public String getXsBlr() {
        return xsBlr;
    }

    public void setXsBlr(String xsBlr) {
        this.xsBlr = xsBlr == null ? null : xsBlr.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getXsBlsj() {
        return xsBlsj;
    }

    public void setXsBlsj(Date xsBlsj) {
        this.xsBlsj = xsBlsj;
    }

    public String getXsSfzytp() {
        return xsSfzytp;
    }

    public void setXsSfzytp(String xsSfzytp) {
        this.xsSfzytp = xsSfzytp == null ? null : xsSfzytp.trim();
    }

    public String getXsTply() {
        return xsTply;
    }

    public void setXsTply(String xsTply) {
        this.xsTply = xsTply == null ? null : xsTply.trim();
    }

    public String getXsTpzm() {
        return xsTpzm;
    }

    public void setXsTpzm(String xsTpzm) {
        this.xsTpzm = xsTpzm == null ? null : xsTpzm.trim();
    }

    public String getXsTpbz() {
        return xsTpbz;
    }

    public void setXsTpbz(String xsTpbz) {
        this.xsTpbz = xsTpbz == null ? null : xsTpbz.trim();
    }

    public String getXsZdqxzt() {
        return xsZdqxzt;
    }

    public void setXsZdqxzt(String xsZdqxzt) {
        this.xsZdqxzt = xsZdqxzt == null ? null : xsZdqxzt.trim();
    }

    public String getXsZdqxsbyy() {
        return xsZdqxsbyy;
    }

    public void setXsZdqxsbyy(String xsZdqxsbyy) {
        this.xsZdqxsbyy = xsZdqxsbyy == null ? null : xsZdqxsbyy.trim();
    }

    public String getXsZdtpzt() {
        return xsZdtpzt;
    }

    public void setXsZdtpzt(String xsZdtpzt) {
        this.xsZdtpzt = xsZdtpzt == null ? null : xsZdtpzt.trim();
    }

    public String getXsZdtpsbyy() {
        return xsZdtpsbyy;
    }

    public void setXsZdtpsbyy(String xsZdtpsbyy) {
        this.xsZdtpsbyy = xsZdtpsbyy == null ? null : xsZdtpsbyy.trim();
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

    public String getCgHbh() {
        return cgHbh;
    }

    public void setCgHbh(String cgHbh) {
        this.cgHbh = cgHbh == null ? null : cgHbh.trim();
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

    public String getCgTgq() {
        return cgTgq;
    }

    public void setCgTgq(String cgTgq) {
        this.cgTgq = cgTgq == null ? null : cgTgq.trim();
    }

    public BigDecimal getCgTpfl() {
        return cgTpfl;
    }

    public void setCgTpfl(BigDecimal cgTpfl) {
        this.cgTpfl = cgTpfl;
    }

    public BigDecimal getCgTpf() {
        return cgTpf;
    }

    public void setCgTpf(BigDecimal cgTpf) {
        this.cgTpf = cgTpf;
    }

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

    public String getCgTkkm() {
        return cgTkkm;
    }

    public void setCgTkkm(String cgTkkm) {
        this.cgTkkm = cgTkkm == null ? null : cgTkkm.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getCgZwblsj() {
        return cgZwblsj;
    }

    public void setCgZwblsj(Date cgZwblsj) {
        this.cgZwblsj = cgZwblsj;
    }

    public String getCgTpzt() {
        return cgTpzt;
    }

    public void setCgTpzt(String cgTpzt) {
        this.cgTpzt = cgTpzt == null ? null : cgTpzt.trim();
    }

    public String getCgTjr() {
        return cgTjr;
    }

    public void setCgTjr(String cgTjr) {
        this.cgTjr = cgTjr == null ? null : cgTjr.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getCgTjsj() {
        return cgTjsj;
    }

    public void setCgTjsj(Date cgTjsj) {
        this.cgTjsj = cgTjsj;
    }

    public String getCgBlr() {
        return cgBlr;
    }

    public void setCgBlr(String cgBlr) {
        this.cgBlr = cgBlr == null ? null : cgBlr.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getCgBlsj() {
        return cgBlsj;
    }

    public void setCgBlsj(Date cgBlsj) {
        this.cgBlsj = cgBlsj;
    }

    public String getCgBllx() {
        return cgBllx;
    }

    public void setCgBllx(String cgBllx) {
        this.cgBllx = cgBllx == null ? null : cgBllx.trim();
    }

    public String getCgSfzytp() {
        return cgSfzytp;
    }

    public void setCgSfzytp(String cgSfzytp) {
        this.cgSfzytp = cgSfzytp == null ? null : cgSfzytp.trim();
    }

    public String getCgTply() {
        return cgTply;
    }

    public void setCgTply(String cgTply) {
        this.cgTply = cgTply == null ? null : cgTply.trim();
    }

    public String getCgTpzm() {
        return cgTpzm;
    }

    public void setCgTpzm(String cgTpzm) {
        this.cgTpzm = cgTpzm == null ? null : cgTpzm.trim();
    }

    public String getCgTpbz() {
        return cgTpbz;
    }

    public void setCgTpbz(String cgTpbz) {
        this.cgTpbz = cgTpbz == null ? null : cgTpbz.trim();
    }

    public String getCgZdqxzt() {
        return cgZdqxzt;
    }

    public void setCgZdqxzt(String cgZdqxzt) {
        this.cgZdqxzt = cgZdqxzt == null ? null : cgZdqxzt.trim();
    }

    public String getCgZdqxsbyy() {
        return cgZdqxsbyy;
    }

    public void setCgZdqxsbyy(String cgZdqxsbyy) {
        this.cgZdqxsbyy = cgZdqxsbyy == null ? null : cgZdqxsbyy.trim();
    }

    public String getCgZdtpzt() {
        return cgZdtpzt;
    }

    public void setCgZdtpzt(String cgZdtpzt) {
        this.cgZdtpzt = cgZdtpzt == null ? null : cgZdtpzt.trim();
    }

    public String getCgZdtpsbyy() {
        return cgZdtpsbyy;
    }

    public void setCgZdtpsbyy(String cgZdtpsbyy) {
        this.cgZdtpsbyy = cgZdtpsbyy == null ? null : cgZdtpsbyy.trim();
    }

    public String getSfzdd() {
        return sfzdd;
    }

    public void setSfzdd(String sfzdd) {
        this.sfzdd = sfzdd == null ? null : sfzdd.trim();
    }

    public String getZddbh() {
        return zddbh;
    }

    public void setZddbh(String zddbh) {
        this.zddbh = zddbh == null ? null : zddbh.trim();
    }

    public String getZddlx() {
        return zddlx;
    }

    public void setZddlx(String zddlx) {
        this.zddlx = zddlx == null ? null : zddlx.trim();
    }

    public String getDzshZt() {
        return dzshZt;
    }

    public void setDzshZt(String dzshZt) {
        this.dzshZt = dzshZt == null ? null : dzshZt.trim();
    }

    public String getDzshYhbh() {
        return dzshYhbh;
    }

    public void setDzshYhbh(String dzshYhbh) {
        this.dzshYhbh = dzshYhbh == null ? null : dzshYhbh.trim();
    }

    public String getDzshBmbh() {
        return dzshBmbh;
    }

    public void setDzshBmbh(String dzshBmbh) {
        this.dzshBmbh = dzshBmbh == null ? null : dzshBmbh.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getDzshDatetime() {
        return dzshDatetime;
    }

    public void setDzshDatetime(Date dzshDatetime) {
        this.dzshDatetime = dzshDatetime;
    }

    public String getYwshZt() {
        return ywshZt;
    }

    public void setYwshZt(String ywshZt) {
        this.ywshZt = ywshZt == null ? null : ywshZt.trim();
    }

    public String getYwshYhbh() {
        return ywshYhbh;
    }

    public void setYwshYhbh(String ywshYhbh) {
        this.ywshYhbh = ywshYhbh == null ? null : ywshYhbh.trim();
    }

    public String getYwshBmbh() {
        return ywshBmbh;
    }

    public void setYwshBmbh(String ywshBmbh) {
        this.ywshBmbh = ywshBmbh == null ? null : ywshBmbh.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getYwshDatetime() {
        return ywshDatetime;
    }

    public void setYwshDatetime(Date ywshDatetime) {
        this.ywshDatetime = ywshDatetime;
    }

    public String getXgly() {
        return xgly;
    }

    public void setXgly(String xgly) {
        this.xgly = xgly == null ? null : xgly.trim();
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgyh() {
        return xgyh;
    }

    public void setXgyh(String xgyh) {
        this.xgyh = xgyh == null ? null : xgyh.trim();
    }

	public String getCgly() {
		return cgly;
	}

	public void setCgly(String cgly) {
		this.cgly  = cgly == null ? null : cgly.trim();
	}

	public String getCgdw() {
		return cgdw;
	}

	public void setCgdw(String cgdw) {
		this.cgdw  = cgdw == null ? null : cgdw.trim();
	}


	public String getLxkpzt() {
		return lxkpzt;
	}

	public void setLxkpzt(String lxkpzt) {
		this.lxkpzt = lxkpzt == null ? null : lxkpzt.trim();
	}


    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
	public Date getCgYjhksj() {
		return cgYjhksj;
	}

	public void setCgYjhksj(Date cgYjhksj) {
		this.cgYjhksj = cgYjhksj;
	}
	
	public Double getCgYjhksc() {
		return cgYjhksc;
	}

	public void setCgYjhksc(Double cgYjhksc) {
		this.cgYjhksc = cgYjhksc;
	}


    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
	public Date getCgSjhksj() {
		return cgSjhksj;
	}

	public void setCgSjhksj(Date cgSjhksj) {
		this.cgSjhksj = cgSjhksj;
	}
	public String getXsdzzt() {
		return xsdzzt;
	}


	public void setXsdzzt(String xsdzzt) {
		this.xsdzzt = xsdzzt;
	}

	
	public String getSfqxzw() {
		return sfqxzw;
	}

	public void setSfqxzw(String sfqxzw) {
		this.sfqxzw = sfqxzw;
	}

	public String getCgTpdh() {
		return cgTpdh;
	}

	public void setCgTpdh(String cgTpdh) {
		this.cgTpdh = cgTpdh;
	}

	public String getCgQtly() {
		return cgQtly;
	}

	public void setCgQtly(String cgQtly) {
		this.cgQtly = cgQtly;
	}

	public String getXsQtly() {
		return xsQtly;
	}

	public void setXsQtly(String xsQtly) {
		this.xsQtly = xsQtly;
	}
	
	public BigDecimal getXsYj() {
		return xsYj;
	}

	public void setXsYj(BigDecimal xsYj) {
		this.xsYj = xsYj;
	}
	
	public String getWbtpzt() {
		return wbtpzt;
	}

	public void setWbtpzt(String wbtpzt) {
		this.wbtpzt = wbtpzt;
	}

	public String getWbddbh() {
		return wbddbh;
	}

	public void setWbddbh(String wbddbh) {
		this.wbddbh = wbddbh;
	}
	
	public String getCgTppzTjzt() {
        return cgTppzTjzt;
    }

    public void setCgTppzTjzt(String cgTppzTjzt) {
        this.cgTppzTjzt = cgTppzTjzt == null ? null : cgTppzTjzt.trim();
    }

    public Date getCgTppzTjsj() {
        return cgTppzTjsj;
    }

    @XmlJavaTypeAdapter(JaxbDateSerializer.class) 
    public void setCgTppzTjsj(Date cgTppzTjsj) {
        this.cgTppzTjsj = cgTppzTjsj;
    }

    public String getCgTppzTjyh() {
        return cgTppzTjyh;
    }

    public void setCgTppzTjyh(String cgTppzTjyh) {
        this.cgTppzTjyh = cgTppzTjyh == null ? null : cgTppzTjyh.trim();
    }

	public String getCgDdbh() {
		return cgDdbh;
	}

	public void setCgDdbh(String cgDdbh) {
		 this.cgDdbh = cgDdbh == null ? null : cgDdbh.trim();
	}
    
    
    
}