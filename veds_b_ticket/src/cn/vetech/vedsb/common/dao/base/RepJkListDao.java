package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.RepJkList;

public interface RepJkListDao extends Mapper<RepJkList> {
	/**
	 * <查询平台采购账号>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Shyhb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	@Select(value = "SELECT zfdjm,by1,by2,by3 FROM rep_jk_list where cpbh='312012401' and jklx='1'")
	List<RepJkList> getRepJkList();
}
