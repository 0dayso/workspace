package cn.vetech.vedsb.jp.entity.jpxepnr;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_XEPNR")
public class JpXepnr extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**主键id*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**订单编号*/
	private String ddbh;
	/**pnr*/
	private String pnrNo;
	/**office号*/
	private String officeid;
	/**乘机人信息:乘机人姓名,证件号|…*/
	private String cjr;
	/**航段信息*/
	private String hd;
	/**是否验证证件0否，1是*/
	private String sfyzzj;
	/**是否验证航段0否，1是*/
	private String sfyzhd;
	/**取消类型XEPNR取消编码,XECJR取消座位*/
	private String xelx;
	/**取消事由*/
	private String xesy;
	/**取消状态0待取消，1已取消，2取消失败*/
	private String xezt;
	/**取消失败原因*/
	private String xesbyy;
	/**取消用户*/
	private String xeyh;
	/**取消时间*/
	private Date xesj;
	/**操作来源,降舱出票，换编码出票，退票*/
	private String czly;
	/**商户编号*/
	private String shbh;
	/**创建用户*/
	private String cjyh;
	/**创建时间*/
	private Date cjsj;
	
	/**
	 * PNR内容
	 */
	private String pnrLr;

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setDdbh(String ddbh){
		this.ddbh=StringUtils.trim(ddbh);
	}
	public String getDdbh(){
		return this.ddbh;
	}

	public void setPnrNo(String pnrNo){
		this.pnrNo=StringUtils.trim(pnrNo);
	}
	public String getPnrNo(){
		return this.pnrNo;
	}

	public void setOfficeid(String officeid){
		this.officeid=StringUtils.trim(officeid);
	}
	public String getOfficeid(){
		return this.officeid;
	}

	public void setCjr(String cjr){
		this.cjr=StringUtils.trim(cjr);
	}
	public String getCjr(){
		return this.cjr;
	}

	public void setHd(String hd){
		this.hd=StringUtils.trim(hd);
	}
	public String getHd(){
		return this.hd;
	}

	public void setSfyzzj(String sfyzzj){
		this.sfyzzj=StringUtils.trim(sfyzzj);
	}
	public String getSfyzzj(){
		return this.sfyzzj;
	}

	public void setSfyzhd(String sfyzhd){
		this.sfyzhd=StringUtils.trim(sfyzhd);
	}
	public String getSfyzhd(){
		return this.sfyzhd;
	}

	public void setXelx(String xelx){
		this.xelx=StringUtils.trim(xelx);
	}
	public String getXelx(){
		return this.xelx;
	}

	public void setXesy(String xesy){
		this.xesy=StringUtils.trim(xesy);
	}
	public String getXesy(){
		return this.xesy;
	}

	public void setXezt(String xezt){
		this.xezt=StringUtils.trim(xezt);
	}
	public String getXezt(){
		return this.xezt;
	}

	public void setXesbyy(String xesbyy){
		this.xesbyy=StringUtils.trim(xesbyy);
	}
	public String getXesbyy(){
		return this.xesbyy;
	}

	public void setXeyh(String xeyh){
		this.xeyh=StringUtils.trim(xeyh);
	}
	public String getXeyh(){
		return this.xeyh;
	}

	public void setCzly(String czly){
		this.czly=StringUtils.trim(czly);
	}
	public String getCzly(){
		return this.czly;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setCjyh(String cjyh){
		this.cjyh=StringUtils.trim(cjyh);
	}
	public String getCjyh(){
		return this.cjyh;
	}
	public Date getXesj() {
		return xesj;
	}
	public void setXesj(Date xesj) {
		this.xesj = xesj;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public String getPnrLr() {
		return pnrLr;
	}
	public void setPnrLr(String pnrLr) {
		this.pnrLr = StringUtils.trim(pnrLr);;
	}
  
	
}