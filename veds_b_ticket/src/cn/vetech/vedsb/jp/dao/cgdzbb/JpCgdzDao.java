package cn.vetech.vedsb.jp.dao.cgdzbb;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdz;

@JpMyBatisRepository
public interface JpCgdzDao extends Mapper<JpCgdz> {
	/**
	 * 查询采购对账数据
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> genCgdzData(Map<String, Object> param);

	/**
	 * 查询航协正常票对账数据
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> genZcpData(Map<String, Object> param);

	/**
	 * 查询航协退费票对账数据
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> genTfpData(Map<String, Object> param);
}
