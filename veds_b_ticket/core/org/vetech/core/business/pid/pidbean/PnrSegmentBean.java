package org.vetech.core.business.pid.pidbean;

/**
 * 解析PNR内容---航段信息Bean
 * @author  gengxianyan
 * @version  [版本号, Apr 9, 2013]
 * @see  [相关类/方法]
 * @since  [民航代理人系统(ASMS)/ASMS5000]
 */
public class PnrSegmentBean {

	private String id;
	private String sxh;
	private String hbh;
	private String fjjx;
	private String cw;
	private String zcw;//子舱位
	private String cfsj;
	private String ddsj;
	private String cfcity;
	private String ddcity;
	private String cfhzl;
	private String ddhzl;
	
	private double discount;//折扣
	private double price;//票价
	private int standPrice;//标准运价
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSxh() {
		return sxh;
	}
	public void setSxh(String sxh) {
		this.sxh = sxh;
	}
	public String getHbh() {
		return hbh;
	}
	public void setHbh(String hbh) {
		this.hbh = hbh;
	}
	public String getFjjx() {
		return fjjx;
	}
	public void setFjjx(String fjjx) {
		this.fjjx = fjjx;
	}
	public String getCw() {
		return cw;
	}
	public void setCw(String cw) {
		this.cw = cw;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getDdsj() {
		return ddsj;
	}
	public void setDdsj(String ddsj) {
		this.ddsj = ddsj;
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
	public String getCfhzl() {
		return cfhzl;
	}
	public void setCfhzl(String cfhzl) {
		this.cfhzl = cfhzl;
	}
	public String getDdhzl() {
		return ddhzl;
	}
	public void setDdhzl(String ddhzl) {
		this.ddhzl = ddhzl;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStandPrice() {
		return standPrice;
	}
	public void setStandPrice(int standPrice) {
		this.standPrice = standPrice;
	}
    public String getZcw() {
        return zcw;
    }
    public void setZcw(String zcw) {
        this.zcw = zcw;
    }
	
	
}
