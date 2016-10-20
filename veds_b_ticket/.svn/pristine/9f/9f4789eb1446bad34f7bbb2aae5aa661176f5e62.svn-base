package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.Shyhb;

public interface ShyhbDao extends Mapper<Shyhb> {
	@Select(value = "SELECT * FROM SH_YHB WHERE shbh=#{param1}  AND bh=#{param2}")
	Shyhb getShyhb(String shbh, String bh);

	/**
	 * <根据商户编号查找用户>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Shyhb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@Select(value = "SELECT bh,shbh,xm,mm,shbmid FROM SH_YHB WHERE shbh=#{param1}")
	List<Shyhb> queryShyhbListBySh(String shbh);
}
