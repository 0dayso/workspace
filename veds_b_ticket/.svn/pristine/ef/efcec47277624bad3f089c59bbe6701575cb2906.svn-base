package cn.vetech.vedsb.jp.dao.jpzdcp;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpGjGzYxjsz;

@JpMyBatisRepository
public interface JpZdcpGjGzYxjszDao extends Mapper<JpZdcpGjGzYxjsz>{
	/**
	 * 根据gjgzid获取对应的记录
	 * @param gjgzid
	 * @return
	 */
	@Select(value="select * from JP_ZDCP_GJ_GZ_YXJSZ where gjgzid = #{param1}")
	List<JpZdcpGjGzYxjsz> getListByGjgzid(String gjgzid);
	/**
	 * 根据gjgzid删除对应的记录
	 * @param gjgzid
	 */
	@Delete(value="delete from JP_ZDCP_GJ_GZ_YXJSZ where gjgzid = #{param1}")
	void delByGjgzid(String gjgzid);
}
