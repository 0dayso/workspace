package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_TJSQ_JCSZ")
public class JpTjsqJcsz extends AbstractPageEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 287660282674227914L;
	
	/**ID号主键*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**字段英文名*/
	private String zdywm;
	/**字段值*/
	private String zdz;
	/**商户编号*/
	private String shbh;
	/**创建时间*/
	private Date cjsj;
	/**创建用户自动创建固定为0000*/
	private String cjyh;
	/**最后修改时间*/
	private Date xgsj;
	/**最后修改用户*/
	private String xgyh;

	public void setId(String id){
		this.id=id;
	}
	public String getId(){
		return this.id;
	}

	public void setZdywm(String zdywm){
		this.zdywm=StringUtils.trim(zdywm);
	}
	public String getZdywm(){
		return this.zdywm;
	}

	public void setZdz(String zdz){
		this.zdz=StringUtils.trim(zdz);
	}
	public String getZdz(){
		return this.zdz;
	}

	public void setShbh(String shbh){
		this.shbh=StringUtils.trim(shbh);
	}
	public String getShbh(){
		return this.shbh;
	}

	public void setCjsj(Date cjsj){
		this.cjsj=cjsj;
	}
	public Date getCjsj(){
		return this.cjsj;
	}

	public void setCjyh(String cjyh){
		this.cjyh=StringUtils.trim(cjyh);
	}
	public String getCjyh(){
		return this.cjyh;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}
	public Date getXgsj(){
		return this.xgsj;
	}

	public void setXgyh(String xgyh){
		this.xgyh=StringUtils.trim(xgyh);
	}
	public String getXgyh(){
		return this.xgyh;
	}

}