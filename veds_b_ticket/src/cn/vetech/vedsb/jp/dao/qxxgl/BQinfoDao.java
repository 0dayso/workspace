package cn.vetech.vedsb.jp.dao.qxxgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfo;
@JpMyBatisRepository
public interface BQinfoDao extends Mapper<BQinfo>{
	
	/**
	 * 根据条件查询Qinfo集合
	 * @param shbh
	 * @return
	 */
	List<Map<String,Object>> query(Map<String,Object> param);
	/**
	 * 根据条件查询记录数
	 * @param param
	 * @return
	 */
	int queryCount(Map<String,Object> param);
	/**
	 * 批量更新
	 * @param bqinfo
	 */
	void updateBatchBQinfo(Map<String,Object> param);
	
	/**
	 * 根据ID和商户编号查询对应的bean
	 * @param shbh
	 * @param id
	 * @return
	 */
	@Select(value="select * from B_Qinfo where shbh = #{param1} and id = #{param2}")
	BQinfo getBeanByShbhAndId(String shbh,String id);
	
    /**
     * 判断是否有Q信息  返回 0 或 1
     * @param t
     * @return
     */
	int isBqinfo(BQinfo t);
}

