package cn.vetech.vedsb.jp.entity.jpzdcp;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.utils.LogUtil;
@Table(name="JP_ZDCP_JK")
public class JpZdcpJk extends AbstractPageEntity{
	private static final long serialVersionUID = 1466479621937L;

	/**ID主键，序列生成*/
	@Id
	private String id;
	/**商户编号*/
	private String shbh;
	/**订单编号*/
	private String ddbh;
	/**是否符合自动出票条件1符合，0不符合*/
	private String zdcpSffh;
	/**自动出票规则ID*/
	private String gzid;
	/**创建时间*/
	private Date cjsj;
	/**出票状态出票状态，小于0失败等于0转机票成功，大于0出票中1初始状态0转机票成功-1不符合规则-10*/
	private String cpzt;
	/**订单编码*/
	private String ddPnrno;
	/**大编码*/
	private String ddHkgspnr;
	/**航空公司二字码*/
	private String ddHkgs;
	/**航程类型1.单程，2.往返，3.联程，4.缺口*/
	private String ddHclx;
	/**国内国际1国内，2国际*/
	private String ddHcgngj;
	/**航程格式：WUHPEK|PEKWUH*/
	private String ddHc;
	/**舱位格式：V|R*/
	private String ddCw;
	/**子舱位格式：|R1*/
	private String ddCwSub;
	/**航班号格式：CZ1234|CZ3541*/
	private String ddHbh;
	/**航程起飞时间格式：2016-06-18*/
	private String ddQfsj;
	/**订单乘机人多个使用英文逗号,分隔*/
	private String ddCjr;
	/**订单人数*/
	private Integer ddAdultno;
	/**订单儿童数*/
	private Integer ddChdno;
	/**订单婴儿数*/
	private Integer ddInfno;
	/**订单机建*/
	private Double ddJjhj;
	/**订单税费*/
	private Double ddTaxhj;
	/**订单销售价*/
	private Double ddXsj;
	/**订单奖励金额*/
	private Double ddJlje;
	/**订单下级结算价*/
	private Double ddXjjsj;
	/**订单支付金额*/
	private Double ddZfje;
	/**账单价单人*/
	private Double ddZdj;
	/**机建单人*/
	private Double ddJj;
	/**税费单人*/
	private Double ddSf;
	/**网店供应单号*/
	private String gyDdbh;
	/**自动出票员*/
	private String zdcpy;
	/**自动出票员密码存值为密码明文*/
	private String zdcpymm;
	/**三方协议号*/
	private String xyh;
	/**是否换编码1换编码，0不换编码*/
	private String sfhbm;
	/**是否开启原编码重出0不开启，1开启*/
	private String ypnrcc;
	/**新编码换编码后的编码*/
	private String newPnr;
	/**新大编码换编码后的大编码*/
	private String newHkgspnr;
	/**是否开启同时刻航班比价出票1开启，0不开启,2开启共享航班换主飞航班*/
	private String sftskbjcp;
	/**最小利润*/
	private Double zxlr;
	/**最终出票渠道BJCP,BPET,BSPET,BOP比价出票使用BJCP标识*/
	private String cpCpqd;
	/**出票类型BSPET、BOP、BPET、OP*/
	private String cplx;
	/**出票账单价*/
	private Double cpZdj;
	/**出票机建燃油合计*/
	private Double cpJjryhj;
	/**出票代理费*/
	private Double cpDdf;
	/**出票支付金额*/
	private Double cpZfje;
	/**出票支付流水号*/
	private String cpZflsh;
	/**出票支付类型*/
	private String cpZflx;
	/**出票支付账号*/
	private String cpZfzh;
	/**出票航司订单号*/
	private String cpHkgsddbh;
	/**采购平台标识*/
	private String cgPtbs;
	/**平台采购订单号*/
	private String ptcgdd;
	/**BOP密码*/
	private String bopmm;
	/**说明，失败原因等*/
	private String sm;
	/**是否开启比价出票1开启，0关闭*/
	private String kqbjcp;
	/**参与比价的政策例：BPET,10100000开启平台则使用平台编号，如果BPET直接存BPET*/
	private String bjCybjzc;
	/**比价BPET出票账号单个*/
	private String bjB2bCpzh;
	/**比价BPET后返格式：0.006，不带%号*/
	private Double bjB2bHf;
	/**单人采购净价差元，可正可负*/
	private Double bjCgjjc;
	/**OP出票返点最大误差值可正可负，格式：-0.005，不带%号*/
	private Double bjCpfdwc;
	/**比价出票状态1出票成功，0出票失败，2出票中*/
	private String bjCpzt;
	/**是否开启BPET出票1开启，0关闭*/
	private String kqbpet;
	/**BPET出票账号单个*/
	private String b2bCpzh;
	/**BPET后返格式：0.006，不带%号*/
	private Double b2bHf;
	/**BPET出票状态1出票成功，0出票失败，2出票中*/
	private String b2bCpzt;
	/**是否开启BSPET出票1开启，0关闭*/
	private String kqbspet;
	/**BSPET出票OFFICE*/
	private String bspOffice;
	/**BSPET出票工作号*/
	private String bspAgent;
	/**BSPET出票打票机号*/
	private String bspPrintno;
	/**BSPET出票状态1出票成功，0出票失败，2出票中*/
	private String bspCpzt;
	/**是否开启BOP出票1开启，0关闭*/
	private String kqbop;
	/**BOP出票OFFICE*/
	private String bopOffice;
	/**BOP出票工作号*/
	private String bopAgent;
	/**BOP出票状态1出票成功，0出票失败，2出票中*/
	private String bopCpzt;
	/**出票优先级BJCP,BPET,BSPET,BOP比价出票使用BJCP标识*/
	private String bpyxj;
	/**参与自动比价出票的CPS政策类型1普通，2特价，3特殊多个使用英文逗号分隔，例1,2*/
	private String ptbjCpzclx;
	/**参与自动比价出票的淘宝旗舰店政策类型1全网最低价，2精选，3金牌，4航司；多个使用英文逗号分隔，例1,2*/
	private String ptbjTbzclx;
	/**换编码政策是否参与比价1参与，0不参与*/
	private String ptbjHbmzc;
	/**非CPS政策特殊政策是否参与比价1参与，0不参与*/
	private String ptbjTszc;
	/**废票时间点限制*/
	private String ptbjFpsjxz;
	/**废票时间差忽略范围要求分钟*/
	private Integer ptbjFpsjc;
	/**出票时间差忽略范围要求分钟*/
	private Integer ptbjCpsjc;
	/**起飞时间限制小时，支持小数*/
	private Double ptbjQfsjxz;
	/**排序忽略返点差格式：0.005*/
	private Double ptbjPxhlfd;
	/**出票类型优先级格式：BSPET,BPET表示BSPET优先*/
	private String ptbjCplxyxj;
	/**平台优先级格式：10100000,10100005放平台编号，最前面的优先级最高，固定CPS优先级最高*/
	private String ptbjPtyxj;
	/**平台自动比价优先级格式：1,2,3,4其中：1.废票时间最晚2.出票类型优先级3.采购平台优先级别4.出票时间最晚*/
	private String ptbjZdxjyxj;
	/**未取到OP政策是否继续自动出票1*/
	private String ptbjOpyccl;
	/**未取到BPET政策是否继续自动出票1*/
	private String ptbjB2byccl;
	/**未取到淘宝旗舰店政策是否继续自动出票1*/
	private String ptbjTbyccl;
	/**是否检查osi*/
	private String sfjcosi;
	/**换编码osi*/
	private String hbmosi;
	/**是否低价出票1按低价出票，0订单账单价*/
	private String sfdjcp;
	/**出票完成时间*/
	private Date cpwcsj;
	/**净价差价*/
	private Double jjcj;
	/**
	 * 执行耗时(自动出票开始到出票结束)
	 */
	private Integer zxhs;
	@Transient
	private String ifhbmed;//是否已经换编码 0换过编码
	@Transient
	private StringBuffer info = new StringBuffer();
	
	public String add(String msg){
		LogUtil.getAutoCp().error(msg);
		info.append("【").append(VeDate.getStringDate()).append("】").append(msg).append("<br>");
		return msg;
	}
	
	
	public String getString(){
		return this.info.toString();
	}
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return this.id;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setZdcpSffh(String zdcpSffh){
		this.zdcpSffh=StringUtils.trim(zdcpSffh);
	}
	public String getZdcpSffh(){
		return this.zdcpSffh;
	}

	public void setGzid(String gzid){
		this.gzid=gzid;
	}
	public String getGzid(){
		return this.gzid;
	}

	public void setCjsj(Date cjsj){
		this.cjsj=cjsj;
	}
	public Date getCjsj(){
		return this.cjsj;
	}

	public void setCpzt(String cpzt){
		this.cpzt=StringUtils.trim(cpzt);
	}
	public String getCpzt(){
		return this.cpzt;
	}

	public void setDdPnrno(String ddPnrno){
		this.ddPnrno=StringUtils.trim(ddPnrno);
	}
	public String getDdPnrno(){
		return this.ddPnrno;
	}

	public void setDdHkgspnr(String ddHkgspnr){
		this.ddHkgspnr=StringUtils.trim(ddHkgspnr);
	}
	public String getDdHkgspnr(){
		return this.ddHkgspnr;
	}

	public void setDdHkgs(String ddHkgs){
		this.ddHkgs=StringUtils.trim(ddHkgs);
	}
	public String getDdHkgs(){
		return this.ddHkgs;
	}

	public void setDdHclx(String ddHclx){
		this.ddHclx=StringUtils.trim(ddHclx);
	}
	public String getDdHclx(){
		return this.ddHclx;
	}

	public void setDdHcgngj(String ddHcgngj){
		this.ddHcgngj=StringUtils.trim(ddHcgngj);
	}
	public String getDdHcgngj(){
		return this.ddHcgngj;
	}

	public void setDdHc(String ddHc){
		this.ddHc=StringUtils.trim(ddHc);
	}
	public String getDdHc(){
		return this.ddHc;
	}

	public void setDdCw(String ddCw){
		this.ddCw=StringUtils.trim(ddCw);
	}
	public String getDdCw(){
		return this.ddCw;
	}

	public void setDdCwSub(String ddCwSub){
		this.ddCwSub=StringUtils.trim(ddCwSub);
	}
	public String getDdCwSub(){
		return this.ddCwSub;
	}

	public void setDdHbh(String ddHbh){
		this.ddHbh=StringUtils.trim(ddHbh);
	}
	public String getDdHbh(){
		return this.ddHbh;
	}

	public void setDdQfsj(String ddQfsj){
		this.ddQfsj=StringUtils.trim(ddQfsj);
	}
	public String getDdQfsj(){
		return this.ddQfsj;
	}

	public void setDdCjr(String ddCjr){
		this.ddCjr=StringUtils.trim(ddCjr);
	}
	public String getDdCjr(){
		return this.ddCjr;
	}

	public void setDdAdultno(Integer ddAdultno){
		this.ddAdultno=ddAdultno;
	}
	public Integer getDdAdultno(){
		return this.ddAdultno;
	}

	public void setDdChdno(Integer ddChdno){
		this.ddChdno=ddChdno;
	}
	public Integer getDdChdno(){
		return this.ddChdno;
	}

	public void setDdInfno(Integer ddInfno){
		this.ddInfno=ddInfno;
	}
	public Integer getDdInfno(){
		return this.ddInfno;
	}

	public void setDdJjhj(Double ddJjhj){
		this.ddJjhj=ddJjhj;
	}
	public Double getDdJjhj(){
		return this.ddJjhj;
	}

	public void setDdTaxhj(Double ddTaxhj){
		this.ddTaxhj=ddTaxhj;
	}
	public Double getDdTaxhj(){
		return this.ddTaxhj;
	}

	public void setDdXsj(Double ddXsj){
		this.ddXsj=ddXsj;
	}
	public Double getDdXsj(){
		return this.ddXsj;
	}

	public void setDdJlje(Double ddJlje){
		this.ddJlje=ddJlje;
	}
	public Double getDdJlje(){
		return this.ddJlje;
	}

	public void setDdXjjsj(Double ddXjjsj){
		this.ddXjjsj=ddXjjsj;
	}
	public Double getDdXjjsj(){
		return this.ddXjjsj;
	}

	public void setDdZfje(Double ddZfje){
		this.ddZfje=ddZfje;
	}
	public Double getDdZfje(){
		return this.ddZfje;
	}

	public void setDdZdj(Double ddZdj){
		this.ddZdj=ddZdj;
	}
	public Double getDdZdj(){
		return this.ddZdj;
	}

	public void setDdJj(Double ddJj){
		this.ddJj=ddJj;
	}
	public Double getDdJj(){
		return this.ddJj;
	}

	public void setDdSf(Double ddSf){
		this.ddSf=ddSf;
	}
	public Double getDdSf(){
		return this.ddSf;
	}

	public void setGyDdbh(String gyDdbh){
		this.gyDdbh=StringUtils.trim(gyDdbh);
	}
	public String getGyDdbh(){
		return this.gyDdbh;
	}

	public void setZdcpy(String zdcpy){
		this.zdcpy=StringUtils.trim(zdcpy);
	}
	public String getZdcpy(){
		return this.zdcpy;
	}

	public void setZdcpymm(String zdcpymm){
		this.zdcpymm=StringUtils.trim(zdcpymm);
	}
	public String getZdcpymm(){
		return this.zdcpymm;
	}

	public void setXyh(String xyh){
		this.xyh=StringUtils.trim(xyh);
	}
	public String getXyh(){
		return this.xyh;
	}

	public void setSfhbm(String sfhbm){
		this.sfhbm=StringUtils.trim(sfhbm);
	}
	public String getSfhbm(){
		return this.sfhbm;
	}

	public void setNewPnr(String newPnr){
		this.newPnr=StringUtils.trim(newPnr);
	}
	public String getNewPnr(){
		return this.newPnr;
	}

	public void setNewHkgspnr(String newHkgspnr){
		this.newHkgspnr=StringUtils.trim(newHkgspnr);
	}
	public String getNewHkgspnr(){
		return this.newHkgspnr;
	}

	public void setSftskbjcp(String sftskbjcp){
		this.sftskbjcp=StringUtils.trim(sftskbjcp);
	}
	public String getSftskbjcp(){
		return this.sftskbjcp;
	}

	public void setZxlr(Double zxlr){
		this.zxlr=zxlr;
	}
	public Double getZxlr(){
		return this.zxlr;
	}

	public void setCpCpqd(String cpCpqd){
		this.cpCpqd=StringUtils.trim(cpCpqd);
	}
	public String getCpCpqd(){
		return this.cpCpqd;
	}

	public void setCplx(String cplx){
		this.cplx=StringUtils.trim(cplx);
	}
	public String getCplx(){
		return this.cplx;
	}

	public void setCpZdj(Double cpZdj){
		this.cpZdj=cpZdj;
	}
	public Double getCpZdj(){
		return this.cpZdj;
	}

	public void setCpJjryhj(Double cpJjryhj){
		this.cpJjryhj=cpJjryhj;
	}
	public Double getCpJjryhj(){
		return this.cpJjryhj;
	}

	public void setCpDdf(Double cpDdf){
		this.cpDdf=cpDdf;
	}
	public Double getCpDdf(){
		return this.cpDdf;
	}

	public void setCpZfje(Double cpZfje){
		this.cpZfje=cpZfje;
	}
	public Double getCpZfje(){
		return this.cpZfje;
	}

	public void setCpZflsh(String cpZflsh){
		this.cpZflsh=StringUtils.trim(cpZflsh);
	}
	public String getCpZflsh(){
		return this.cpZflsh;
	}

	public void setCpZflx(String cpZflx){
		this.cpZflx=StringUtils.trim(cpZflx);
	}
	public String getCpZflx(){
		return this.cpZflx;
	}

	public void setCpZfzh(String cpZfzh){
		this.cpZfzh=StringUtils.trim(cpZfzh);
	}
	public String getCpZfzh(){
		return this.cpZfzh;
	}

	public void setCpHkgsddbh(String cpHkgsddbh){
		this.cpHkgsddbh=StringUtils.trim(cpHkgsddbh);
	}
	public String getCpHkgsddbh(){
		return this.cpHkgsddbh;
	}

	public void setCgPtbs(String cgPtbs){
		this.cgPtbs=StringUtils.trim(cgPtbs);
	}
	public String getCgPtbs(){
		return this.cgPtbs;
	}

	public void setPtcgdd(String ptcgdd){
		this.ptcgdd=StringUtils.trim(ptcgdd);
	}
	public String getPtcgdd(){
		return this.ptcgdd;
	}

	public void setBopmm(String bopmm){
		this.bopmm=StringUtils.trim(bopmm);
	}
	public String getBopmm(){
		return this.bopmm;
	}

	public void setSm(String sm){
		this.sm=StringUtils.trim(sm);
	}
	public String getSm(){
		return this.sm;
	}

	public void setKqbjcp(String kqbjcp){
		this.kqbjcp=StringUtils.trim(kqbjcp);
	}
	public String getKqbjcp(){
		return this.kqbjcp;
	}

	public void setBjCybjzc(String bjCybjzc){
		this.bjCybjzc=StringUtils.trim(bjCybjzc);
	}
	public String getBjCybjzc(){
		return this.bjCybjzc;
	}

	public void setBjB2bCpzh(String bjB2bCpzh){
		this.bjB2bCpzh=StringUtils.trim(bjB2bCpzh);
	}
	public String getBjB2bCpzh(){
		return this.bjB2bCpzh;
	}

	public void setBjB2bHf(Double bjB2bHf){
		this.bjB2bHf=bjB2bHf;
	}
	public Double getBjB2bHf(){
		return this.bjB2bHf;
	}

	public void setBjCgjjc(Double bjCgjjc){
		this.bjCgjjc=bjCgjjc;
	}
	public Double getBjCgjjc(){
		return this.bjCgjjc;
	}

	public void setBjCpfdwc(Double bjCpfdwc){
		this.bjCpfdwc=bjCpfdwc;
	}
	public Double getBjCpfdwc(){
		return this.bjCpfdwc;
	}

	public void setBjCpzt(String bjCpzt){
		this.bjCpzt=StringUtils.trim(bjCpzt);
	}
	public String getBjCpzt(){
		return this.bjCpzt;
	}

	public void setKqbpet(String kqbpet){
		this.kqbpet=StringUtils.trim(kqbpet);
	}
	public String getKqbpet(){
		return this.kqbpet;
	}

	public void setB2bCpzh(String b2bCpzh){
		this.b2bCpzh=StringUtils.trim(b2bCpzh);
	}
	public String getB2bCpzh(){
		return this.b2bCpzh;
	}

	public void setB2bHf(Double b2bHf){
		this.b2bHf=b2bHf;
	}
	public Double getB2bHf(){
		return this.b2bHf;
	}

	public void setB2bCpzt(String b2bCpzt){
		this.b2bCpzt=StringUtils.trim(b2bCpzt);
	}
	public String getB2bCpzt(){
		return this.b2bCpzt;
	}

	public void setKqbspet(String kqbspet){
		this.kqbspet=StringUtils.trim(kqbspet);
	}
	public String getKqbspet(){
		return this.kqbspet;
	}

	public void setBspOffice(String bspOffice){
		this.bspOffice=StringUtils.trim(bspOffice);
	}
	public String getBspOffice(){
		return this.bspOffice;
	}

	public void setBspAgent(String bspAgent){
		this.bspAgent=StringUtils.trim(bspAgent);
	}
	public String getBspAgent(){
		return this.bspAgent;
	}

	public void setBspPrintno(String bspPrintno){
		this.bspPrintno=StringUtils.trim(bspPrintno);
	}
	public String getBspPrintno(){
		return this.bspPrintno;
	}

	public void setBspCpzt(String bspCpzt){
		this.bspCpzt=StringUtils.trim(bspCpzt);
	}
	public String getBspCpzt(){
		return this.bspCpzt;
	}

	public void setKqbop(String kqbop){
		this.kqbop=StringUtils.trim(kqbop);
	}
	public String getKqbop(){
		return this.kqbop;
	}

	public void setBopOffice(String bopOffice){
		this.bopOffice=StringUtils.trim(bopOffice);
	}
	public String getBopOffice(){
		return this.bopOffice;
	}

	public void setBopAgent(String bopAgent){
		this.bopAgent=StringUtils.trim(bopAgent);
	}
	public String getBopAgent(){
		return this.bopAgent;
	}

	public void setBopCpzt(String bopCpzt){
		this.bopCpzt=StringUtils.trim(bopCpzt);
	}
	public String getBopCpzt(){
		return this.bopCpzt;
	}

	public void setBpyxj(String bpyxj){
		this.bpyxj=StringUtils.trim(bpyxj);
	}
	public String getBpyxj(){
		return this.bpyxj;
	}

	public void setPtbjCpzclx(String ptbjCpzclx){
		this.ptbjCpzclx=StringUtils.trim(ptbjCpzclx);
	}
	public String getPtbjCpzclx(){
		return this.ptbjCpzclx;
	}

	public void setPtbjTbzclx(String ptbjTbzclx){
		this.ptbjTbzclx=StringUtils.trim(ptbjTbzclx);
	}
	public String getPtbjTbzclx(){
		return this.ptbjTbzclx;
	}

	public void setPtbjHbmzc(String ptbjHbmzc){
		this.ptbjHbmzc=StringUtils.trim(ptbjHbmzc);
	}
	public String getPtbjHbmzc(){
		return this.ptbjHbmzc;
	}

	public void setPtbjTszc(String ptbjTszc){
		this.ptbjTszc=StringUtils.trim(ptbjTszc);
	}
	public String getPtbjTszc(){
		return this.ptbjTszc;
	}

	public void setPtbjFpsjxz(String ptbjFpsjxz){
		this.ptbjFpsjxz=StringUtils.trim(ptbjFpsjxz);
	}
	public String getPtbjFpsjxz(){
		return this.ptbjFpsjxz;
	}

	public void setPtbjFpsjc(Integer ptbjFpsjc){
		this.ptbjFpsjc=ptbjFpsjc;
	}
	public Integer getPtbjFpsjc(){
		return this.ptbjFpsjc;
	}

	public void setPtbjCpsjc(Integer ptbjCpsjc){
		this.ptbjCpsjc=ptbjCpsjc;
	}
	public Integer getPtbjCpsjc(){
		return this.ptbjCpsjc;
	}

	public void setPtbjQfsjxz(Double ptbjQfsjxz){
		this.ptbjQfsjxz=ptbjQfsjxz;
	}
	public Double getPtbjQfsjxz(){
		return this.ptbjQfsjxz;
	}

	public void setPtbjPxhlfd(Double ptbjPxhlfd){
		this.ptbjPxhlfd=ptbjPxhlfd;
	}
	public Double getPtbjPxhlfd(){
		return this.ptbjPxhlfd;
	}

	public void setPtbjCplxyxj(String ptbjCplxyxj){
		this.ptbjCplxyxj=StringUtils.trim(ptbjCplxyxj);
	}
	public String getPtbjCplxyxj(){
		return this.ptbjCplxyxj;
	}

	public void setPtbjPtyxj(String ptbjPtyxj){
		this.ptbjPtyxj=StringUtils.trim(ptbjPtyxj);
	}
	public String getPtbjPtyxj(){
		return this.ptbjPtyxj;
	}

	public void setPtbjZdxjyxj(String ptbjZdxjyxj){
		this.ptbjZdxjyxj=StringUtils.trim(ptbjZdxjyxj);
	}
	public String getPtbjZdxjyxj(){
		return this.ptbjZdxjyxj;
	}

	public void setPtbjOpyccl(String ptbjOpyccl){
		this.ptbjOpyccl=StringUtils.trim(ptbjOpyccl);
	}
	public String getPtbjOpyccl(){
		return this.ptbjOpyccl;
	}

	public void setPtbjB2byccl(String ptbjB2byccl){
		this.ptbjB2byccl=StringUtils.trim(ptbjB2byccl);
	}
	public String getPtbjB2byccl(){
		return this.ptbjB2byccl;
	}

	public void setPtbjTbyccl(String ptbjTbyccl){
		this.ptbjTbyccl=StringUtils.trim(ptbjTbyccl);
	}
	public String getPtbjTbyccl(){
		return this.ptbjTbyccl;
	}
	public String getSfjcosi() {
		return sfjcosi;
	}
	public void setSfjcosi(String sfjcosi) {
		this.sfjcosi = sfjcosi;
	}
	public String getHbmosi() {
		return hbmosi;
	}
	public void setHbmosi(String hbmosi) {
		this.hbmosi = hbmosi;
	}
	public String getYpnrcc() {
		return ypnrcc;
	}
	public void setYpnrcc(String ypnrcc) {
		this.ypnrcc = ypnrcc;
	}
	public String getSfdjcp() {
		return sfdjcp;
	}
	public void setSfdjcp(String sfdjcp) {
		this.sfdjcp = sfdjcp;
	}
	public Date getCpwcsj() {
		return cpwcsj;
	}
	public void setCpwcsj(Date cpwcsj) {
		this.cpwcsj = cpwcsj;
	}
	public Double getJjcj() {
		return jjcj;
	}
	public void setJjcj(Double jjcj) {
		this.jjcj = jjcj;
	}

	public Integer getZxhs() {
        return zxhs;
    }

    public void setZxhs(Integer zxhs) {
        this.zxhs = zxhs;
    }


	public String getIfhbmed() {
		return ifhbmed;
	}


	public void setIfhbmed(String ifhbmed) {
		this.ifhbmed = ifhbmed;
	}
}