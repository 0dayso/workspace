package org.vetech.core.modules.mybatis.page;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import org.vetech.core.modules.mybatis.entity.AbstractMyBatisEntity;

/**
 * 分页用的实体对象，如果要用到分页就必须继承这个对象
 * 
 * @author 章磊
 *
 */
public abstract class AbstractPageEntity extends AbstractMyBatisEntity {
	@Transient
	private int start;
	@Transient
	private int count;

	@Transient
	private String orderBy;
	/**
	 * 存储代号对应的中文
	 */
	@Transient
	private Map<String, Object> ex = new HashMap<String, Object>();

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Map<String, Object> getEx() {
		return ex;
	}
}
