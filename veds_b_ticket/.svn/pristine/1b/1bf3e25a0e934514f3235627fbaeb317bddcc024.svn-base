package cn.vetech.vedsb.jp.entity.jpzdcp;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 自动出票国际规则
 */
@Table(name="JP_ZDCP_GJ_GZ")
public class JpZdcpGjGz extends AbstractPageEntity{
	
	private static final long serialVersionUID = 1L;

	/**ID主键，序列生成*/
	@Id
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
	/**单人最小利润（元，比价出票时，1，选择票面价比价：需判断【单人票面价-QTE票面价】大于或等于此值才可自动出票 
	 * 2，选择销售价比价：需判断【单人销售价-QTE票面价*（1-代理费率）】大于或等于此值才可自动出票 ）*/
	private Integer drzxlr;
	/**是否低价出票1按低价出票，0订单账单价*/
	private String sfdjcp;
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
	/**航程类型 0单程 1往返 2单程和往返*/
	private String hclx;
	/**乘机人类型(0成人 1儿童 2婴儿 3老人 4劳务 5移民 6海员)*/
	private String cjrlx;
	/**乘机日期始格式：YYYY-MM-DD*/
	private String cjrqs;
	/**乘机日期止格式：YYYY-MM-DD*/
	private String cjrqz;
	/**不享受日期格式：YYYY-MM-DD，多个使用英文逗号,分隔*/
	private String bxsrq;
	/**出发城市 三字码，多个使用/分隔*/
	private String cfcs;
	/**到达城市 三字码，多个使用/分隔*/
	private String ddcs;
	/**航班适用 0全部适用  1 适用 2不适用*/
	private String hbhsytj;
	/**航班号 多个使用/分隔  ---标识全部航班号*/
	private String hbh;
	/**不适用航班号 多个使用/分隔*/
	private String bsyhbh;
	/**舱位 多个使用/分隔*/
	private String cw;
	/**最晚提前出票天数*/
	private Integer zwtqcpts;
	/**最早提前出票天数*/
	private Integer zztqcpts;
	/**订单人数限制始 为空代表不限制*/
	private Integer ddrsxzs;
	/**订单人数限制止 为空代表不限制*/
	private Integer ddrsxzz;
	/**年龄限制始 为空代表不限制*/
	private Integer nlxzs;
	/**年龄限制止 为空代表不限制*/
	private Integer nlxzz;
	/**乘机人名中含有的字符不能自动出票（支持录入多个，使用英文逗号","分隔）*/
	private String cjrmzh;
	/**适用国籍（国家二字码，多个用/分隔）---表示适用全部国籍*/
	private String sygj;
	/**不适用国籍（国家二字码，多个用/分隔）*/
	private String bsygj;
	/**OSI项检查  0检查订单乘机人手机号 1检查PNR内容中CTCT项*/
	private String osixjc;
	/**是否换编码出票 0否 1是*/
	private String sfhbmcp;
	/**开启原编码重出  1表示开启  0表示不开启*/
	private String kqybmcc;
	/**换编码OSI项*/
	private String hbmosix;
	/**换编码OFFICE*/
	private String hbmoffice;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public Integer getYxj() {
		return yxj;
	}
	public void setYxj(Integer yxj) {
		this.yxj = yxj;
	}
	public String getFbczy() {
		return fbczy;
	}
	public void setFbczy(String fbczy) {
		this.fbczy = fbczy;
	}
	public Date getFbDatetime() {
		return fbDatetime;
	}
	public void setFbDatetime(Date fbDatetime) {
		this.fbDatetime = fbDatetime;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public String getXgczy() {
		return xgczy;
	}
	public void setXgczy(String xgczy) {
		this.xgczy = xgczy;
	}
	public Date getXgDatetime() {
		return xgDatetime;
	}
	public void setXgDatetime(Date xgDatetime) {
		this.xgDatetime = xgDatetime;
	}
	public String getGzmc() {
		return gzmc;
	}
	public void setGzmc(String gzmc) {
		this.gzmc = gzmc;
	}
	public String getZdcpy() {
		return zdcpy;
	}
	public void setZdcpy(String zdcpy) {
		this.zdcpy = zdcpy;
	}
	public String getZdcpymm() {
		return zdcpymm;
	}
	public void setZdcpymm(String zdcpymm) {
		this.zdcpymm = zdcpymm;
	}
	public String getSysjs() {
		return sysjs;
	}
	public void setSysjs(String sysjs) {
		this.sysjs = sysjs;
	}
	public String getSysjz() {
		return sysjz;
	}
	public void setSysjz(String sysjz) {
		this.sysjz = sysjz;
	}
	public String getHkgs() {
		return hkgs;
	}
	public void setHkgs(String hkgs) {
		this.hkgs = hkgs;
	}
	public String getZcdm() {
		return zcdm;
	}
	public void setZcdm(String zcdm) {
		this.zcdm = zcdm;
	}
	public Integer getDrzxlr() {
		return drzxlr;
	}
	public void setDrzxlr(Integer drzxlr) {
		this.drzxlr = drzxlr;
	}
	public String getSfdjcp() {
		return sfdjcp;
	}
	public void setSfdjcp(String sfdjcp) {
		this.sfdjcp = sfdjcp;
	}
	public String getKqbspet() {
		return kqbspet;
	}
	public void setKqbspet(String kqbspet) {
		this.kqbspet = kqbspet;
	}
	public String getBspOffice() {
		return bspOffice;
	}
	public void setBspOffice(String bspOffice) {
		this.bspOffice = bspOffice;
	}
	public String getBspAgent() {
		return bspAgent;
	}
	public void setBspAgent(String bspAgent) {
		this.bspAgent = bspAgent;
	}
	public String getBspPrintno() {
		return bspPrintno;
	}
	public void setBspPrintno(String bspPrintno) {
		this.bspPrintno = bspPrintno;
	}
	public String getKqbop() {
		return kqbop;
	}
	public void setKqbop(String kqbop) {
		this.kqbop = kqbop;
	}
	public String getBopOffice() {
		return bopOffice;
	}
	public void setBopOffice(String bopOffice) {
		this.bopOffice = bopOffice;
	}
	public String getBopAgent() {
		return bopAgent;
	}
	public void setBopAgent(String bopAgent) {
		this.bopAgent = bopAgent;
	}
	public String getHclx() {
		return hclx;
	}
	public void setHclx(String hclx) {
		this.hclx = hclx;
	}
	public String getCjrlx() {
		return cjrlx;
	}
	public void setCjrlx(String cjrlx) {
		this.cjrlx = cjrlx;
	}
	public String getCjrqs() {
		return cjrqs;
	}
	public void setCjrqs(String cjrqs) {
		this.cjrqs = cjrqs;
	}
	public String getCjrqz() {
		return cjrqz;
	}
	public void setCjrqz(String cjrqz) {
		this.cjrqz = cjrqz;
	}
	public String getBxsrq() {
		return bxsrq;
	}
	public void setBxsrq(String bxsrq) {
		this.bxsrq = bxsrq;
	}
	public String getCfcs() {
		return cfcs;
	}
	public void setCfcs(String cfcs) {
		this.cfcs = cfcs;
	}
	public String getDdcs() {
		return ddcs;
	}
	public void setDdcs(String ddcs) {
		this.ddcs = ddcs;
	}
	public String getHbhsytj() {
		return hbhsytj;
	}
	public void setHbhsytj(String hbhsytj) {
		this.hbhsytj = hbhsytj;
	}
	public String getHbh() {
		return hbh;
	}
	public void setHbh(String hbh) {
		this.hbh = hbh;
	}
	public String getBsyhbh() {
		return bsyhbh;
	}
	public void setBsyhbh(String bsyhbh) {
		this.bsyhbh = bsyhbh;
	}
	public String getCw() {
		return cw;
	}
	public void setCw(String cw) {
		this.cw = cw;
	}
	public Integer getZwtqcpts() {
		return zwtqcpts;
	}
	public void setZwtqcpts(Integer zwtqcpts) {
		this.zwtqcpts = zwtqcpts;
	}
	public Integer getZztqcpts() {
		return zztqcpts;
	}
	public void setZztqcpts(Integer zztqcpts) {
		this.zztqcpts = zztqcpts;
	}
	public Integer getDdrsxzs() {
		return ddrsxzs;
	}
	public void setDdrsxzs(Integer ddrsxzs) {
		this.ddrsxzs = ddrsxzs;
	}
	public Integer getDdrsxzz() {
		return ddrsxzz;
	}
	public void setDdrsxzz(Integer ddrsxzz) {
		this.ddrsxzz = ddrsxzz;
	}
	public Integer getNlxzs() {
		return nlxzs;
	}
	public void setNlxzs(Integer nlxzs) {
		this.nlxzs = nlxzs;
	}
	public Integer getNlxzz() {
		return nlxzz;
	}
	public void setNlxzz(Integer nlxzz) {
		this.nlxzz = nlxzz;
	}
	public String getCjrmzh() {
		return cjrmzh;
	}
	public void setCjrmzh(String cjrmzh) {
		this.cjrmzh = cjrmzh;
	}
	public String getSygj() {
		return sygj;
	}
	public void setSygj(String sygj) {
		this.sygj = sygj;
	}
	public String getBsygj() {
		return bsygj;
	}
	public void setBsygj(String bsygj) {
		this.bsygj = bsygj;
	}
	public String getOsixjc() {
		return osixjc;
	}
	public void setOsixjc(String osixjc) {
		this.osixjc = osixjc;
	}
	public String getSfhbmcp() {
		return sfhbmcp;
	}
	public void setSfhbmcp(String sfhbmcp) {
		this.sfhbmcp = sfhbmcp;
	}
	public String getKqybmcc() {
		return kqybmcc;
	}
	public void setKqybmcc(String kqybmcc) {
		this.kqybmcc = kqybmcc;
	}
	public String getHbmosix() {
		return hbmosix;
	}
	public void setHbmosix(String hbmosix) {
		this.hbmosix = hbmosix;
	}
	public String getHbmoffice() {
		return hbmoffice;
	}
	public void setHbmoffice(String hbmoffice) {
		this.hbmoffice = hbmoffice;
	}
}
