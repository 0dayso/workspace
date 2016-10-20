package cn.vetech.vedsb.jp.entity.jpzdcp;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_ZDCP_DBJPNR")
public class JpZdcpDbjpnr extends AbstractPageEntity{
	private static final long serialVersionUID = 1466479682928L;

	/**ID主键，序列生成*/
	@Id
	private String id;
	/**自动出票监控表ID*/
	private String jkbid;
	/**订单编号*/
	private String ddbh;
	/**编码*/
	private String pnr;
	/**大编码*/
	private String hkgspnr;
	/**航空公司二字码*/
	private String hkgs;
	/**航程类型1.单程，2.往返，3.联程，4.缺口*/
	private String hclx;
	/**国内国际1国内，2国际*/
	private String hcgngj;
	/**航程格式：WUHPEK|PEKWUH*/
	private String hc;
	/**舱位格式：V|R*/
	private String cw;
	/**舱位折扣率格式：0.006|0.005*/
	private String cwzkl;
	/**子舱位格式：|R1*/
	private String cwSub;
	/**航班号格式：CZ1234|*CZ3541*/
	private String hbh;
	/**实际承运航班号格式：-|CZ4321当航段是共享航班时，这里会记录下实际承运航班号*/
	private String cyHbh;
	/**航程起飞时间格式：2016-06-18*/
	private String qfsj;
	/**航程到达时间格式：2016-06-18*/
	private String ddsj;
	/**航站楼格式：T1|T*/
	private String hzl;
	/**订单乘机人多个使用英文逗号,分隔*/
	private String cjr;
	/**预订时间*/
	private Date ydDatetime;
	/**PNR状态*/
	private String pnrZt;
	/**XEPNR时间*/
	private String xeDatetime;
	/**是否出票编码1是，0否*/
	private String sfcpbm;
	/**PAT账单价*/
	private Double patZdj;
	/**PAT机建*/
	private Double patJj;
	/**PAT税费*/
	private Double patSf;
	/**出票账单价*/
	private Double cpZdj;
	/**出票机建*/
	private Double cpJj;
	/**出票税费*/
	private Double cpSf;
	/**出票采购金额*/
	private Double cpCgje;
	/**出票类型*/
	private String cpCplx;
	/**采购平台标识*/
	private String cgPtbs;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return this.id;
	}

	public void setJkbid(String jkbid){
		this.jkbid=jkbid;
	}
	public String getJkbid(){
		return this.jkbid;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setPnr(String pnr){
		this.pnr=StringUtils.trim(pnr);
	}
	public String getPnr(){
		return this.pnr;
	}

	public void setHkgspnr(String hkgspnr){
		this.hkgspnr=StringUtils.trim(hkgspnr);
	}
	public String getHkgspnr(){
		return this.hkgspnr;
	}

	public void setHkgs(String hkgs){
		this.hkgs=StringUtils.trim(hkgs);
	}
	public String getHkgs(){
		return this.hkgs;
	}

	public void setHclx(String hclx){
		this.hclx=StringUtils.trim(hclx);
	}
	public String getHclx(){
		return this.hclx;
	}

	public void setHcgngj(String hcgngj){
		this.hcgngj=StringUtils.trim(hcgngj);
	}
	public String getHcgngj(){
		return this.hcgngj;
	}

	public void setHc(String hc){
		this.hc=StringUtils.trim(hc);
	}
	public String getHc(){
		return this.hc;
	}

	public void setCw(String cw){
		this.cw=StringUtils.trim(cw);
	}
	public String getCw(){
		return this.cw;
	}

	public void setCwzkl(String cwzkl){
		this.cwzkl=StringUtils.trim(cwzkl);
	}
	public String getCwzkl(){
		return this.cwzkl;
	}

	public void setCwSub(String cwSub){
		this.cwSub=StringUtils.trim(cwSub);
	}
	public String getCwSub(){
		return this.cwSub;
	}

	public void setHbh(String hbh){
		this.hbh=StringUtils.trim(hbh);
	}
	public String getHbh(){
		return this.hbh;
	}

	public void setCyHbh(String cyHbh){
		this.cyHbh=StringUtils.trim(cyHbh);
	}
	public String getCyHbh(){
		return this.cyHbh;
	}

	public void setQfsj(String qfsj){
		this.qfsj=StringUtils.trim(qfsj);
	}
	public String getQfsj(){
		return this.qfsj;
	}

	public void setDdsj(String ddsj){
		this.ddsj=StringUtils.trim(ddsj);
	}
	public String getDdsj(){
		return this.ddsj;
	}

	public void setHzl(String hzl){
		this.hzl=StringUtils.trim(hzl);
	}
	public String getHzl(){
		return this.hzl;
	}

	public void setCjr(String cjr){
		this.cjr=StringUtils.trim(cjr);
	}
	public String getCjr(){
		return this.cjr;
	}

	public void setYdDatetime(Date ydDatetime){
		this.ydDatetime=ydDatetime;
	}
	public Date getYdDatetime(){
		return this.ydDatetime;
	}

	public void setPnrZt(String pnrZt){
		this.pnrZt=StringUtils.trim(pnrZt);
	}
	public String getPnrZt(){
		return this.pnrZt;
	}

	public void setXeDatetime(String xeDatetime){
		this.xeDatetime=StringUtils.trim(xeDatetime);
	}
	public String getXeDatetime(){
		return this.xeDatetime;
	}

	public void setSfcpbm(String sfcpbm){
		this.sfcpbm=StringUtils.trim(sfcpbm);
	}
	public String getSfcpbm(){
		return this.sfcpbm;
	}

	public void setPatZdj(Double patZdj){
		this.patZdj=patZdj;
	}
	public Double getPatZdj(){
		return this.patZdj;
	}

	public void setPatJj(Double patJj){
		this.patJj=patJj;
	}
	public Double getPatJj(){
		return this.patJj;
	}

	public void setPatSf(Double patSf){
		this.patSf=patSf;
	}
	public Double getPatSf(){
		return this.patSf;
	}

	public void setCpZdj(Double cpZdj){
		this.cpZdj=cpZdj;
	}
	public Double getCpZdj(){
		return this.cpZdj;
	}

	public void setCpJj(Double cpJj){
		this.cpJj=cpJj;
	}
	public Double getCpJj(){
		return this.cpJj;
	}

	public void setCpSf(Double cpSf){
		this.cpSf=cpSf;
	}
	public Double getCpSf(){
		return this.cpSf;
	}

	public void setCpCgje(Double cpCgje){
		this.cpCgje=cpCgje;
	}
	public Double getCpCgje(){
		return this.cpCgje;
	}

	public void setCpCplx(String cpCplx){
		this.cpCplx=StringUtils.trim(cpCplx);
	}
	public String getCpCplx(){
		return this.cpCplx;
	}

	public void setCgPtbs(String cgPtbs){
		this.cgPtbs=StringUtils.trim(cgPtbs);
	}
	public String getCgPtbs(){
		return this.cgPtbs;
	}

}