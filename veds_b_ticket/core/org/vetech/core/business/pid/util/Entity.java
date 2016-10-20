package org.vetech.core.business.pid.util;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

/**
 * 统一定义version列的entity基类.
 * 
 * 其中version列用于乐观并发版本控制.
 * 
 * @author zhanglei
 */
public abstract class Entity implements Serializable {
	
	@Transient
	private Long version;
	
	/**
	 * 排序的属性
	 */
	@Transient
	private String orderClause;
	
	/**
	 * 翻页的开始行
	 */
	@Transient
	private int start = 0;
	
//	/**
//	 * 每页的行数
//	 */
//	@Transient
//	private int count = new Page().getCount();
	
	/**
	 * SAVE时的标识，区别create与edit
	 */
	@Transient
	private String operate;
	
	/**
	 * 其它条件
	 */
	@Transient
	private String otherwhere;
	
	/**
	 * 日志来源模块
	 */
	@Transient
	
	private String logCzly = "系统默认";
	
	/**
	 * 日志来源平台
	 */
	@Transient
	
	private String logPlat = "A";
	
	/**
	 * 日志操作用户
	 */
	@Transient
	
	private String logUser = "System";
	
	/**
	 * 日志查看范围
	 */
	@Transient
	
	private String logCkfw = "0"; // 查看范围 0表示采购/供应/平台都可查看 1表示平台 2表示采购/平台 3表示供应/平台
	
	@Transient
	
	private int ismap = 1; 			//查询列表结果是否是Map 1：是map 0 :是bean;
	
	/**
	 * 日志内容
	 */
	@Transient
	
	private String logNr;

	/** operate属性分页查询语句时不执行统计操作的标识(即当operate为_NoCount时不执行统计语句) */
	@Transient
	
	public static String NOCOUNT = "_NoCount";

	public String getOtherwhere() {
		return otherwhere;
	}

	public void setOtherwhere(String otherwhere) {
		this.otherwhere = otherwhere;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getOrderClause() {
		return orderClause;
	}

	public void setOrderClause(String orderClause) {
		this.orderClause = orderClause;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getLogCzly() {
		return logCzly;
	}

	public void setLogCzly(String logCzly) {
		this.logCzly = logCzly;
	}

	public String getLogPlat() {
		return logPlat;
	}

	public void setLogPlat(String logPlat) {
		this.logPlat = logPlat;
	}

	public String getLogUser() {
		return logUser;
	}

	public void setLogUser(String logUser) {
		this.logUser = logUser;
	}

	public String getLogCkfw() {
		return logCkfw;
	}

	public void setLogCkfw(String logCkfw) {
		this.logCkfw = logCkfw;
	}

	public String getLogNr() {
		return logNr;
	}

	public void setLogNr(String logNr) {
		this.logNr = logNr;
	}
	
	public int getIsmap() {
		return ismap;
	}

	public void setIsmap(int ismap) {
		this.ismap = ismap;
	}
	
//	/**
//	 * 由于ibatis连接timesten不支持加法运算
//	 * 所以在java处理
//	 * @return [参数说明]
//	 * 
//	 * @return int [返回类型说明]
//	 * @exception throws [违例类型] [违例说明]
//	 * @see [类、类#方法、类#成员]
//	 */
//	public int getEndnum(){
//		return start+count;
//	}
}
