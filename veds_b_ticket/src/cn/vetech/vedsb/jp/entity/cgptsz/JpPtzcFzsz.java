package cn.vetech.vedsb.jp.entity.cgptsz;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_PTZC_FZSZ")
public class JpPtzcFzsz extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	@Id
	private String id;
	/**商户编号*/
	private String shbh;
	/**分账方*/
	private String fzFzf;
	/**分账手续费率*/
	private Double fzSxfl;
	/**对应系统支付科目*/
	private String xtZfkm;
	/**业务类型 1销售，2采购*/
	private String ywlx;
	/**最后修改人*/
	private String xgr;
	/**最后修改日期*/
	private String xgrq;
	/**平台政策标识*/
	private String ptzcbs;

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setFzFzf(String fzFzf){
		this.fzFzf=StringUtils.trim(fzFzf);
	}
	public String getFzFzf(){
		return this.fzFzf;
	}

	public void setFzSxfl(Double fzSxfl){
		this.fzSxfl=fzSxfl;
	}
	public Double getFzSxfl(){
		return this.fzSxfl;
	}

	public void setXtZfkm(String xtZfkm){
		this.xtZfkm=StringUtils.trim(xtZfkm);
	}
	public String getXtZfkm(){
		return this.xtZfkm;
	}

	public void setYwlx(String ywlx){
		this.ywlx=StringUtils.trim(ywlx);
	}
	public String getYwlx(){
		return this.ywlx;
	}

	public void setXgr(String xgr){
		this.xgr=StringUtils.trim(xgr);
	}
	public String getXgr(){
		return this.xgr;
	}

	public void setXgrq(String xgrq){
		this.xgrq=StringUtils.trim(xgrq);
	}
	public String getXgrq(){
		return this.xgrq;
	}

	public void setPtzcbs(String ptzcbs){
		this.ptzcbs=StringUtils.trim(ptzcbs);
	}
	public String getPtzcbs(){
		return this.ptzcbs;
	}

}