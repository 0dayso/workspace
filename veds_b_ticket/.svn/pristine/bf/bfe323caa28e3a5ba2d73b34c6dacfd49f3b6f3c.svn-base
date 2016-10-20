package cn.vetech.vedsb.jp.dao.pzzx;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.pzzx.JpPzKc;

@JpMyBatisRepository
public interface JpPzKcDao extends Mapper<JpPzKc>{

    int insert(JpPzKc record);

	List<JpPzKc> queryFromKC(Map<String, Object> param);

	int selectCountByAll(Map<String, Object> param);

	void updatePzztById(String id);

	void deleteByInNo(JpPzKc jpPzKc);
	
	List<JpPzKc> getListByAll(Map<String, Object> param);
	/**
	 * 在票证退回区列表显示
	 * @param param
	 * @return
	 */
	List<JpPzKc> getKcThList(Map<String, Object> param);
	/**
	 * 在票证退回区列表总记录数
	 * @param param
	 * @return
	 */
	int getKcThCount(Map<String, Object> param);
	/**
	 * 在票证报废区列表
	 * @param param
	 * @return
	 */
	List<JpPzKc> getKcBfList(Map<String,Object> param);
	/**
	 * 在票证报废区列表总记录数
	 * @param param
	 * @return
	 */
	int getKcBfCount(Map<String,Object> param);
}
