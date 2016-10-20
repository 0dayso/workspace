package cn.vetech.vedsb.jp.dao.procedures;

import java.util.Map;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
@JpMyBatisRepository
public interface PkgTicketTfDao {
	/**
	 * <平台退废票通知结果提交到后台处理>
	 * @param param
	 */
	void ptCgProcess(Map<String, Object> param);
}
