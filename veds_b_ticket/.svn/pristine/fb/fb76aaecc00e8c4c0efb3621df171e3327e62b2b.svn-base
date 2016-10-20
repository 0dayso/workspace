package cn.vetech.vedsb.jp.dao.qxxgl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.qxxgl.BQinfoRz;
@JpMyBatisRepository
public interface BQinfoRzDao extends Mapper<BQinfoRz>{

	/**
	 * 根据商户编号 Q信息编号查询日志集合
	 * @param shbh
	 * @param qInfoBh
	 * @return
	 */
	List<BQinfoRz> queryByShbhAndId(Map<String,Object> param);
	/**
	 * 根据商户编号 Q信息编号查询对应的记录数
	 * @param shbh
	 * @param qInfoBh
	 * @return
	 */
	@Select(value="select count(1) from B_QINFO_RZ where shbh = #{param1} and q_info_bh = #{param2}")
	int queryCount(String shbh,String qInfoBh);
}
