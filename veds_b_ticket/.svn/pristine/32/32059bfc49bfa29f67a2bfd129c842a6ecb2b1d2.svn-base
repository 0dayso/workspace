package cn.vetech.vedsb.jp.dao.jpzdcp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpGjGz;
@JpMyBatisRepository
public interface JpZdcpGjGzDao extends Mapper<JpZdcpGjGz>{

	/**
	 * 获取优先级值
	 * @param shbh
	 * @return
	 */
	@Select(value="select nvl(max(yxj),0) as maxyxj from JP_ZDCP_GJ_GZ where shbh = #{param1}")
	String getMaxYxj(String shbh);
	
	/**
	 * 验证自动出票规则名称唯一性
	 * @param shbh
	 * @param gzmc
	 * @return
	 */
	@Select(value="select count(1) from JP_ZDCP_GJ_GZ where shbh=#{param1} and gzmc=#{param2}")
	int verifyOnly(String shbh,String gzmc);
	
	/**
	 * 根据商户编号查询规则设置
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from JP_ZDCP_GJ_GZ where shbh=#{param1} and zt<>'2'")
	List<JpZdcpGjGz> getzdcpidbyshbh(String shbh);
	/**
	 * 根据条件查询集合
	 * @param jpzdcpgjgz
	 * @return
	 */
	List<JpZdcpGjGz> queryPage(JpZdcpGjGz jpzdcpgjgz);
	
	/**
	 * 根据条件获得总条数
	 * @param jpzdcpgjgz
	 * @return
	 */
	int getTotalCount(JpZdcpGjGz jpzdcpgjgz);
	/**
	 * 批量更新
	 * @param param
	 */
	void batchUpdateZt(Map<String,Object> param);
	
	/**
	 * 根据商户编号和状态查询条数
	 */
	@Select(value="select count(1) from JP_ZDCP_GJ_GZ where shbh=#{param1} and zt=#{param2}")
	int getCountByShbhAndZt(String shbh,String zt);
}
