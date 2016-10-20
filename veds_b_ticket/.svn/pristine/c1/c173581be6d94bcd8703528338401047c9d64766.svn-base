package cn.vetech.vedsb.jp.dao.jpzdcp;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgz;
@JpMyBatisRepository
public interface JpZdcpgzDao extends Mapper<JpZdcpgz>{
	/**
	 * 获取优先级值
	 * @param shbh
	 * @return
	 */
	@Select(value="select nvl(max(yxj),0) as maxyxj from JP_ZDCPGZ where shbh = #{param1}")
	String getMaxYxj(String shbh);
	
	/**
	 * 验证自动出票规则名称唯一性
	 * @param shbh
	 * @param gzmc
	 * @return
	 */
	@Select(value="select count(gzmc) from JP_ZDCPGZ where shbh=#{param1} and gzmc=#{param2}")
	int verifyOnly(String shbh,String gzmc);
	
	/**
	 * 根据商户编号查询规则设置
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from JP_ZDCPGZ where shbh=#{param1} and zt<>'2'")
	List<JpZdcpgz> getzdcpidbyshbh(String shbh);
	
	//不分页查询
	List<JpZdcpgz> getCpgzList(JpZdcpgz jpZdcpgz) ;
}