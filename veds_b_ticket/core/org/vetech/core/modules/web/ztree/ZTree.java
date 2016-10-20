package org.vetech.core.modules.web.ztree;



import java.util.ArrayList;
import java.util.List;

public class ZTree {
	/**
	 * 使用默认属性配置将数据转换成treeList
	 * 
	 * @param datas
	 *            待转换的数据
	 * @param node
	 *            转换是datas中的数据对应到ZTreeNode中的属性对应
	 */
	public static List<ZTreeNode> toZTree(List<?> datas, ZTreeNode ztreeNode) {
		ZTreeCopy copy = new ZTreeCopy(ztreeNode);
		List<ZTreeNode> result = new ArrayList<ZTreeNode>();
		for (Object obj : datas) {
			ZTreeNode node = new ZTreeNode();
			copy.copy(node,obj); // 拷贝自定义属性
			result.add(node);
		}

		return result;
	}
}
