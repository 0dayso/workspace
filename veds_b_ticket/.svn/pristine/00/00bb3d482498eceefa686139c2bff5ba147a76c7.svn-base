package cn.vetech.vedsb.jp.dao.cgptsz;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtbjHfsz;
@JpMyBatisRepository
public interface JpPtbjHfszDao extends Mapper<JpPtbjHfsz>{
	@Select(value="select * from jp_ptbj_hfsz where shbh=#{param1} order by id asc")
	List<JpPtbjHfsz> getHfszListByShbh(String shbh);
	
	void updateHfszById(JpPtbjHfsz hfsz);
}