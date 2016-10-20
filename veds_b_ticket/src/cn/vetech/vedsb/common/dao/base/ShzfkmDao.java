package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.Shzfkm;

/**
 * 获取支付科目
 * @author vetech
 *
 */
public interface ShzfkmDao extends Mapper<Shzfkm>{
	
	List<Shzfkm> getShzfkmList(Shzfkm zfkm);
	
	@Select(value="select * from sh_zfkm where kmbh=#{param1} and shbh=#{param2}")
	Shzfkm getshzfkm(String zfbh,String shbh);

	/**
	 * <出票报表中 通过商户编号和科目编号获取科目>
	 * 
	 * @param zfkm
	 * @return [参数说明]
	 * @author heqiwen
	 * @return Shzfkm [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	Shzfkm getShzfkmByBh(Shzfkm zfkm);
	
}
