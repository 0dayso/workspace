package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="jp_tjsq_dr")
public class JpTjsqDr extends AbstractPageEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1019782700105907534L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	
	private String pnrNo;//导入订单的pnr
	
	private String pnrHc;//航程
	
	private String pnrHb;//航班
	
	private String pnrHkgs;//航空公司
	
	private String pnrCfrqsj;//起飞时间
	
	private String pnrCjr;//乘机人
	
	private String pnrCjrzjh;//乘机人证件号
	
	private String sqZt;//申请状态
	
	private Date sqDatetime;//申请时间
	
	private String lxr;//联系人
	
	private String lxdh;//联系电话
	
	private Date cpDatetime;//出票时间
	
	private String sftjzw;//是否提交追位 1是,0否
	
	private Date drsj;//导入时间
	
	private String sqdh;//申请单号
	
	private String by1;
	
	private String by2;
	
	private String by3;
	
	private Date cjrqz;//乘机日期止
	
	private Date cjrqs;//乘机日期始
	
	private double pjfw;//票价范围
	
	private double zkfw;//折扣范围
	
	private String cfcity;//出发城市
	
	private String ddcity;//到达城市
	
	private String ddbh;//订单编号
	
	private String sqUserid;//申请人
	
	private String sqDeptid;//申请部门
	
	private String shbh;//商户编号
	
	private String pnrCw;//舱位
	
	private Date clDatetime;//处理时间
	
	private String clUserid;//处理人
	
	private String clDeptid;//处理部门
	
	private Date tjsj;//提交时间
	
	private String pnrNpnr;//追位成功后生成新的pnr
	
	private String mbcw;//目标舱位
	
	private String gngj;//国内国际
	
	private String pnrCjrzjlx;//乘机人证件类型--身份证 NI--护照 P--其他--ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public String getPnrHc() {
		return pnrHc;
	}

	public void setPnrHc(String pnrHc) {
		this.pnrHc = pnrHc;
	}

	public String getPnrHb() {
		return pnrHb;
	}

	public void setPnrHb(String pnrHb) {
		this.pnrHb = pnrHb;
	}

	public String getPnrHkgs() {
		return pnrHkgs;
	}

	public void setPnrHkgs(String pnrHkgs) {
		this.pnrHkgs = pnrHkgs;
	}

	public String getPnrCjr() {
		return pnrCjr;
	}

	public void setPnrCjr(String pnrCjr) {
		this.pnrCjr = pnrCjr;
	}

	public String getPnrCjrzjh() {
		return pnrCjrzjh;
	}

	public void setPnrCjrzjh(String pnrCjrzjh) {
		this.pnrCjrzjh = pnrCjrzjh;
	}

	public String getSqZt() {
		return sqZt;
	}

	public void setSqZt(String sqZt) {
		this.sqZt = sqZt;
	}
	
	public Date getSqDatetime() {
		return sqDatetime;
	}

	public void setSqDatetime(Date sqDatetime) {
		this.sqDatetime = sqDatetime;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public Date getCpDatetime() {
		return cpDatetime;
	}

	public void setCpDatetime(Date cpDatetime) {
		this.cpDatetime = cpDatetime;
	}

	public String getSftjzw() {
		return sftjzw;
	}

	public void setSftjzw(String sftjzw) {
		this.sftjzw = sftjzw;
	}
	
	public Date getDrsj() {
		return drsj;
	}

	public void setDrsj(Date drsj) {
		this.drsj = drsj;
	}

	public String getSqdh() {
		return sqdh;
	}

	public void setSqdh(String sqdh) {
		this.sqdh = sqdh;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	public Date getCjrqz() {
		return cjrqz;
	}

	public void setCjrqz(Date cjrqz) {
		this.cjrqz = cjrqz;
	}

	public Date getCjrqs() {
		return cjrqs;
	}

	public void setCjrqs(Date cjrqs) {
		this.cjrqs = cjrqs;
	}

	public String getCfcity() {
		return cfcity;
	}

	public void setCfcity(String cfcity) {
		this.cfcity = cfcity;
	}

	public String getDdcity() {
		return ddcity;
	}

	public void setDdcity(String ddcity) {
		this.ddcity = ddcity;
	}

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}

	public String getSqUserid() {
		return sqUserid;
	}

	public void setSqUserid(String sqUserid) {
		this.sqUserid = sqUserid;
	}

	public String getSqDeptid() {
		return sqDeptid;
	}

	public void setSqDeptid(String sqDeptid) {
		this.sqDeptid = sqDeptid;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getPnrCw() {
		return pnrCw;
	}

	public void setPnrCw(String pnrCw) {
		this.pnrCw = pnrCw;
	}

	public Date getClDatetime() {
		return clDatetime;
	}

	public void setClDatetime(Date clDatetime) {
		this.clDatetime = clDatetime;
	}

	public String getClUserid() {
		return clUserid;
	}

	public void setClUserid(String clUserid) {
		this.clUserid = clUserid;
	}

	public String getClDeptid() {
		return clDeptid;
	}

	public void setClDeptid(String clDeptid) {
		this.clDeptid = clDeptid;
	}

	public Date getTjsj() {
		return tjsj;
	}

	public void setTjsj(Date tjsj) {
		this.tjsj = tjsj;
	}

	public String getPnrNpnr() {
		return pnrNpnr;
	}

	public void setPnrNpnr(String pnrNpnr) {
		this.pnrNpnr = pnrNpnr;
	}

	public String getMbcw() {
		return mbcw;
	}

	public void setMbcw(String mbcw) {
		this.mbcw = mbcw;
	}

	public String getGngj() {
		return gngj;
	}

	public void setGngj(String gngj) {
		this.gngj = gngj;
	}

	public double getPjfw() {
		return pjfw;
	}

	public void setPjfw(double pjfw) {
		this.pjfw = pjfw;
	}

	public double getZkfw() {
		return zkfw;
	}

	public void setZkfw(double zkfw) {
		this.zkfw = zkfw;
	}

	public String getPnrCfrqsj() {
		return pnrCfrqsj;
	}

	public void setPnrCfrqsj(String pnrCfrqsj) {
		this.pnrCfrqsj = pnrCfrqsj;
	}

	public String getPnrCjrzjlx() {
		return pnrCjrzjlx;
	}

	public void setPnrCjrzjlx(String pnrCjrzjlx) {
		this.pnrCjrzjlx = pnrCjrzjlx;
	}
	/**
	 * 将导入单转换成JpTjsq
	 * @return
	 */
	@Transient
	public JpTjsq copyToJptjsq(){
		JpTjsq jpTjsq=new JpTjsq();
		jpTjsq.setLxr(lxr);
		jpTjsq.setLxdh(lxdh);
		jpTjsq.setHkgs(pnrHkgs);
		jpTjsq.setCfcity(cfcity);
		jpTjsq.setDdcity(ddcity);
		jpTjsq.setCjrqs(cjrqs);
		jpTjsq.setCjrqz(cjrqz);
		jpTjsq.setJglx("1");
		jpTjsq.setZkfw(0.0);
		jpTjsq.setCjrs(1);
		jpTjsq.setNffkyd("0");
		jpTjsq.setSfzd("1");
		String hbsk = pnrCfrqsj;
		if(StringUtils.isBlank(hbsk)){
			hbsk = hbsk.substring(11, 16);
			jpTjsq.setHbsks(hbsk);
			jpTjsq.setHbskz(hbsk);
		}else {
			jpTjsq.setHbsks("00:00");
			jpTjsq.setHbskz("23:59");
		}
		jpTjsq.setYxq(cjrqs);
		jpTjsq.setDdbh(ddbh);
		jpTjsq.setSqZt("1");
		jpTjsq.setSqDatetime(sqDatetime);
		jpTjsq.setSqUserid(sqUserid);
		jpTjsq.setSqDeptid(sqDeptid);
		jpTjsq.setShbh(shbh);
		jpTjsq.setCkDatetime(sqDatetime);
		jpTjsq.setCkUserid(sqUserid);
		jpTjsq.setCkDeptid(sqDeptid);
		jpTjsq.setZfFkf("1");
		jpTjsq.setZfZfje(pjfw);
		jpTjsq.setZfZfsj(cpDatetime);
		jpTjsq.setZfUserid(sqUserid);
		jpTjsq.setZfDeptid(sqDeptid);
		jpTjsq.setTkFkf("0");
		jpTjsq.setTkZfje(0.0);
		jpTjsq.setTkUserid("");
		jpTjsq.setYpnrNo(pnrNo);
		jpTjsq.setDpDatetime(cpDatetime);
		jpTjsq.setGngj("1");
		jpTjsq.setHbh(pnrHb);
		jpTjsq.setClZt("0");
		jpTjsq.setQftime(cjrqz);
		jpTjsq.setYcw(pnrCw);
		jpTjsq.setYpnrZt("HK");
		jpTjsq.setZwZt("1");
		jpTjsq.setSftjzw("0");
		jpTjsq.setSfwbdr("1");
		jpTjsq.setCpDatetime(cpDatetime);
		return jpTjsq;
	}
}
