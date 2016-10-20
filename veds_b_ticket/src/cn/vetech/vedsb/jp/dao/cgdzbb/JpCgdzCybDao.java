package cn.vetech.vedsb.jp.dao.cgdzbb;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzCyb;
@JpMyBatisRepository
public interface JpCgdzCybDao extends Mapper<JpCgdzCyb>{
	@Select(value="select * from jp_cgdz_cyb where ddbh = #{param1} and shbh = #{param2}")
	JpCgdzCyb searchcgdzObject(String ddbh,String shbh);
}