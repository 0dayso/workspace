package cn.vetech.vedsb.jp.entity.cgptsz;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_CGDD")
public class JpCgdd extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**null*/
	@Id
	private String id;
	/**系统订单*/
	private String ddbh;
	/**0 等待支付 1 出票完成 2 待出票 5客票挂起 -1 订单取消*/
	private String zt;
	/**创建用户*/
	private String cjUserid;
	/**创建时间*/
	private Date cjdatetime;
	/**错误信息*/
	private String error;
	/**orderbean的xml系统*/
	private String submitParam;
	/**下单到航空公司*/
	private String orderhkgs;
	/**承运航空公司二字码*/
	private String hkgs;
	/**航空公司订单号，外采单号*/
	private String hkgsDdbh;
	/**pnr*/
	private String pnrno;
	/**航空公司pnr*/
	private String hkgsPnrno;
	/**支付方式*/
	private String paykind;
	/**支付流水号*/
	private String tradeNo;
	/**支付账号*/
	private String zfzh;
	/**支付金额*/
	private Double zfje;
	/**航空公司账号*/
	private String hkgszh;
	/**航班号*/
	private String hbh;
	/**舱位*/
	private String cw;
	/**舱位票价*/
	private Double cwpj;
	/**是否全自动出票 1为是 0为否 默认为否*/
	private String ifqzdcp;
	/**此订单已扫描次数*/
	private Integer ysmcs;
	/**采购支付科目*/
	private String cgZfkm;
	/**给淘宝C站使用，0代码要XE编码，1代表不XE编码。默认0*/
	private String ifxepnr;
	/**采购来源*/
	private String cgly;
	/**采购单位*/
	private String cgdw;
	/**商户编号*/
	private String shbh;
	/**在线交易状态 0 初始  1 支付成功，2支付失败 3支付中 4 退款成功 5退款中  6退款失败*/
	private String jyzt;
	/**代购单号*/
	private String dgdh;
	/**平台采购状态：1预定成功,等待采购方支付 10：支付完成，等待出票 12：无法出票 13：出票完成 14：更换编码出票完成 21：退票处理中 22：无法退票 
	 * 31：废票处理中 32：无法废票 90：完成退费票 99：交易取消退款*/
	private String platzt;
	/**平台授权状态: 0需要授权且未授权 1授权成功 -1授权失败  */
	private String platsqzt;
	/** 授权失败原因 */
	private String sqsbyy;
	/**需要授权的office  */
	private String platoffice;
	
	private String bzbz;

	public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}
	public String getPlatzt() {
		return platzt;
	}
	public void setPlatzt(String platzt) {
		this.platzt = platzt;
	}
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

	public void setZt(String zt){
		this.zt=StringUtils.trim(zt);
	}
	public String getZt(){
		return this.zt;
	}

	public void setCjUserid(String cjUserid){
		this.cjUserid=StringUtils.trim(cjUserid);
	}
	public String getCjUserid(){
		return this.cjUserid;
	}

	public Date getCjdatetime() {
		return cjdatetime;
	}
	public void setCjdatetime(Date cjdatetime) {
		this.cjdatetime = cjdatetime;
	}
	public void setError(String error){
		this.error=StringUtils.trim(error);
	}
	public String getError(){
		return this.error;
	}

	public void setSubmitParam(String submitParam){
		this.submitParam=StringUtils.trim(submitParam);
	}
	public String getSubmitParam(){
		return this.submitParam;
	}

	public void setOrderhkgs(String orderhkgs){
		this.orderhkgs=StringUtils.trim(orderhkgs);
	}
	public String getOrderhkgs(){
		return this.orderhkgs;
	}

	public void setHkgs(String hkgs){
		this.hkgs=StringUtils.trim(hkgs);
	}
	public String getHkgs(){
		return this.hkgs;
	}

	public void setHkgsDdbh(String hkgsDdbh){
		this.hkgsDdbh=StringUtils.trim(hkgsDdbh);
	}
	public String getHkgsDdbh(){
		return this.hkgsDdbh;
	}

	public void setPnrno(String pnrno){
		this.pnrno=StringUtils.trim(pnrno);
	}
	public String getPnrno(){
		return this.pnrno;
	}

	public void setHkgsPnrno(String hkgsPnrno){
		this.hkgsPnrno=StringUtils.trim(hkgsPnrno);
	}
	public String getHkgsPnrno(){
		return this.hkgsPnrno;
	}

	public void setPaykind(String paykind){
		this.paykind=StringUtils.trim(paykind);
	}
	public String getPaykind(){
		return this.paykind;
	}

	public void setTradeNo(String tradeNo){
		this.tradeNo=StringUtils.trim(tradeNo);
	}
	public String getTradeNo(){
		return this.tradeNo;
	}

	public void setZfzh(String zfzh){
		this.zfzh=StringUtils.trim(zfzh);
	}
	public String getZfzh(){
		return this.zfzh;
	}

	public void setZfje(Double zfje){
		this.zfje=zfje;
	}
	public Double getZfje(){
		return this.zfje;
	}

	public void setHkgszh(String hkgszh){
		this.hkgszh=StringUtils.trim(hkgszh);
	}
	public String getHkgszh(){
		return this.hkgszh;
	}

	public void setHbh(String hbh){
		this.hbh=StringUtils.trim(hbh);
	}
	public String getHbh(){
		return this.hbh;
	}

	public void setCw(String cw){
		this.cw=StringUtils.trim(cw);
	}
	public String getCw(){
		return this.cw;
	}

	public void setCwpj(Double cwpj){
		this.cwpj=cwpj;
	}
	public Double getCwpj(){
		return this.cwpj;
	}

	public void setIfqzdcp(String ifqzdcp){
		this.ifqzdcp=StringUtils.trim(ifqzdcp);
	}
	public String getIfqzdcp(){
		return this.ifqzdcp;
	}

	public void setYsmcs(Integer ysmcs){
		this.ysmcs=ysmcs;
	}
	public Integer getYsmcs(){
		return this.ysmcs;
	}

	public void setCgZfkm(String cgZfkm){
		this.cgZfkm=StringUtils.trim(cgZfkm);
	}
	public String getCgZfkm(){
		return this.cgZfkm;
	}

	public void setIfxepnr(String ifxepnr){
		this.ifxepnr=StringUtils.trim(ifxepnr);
	}
	public String getIfxepnr(){
		return this.ifxepnr;
	}

	public void setCgly(String cgly){
		this.cgly=StringUtils.trim(cgly);
	}
	public String getCgly(){
		return this.cgly;
	}

	public void setCgdw(String cgdw){
		this.cgdw=StringUtils.trim(cgdw);
	}
	public String getCgdw(){
		return this.cgdw;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setJyzt(String jyzt){
		this.jyzt=StringUtils.trim(jyzt);
	}
	public String getJyzt(){
		return this.jyzt;
	}

	public void setDgdh(String dgdh){
		this.dgdh=StringUtils.trim(dgdh);
	}
	public String getDgdh(){
		return this.dgdh;
	}
	public String getPlatsqzt() {
		return platsqzt;
	}
	public void setPlatsqzt(String platsqzt) {
		this.platsqzt = platsqzt;
	}
	public String getSqsbyy() {
		return sqsbyy;
	}
	public void setSqsbyy(String sqsbyy) {
		this.sqsbyy = sqsbyy;
	}
	public String getPlatoffice() {
		return platoffice;
	}
	public void setPlatoffice(String platoffice) {
		this.platoffice = platoffice;
	}

}