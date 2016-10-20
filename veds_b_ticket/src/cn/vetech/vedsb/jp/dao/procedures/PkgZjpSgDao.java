package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
@JpMyBatisRepository
public interface PkgZjpSgDao {
	
	void sgZjp(Map<String, Object> param);
	
	/**
	 * cps出票通知给电商转机票
	 */
	void fzdzjpCps(Map<String, Object> param);
	
	/**
	 * 平台出票通知给电商
	 * @param param
	 */
	void fzjppt(Map<String, Object> param);
	
	/**
	 * 淘宝派单转机票
	 * @param param
	 */
	void fzjppd(Map<String, Object> param);
	
	/**
	 * 航司官网订单转机票
	 * @param param
	 */
	void fzjpb2b(Map<String, Object> param);
	
	
}
