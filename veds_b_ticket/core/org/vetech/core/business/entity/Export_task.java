package org.vetech.core.business.entity;


import java.util.Date;

import javax.persistence.Id;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;


/**
 * 导出任务
 * 
 * @author heer
 * @version [版本号, Sep 1, 2014]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public class Export_task extends AbstractPageEntity {
	private static final long serialVersionUID = 6767868318457437667L;

	@Id
	/** 主键 */
	private String id;

	/** 任务类型1立即导出 2定时导出 */
	private String lx;

	/** 平台：A平台；B平台 */
	private String pt;

	/** 操作人用户编号 */
	private String bh;

	/** 创建时间 */
	private Date createtime;

	/** 状态：0未处理；1处理中；2成功；3失败 */
	private String status;

	/** 所属模块 */
	private String mkbh;

	/** 任务名称 */
	private String mc;

	/** 导出的文件名 */
	private String exportfile;

	/** 导出参数(序列化对象文件) */
	private String paramfile;

	/** 开始时间 */
	private Date starttime;

	/** 结束时间 */
	private Date endtime;

	/** 执行次数,修改时表示原times值加上该属性值.如:当times属性为1原有值为2,则修改后的值为3 */
	private Integer times;

	/** 执行错误的次数,修改同times一样 */
	private Integer failedtimes;

	/** 开始日期 查询用 */
	private String ksrq;

	/** 结束日期 查询用 */
	private String jsrq;
	
	/** 开始与结束时间差 */
	private Long sjc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getPt() {
		return pt;
	}

	public void setPt(String pt) {
		this.pt = pt;
	}

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMkbh() {
		return mkbh;
	}

	public void setMkbh(String mkbh) {
		this.mkbh = mkbh;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getExportfile() {
		return exportfile;
	}

	public void setExportfile(String exportfile) {
		this.exportfile = exportfile;
	}

	public String getParamfile() {
		return paramfile;
	}

	public void setParamfile(String paramfile) {
		this.paramfile = paramfile;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getFailedtimes() {
		return failedtimes;
	}

	public void setFailedtimes(Integer failedtimes) {
		this.failedtimes = failedtimes;
	}

	public String getKsrq() {
		return ksrq;
	}

	public void setKsrq(String ksrq) {
		this.ksrq = ksrq;
	}

	public String getJsrq() {
		return jsrq;
	}

	public void setJsrq(String jsrq) {
		this.jsrq = jsrq;
	}

	public Long getSjc() {
		return sjc;
	}

	public void setSjc(Long sjc) {
		this.sjc = sjc;
	}

}
