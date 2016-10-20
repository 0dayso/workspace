package org.vetech.core.modules.web.select;


import java.util.ArrayList;
import java.util.List;

public class Select {

	/**
	 * 使用默认属性配置将数据转换成treeList
	 * 
	 * @param datas
	 *            待转换的数据
	 * @param node
	 *            转换是datas中的数据对应到ComboNode中的属性对应
	 */
	public static List<SelectNode> toSelect(List<?> datas, SelectNode selectNode) {
		SelectCopy copy = new SelectCopy(selectNode);
		List<SelectNode> result = new ArrayList<SelectNode>();
		for (Object obj : datas) {
			SelectNode node = new SelectNode();
			copy.copy(node,obj); // 拷贝自定义属性
			result.add(node);
		}

		return result;
	}
}
