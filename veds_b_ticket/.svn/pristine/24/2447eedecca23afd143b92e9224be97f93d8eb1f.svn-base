package cn.vetech.vedsb.jp.dao.jpzdcp;

import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpGjdocaSz;
@JpMyBatisRepository
public interface JpGjdocaSzDao extends Mapper<JpGjdocaSz>{

	/**
	 * 根据商户编号和csszm查询，用于验证csszm的唯一性
	 * @param param
	 * @return
	 */
	int getCountByShbhAndCsszm(Map<String,Object> param);

}

