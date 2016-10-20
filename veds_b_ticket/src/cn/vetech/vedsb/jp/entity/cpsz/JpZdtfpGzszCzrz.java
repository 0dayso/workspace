package cn.vetech.vedsb.jp.entity.cpsz;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name = "JP_ZDTFP_GZSZ_CZRZ")
public class JpZdtfpGzszCzrz extends AbstractPageEntity {
	private static final long serialVersionUID = 622826235296088142L;
	@Id
	/**主键*/	private String id;	private String gzid; 	/**修改前状态*/	private String zt;	private String czDatetime; 	/**操作商户编号*/	private String czshbh; 	/**操作部门编号*/	private String czbmbh; 	/**操作用户编号*/	private String czyhbh;	private String bzbz;	private String czsm;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}		public String getGzid() {
		return gzid;
	}
	public void setGzid(String gzid) {
		this.gzid = gzid;
	}		public String getZt() {
		return zt;
	}
	public void setZt(String zt) {
		this.zt = zt;
	}		public String getCzDatetime() {
		return czDatetime;
	}
	public void setCzDatetime(String czDatetime) {
		this.czDatetime = czDatetime;
	}		public String getCzshbh() {
		return czshbh;
	}
	public void setCzshbh(String czshbh) {
		this.czshbh = czshbh;
	}		public String getCzbmbh() {
		return czbmbh;
	}
	public void setCzbmbh(String czbmbh) {
		this.czbmbh = czbmbh;
	}		public String getCzyhbh() {
		return czyhbh;
	}
	public void setCzyhbh(String czyhbh) {
		this.czyhbh = czyhbh;
	}		public String getBzbz() {
		return bzbz;
	}
	public void setBzbz(String bzbz) {
		this.bzbz = bzbz;
	}		public String getCzsm() {
		return czsm;
	}
	public void setCzsm(String czsm) {
		this.czsm = czsm;
	}

}
