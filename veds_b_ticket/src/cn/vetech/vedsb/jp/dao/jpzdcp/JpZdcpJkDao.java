package cn.vetech.vedsb.jp.dao.jpzdcp;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpJk;
@JpMyBatisRepository
public interface JpZdcpJkDao extends Mapper<JpZdcpJk>{

	/**
	 * 查询自动监控信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectAllJpZdcpJk(Map<String,Object> param);
	/**
	 * 查询自动监控信息记录数
	 * @param param
	 * @return
	 */
	public int selectAllJpZdcpJkCount(Map<String,Object> param);
	
	/**
	 * 查询需要全自动出票的订单
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> getZdcpDdByWdid(Map<String,Object> param);
	
	
}