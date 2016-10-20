package cn.vetech.vedsb.jp.entity.jpgqgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_gqd")
public class JpGqd extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 1452962070617558829L;

	/**
	 * 改期
	 */
	public static final String GQLX_GQ = "1";
	
	/**
	 * 升舱
	 */
	public static final String GQLX_SC = "2";
	
	/**
	 * 是否主订单：是
	 */
	public static final String SFZDD_S = "1";
	
	/**
	 * 是否主订单：否
	 */
	public static final String SFZDD_F = "2";
	
	/**
	 * 待确认
	 */
	public static final String GQZT_DQR = "0";
	
	/**
	 * 已确认
	 */
	public static final String GQZT_YQR = "1";
	
	/**
	 * 改签中
	 */
	public static final String GQZT_GQZ = "3";
	
	/**
	 * 已改签
	 */
	public static final String GQZT_YGQ = "4";
	
	/**
	 * 客户消
	 */
	public static final String GQZT_KHX = "7";
	
	/**
	 * 已取消
	 */
	public static final String GQZT_YQX = "8";
	
	/**
	 * 改签单号
	 */
	@Id
    private String gqdh;

    /**
	 * 改签类型1改期，2升舱
	 */
    private String gqlx;

    /**
	 * 改签状态  0待确认  1已确认 3改签中 4已改签 7客户消 8已取消
	 */
    private String gqzt;

    /**
	 * 改签审核人
	 */
    private String gqShr;

    /**
	 * 改签审核时间
	 */
    private Date gqShsj;

    /**
	 * 改签办理人
	 */
    private String gqBlr;

    /**
	 * 改签办理时间
	 */
    private Date gqBlsj;

    /**
	 * 订单编号
	 */
    private String ddbh;

    /**
	 * 国内国际1国内，0国际
	 */
    private String gngj;

    /**
	 * 商户编号
	 */
    private String shbh;

    /**
	 * 方案ID
	 */
    private String faid;

    /**
	 * 网店平台
	 */
    private String wdpt;

    /**
	 * 网店id
	 */
    private String wdid;

    /**
	 * 网店产品类型
	 */
    private String wdZclx;

    /**
	 * 网店订单类型
	 */
    private String wdDdlx;

    /**
	 * 网店政策代码
	 */
    private String wdZcdm;

    /**
	 * 政策渠道
	 */
    private String zcQd;

    /**
	 * 政策来源
	 */
    private String zcLy;

    /**
	 * 外部改签单号
	 */
    private String wbdh;

    /**
	 * 收款科目
	 */
    private String skkm;

    /**
     * 收款状态 0未收款 1已收款
     */
    private String skzt;

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
	 * 销售PNR_NO
	 */
    private String xsPnrNo;

    /**
	 * 销售航司大编码
	 */
    private String xsHkgsPnr;

    /**
	 * 销售航班号
	 */
    private String xsHbh;

    /**
	 * 销售舱位
	 */
    private String xsCw;

    /**
	 * 改签原因
	 */
    private String gqyy;

    /**
	 *改签后出发日期
	 */
    private Date gqCfrq;

    /**
	 * 改签后出发时间
	 */
    private String gqCfsj;

    /**
	 * 改签后销售PNR_NO
	 */
    private String gqXsPnrNo;

    /**
	 * 改签后销售航司大编码
	 */
    private String gqXsHkgsPnr;

    /**
	 * 改签后销售航班号
	 */
    private String gqXsHbh;

    /**
	 * 改签后销售舱位
	 */
    private String gqXsCw;

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
	 * 采购舱位
	 */
    private String cgCw;

    /**
	 * 改签后采购PNR_NO
	 */
    private String gqCgPnrNo;

	/**
	 * 改签后采购航司大编码
	 */
    private String gqCgHkgsPnr;

	/**
	 * 改签后采购航班号
	 */
    private String gqCgHbh;

	/**
	 * 改签后采购舱位
	 */
    private String gqCgCw;
    
    /**
	 * 改签销售费用
	 */
    private BigDecimal gqXsfy;

    /**
	 * 改签采购费用
	 */
    private BigDecimal gqCgfy;

    /**
	 * 是否主订单
	 */
    private String sfzdd;

    /**
	 * 主订单编号
	 */
    private String zddbh;

    /**
	 * 主订单类型
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
     * 改签采购来源
     */
    @Transient
    private String gqCgly;

    /**
     * 改签采购单位
     */
    private String gqCgdw;

    /**
     * 改签采购状态
     */
    private String gqCgzt;

    /**
     * 改签采购单号
     */
    private String gqCgDdbh;

    /**
     * 改签采购科目
     */
    private String gqCgkm;

    /**
     * 备注
     */
    private String bzbz;
    
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
     * 乘机人信息集合
     */
    @Transient
    private List<JpGqdCjr> cjrList;
    
    /**
     * 航段信息集合
     */
    @Transient
    private List<JpGqdHd> hdList;
    
    /**
     * 白屏改签状态
     */
    private String bpgqzt;
    
    /**
     * 降舱标识改签后是否做了降舱，0或空表示未降舱，1表示已降舱
     * @return
     */
    private String jcbs;
    
    /**
     * 改签采购提交状态,0未改签采购提交 1采购提交中 2采购提交成功
     */
    private String cgtjzt;
    
    /**
     * 采购提交失败原因
     */
    private String cgtjsbyy;
    
    private String xsdzzt;//销售对账状态：1已对账，且对比正确，0或空为未对比
    
    /**
     * 航空公司
     */
    private String hkgs;
    /**
     * 外部改签状态，目前仅存淘宝外部退票单状态
     * 淘宝
     * 1：初始状态，2：已改签成功，3：已拒绝，4：未付款（已回填退票费），5：已付款
     */
    private String wbgqzt;
    
    /**
     * 外部订单编号
     */
    private String wbddbh;

    /**出票部门*/
    private String gqCpbm;
    /**改签销售升舱费用*/
    private BigDecimal gqXsscfy;
    /**改签采购升舱费用*/
    private BigDecimal gqCgscfy;
    
    
    public BigDecimal getGqXsscfy() {
		return gqXsscfy;
	}

	public BigDecimal getGqCgscfy() {
		return gqCgscfy;
	}

	public void setGqXsscfy(BigDecimal gqXsscfy) {
		this.gqXsscfy = gqXsscfy;
	}

	public void setGqCgscfy(BigDecimal gqCgscfy) {
		this.gqCgscfy = gqCgscfy;
	}

	public String getGqCpbm() {
		return gqCpbm;
	}

	public void setGqCpbm(String gqCpbm) {
		this.gqCpbm = gqCpbm;
	}

	public String getGqdh() {
        return gqdh;
    }

    public void setGqdh(String gqdh) {
        this.gqdh = gqdh == null ? null : gqdh.trim();
    }

    public String getGqlx() {
        return gqlx;
    }

    public void setGqlx(String gqlx) {
        this.gqlx = gqlx == null ? null : gqlx.trim();
    }

    public String getGqzt() {
        return gqzt;
    }

    public void setGqzt(String gqzt) {
        this.gqzt = gqzt == null ? null : gqzt.trim();
    }

    public String getGqShr() {
        return gqShr;
    }

    public void setGqShr(String gqShr) {
        this.gqShr = gqShr == null ? null : gqShr.trim();
    }

    public Date getGqShsj() {
        return gqShsj;
    }

    public void setGqShsj(Date gqShsj) {
        this.gqShsj = gqShsj;
    }

    public String getGqBlr() {
        return gqBlr;
    }

    public void setGqBlr(String gqBlr) {
        this.gqBlr = gqBlr == null ? null : gqBlr.trim();
    }

    public Date getGqBlsj() {
        return gqBlsj;
    }

    public void setGqBlsj(Date gqBlsj) {
        this.gqBlsj = gqBlsj;
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh == null ? null : ddbh.trim();
    }

    public String getGngj() {
        return gngj;
    }

    public void setGngj(String gngj) {
        this.gngj = gngj == null ? null : gngj.trim();
    }

    public String getShbh() {
        return shbh;
    }

    public void setShbh(String shbh) {
        this.shbh = shbh == null ? null : shbh.trim();
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

    public String getSkzt() {
        return skzt;
    }

    public void setSkzt(String skzt) {
        this.skzt = skzt == null ? null : skzt.trim();
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

    public String getHc() {
        return hc;
    }

    public void setHc(String hc) {
        this.hc = hc == null ? null : hc.trim();
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

    public String getXsPnrNo() {
        return xsPnrNo;
    }

    public void setXsPnrNo(String xsPnrNo) {
        this.xsPnrNo = xsPnrNo == null ? null : xsPnrNo.trim();
    }

    public String getXsHkgsPnr() {
        return xsHkgsPnr;
    }

    public void setXsHkgsPnr(String xsHkgsPnr) {
        this.xsHkgsPnr = xsHkgsPnr == null ? null : xsHkgsPnr.trim();
    }

    public String getXsHbh() {
        return xsHbh;
    }

    public void setXsHbh(String xsHbh) {
        this.xsHbh = xsHbh == null ? null : xsHbh.trim();
    }

    public String getXsCw() {
        return xsCw;
    }

    public void setXsCw(String xsCw) {
        this.xsCw = xsCw == null ? null : xsCw.trim();
    }

    public String getGqyy() {
        return gqyy;
    }

    public void setGqyy(String gqyy) {
        this.gqyy = gqyy == null ? null : gqyy.trim();
    }

    public Date getGqCfrq() {
        return gqCfrq;
    }

    public void setGqCfrq(Date gqCfrq) {
        this.gqCfrq = gqCfrq;
    }

    public String getGqCfsj() {
        return gqCfsj;
    }

    public void setGqCfsj(String gqCfsj) {
        this.gqCfsj = gqCfsj == null ? null : gqCfsj.trim();
    }

    public String getGqXsPnrNo() {
        return gqXsPnrNo;
    }

    public void setGqXsPnrNo(String gqXsPnrNo) {
        this.gqXsPnrNo = gqXsPnrNo == null ? null : gqXsPnrNo.trim();
    }

    public String getGqXsHkgsPnr() {
        return gqXsHkgsPnr;
    }

    public void setGqXsHkgsPnr(String gqXsHkgsPnr) {
        this.gqXsHkgsPnr = gqXsHkgsPnr == null ? null : gqXsHkgsPnr.trim();
    }

    public String getGqXsHbh() {
        return gqXsHbh;
    }

    public void setGqXsHbh(String gqXsHbh) {
        this.gqXsHbh = gqXsHbh == null ? null : gqXsHbh.trim();
    }

    public String getGqXsCw() {
        return gqXsCw;
    }

    public void setGqXsCw(String gqXsCw) {
        this.gqXsCw = gqXsCw == null ? null : gqXsCw.trim();
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

    public String getCgCw() {
        return cgCw;
    }

    public void setCgCw(String cgCw) {
        this.cgCw = cgCw == null ? null : cgCw.trim();
    }

    public String getGqCgPnrNo() {
        return gqCgPnrNo;
    }

    public void setGqCgPnrNo(String gqCgPnrNo) {
        this.gqCgPnrNo = gqCgPnrNo == null ? null : gqCgPnrNo.trim();
    }

    public String getGqCgHkgsPnr() {
        return gqCgHkgsPnr;
    }

    public void setGqCgHkgsPnr(String gqCgHkgsPnr) {
        this.gqCgHkgsPnr = gqCgHkgsPnr == null ? null : gqCgHkgsPnr.trim();
    }

    public String getGqCgHbh() {
        return gqCgHbh;
    }

    public void setGqCgHbh(String gqCgHbh) {
        this.gqCgHbh = gqCgHbh == null ? null : gqCgHbh.trim();
    }

    public String getGqCgCw() {
        return gqCgCw;
    }

    public void setGqCgCw(String gqCgCw) {
        this.gqCgCw = gqCgCw == null ? null : gqCgCw.trim();
    }

    public BigDecimal getGqXsfy() {
        return gqXsfy;
    }

    public void setGqXsfy(BigDecimal gqXsfy) {
        this.gqXsfy = gqXsfy;
    }

    public BigDecimal getGqCgfy() {
        return gqCgfy;
    }

    public void setGqCgfy(BigDecimal gqCgfy) {
        this.gqCgfy = gqCgfy;
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

	public List<JpGqdCjr> getCjrList() {
		return cjrList;
	}

	public void setCjrList(List<JpGqdCjr> cjrList) {
		this.cjrList = cjrList;
	}

	public List<JpGqdHd> getHdList() {
		return hdList;
	}

	public void setHdList(List<JpGqdHd> hdList) {
		this.hdList = hdList;
	}
	
	public String getGqCgly() {
        return gqCgly;
    }

    public void setGqCgly(String gqCgly) {
        this.gqCgly = gqCgly == null ? null : gqCgly.trim();
    }

    public String getGqCgdw() {
        return gqCgdw;
    }

    public void setGqCgdw(String gqCgdw) {
        this.gqCgdw = gqCgdw == null ? null : gqCgdw.trim();
    }

    public String getGqCgzt() {
        return gqCgzt;
    }

    public void setGqCgzt(String gqCgzt) {
        this.gqCgzt = gqCgzt == null ? null : gqCgzt.trim();
    }

    public String getGqCgDdbh() {
        return gqCgDdbh;
    }

    public void setGqCgDdbh(String gqCgDdbh) {
        this.gqCgDdbh = gqCgDdbh == null ? null : gqCgDdbh.trim();
    }

    public String getGqCgkm() {
        return gqCgkm;
    }

    public void setGqCgkm(String gqCgkm) {
        this.gqCgkm = gqCgkm == null ? null : gqCgkm.trim();
    }

    public String getBzbz() {
        return bzbz;
    }

    public void setBzbz(String bzbz) {
        this.bzbz = bzbz == null ? null : bzbz.trim();
    }

	public String getBpgqzt() {
		return bpgqzt;
	}

	public void setBpgqzt(String bpgqzt) {
		this.bpgqzt = bpgqzt;
	}

	public String getJcbs() {
		return jcbs;
	}

	public void setJcbs(String jcbs) {
		this.jcbs = jcbs;
	}

	public String getCgtjzt() {
		return cgtjzt;
	}

	public void setCgtjzt(String cgtjzt) {
		this.cgtjzt = cgtjzt;
	}

	public String getCgtjsbyy() {
		return cgtjsbyy;
	}

	public void setCgtjsbyy(String cgtjsbyy) {
		this.cgtjsbyy = cgtjsbyy;
	}

	public String getXsdzzt() {
		return xsdzzt;
	}

	public void setXsdzzt(String xsdzzt) {
		this.xsdzzt = xsdzzt;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}

	public String getWbgqzt() {
		return wbgqzt;
	}

	public void setWbgqzt(String wbgqzt) {
		this.wbgqzt = wbgqzt;
	}

	public String getWbddbh() {
		return wbddbh;
	}

	public void setWbddbh(String wbddbh) {
		this.wbddbh = wbddbh;
	}
	
	
}
