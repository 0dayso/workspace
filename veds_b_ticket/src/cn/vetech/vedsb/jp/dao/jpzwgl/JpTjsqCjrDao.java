package cn.vetech.vedsb.jp.dao.jpzwgl;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpzwgl.JpTjsqCjr;
@JpMyBatisRepository
public interface JpTjsqCjrDao extends Mapper<JpTjsqCjr>{
	
	/**
	 * 根据商户编号和申请单号查询乘机人
	 * @param shbh
	 * @param sqdh
	 * @return
	 */
	@Select(value="select * from jp_tjsq_cjr where shbh=#{param1} and sqdh=#{param2}")
	JpTjsqCjr gettjsqcjr(String shbh,String sqdh);
}
