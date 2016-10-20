package cn.vetech.vedsb.jp.entity.jpcwgl;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_YSDZ")
public class JpYsdz extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**主键*/
	@Id
	private String id;
	/**对账日期*/
	private String dzrq;
	/**网店平台*/
	private String wdpt;
	/**网店编号*/
	private String wdid;
	/**对账状态，0未对账，1核对无误已到账，2对账有异常，3核对无误未到账*/
	private String dzZt;
	/**对账员*/
	private String dzUserid;
	/**对账部门*/
	private String dzDeptid;
	/**对账时间*/
	private Date dzDatetime;
	/**审核状态，0未审核，1审核通过，2审核未通过*/
	private String shZt;
	/**审核员*/
	private String shUserid;
	/**审核部门*/
	private String shDeptid;
	/**审核时间*/
	private Date shDatetime;
	/**备用一*/
	private String by1;
	/**备用二*/
	private String by2;
	/**备用三*/
	private String by3;
	/**商户编号*/
	private String shbh;

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setDzrq(String dzrq){
		this.dzrq=StringUtils.trim(dzrq);
	}
	public String getDzrq(){
		return this.dzrq;
	}

	public void setWdpt(String wdpt){
		this.wdpt=StringUtils.trim(wdpt);
	}
	public String getWdpt(){
		return this.wdpt;
	}

	public void setWdid(String wdid){
		this.wdid=StringUtils.trim(wdid);
	}
	public String getWdid(){
		return this.wdid;
	}

	public void setDzZt(String dzZt){
		this.dzZt=StringUtils.trim(dzZt);
	}
	public String getDzZt(){
		return this.dzZt;
	}

	public void setDzUserid(String dzUserid){
		this.dzUserid=StringUtils.trim(dzUserid);
	}
	public String getDzUserid(){
		return this.dzUserid;
	}

	public void setDzDeptid(String dzDeptid){
		this.dzDeptid=StringUtils.trim(dzDeptid);
	}
	public String getDzDeptid(){
		return this.dzDeptid;
	}

	public void setDzDatetime(Date dzDatetime){
		this.dzDatetime=dzDatetime;
	}
	public Date getDzDatetime(){
		return this.dzDatetime;
	}

	public void setShZt(String shZt){
		this.shZt=StringUtils.trim(shZt);
	}
	public String getShZt(){
		return this.shZt;
	}

	public void setShUserid(String shUserid){
		this.shUserid=StringUtils.trim(shUserid);
	}
	public String getShUserid(){
		return this.shUserid;
	}

	public void setShDeptid(String shDeptid){
		this.shDeptid=StringUtils.trim(shDeptid);
	}
	public String getShDeptid(){
		return this.shDeptid;
	}

	public void setShDatetime(Date shDatetime){
		this.shDatetime=shDatetime;
	}
	public Date getShDatetime(){
		return this.shDatetime;
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

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

}