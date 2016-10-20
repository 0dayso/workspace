package cn.vetech.vedsb.jp.dao.pzzx;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzBf;
@JpMyBatisRepository
public interface JpPzBfDao  extends Mapper<JpPzBf> {

	/**
	 * 批量保存JpPzBf对象
	 * @param list
	 */
	public void batchInsertJpPzBf(List<JpPzBf> list);
	/**
	 * 根据用户输入的信息查询
	 * @param param
	 * @return
	 */
	public List<JpPzBf> getListByAll(Map<String,Object> param);
	
	/**
	 * 根据用户编号查询总有记录数
	 * @param param
	 * @return
	 */
	public int selectCountByAll(Map<String,Object> param);
	/**
	 * 根据bfNo修改SFSH的状态
	 * @param param
	 */
	public void updateSfshBybfNo(Map<String,Object> param);
}
