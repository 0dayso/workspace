package cn.vetech.vedsb.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.Shbm;

public interface JpShbmDao extends Mapper<Shbm>{

	/**
	 * 根据商户编号查询商户部门集合
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from SH_BM where shbh=#{param1}")
	List<Shbm> query(String shbh);
	/**
	 * 根据部门ID查询对应的bean
	 * @param id
	 * @return
	 */
	@Select(value="select * from SH_BM where id=#{param1} and shbh=#{param2}")
	Shbm getShbmById(String id,String shbh);
}
