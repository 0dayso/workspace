package cn.vetech.vedsb.jp.dao.cgptsz;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtzcFzsz;
@JpMyBatisRepository
public interface JpPtzcFzszDao extends Mapper<JpPtzcFzsz>{
	@Select(value="select * from jp_ptzc_fzsz where ywlx=#{param1} and shbh=#{param2}")
	List<JpPtzcFzsz> getFzszListByYwlx(String ywlx, String shbh);
	
	@Select(value="delete jp_ptzc_fzsz where id=#{param1} and shbh=#{param2}")
	void delFzszById(String id, String shbh);
	
	void updateFzszById(JpPtzcFzsz fzsz);
	
	@Select(value="select * from jp_ptzc_fzsz where ywlx=#{param1} and shbh=#{param2} and FZ_FZF=#{param3} and PTZCBS=#{param4}")
	JpPtzcFzsz genZfkmByDjm(String ywlx,String shhb,String djm,String platcode);
}