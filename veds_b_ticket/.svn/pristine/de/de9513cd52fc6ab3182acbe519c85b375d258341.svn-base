package cn.vetech.vedsb.jp.dao.jpgqgl;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqd;
@JpMyBatisRepository
public interface JpGqdDao extends Mapper<JpGqd>{
	
	/**
	 * 改签单管理页面分页查询
	 * @param jpgqd
	 * @return
	 */
	List<Map<String,Object>> query(Map<String,Object> jpgqdMap);
	
	/**
	 * 获得总条数
	 * @param jpgqd
	 * @return
	 */
	int getPageCount(Map<String,Object> jpgqdMap);
	
	/**
	 * 通过改签单号查询改签信息
	 * @param jpGqd
	 * @return
	 */
	JpGqd getJpGqdByGqdh(JpGqd jpGqd);
	
	/**
	 * 通过改签单号修改改签单信息
	 * @param jpgqd
	 * @return
	 */
	int updateJpGqdById(JpGqd jpgqd);
	
	
	/**
	 * 历史数据调整机票改签单调整列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getHistoryGqList(Map<String, Object> map);
	
	/**
	 * 历史数据调整根据条件查询
	 * @param map
	 * @return
	 */
	Map<String, Object> getjpgqdobject(Map<String, Object> map);
	
	/**
	 * 修改改签单价格
	 * @param map
	 */
	void saveGqdHistory(Map<String, Object> map);
	
	
	/**
	 * 根据外部单号找对账的业务订单
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> genDzDataByWbdh(Map<String, Object> map);
	
	public List<JpGqd> checkGqdByWbdh(Map<String,Object> map);
	
	public List<Map<String, String>> getTknoByGqTkno(Map<String,Object> map);
}
