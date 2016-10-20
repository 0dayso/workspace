package cn.vetech.vedsb.common.dao;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.BYdrzCzrz;
/**
 * 操作日志dao
 */

public interface BYdrzCzrzDao extends Mapper<BYdrzCzrz>{
	/**
	 * 添加日志
	 * @param ydrzCzrz
	 */
	public void xzrz(BYdrzCzrz ydrzCzrz);
	/**
	 * 批量分页
	 * @param wdzlsz
	 * @return
	 */
	public List searchCzrzs(BYdrzCzrz o);
	/**
	 * 批量总页数
	 * @param wdzlsz
	 * @return
	 */
	public int getTotalCounts(BYdrzCzrz o);
	
	/**
	 * 分页
	 * @param wdzlsz
	 * @return
	 */
	public List searchCzrz(BYdrzCzrz o);

	/**
	 * 总页数
	 * @param wdzlsz
	 * @return
	 */
	public int getTotalCount(BYdrzCzrz o);
}
