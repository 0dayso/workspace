package cn.vetech.vedsb.jp.entity.jpcwgl;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * 未到账报表  查询导出使用
 * @author vetech
 *
 */
public class Wdz extends AbstractPageEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String jglx;
	/**网店平台*/
	private String wdpt;
	/**网店*/
	private String wdid;
	/**到账日始*/
	private String dzrqs;
	/**到账日止*/
	private String dzrqz;
	/**订单类型*/
	private String ddlx;
	/**外部单号*/
	private String gyddh;
	/**订单编号*/
	private String ddbh;
	
	private String pnrno;
	/**到账状态*/
	private String dz_sfdz;
	/**票号*/
	private String tkno;
	
	private int pageNum;
	
	private int pageSize;
	/**商户编号*/
	private String shbh;
	
	

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

	public String getJglx() {
		return jglx;
	}

	public void setJglx(String jglx) {
		this.jglx = jglx == null ? "" : StringUtils.trim(jglx);
	}

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

	public String getDzrqs() {
		return dzrqs;
	}

	public void setDzrqs(String dzrqs) {
		this.dzrqs = dzrqs;
	}

	public String getDzrqz() {
		return dzrqz;
	}

	public void setDzrqz(String dzrqz) {
		this.dzrqz = dzrqz;
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

	public String getDdbh() {
		return ddbh;
	}

	public void setDdbh(String ddbh) {
		this.ddbh = ddbh == null ? "" : StringUtils.trim(ddbh);
	}

	public String getPnrno() {
		return pnrno;
	}

	public void setPnrno(String pnrno) {
		this.pnrno = pnrno == null ? "" : StringUtils.trim(pnrno);
	}

	public String getDz_sfdz() {
		return dz_sfdz;
	}

	public void setDz_sfdz(String dz_sfdz) {
		this.dz_sfdz = dz_sfdz == null ? "" : StringUtils.trim(dz_sfdz);
	}

	public String getTkno() {
		return tkno;
	}

	public void setTkno(String tkno) {
		this.tkno = tkno == null ? "" : StringUtils.trim(tkno);
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
