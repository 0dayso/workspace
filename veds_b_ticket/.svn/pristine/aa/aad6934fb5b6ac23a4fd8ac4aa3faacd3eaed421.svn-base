package cn.vetech.vedsb.jp.entity.cgptsz;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_PTBJ_HFSZ")
public class JpPtbjHfsz extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	@Id
	private String id;
	/**商户编号*/
	private String shbh;
	/**平台后返点数，单位%*/
	private Double pthfds;
	/**平台后返金额，单位元*/
	private Double pthfje;
	/**单个平台是否开启比价 0关闭，1开启*/
	private String sfkqbj;
	/**是否开启该平台的后返*/
	private String sfkqhf;
	/**修改日期*/
	private String xgrq;
	/**修改人*/
	private String xgr;
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

	public void setPthfds(Double pthfds){
		this.pthfds=pthfds;
	}
	public Double getPthfds(){
		return this.pthfds;
	}

	public void setPthfje(Double pthfje){
		this.pthfje=pthfje;
	}
	public Double getPthfje(){
		return this.pthfje;
	}

	public void setSfkqbj(String sfkqbj){
		this.sfkqbj=StringUtils.trim(sfkqbj);
	}
	public String getSfkqbj(){
		return this.sfkqbj;
	}

	public void setSfkqhf(String sfkqhf){
		this.sfkqhf=StringUtils.trim(sfkqhf);
	}
	public String getSfkqhf(){
		return this.sfkqhf;
	}

	public void setXgrq(String xgrq){
		this.xgrq=StringUtils.trim(xgrq);
	}
	public String getXgrq(){
		return this.xgrq;
	}

	public void setXgr(String xgr){
		this.xgr=StringUtils.trim(xgr);
	}
	public String getXgr(){
		return this.xgr;
	}

	public void setPtzcbs(String ptzcbs){
		this.ptzcbs=StringUtils.trim(ptzcbs);
	}
	public String getPtzcbs(){
		return this.ptzcbs;
	}

}