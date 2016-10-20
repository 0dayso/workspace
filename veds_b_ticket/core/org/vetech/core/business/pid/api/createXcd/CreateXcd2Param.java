package org.vetech.core.business.pid.api.createXcd;

import org.vetech.core.business.pid.api.Param;

/**
 * 
 * 行程单创建新接口 
 * @author  wangxuexing
 * @version  [版本号, 2016-0802]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CreateXcd2Param extends Param{
	private String tkno;
	private String xcdno;
	public String getTkno() {
		return tkno;
	}
	public void setTkno(String tkno) {
		this.tkno = tkno;
	}
	public String getXcdno() {
		return xcdno;
	}
	public void setXcdno(String xcdno) {
		this.xcdno = xcdno;
	}
}
