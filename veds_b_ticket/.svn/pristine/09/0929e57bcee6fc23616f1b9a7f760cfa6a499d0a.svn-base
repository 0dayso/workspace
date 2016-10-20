package cn.vetech.vedsb.jp.dao.pzzx;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzTh;
@JpMyBatisRepository
public interface JpPzThDao  extends Mapper<JpPzTh> {

	/**
	 * 批量保存JpPzTh对象
	 * @param list
	 */
	public void batchInsertJpPzTh(List<JpPzTh> list);
	/**
	 * 根据用户输入的信息查询
	 * @param param
	 * @return
	 */
	public List<JpPzTh> getListByAll(Map<String,Object> param);
	
	/**
	 * 根据用户编号查询总有记录数
	 * @param param
	 * @return
	 */
	public int selectCountByAll(Map<String,Object> param);
}
