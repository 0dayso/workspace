package cn.vetech.vedsb.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 商户网店类型和数量控制表
 * @author lishanliang
 *
 */
@Table(name="sh_wdkz")
public class JpShwdkz extends AbstractPageEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8736584524531303405L;
	@Id
	@NotBlank
	private String id;//主键
	@NotBlank
	private String shbh;//商户编号
	
	private String shmc;//商户名称
	@NotBlank
	private String wdlx;//商户可建网店类型，存网店对应平台编号

	private String wdmc;//网店类型名称
	@NotBlank
	private String wdsl;//商户可建网店数量
	
	private String by1;//
	
	private String by2;//
	
	private String by3;//
	
	private String cj_userid;//创建人id
	
	private Date cjsj;//创建时间
	
	private String  xg_userid;//修改人id
	
	private Date  xgsj;//修改时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getShmc() {
		return shmc;
	}

	public void setShmc(String shmc) {
		this.shmc = shmc;
	}

	public String getWdlx() {
		return wdlx;
	}

	public void setWdlx(String wdlx) {
		this.wdlx = wdlx;
	}

	public String getWdmc() {
		return wdmc;
	}

	public void setWdmc(String wdmc) {
		this.wdmc = wdmc;
	}

	public String getWdsl() {
		return wdsl;
	}

	public void setWdsl(String wdsl) {
		this.wdsl = wdsl;
	}

	public String getBy1() {
		return by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public String getBy2() {
		return by2;
	}

	public void setBy2(String by2) {
		this.by2 = by2;
	}

	public String getBy3() {
		return by3;
	}

	public void setBy3(String by3) {
		this.by3 = by3;
	}

	public String getCj_userid() {
		return cj_userid;
	}

	public void setCj_userid(String cj_userid) {
		this.cj_userid = cj_userid;
	}

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXg_userid() {
		return xg_userid;
	}

	public void setXg_userid(String xg_userid) {
		this.xg_userid = xg_userid;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	

}
