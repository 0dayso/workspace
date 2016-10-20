package cn.vetech.vedsb.jp.entity.cgdzbb;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_TSL_DZJG")
public class JpTslDzjg extends AbstractPageEntity{
	private static final long serialVersionUID = 7686869501881968300L;

	/**主键*/
	private String id;
	/**商户编号*/
	private String shbh;
	/**1正常票，2退票，3废票*/
	private String pzlx;
	/**票号*/
	private String tkno;
	/**系统航程*/
	private String xthc;
	/**系统账单价*/
	private Integer xtzdj;
	/**系统税费*/
	private Integer xttax;
	/**系统代理费率*/
	private Double xtdlfl;
	/**系统pnr*/
	private String xtpnr;
	/**office*/
	private String xtoffice;
	/**系统工作号*/
	private String xtworkno;
	/**打票机号*/
	private String xtpringtno;
	/**TSL航程*/
	private String tslhc;
	/**TSL账单价*/
	private Integer tslzdj;
	/**TSL税费*/
	private Integer tsltax;
	/**TSL代理费率*/
	private Double tsldlfl;
	/**TSLpnr*/
	private String tslpnr;
	/**TSL工作号*/
	private String tslworkno;
	/**出票日期*/
	private String cpDate;
	/**0对比正确；1对比错误；2只存在系统；3只存在TSL账单*/
	private String dbjg;
	/**多个用'/'分隔；0账单金额不对，1税费不对，2代理费率不对*/
	private String cwlx;

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

	public void setPzlx(String pzlx){
		this.pzlx=StringUtils.trim(pzlx);
	}
	public String getPzlx(){
		return this.pzlx;
	}

	public void setTkno(String tkno){
		this.tkno=StringUtils.trim(tkno);
	}
	public String getTkno(){
		return this.tkno;
	}

	public void setXthc(String xthc){
		this.xthc=StringUtils.trim(xthc);
	}
	public String getXthc(){
		return this.xthc;
	}

	public void setXtzdj(Integer xtzdj){
		this.xtzdj=xtzdj;
	}
	public Integer getXtzdj(){
		return this.xtzdj;
	}

	public void setXttax(Integer xttax){
		this.xttax=xttax;
	}
	public Integer getXttax(){
		return this.xttax;
	}

	public void setXtdlfl(Double xtdlfl){
		this.xtdlfl=xtdlfl;
	}
	public Double getXtdlfl(){
		return this.xtdlfl;
	}

	public void setXtpnr(String xtpnr){
		this.xtpnr=StringUtils.trim(xtpnr);
	}
	public String getXtpnr(){
		return this.xtpnr;
	}

	public void setXtoffice(String xtoffice){
		this.xtoffice=StringUtils.trim(xtoffice);
	}
	public String getXtoffice(){
		return this.xtoffice;
	}

	public void setXtworkno(String xtworkno){
		this.xtworkno=StringUtils.trim(xtworkno);
	}
	public String getXtworkno(){
		return this.xtworkno;
	}

	public void setXtpringtno(String xtpringtno){
		this.xtpringtno=StringUtils.trim(xtpringtno);
	}
	public String getXtpringtno(){
		return this.xtpringtno;
	}

	public void setTslhc(String tslhc){
		this.tslhc=StringUtils.trim(tslhc);
	}
	public String getTslhc(){
		return this.tslhc;
	}

	public void setTslzdj(Integer tslzdj){
		this.tslzdj=tslzdj;
	}
	public Integer getTslzdj(){
		return this.tslzdj;
	}

	public void setTsltax(Integer tsltax){
		this.tsltax=tsltax;
	}
	public Integer getTsltax(){
		return this.tsltax;
	}

	public void setTsldlfl(Double tsldlfl){
		this.tsldlfl=tsldlfl;
	}
	public Double getTsldlfl(){
		return this.tsldlfl;
	}

	public void setTslpnr(String tslpnr){
		this.tslpnr=StringUtils.trim(tslpnr);
	}
	public String getTslpnr(){
		return this.tslpnr;
	}

	public void setTslworkno(String tslworkno){
		this.tslworkno=StringUtils.trim(tslworkno);
	}
	public String getTslworkno(){
		return this.tslworkno;
	}

	public void setCpDate(String cpDate){
		this.cpDate=StringUtils.trim(cpDate);
	}
	public String getCpDate(){
		return this.cpDate;
	}

	public void setDbjg(String dbjg){
		this.dbjg=StringUtils.trim(dbjg);
	}
	public String getDbjg(){
		return this.dbjg;
	}

	public void setCwlx(String cwlx){
		this.cwlx=StringUtils.trim(cwlx);
	}
	public String getCwlx(){
		return this.cwlx;
	}

}