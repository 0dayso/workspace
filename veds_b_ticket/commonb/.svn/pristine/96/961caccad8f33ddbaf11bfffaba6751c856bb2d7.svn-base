package cn.vetech.vedsb.common.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;


@Table(name = "SH_MKB")
public class Shmkb extends AbstractPageEntity {

	private static final long serialVersionUID = 4563184405850006991L;

	@Id
	private String bh; // 模块编号主键

	@NotBlank
	private String mc; // 模块名称

	private String ywmc; // 英文名称

	@NotBlank
	private String parbh; // 上级模块编号

	@NotBlank
	private String sxh; // 顺序号不能为空,可以为0,默认为1

	private String pic; // 图片

	private String bzbz; // 备注

	@NotBlank
	private String ssxt; // 所属系统编号取自数据字典

	private String url; // 模块URL

	@NotBlank
	private String sfyx; // 是否有效1有效，0无效

	@NotBlank
	private String cpbh; // 产品编号

	@NotBlank
	private String sfsf; // 是否单独收费模块0否，1是

	private String cj_userid; // 创建人

	private Date cjsj; // 创建时间

	private String xg_userid; // 最后修改人

	private Date xgsj; // 最后修改时间
	
	@Transient
	private List<Shmkgn> shmkgnList; // 商户模块功能
	@Transient
	private String lx="1";    //游离字段类型
	@Transient
	private String []gnbh;  //功能编号
	@Transient
	private String []gnmc;  //功能名称
	@Transient
	private String []gnurl;  //功能url
	@Transient
	private String []sfxsbzxx;  //是否显示帮助信息
	@Transient
	private String []sfxsqx;  //是否显示权限
	@Transient
	private String name;  //游离字段 树的name属性
	@Transient
	private boolean isParent; //游离字段  是否为父节点
	@Transient
	private String gn;
	@Transient
	private String iskq="0"; //1开启,0不开启
	
	public List<Shmkgn> getShmkgnList() {
		return shmkgnList;
	}
	public void setShmkgnList(List<Shmkgn> shmkgnList) {
		this.shmkgnList = shmkgnList;
	}
	public String[] getSfxsbzxx() {
		return sfxsbzxx;
	}
	public void setSfxsbzxx(String[] sfxsbzxx) {
		this.sfxsbzxx = sfxsbzxx;
	}
	public String[] getSfxsqx() {
		return sfxsqx;
	}
	public void setSfxsqx(String[] sfxsqx) {
		this.sfxsqx = sfxsqx;
	}
	public String getIskq() {
		return iskq;
	}
	public void setIskq(String iskq) {
		this.iskq = iskq;
	}
	public String getGn() {
		return gn;
	}
	public void setGn(String gn) {
		this.gn = gn;
	}
	public String[] getGnbh() {
		return gnbh;
	}
	public void setGnbh(String[] gnbh) {
		this.gnbh = gnbh;
	}
	public String[] getGnmc() {
		return gnmc;
	}
	public void setGnmc(String[] gnmc) {
		this.gnmc = gnmc;
	}
	public String[] getGnurl() {
		return gnurl;
	}
	public void setGnurl(String[] gnurl) {
		this.gnurl = gnurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getLx() {
		return lx;
	}
	public void setLx(String lx) {
		this.lx = lx;
	}

	@Transient
	private String qxbh; // 功能编号多个用","分开

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getYwmc() {
		return ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getParbh() {
		return parbh;
	}

	public void setParbh(String parbh) {
		this.parbh = parbh;
	}

	public String getSxh() {
		return sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getBzbz() {
		return bzbz;
	}

	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}

	public String getSsxt() {
		return ssxt;
	}

	public void setSsxt(String ssxt) {
		this.ssxt = ssxt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSfyx() {
		return sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
	}

	public String getCpbh() {
		return cpbh;
	}

	public void setCpbh(String cpbh) {
		this.cpbh = cpbh;
	}

	public String getSfsf() {
		return sfsf;
	}

	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
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

	public String getQxbh() {
		return qxbh;
	}

	public void setQxbh(String qxbh) {
		this.qxbh = qxbh;
	}

}
