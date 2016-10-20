package cn.vetech.vedsb.jp.entity.jpzwgl;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_TJSQ_CJR_ZDSC")
public class JpTjsqCjrZdsc extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**null*/
	@Id
	private String id;
	/**null*/
	private String sqdh;
	/**null*/
	private String cjr;
	/**null*/
	private String zjlx;
	/**null*/
	private String zjhm;
	/**null*/
	private String by1;
	/**null*/
	private String by2;
	/**null*/
	private String by3;
	/**null*/
	private String sfzwcg;
	/**null*/
	private String pnrNo;
	/**null*/
	private String dpDdbh;
	/**null*/
	private String cjrid;
	/**null*/
	private String dpCjrid;
	/**null*/
	private Date clDatetime;
	/**null*/
	private String clUserid;
	/**null*/
	private String clDeptid;
	/**null*/
	private String shbh;
	/**null*/
	private String cw;
	/**null*/
	private Double price;
	/**null*/
	private Double yprice;
	/**null*/
	private String qxYy;
	/**null*/
	private String qxSm;

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setSqdh(String sqdh){
		this.sqdh=StringUtils.trim(sqdh);
	}
	public String getSqdh(){
		return this.sqdh;
	}

	public void setCjr(String cjr){
		this.cjr=StringUtils.trim(cjr);
	}
	public String getCjr(){
		return this.cjr;
	}

	public void setZjlx(String zjlx){
		this.zjlx=StringUtils.trim(zjlx);
	}
	public String getZjlx(){
		return this.zjlx;
	}

	public void setZjhm(String zjhm){
		this.zjhm=StringUtils.trim(zjhm);
	}
	public String getZjhm(){
		return this.zjhm;
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

	public void setSfzwcg(String sfzwcg){
		this.sfzwcg=StringUtils.trim(sfzwcg);
	}
	public String getSfzwcg(){
		return this.sfzwcg;
	}

	public void setPnrNo(String pnrNo){
		this.pnrNo=StringUtils.trim(pnrNo);
	}
	public String getPnrNo(){
		return this.pnrNo;
	}

	public void setDpDdbh(String dpDdbh){
		this.dpDdbh=StringUtils.trim(dpDdbh);
	}
	public String getDpDdbh(){
		return this.dpDdbh;
	}

	public void setCjrid(String cjrid){
		this.cjrid=StringUtils.trim(cjrid);
	}
	public String getCjrid(){
		return this.cjrid;
	}

	public void setDpCjrid(String dpCjrid){
		this.dpCjrid=StringUtils.trim(dpCjrid);
	}
	public String getDpCjrid(){
		return this.dpCjrid;
	}

	public void setClDatetime(Date clDatetime){
		this.clDatetime=clDatetime;
	}
	public Date getClDatetime(){
		return this.clDatetime;
	}

	public void setClUserid(String clUserid){
		this.clUserid=StringUtils.trim(clUserid);
	}
	public String getClUserid(){
		return this.clUserid;
	}

	public void setClDeptid(String clDeptid){
		this.clDeptid=StringUtils.trim(clDeptid);
	}
	public String getClDeptid(){
		return this.clDeptid;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setCw(String cw){
		this.cw=StringUtils.trim(cw);
	}
	public String getCw(){
		return this.cw;
	}

	public void setPrice(Double price){
		this.price=price;
	}
	public Double getPrice(){
		return this.price;
	}

	public void setYprice(Double yprice){
		this.yprice=yprice;
	}
	public Double getYprice(){
		return this.yprice;
	}

	public void setQxYy(String qxYy){
		this.qxYy=StringUtils.trim(qxYy);
	}
	public String getQxYy(){
		return this.qxYy;
	}

	public void setQxSm(String qxSm){
		this.qxSm=StringUtils.trim(qxSm);
	}
	public String getQxSm(){
		return this.qxSm;
	}

}