package cn.vetech.vedsb.jp.entity.jpzwgl;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="JP_TJSQ_JCGZ")
public class JpTjsqJcgz extends AbstractPageEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8794087574181982630L;
	/**ID号主键*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**顺序号*/
	private Integer sxh;
	/**字段英文名*/
	private String zdywm;
	/**字段中文名前缀*/
	private String zdzwmqz;
	/**字段类型如text,num,radio,select,checkbox*/
	private String zdlx;
	/**字段验证存放JS代码，做字段长度及取值方面的验证*/
	private String zdxz;
	/**枚举型值指radio,select,checkbox，多个/分隔*/
	private String mjxz;
	/**枚举型名称多个/分隔*/
	private String mjxmc;
	/**默认值*/
	private String mrz;
	/**单位名称如%,元等*/
	private String dwmc;
	/**字段说明页面上显示的字段说明*/
	private String zdsm;
	/**备注*/
	private String bzbz;
	/**创建人*/
	private String cjUserid;
	/**创建时间*/
	private Date cjsj;
	/**最后修改人*/
	private String xgUserid;
	/**最后修改时间*/
	private Date xgsj;
	/**字段属性，存input元素属性值*/
	private String zdsx;
	/**字段中文名后缀*/
	private String zdzwmhz;
	
	@Transient
	private String shbh;//商户编号
	
	@Transient
	private String zdz;//字段值
	
	@Transient
	private String szid;//基础设置id

	public void setId(String id){
		this.id=StringUtils.trim(id);
	}
	public String getId(){
		return this.id;
	}

	public void setSxh(Integer sxh){
		this.sxh=sxh;
	}
	public Integer getSxh(){
		return this.sxh;
	}

	public void setZdywm(String zdywm){
		this.zdywm=StringUtils.trim(zdywm);
	}
	public String getZdywm(){
		return this.zdywm;
	}

	public void setZdzwmqz(String zdzwmqz){
		this.zdzwmqz=StringUtils.trim(zdzwmqz);
	}
	public String getZdzwmqz(){
		return this.zdzwmqz;
	}

	public void setZdlx(String zdlx){
		this.zdlx=StringUtils.trim(zdlx);
	}
	public String getZdlx(){
		return this.zdlx;
	}

	public void setZdxz(String zdxz){
		this.zdxz=zdxz;
	}
	public String getZdxz(){
		return this.zdxz;
	}

	public void setMjxz(String mjxz){
		this.mjxz=StringUtils.trim(mjxz);
	}
	public String getMjxz(){
		return this.mjxz;
	}

	public void setMjxmc(String mjxmc){
		this.mjxmc=StringUtils.trim(mjxmc);
	}
	public String getMjxmc(){
		return this.mjxmc;
	}

	public void setMrz(String mrz){
		this.mrz=StringUtils.trim(mrz);
	}
	public String getMrz(){
		return this.mrz;
	}

	public void setDwmc(String dwmc){
		this.dwmc=StringUtils.trim(dwmc);
	}
	public String getDwmc(){
		return this.dwmc;
	}

	public void setZdsm(String zdsm){
		this.zdsm=StringUtils.trim(zdsm);
	}
	public String getZdsm(){
		return this.zdsm;
	}

	public void setBzbz(String bzbz){
		this.bzbz=StringUtils.trim(bzbz);
	}
	public String getBzbz(){
		return this.bzbz;
	}

	public void setCjUserid(String cjUserid){
		this.cjUserid=StringUtils.trim(cjUserid);
	}
	public String getCjUserid(){
		return this.cjUserid;
	}

	public void setCjsj(Date cjsj){
		this.cjsj=cjsj;
	}
	public Date getCjsj(){
		return this.cjsj;
	}

	public void setXgUserid(String xgUserid){
		this.xgUserid=StringUtils.trim(xgUserid);
	}
	public String getXgUserid(){
		return this.xgUserid;
	}

	public void setXgsj(Date xgsj){
		this.xgsj=xgsj;
	}
	public Date getXgsj(){
		return this.xgsj;
	}

	public void setZdsx(String zdsx){
		this.zdsx=zdsx;
	}
	public String getZdsx(){
		return this.zdsx;
	}

	public void setZdzwmhz(String zdzwmhz){
		this.zdzwmhz=StringUtils.trim(zdzwmhz);
	}
	public String getZdzwmhz(){
		return this.zdzwmhz;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = StringUtils.trim(shbh);
	}
	public String getZdz() {
		return zdz;
	}
	public void setZdz(String zdz) {
		this.zdz = StringUtils.trim(zdz);
	}
	public String getSzid() {
		return szid;
	}
	public void setSzid(String szid) {
		this.szid = StringUtils.trim(szid);
	}

}