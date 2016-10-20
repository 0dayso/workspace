package cn.vetech.vedsb.platpolicy.jzcp.b2c.bean;

import java.util.Date;

public class Order {
    
    private String ddbh; //订单编号 
    
    private String xdbh; //下单编号8000
    
    private String shbh; //商户编号
    
    private String hsddh; //航司订单编号
    
    private String userid; //操作人ID
    
    private Date cjrq; //乘机日期       
    
    private String hbh; //航班号
    
    private String cw; //舱位
    
    private String ddzt; //订单状态   (0.待处理  1.处理中   2.已下单待支付  3.已支付待出票 4.已出票  5.下单失败   6.取消订单)
    
    private String zxzt; //任务执行状态(0.登陆 1.登陆失败 2.查询航班 3.查询航班失败 4.生成订单 5.生成订单失败 6.支付中 7.支付失败 8.票号回填 9.票号回填失败  10.订单已取消 11.订单取消失败)
    
    private Date xgsj; //修改时间
    
    private Date xdkssj; //下单开始时间
    
    private Date xdwcsj; //下单完成时间
    
    private String dgly; //代购来源 
    
    private Integer xdhs; //下单耗时
    
    private String zxip; //执行ip
    
    private String jqbs; //接收订单的机器标示 扫描任务的时候扫描自己接收到的任务
    
    private String lxr; //联系人
    
    private String lxrsj; //联系人手机
    
    private String lxryx; //联系人邮箱
    
    private String zfpt; //支付平台
    
    private String zfzh; //支付账号
    
    private String zfxx; //支付信息（加密）
    
    private String zdzf; //自动支付 0-自动 1-手动
    
    private String qfsj; //起飞时间
    
    private String ddsj; //到达时间
    
    private String xsjg; //销售价格
    
    private Double zfje; //支付金额
    
    private String hszh; //航司账号
    
    private String hsmm; //航司密码
    
    private String sfjk; //是否用接口（1接口下单  0正常下单）
    
    private String zfzt; //支付状态
    
    private String hkgs; //航空公司
    
    private String cfcs; //出发城市
    
    private String ddcs; //到达城市
    
    private String ship; //商户ip，（回填票号  ip+端口号）
    
    private String dlfs; //登陆方式
    
    private String pnr; //pnr编码
    
    private String errormsg; //下单错误信息
    
    private String zflsh; //支付流水号  自动代扣支付流水号
    
    public String getDdbh() {
        return ddbh;
    }
    
    public void setDdbh(String ddbh) {
        this.ddbh = ddbh;
    }
    
    public String getXdbh() {
        return xdbh;
    }
    
    public void setXdbh(String xdbh) {
        this.xdbh = xdbh;
    }
    
    public String getShbh() {
        return shbh;
    }
    
    public void setShbh(String shbh) {
        this.shbh = shbh;
    }
    
    public String getHsddh() {
        return hsddh;
    }
    
    public void setHsddh(String hsddh) {
        this.hsddh = hsddh;
    }
    
    public String getUserid() {
        return userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public Date getCjrq() {
        return cjrq;
    }
    
    public void setCjrq(Date cjrq) {
        this.cjrq = cjrq;
    }
    
    public String getHbh() {
        return hbh;
    }
    
    public void setHbh(String hbh) {
        this.hbh = hbh;
    }
    
    public String getCw() {
        return cw;
    }
    
    public void setCw(String cw) {
        this.cw = cw;
    }
    
    public String getDdzt() {
        return ddzt;
    }
    
    public void setDdzt(String ddzt) {
        this.ddzt = ddzt;
    }
    
    public String getZxzt() {
        return zxzt;
    }
    
    public void setZxzt(String zxzt) {
        this.zxzt = zxzt;
    }
    
    public Date getXgsj() {
        return xgsj;
    }
    
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }
    
    public Date getXdkssj() {
        return xdkssj;
    }
    
    public void setXdkssj(Date xdkssj) {
        this.xdkssj = xdkssj;
    }
    
    public Date getXdwcsj() {
        return xdwcsj;
    }
    
    public void setXdwcsj(Date xdwcsj) {
        this.xdwcsj = xdwcsj;
    }
    
    public String getDgly() {
        return dgly;
    }
    
    public void setDgly(String dgly) {
        this.dgly = dgly;
    }
    
    public Integer getXdhs() {
        return xdhs;
    }
    
    public void setXdhs(Integer xdhs) {
        this.xdhs = xdhs;
    }
    
    public String getZxip() {
        return zxip;
    }
    
    public void setZxip(String zxip) {
        this.zxip = zxip;
    }
    
    public String getJqbs() {
        return jqbs;
    }
    
    public void setJqbs(String jqbs) {
        this.jqbs = jqbs;
    }
    
    public String getLxr() {
        return lxr;
    }
    
    public void setLxr(String lxr) {
        this.lxr = lxr;
    }
    
    public String getLxrsj() {
        return lxrsj;
    }
    
    public void setLxrsj(String lxrsj) {
        this.lxrsj = lxrsj;
    }
    
    public String getLxryx() {
        return lxryx;
    }
    
    public void setLxryx(String lxryx) {
        this.lxryx = lxryx;
    }
    
    public String getZfpt() {
        return zfpt;
    }
    
    public void setZfpt(String zfpt) {
        this.zfpt = zfpt;
    }
    
    public String getZfzh() {
        return zfzh;
    }
    
    public void setZfzh(String zfzh) {
        this.zfzh = zfzh;
    }
    
    public String getZfxx() {
        return zfxx;
    }
    
    public void setZfxx(String zfxx) {
        this.zfxx = zfxx;
    }
    
    public String getZdzf() {
        return zdzf;
    }
    
    public void setZdzf(String zdzf) {
        this.zdzf = zdzf;
    }
    
    public String getQfsj() {
        return qfsj;
    }
    
    public void setQfsj(String qfsj) {
        this.qfsj = qfsj;
    }
    
    public String getDdsj() {
        return ddsj;
    }
    
    public void setDdsj(String ddsj) {
        this.ddsj = ddsj;
    }
    
    public String getXsjg() {
        return xsjg;
    }
    
    public void setXsjg(String xsjg) {
        this.xsjg = xsjg;
    }
    
    public Double getZfje() {
        return zfje;
    }
    
    public void setZfje(Double zfje) {
        this.zfje = zfje;
    }
    
    public String getHszh() {
        return hszh;
    }
    
    public void setHszh(String hszh) {
        this.hszh = hszh;
    }
    
    public String getHsmm() {
        return hsmm;
    }
    
    public void setHsmm(String hsmm) {
        this.hsmm = hsmm;
    }
    
    public String getSfjk() {
        return sfjk;
    }
    
    public void setSfjk(String sfjk) {
        this.sfjk = sfjk;
    }
    
    public String getZfzt() {
        return zfzt;
    }
    
    public void setZfzt(String zfzt) {
        this.zfzt = zfzt;
    }
    
    public String getHkgs() {
        return hkgs;
    }
    
    public void setHkgs(String hkgs) {
        this.hkgs = hkgs;
    }
    
    public String getCfcs() {
        return cfcs;
    }
    
    public void setCfcs(String cfcs) {
        this.cfcs = cfcs;
    }
    
    public String getDdcs() {
        return ddcs;
    }
    
    public void setDdcs(String ddcs) {
        this.ddcs = ddcs;
    }
    
    public String getShip() {
        return ship;
    }
    
    public void setShip(String ship) {
        this.ship = ship;
    }
    
    public String getDlfs() {
        return dlfs;
    }
    
    public void setDlfs(String dlfs) {
        this.dlfs = dlfs;
    }
    
    public String getPnr() {
        return pnr;
    }
    
    public void setPnr(String pnr) {
        this.pnr = pnr;
    }
    
    public String getErrormsg() {
        return errormsg;
    }
    
    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }
    
    public String getZflsh() {
        return zflsh;
    }
    
    public void setZflsh(String zflsh) {
        this.zflsh = zflsh;
    }
    
}
