package cn.vetech.vedsb.jp.entity.jpjpgl;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_SMSJSZ")
public class JpSmSjSz extends AbstractPageEntity implements Serializable{

	private static final long serialVersionUID = -5163451357958940374L;
	/**
	 * ID主键
	 */
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**
	 * 商户编号
	 */
	private String shbh;
	/**
	 * 提前天数
	 */
	private Integer tqts;
	/**
	 * 是否开启 0不开启 1开启  默认0
	 */
	private String sfkq;
	/**
	 * pid用户编号
	 */
	private String pidyhbh;
	/**
	 * pid密码
	 */
	private String pidmm;
	
	public String getPidyhbh() {
		return pidyhbh;
	}
	public void setPidyhbh(String pidyhbh) {
		this.pidyhbh = pidyhbh;
	}
	public String getPidmm() {
		return pidmm;
	}
	public void setPidmm(String pidmm) {
		this.pidmm = pidmm;
	}
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
	public Integer getTqts() {
		return tqts;
	}
	public void setTqts(Integer tqts) {
		this.tqts = tqts;
	}
	public String getSfkq() {
		return sfkq;
	}
	public void setSfkq(String sfkq) {
		this.sfkq = sfkq;
	}
	
}
