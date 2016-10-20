package cn.vetech.vedsb.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.entity.BusinessId;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.service.BYdlog;
import cn.vetech.vedsb.common.sso.SsoUser;

/**
 * 商户用户表
 */
@Table(name = "SH_YHB")
public class Shyhb extends SsoUser {

	private static final long serialVersionUID = 8562122791146139249L;

	@Id
	@NotBlank
	private String bh; // 用户编号主键，商户设置，验证商户内不重复

	@BusinessId
	private String shbh; // 商户编号主键

	@NotBlank
	private String shbmid; // 所属部门

	@Formula("(SELECT MC FROM SH_BM WHERE SHBH=ENTITY.SHBH AND ID=ENTITY.SHBMID)")
	@BYdlog(name="所属部门",logView=1)
	private String bmmc; // 所属部门
	
	private String shfzid; // 商户分组ID

	@Formula("(SELECT MC FROM SH_BMFZ WHERE SHBH=ENTITY.SHBH AND ID=ENTITY.SHFZID)")
	@BYdlog(name="所属分组",logView=1)
	private String fzmc; // 商户分组ID

	@NotBlank
	@BYdlog(name="姓名",logView=1)
	private String xm; // 姓名

	@NotBlank
	private String xb; // 性别男M，女F

	@NotBlank
	private String dlzh; // 登录账号用户自己设置的登录账号，系统内不能重复，可用邮箱或手机号码

	@NotBlank
	private String mm; // 密码存MD5密文
	@BYdlog(name="电话",logView=1)
	private String dh; // 电话
	@BYdlog(name="电子邮箱",logView=1)
	private String email; // 电子邮箱

	@NotBlank
	private String qxjb; // 权限级别1个人级，3部门级，5公司级，9商户管理员

	@NotBlank
	private String zt; // 在职状态，1在职，0离职

	private Date cjsj; // 创建时间

	private String cjyh; // 创建用户自动创建固定为0000
	
	@Transient
	private String cjyhmc; // 创建用户自动创建固定为0000
	
	@BYdlog(name="最后修改时间",logView=1)
	private Date xgsj; // 最后修改时间
	
	@BYdlog(name="最后修改用户",logView=1)
	private String xgyh; // 最后修改用户
	
	@Transient
	private String xgyhmc; // 最后修改用户

	private String bzbz; // 备注

	@Transient
	private List<Shqxb> qxList;

	@Transient
	private String yhjsid; // 商户用户角色ID

	@Transient
	private String yhjsidzt; // 商户用户角色状态 查询用 为1时表示这个用户包含在一个角色内

	@Transient
	private String qtjsmc; // 其它角色名称

	@Transient
	private Shmkb mk;

	@Transient
	private Shmkgn mkgn;
	private String sspidz;//所属PID组
	private String pidyh;//pid用户
	
	@Transient
	private String pidZName;//所属PID组名称
	
	
	public String getPidyh() {
		return pidyh;
	}

	public void setPidyh(String pidyh) {
		this.pidyh = pidyh;
	}

	public String getPidZName() {
		return pidZName;
	}

	public void setPidZName(String pidZName) {
		this.pidZName = pidZName;
	}

	public String getSspidz() {
		return sspidz;
	}

	public void setSspidz(String sspidz) {
		this.sspidz = sspidz;
	}

	@Transient
	private Shshb shshb;
	@Transient
	private String ip;
	@Transient
	@BYdlog(name="性别",logView=1)
	private String xbs;
	@Transient
	@BYdlog(name="在职状态",logView=1)
	private String zts;
	@Transient
	@BYdlog(name="权限级别",logView=1)
	private String qxjbs;
	@Transient
	@BYdlog(name="权限",logView=1)
	private String qxxg;
	@Transient
	@BYdlog(name="密码",logView=1)
	private String xgmm;
	@Transient
	private String shzts;
	public String getShzts() {
		return shzts;
	}

	public void setShzts(String shzts) {
		this.shzts = shzts;
	}

	public String getXgmm() {
		return xgmm;
	}

	public void setXgmm(String xgmm) {
		this.xgmm = xgmm;
	}

	public String getQxxg() {
		return qxxg;
	}

	public void setQxxg(String qxxg) {
		this.qxxg = qxxg;
	}

	public String getQxjbs() {
		if("1".equals(qxjb)){
			return "个人级";
		}else if("3".equals(qxjb)){
			return "部门级";
		}else{
			return "公司级";
		}
	}

	public void setQxjbs(String qxjbs) {
		this.qxjbs = qxjbs;
	}

	public String getXbs() {
		if("M".equals(xb)){
			return "男";
		}else{
			return "女";
		}
		
	}
	public void setXbs(String xbs) {
		this.xbs = xbs;
	}

	public String getZts() {
		if("1".equals(zt)){
			return "在职";
		}else{
			return "离职";
		}
	}

	public void setZts(String zts) {
		this.zts = zts;
	}

	public Shshb getShshb() {
		return shshb;
	}

	public void setShshb(Shshb shshb) {
		this.shshb = shshb;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getShbmid() {
		return shbmid;
	}

	public void setShbmid(String shbmid) {
		this.shbmid = shbmid;
	}

	public String getShfzid() {
		return shfzid;
	}

	public void setShfzid(String shfzid) {
		this.shfzid = shfzid;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getDlzh() {
		return dlzh;
	}

	public void setDlzh(String dlzh) {
		this.dlzh = dlzh;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQxjb() {
		return qxjb;
	}

	public void setQxjb(String qxjb) {
		this.qxjb = qxjb;
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

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getFzmc() {
		return fzmc;
	}

	public void setFzmc(String fzmc) {
		this.fzmc = fzmc;
	}

	public String getZtmc() {
		if ("1".equals(zt)) {
			return "在职";
		} else if ("0".equals(zt)) {
			return "离职";
		}
		return zt;
	}

	public String getQxjbmc() {
		if ("1".equals(qxjb)) {
			return "个人级";
		} else if ("3".equals(qxjb)) {
			return "部门级";
		} else if ("5".equals(qxjb)) {
			return "公司级";
		} else if ("9".equals(qxjb)) {
			return "商户管理员";
		}
		return qxjb;
	}

	public String getCjsjmc() {
		return VeDate.dateToStrLong(cjsj);
	}

	public List<Shqxb> getQxList() {
		return qxList;
	}

	public void setQxList(List<Shqxb> qxList) {
		this.qxList = qxList;
	}

	public String getYhjsid() {
		return yhjsid;
	}

	public void setYhjsid(String yhjsid) {
		this.yhjsid = yhjsid;
	}

	public String getYhjsidzt() {
		return yhjsidzt;
	}

	public void setYhjsidzt(String yhjsidzt) {
		this.yhjsidzt = yhjsidzt;
	}

	public String getQtjsmc() {
		return qtjsmc;
	}

	public void setQtjsmc(String qtjsmc) {
		this.qtjsmc = qtjsmc;
	}

	public Shmkb getMk() {
		return mk;
	}

	public void setMk(Shmkb mk) {
		this.mk = mk;
	}

	public Shmkgn getMkgn() {
		return mkgn;
	}

	public void setMkgn(Shmkgn mkgn) {
		this.mkgn = mkgn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCjyhmc() {
		if("0000".equals(cjyh)){
			return "系统自动创建";
		}else{
			return cjyhmc;
		}
	}

	public void setCjyhmc(String cjyhmc) {
		this.cjyhmc = cjyhmc;
	}

	public String getXgyhmc() {
		return xgyhmc;
	}

	public void setXgyhmc(String xgyhmc) {
		this.xgyhmc = xgyhmc;
	}
	
}
