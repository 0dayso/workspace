package cn.vetech.vedsb.jp.entity.jpddgl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.excel.ExcelTitle;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

@Table(name="JP_KHDD")
public class JpKhdd  extends AbstractPageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7917700025401485069L;

	/**
	 * 订单编号
	 */
	@Id
    private String ddbh;

	/**
	 * 国内国际1国内，0国际
	 */
    private String gngj;

    /**
     * 商户编号
     */
    @ExcelTitle("商户编号")
    private String shbh;

    /**
     * 方案ID
     */
    @ExcelTitle("方案ID")
    private String faid;

    /**
     * 网店平台
     */
    @ExcelTitle("网店平台")
    private String wdpt;

    /**
     * 网店ID
     */
    @ExcelTitle("网店ID")
    private String wdid;

    /**
     * 网店产品类型普通、包机等
     */
    private String wdZclx;

    /**
     * '网店订单类型金牌服务等
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
     * 是否比价政策0否，1是
     */
    private String zcSfbj;

    /**
     * 政策投放ID
     */
    private String zcTfid;

    /**
     * 政策返点
     */
    private Double zcFd;

    /**
     * 政策留点
     */
    private Double zcLd;

    /**
     * 政策留钱
     */
    private Double zcLq;

    /**
     * 外部订单编号
     */
    private String wbdh;

    /**
     * NO位时间
     */
    private Date nosj;

    /**
     * 订单状态0待确认，1已订座，2出票中，3已完成,4客户消,5已取消
     */
    private String ddzt;

    /**
     * 出票锁单人
     */
    private String cpSdr;

    /**
     * 出票锁单时间
     */
    private Date cpSdsj;

    /**
     * 完成办理人
     */
    private String wcBlr;

    /**
     * 完成办理时间
     */
    private Date wcBlsj;

    /**
     * 收款状态0未收款，1已收款
     */
    private String skzt;

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
     * 是否邮寄行程单0否，1是
     */
    private String sfyjxcd;

    /**
     * 收件人
     */
    private String sjr;

    /**
     * 邮政编码
     */
    private String yzbm;

    /**
     * 邮寄地址
     */
    private String xjdz;

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
     * 航程类型1单程，2往返
     */
    private String hclx;

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
     * '销售舱位
     */
    private String xsCw;

    /**
     * 销售账单价
     */
    private Long xsZdj;

    /**
     * '销售价
     */
    private Double xsPj;

    /**
     * 销售机建
     */
    private Long xsJsf;

    /**
     * 销售税费
     */
    private Long xsTax;

    /**
     * 销售航意险份数
     */
    private Short xsHyxfs;

    /**
     * 销售延误险份数
     */
    private Short xsYwxfs;

    /**
     * 销售航意险利润
     */
    private Double xsHyxlr;

    /**
     * 销售延误险利润
     */
    private Double xsYwxlr;

    /**
     * 销售邮寄费
     */
    private Double xsYjf;

    /**
     * 销售金额
     */
    private Double xsJe;

    /**
     * 采购PNR_NO
     */
    private String cgPnrNo;

    /**
     * 采购PNR状态
     */
    private String cgPnrZt;

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
     * 采购账单价
     */
    private Long cgZdj;

    /**
     * '采购价
     */
    private Double cgPj;

    /**
     * 采购机建
     */
    private Long cgJsf;

    /**
     * 采购税费
     */
    private Long cgTax;

    /**
     * 采购来源
     */
    private String cgly;

    /**
     * 采购单位
     */
    private String cgdw;

    /**
     * 采购状态
     */
    private String cgzt;

    /**
     * 采购订单编号
     */
    private String cgDdbh;

    /**
     * 采购科目
     */
    private String cgkm;

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
     * 降舱标识空或0未降舱，1出票前降舱，2出票
     */
    private String jcbs;

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
     * '业务审核部门
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
     * 邮寄状态,0待打印，1待邮寄，2已邮寄
     */
    private String yjzt;
    
    /**
     * 采购邮寄费
     */
    private Double cgYjf;
    
    /**
     * 邮寄人
     */
    private String yjr;
    
    /**
     * 邮寄单号
     */
    private String yjdh;
    
    /**
     * 邮寄时间
     */
    private Date yjsj;
    
    /**
     * 备注
     */
    private String bzbz;
    
    /**
     * 紧急程度，0普，1急，2特，3新
     */
    private String jjcd;
    /**
     * 航司二字码
     */
    private String hkgs;
    /**
     * 是否手工单，1为手工单，0为非手工单
     */
    private String sfsgd;
    //打印人
    private String dyr;
    /**
     * 打印时间
     */
    private Date dysj;
    
    private String dxzt;//0表示未发送,1表示已发送.
    
    private String xsdzzt;//销售对账状态：1已对账，且对比正确，0或空为未对比
    
    private String cgZflsh;//采购支付流水号
    
    private String pnrLr;//pnr内容
    
    private String patLr;//pat内容
    
    private String dpOffice;//订票office
    
    private String sfwbcpz;  //是否外部出票中，1是，0和空为否，2出票成功， 导单时写入，扫描出票失败为改为3  4，获取票号异常
    
    /**
     * 外部订单状态，目前仅存淘宝外部订单状态
     * 淘宝
     * 订单状态定义==> 0:未付款;(注：支持保险分润时，订单已付款，保险未付款也为0)1:处理中;2:确定出票;3:预定成功;4:预定失败;5:处理中超时失效;6:支付超时失效;7:买家取消
     * 
     */
    private String wbddzt;
    
    
    /**
     * 销售佣金
     */
    private Double xsYj;
    
    /**
     * 是否补位成功.订单导入系统编码是no位状态,调补位接口及时补位,1补位成功，2补位失败
     */
    private String sfbwcg;
    
    private String ddxh;
    
    //
    @Transient
    private JpKhddYj jpKhddyj;
    
    //这个字段在表中没有，只是为了查询
    @Transient
    private String startTime;
    //这个字段在表中没有，只是为了查询
    @Transient
    private String endTime;
    
    @Transient
    private String newPrice;
    
    @Transient
    private String newCw;
    
    public static final String DDZT_YGZ = "1";
    
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

    public String getZcSfbj() {
        return zcSfbj;
    }

    public void setZcSfbj(String zcSfbj) {
        this.zcSfbj = zcSfbj == null ? null : zcSfbj.trim();
    }

    public String getZcTfid() {
        return zcTfid;
    }

    public void setZcTfid(String zcTfid) {
        this.zcTfid = zcTfid == null ? null : zcTfid.trim();
    }

    public Double getZcFd() {
        return zcFd;
    }

    public void setZcFd(Double zcFd) {
        this.zcFd = zcFd;
    }

    public Double getZcLd() {
        return zcLd;
    }

    public void setZcLd(Double zcLd) {
        this.zcLd = zcLd;
    }

    public Double getZcLq() {
        return zcLq;
    }

    public void setZcLq(Double zcLq) {
        this.zcLq = zcLq;
    }

    public String getWbdh() {
        return wbdh;
    }

    public void setWbdh(String wbdh) {
        this.wbdh = wbdh == null ? null : wbdh.trim();
    }

    public Date getNosj() {
        return nosj;
    }

    public void setNosj(Date nosj) {
        this.nosj = nosj;
    }

    public String getDdzt() {
        return ddzt;
    }

    public void setDdzt(String ddzt) {
        this.ddzt = ddzt == null ? null : ddzt.trim();
    }

    public String getCpSdr() {
        return cpSdr;
    }

    public void setCpSdr(String cpSdr) {
        this.cpSdr = cpSdr == null ? null : cpSdr.trim();
    }

    public Date getCpSdsj() {
        return cpSdsj;
    }

    public void setCpSdsj(Date cpSdsj) {
        this.cpSdsj = cpSdsj;
    }

    public String getWcBlr() {
        return wcBlr;
    }

    public void setWcBlr(String wcBlr) {
        this.wcBlr = wcBlr == null ? null : wcBlr.trim();
    }

    public Date getWcBlsj() {
        return wcBlsj;
    }

    public void setWcBlsj(Date wcBlsj) {
        this.wcBlsj = wcBlsj;
    }

    public String getSkzt() {
        return skzt;
    }

    public void setSkzt(String skzt) {
        this.skzt = skzt == null ? null : skzt.trim();
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

    public String getSfyjxcd() {
        return sfyjxcd;
    }

    public void setSfyjxcd(String sfyjxcd) {
        this.sfyjxcd = sfyjxcd == null ? null : sfyjxcd.trim();
    }

    public String getSjr() {
        return sjr;
    }

    public void setSjr(String sjr) {
        this.sjr = sjr == null ? null : sjr.trim();
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm == null ? null : yzbm.trim();
    }

    public String getXjdz() {
        return xjdz;
    }

    public void setXjdz(String xjdz) {
        this.xjdz = xjdz == null ? null : xjdz.trim();
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

    public String getHclx() {
        return hclx;
    }

    public void setHclx(String hclx) {
        this.hclx = hclx == null ? null : hclx.trim();
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

    public Long getXsZdj() {
        return xsZdj;
    }

    public void setXsZdj(Long xsZdj) {
        this.xsZdj = xsZdj;
    }

    public Double getXsPj() {
        return xsPj;
    }

    public void setXsPj(Double xsPj) {
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

    public Short getXsHyxfs() {
        return xsHyxfs;
    }

    public void setXsHyxfs(Short xsHyxfs) {
        this.xsHyxfs = xsHyxfs;
    }

    public Short getXsYwxfs() {
        return xsYwxfs;
    }

    public void setXsYwxfs(Short xsYwxfs) {
        this.xsYwxfs = xsYwxfs;
    }

    public Double getXsHyxlr() {
        return xsHyxlr;
    }

    public void setXsHyxlr(Double xsHyxlr) {
        this.xsHyxlr = xsHyxlr;
    }

    public Double getXsYwxlr() {
        return xsYwxlr;
    }

    public void setXsYwxlr(Double xsYwxlr) {
        this.xsYwxlr = xsYwxlr;
    }

    public Double getXsYjf() {
        return xsYjf;
    }

    public void setXsYjf(Double xsYjf) {
        this.xsYjf = xsYjf;
    }

    public Double getXsJe() {
        return xsJe;
    }

    public void setXsJe(Double xsJe) {
        this.xsJe = xsJe;
    }

    public String getCgPnrNo() {
        return cgPnrNo;
    }

    public void setCgPnrNo(String cgPnrNo) {
        this.cgPnrNo = cgPnrNo == null ? null : cgPnrNo.trim();
    }

    public String getCgPnrZt() {
        return cgPnrZt;
    }

    public void setCgPnrZt(String cgPnrZt) {
        this.cgPnrZt = cgPnrZt == null ? null : cgPnrZt.trim();
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

    public Long getCgZdj() {
        return cgZdj;
    }

    public void setCgZdj(Long cgZdj) {
        this.cgZdj = cgZdj;
    }

    public Double getCgPj() {
        return cgPj;
    }

    public void setCgPj(Double cgPj) {
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

    public String getCgly() {
        return cgly;
    }

    public void setCgly(String cgly) {
        this.cgly = cgly == null ? null : cgly.trim();
    }

    public String getCgdw() {
        return cgdw;
    }

    public void setCgdw(String cgdw) {
        this.cgdw = cgdw == null ? null : cgdw.trim();
    }

    public String getCgzt() {
        return cgzt;
    }

    public void setCgzt(String cgzt) {
        this.cgzt = cgzt == null ? null : cgzt.trim();
    }

    public String getCgDdbh() {
        return cgDdbh;
    }

    public void setCgDdbh(String cgDdbh) {
        this.cgDdbh = cgDdbh == null ? null : cgDdbh.trim();
    }

    public String getCgkm() {
        return cgkm;
    }

    public void setCgkm(String cgkm) {
        this.cgkm = cgkm == null ? null : cgkm.trim();
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

    public String getJcbs() {
        return jcbs;
    }

    public void setJcbs(String jcbs) {
        this.jcbs = jcbs == null ? null : jcbs.trim();
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
    
    
    public String getYjzt() {
		return yjzt;
	}

	public void setYjzt(String yjzt) {
		this.yjzt = yjzt;
	}

	public Double getCgYjf() {
		return cgYjf;
	}

	public void setCgYjf(Double cgyjf) {
		this.cgYjf = cgyjf;
	}

	public String getYjr() {
		return yjr;
	}

	public void setYjr(String yjr) {
		this.yjr = yjr;
	}

	public Date getYjsj() {
		return yjsj;
	}

	public void setYjsj(Date yjsj) {
		this.yjsj = yjsj;
	}

	@Transient
	public String getDdsjStr(){
		String ddsjLong = VeDate.dateToStrLong(this.ddsj);
		if(StringUtils.isNotBlank(ddsjLong) && ddsjLong.length()>17){
			ddsjLong = ddsjLong.substring(5,16);
		}
		return ddsjLong;
	}
    
    @Transient
	public String getCfrqStr(){
		String cfrqLong = VeDate.dateToStrLong(this.cfrq);
		if(StringUtils.isNotBlank(cfrqLong) && cfrqLong.length()>17){
			cfrqLong = cfrqLong.substring(5,16);
		}
		return cfrqLong;
	}
    
    @Transient
	public String getNosjStr(){
		String nosjLong = VeDate.dateToStrLong(this.nosj);
		if(StringUtils.isNotBlank(nosjLong) && nosjLong.length()>17){
			nosjLong = nosjLong.substring(5,16);
		}
		return nosjLong;
	}
    
    @Transient
	public String getCpSdsjStr(){
		String cpSdsjLong = VeDate.dateToStrLong(this.cpSdsj);
		if(StringUtils.isNotBlank(cpSdsjLong) && cpSdsjLong.length()>17){
			cpSdsjLong = cpSdsjLong.substring(5,16);
		}
		return cpSdsjLong;
	}
    @Transient
	public Date getDysj() {
		return dysj;
	}
	
	public void setDysj(Date dysj) {
		this.dysj = dysj;
	}
	
	@Transient
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Transient
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getYjdh() {
		return yjdh;
	}

	public void setYjdh(String yjdh) {
		this.yjdh = yjdh;
	}

	public JpKhddYj getJpKhddyj() {
		return jpKhddyj;
	}

	public void setJpKhddyj(JpKhddYj jpKhddyj) {
		this.jpKhddyj = jpKhddyj;
	}

	public String getJjcd() {
		return jjcd;
	}

	public void setJjcd(String jjcd) {
		this.jjcd = jjcd;
	}

	public String getHkgs() {
		return hkgs;
	}

	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}

	public String getSfsgd() {
		return sfsgd;
	}

	public void setSfsgd(String sfsgd) {
		this.sfsgd = sfsgd;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}
	
	public String getBzbz() {
		return this.bzbz;
	}

	public String getDyr() {
		return dyr;
	}

	public void setDyr(String dyr) {
		this.dyr = dyr;
	}

	public String getDxzt() {
		return dxzt;
	}

	public void setDxzt(String dxzt) {
		this.dxzt = dxzt;
	}

	public String getXsdzzt() {
		return xsdzzt;
	}

	public void setXsdzzt(String xsdzzt) {
		this.xsdzzt = xsdzzt;
	}

	public String getPnrLr() {
		return pnrLr;
	}

	public void setPnrLr(String pnrLr) {
		this.pnrLr = pnrLr;
	}

	public String getPatLr() {
		return patLr;
	}

	public void setPatLr(String patLr) {
		this.patLr = patLr;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getDpOffice() {
		return dpOffice;
	}

	public void setDpOffice(String dpOffice) {
		this.dpOffice = dpOffice;
	}

	public String getCgZflsh() {
		return cgZflsh;
	}

	public void setCgZflsh(String cgZflsh) {
		this.cgZflsh = cgZflsh;
	}

	public String getSfwbcpz() {
		return sfwbcpz;
	}

	public void setSfwbcpz(String sfwbcpz) {
		this.sfwbcpz = sfwbcpz;
	}

	public String getWbddzt() {
		return wbddzt;
	}

	public void setWbddzt(String wbddzt) {
		this.wbddzt = wbddzt;
	}

	public String getNewCw() {
		return newCw;
	}

	public void setNewCw(String newCw) {
		this.newCw = newCw;
	}

	public String getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}

	public Double getXsYj() {
		return xsYj;
	}

	public void setXsYj(Double xsYj) {
		this.xsYj = xsYj;
	}

	public String getSfbwcg() {
		return sfbwcg;
	}

	public void setSfbwcg(String sfbwcg) {
		this.sfbwcg = sfbwcg;
	}

	public String getDdxh() {
		return ddxh;
	}

	public void setDdxh(String ddxh) {
		this.ddxh = ddxh;
	}
	
}