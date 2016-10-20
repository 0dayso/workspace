package cn.vetech.vedsb.jp.entity.jpddsz;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

@Table(name="JP_KHDD_JPLB")
public class JpKhddJplb extends AbstractPageEntity{
	private static final long serialVersionUID = -1716599941038639929L;
	@Id
	@GeneratedValue(generator="no")
	private String id;//主键
	private String shbh;//商户编号
	private String wdpt;//网店平台
	private String wdid;//网店ID
	private String wbdh;//外部单号
	private String ddbh;//订单编号
	private String phhtzt;//票号回填状态 0未回填,1已回填,2回填失败
	private String phhtsbyy;//票号回填失败原因
	private Integer phhtsbcs;//票号回填失败次数
	private Date xchtsj;//下次回填时间
	private String cjyh;//创建用户
	private Date cjsj;//创建时间
	private Date xgsj;//修改时间
	private String xgyh;//修改用户
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
	public String getWdpt() {
		return wdpt;
	}
	public void setWdpt(String wdpt) {
		this.wdpt = wdpt;
	}
	public String getWdid() {
		return wdid;
	}
	public void setWdid(String wdid) {
		this.wdid = wdid;
	}
	public String getWbdh() {
		return wbdh;
	}
	public void setWbdh(String wbdh) {
		this.wbdh = wbdh;
	}
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getPhhtzt() {
		return phhtzt;
	}
	public void setPhhtzt(String phhtzt) {
		this.phhtzt = phhtzt;
	}
	public String getPhhtsbyy() {
		return phhtsbyy;
	}
	public void setPhhtsbyy(String phhtsbyy) {
		this.phhtsbyy = phhtsbyy;
	}
	public Integer getPhhtsbcs() {
		return phhtsbcs;
	}
	public void setPhhtsbcs(Integer phhtsbcs) {
		this.phhtsbcs = phhtsbcs;
	}
	public Date getXchtsj() {
		return xchtsj;
	}
	public void setXchtsj(Date xchtsj) {
		this.xchtsj = xchtsj;
	}
	public String getCjyh() {
		return cjyh;
	}
	public void setCjyh(String cjyh) {
		this.cjyh = cjyh;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
	public Date getXgsj() {
		return xgsj;
	}
	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	public String getXgyh() {
		return xgyh;
	}
	public void setXgyh(String xgyh) {
		this.xgyh = xgyh;
	}
}
