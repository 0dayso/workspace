package org.vetech.core.modules.web.select;


import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class SelectCopy {
	/**
	 * 配置拷贝的属性对应到传入list中的值 ComboNode中需要拷贝的属性需要有默认值
	 */
	private SelectNode defaultNode;

	public SelectCopy() {
	}

	public SelectCopy(SelectNode node) {
		this.defaultNode = node;
	}

	/**
	 * 拷贝Map
	 * 
	 * @param node
	 * @param obj
	 */
	private void copyMap(SelectNode node, Map<?, ?> obj) {
		node.setValue(obj.get(defaultNode.getValue()) != null ? obj.get(defaultNode.getValue()).toString() : "");
		node.setLabel(obj.get(defaultNode.getLabel()) != null ? obj.get(defaultNode.getLabel()).toString() : "");
		node.setMc(obj.get(defaultNode.getMc()) != null ? obj.get(defaultNode.getMc()).toString() : "");
		node.setPyqp(obj.get(defaultNode.getPyqp()) != null ? obj.get(defaultNode.getPyqp()).toString() : "");
		node.setPyszm(obj.get(defaultNode.getPyszm()) != null ? obj.get(defaultNode.getPyszm()).toString() : "");
		if(defaultNode.getAttributes()==null)
			node.setAttributes(obj);
	}

	/**
	 * 拷贝对象中的属性
	 * 
	 * @param node
	 *            拷贝的目标对象
	 * @param obj
	 *            待拷贝的对象
	 */
	private void copyBean(SelectNode node, Object obj) {
		node.setValue(propertyValue(obj, defaultNode.getValue()));
		node.setLabel(propertyValue(obj, defaultNode.getLabel()));
		node.setMc(propertyValue(obj, defaultNode.getMc()));
		node.setPyqp(propertyValue(obj, defaultNode.getPyqp()));
		node.setPyszm(propertyValue(obj, defaultNode.getPyszm()));
		if(defaultNode.getAttributes()==null)
			node.setAttributes(obj);
	}

	/**
	 * 获得指定对象中指定属性的值,取不到则反馈空窜("")
	 * 
	 * @param obj
	 *            需要取值的对象
	 * @param property
	 *            需要获得属性的属性名
	 */
	private String propertyValue(Object obj, String property) {
		try {
			return (String)BeanUtils.getProperty(obj, property);
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将指定对象复制成一个ComboNode对象
	 */
	final void copy(SelectNode node, Object obj) {
		if (obj instanceof Map) { // Map或javaBean
			copyMap(node, (Map<?, ?>) obj);
		} else {
			copyBean(node, obj);
		}
	}

}
