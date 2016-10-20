package cn.vetech.vedsb.jp.dao.jphbyd;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;
import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jphbyd.BHkgsHbgz;
@JpMyBatisRepository
public interface BHkgsHbgzDao extends Mapper<BHkgsHbgz>{
	/**
	 * 根据商户编号查询所有的设置
	 * @param shbh
	 * @param hkgs
	 * @return
	 */
	@Select("SELECT * FROM B_Hkgs_Hbgz where shbh = #{param1}")
	List<BHkgsHbgz> getBeanByShbh(String shbh);

}
