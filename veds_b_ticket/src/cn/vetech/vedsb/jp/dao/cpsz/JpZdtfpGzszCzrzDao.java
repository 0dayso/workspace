package cn.vetech.vedsb.jp.dao.cpsz;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cpsz.JpZdtfpGzszCzrz;


/**
 * 
 * @author wangxuexing
 */
@JpMyBatisRepository
public interface JpZdtfpGzszCzrzDao extends Mapper<JpZdtfpGzszCzrz>{
	/**
	 * 根据实体Bean获取操作日志列表
	 * @param jpZdtfpGzszCzrz
	 * @return
	 */
	public List<JpZdtfpGzszCzrz> getCzRzByEntity(JpZdtfpGzszCzrz jpZdtfpGzszCzrz);
}
