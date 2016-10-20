package org.vetech.core.business.cache;

/**
 * 需要串联的提供代码获取对象的继承
 * 
 * @author zhanglei
 *
 */
public interface AttachInf {
	/**
	 * 
	 * @param fixedvalue
	 *            固定的参数 如商户编号
	 * @param attrObject
	 *            从当前bean或map中动态获取的值
	 * @return
	 */
	Object getBean(Object[] fixedvalue, Object[] attrObject);
}
