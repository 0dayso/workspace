package cn.vetech.vedsb.jp.entity.jpzdcp;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_ZDCPGZ")
public class JpZdcpgz extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**ID主键，序列生成*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**商户编号*/
	private String shbh;
	/**优先级数字越大优先级越高*/
	private Integer yxj;
	/**发布操作员*/
	private String fbczy;
	/**发布时间*/
	private Date fbDatetime;
	/**状态状态0禁用，1启用，2删除*/
	private String zt;
	/**审核操作员*/
	private String shczy;
	/**审核时间*/
	private Date shDatetime;
	/**最后修改操作员*/
	private String xgczy;
	/**最后修改时间*/
	private Date xgDatetime;
	/**规则名称*/
	private String gzmc;
	/**自动出票员*/
	private String zdcpy;
	/**自动出票员密码存值为密码明文*/
	private String zdcpymm;
	/**适用时间始格式：00:00*/
	private String sysjs;
	/**适用时间止格式：00:00*/
	private String sysjz;
	/**航空公司多个使用/分隔，---表示所有*/
	private String hkgs;
	/**适用政策代码多个使用英文逗号“,”分隔，严格区分大小写*/
	private String zcdm;
	/**最小利润可正可负*/
	private Integer zxlr;
	/**是否低价出票1按低价出票，0订单账单价*/
	private String sfdjcp;
	/**是否开启同时刻航班比价1开启，0关闭,2开启共享航班换主飞航班*/
	private String kqkhbbj;
	/**是否开启比价出票1开启，0关闭*/
	private String kqbjcp;
	/**参与比价的政策例：BPET,10100000开启平台则使用平台编号，如果BPET直接存BPET*/
	private String bjCybjzc;
	/**比价BPET出票账号存出票账号ID，可多个，使用英文逗号,分隔*/
	private String bjB2bCpzh;
	/**比价BPET后返格式：0.006，不带%号*/
	private Double bjB2bHf;
	/**单人采购净价差元，可正可负*/
	private Double bjCgjjc;
	/**OP出票返点最大误差值可正可负，格式：-0.005，不带%号*/
	private Double bjCpfdwc;
	/**是否开启BPET出票1开启，0关闭*/
	private String kqbpet;
	/**BPET出票账号存出票账号ID，可多个，使用英文逗号,分隔*/
	private String b2bCpzh;
	/**BPET后返格式：0.006，不带%号*/
	private Double b2bHf;
	/**是否开启BSPET出票1开启，0关闭*/
	private String kqbspet;
	/**BSPET出票OFFICE*/
	private String bspOffice;
	/**BSPET出票工作号*/
	private String bspAgent;
	/**BSPET出票打票机号*/
	private String bspPrintno;
	/**是否开启BOP出票1开启，0关闭*/
	private String kqbop;
	/**BOP出票OFFICE*/
	private String bopOffice;
	/**BOP出票工作号*/
	private String bopAgent;
	/**出票优先级BJCP,BPET,BSPET,BOP比价出票使用BJCP标识*/
	private String bpyxj;
	/**航班是否适用0全部*/
	private String hbhsfsy;
	/**航班号多个使用/分隔，---表示全部*/
	private String hbh;
	/**共享航班是否适用1适用，0不适用*/
	private String gxhbsfsy;
	/**出发城市多个使用/分隔，---表示所有*/
	private String cfcity;
	/**到达城市多个使用/分隔，---表示所有*/
	private String ddcity;
	/**中转城市多个使用/分隔，---表示所有*/
	private String zzcity;
	/**舱位多个使用/分隔，---表示所有*/
	private String cw;
	/**乘机日期始格式：YYYY-MM-DD*/
	private String cjrqs;
	/**乘机日期止格式：YYYY-MM-DD*/
	private String cjrqz;
	/**不享受日期格式：YYYY-MM-DD，多个使用英文逗号,分隔*/
	private String bxsrq;
	/**航程类型1.单程，2.往返，3.联程，4.缺口*/
	private String hclx;
	/**乘机人类型乘机人类型*/
	private String cjrlx;
	/**适用班期1/2/3/4/5/6/7*/
	private String sybq;
	/**最晚提前出票天数*/
	private Integer zwtqcpts;
	/**最早提前出票天数*/
	private Integer zztqcpts;
	/**最少旅客人数限制0表示不限制，订单人数要大于等于此值*/
	private Integer zslkrs;
	/**协议号多个使用英文逗号分隔*/
	private String xyh;
	/**是否换编码0不换，1换编码*/
	private String sfhpnr;
	/**是否开启原编码重出0不开启，1开启*/
	private String ypnrcc;
	/**换编码OSI项*/
	private String hbmosi;
	/**换编码OFFICE*/
	private String hbmoffice;
	/**最短停留天数（含）*/
	private Integer zdtlts;
	/**最长停留天数（含）*/
	private Integer zctlts;
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
	/**乘机人中含有xx的不能自动出票,支持设置多个*/
	private String bnzdcp;
	/**是否检查osi*/
	private String sfjcosi;
	/**净价差价*/
	private Double jjcj;
	/**适用订单来源,1:正常导入订单适用,2:外部出票失败订单适用,3:两种都适用,默认值是1*/
	private String syddly;
	/**网店id,可选择多个网店*/
	private String wdid;
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

	public void setYxj(Integer yxj){
		this.yxj=yxj;
	}
	public Integer getYxj(){
		return this.yxj;
	}

	public void setFbczy(String fbczy){
		this.fbczy=StringUtils.trim(fbczy);
	}
	public String getFbczy(){
		return this.fbczy;
	}

	public void setFbDatetime(Date fbDatetime){
		this.fbDatetime=fbDatetime;
	}
	public Date getFbDatetime(){
		return this.fbDatetime;
	}

	public void setZt(String zt){
		this.zt=StringUtils.trim(zt);
	}
	public String getZt(){
		return this.zt;
	}

	public void setShczy(String shczy){
		this.shczy=StringUtils.trim(shczy);
	}
	public String getShczy(){
		return this.shczy;
	}

	public void setShDatetime(Date shDatetime){
		this.shDatetime=shDatetime;
	}
	public Date getShDatetime(){
		return this.shDatetime;
	}

	public void setXgczy(String xgczy){
		this.xgczy=StringUtils.trim(xgczy);
	}
	public String getXgczy(){
		return this.xgczy;
	}

	public void setXgDatetime(Date xgDatetime){
		this.xgDatetime=xgDatetime;
	}
	public Date getXgDatetime(){
		return this.xgDatetime;
	}

	public void setGzmc(String gzmc){
		this.gzmc=StringUtils.trim(gzmc);
	}
	public String getGzmc(){
		return this.gzmc;
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

	public void setSysjs(String sysjs){
		this.sysjs=StringUtils.trim(sysjs);
	}
	public String getSysjs(){
		return this.sysjs;
	}

	public void setSysjz(String sysjz){
		this.sysjz=StringUtils.trim(sysjz);
	}
	public String getSysjz(){
		return this.sysjz;
	}

	public void setHkgs(String hkgs){
		this.hkgs=StringUtils.trim(hkgs);
	}
	public String getHkgs(){
		return this.hkgs;
	}

	public void setZcdm(String zcdm){
		this.zcdm=StringUtils.trim(zcdm);
	}
	public String getZcdm(){
		return this.zcdm;
	}

	public void setZxlr(Integer zxlr){
		this.zxlr=zxlr;
	}
	public Integer getZxlr(){
		return this.zxlr;
	}

	public void setSfdjcp(String sfdjcp){
		this.sfdjcp=StringUtils.trim(sfdjcp);
	}
	public String getSfdjcp(){
		return this.sfdjcp;
	}

	public void setKqkhbbj(String kqkhbbj){
		this.kqkhbbj=StringUtils.trim(kqkhbbj);
	}
	public String getKqkhbbj(){
		return this.kqkhbbj;
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

	public void setBpyxj(String bpyxj){
		this.bpyxj=StringUtils.trim(bpyxj);
	}
	public String getBpyxj(){
		return this.bpyxj;
	}

	public void setHbhsfsy(String hbhsfsy){
		this.hbhsfsy=StringUtils.trim(hbhsfsy);
	}
	public String getHbhsfsy(){
		return this.hbhsfsy;
	}

	public void setHbh(String hbh){
		this.hbh=StringUtils.trim(hbh);
	}
	public String getHbh(){
		return this.hbh;
	}

	public void setGxhbsfsy(String gxhbsfsy){
		this.gxhbsfsy=StringUtils.trim(gxhbsfsy);
	}
	public String getGxhbsfsy(){
		return this.gxhbsfsy;
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

	public void setCw(String cw){
		this.cw=StringUtils.trim(cw);
	}
	public String getCw(){
		return this.cw;
	}

	public void setCjrqs(String cjrqs){
		this.cjrqs=StringUtils.trim(cjrqs);
	}
	public String getCjrqs(){
		return this.cjrqs;
	}

	public void setCjrqz(String cjrqz){
		this.cjrqz=StringUtils.trim(cjrqz);
	}
	public String getCjrqz(){
		return this.cjrqz;
	}

	public void setBxsrq(String bxsrq){
		this.bxsrq=StringUtils.trim(bxsrq);
	}
	public String getBxsrq(){
		return this.bxsrq;
	}

	public void setHclx(String hclx){
		this.hclx=StringUtils.trim(hclx);
	}
	public String getHclx(){
		return this.hclx;
	}

	public void setCjrlx(String cjrlx){
		this.cjrlx=StringUtils.trim(cjrlx);
	}
	public String getCjrlx(){
		return this.cjrlx;
	}

	public void setSybq(String sybq){
		this.sybq=StringUtils.trim(sybq);
	}
	public String getSybq(){
		return this.sybq;
	}

	public void setZwtqcpts(Integer zwtqcpts){
		this.zwtqcpts=zwtqcpts;
	}
	public Integer getZwtqcpts(){
		return this.zwtqcpts;
	}

	public void setZztqcpts(Integer zztqcpts){
		this.zztqcpts=zztqcpts;
	}
	public Integer getZztqcpts(){
		return this.zztqcpts;
	}

	public void setZslkrs(Integer zslkrs){
		this.zslkrs=zslkrs;
	}
	public Integer getZslkrs(){
		return this.zslkrs;
	}

	public void setXyh(String xyh){
		this.xyh=StringUtils.trim(xyh);
	}
	public String getXyh(){
		return this.xyh;
	}

	public void setSfhpnr(String sfhpnr){
		this.sfhpnr=StringUtils.trim(sfhpnr);
	}
	public String getSfhpnr(){
		return this.sfhpnr;
	}

	public void setYpnrcc(String ypnrcc){
		this.ypnrcc=StringUtils.trim(ypnrcc);
	}
	public String getYpnrcc(){
		return this.ypnrcc;
	}

	public void setHbmosi(String hbmosi){
		this.hbmosi=StringUtils.trim(hbmosi);
	}
	public String getHbmosi(){
		return this.hbmosi;
	}

	public void setHbmoffice(String hbmoffice){
		this.hbmoffice=StringUtils.trim(hbmoffice);
	}
	public String getHbmoffice(){
		return this.hbmoffice;
	}

	public void setZdtlts(Integer zdtlts){
		this.zdtlts=zdtlts;
	}
	public Integer getZdtlts(){
		return this.zdtlts;
	}

	public void setZctlts(Integer zctlts){
		this.zctlts=zctlts;
	}
	public Integer getZctlts(){
		return this.zctlts;
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
	public String getZzcity() {
		return zzcity;
	}
	public void setZzcity(String zzcity) {
		this.zzcity = zzcity;
	}
	public String getBnzdcp() {
		return bnzdcp;
	}
	public void setBnzdcp(String bnzdcp) {
		this.bnzdcp = bnzdcp;
	}
	public String getSfjcosi() {
		return sfjcosi;
	}
	public void setSfjcosi(String sfjcosi) {
		this.sfjcosi = sfjcosi;
	}
	public Double getJjcj() {
		return jjcj;
	}
	public void setJjcj(Double jjcj) {
		this.jjcj = jjcj;
	}
	public String getSyddly() {
		return syddly;
	}
	public void setSyddly(String syddly) {
		this.syddly = syddly;
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}

}