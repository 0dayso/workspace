package cn.vetech.vedsb.jp.dao.cpsz;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtpJk;
@JpMyBatisRepository
public interface JpZdtpJkDao extends Mapper<JpZdtpJk>{

	/**
	 * 查询自动监控信息
	 * @param param
	 * @return
	 */
	public List<Map<String,Object>> selectAllJpZdtpJk(Map<String,Object> param);
	/**
	 * 查询自动监控信息记录数
	 * @param param
	 * @return
	 */
	public int selectAllJpZdtpJkCount(Map<String,Object> param);
	
	public JpZdtpJk getJpZdtpJkCreateInfo(String shbh,String tpdh);
	
	public JpZdtpJk getJpZdtpJkInfo(JpZdtpJk jpZdtpJk);
	
}