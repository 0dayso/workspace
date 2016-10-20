package cn.vetech.vedsb.jp.entity.jpzdcp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 自动出票国际规则优先级设置
 */
@Table(name="JP_ZDCP_GJ_GZ_YXJSZ")
public class JpZdcpGjGzYxjsz extends AbstractPageEntity{
	private static final long serialVersionUID = 1L;
	/**ID主键，序列生成*/
	@Id
	@GeneratedValue(generator="no")
	private String id;
	/**国际自动出票规则ID*/
	private String gjgzid;
	/**顺序号*/
	private Integer sxh;
	/**票价始（大于）*/
	private Integer pjs;
	/**票价始（小于等于）*/
	private Integer pjz;
	/**票价类型 0票面价 1销售价*/
	private String pjlx;
	/**出票类型 例TB>BOP>BSPET*/
	private String cplx;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGjgzid() {
		return gjgzid;
	}
	public void setGjgzid(String gjgzid) {
		this.gjgzid = gjgzid;
	}
	public Integer getSxh() {
		return sxh;
	}
	public void setSxh(Integer sxh) {
		this.sxh = sxh;
	}
	public Integer getPjs() {
		return pjs;
	}
	public void setPjs(Integer pjs) {
		this.pjs = pjs;
	}
	public Integer getPjz() {
		return pjz;
	}
	public void setPjz(Integer pjz) {
		this.pjz = pjz;
	}
	public String getPjlx() {
		return pjlx;
	}
	public void setPjlx(String pjlx) {
		this.pjlx = pjlx;
	}
	public String getCplx() {
		return cplx;
	}
	public void setCplx(String cplx) {
		this.cplx = cplx;
	}
}
