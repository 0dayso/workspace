package cn.vetech.vedsb.jp.dao.procedures;

import java.util.List;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;

@JpMyBatisRepository
public interface PkgPsChildDao {
	
	/**
	 * 批量订单完成
	 * @param paramsXml
	 * @return
	 */
	public List<Object> batchWc(String paramsXml);
}
