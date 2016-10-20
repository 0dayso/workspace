package org.vetech.core.modules.web.ztree;



import java.util.Map;

import org.vetech.core.modules.utils.Reflections;


public class ZTreeCopy {
	/**
	 * 配置拷贝的属性对应到传入list中的值 ComboNode中需要拷贝的属性需要有默认值
	 */
	private ZTreeNode defaultNode;

	public ZTreeCopy() {
	}

	public ZTreeCopy(ZTreeNode node) {
		this.defaultNode = node;
	}

	/**
	 * 拷贝Map
	 * 
	 * @param node
	 * @param obj
	 */
	private void copyMap(ZTreeNode node, Map<?, ?> obj) {
		node.setId(obj.get(defaultNode.getId()) != null ? obj.get(defaultNode.getId()).toString() : "");
		node.setName(obj.get(defaultNode.getName()) != null ? obj.get(defaultNode.getName()).toString() : "");
		node.setpId(obj.get(defaultNode.getpId()) != null ? obj.get(defaultNode.getpId()).toString() : "");
		node.setChecked(obj.get(defaultNode.getChecked()) != null ? obj.get(defaultNode.getChecked()).toString() : "");
		node.setIsParent(obj.get(defaultNode.getIsParent()) != null ? obj.get(defaultNode.getIsParent()).toString() : "");
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
	private void copyBean(ZTreeNode node, Object obj) {
		node.setId(propertyValue(obj, defaultNode.getId()));
		node.setName(propertyValue(obj, defaultNode.getName()));
		node.setpId(propertyValue(obj, defaultNode.getpId()));
		node.setChecked(propertyValue(obj, defaultNode.getChecked()));
		node.setIsParent(propertyValue(obj, defaultNode.getIsParent()));
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
			return Reflections.getFieldValue(obj, property)+"";
		} catch (Exception e) {
//			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 将指定对象复制成一个ComboNode对象
	 */
	final void copy(ZTreeNode node, Object obj) {
		if (obj instanceof Map) { // Map或javaBean
			copyMap(node, (Map<?, ?>) obj);
		} else {
			copyBean(node, obj);
		}
	}
}
