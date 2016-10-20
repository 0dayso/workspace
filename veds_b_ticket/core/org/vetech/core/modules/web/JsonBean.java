package org.vetech.core.modules.web;

import java.io.Serializable;
/**
 * 封装JSON对象,提供错误信息
 * @author 章磊
 *
 */
public class JsonBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2836888626800793314L;
	private Object result;//实际返回的结果
	private String error;//错误信息
	public JsonBean(Object result){
		this.result = result;
	}
	public JsonBean(Object result,String error){
		this.result = result;
		this.error = error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
