package cn.vetech.vedsb.jp.entity.job;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;
/**
 * 机票系统job登记表
 * @author zhanglei
 *
 */
@Table(name = "qrtz_job")
public class QrtzJob extends AbstractPageEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3844227280539646142L;
	@Id
	private String bh;
	private String jobgroup, jobname, jobdesc;
	private String classname, concurrent, type, expression;
	private Integer repeatinterval;
	private Date createtime;

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getJobgroup() {
		return jobgroup;
	}

	public void setJobgroup(String jobgroup) {
		this.jobgroup = jobgroup;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

	public String getJobdesc() {
		return jobdesc;
	}

	public void setJobdesc(String jobdesc) {
		this.jobdesc = jobdesc;
	}

	public String getConcurrent() {
		return concurrent;
	}

	public void setConcurrent(String concurrent) {
		this.concurrent = concurrent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Integer getRepeatinterval() {
		return repeatinterval;
	}

	public void setRepeatinterval(Integer repeatinterval) {
		this.repeatinterval = repeatinterval;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}
}
