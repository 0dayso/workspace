package cn.vetech.vedsb.jp.entity.xcd;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.dom4j.tree.AbstractEntity;

/**
 * 行程单打印信息
 * @version  [版本号, Oct 22, 2011]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Table(name = "JP_XCD_TICKET")
public class JpXcdTicket extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = -4356975032789468662L;
	@Id
    @GeneratedValue(generator="uuid")
	/**主键*/
	private String id;
	/**行程单号*/
	private String xcdh;
	/**PNR*/
	private String pnrNo;
	/**票号*/
	private String tkno;
	/**旅客姓名*/
	private String lkxm;
	/**证件号码*/
	private String zjhm;
	/**签注*/
	private String qz;
	/**票面价*/
	private String pjZdj;
	/**建设费*/
	private String pjJsf;
	/**税费*/
	private String pjTax;
	/**其他*/
	private String pjQt;
	/**合计（不含保险）*/
	private String pjHj;
	/**验证码*/
	private String yzm;
	/**提示信息*/
	private String tsxx;
	/**保险*/
	private String pjBx;
	/**OFFICE号*/
	private String office;
	/**销售单位代号*/
	private String dwdh;
	/**填开单位*/
	private String tkdw;
	/**填开日期*/
	private String tkrq;
	/**接口返回原始信息*/
	private String jkfhsj;
	/**总公司*/
	private String zgs;
	private String by1;
	/**打印数据来源 1 订单表 2 RT数据  3 DETR数据 4航信行程单数据*/
	private String by2;
	private String by3;
	/**创建时间*/
	private String cjDatetime;
	/**商户编号*/
	private String shbh;
	/**创建部门编号*/
	private String cjBmbh;
	/**创建用户编号*/
	private String cjYhbh;
	/**连续客票*/
	private String lxkp;
	
	//计价符号
	private String pj_fh;//
	private String jsf_fh;//
	private String tax_fh;//
	private String qt_fh;//
	private String hj_fh;//
	private String realtkno;//真实票号
	
	private String datafrom;//打印数据来源 1 订单表 2 RT数据  3 DETR数据 4航信行程单数据
	
	private String gp_no;//GP查验单号
	
	private String cplx;//出票类型  BSP BP  GP
	
	private String pd;//=
	
	private String ifgq;//是否改签 1表示改签
	
	private List<JpXcdTickethd> hdlist;
//	@Override
//	public String toString() {
//		String string="GP单号："+gp_no+"行程单号："+xcdh+" 票号："+tkno+" 连续票号："+lxkp+" PNR："+pnr_no+" \r\n乘机人姓名："+lkxm+" 证件号码："+zjhm+" 签注："+qz+"  \r\n票面价："+pj_zdj+" 建设费："+pj_jsf+" 其他："+pj_qt+" 燃油税："+pj_tax+" 合计："
//		+pj_hj+" \r\n验证码："+yzm+" 提示信息："+tsxx+" 保险费："+pj_bx+"\r\nOffice："+office+" 填开日期："+tkrq+" agent_code:"+dwdh+" 填开单位："+tkdw;
//		string+="\r\n【航段信息】：\r\n";
//		if(hdlist!=null&&hdlist.size()>0){
//			for (int i = 0; i < hdlist.size(); i++) {
//				JpXcdTickethd hInfo=hdlist.get(i);
//				string+="城市名称："+hInfo.getJcmc()+"  出发航站楼："+hInfo.getCfhzl()+" 到达航站楼："+hInfo.getDdhzl()+" 航空公司简称："+hInfo.getHkgsjc()+" "
//				+" 航班号："+hInfo.getHbh()+" 舱位等级："+hInfo.getZwdj()+" 出发日期："+hInfo.getCfrq()+" 出发时间："+hInfo.getCfsj()+" 客票类别/票价级别："+hInfo.getKpjb()+" 客票生效日期："+hInfo.getYxrq()+" 客票截止日期："+hInfo.getJzrq()+" 免费行李："+hInfo.getMfxl()+" \r\n";
//			}
//		}
//		return string;
//	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXcdh() {
		return xcdh;
	}
	public void setXcdh(String xcdh) {
		this.xcdh = xcdh;
	}
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	public String getLkxm() {
		return lkxm;
	}
	public void setLkxm(String lkxm) {
		this.lkxm = lkxm;
	}
	public String getZjhm() {
		return zjhm;
	}
	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}
	public String getQz() {
		return qz;
	}
	public void setQz(String qz) {
		this.qz = qz;
	}
	public String getPjZdj() {
		return pjZdj;
	}
	public void setPjZdj(String pjZdj) {
		this.pjZdj = pjZdj;
	}
	public String getPjJsf() {
		return pjJsf;
	}
	public void setPjJsf(String pjJsf) {
		this.pjJsf = pjJsf;
	}
	public String getPjTax() {
		return pjTax;
	}
	public void setPjTax(String pjTax) {
		this.pjTax = pjTax;
	}
	public String getPjQt() {
		return pjQt;
	}
	public void setPjQt(String pjQt) {
		this.pjQt = pjQt;
	}
	public String getPjHj() {
		return pjHj;
	}
	public void setPjHj(String pjHj) {
		this.pjHj = pjHj;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public String getTsxx() {
		return tsxx;
	}
	public void setTsxx(String tsxx) {
		this.tsxx = tsxx;
	}
	public String getPjBx() {
		return pjBx;
	}
	public void setPjBx(String pjBx) {
		this.pjBx = pjBx;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public String getDwdh() {
		return dwdh;
	}
	public void setDwdh(String dwdh) {
		this.dwdh = dwdh;
	}
	public String getTkdw() {
		return tkdw;
	}
	public void setTkdw(String tkdw) {
		this.tkdw = tkdw;
	}
	public String getTkrq() {
		return tkrq;
	}
	public void setTkrq(String tkrq) {
		this.tkrq = tkrq;
	}
	public String getJkfhsj() {
		return jkfhsj;
	}
	public void setJkfhsj(String jkfhsj) {
		this.jkfhsj = jkfhsj;
	}
	public String getZgs() {
		return zgs;
	}
	public void setZgs(String zgs) {
		this.zgs = zgs;
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
	public String getCjDatetime() {
		return cjDatetime;
	}
	public void setCjDatetime(String cjDatetime) {
		this.cjDatetime = cjDatetime;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getCjBmbh() {
		return cjBmbh;
	}
	public void setCjBmbh(String cjBmbh) {
		this.cjBmbh = cjBmbh;
	}
	public String getCjYhbh() {
		return cjYhbh;
	}
	public void setCjYhbh(String cjYhbh) {
		this.cjYhbh = cjYhbh;
	}
	public String getLxkp() {
		return lxkp;
	}
	public void setLxkp(String lxkp) {
		this.lxkp = lxkp;
	}
	public String getPj_fh() {
		return pj_fh;
	}
	public void setPj_fh(String pj_fh) {
		this.pj_fh = pj_fh;
	}
	public String getJsf_fh() {
		return jsf_fh;
	}
	public void setJsf_fh(String jsf_fh) {
		this.jsf_fh = jsf_fh;
	}
	public String getTax_fh() {
		return tax_fh;
	}
	public void setTax_fh(String tax_fh) {
		this.tax_fh = tax_fh;
	}
	public String getQt_fh() {
		return qt_fh;
	}
	public void setQt_fh(String qt_fh) {
		this.qt_fh = qt_fh;
	}
	public String getHj_fh() {
		return hj_fh;
	}
	public void setHj_fh(String hj_fh) {
		this.hj_fh = hj_fh;
	}
	public String getRealtkno() {
		return realtkno;
	}
	public void setRealtkno(String realtkno) {
		this.realtkno = realtkno;
	}
	public String getDatafrom() {
		return datafrom;
	}
	public void setDatafrom(String datafrom) {
		this.datafrom = datafrom;
	}
	public String getGp_no() {
		return gp_no;
	}
	public void setGp_no(String gp_no) {
		this.gp_no = gp_no;
	}
	public String getCplx() {
		return cplx;
	}
	public void setCplx(String cplx) {
		this.cplx = cplx;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getIfgq() {
		return ifgq;
	}
	public void setIfgq(String ifgq) {
		this.ifgq = ifgq;
	}
	public List<JpXcdTickethd> getHdlist() {
		return hdlist;
	}
	public void setHdlist(List<JpXcdTickethd> hdlist) {
		this.hdlist = hdlist;
	}
}
