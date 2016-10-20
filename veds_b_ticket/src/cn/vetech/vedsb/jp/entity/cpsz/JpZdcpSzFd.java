/**
*@Title:       
*@Description: 
*@Author:      
*@Version:     1.1
*@Date:        2016-05-06
*@Modify:      
*/

package cn.vetech.vedsb.jp.entity.cpsz;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "JP_ZDCP_SZ_FD")
public class JpZdcpSzFd extends AbstractPageEntity {
	private static final long serialVersionUID = 2485676299130408809L;
	@Id
    @GeneratedValue(generator="uuid")
	/**主键*/	private String id; 	/**出票规则编号*/	private String gzbh; 	/**返点范围始/票价范围始*/	private BigDecimal fdfws; 	/**返点范围止/票价范围止*/	private BigDecimal fdfwz; 	/**三方协议号*/	private String sfxyh; 	/**是否换编码，1是，0否*/	private String sfhbm; 	/**是否检查OSI项，0不检查，1检查订单乘机人手机号，2检查PNR内容中CTCT项，3订单乘机人手机号与PNR内容中CTCT项都要检查*/	private String osi; 	/**OSI手机号，勾选换编码时，可以指定OSI项手机号*/	private String osisjh; 	/**计算最小利润方式：1.订单最小利润，0.单人最小利润*/	private String zxlrfs; 	/**自动出票账号ID，T_ZDCP_B2BZH表主键*/	private String zdcpzhid; 	/**出票渠道类型设置,1BSPET,2BPET,3B2C商旅卡4B2C会员5BOP，6综合比价*/	private String cpqdlx; 	/**OFFICE号*/	private String office; 	/**工作号*/	private String agent; 	/**打票机号*/	private String printno; 	/**航空公司基础返点*/	private BigDecimal hkgsC; 	/**航空公司Z值*/	private BigDecimal hkgsZ; 	/**后返*/	private BigDecimal hf; 	/**最小利润*/	private BigDecimal zxlr; 	/**使用协议号的后返止*/	private BigDecimal xyhhfz; 	/**使用协议号的后返始*/	private BigDecimal xyhhfs; 	/**是否指定仓位,1是 ,0否。暂不使用*/	private String sfzdcw; 	/**备注信息*/	private String bzbz; 	/**备用1*/	private String by1; 	/**备用2*/	private String by2; 	/**BSPET时是否自动出票 1是 ；0 否*/	private String by3; 	/**采购净价差额，为空时，表示不限制，可输入两位小数的金额，单位为元，按单人来判断,本地BPET减OP政策的差额*/	private String drcgjjce; 	/**换编码失败后，是否按原编码出票，空或0否，1是。默认为0*/	private String sfybmcp; 	/**是否开启UATP出票，1开启，空或0不开启*/	private String uatp; 	/**综合比价代理费率与平台政策返点范围差值(比价代理费率-政策返点>fdfwcz则可自动出票，空值也可自动出票)*/	private BigDecimal fdfwcz;

		public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}		public String getGzbh() {
		return gzbh;
	}
	public void setGzbh(String gzbh) {
		this.gzbh = gzbh;
	}		public BigDecimal getFdfws() {
		return fdfws;
	}
	public void setFdfws(BigDecimal fdfws) {
		this.fdfws = fdfws;
	}		public BigDecimal getFdfwz() {
		return fdfwz;
	}
	public void setFdfwz(BigDecimal fdfwz) {
		this.fdfwz = fdfwz;
	}		public String getSfxyh() {
		return sfxyh;
	}
	public void setSfxyh(String sfxyh) {
		this.sfxyh = sfxyh;
	}		public String getSfhbm() {
		return sfhbm;
	}
	public void setSfhbm(String sfhbm) {
		this.sfhbm = sfhbm;
	}		public String getOsi() {
		return osi;
	}
	public void setOsi(String osi) {
		this.osi = osi;
	}		public String getOsisjh() {
		return osisjh;
	}
	public void setOsisjh(String osisjh) {
		this.osisjh = osisjh;
	}		public String getZxlrfs() {
		return zxlrfs;
	}
	public void setZxlrfs(String zxlrfs) {
		this.zxlrfs = zxlrfs;
	}		public String getZdcpzhid() {
		return zdcpzhid;
	}
	public void setZdcpzhid(String zdcpzhid) {
		this.zdcpzhid = zdcpzhid;
	}		public String getCpqdlx() {
		return cpqdlx;
	}
	public void setCpqdlx(String cpqdlx) {
		this.cpqdlx = cpqdlx;
	}		public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}		public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}		public String getPrintno() {
		return printno;
	}
	public void setPrintno(String printno) {
		this.printno = printno;
	}		public BigDecimal getHkgsC() {
		return hkgsC;
	}
	public void setHkgsC(BigDecimal hkgsC) {
		this.hkgsC = hkgsC;
	}		public BigDecimal getHkgsZ() {
		return hkgsZ;
	}
	public void setHkgsZ(BigDecimal hkgsZ) {
		this.hkgsZ = hkgsZ;
	}		public BigDecimal getHf() {
		return hf;
	}
	public void setHf(BigDecimal hf) {
		this.hf = hf;
	}		public BigDecimal getZxlr() {
		return zxlr;
	}
	public void setZxlr(BigDecimal zxlr) {
		this.zxlr = zxlr;
	}		public BigDecimal getXyhhfz() {
		return xyhhfz;
	}
	public void setXyhhfz(BigDecimal xyhhfz) {
		this.xyhhfz = xyhhfz;
	}		public BigDecimal getXyhhfs() {
		return xyhhfs;
	}
	public void setXyhhfs(BigDecimal xyhhfs) {
		this.xyhhfs = xyhhfs;
	}		public String getSfzdcw() {
		return sfzdcw;
	}
	public void setSfzdcw(String sfzdcw) {
		this.sfzdcw = sfzdcw;
	}		public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}		public String getBy1() {
		return by1;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}		public String getBy2() {
		return by2;
	}
	public void setBy2(String by2) {
		this.by2 = by2;
	}		public String getBy3() {
		return by3;
	}
	public void setBy3(String by3) {
		this.by3 = by3;
	}		public String getDrcgjjce() {
		return drcgjjce;
	}
	public void setDrcgjjce(String drcgjjce) {
		this.drcgjjce = drcgjjce;
	}		public String getSfybmcp() {
		return sfybmcp;
	}
	public void setSfybmcp(String sfybmcp) {
		this.sfybmcp = sfybmcp;
	}		public String getUatp() {
		return uatp;
	}
	public void setUatp(String uatp) {
		this.uatp = uatp;
	}		public BigDecimal getFdfwcz() {
		return fdfwcz;
	}
	public void setFdfwcz(BigDecimal fdfwcz) {
		this.fdfwcz = fdfwcz;
	}

}
