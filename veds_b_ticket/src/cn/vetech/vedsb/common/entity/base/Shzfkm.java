package cn.vetech.vedsb.common.entity.base;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
@Table(name="sh_zfkm")
public class Shzfkm extends AbstractPageEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="no")
	private String id;
	
	private String shbh;//商户编号
	
	private String kmbh;//科目编号
	
	private String kmmc;//科目名称
	
	private Integer sxh;//顺序号
	
	private String sfskkm;//是否收款科目
	
	private String sffkkm;//是否付款科目
	
	private String sycp;//实用产品
	
	private String by1,by2,by3;
	
	private String cjyh;
	
	private String cjsj;
	
	private String xgyh;
	
	private String xgsj;
	
	/**
	 * 是否启用
	 */
	private String sfqy;

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

	public String getKmbh() {
		return kmbh;
	}

	public void setKmbh(String kmbh) {
		this.kmbh = kmbh;
	}

	public String getKmmc() {
		return kmmc;
	}

	public void setKmmc(String kmmc) {
		this.kmmc = kmmc;
	}

	public Integer getSxh() {
		return sxh;
	}

	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}

	public String getSfskkm() {
		return sfskkm;
	}

	public void setSfskkm(String sfskkm) {
		this.sfskkm = sfskkm;
	}

	public String getSffkkm() {
		return sffkkm;
	}

	public void setSffkkm(String sffkkm) {
		this.sffkkm = sffkkm;
	}

	public String getSycp() {
		return sycp;
	}

	public void setSycp(String sycp) {
		this.sycp = sycp;
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

	public String getCjyh() {
		return cjyh;
	}

	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public String getXgyh() {
		return xgyh;
	}

	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getSfqy() {
		return sfqy;
	}

	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	
}
