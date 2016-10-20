package cn.vetech.vedsb.jp.entity.cgdzbb;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Formula;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_CGDZ_CYB")
public class JpCgdzCyb extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**主键*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**对账日期*/
	private String dzrq;
	/**差异类型，取b_jcsjb lb=’10118’*/
	private String cylx;
	/**PNR*/
	private String pnrno;
	/**订单编号*/
	private String ddbh;
	/**票号*/
	private String tkno;
	/**出票类型，BSPET、BPET、OP（数据字典，采购来源）*/
	private String cplx;
	/**采购科目*/
	private String cgZfkm;
	/**采购支付流水号*/
	private String cgZflsh;
	/**处理状态，0未处理，1已处理*/
	private String clZt;
	/**处理人*/
	private String clUserid;
	/**处理部门*/
	private String clDeptid;
	/**处理时间*/
	private Date clDatetime;
	/**创建人*/
	private String cjUserid;
	/**创建部门*/
	private String cjDeptid;
	/**创建时间*/
	private Date cjDatetime;
	/**ASMS采购金额*/
	private Double zfjeAsms;
	/**账单支付金额*/
	private Double zfjeZd;
	/**备用一*/
	private String by1;
	/**备用二*/
	private String by2;
	/**备用三(异常说明)*/
	private String by3;
	/**处理说明*/
	private String clSm;
	/**外出票单位*/
	private String wcpdw;
	/**航空公司*/
	private String hkgs;
	/**出票时间*/
	private Date cpDatetime;
	/**航空公司编码*/
	private String hkgsPnr;
	/**商户编号*/
	private String shbh;
	/**乘机人*/
	@Formula("(SELECT cjr FROM JP_KHDD WHERE SHBH=ENTITY.SHBH AND DDBH=ENTITY.DDBH)")
	private String cjr;
	/**航程*/
	@Formula("(SELECT hc FROM JP_KHDD WHERE SHBH=ENTITY.SHBH AND DDBH=ENTITY.DDBH)")
	private String hc;
	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setDzrq(String dzrq){
		this.dzrq=StringUtils.trim(dzrq);
	}
	public String getDzrq(){
		return this.dzrq;
	}

	public void setCylx(String cylx){
		this.cylx=StringUtils.trim(cylx);
	}
	public String getCylx(){
		return this.cylx;
	}

	public void setPnrno(String pnrno){
		this.pnrno=StringUtils.trim(pnrno);
	}
	public String getPnrno(){
		return this.pnrno;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setTkno(String tkno){
		this.tkno=StringUtils.trim(tkno);
	}
	public String getTkno(){
		return this.tkno;
	}

	public void setCplx(String cplx){
		this.cplx=StringUtils.trim(cplx);
	}
	public String getCplx(){
		return this.cplx;
	}

	public void setCgZfkm(String cgZfkm){
		this.cgZfkm=StringUtils.trim(cgZfkm);
	}
	public String getCgZfkm(){
		return this.cgZfkm;
	}

	public void setCgZflsh(String cgZflsh){
		this.cgZflsh=StringUtils.trim(cgZflsh);
	}
	public String getCgZflsh(){
		return this.cgZflsh;
	}

	public void setClZt(String clZt){
		this.clZt=StringUtils.trim(clZt);
	}
	public String getClZt(){
		return this.clZt;
	}

	public void setClUserid(String clUserid){
		this.clUserid=StringUtils.trim(clUserid);
	}
	public String getClUserid(){
		return this.clUserid;
	}

	public void setClDeptid(String clDeptid){
		this.clDeptid=StringUtils.trim(clDeptid);
	}
	public String getClDeptid(){
		return this.clDeptid;
	}

	public void setClDatetime(Date clDatetime){
		this.clDatetime=clDatetime;
	}
	public Date getClDatetime(){
		return this.clDatetime;
	}

	public void setCjUserid(String cjUserid){
		this.cjUserid=StringUtils.trim(cjUserid);
	}
	public String getCjUserid(){
		return this.cjUserid;
	}

	public void setCjDeptid(String cjDeptid){
		this.cjDeptid=StringUtils.trim(cjDeptid);
	}
	public String getCjDeptid(){
		return this.cjDeptid;
	}

	public void setCjDatetime(Date cjDatetime){
		this.cjDatetime=cjDatetime;
	}
	public Date getCjDatetime(){
		return this.cjDatetime;
	}

	public void setZfjeAsms(Double zfjeAsms){
		this.zfjeAsms=zfjeAsms;
	}
	public Double getZfjeAsms(){
		return this.zfjeAsms;
	}

	public void setZfjeZd(Double zfjeZd){
		this.zfjeZd=zfjeZd;
	}
	public Double getZfjeZd(){
		return this.zfjeZd;
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

	public void setClSm(String clSm){
		this.clSm=StringUtils.trim(clSm);
	}
	public String getClSm(){
		return this.clSm;
	}

	public void setWcpdw(String wcpdw){
		this.wcpdw=StringUtils.trim(wcpdw);
	}
	public String getWcpdw(){
		return this.wcpdw;
	}

	public void setHkgs(String hkgs){
		this.hkgs=StringUtils.trim(hkgs);
	}
	public String getHkgs(){
		return this.hkgs;
	}

	public void setCpDatetime(Date cpDatetime){
		this.cpDatetime=cpDatetime;
	}
	public Date getCpDatetime(){
		return this.cpDatetime;
	}

	public void setHkgsPnr(String hkgsPnr){
		this.hkgsPnr=StringUtils.trim(hkgsPnr);
	}
	public String getHkgsPnr(){
		return this.hkgsPnr;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getHc() {
		return hc;
	}
	public void setHc(String hc) {
		this.hc = hc;
	}

}