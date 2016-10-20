package cn.vetech.vedsb.jp.entity.jpbcdgl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

@Table(name="jp_cyd")
public class JpCyd extends AbstractPageEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String id;
	
	private String wdpt;//网店平台
	
	private String wdid;//网店id
	
	private String wbdh;//外部单号

	private String shbh;//商户编号
	
	private String bcdh;//补差单号

	private String ddlx;//订单类型
	
	private String ddbh;//订单编号
	
	private String pnrNo;//pnr
	
	private String cylx;//差异类型
	
	private Double xtTpfl;//系统退票费率
	
	private Double xtTkje;//系统退款价格
	
	private Double wdTpfl;//网店退票费率
	
	private Double wdTkje;//网店退款价格
	
	private Double cyje;//差异金额
	
	private String cysm;//差异说明
	
	private String cjyh;//创建用户
	
	private Date cjsj;//创建时间
	
	private String shyh;//审核用户
	
	private Date shsj;//审核时间
	
	private String qyyh;//确认用户
	
	private Date qysj;//确认时间
	
	private String  zt;//状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}
	public String getWbdh() {
		return wbdh;
	}
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getBcdh() {
		return bcdh;
	}
	public void setBcdh(String bcdh) {
		this.bcdh = bcdh;
	}
	public String getDdlx() {
		return ddlx;
	}
	public void setDdlx(String ddlx) {
		this.ddlx = ddlx;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getCylx() {
		return cylx;
	}
	public void setCylx(String cylx) {
		this.cylx = cylx;
	}
	public String getCysm() {
		return cysm;
	}
	public void setCysm(String cysm) {
		this.cysm = cysm;
	}
	public String getCjyh() {
		return cjyh;
	}
	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}
	public String getShyh() {
		return shyh;
	}
	public void setShyh(String shyh) {
		this.shyh = shyh;
	}
	public String getQyyh() {
		return qyyh;
	}
	public void setQyyh(String qyyh) {
		this.qyyh = qyyh;
	}
	public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public Date getShsj() {
		return shsj;
	}
	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}
	public Date getQysj() {
		return qysj;
	}
	public void setQysj(Date qysj) {
		this.qysj = qysj;
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	@Transient
	public String getXgsjStr(){
		String xgsjLong = VeDate.dateToStrLong(this.cjsj);
		if(StringUtils.isNotBlank(xgsjLong) && xgsjLong.length()>17){
			xgsjLong = xgsjLong.substring(5,16);
		}
		return xgsjLong;	
	}
	
	@Transient
	public Double getWdTpflDouble(){
		if(null!=this.wdTpfl){
			BigDecimal   b   =   new   BigDecimal(this.wdTpfl*100d);  
			double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();  
		    return f1;
		}
		return null;
	}
	@Transient
	public String getWdTkjeString(){
		if(null !=this.wdTkje){
			String res = Double.toString(this.wdTkje);
			if(res.indexOf(".0")>-1){
				res = res.replace(".0", "");
			}
			return res;
		}
		return null;
	}
	
	@Transient
	public String getCyjeString(){
		if(null != this.cyje){
		String res = Double.toString(this.cyje);
			if(res.indexOf(".0")>-1){
				res = res.replace(".0", "");
			}
			return res;
		}
		return null;
	}
	
	@Transient
	public String getWbdhString(){
		if(StringUtils.isNotBlank(this.wbdh)){
			String res = "";
			if(this.wbdh.length()>12){
				res = this.wbdh.substring(0, 12)+"...";
			}else{
				res = this.wbdh;
			}
			return res;
		}
		return null;
	}
	
	@Transient
	public String getcysmString(){
		if(StringUtils.isNotBlank(this.cysm)){
			String res="";
			if(this.cysm.length()>10){
				res = this.cysm.substring(0, 10)+"...";
			}else{
				res = this.cysm;
			}
			return res;
		}
		return null;
	}
	
	public String getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}
	public Double getXtTpfl() {
		return xtTpfl;
	}
	public void setXtTpfl(Double xtTpfl) {
		this.xtTpfl = xtTpfl;
	}
	public Double getXtTkje() {
		return xtTkje;
	}
	public void setXtTkje(Double xtTkje) {
		this.xtTkje = xtTkje;
	}
	public Double getWdTpfl() {
		return wdTpfl;
	}
	public void setWdTpfl(Double wdTpfl) {
		this.wdTpfl = wdTpfl;
	}
	public Double getWdTkje() {
		return wdTkje;
	}
	public void setWdTkje(Double wdTkje) {
		this.wdTkje = wdTkje;
	}
	public Double getCyje() {
		return cyje;
	}
	public void setCyje(Double cyje) {
		this.cyje = cyje;
	}
}
