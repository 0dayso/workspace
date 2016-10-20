package cn.vetech.vedsb.jp.dao.jpbcd;

import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JpCyd;
@JpMyBatisRepository
public interface JpCydDao extends Mapper<JpCyd>{
	/**
	 * 查询正常机票订单
	 * @param jpPnr
	 * @param jpWbdh
	 * @param shbh
	 * @return
	 */
	public List<Map<String,Object>> getCydKhdd(JpCyd jpcyd);
	
	/**
	 * 根据订单编号查订单
	 * @param shbh
	 * @param ddbh
	 * @return
	 */
	public List<Map<String,Object>> getCydKhddDdbh(JpCyd jpcyd);
	
	/**
	 * 查询退废订单
	 * @param shbh
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	public List<Map<String,Object>> getCydTfdd(JpCyd jpcyd);
	
	/**
	 * 根据退票单号查询退票订单
	 * @param jpcyd
	 * @return
	 */
	public List<Map<String,Object>> getCydTfddTpdh(JpCyd jpcyd);
	/**
	 * 查询改签订单
	 * @param shbh
	 * @param jpPnr
	 * @param jpWbdh
	 * @return
	 */
	public List<Map<String,Object>> getCydgqdd(JpCyd jpcyd);
	
	/**
	 * 根据改签单号查询改签订单
	 * @param jpcyd
	 * @return
	 */
	public List<Map<String,Object>> getCydgqddGqdd(JpCyd jpcyd);
	/**
	 * 修改保存差异单
	 * @param jpcyd
	 */
	public void updateCyd(JpCyd jpcyd);
	
	/**
	 * 历史数据调整查询差异单
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getHistoryCyList(Map<String, Object> map);
}
