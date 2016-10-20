package cn.vetech.vedsb.jp.entity.cgptsz;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;
@Table(name="JP_PT_LOG")
public class JpPtLog extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**主键*/
	@Id
	private String id;
	/**平台*/
	private String ptzcbs;
	/**订单编号*/
	private String ddbh;
	/**编码*/
	private String pnrNo;
	/**订单类型,1正常单,2退废单,3改签单*/
	private String ddlx;
	/**退废单ID*/
	private String tfid;
	/**业务类型*/
	private String ywlx;
	/**文本日志路径*/
	private String rzlj;
	/**操作时间*/
	private Date logDate;
	/**商户编号*/
	private String shbh;
	/**操作用户*/
	private String yhbh;
	/**操作说明*/
	private String czsm;
	/**2交票成功 3 交票失败*/
	private String jpzt;
	/**网店ID，目前只有供应日志才有值**/
	private String wdid;
	/**网店名称，目前只有供应日志才有值**/
	private String wdmc;
	/**产品类型，存放数据字典的产品类型，类别10019*/
	private String by1;
	/**1采购2供应*/
	private String by2;
	/**null*/
	private String by3;
	
	@Transient
	private StringBuffer infobuf = new StringBuffer();

	/**
	 * 记录日志
	 * @param info
	 */
	public void add(String info) {
		infobuf.append(VeDate.getStringDate() + "==>");
		infobuf.append(StringUtils.isNotBlank(shbh) ? "|商户:" + shbh : ""); 
		infobuf.append(StringUtils.isNotBlank(ddbh) ? "|订单:" + ddbh : ""); 
		infobuf.append(info + "\r\n");
	}
	/**
	 * 记录日志2
	 * 不添加其他额外信息记录用
	 * @param info
	 */
	public void add2(String info) {
		infobuf.append(info + "\r\n");
	}
	public void clearInfo(){
		infobuf = new StringBuffer("");
	}
	
	public String getInfo() {
		return infobuf.toString();
	}
	
	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return this.id;
	}

	public void setPtzcbs(String ptzcbs){
		this.ptzcbs=StringUtils.trim(ptzcbs);
	}
	public String getPtzcbs(){
		return this.ptzcbs;
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

	public void setDdlx(String ddlx){
		this.ddlx=StringUtils.trim(ddlx);
	}
	public String getDdlx(){
		return this.ddlx;
	}

	public void setTfid(String tfid){
		this.tfid=StringUtils.trim(tfid);
	}
	public String getTfid(){
		return this.tfid;
	}

	public void setYwlx(String ywlx){
		this.ywlx=StringUtils.trim(ywlx);
	}
	public String getYwlx(){
		return this.ywlx;
	}

	public void setRzlj(String rzlj){
		this.rzlj=StringUtils.trim(rzlj);
	}
	public String getRzlj(){
		return this.rzlj;
	}

	public void setLogDate(Date logDate){
		this.logDate=logDate;
	}
	public Date getLogDate(){
		return this.logDate;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setYhbh(String yhbh){
		this.yhbh=StringUtils.trim(yhbh);
	}
	public String getYhbh(){
		return this.yhbh;
	}

	public void setCzsm(String czsm){
		this.czsm=StringUtils.trim(czsm);
	}
	public String getCzsm(){
		return this.czsm;
	}

	public void setJpzt(String jpzt){
		this.jpzt=StringUtils.trim(jpzt);
	}
	public String getJpzt(){
		return this.jpzt;
	}

	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	
	public String getWdmc() {
		return wdmc;
	}
	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
	}
	
	public void setBy1(String by1){
		this.by1=StringUtils.trim(by1);
	}
	public String getBy1(){
		return this.by1;
	}

	public void setBy2(String by2){
		this.by2=StringUtils.trim(by2);
	}
	public String getBy2(){
		return this.by2;
	}

	public void setBy3(String by3){
		this.by3=StringUtils.trim(by3);
	}
	public String getBy3(){
		return this.by3;
	}

}