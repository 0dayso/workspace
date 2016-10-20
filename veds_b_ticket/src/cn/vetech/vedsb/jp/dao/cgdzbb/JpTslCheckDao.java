package cn.vetech.vedsb.jp.dao.cgdzbb;
import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpTslCheck;
@JpMyBatisRepository
public interface JpTslCheckDao extends Mapper<JpTslCheck>{
	/**
	 * 查询数据库中是否存在对应的TSL数据
	 * @param date
	 * @param office
	 * @param agent
	 * @param printno
	 * @param shbh
	 * @return
	 */
	@Select(value="select * from jp_tsl_check where cp_date=#{param1} and officeid=#{param2} and agent =#{param3} and printno=#{param4} and shbh=#{param5} and  xmlstr is not null and  rownum =1")
	JpTslCheck selTslCheck(String date,String office,String agent,String printno,String shbh);
	@Select(value="delete from T_TSL_CHECK where CP_DATE=#{param1} and OFFICEID=#{param2} and AGENT=#{param3} and PRINTNO=#{param4} and SHBH=#{param5}")
	void deleteTslCheck(String cp_date,String officeid,String agent,String printno,String shbh );
}