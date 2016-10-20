package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.Shfwkqsz;


/**
 * 商户服务开启设置表dao
 * @author lishanlaing
 *
 */
public interface ShfwkqszDao extends Mapper<Shfwkqsz>{
	
	/**
	 * 根据商户编号查询出本商户所有的服务设置
	 * @param shbh
	 * @return=#{param1}
	 */
	@Select(value="select * from sh_fwkqsz where shbh=#{param1} and lx=#{param2}")
	public List<Shfwkqsz> getShfwkqszByShbhAndLx(String shbh,String lx);

	/**
	 * 根据商户编号，类型，服务类型编号查询 商户的具体服务开启设置
	 * @param shbh
	 * @param lx
	 * @param fw_lxid
	 * @return
	 */
	@Select(value="select * from sh_fwkqsz where shbh=#{param1} and lx=#{param2} and fw_lxid=#{param3}")
	public Shfwkqsz getShfwkqszByShbhLxFwlxid(String shbh, String lx, String fw_lxid);
}
