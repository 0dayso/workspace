package org.vetech.core.business.pid.util;

import java.util.Date;

public class Pidlog extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2707895611350787929L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * A B平台 发起接口调用的地方
	 */
	private String plat;
	/**
	 * 接口地址
	 */
	private String url;
	/**
	 * 接口名称
	 */
	private String jkname;
	/**
	 * 调用时间
	 */
	private Date inputtime;
	/**
	 * 输入参数
	 */
	private String input;
	/**
	 * 输出时间
	 */
	private Date outputtime;
	/**
	 * 输出内容
	 */
	private String output;
	/**
	 * 发起接口调用的商户
	 */
	private String czshbh;
	/**
	 * 发起接口调用的部门
	 */
	private String czdeptid;
	/**
	 * 发起接口调用的用户
	 */
	private String czuserid;

	/**
	 * 类型
	 */
	private String jktype;
	
	/**
	 * 耗时
	 */
	private Long usetime;

	/**
	 * 获得主键
	 * 
	 * @return String 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获得A B平台 发起接口调用的地方
	 * 
	 * @return String A B平台 发起接口调用的地方
	 */
	public String getPlat() {
		return plat;
	}

	/**
	 * 设置A B平台 发起接口调用的地方
	 * 
	 * @param plat
	 *            A B平台 发起接口调用的地方
	 */
	public void setPlat(String plat) {
		this.plat = plat;
	}

	/**
	 * 获得接口地址
	 * 
	 * @return String 接口地址
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置接口地址
	 * 
	 * @param url
	 *            接口地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得接口名称
	 * 
	 * @return String 接口名称
	 */
	public String getJkname() {
		return jkname;
	}

	/**
	 * 设置接口名称
	 * 
	 * @param jkname
	 *            接口名称
	 */
	public void setJkname(String jkname) {
		this.jkname = jkname;
	}

	/**
	 * 获得调用时间
	 * 
	 * @return Date 调用时间
	 */
	public Date getInputtime() {
		return inputtime;
	}

	/**
	 * 设置调用时间
	 * 
	 * @param inputtime
	 *            调用时间
	 */
	public void setInputtime(Date inputtime) {
		this.inputtime = inputtime;
	}

	/**
	 * 获得输入参数
	 * 
	 * @return String 输入参数
	 */
	public String getInput() {
		return input;
	}

	/**
	 * 设置输入参数
	 * 
	 * @param input
	 *            输入参数
	 */
	public void setInput(String input) {
		this.input = input;
	}

	/**
	 * 获得输出时间
	 * 
	 * @return Date 输出时间
	 */
	public Date getOutputtime() {
		return outputtime;
	}

	/**
	 * 设置输出时间
	 * 
	 * @param outputtime
	 *            输出时间
	 */
	public void setOutputtime(Date outputtime) {
		this.outputtime = outputtime;
	}

	/**
	 * 获得输出内容
	 * 
	 * @return String 输出内容
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * 设置输出内容
	 * 
	 * @param output
	 *            输出内容
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * 获得发起接口调用的商户
	 * 
	 * @return String 发起接口调用的商户
	 */
	public String getCzshbh() {
		return czshbh;
	}

	/**
	 * 设置发起接口调用的商户
	 * 
	 * @param czshbh
	 *            发起接口调用的商户
	 */
	public void setCzshbh(String czshbh) {
		this.czshbh = czshbh;
	}

	/**
	 * 获得发起接口调用的部门
	 * 
	 * @return String 发起接口调用的部门
	 */
	public String getCzdeptid() {
		return czdeptid;
	}

	/**
	 * 设置发起接口调用的部门
	 * 
	 * @param czdeptid
	 *            发起接口调用的部门
	 */
	public void setCzdeptid(String czdeptid) {
		this.czdeptid = czdeptid;
	}

	/**
	 * 获得发起接口调用的用户
	 * 
	 * @return String 发起接口调用的用户
	 */
	public String getCzuserid() {
		return czuserid;
	}

	/**
	 * 设置发起接口调用的用户
	 * 
	 * @param czuserid
	 *            发起接口调用的用户
	 */
	public void setCzuserid(String czuserid) {
		this.czuserid = czuserid;
	}

	/**
	 * 获得耗时
	 * 
	 * @return Integer 耗时
	 */
	public Long getUsetime() {
		return usetime;
	}

	/**
	 * 设置耗时
	 * 
	 * @param usetime
	 *            耗时
	 */
	public void setUsetime(Long usetime) {
		this.usetime = usetime;
	}

	/**
	 * 获得类型
	 * 
	 * @return String 类型
	 */
	public String getJktype() {
		return jktype;
	}

	/**
	 * 设置类型
	 * 
	 * @param jktype
	 *            类型
	 */
	public void setJktype(String jktype) {
		this.jktype = jktype;
	}

}
