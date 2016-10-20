package cn.vetech.vedsb.common.entity.base;

import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 商户基础数据
 * 
 * @author zhanglei
 *
 */
@Table(name = "sh_jcsj")
public class Shjcsj extends AbstractPageEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2821482304888002545L;
	private String shbh, bh;// 唯一
	private String mc, lb, sjbh, sfmr, by1, by2, by3;

	public String getShbh() {
		return shbh;
	}

	public void setShbh(String shbh) {
		this.shbh = shbh;
	}

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

	public String getLb() {
		return lb;
	}

	public void setLb(String lb) {
		this.lb = lb;
	}

	public String getSjbh() {
		return sjbh;
	}

	public void setSjbh(String sjbh) {
		this.sjbh = sjbh;
	}

	public String getSfmr() {
		return sfmr;
	}

	public void setSfmr(String sfmr) {
		this.sfmr = sfmr;
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
}
