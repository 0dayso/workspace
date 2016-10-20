package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;



@Table(name="JP_DDSZ")
public class JpDdsz extends AbstractPageEntity{
	private static final long serialVersionUID = -2588165717515693103L;
	@Id
	private String wdid;//网店ID号
	private String ddKqzcdd;//是否开启正常导单0否，1是
	private String ddKqtpdd;//是否开启退票导单0否，1是
	private String ddKqgqdd;//是否开启改签导单0否，1是
	private String ddUserid;//默认导单订票员
	private String ddBmid;//订票员所属部门
	private String ddJkzh;//导单接口账号
	private String ddJkmm;//导单接口密码
	private String ddAqm1;//导单安全码1
	private String ddAqm2;//导单安全码2
	private String ddAqm3;//导单安全码3

	private String ddJkdz;//导单接口地址
	private String ddSfqr;//自动确认订单 携程:认领订单 
	private String ddMrkm;//默认收款科目

	private String ddZdpat;//自动PAT0不自动PAT，1账单价为0的订单自动PAT，2所有订单自动PAT，3按政策代码PAT 
	private String ddZcdmpat;//政策代码PAT
	private String ddNwsx;//检查NO位时限 1是，0否
	private String ddqxtx;//订单取消提醒
	private String rtxtx;//RTX提醒   RTX提醒输入多个RTX号码,用逗号分隔
	private String dxtx;//短信提醒   短信提醒输入多个手机号码,用逗号分隔
	private String yjtx;//邮件提醒   邮件提醒输入多个邮箱用逗号分隔 
	private String shbh;//商户编号
	private String cjyh;//创建用户  创建用户自动创建固定为0000
	private Date cjsj;//创建时间
	private Date xgsj;//修改时间
	private String xgyh;//最后修改用户
	//新增字段
	private String wdpt;//网店平台
	private String wdmc;//网店平台
	private String ddGngj;//导单国内国际 0国内 1国际 默认国内
	private String ddoffice;//订单office号
	private String sqoffice;//携程授权office号
	private String lxrsj;//联系人手机，去哪专用，订单入库的时候以网店设置联系人手机为准
	private String sfdrpsxx;//是否导入配送信息 0不导入配送信息和快递费用 1导入配送信息和快递费用  2只导入配送信息不导入快递费用
	private String ddwzffs;//导单无支付方式  去哪业务，不传支付方式的特殊订单，入库必须是未付状态 1导入无支付方式订单  0不导入无支付方式订单  默认0
	private String gqddz;//改签单请求地址
	private String gqdlzh; //改签登录账号
	private String sfsmpdyw; //淘宝是否扫描派单业务  1开启 其他关闭 默认0
	private String ddautocp;//订单自动出票 0不开启  1开启  默认0
	private Integer tbsmsjjg;//淘宝扫描时间间隔 正常当扫描
	
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	public String getDdKqzcdd() {
		return ddKqzcdd;
	}
	public void setDdKqzcdd(String ddKqzcdd) {
		this.ddKqzcdd = ddKqzcdd;
	}
	public String getDdKqtpdd() {
		return ddKqtpdd;
	}
	public void setDdKqtpdd(String ddKqtpdd) {
		this.ddKqtpdd = ddKqtpdd;
	}
	public String getDdKqgqdd() {
		return ddKqgqdd;
	}
	public void setDdKqgqdd(String ddKqgqdd) {
		this.ddKqgqdd = ddKqgqdd;
	}
	public String getDdUserid() {
		return ddUserid;
	}
	public void setDdUserid(String ddUserid) {
		this.ddUserid = ddUserid;
	}
	public String getDdBmid() {
		return ddBmid;
	}
	public void setDdBmid(String ddBmid) {
		this.ddBmid = ddBmid;
	}
	public String getDdJkzh() {
		return ddJkzh;
	}
	public void setDdJkzh(String ddJkzh) {
		this.ddJkzh = ddJkzh;
	}
	public String getDdJkmm() {
		return ddJkmm;
	}
	public void setDdJkmm(String ddJkmm) {
		this.ddJkmm = ddJkmm;
	}
	public String getDdAqm1() {
		return ddAqm1;
	}
	public void setDdAqm1(String ddAqm1) {
		this.ddAqm1 = ddAqm1;
	}
	public String getDdAqm2() {
		return ddAqm2;
	}
	public void setDdAqm2(String ddAqm2) {
		this.ddAqm2 = ddAqm2;
	}
	public String getDdAqm3() {
		return ddAqm3;
	}
	public void setDdAqm3(String ddAqm3) {
		this.ddAqm3 = ddAqm3;
	}
	public String getDdJkdz() {
		return ddJkdz;
	}
	public void setDdJkdz(String ddJkdz) {
		this.ddJkdz = ddJkdz;
	}
	public String getDdSfqr() {
		return ddSfqr;
	}
	public void setDdSfqr(String ddSfqr) {
		this.ddSfqr = ddSfqr;
	}
	public String getDdMrkm() {
		return ddMrkm;
	}
	public void setDdMrkm(String ddMrkm) {
		this.ddMrkm = ddMrkm;
	}
	public String getDdZdpat() {
		return ddZdpat;
	}
	public void setDdZdpat(String ddZdpat) {
		this.ddZdpat = ddZdpat;
	}
	public String getDdZcdmpat() {
		return ddZcdmpat;
	}
	public void setDdZcdmpat(String ddZcdmpat) {
		this.ddZcdmpat = ddZcdmpat;
	}
	public String getDdNwsx() {
		return ddNwsx;
	}
	public void setDdNwsx(String ddNwsx) {
		this.ddNwsx = ddNwsx;
	}
	public String getDdqxtx() {
		return ddqxtx;
	}
	public void setDdqxtx(String ddqxtx) {
		this.ddqxtx = ddqxtx;
	}
	public String getRtxtx() {
		return rtxtx;
	}
	public void setRtxtx(String rtxtx) {
		this.rtxtx = rtxtx;
	}
	public String getDxtx() {
		return dxtx;
	}
	public void setDxtx(String dxtx) {
		this.dxtx = dxtx;
	}
	public String getYjtx() {
		return yjtx;
	}
	public void setYjtx(String yjtx) {
		this.yjtx = yjtx;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getCjyh() {
		return cjyh;
	}
	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
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
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}
	public String getWdmc() {
		return wdmc;
	}
	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
	}
	public String getDdGngj() {
		return ddGngj;
	}
	public void setDdGngj(String ddGngj) {
		this.ddGngj = ddGngj;
	}
	public String getDdoffice() {
		return ddoffice;
	}
	public void setDdoffice(String ddoffice) {
		this.ddoffice = ddoffice;
	}
	public String getSqoffice() {
		return sqoffice;
	}
	public void setSqoffice(String sqoffice) {
		this.sqoffice = sqoffice;
	}
	public String getLxrsj() {
		return lxrsj;
	}
	public void setLxrsj(String lxrsj) {
		this.lxrsj = lxrsj;
	}
	public String getSfdrpsxx() {
		return sfdrpsxx;
	}
	public void setSfdrpsxx(String sfdrpsxx) {
		this.sfdrpsxx = sfdrpsxx;
	}
	public String getDdwzffs() {
		return ddwzffs;
	}
	public void setDdwzffs(String ddwzffs) {
		this.ddwzffs = ddwzffs;
	}
	public String getGqddz() {
		return gqddz;
	}
	public void setGqddz(String gqddz) {
		this.gqddz = gqddz;
	}
	public String getGqdlzh() {
		return gqdlzh;
	}
	public void setGqdlzh(String gqdlzh) {
		this.gqdlzh = gqdlzh;
	}
	public String getSfsmpdyw() {
		return sfsmpdyw;
	}
	public void setSfsmpdyw(String sfsmpdyw) {
		this.sfsmpdyw = sfsmpdyw;
	}
	public String getDdautocp() {
		return ddautocp;
	}
	public void setDdautocp(String ddautocp) {
		this.ddautocp = ddautocp;
	}
	public Integer getTbsmsjjg() {
		return tbsmsjjg;
	}
	public void setTbsmsjjg(Integer tbsmsjjg) {
		this.tbsmsjjg = tbsmsjjg;
	}
}
