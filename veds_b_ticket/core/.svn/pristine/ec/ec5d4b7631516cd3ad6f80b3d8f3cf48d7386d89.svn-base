package org.vetech.core.business.pid.pidbean;

import java.util.List;
import java.util.Map;

/**
 * 预订公共ActionForm
 * @author  gengxianyan
 * @version  [版本号, Apr 24, 2012]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class BookForm {

	//公共字段
	private String pt;		//平台标示
	private String jrip;	//接入IP
	private String jrport;	//接入端口
	private String bookType;//0是eterm 其他是ibe
	private String userid;	//调用白屏接口账号
	private String kl;		//调用白屏接口密码
	private String searchHclx;//查询航程类型
	
	private String[] ifnoseat;//是否无座位并订票为当天标示
	
	private String[] index;//存放生成订单序号
	
	private List<List<Map>> khddList;//根据长度控制生成订单数，外层生成订单数，里层一个订单对应的航段信息
	
	private AsmsBookForm asmsBookForm;//ASMS平台预定ActionForm
	
	private String gngj;//国内/国际预订 1为国际 其他为国内
	
	private String zgscompid;
	
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public String getBookType() {
		return bookType;
	}
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}
	public String getJrip() {
		return jrip;
	}
	public void setJrip(String jrip) {
		this.jrip = jrip;
	}
	public String getJrport() {
		return jrport;
	}
	public void setJrport(String jrport) {
		this.jrport = jrport;
	}

	public String[] getIfnoseat() {
		return ifnoseat;
	}
	public void setIfnoseat(String[] ifnoseat) {
		this.ifnoseat = ifnoseat;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getKl() {
		return kl;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public AsmsBookForm getAsmsBookForm() {
		return asmsBookForm;
	}
	public void setAsmsBookForm(AsmsBookForm asmsBookForm) {
		this.asmsBookForm = asmsBookForm;
	}
	public String getSearchHclx() {
		return searchHclx;
	}
	public void setSearchHclx(String searchHclx) {
		this.searchHclx = searchHclx;
	}
	
	public List<List<Map>> getKhddList() {
		return khddList;
	}
	public void setKhddList(List<List<Map>> khddList) {
		this.khddList = khddList;
	}
	public String[] getIndex() {
		return index;
	}
	public void setIndex(String[] index) {
		this.index = index;
	}
	public String getGngj() {
		return gngj;
	}
	public void setGngj(String gngj) {
		this.gngj = gngj;
	}
	public String getZgscompid() {
		return zgscompid;
	}
	public void setZgscompid(String zgscompid) {
		this.zgscompid = zgscompid;
	}
}
