package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Formula;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

/**
 * 网店资料设置
 * 
 * @author lishanliang
 * 
 */
@Table(name = "wd_zlsz")
public class Wdzlsz extends AbstractPageEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3144093823414275202L;

	@Id
	private String id;// ID号主键

	private String ywlx;// 业务类型国内机票、国际机票、酒店等

	private String wdmc;// 网店名称

	private String shbh;// 商户编号

	private String wdpt;// 网店平台10100011淘宝，10100010去哪儿，酷讯10100012，10100050携程

	@Formula("(select mc from ve_class where id=entity.wdpt)")
	private String wdptmc;// 网店平台名称

	private String kqzctf;// 是否开启政策投放0不开启，1开启

	private String zc_jkzh;// 政策接口账号

	private String zc_jkmm;// 政策投口密码

	private String zc_aqm1;// 政策安全码1

	private String zc_aqm2;// 政策安全码2

	private String zc_aqm3;// 政策安全码3

	private String zc_jkdz;// 政策接口地址

	private Date yxq;// 网店有效期默认2099-12-31

	private String zt;// 状态0停用，1启用

	private Date cjsj;// 创建时间

	private String cjyh;// 创建用户自动创建固定为0000

	private Date xgsj;// 最后修改时间

	private String xgyh;// 最后修改用户

	private String lxr;// 联系人

	private String lxrsj;// 联系人手机

	private String lxrxb;// 联系人性别

	private String lxrzw;// 联系人职位

	private String lxremail;// 联系人邮件

	private String lxrqq;// 联系人qq

	private String lxrwx;// 联系人微信

	private String wdzh;// 联系人qq

	private String wdmm;// 联系人微信

	private String jj_kssj;// 竞价开始时间

	private String jj_jssj;// 竞价结束时间

	private String jj_zdkq;

	private Integer jj_tqsj;

	private String sftbjk;// 是否淘宝接口,1是，0否（主要是用于和采用淘宝接口方式的第三方平台对接）

	private String bj_sfkq;

	private String bj_appkey;

	private String bj_appsecret;

	private String bj_sessionkey;

	private String bj_shop_nick_name;

	private String bj_password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getWdmc() {
		return wdmc;
	}

	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
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

	public String getKqzctf() {
		return kqzctf;
	}

	public void setKqzctf(String kqzctf) {
		this.kqzctf = kqzctf;
	}

	public String getZc_jkzh() {
		return zc_jkzh;
	}

	public void setZc_jkzh(String zc_jkzh) {
		this.zc_jkzh = zc_jkzh;
	}

	public String getZc_jkmm() {
		return zc_jkmm;
	}

	public void setZc_jkmm(String zc_jkmm) {
		this.zc_jkmm = zc_jkmm;
	}

	public String getZc_aqm1() {
		return zc_aqm1;
	}

	public void setZc_aqm1(String zc_aqm1) {
		this.zc_aqm1 = zc_aqm1;
	}

	public String getZc_aqm2() {
		return zc_aqm2;
	}

	public void setZc_aqm2(String zc_aqm2) {
		this.zc_aqm2 = zc_aqm2;
	}

	public String getZc_aqm3() {
		return zc_aqm3;
	}

	public void setZc_aqm3(String zc_aqm3) {
		this.zc_aqm3 = zc_aqm3;
	}

	public String getZc_jkdz() {
		return zc_jkdz;
	}

	public void setZc_jkdz(String zc_jkdz) {
		this.zc_jkdz = zc_jkdz;
	}

	public Date getYxq() {
		return yxq;
	}

	@Transient
	public String getYxqStr() {
		if (yxq != null) {
			return VeDate.dateToStr(this.yxq);
		} else {
			return "";
		}
	}

	public void setYxq(Date yxq) {
		this.yxq = yxq;
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

	@Transient
	public String getXgsjStr() {
		if (xgsj != null) {
			return VeDate.dateToStrLong(this.xgsj);
		} else {
			return "";
		}
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

	public String getWdptmc() {
		return wdptmc;
	}

	public void setWdptmc(String wdptmc) {
		this.wdptmc = wdptmc;
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

	public String getLxrxb() {
		return lxrxb;
	}

	public void setLxrxb(String lxrxb) {
		this.lxrxb = lxrxb;
	}

	public String getLxrzw() {
		return lxrzw;
	}

	public void setLxrzw(String lxrzw) {
		this.lxrzw = lxrzw;
	}

	public String getLxremail() {
		return lxremail;
	}

	public void setLxremail(String lxremail) {
		this.lxremail = lxremail;
	}

	public String getLxrqq() {
		return lxrqq;
	}

	public void setLxrqq(String lxrqq) {
		this.lxrqq = lxrqq;
	}

	public String getLxrwx() {
		return lxrwx;
	}

	public void setLxrwx(String lxrwx) {
		this.lxrwx = lxrwx;
	}

	public String getSftbjk() {
		return sftbjk;
	}

	public void setSftbjk(String sftbjk) {
		this.sftbjk = sftbjk;
	}

	public String getSftbjkStr() {
		if (StringUtils.isNotBlank(sftbjk)) {
			if ("1".equals(sftbjk)) {
				return "是";
			} else {
				return "否";
			}
		} else {
			return "否";
		}
	}

	public String getBj_sfkq() {
		return bj_sfkq;
	}

	public void setBj_sfkq(String bj_sfkq) {
		this.bj_sfkq = bj_sfkq;
	}

	public String getBj_appkey() {
		return bj_appkey;
	}

	public void setBj_appkey(String bj_appkey) {
		this.bj_appkey = bj_appkey;
	}

	public String getBj_appsecret() {
		return bj_appsecret;
	}

	public void setBj_appsecret(String bj_appsecret) {
		this.bj_appsecret = bj_appsecret;
	}

	public String getBj_sessionkey() {
		return bj_sessionkey;
	}

	public void setBj_sessionkey(String bj_sessionkey) {
		this.bj_sessionkey = bj_sessionkey;
	}

	public String getBj_shop_nick_name() {
		return bj_shop_nick_name;
	}

	public void setBj_shop_nick_name(String bj_shop_nick_name) {
		this.bj_shop_nick_name = bj_shop_nick_name;
	}

	public String getBj_password() {
		return bj_password;
	}

	public void setBj_password(String bj_password) {
		this.bj_password = bj_password;
	}

	public String getWdzh() {
		return wdzh;
	}

	public void setWdzh(String wdzh) {
		this.wdzh = wdzh;
	}

	public String getWdmm() {
		return wdmm;
	}

	public void setWdmm(String wdmm) {
		this.wdmm = wdmm;
	}

	public String getJj_zdkq() {
		return jj_zdkq;
	}

	public void setJj_zdkq(String jj_zdkq) {
		this.jj_zdkq = jj_zdkq;
	}

	public Integer getJj_tqsj() {
		return jj_tqsj;
	}

	public void setJj_tqsj(Integer jj_tqsj) {
		this.jj_tqsj = jj_tqsj;
	}

	public String getJj_kssj() {
		return jj_kssj;
	}

	public void setJj_kssj(String jj_kssj) {
		this.jj_kssj = jj_kssj;
	}

	public String getJj_jssj() {
		return jj_jssj;
	}

	public void setJj_jssj(String jj_jssj) {
		this.jj_jssj = jj_jssj;
	}

}
