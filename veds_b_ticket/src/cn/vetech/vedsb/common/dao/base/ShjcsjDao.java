package cn.vetech.vedsb.common.dao.base;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.common.entity.base.Shjcsj;

/**
 * 商户基础数据
 * 
 * @author zhanglei
 *
 */
public interface ShjcsjDao extends Mapper<Shjcsj> {
	@Select(value = "SELECT  shbh, bh, mc,  lb, sjbh, sfmr, by1, by2, by3  FROM sh_jcsj WHERE shbh=#{param1}  AND bh=#{param2}")
	Shjcsj getShjcsj(String shbh, String bh);

	@Select(value = "SELECT  shbh, bh, mc,  lb, sjbh, sfmr, by1, by2, by3  FROM sh_jcsj WHERE shbh=#{param1}  AND sjbh=#{param2}")
	List<Shjcsj> getShjcsjList(String shbh, String sjbh);
}
