package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.service.BYdlog;

/**
 * @author win7
 *
 */
/**
 * @author win7
 *
 */
@Table(name="wd_xsfa")
public class WdXsfa extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String wdid;
	@BYdlog(name="方案名称",logView=1)
	private String famc;
	private String hclx;// 1 单程 2往返
	private String wdcplx;
	@BYdlog(name="政策渠道",logView=1)
	private String zcqd;//正常渠道，多个用/隔开，---表示全部
	@BYdlog(name="最低票价",logView=1)
	private Double zdpj;
	@BYdlog(name="最高票价",logView=1)
	private Double zgpj;
	@BYdlog(name="最低返点",logView=1)
	private Double zdfd;
	@BYdlog(name="最高返点",logView=1)
	private Double zgfd;
	@BYdlog(name="乘机日期始",logView=1)
	private Date cjrqs;
	@BYdlog(name="乘机日期止",logView=1)
	private Date cjrqz;
	private Integer gw_zwxz;
	@BYdlog(name="FD差价金额限制",logView=1)
	private Double gw_cj;
	
	private Integer qjd_zwxz;
	private Double qjd_cj;
	private Double qjd_cjz;
	private Double gw_cjz;
	private Double b2t_cjz;
	
	private Double b2t_cj;
	private Integer b2t_zwxz;
	
	@NotBlank
	@BYdlog(name="舱位等级",logView=1)
	private String cwdj;
	@NotBlank
	@BYdlog(name="是否过滤子舱位",logView=1)
	private String glzcw;
	@NotBlank
	@BYdlog(name="只投共享",logView=1)
	private String isgx;
	@NotBlank
	@BYdlog(name="任务执行时间始",logView=1)
	private String zxsjs;
	@NotBlank
	@BYdlog(name="任务执行时间止",logView=1)
	private String zxsjz;
	@NotBlank
	@BYdlog(name="商户编号",logView=1)
	private String shbh;
	@NotBlank
	@BYdlog(name="状态",logView=1)
	private String zt;
	@NotNull
	private Date cjsj;
	@NotBlank
	private String cjyh;
	private String sfbj;
	@Transient
	private String cjyhxm;
	private Date xgsj;
	@NotBlank
	private String xgyh;
	@Transient
	private String xgyhxm;
	@Transient
	private String xgyhxma;
	@Formula("(SELECT wdmc wdid FROM wd_zlsz where id=entity.wdid)") 
	private String wdmc;
	private String wdpt;
	@Formula("(select mc wdpt from ve_class where id = entity.wdpt)") 
	private String wdptmc;
	@Transient
	private String cplxmc;
	private String zcqdmc;
	private String zcdmqz;
	@Transient
	private String hkgs;
	@Transient
	private String hkgscp;
	private String tfzclx;
	@Transient
	private String result;
	@Transient
	private String copyerror;
	private Date zhtfsj;
	private String zhtffs;
	private Integer zhtfzcl;
	@Transient
	private String sfszkrgz;
	@Transient
	@BYdlog(name="状态",logView=1)
	private String zts;
	private String zhtfsm;
	private String famm;
	private String zcfbr;
	private String zcmc;
	
	@Transient
	private String ztlx;
	@Transient
	private String yhqxjb;
	@Transient
	private String yhbh;
	@Transient
	private String yhfzid;//当前登陆用户分组id
	@Transient
	private String yhbmid;//当前登陆用户部门id
	
	@BYdlog(name="是否过滤换编码政策",logView=1)
	private String glhbmzc;
	private String czpt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	public String getFamc() {
		return famc;
	}
	public void setFamc(String famc) {
		this.famc = famc;
	}
	public Double getQjd_cjz() {
		return qjd_cjz;
	}
	public void setQjd_cjz(Double qjd_cjz) {
		this.qjd_cjz = qjd_cjz;
	}
	public Double getGw_cjz() {
		return gw_cjz;
	}
	public void setGw_cjz(Double gw_cjz) {
		this.gw_cjz = gw_cjz;
	}
	public String getHclx() {
		return hclx;
	}
	public void setHclx(String hclx) {
		this.hclx = hclx;
	}
	public String getWdcplx() {
		return wdcplx;
	}
	public void setWdcplx(String wdcplx) {
		this.wdcplx = wdcplx;
	}
	public String getZcqd() {
		return zcqd;
	}
	public void setZcqd(String zcqd) {
		this.zcqd = zcqd;
	}
	public Double getZdpj() {
		return zdpj;
	}
	public void setZdpj(Double zdpj) {
		this.zdpj = zdpj;
	}
	public Double getZgpj() {
		return zgpj;
	}
	public void setZgpj(Double zgpj) {
		this.zgpj = zgpj;
	}
	public Double getZdfd() {
		return zdfd;
	}
	public void setZdfd(Double zdfd) {
		this.zdfd = zdfd;
	}
	public Double getZgfd() {
		return zgfd;
	}
	public void setZgfd(Double zgfd) {
		this.zgfd = zgfd;
	}
	public Integer getGw_zwxz() {
		return gw_zwxz;
	}
	public void setGw_zwxz(Integer gw_zwxz) {
		this.gw_zwxz = gw_zwxz;
	}
	public Double getGw_cj() {
		return gw_cj;
	}
	public void setGw_cj(Double gw_cj) {
		this.gw_cj = gw_cj;
	}
	public Date getCjrqs() {
		return cjrqs;
	}
	public void setCjrqs(Date cjrqs) {
		this.cjrqs = cjrqs;
	}
	public Date getCjrqz() {
		return cjrqz;
	}
	public void setCjrqz(Date cjrqz) {
		this.cjrqz = cjrqz;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getCjyh() {
		return cjyh;
	}
	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
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
		this.xgyh = xgyh;
	}
	public String getCwdj() {
		return cwdj;
	}
	public void setCwdj(String cwdj) {
		this.cwdj = cwdj;
	}
	public String getZxsjs() {
		return zxsjs;
	}
	public void setZxsjs(String zxsjs) {
		this.zxsjs = zxsjs;
	}
	public String getZxsjz() {
		return zxsjz;
	}
	public void setZxsjz(String zxsjz) {
		this.zxsjz = zxsjz;
	}
	public String getWdmc() {
		return wdmc;
	}
	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
	}
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}
	public String getCplxmc() {
		return cplxmc;
	}
	public void setCplxmc(String cplxmc) {
		this.cplxmc = cplxmc;
	}
	public String getZcqdmc() {
		return zcqdmc;
	}
	public void setZcqdmc(String zcqdmc) {
		this.zcqdmc = zcqdmc;
	}
	@Transient
	public String getZtStr(){
		String res = "";
		if(StringUtils.isNoneBlank(this.zt)){
			res = "1".equals(this.zt) ? "启用" : "停用";
		}
		return res;
	}
	@Transient
	public String getCjsjStr(){
		String cjsjLong = VeDate.dateToStrLong(this.cjsj);
		if(StringUtils.isNotBlank(cjsjLong) && cjsjLong.length()>17){
			cjsjLong = cjsjLong.substring(5,16);
		}
		return cjsjLong;	
	}
	@Transient
	public String getXgsjStr(){
		String xgsjLong = VeDate.dateToStrLong(this.xgsj);
		if(StringUtils.isNotBlank(xgsjLong) && xgsjLong.length()>17){
			xgsjLong = xgsjLong.substring(5,16);
		}
		return xgsjLong;
	}
	public String getWdptmc() {
		return wdptmc;
	}
	public void setWdptmc(String wdptmc) {
		this.wdptmc = wdptmc;
	}
	public String getCjrqsStr(){
		return VeDate.dateToStr(this.cjrqs);
	}
	public String getCjrqzStr(){
		return VeDate.dateToStr(this.cjrqz);
	}
	public String getZcdmqz() {
		return zcdmqz;
	}
	public void setZcdmqz(String zcdmqz) {
		this.zcdmqz = zcdmqz;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getHkgscp() {
		return hkgscp;
	}
	public void setHkgscp(String hkgscp) {
		this.hkgscp = hkgscp;
	}
	public String getTfzclx() {
		return tfzclx;
	}
	public void setTfzclx(String tfzclx) {
		this.tfzclx = tfzclx;
	}
	public String getCjyhxm() {
		return cjyhxm;
	}
	public void setCjyhxm(String cjyhxm) {
		this.cjyhxm = cjyhxm;
	}
	public String getXgyhxm() {
		return xgyhxm;
	}
	public void setXgyhxm(String xgyhxm) {
		this.xgyhxm = xgyhxm;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCopyerror() {
		return copyerror;
	}
	public void setCopyerror(String copyerror) {
		this.copyerror = copyerror;
	}
	public Date getZhtfsj() {
		return zhtfsj;
	}
	public void setZhtfsj(Date zhtfsj) {
		this.zhtfsj = zhtfsj;
	}
	public String getZhtffs() {
		return zhtffs;
	}
	public void setZhtffs(String zhtffs) {
		this.zhtffs = zhtffs;
	}
	public Integer getZhtfzcl() {
		return zhtfzcl;
	}
	public void setZhtfzcl(Integer zhtfzcl) {
		this.zhtfzcl = zhtfzcl;
	}
	//最后投放时间
	@Transient
	public String getZhtfsjStr(){
		return VeDate.dateToStrLong(this.zhtfsj);
	}
	
	public String getSfszkrgz() {
		return sfszkrgz;
	}
	public void setSfszkrgz(String sfszkrgz) {
		this.sfszkrgz = sfszkrgz;
	}
	public String getZgpjStr(){
		String res = Double.toString(this.zgpj);
		if(res.indexOf(".0")>-1){
			res = res.replace(".0", "");
		}
		return res;
	}
	public String getZdpjStr(){
		String res = Double.toString(this.zdpj);
		if(res.indexOf(".0")>-1){
			res = res.replace(".0", "");
		}
		return res;
	}
	@Transient
	public Double getZdfdDouble(){
		if(null!=this.zdfd){
			BigDecimal   b   =   new   BigDecimal(this.zdfd*100d);  
			double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		    return f1;
		}
		return null;
	}
	@Transient
	public Double getZgfdDouble(){
		if(null!=this.zgfd){
			BigDecimal   b   =   new   BigDecimal(this.zgfd*100d);  
			double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		    return f1;
		}
		return null;
	}
	public String getZts() {
		return zts;
	}
	public void setZts(String zts) {
		this.zts = zts;
	}
	public String getZhtfsm() {
		return zhtfsm;
	}
	public void setZhtfsm(String zhtfsm) {
		this.zhtfsm = zhtfsm;
	}
	public String getXgyhxma() {
		return xgyhxma;
	}
	public void setXgyhxma(String xgyhxma) {
		this.xgyhxma = xgyhxma;
	}
	public String getFamm() {
		return famm;
	}
	public void setFamm(String famm) {
		this.famm = famm;
	}
	public String getZcfbr() {
		return zcfbr;
	}
	public void setZcfbr(String zcfbr) {
		this.zcfbr = zcfbr;
	}
	public String getZcmc() {
		return zcmc;
	}
	public void setZcmc(String zcmc) {
		this.zcmc = zcmc;
	}
	public String getZtlx() {
		return ztlx;
	}
	public void setZtlx(String ztlx) {
		this.ztlx = ztlx;
	}
	public String getYhqxjb() {
		return yhqxjb;
	}
	public void setYhqxjb(String yhqxjb) {
		this.yhqxjb = yhqxjb;
	}
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	public String getYhfzid() {
		return yhfzid;
	}
	public void setYhfzid(String yhfzid) {
		this.yhfzid = yhfzid;
	}
	public String getYhbmid() {
		return yhbmid;
	}
	public void setYhbmid(String yhbmid) {
		this.yhbmid = yhbmid;
	}
	public String getGlhbmzc() {
		return glhbmzc;
	}
	public void setGlhbmzc(String glhbmzc) {
		this.glhbmzc = glhbmzc;
	}
	public String getCzpt() {
		return czpt;
	}
	public void setCzpt(String czpt) {
		this.czpt = czpt;
	}
	public Integer getQjd_zwxz() {
		return qjd_zwxz;
	}
	public void setQjd_zwxz(Integer qjd_zwxz) {
		this.qjd_zwxz = qjd_zwxz;
	}
	public Double getQjd_cj() {
		return qjd_cj;
	}
	public void setQjd_cj(Double qjd_cj) {
		this.qjd_cj = qjd_cj;
	}
	public String getSfbj() {
		return sfbj;
	}
	public void setSfbj(String sfbj) {
		this.sfbj = sfbj;
	}
	public Double getB2t_cjz() {
		return b2t_cjz;
	}
	public void setB2t_cjz(Double b2t_cjz) {
		this.b2t_cjz = b2t_cjz;
	}
	public Double getB2t_cj() {
		return b2t_cj;
	}
	public void setB2t_cj(Double b2t_cj) {
		this.b2t_cj = b2t_cj;
	}
	public Integer getB2t_zwxz() {
		return b2t_zwxz;
	}
	public void setB2t_zwxz(Integer b2t_zwxz) {
		this.b2t_zwxz = b2t_zwxz;
	}
	public String getGlzcw() {
		return glzcw;
	}
	public void setGlzcw(String glzcw) {
		this.glzcw = glzcw;
	}
	public String getIsgx() {
		return isgx;
	}
	public void setIsgx(String isgx) {
		this.isgx = isgx;
	}
}
