package cn.vetech.vedsb.jp.entity.cgptsz;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_PTZC_ZH")
public class JpPtzcZh extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**平台政策标识*/
	private String ptzcbs;
	/**商户编号*/
	private String shbh;
	/**采购接口地址*/
	private String url1;
	/**null*/
	private String url2;
	/**采购账号1*/
	private String user1;
	/**采购账号2*/
	private String user2;
	/**key*/
	private String pwd1;
	/**null*/
	private String pwd2;
	/**null*/
	private String gyurl1;
	/**null*/
	private String gyurl2;
	/**null*/
	private String gyuser1;
	/**null*/
	private String gyuser2;
	/**null*/
	private String gypwd1;
	/**null*/
	private String gypwd2;
	/**采购是否开启*/
	private String open1;
	/**供应是否开启*/
	private String open2;
	/**最后修改人*/
	private String zhxgr;
	/**最后修改日期*/
	private String zhxgrq;
	/**供应订单入库人*/
	private String gyrkr;
	/**供应对应的部门*/
	private String gydept;
	/**采购时对分销开放 1开放 0不开放*/
	private String by1;
	/**采购是否有同步政策数据 1 同步 0 不同步 有同步则通过job定时同步*/
	private String by2;
	/**采购 分销支付后，自动代扣，默认的asms用户*/
	private String by3;
	/**采购对应部门*/
	private String cgdept;
	/**全取政策是否ok 1 成功 0 没成功 用于51book等 如果成功了则不再全取政策*/
	private String qqczok;
	/**采购时是否开启了代扣，需要结合平台功能，平台要支持代扣才行 1开启 0 未开启*/
	private String sfkqdk;
	/**是否匹配特殊政策1匹配 其他 不匹配*/
	private String sfpptxzc;
	/**淘宝是否自动确认*/
	private String gyAutoqr;
	/**供应是否自动交票 1自动交票 其他不自动交票*/
	private String gyAutojp;
	/**纵横的office号*/
	private String xx1;
	/**供应商入库的时候回填保险的险种*/
	private String xx2;
	/**是否对分销开启自动改签*/
	private String xx3;
	/**是否对分销开启自动退款*/
	private String xx4;
	/**是否限制不允许换编码 1不允许 其他允许*/
	private String xx5;
	/**380会员支付密码*/
	private String xx6;
	/**null*/
	private String jsbm;
	/**用于设置平台采购自动代扣时，使用的支付类型 */
	private String zddklx;
	/**380会员支付账号*/
	private String xx7;
	@Transient
	private String mc;
	
	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setPtzcbs(String ptzcbs){
		this.ptzcbs=StringUtils.trim(ptzcbs);
	}
	public String getPtzcbs(){
		return this.ptzcbs;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setUrl1(String url1){
		this.url1=StringUtils.trim(url1);
	}
	public String getUrl1(){
		return this.url1;
	}

	public void setUrl2(String url2){
		this.url2=StringUtils.trim(url2);
	}
	public String getUrl2(){
		return this.url2;
	}

	public void setUser1(String user1){
		this.user1=StringUtils.trim(user1);
	}
	public String getUser1(){
		return this.user1;
	}

	public void setUser2(String user2){
		this.user2=StringUtils.trim(user2);
	}
	public String getUser2(){
		return this.user2;
	}

	public void setPwd1(String pwd1){
		this.pwd1=StringUtils.trim(pwd1);
	}
	public String getPwd1(){
		return this.pwd1;
	}

	public void setPwd2(String pwd2){
		this.pwd2=StringUtils.trim(pwd2);
	}
	public String getPwd2(){
		return this.pwd2;
	}

	public void setGyurl1(String gyurl1){
		this.gyurl1=StringUtils.trim(gyurl1);
	}
	public String getGyurl1(){
		return this.gyurl1;
	}

	public void setGyurl2(String gyurl2){
		this.gyurl2=StringUtils.trim(gyurl2);
	}
	public String getGyurl2(){
		return this.gyurl2;
	}

	public void setGyuser1(String gyuser1){
		this.gyuser1=StringUtils.trim(gyuser1);
	}
	public String getGyuser1(){
		return this.gyuser1;
	}

	public void setGyuser2(String gyuser2){
		this.gyuser2=StringUtils.trim(gyuser2);
	}
	public String getGyuser2(){
		return this.gyuser2;
	}

	public void setGypwd1(String gypwd1){
		this.gypwd1=StringUtils.trim(gypwd1);
	}
	public String getGypwd1(){
		return this.gypwd1;
	}

	public void setGypwd2(String gypwd2){
		this.gypwd2=StringUtils.trim(gypwd2);
	}
	public String getGypwd2(){
		return this.gypwd2;
	}

	public void setOpen1(String open1){
		this.open1=StringUtils.trim(open1);
	}
	public String getOpen1(){
		return this.open1;
	}

	public void setOpen2(String open2){
		this.open2=StringUtils.trim(open2);
	}
	public String getOpen2(){
		return this.open2;
	}

	public void setZhxgr(String zhxgr){
		this.zhxgr=StringUtils.trim(zhxgr);
	}
	public String getZhxgr(){
		return this.zhxgr;
	}

	public void setZhxgrq(String zhxgrq){
		this.zhxgrq=StringUtils.trim(zhxgrq);
	}
	public String getZhxgrq(){
		return this.zhxgrq;
	}

	public void setGyrkr(String gyrkr){
		this.gyrkr=StringUtils.trim(gyrkr);
	}
	public String getGyrkr(){
		return this.gyrkr;
	}

	public void setGydept(String gydept){
		this.gydept=StringUtils.trim(gydept);
	}
	public String getGydept(){
		return this.gydept;
	}

	public void setBy1(String by1){
		this.by1=StringUtils.trim(by1);
	}
	public String getBy1(){
		return this.by1;
	}

	public void setBy2(String by2){
		this.by2=StringUtils.trim(by2);
	}
	public String getBy2(){
		return this.by2;
	}

	public void setBy3(String by3){
		this.by3=StringUtils.trim(by3);
	}
	public String getBy3(){
		return this.by3;
	}

	public void setCgdept(String cgdept){
		this.cgdept=StringUtils.trim(cgdept);
	}
	public String getCgdept(){
		return this.cgdept;
	}

	public void setQqczok(String qqczok){
		this.qqczok=StringUtils.trim(qqczok);
	}
	public String getQqczok(){
		return this.qqczok;
	}

	public void setSfkqdk(String sfkqdk){
		this.sfkqdk=StringUtils.trim(sfkqdk);
	}
	public String getSfkqdk(){
		return this.sfkqdk;
	}

	public void setSfpptxzc(String sfpptxzc){
		this.sfpptxzc=StringUtils.trim(sfpptxzc);
	}
	public String getSfpptxzc(){
		return this.sfpptxzc;
	}

	public void setGyAutoqr(String gyAutoqr){
		this.gyAutoqr=StringUtils.trim(gyAutoqr);
	}
	public String getGyAutoqr(){
		return this.gyAutoqr;
	}

	public void setGyAutojp(String gyAutojp){
		this.gyAutojp=StringUtils.trim(gyAutojp);
	}
	public String getGyAutojp(){
		return this.gyAutojp;
	}

	public void setXx1(String xx1){
		this.xx1=StringUtils.trim(xx1);
	}
	public String getXx1(){
		return this.xx1;
	}

	public void setXx2(String xx2){
		this.xx2=StringUtils.trim(xx2);
	}
	public String getXx2(){
		return this.xx2;
	}

	public void setXx3(String xx3){
		this.xx3=StringUtils.trim(xx3);
	}
	public String getXx3(){
		return this.xx3;
	}

	public void setXx4(String xx4){
		this.xx4=StringUtils.trim(xx4);
	}
	public String getXx4(){
		return this.xx4;
	}

	public void setXx5(String xx5){
		this.xx5=StringUtils.trim(xx5);
	}
	public String getXx5(){
		return this.xx5;
	}

	public void setXx6(String xx6){
		this.xx6=StringUtils.trim(xx6);
	}
	public String getXx6(){
		return this.xx6;
	}

	public void setJsbm(String jsbm){
		this.jsbm=StringUtils.trim(jsbm);
	}
	public String getJsbm(){
		return this.jsbm;
	}

	public void setZddklx(String zddklx){
		this.zddklx=StringUtils.trim(zddklx);
	}
	public String getZddklx(){
		return this.zddklx;
	}

	public void setXx7(String xx7){
		this.xx7=StringUtils.trim(xx7);
	}
	public String getXx7(){
		return this.xx7;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}

}