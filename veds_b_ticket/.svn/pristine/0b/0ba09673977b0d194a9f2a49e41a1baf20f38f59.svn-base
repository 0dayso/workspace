package cn.vetech.vedsb.common.entity.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
import org.vetech.core.modules.utils.VeDate;

import cn.vetech.vedsb.common.service.BYdlog;
/**
 * 商户服务开启设置
 * @author lishanliang
 *
 */
@Table(name="sh_fwkqsz")
public class Shfwkqsz extends AbstractPageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7650759185938334894L;
	@Id
	@NotBlank
	private String id;//主键
	@NotBlank
	@BYdlog(name="商户编号",logView=1)
	private String shbh;//商户编号
	@NotBlank
	@BYdlog(name="服务类型1表示服务2表示数据",logView=1)
	private String lx;//类型 1表示服务2表示数据
	@NotBlank
	@BYdlog(name="服务类型编号",logView=1)
	private String fw_lxid;//服务类型---服务对应ve_class表中的70061  ， 数据10002 
	
	@Formula("(select mc from ve_class where id=entity.fw_lxid)")
	private String fw_lxmc;//服务类型名称
	@BYdlog(name="是否开启",logView=1)
	private String sfkq;//是否开启
	@BYdlog(name="是否收费",logView=1)
	private String sfsf;//是否收费
	@BYdlog(name="收费方式",logView=1)
	private String sffs;//收费方式 1按单收费，2按月收费，3按系统默认设置，4单独设置，5免费
	@BYdlog(name="收费金额",logView=1)
	private Double sfje;//收费金额
	
	private Double veje;//胜意金额
	@BYdlog(name="有效期",logView=1)
	private Date yxq;//有效期
	
	private String  bzbz;//备注
	@BYdlog(name="开通数据类型子类型",logView=1)
	private String by1;//备用1,存数据类型的子类,使用","分个,"---"表示全部
	
	private String by2;//备用2
	
	private String by3;//备用3
	
	private String xg_userid;//最后修改人
	
	@Formula("(select xm from ve_user where bh=entity.xg_userid)")
	private String xg_usermc;//最后修改人名称
	
	private Date xg_datetime;//最后修改时间
	
	private String  cj_userid;//创建人
	
	@Formula("(select xm from ve_user where bh=entity.xg_userid)")
	private String  cj_usermc;//创建人
	
	private Date  cjsj;//创建时间

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

	public String getSfkq() {
		return sfkq;
	}

	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}

	public String getSffs() {
		return sffs;
	}

	public void setSffs(String sffs) {
		this.sffs = sffs;
	}

	public Double getSfje() {
		return sfje;
	}

	public void setSfje(Double sfje) {
		this.sfje = sfje;
	}

	public Double getVeje() {
		return veje;
	}

	public void setVeje(Double veje) {
		this.veje = veje;
	}

	public Date getYxq() {
		return yxq;
	}
	@Transient
	public String getYxqStr(){
		if(yxq != null){
			return VeDate.dateToStr(this.yxq);
		}else{
			return "";
		}
	}
	public void setYxq(Date yxq) {
		this.yxq = yxq;
	}

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
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

	public String getXg_userid() {
		return xg_userid;
	}

	public void setXg_userid(String xg_userid) {
		this.xg_userid = xg_userid;
	}

	public Date getXg_datetime() {
		return xg_datetime;
	}
	@Transient
	public String getXg_datetimeStr(){
		if(xg_datetime != null){
			return VeDate.dateToStrLong(this.xg_datetime);
		}else{
			return "";
		}
	}
	public void setXg_datetime(Date xg_datetime) {
		this.xg_datetime = xg_datetime;
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
	@Transient
	public String getCjsjStr(){
		if(cjsj != null){
			return VeDate.dateToStrLong(this.cjsj);
		}else{
			return "";
		}
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getXg_usermc() {
		return xg_usermc;
	}

	public void setXg_usermc(String xg_usermc) {
		this.xg_usermc = xg_usermc;
	}

	public String getCj_usermc() {
		return cj_usermc;
	}

	public void setCj_usermc(String cj_usermc) {
		this.cj_usermc = cj_usermc;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getFw_lxid() {
		return fw_lxid;
	}

	public void setFw_lxid(String fw_lxid) {
		this.fw_lxid = fw_lxid;
	}

	public String getFw_lxmc() {
		return fw_lxmc;
	}

	public void setFw_lxmc(String fw_lxmc) {
		this.fw_lxmc = fw_lxmc;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	
}
