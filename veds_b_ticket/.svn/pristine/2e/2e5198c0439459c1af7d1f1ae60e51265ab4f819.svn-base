package cn.vetech.vedsb.jp.dao.cgptsz;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgptsz.JpCgdd;
@JpMyBatisRepository
public interface JpCgddDao extends Mapper<JpCgdd>{
	@Select(value="select * from JP_CGDD where ddbh=#{param1} and cgly=#{param2} and dgdh=#{param3} and zt <>'-1'")
	JpCgdd getJpCgddByCgly(String ddbh, String cgly, String dgdh);
	
	@Select(value="select * from (select * from JP_CGDD where ddbh=#{param1} and JYZT=#{param2} and shbh=#{param3} order by CJDATETIME desc) where rownum=1")
	JpCgdd genDdByZfzt(String ddbh,String zfzt,String shbh);
	
	@Select(value="select * from JP_CGDD where hkgs_ddbh =#{ptddbh}")
	JpCgdd getDdByZflsh(String ptddbh);
	
	@Select(value="select *　from JP_CGDD where ddbh=#{param1} and cgly='OP' and shbh=#{param2} and jyzt = '5'")
	JpCgdd getJpCgddByDdbh(String ddbh,String shbh);
	
	@Select(value="select * from JP_CGDD where id=#{param2}")
	JpCgdd getJpCgddById(String id);
}