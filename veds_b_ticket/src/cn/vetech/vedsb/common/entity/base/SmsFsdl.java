package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "sms_fsdl")
public class SmsFsdl extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = -172062338507235656L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	//分类1表示平台发送的2表示商户发送的
    private String fl;
    //发送操作人工号加姓名
    private String fsCzr;
    //发送单位有可能是平台，有可能是商户
    private String fsDw;
    //发送内容
    private String fsnr;
    //模板编号
    private String mbbh;
    //接收手机号码
    private String jshm;
    //发送时间通道发送的时间
    private Date fssj;
    //创建时间
    private Date cjsj;
    //要求发送时间
    private Date yqfssj;
    //发送方式1自动发送0手动发送
    private String fsfs;
    //短信类型
    private String dxlx;
    //状态
    private String zt;
    //订单编号
    private String ddbh;

    private String pnrNo;
    //短信金额
    private BigDecimal dxje;
    //短信发送条数
    private Short dxfsts;
    //是否已签名0没有1有
    private String ifqm;
    //网关返回ID
    private String dxid;
    //促销活动提醒方式ID
    private String txfsid;
    //短信接收商户编号
    private String jsShbh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl == null ? null : fl.trim();
    }

    public String getFsCzr() {
        return fsCzr;
    }

    public void setFsCzr(String fsCzr) {
        this.fsCzr = fsCzr == null ? null : fsCzr.trim();
    }

    public String getFsDw() {
        return fsDw;
    }

    public void setFsDw(String fsDw) {
        this.fsDw = fsDw == null ? null : fsDw.trim();
    }

    public String getFsnr() {
        return fsnr;
    }

    public void setFsnr(String fsnr) {
        this.fsnr = fsnr == null ? null : fsnr.trim();
    }

    public String getMbbh() {
        return mbbh;
    }

    public void setMbbh(String mbbh) {
        this.mbbh = mbbh == null ? null : mbbh.trim();
    }

    public String getJshm() {
        return jshm;
    }

    public void setJshm(String jshm) {
        this.jshm = jshm == null ? null : jshm.trim();
    }

    public Date getFssj() {
        return fssj;
    }

    public void setFssj(Date fssj) {
        this.fssj = fssj;
    }

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public Date getYqfssj() {
        return yqfssj;
    }

    public void setYqfssj(Date yqfssj) {
        this.yqfssj = yqfssj;
    }

    public String getFsfs() {
        return fsfs;
    }

    public void setFsfs(String fsfs) {
        this.fsfs = fsfs == null ? null : fsfs.trim();
    }

    public String getDxlx() {
        return dxlx;
    }

    public void setDxlx(String dxlx) {
        this.dxlx = dxlx == null ? null : dxlx.trim();
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt == null ? null : zt.trim();
    }

    public String getDdbh() {
        return ddbh;
    }

    public void setDdbh(String ddbh) {
        this.ddbh = ddbh == null ? null : ddbh.trim();
    }

    public String getPnrNo() {
        return pnrNo;
    }

    public void setPnrNo(String pnrNo) {
        this.pnrNo = pnrNo == null ? null : pnrNo.trim();
    }

    public BigDecimal getDxje() {
        return dxje;
    }

    public void setDxje(BigDecimal dxje) {
        this.dxje = dxje;
    }

    public Short getDxfsts() {
        return dxfsts;
    }

    public void setDxfsts(Short dxfsts) {
        this.dxfsts = dxfsts;
    }

    public String getIfqm() {
        return ifqm;
    }

    public void setIfqm(String ifqm) {
        this.ifqm = ifqm == null ? null : ifqm.trim();
    }

    public String getDxid() {
        return dxid;
    }

    public void setDxid(String dxid) {
        this.dxid = dxid == null ? null : dxid.trim();
    }

    public String getTxfsid() {
        return txfsid;
    }

    public void setTxfsid(String txfsid) {
        this.txfsid = txfsid == null ? null : txfsid.trim();
    }

    public String getJsShbh() {
        return jsShbh;
    }

    public void setJsShbh(String jsShbh) {
        this.jsShbh = jsShbh == null ? null : jsShbh.trim();
    }
}