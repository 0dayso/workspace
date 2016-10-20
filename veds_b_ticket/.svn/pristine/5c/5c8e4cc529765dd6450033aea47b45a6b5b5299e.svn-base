package cn.vetech.framework.bookticket.b2cnew.action;

import java.io.Serializable;

/**
 * 这里要和B2C代购系统包名保持一致
 * 
 * @author vetech
 *
 */
public class PaymentInfo implements Serializable {

	private static final long serialVersionUID = 3095960972559401571L;

	private String payhand;// 1手动支付 其他自动支付
	private String id, cjtime, // 创建时间
			compid, // 创建公司
			deptid, // 部门
			userid, // 用户
			zflx, // 支付类型 1=支付宝 2=财付通 3=汇付天下 4=易宝 5=快钱 6=银联御航宝 7=易商旅 8=易生卡

			/**
			 * 当sfsyzzh为1的时候表示支付宝使用操作员账号 易商旅使用操作员方式
			 */
			sfsyzzh,
			/**
			 * 支付账号 支付宝账号，财付通账号
			 * 
			 * (如果是汇付 pUsername：输入参数，字符型；表示该用户在汇付天下的付款账号，即该航空公司B2B网站账号。)
			 * 
			 * (如果是易宝 UserName=易宝账号名)
			 */
			zfzh,
			/**
			 * 子账号
			 */
			zfzzh,

			sfqy, // 是否签约 只有支付宝 财付通 易生卡
			sfkq, // 是否开启使用 1开启 0未开启 开启才能使用

			/**
			 * 支付信息1
			 * 
			 * （如果是支付宝 XB=先信用后余额 X=只使用信用支付 BX=先余额后信用 B=只使用余额支付） (如果是财付通 签约航空公司数量)
			 * 
			 * (如果是汇付 pPayType：输入参数，字符型；表示支付方式，1＝钱管家信用账户；2＝钱管家付款账户。)
			 * 
			 * (如果是易宝 支付账号类型（客户提供账号时需选择）：易宝会员 1000000-NET 易宝信用账户 YEEPAYCREDIT)
			 */
			zfxx1,

			/**
			 * 支付信息2
			 * 
			 * (如果是支付宝 代扣安全码 deduct_third_code )
			 * 
			 * (如果是汇付 pPswd：输入参数，字符型；表示该用户在汇付天下的支付密码，即交易密码。)
			 * 
			 * (如果是易宝 pwd=支付密码)
			 * 
			 * 易商旅存储支付帐号的 身份认证密钥
			 * 
			 * 易生卡 签约者客户号
			 * 
			 */
			zfxx2,

			/**
			 * 支付信息3
			 * 
			 * (如果是支付宝 付款方支付宝人民币资金账号 trans_account_out )
			 * 
			 * (如果是汇付 pOperType：输入参数，字符型；表示用户操作类型，A＝航空公司用户类型；P＝非A类型，当前只支持A类型。)
			 * 
			 * 易商旅存储操作员的 身份认证密钥
			 * 
			 * 易生卡 签约付款签名key
			 */
			zfxx3,

			/**
			 * 支付信息4
			 * 
			 * (如果是支付宝 最后申请代扣安全码的时间 yyyy-MM-dd hh:mi:ss )
			 * 
			 * (如果是汇付 pKeyFilePath：输入参数，字符型；表示汇付公共密钥文件（PgPubk.key）所在的文件夹路径。)
			 * 
			 * (如果是易航宝 代理人的机构码)
			 */
			zfxx4,
			/**
			 * 支付信息5 余额等信息
			 * 
			 * 易航宝存储委托码
			 */
			zfxx5,
			/**
			 * 支付宝是否操作员账号使用方式,如果是此处存储的是支付账号
			 */
			zfxx6,
			/**
			 * 存储支付账号对应的支付科目,可以不设置，不设置，默认取航空公司支付方式对应 如果支付宝操作员存储支付帐号 zfzh存储操作员号
			 * 
			 */
			zfxx7;

	private String info;// 页面传递信息
	/**
	 * 支付宝操作员支付时 保存操作员id，id是从授权后返回的
	 */
	private String zfxx8;
	private String zfxx9;
	private String zfxx10;
	private String zfxx11;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCjtime() {
		return cjtime;
	}

	public void setCjtime(String cjtime) {
		this.cjtime = cjtime;
	}

	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getZflx() {
		return zflx;
	}

	public void setZflx(String zflx) {
		this.zflx = zflx;
	}

	public String getZfzh() {
		return zfzh;
	}

	public void setZfzh(String zfzh) {
		this.zfzh = zfzh;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}

	public String getZfxx1() {
		return zfxx1;
	}

	public void setZfxx1(String zfxx1) {
		this.zfxx1 = zfxx1;
	}

	public String getZfxx2() {
		return zfxx2;
	}

	public void setZfxx2(String zfxx2) {
		this.zfxx2 = zfxx2;
	}

	public String getZfxx3() {
		return zfxx3;
	}

	public void setZfxx3(String zfxx3) {
		this.zfxx3 = zfxx3;
	}

	public String getZfxx4() {
		return zfxx4;
	}

	public void setZfxx4(String zfxx4) {
		this.zfxx4 = zfxx4;
	}

	public String getSfkq() {
		return sfkq;
	}

	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}

	public String getZfxx5() {
		return zfxx5;
	}

	public void setZfxx5(String zfxx5) {
		this.zfxx5 = zfxx5;
	}

	public String getZfxx6() {
		return zfxx6;
	}

	public void setZfxx6(String zfxx6) {
		this.zfxx6 = zfxx6;
	}

	public String getZfxx7() {
		return zfxx7;
	}

	public void setZfxx7(String zfxx7) {
		this.zfxx7 = zfxx7;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getZfxx8() {
		return zfxx8;
	}

	public void setZfxx8(String zfxx8) {
		this.zfxx8 = zfxx8;
	}

	public String getZfxx9() {
		return zfxx9;
	}

	public void setZfxx9(String zfxx9) {
		this.zfxx9 = zfxx9;
	}

	public String getZfxx10() {
		return zfxx10;
	}

	public void setZfxx10(String zfxx10) {
		this.zfxx10 = zfxx10;
	}

	public String getZfxx11() {
		return zfxx11;
	}

	public void setZfxx11(String zfxx11) {
		this.zfxx11 = zfxx11;
	}

	public String getPayhand() {
		return payhand;
	}

	public void setPayhand(String payhand) {
		this.payhand = payhand;
	}

	public String getSfsyzzh() {
		return sfsyzzh;
	}

	public void setSfsyzzh(String sfsyzzh) {
		this.sfsyzzh = sfsyzzh;
	}

	public String getZfzzh() {
		return zfzzh;
	}

	public void setZfzzh(String zfzzh) {
		this.zfzzh = zfzzh;
	}
}
