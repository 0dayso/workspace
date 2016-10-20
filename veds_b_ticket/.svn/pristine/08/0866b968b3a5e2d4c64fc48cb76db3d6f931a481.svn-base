package cn.vetech.vedsb.jp.dao.jphbyd;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jphbyd.JpHbyd;

@JpMyBatisRepository
public interface JpHbydDao extends Mapper<JpHbyd>{

	/**
	 * 根据传入对象进行航班异动分页查询
	 * @param hbyd
	 * @param pageNum
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	List<Map<String, Object>> queryHbydList(@Param(value = "hbyd") JpHbyd hbyd, @Param(value = "pageNum")int pageNum, 
			@Param(value = "pageSize")int pageSize,@Param(value = "sortType")String sortType);

	/**
	 * 查询航班异动记录
	 * @param hbyd
	 * @return
	 */
	int queryHbydCount(@Param(value = "hbyd")JpHbyd hbyd);

	/**
	 * 根据传入条件查询受影响订单列表
	 * @param hbyd
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> queryHbydClList(@Param(value = "hbyd")JpHbyd hbyd, @Param(value = "pageNum")int pageNum, 
			@Param(value = "pageSize")int pageSize);

	/**
	 * 根据传入条件查询受影响订单记录
	 * @param hbyd
	 * @return
	 */
	int queryHbydClCount(@Param(value = "hbyd")JpHbyd hbyd);
	
	
	/**
	 * 
	 */
	int isHbyd(JpHbyd hbyd);
	
	
	JpHbyd getHbydByHbh(JpHbyd hbyd);
}
