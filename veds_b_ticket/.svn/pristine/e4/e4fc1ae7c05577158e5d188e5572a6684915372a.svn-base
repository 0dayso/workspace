package cn.vetech.vedsb.jp.dao.jpbcd;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpbcdgl.JPBcd;

@JpMyBatisRepository
public interface JpBcdDao extends Mapper<JPBcd>{
	/**
	 * 根据商户编号和业务单号获取补差单信息
	 * @param shbh
	 * @param ywdh
	 * @return
	 */
	@Select(value="select * from jp_bcd where shbh=#{param1} and ywdh=#{param2} and ywlx=#{param3}")
	List<JPBcd> getbcdList(String shbh,String ywdh,String ywlx);
	
	/**
	 * 根据外部单号找对账的业务订单
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> genDzDataByWbdh(Map<String, Object> map);
}
