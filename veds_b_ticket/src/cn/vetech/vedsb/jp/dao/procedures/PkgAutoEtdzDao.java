package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;

@JpMyBatisRepository
public interface PkgAutoEtdzDao {
      
	/**
	 * <调用后台检查能否ETDZ>
	 * 
	 * @param param [参数说明]
	 */
	void checkEtdz(Map<String, Object> param);
	
	/**
	 * <自动转机票>
	 * 
	 * @param param [参数说明]
	 */
	void autoEtdz(Map<String, Object> param);
}
