package org.vetech.core.business.job;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 任务定义
 * 
 * @author houya
 * 
 */
public class JobBean implements Serializable {

	/**
	 * 用来存储传递数据的名称
	 */
	public static String JobDataName = "scheduleJob";

	@Override
	public String toString() {
		String s = ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE).replaceAll("<null>", "<>");
		return s;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -795808328064223975L;

	/** 任务名称 */
	private String name;

	/** 任务分组 */
	private String group;

	/** 任务描述 */
	private String desc;

	/**
	 * 使用什么触发器 1 SimpleTrigger 0 cron
	 */
	private String type;

	/**
	 * 如果需要让任务只在某个时刻执行一次，或者，在某个时刻开始，然后按照某个时间间隔重复执行，
	 * 
	 * 简单地说，如果你想让触发器在2007年8月20日上午11：23：54秒执行，然后每个隔10秒钟重复执行一次，
	 * 
	 * 并且这样重复5次。那么SimpleTrigger 就可以满足你的要求。
	 * 
	 * type 为1的时候 ： 开始时间,结束时间,重复次数,重复间隔
	 * 
	 * type 为0的时候 ： 定时器运行时间表达式
	 */

	private String expression;

	/**
	 * 简单脚本开始执行时间 默认为当前时间
	 */
	private Date startdate;

	/**
	 * 间隔的分钟数
	 */
	private int mintime;
	/**
	 * 间隔的秒钟数 分钟和秒钟 2者只能设置一个值，否则报错
	 */
	private int sectime;

	/**
	 * 需要传递给job的数据
	 */
	private String data;
	/**
	 * 真正执行任务的类
	 */
	private String classname;

	/**
	 * 存放错误信息
	 */
	private String info;

	/**
	 * 使用这个任务的定时器 一个job 配对一个定时器，否则任务不能并发
	 */

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getMintime() {
		return mintime;
	}

	public void setMintime(int mintime) {
		this.mintime = mintime;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getSectime() {
		return sectime;
	}

	public void setSectime(int sectime) {
		this.sectime = sectime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
