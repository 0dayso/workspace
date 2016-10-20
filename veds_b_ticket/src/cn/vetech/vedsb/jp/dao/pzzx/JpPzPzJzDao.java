package cn.vetech.vedsb.jp.dao.pzzx;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzPzJz;
@JpMyBatisRepository
public interface JpPzPzJzDao  extends Mapper<JpPzPzJz> {

	/**
	 * 批量插入
	 * @param param
	 */
	public void batchInsert(List<JpPzPzJz> param);
	
	/**
	 * 根据PzBh和商户编号查询
	 * @param param
	 * @return
	 */
	public List<JpPzPzJz> getListByPzBh(Map<String,Object> param);
}
