/**
*@Title:       
*@Description: 
*@Author:      
*@Version:     1.1
*@Date:        2015-04-02
*@Modify:      
*/

package cn.vetech.vedsb.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.Shcsb;

public interface ShcsbDao extends Mapper<Shcsb>{
	
	/**
	 * 根据参数编号和商户编号查询
	 */
	@Select(value="select * from SH_CSB where bh=#{param1} and shbh=#{param2}")
	Shcsb findCs(String bh,String shbh);
	
	/**
	 * 根据商户编号查询参数
	 */
	@Select(value="select * from SH_CSB where shbh=#{param1}")
	List<Shcsb> findbyShbh(String shbh);
	
	/**
	 * 查询商户参数及默认参数
	 */
	List<Shcsb> getShcsbList(Shcsb shcsb);
	
	/**
	 * 查询分页总条数
	 */
	int getTotalCount(Shcsb shcsb);
	/**
	 * 获取该商户下，机票订单类颜色提醒集合
	 * @param param
	 * @return
	 */
	List<Shcsb> getListByShbhAndBhs(Map<String,Object> param);
	/**
	 * 更新机票订单类颜色提醒
	 * @param param
	 */
	void updateColorRemind(List<Shcsb> param);
	/**
	 * 更新机票订单默认排序
	 * @param param
	 */
	void updateMrpx(Map<String,Object> param);
	
	/**
	 * 插入机票订单默认排序
	 */
	void insertMrpx(Shcsb shcsb);
}
