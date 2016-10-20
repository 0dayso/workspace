package cn.vetech.vedsb.jp.dao.jpzwgl;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqZwsz;
@JpMyBatisRepository
public interface JpTjsqZwszDao extends Mapper<JpTjsqZwsz>{
	/**
	 * 获取追位规则
	 * @param jpTjsqZwsz
	 * @return
	 */
	List<JpTjsqZwsz> queryPage(JpTjsqZwsz jpTjsqZwsz);
	/**
	 * 获取追位规则总条数
	 * @param jpTjsqZwsz
	 * @return
	 */
	int getTotalCount(JpTjsqZwsz jpTjsqZwsz);
	/**
	 * 不分页查询列表
	 * @param jpTjsqZwsz
	 * @return
	 */
	List<JpTjsqZwsz> getZwgzList(JpTjsqZwsz jpTjsqZwsz);
}
