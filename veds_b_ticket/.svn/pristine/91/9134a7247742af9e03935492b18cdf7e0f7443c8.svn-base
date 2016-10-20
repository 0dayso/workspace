package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="jp_tjsq")
public class JpTjsq extends AbstractPageEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7686869501881968300L;
	/**申请单号主键*/
	@Id
	private String sqdh;
	/**联系人*/
	private String lxr;
	/**联系电话*/
	private String lxdh;
	/**联系手机*/
	private String lxsj;
	/**航空公司不限为---*/
	private String hkgs;
	/**出发城市*/
	private String cfcity;
	/**到达城市*/
	private String ddcity;
	/**乘机日期始*/
	private Date cjrqs;
	/**乘机日期止*/
	private Date cjrqz;
	/**价格类型1按票价，2按折扣*/
	private String jglx;
	/**票价范围表示要求多少票价以下*/
	private Double pjfw;
	/**折扣范围表示要求多少折扣以下*/
	private Double zkfw;
	/**乘机人数*/
	private Integer cjrs;
	/**能否分开预订1能，0否，如果是否则必须要有足够的座位才能预订*/
	private String nffkyd;
	/**是否要求直达默认为1，现在都是直达的数据不用放出来*/
	private String sfzd;
	/**航班时刻始默认00:00*/
	private String hbsks;
	/**航班时刻止默认23:59*/
	private String hbskz;
	/**有效期默认出发日期前一天*/
	private Date yxq;
	/**订单编号如果是后台直接通过订单追位来的，则要有对应订单编号*/
	private String ddbh;
	/**申请状态0已申请，1已审核/追位中，2部分追位，3追位完成，4追位失败（已过有效期还未追到位置的），5客户取消,6管理员消*/
	private String sqZt;
	/**申请时间*/
	private Date sqDatetime;
	/**申请人*/
	private String sqUserid;
	/**申请部门*/
	private String sqDeptid;
	/**商户编号*/
	private String shbh;
	/**审核时间*/
	private Date ckDatetime;
	/**审核人*/
	private String ckUserid;
	/**审核部门*/
	private String ckDeptid;
	/**收款状态0未收，1已收*/
	private String zfFkf;
	/**收款金额*/
	private Double zfZfje;
	/**收款时间*/
	private Date zfZfsj;
	/**收款人*/
	private String zfUserid;
	/**收款部门*/
	private String zfDeptid;
	/**退款状态0未退，1已退*/
	private String tkFkf;
	/**退款金额*/
	private Double tkZfje;
	/**退款时间*/
	private String tkZfsj;
	/**退款人*/
	private String tkUserid;
	/**预订编码*/
	private String pnrNo;
	/**预订时间*/
	private Date dpDatetime;
	/**备用一 乘机人信息*/
	private String by1;
	/**备用二 临时pnr号*/
	private String by2;
	/**备用三 备注信息*/
	private String by3;
	/**特价申请或追位成功后产生订的订单编号*/
	private String dpDdbh;
	/**原订单的PNR*/
	private String ypnrNo;
	/**1国内,0国际*/
	private String gngj;
	/**航班号*/
	private String hbh;
	/**要追的舱位*/
	private String cw;
	/**订单处理状态(0未处理,1已处理,2部分处理)*/
	private String clZt;
	/**航班起飞时间*/
	private Date qftime;
	/**追位成功的机票订单的航班号*/
	private String zwhbh;
	/**原订单舱位*/
	private String ycw;
	/**原订单pnr状态*/
	private String ypnrZt;
	/**0 HL追位，1降舱追位*/
	private String zwZt;
	/**申请单自动取消原因*/
	private String zdqxyy;
	/**是否已提交追位,1是,0否*/
	private String sftjzw;
	/**提交时间*/
	private Date tjsj;
	/**追位成功预订编码的office号*/
	private String office;
	/**1只追原舱位，0追原舱位及以下舱位*/
	private String zwfs;
	/**出票时间*/
	private Date cpDatetime;
	/**0 自动   1手动   2预约单  3隔天追位*/
	private String zwlx;
	/**最新修改人*/
	private String xguserid;
	/**修改时间*/
	private Date xgdatetime;
	/**版本号*/
	private Integer version;
	/**退票费=账单价*退费费率*/
	private Double tpf;
	/**退费费率，客规维护退费费率*/
	private Double tpfl;
	/**共享航班是否追位，0或空表示不追，1追位*/
	private String gxhbsfzw;
	/**共享航班差价*/
	private Double gxhbcj;
	/**网店平台*/
	private String wdpt;
	/**网店ID号*/
	private String wdid;
	/**是否外部导入，1是，0否*/
	private String sfwbdr;

	public void setSqdh(String sqdh){
		this.sqdh=StringUtils.trim(sqdh);
	}
	public String getSqdh(){
		return this.sqdh;
	}

	public void setLxr(String lxr){
		this.lxr=StringUtils.trim(lxr);
	}
	public String getLxr(){
		return this.lxr;
	}

	public void setLxdh(String lxdh){
		this.lxdh=StringUtils.trim(lxdh);
	}
	public String getLxdh(){
		return this.lxdh;
	}

	public void setLxsj(String lxsj){
		this.lxsj=StringUtils.trim(lxsj);
	}
	public String getLxsj(){
		return this.lxsj;
	}

	public void setHkgs(String hkgs){
		this.hkgs=StringUtils.trim(hkgs);
	}
	public String getHkgs(){
		return this.hkgs;
	}

	public void setCfcity(String cfcity){
		this.cfcity=StringUtils.trim(cfcity);
	}
	public String getCfcity(){
		return this.cfcity;
	}

	public void setDdcity(String ddcity){
		this.ddcity=StringUtils.trim(ddcity);
	}
	public String getDdcity(){
		return this.ddcity;
	}

	public void setCjrqs(Date cjrqs){
		this.cjrqs=cjrqs;
	}
	public Date getCjrqs(){
		return this.cjrqs;
	}

	public void setCjrqz(Date cjrqz){
		this.cjrqz=cjrqz;
	}
	public Date getCjrqz(){
		return this.cjrqz;
	}

	public void setJglx(String jglx){
		this.jglx=StringUtils.trim(jglx);
	}
	public String getJglx(){
		return this.jglx;
	}

	public void setPjfw(Double pjfw){
		this.pjfw=pjfw;
	}
	public Double getPjfw(){
		return this.pjfw;
	}

	public void setZkfw(Double zkfw){
		this.zkfw=zkfw;
	}
	public Double getZkfw(){
		return this.zkfw;
	}

	public void setCjrs(Integer cjrs){
		this.cjrs=cjrs;
	}
	public Integer getCjrs(){
		return this.cjrs;
	}

	public void setNffkyd(String nffkyd){
		this.nffkyd=StringUtils.trim(nffkyd);
	}
	public String getNffkyd(){
		return this.nffkyd;
	}

	public void setSfzd(String sfzd){
		this.sfzd=StringUtils.trim(sfzd);
	}
	public String getSfzd(){
		return this.sfzd;
	}

	public void setHbsks(String hbsks){
		this.hbsks=StringUtils.trim(hbsks);
	}
	public String getHbsks(){
		return this.hbsks;
	}

	public void setHbskz(String hbskz){
		this.hbskz=StringUtils.trim(hbskz);
	}
	public String getHbskz(){
		return this.hbskz;
	}

	public void setYxq(Date yxq){
		this.yxq=yxq;
	}
	public Date getYxq(){
		return this.yxq;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setSqZt(String sqZt){
		this.sqZt=StringUtils.trim(sqZt);
	}
	public String getSqZt(){
		return this.sqZt;
	}

	public void setSqDatetime(Date sqDatetime){
		this.sqDatetime=sqDatetime;
	}
	public Date getSqDatetime(){
		return this.sqDatetime;
	}

	public void setSqUserid(String sqUserid){
		this.sqUserid=StringUtils.trim(sqUserid);
	}
	public String getSqUserid(){
		return this.sqUserid;
	}

	public void setSqDeptid(String sqDeptid){
		this.sqDeptid=StringUtils.trim(sqDeptid);
	}
	public String getSqDeptid(){
		return this.sqDeptid;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setCkDatetime(Date ckDatetime){
		this.ckDatetime=ckDatetime;
	}
	public Date getCkDatetime(){
		return this.ckDatetime;
	}

	public void setCkUserid(String ckUserid){
		this.ckUserid=StringUtils.trim(ckUserid);
	}
	public String getCkUserid(){
		return this.ckUserid;
	}

	public void setCkDeptid(String ckDeptid){
		this.ckDeptid=StringUtils.trim(ckDeptid);
	}
	public String getCkDeptid(){
		return this.ckDeptid;
	}

	public void setZfFkf(String zfFkf){
		this.zfFkf=StringUtils.trim(zfFkf);
	}
	public String getZfFkf(){
		return this.zfFkf;
	}

	public void setZfZfje(Double zfZfje){
		this.zfZfje=zfZfje;
	}
	public Double getZfZfje(){
		return this.zfZfje;
	}

	public void setZfZfsj(Date zfZfsj){
		this.zfZfsj=zfZfsj;
	}
	public Date getZfZfsj(){
		return this.zfZfsj;
	}

	public void setZfUserid(String zfUserid){
		this.zfUserid=StringUtils.trim(zfUserid);
	}
	public String getZfUserid(){
		return this.zfUserid;
	}

	public void setZfDeptid(String zfDeptid){
		this.zfDeptid=StringUtils.trim(zfDeptid);
	}
	public String getZfDeptid(){
		return this.zfDeptid;
	}

	public void setTkFkf(String tkFkf){
		this.tkFkf=StringUtils.trim(tkFkf);
	}
	public String getTkFkf(){
		return this.tkFkf;
	}

	public void setTkZfje(Double tkZfje){
		this.tkZfje=tkZfje;
	}
	public Double getTkZfje(){
		return this.tkZfje;
	}

	public void setTkZfsj(String tkZfsj){
		this.tkZfsj=StringUtils.trim(tkZfsj);
	}
	public String getTkZfsj(){
		return this.tkZfsj;
	}

	public void setTkUserid(String tkUserid){
		this.tkUserid=StringUtils.trim(tkUserid);
	}
	public String getTkUserid(){
		return this.tkUserid;
	}

	public void setPnrNo(String pnrNo){
		this.pnrNo=StringUtils.trim(pnrNo);
	}
	public String getPnrNo(){
		return this.pnrNo;
	}

	public void setDpDatetime(Date dpDatetime){
		this.dpDatetime=dpDatetime;
	}
	public Date getDpDatetime(){
		return this.dpDatetime;
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

	public void setDpDdbh(String dpDdbh){
		this.dpDdbh=StringUtils.trim(dpDdbh);
	}
	public String getDpDdbh(){
		return this.dpDdbh;
	}

	public void setYpnrNo(String ypnrNo){
		this.ypnrNo=StringUtils.trim(ypnrNo);
	}
	public String getYpnrNo(){
		return this.ypnrNo;
	}

	public void setGngj(String gngj){
		this.gngj=StringUtils.trim(gngj);
	}
	public String getGngj(){
		return this.gngj;
	}

	public void setHbh(String hbh){
		this.hbh=StringUtils.trim(hbh);
	}
	public String getHbh(){
		return this.hbh;
	}

	public void setCw(String cw){
		this.cw=StringUtils.trim(cw);
	}
	public String getCw(){
		return this.cw;
	}

	public void setClZt(String clZt){
		this.clZt=StringUtils.trim(clZt);
	}
	public String getClZt(){
		return this.clZt;
	}

	public void setQftime(Date qftime){
		this.qftime=qftime;
	}
	public Date getQftime(){
		return this.qftime;
	}

	public void setZwhbh(String zwhbh){
		this.zwhbh=StringUtils.trim(zwhbh);
	}
	public String getZwhbh(){
		return this.zwhbh;
	}

	public void setYcw(String ycw){
		this.ycw=StringUtils.trim(ycw);
	}
	public String getYcw(){
		return this.ycw;
	}

	public void setYpnrZt(String ypnrZt){
		this.ypnrZt=StringUtils.trim(ypnrZt);
	}
	public String getYpnrZt(){
		return this.ypnrZt;
	}

	public void setZwZt(String zwZt){
		this.zwZt=StringUtils.trim(zwZt);
	}
	public String getZwZt(){
		return this.zwZt;
	}

	public void setZdqxyy(String zdqxyy){
		this.zdqxyy=StringUtils.trim(zdqxyy);
	}
	public String getZdqxyy(){
		return this.zdqxyy;
	}

	public void setSftjzw(String sftjzw){
		this.sftjzw=StringUtils.trim(sftjzw);
	}
	public String getSftjzw(){
		return this.sftjzw;
	}

	public void setTjsj(Date tjsj){
		this.tjsj=tjsj;
	}
	public Date getTjsj(){
		return this.tjsj;
	}

	public void setOffice(String office){
		this.office=StringUtils.trim(office);
	}
	public String getOffice(){
		return this.office;
	}

	public void setZwfs(String zwfs){
		this.zwfs=StringUtils.trim(zwfs);
	}
	public String getZwfs(){
		return this.zwfs;
	}

	public void setCpDatetime(Date cpDatetime){
		this.cpDatetime=cpDatetime;
	}
	public Date getCpDatetime(){
		return this.cpDatetime;
	}

	public void setZwlx(String zwlx){
		this.zwlx=StringUtils.trim(zwlx);
	}
	public String getZwlx(){
		return this.zwlx;
	}

	public void setXguserid(String xguserid){
		this.xguserid=StringUtils.trim(xguserid);
	}
	public String getXguserid(){
		return this.xguserid;
	}

	public void setXgdatetime(Date xgdatetime){
		this.xgdatetime=xgdatetime;
	}
	public Date getXgdatetime(){
		return this.xgdatetime;
	}

	public void setVersion(Integer version){
		this.version=version;
	}
	public Integer getVersion(){
		return this.version;
	}

	public void setTpf(Double tpf){
		this.tpf=tpf;
	}
	public Double getTpf(){
		return this.tpf;
	}

	public void setTpfl(Double tpfl){
		this.tpfl=tpfl;
	}
	public Double getTpfl(){
		return this.tpfl;
	}

	public void setGxhbsfzw(String gxhbsfzw){
		this.gxhbsfzw=StringUtils.trim(gxhbsfzw);
	}
	public String getGxhbsfzw(){
		return this.gxhbsfzw;
	}

	public void setGxhbcj(Double gxhbcj){
		this.gxhbcj=gxhbcj;
	}
	public Double getGxhbcj(){
		return this.gxhbcj;
	}

	public void setWdpt(String wdpt){
		this.wdpt=StringUtils.trim(wdpt);
	}
	public String getWdpt(){
		return this.wdpt;
	}

	public void setWdid(String wdid){
		this.wdid=StringUtils.trim(wdid);
	}
	public String getWdid(){
		return this.wdid;
	}
	public String getSfwbdr() {
		return sfwbdr;
	}
	public void setSfwbdr(String sfwbdr) {
		this.sfwbdr = sfwbdr;
	}

}