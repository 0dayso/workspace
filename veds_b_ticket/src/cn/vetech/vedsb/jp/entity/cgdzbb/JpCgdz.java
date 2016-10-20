package cn.vetech.vedsb.jp.entity.cgdzbb;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;

/**
 * 采购对账主表
 * 
 * @author houya
 *
 */
@Table(name = "JP_CGDZ")
public class JpCgdz extends AbstractPageEntity {
	private static final long serialVersionUID = 5174074879973307979L;
	@Id
	private String id; // 主键
	private String shbh; // 商户编号
	private String dzrq; // 对账日期
	private String dzZt;// 对账状态，0未对账，1核对无误已到账，2对账有异常，3核对无误未到账
	private String dzUserid;// 对账员
	private String dzDeptid; // 对账部门
	private Date dzDatetime; // 对账时间
	private String shZt; // 审核状态，0未审核，1审核通过，2审核未通过
	private String shUserid; // 审核员
	private String shDeptid;// 审核部门
	private Date shDatetime; // 审核时间
	private String by1;// 备用一
	private String by2; // 备用二
	private String by3; // 备用三
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
	public String getDzrq() {
		return dzrq;
	}
	public void setDzrq(String dzrq) {
		this.dzrq = dzrq;
	}
	public String getDzZt() {
		return dzZt;
	}
	public void setDzZt(String dzZt) {
		this.dzZt = dzZt;
	}
	public String getDzUserid() {
		return dzUserid;
	}
	public void setDzUserid(String dzUserid) {
		this.dzUserid = dzUserid;
	}
	public String getDzDeptid() {
		return dzDeptid;
	}
	public void setDzDeptid(String dzDeptid) {
		this.dzDeptid = dzDeptid;
	}
	public Date getDzDatetime() {
		return dzDatetime;
	}
	public void setDzDatetime(Date dzDatetime) {
		this.dzDatetime = dzDatetime;
	}
	public String getShZt() {
		return shZt;
	}
	public void setShZt(String shZt) {
		this.shZt = shZt;
	}
	public String getShUserid() {
		return shUserid;
	}
	public void setShUserid(String shUserid) {
		this.shUserid = shUserid;
	}
	public String getShDeptid() {
		return shDeptid;
	}
	public void setShDeptid(String shDeptid) {
		this.shDeptid = shDeptid;
	}
	public Date getShDatetime() {
		return shDatetime;
	}
	public void setShDatetime(Date shDatetime) {
		this.shDatetime = shDatetime;
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
