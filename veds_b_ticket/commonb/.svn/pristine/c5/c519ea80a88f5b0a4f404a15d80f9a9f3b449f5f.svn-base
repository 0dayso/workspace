package cn.vetech.vedsb.common.entity;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 商户信息表
 */
@Table(name = "SH_SHB")
public class Shshb extends AbstractPageEntity {

	private static final long serialVersionUID = 5607592388208474662L;

	@Id
	@NotBlank
	private String  bh;//商户编号主键
	@NotBlank
	private String  mc;//商户名称
	@NotBlank
	private String  jc;//商户简称
	
	private String  jp;//商户简拼
	
	private String  shly;//商户来源来源数据字典表B_class表中的类别’312003’
	@Transient
	private String  shlymc;//商户来源来源名称
	
	private String  szsf;//所在省份
	@Transient
	private String  szsfmc;//所在省份名称
	
	private String  szcs;//所在城市
	@Transient
	private String  szcsmc;//所在城市名称
	
	private String  yzbm;//邮政编码
	
	private String  gsdh;//公司电话
	
	private String  gscz;//公司传真
	
	private String  email;//电子邮箱 
	
	private String  gswz;//公司网址
	
	private String  fl1;//预留分类1--ve_class表中的1003类别
	
	private String  fl2;//预留分类2--ve_class表中的1004类别
	
	private String  fl3;//预留分类3--ve_class表中的1005类别
	
	private String  fl4;//预留分类4--ve_class表中的1006类别
	
	private String  zt;//''1''已审正常，''2''停用，3审核不通过，''9''未审核。停用的商户，则账号就被锁定，不能登录。

	private String  cjsj;//创建时间
	@Transient
	private String  cjsjs;//创建时间始--查询使用字段
	@Transient
	private String  cjsjz;//创建时间止--查询使用字段
	
	private String  cj_compid;//创建公司
	
	private String  cj_deptid;//创建部门
	
	private String  cj_userid;//创建用户
	
	private String  ywy_userid;//所属业务员
	
	private String  ywy_deptid;//业务员所在部门
	
	private String  ywy_compid;//业务员所在公司
	
	private String  shsj;//审核时间
	
	private String  sh_userid;//审核人
	
	private String  sh_deptid;//审核人所在部门
	
	private String  sh_compid;//审核人所在公司
	
	private String  xg_compid;//最后修改公司
	
	private String  xg_deptid;//最后修改部门
	
	private String  xg_userid;//最后修改人员
	@Transient
	private String  xg_usermc;//最后修改人员名称
	
	private Date  xgsj;//最后修改日期
	
	private String  ywfw;//业务范围产品编号，来源于数据字典，多个使用半角逗号分隔
	
	private String  pyjsm;//拼音检索码
	
	private String  lxr;//联系人
	
	private String  lxrxb;//联系人性别M男，F女
	
	private String  lxrzw;//联系人职务
	
	private String  lxrsj;//联系人手机
	
	private String  lxremail;//联系人邮箱
	
	private String  lxrqq;//联系人qq
	
	private String  lxrwx;//联系人微信
	
	private String  sctzry;//所属市场拓展人员
	
	private String  sctzryqy;//市场拓展人员负责区域
	
	private String  sctzryfl;//市场拓展人员负责分类
	
	private String  shry;//售后人员
	
	private String  ywy;//所属业务员
	
	private String  fwz;//所属服务组
	
	private String  djdz;//登记地址
	
	private String  yydz;//营业地址
	
	private Integer  kjzhs;//可建账号数
	
	private String  yxq;//有效期,过期不可登录
	
	private String gqtyyy;//挂起停用原因
	
	@Transient
	private String ktwd;//开通网店
	@Transient
	private String ktsj;//开通数据
	@Transient
	private String ktfw;//开通服务
	
	private String fl5;//预留分类5--ve_class表中的1007类别
	
	private String fl6;//预留分类6--ve_class表中的1008类别
	
	private String pidfwz;//商户对应的PID服务器组 --ve_class表中的1001类别
	
	private String zcfwz;//商户对应的政策服务器组--ve_class表中的1002类别

	@Transient
	private String kjwd_sl; // 商户可建网店类型和数量，网店类型和数量使用“^”隔开，多个网店类型和数量使用“，”隔开，例如10100010^10,10100012^3表示去哪儿最多可以建10个网店，酷讯最多可建3个网店，数量存0表示不限制

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getJc() {
		return jc;
	}

	public void setJc(String jc) {
		this.jc = jc;
	}

	public String getJp() {
		return jp;
	}

	public void setJp(String jp) {
		this.jp = jp;
	}

	public String getShly() {
		return shly;
	}

	public void setShly(String shly) {
		this.shly = shly;
	}

	public String getSzsf() {
		return szsf;
	}

	public void setSzsf(String szsf) {
		this.szsf = szsf;
	}

	public String getSzcs() {
		return szcs;
	}

	public void setSzcs(String szcs) {
		this.szcs = szcs;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}

	public String getGsdh() {
		return gsdh;
	}

	public void setGsdh(String gsdh) {
		this.gsdh = gsdh;
	}

	public String getGscz() {
		return gscz;
	}

	public void setGscz(String gscz) {
		this.gscz = gscz;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGswz() {
		return gswz;
	}

	public void setGswz(String gswz) {
		this.gswz = gswz;
	}

	public String getFl1() {
		return fl1;
	}

	public void setFl1(String fl1) {
		this.fl1 = fl1;
	}

	public String getFl2() {
		return fl2;
	}

	public void setFl2(String fl2) {
		this.fl2 = fl2;
	}

	public String getFl3() {
		return fl3;
	}

	public void setFl3(String fl3) {
		this.fl3 = fl3;
	}

	public String getFl4() {
		return fl4;
	}

	public void setFl4(String fl4) {
		this.fl4 = fl4;
	}

	public String getZt() {
		return zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getCj_compid() {
		return cj_compid;
	}

	public void setCj_compid(String cj_compid) {
		this.cj_compid = cj_compid;
	}

	public String getCj_deptid() {
		return cj_deptid;
	}

	public void setCj_deptid(String cj_deptid) {
		this.cj_deptid = cj_deptid;
	}

	public String getCj_userid() {
		return cj_userid;
	}

	public void setCj_userid(String cj_userid) {
		this.cj_userid = cj_userid;
	}

	public String getYwy_userid() {
		return ywy_userid;
	}

	public void setYwy_userid(String ywy_userid) {
		this.ywy_userid = ywy_userid;
	}

	public String getYwy_deptid() {
		return ywy_deptid;
	}

	public void setYwy_deptid(String ywy_deptid) {
		this.ywy_deptid = ywy_deptid;
	}

	public String getYwy_compid() {
		return ywy_compid;
	}

	public void setYwy_compid(String ywy_compid) {
		this.ywy_compid = ywy_compid;
	}

	public String getShsj() {
		return shsj;
	}

	public void setShsj(String shsj) {
		this.shsj = shsj;
	}

	public String getSh_userid() {
		return sh_userid;
	}

	public void setSh_userid(String sh_userid) {
		this.sh_userid = sh_userid;
	}

	public String getSh_deptid() {
		return sh_deptid;
	}

	public void setSh_deptid(String sh_deptid) {
		this.sh_deptid = sh_deptid;
	}

	public String getSh_compid() {
		return sh_compid;
	}

	public void setSh_compid(String sh_compid) {
		this.sh_compid = sh_compid;
	}

	public String getXg_compid() {
		return xg_compid;
	}

	public void setXg_compid(String xg_compid) {
		this.xg_compid = xg_compid;
	}

	public String getXg_deptid() {
		return xg_deptid;
	}

	public void setXg_deptid(String xg_deptid) {
		this.xg_deptid = xg_deptid;
	}

	public String getXg_userid() {
		return xg_userid;
	}

	public void setXg_userid(String xg_userid) {
		this.xg_userid = xg_userid;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}

	public String getYwfw() {
		return ywfw;
	}

	public void setYwfw(String ywfw) {
		this.ywfw = ywfw;
	}

	public String getPyjsm() {
		return pyjsm;
	}

	public void setPyjsm(String pyjsm) {
		this.pyjsm = pyjsm;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
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

	public String getLxrsj() {
		return lxrsj;
	}

	public void setLxrsj(String lxrsj) {
		this.lxrsj = lxrsj;
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

	public String getSctzry() {
		return sctzry;
	}

	public void setSctzry(String sctzry) {
		this.sctzry = sctzry;
	}

	public String getSctzryqy() {
		return sctzryqy;
	}

	public void setSctzryqy(String sctzryqy) {
		this.sctzryqy = sctzryqy;
	}

	public String getSctzryfl() {
		return sctzryfl;
	}

	public void setSctzryfl(String sctzryfl) {
		this.sctzryfl = sctzryfl;
	}

	public String getShry() {
		return shry;
	}

	public void setShry(String shry) {
		this.shry = shry;
	}

	public String getYwy() {
		return ywy;
	}

	public void setYwy(String ywy) {
		this.ywy = ywy;
	}

	public String getFwz() {
		return fwz;
	}

	public void setFwz(String fwz) {
		this.fwz = fwz;
	}

	public String getDjdz() {
		return djdz;
	}

	public void setDjdz(String djdz) {
		this.djdz = djdz;
	}

	public String getYydz() {
		return yydz;
	}

	public void setYydz(String yydz) {
		this.yydz = yydz;
	}


	public String getYxq() {
		return yxq;
	}

	public void setYxq(String yxq) {
		this.yxq = yxq;
	}

	public String getKjwd_sl() {
		return kjwd_sl;
	}

	public void setKjwd_sl(String kjwd_sl) {
		this.kjwd_sl = kjwd_sl;
	}

	public String getShlymc() {
		return shlymc;
	}

	public void setShlymc(String shlymc) {
		this.shlymc = shlymc;
	}

	public String getSzsfmc() {
		return szsfmc;
	}

	public void setSzsfmc(String szsfmc) {
		this.szsfmc = szsfmc;
	}

	public String getSzcsmc() {
		return szcsmc;
	}

	public void setSzcsmc(String szcsmc) {
		this.szcsmc = szcsmc;
	}

	public String getCjsjs() {
		return cjsjs;
	}

	public void setCjsjs(String cjsjs) {
		this.cjsjs = cjsjs;
	}

	public String getCjsjz() {
		return cjsjz;
	}

	public void setCjsjz(String cjsjz) {
		this.cjsjz = cjsjz;
	}

	public String getXg_usermc() {
		return xg_usermc;
	}

	public void setXg_usermc(String xg_usermc) {
		this.xg_usermc = xg_usermc;
	}

	public Integer getKjzhs() {
		return kjzhs;
	}

	public void setKjzhs(Integer kjzhs) {
		this.kjzhs = kjzhs;
	}

	public String getGqtyyy() {
		return gqtyyy;
	}

	public void setGqtyyy(String gqtyyy) {
		this.gqtyyy = gqtyyy;
	}

	public String getKtwd() {
		return ktwd;
	}

	public void setKtwd(String ktwd) {
		this.ktwd = ktwd;
	}

	public String getKtsj() {
		return ktsj;
	}

	public void setKtsj(String ktsj) {
		this.ktsj = ktsj;
	}

	public String getKtfw() {
		return ktfw;
	}

	public void setKtfw(String ktfw) {
		this.ktfw = ktfw;
	}

	public String getFl5() {
		return fl5;
	}

	public void setFl5(String fl5) {
		this.fl5 = fl5;
	}

	public String getFl6() {
		return fl6;
	}

	public void setFl6(String fl6) {
		this.fl6 = fl6;
	}

	public String getPidfwz() {
		return pidfwz;
	}

	public void setPidfwz(String pidfwz) {
		this.pidfwz = pidfwz;
	}

	public String getZcfwz() {
		return zcfwz;
	}

	public void setZcfwz(String zcfwz) {
		this.zcfwz = zcfwz;
	}

}
