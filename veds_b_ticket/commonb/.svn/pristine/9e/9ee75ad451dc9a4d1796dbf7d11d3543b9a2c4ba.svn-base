package cn.vetech.vedsb.common.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * 批量操作entity
 */
public class BYdrzCzrzMx extends AbstractPageEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator="no")
	private String zbid;		//批量操作的日志ID
	private String ywid;		//批量操作的业务记录ID
	@Transient
	private String bm; 			//表名
	public String getZbid() {
		return zbid;
	}
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	public String getYwid() {
		return ywid;
	}
	public void setYwid(String ywid) {
		this.ywid = ywid;
	}
	public String getBm() {
		return bm;
	}
	public void setBm(String bm) {
		this.bm = bm;
	}
	

	
}
