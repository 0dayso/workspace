package cn.vetech.vedsb.jp.dao.jppz;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.business.pid.api.pidgl.JpPz;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
@JpMyBatisRepository
public interface JpPzDao extends Mapper<JpPz>{
	
	JpPz getJpPzByShbh(String shbh);
	
	@Select("select * from jp_pz where shbh = #{param1}")
	List<JpPz> selectJpPzByShbh(String shbh);
	
	@Select("select * from jp_pz where shbh = #{param1} and id = #{param2}")
	JpPz getJpPzById(String shbh, String id);
	
	void updateJppzById(JpPz jppz);
	
	@Select("select * from jp_pz where officeid = #{param1} and shbh = #{param2} and rownum = '1'")
	JpPz getJpPzByOfficeid(String officeid, String shbh);
}