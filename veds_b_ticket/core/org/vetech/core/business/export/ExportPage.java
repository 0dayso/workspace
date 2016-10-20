package org.vetech.core.business.export;


import java.io.Serializable;
import java.util.Collection;

import org.vetech.core.modules.mybatis.page.AbstractPageEntity;



/**
 * 分页导出
 * 
 * @author heer
 * @version [版本号, Aug 19, 2014]
 * @see [相关类/方法]
 * @since [民航代理人系统(ASMS)/ASMS5000]
 */
public abstract class ExportPage<T extends AbstractPageEntity> implements Serializable {
	
	protected T t;
	
	/**
	 *<默认构造函数>
	 *
	 *@param t getCollection方法的参数
	 */
	public ExportPage(T t) {
//		t.setOperate(Entity.NOCOUNT);	//调用getCollection不执行DAO的getPage方法中的统计语句
		this.t = t;
	}

	/**
	 * 分页导出的数据
	 * 分页中的start和count参数是根据构造该类传入参数的start和count属性
	 * 
	 * @param t 查询参数对象
	 * @return Collection<?> [返回类型说明]
	 */
	public abstract Collection<?> getCollection(T t);
	
	/**
	 * 导出时对每个对象做前期处理
	 * 
	 * @param o getCollection方法返回集合中的一个对象
	 */
	public void beforeExport(Object o){};
	
}
