package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;

@JpMyBatisRepository
public interface PkgQueryDao {
	/**
	 * 退票报表查询
	 * @param param
	 * @return
	 */
	void tfpReport(Map<String, Object> param);
	
	/**
	 * 改签报表查询
	 * @param param
	 * @return
	 */
	void gqReport(Map<String,Object> param);
	
	/**
	 * 出票报表查询
	 * @param param
	 * @return
	 */
	void cpReport(Map<String,Object> param);
}
