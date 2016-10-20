package cn.vetech.vedsb.jp.entity.jpcwgl;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 漏单报表 查询导出使用
 * @author vetech
 *
 */
public class Ld extends AbstractPageEntity {

	private static final long serialVersionUID = 1L;
	
	/**网店平台*/
	private String wdpt;
	/**网店ID*/
	private String wdid;
	/**账期日始*/
	private String syrqs;
	/**账期日止*/
	private String syrqz;
	/**订单类型*/
	private String ddlx;
	/**外部单号*/
	private String gyddh;
	/**补单状态*/
	private String bd_sfybd;
	/**商户编号*/
	private String shbh;
	
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt == null ? "" : StringUtils.trim(wdpt);
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid == null ? "" : StringUtils.trim(wdid);
	}
	public String getSyrqs() {
		return syrqs;
	}
	public void setSyrqs(String syrqs) {
		this.syrqs = syrqs == null ? "" : StringUtils.trim(syrqs);
	}
	public String getSyrqz() {
		return syrqz;
	}
	public void setSyrqz(String syrqz) {
		this.syrqz = syrqz == null ? "" : StringUtils.trim(syrqz);
	}
	public String getDdlx() {
		return ddlx;
	}
	public void setDdlx(String ddlx) {
		this.ddlx = ddlx == null ? "" : StringUtils.trim(ddlx);
	}
	public String getGyddh() {
		return gyddh;
	}
	public void setGyddh(String gyddh) {
		this.gyddh = gyddh == null ? "" : StringUtils.trim(gyddh);
	}
	public String getBd_sfybd() {
		return bd_sfybd;
	}
	public void setBd_sfybd(String bd_sfybd) {
		this.bd_sfybd = bd_sfybd == null ? "" : StringUtils.trim(bd_sfybd);
	}
	public String getShbh() {
		return shbh;
	}
	public void setShbh(String shbh) {
		this.shbh = shbh ;
	}
}
