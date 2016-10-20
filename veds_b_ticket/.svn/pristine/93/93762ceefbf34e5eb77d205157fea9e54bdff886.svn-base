package cn.vetech.vedsb.jp.dao.cgptsz;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcZh;
@JpMyBatisRepository
public interface JpPtzcZhDao extends Mapper<JpPtzcZh>{

	@Select(value="select * from jp_ptzc_zh where ptzcbs=#{param1} and shbh=#{param2}")
	public JpPtzcZh getPtzhByPtBs(String ptzcbs, String shbh);
	
	
	@Select(value="select * from jp_ptzc_zh where ptzcbs=#{param1}")
	public List<JpPtzcZh> getPtzhByPtBsList(String ptzcbs);
	
	@Select(value="select * from jp_ptzc_zh where shbh=#{param1} and ptzcbs in ${param2} and open1='1' ")
	public List<JpPtzcZh> genPtzhByPtbss(String shbh,String platcodes);
	
	@Select(value="select * from jp_ptzc_zh where shbh=#{param1} and upper(user1)=#{param2} and ptzcbs=#{param3}")
	public JpPtzcZh getPtzhByPtzh(String shbh,String businessNo,String ptzcbs);
}