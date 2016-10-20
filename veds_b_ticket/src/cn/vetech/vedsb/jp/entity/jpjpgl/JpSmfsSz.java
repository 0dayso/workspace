package cn.vetech.vedsb.jp.entity.jpjpgl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_SMFS_SZ")
public class JpSmfsSz extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = 3916865866638519092L;
	@Id
	@GeneratedValue(generator="no")
	private String id;//
	private String smlx;//扫描类型0到期OPEN票扫描 1未乘机2异常改签
	private String cgly;//采购来源，多个逗号分隔,全部为---
	private String detrLx;//DETR类型，0调用本地PIDDETR，1调用CPS接口DETR
	private String shbh;
	private String yhbh;
	private Date czsj;
	private String wdid;
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSmlx() {
		return smlx;
	}
	public void setSmlx(String smlx) {
		this.smlx = smlx;
	}
	public String getCgly() {
		return cgly;
	}
	public void setCgly(String cgly) {
		this.cgly = cgly;
	}
	
	public String getDetrLx() {
		return detrLx;
	}
	public void setDetrLx(String detrLx) {
		this.detrLx = detrLx;
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh;
	}
	public String getYhbh() {
		return yhbh;
	}
	public void setYhbh(String yhbh) {
		this.yhbh = yhbh;
	}
	public Date getCzsj() {
		return czsj;
	}
	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}
	
	

}
