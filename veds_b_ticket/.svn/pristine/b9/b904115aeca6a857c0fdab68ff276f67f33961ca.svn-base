package cn.vetech.vedsb.common.dao.base;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.Shshb;
public interface ShshbDao extends Mapper<Shshb> {
	
	@Select(value = "SELECT * FROM SH_SHB WHERE BH=#{param1}")
	Shshb selectByBh(String bh);
}
