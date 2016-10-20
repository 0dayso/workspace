package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
@JpMyBatisRepository
public interface PkgExtendDao {
	
	/**
	 * 根据商户编号获取本地扫描需要扫描的OPEN票
	 * @param param
	 */
	void getTksForCompanies(Map<String,Object> param);
	
	/**
	 * DETR回写
	 */
	void fUpdateTkState(Map<String,Object> param);
}
