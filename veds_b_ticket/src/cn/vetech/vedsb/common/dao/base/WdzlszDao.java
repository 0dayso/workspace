package cn.vetech.vedsb.common.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.Wdzlsz;

public interface WdzlszDao extends Mapper<Wdzlsz>{
	List<Wdzlsz> getWdZlszListByYwlx(Map<String,Object> param);

	List<Wdzlsz> queryWdList(Wdzlsz wd);

	int getTotalCount(Wdzlsz wd);

	/**
	 * <功能详细描述>
	 * 
	 * @param wds
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Map<String,String>> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	List<String> queryWdmcByIds(Map<String,Object> wds);
	
	 @Select(value="select * from wd_zlsz where shbh=#{param1} and zt=#{param2} and ywlx=#{param3}")
	 List<Wdzlsz> getWdZlszListByBean(String shbh ,String zt,String gngj);
}