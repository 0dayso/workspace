package cn.vetech.vedsb.jp.dao.cgdzbb;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgdzbb.DetrTsl;
@JpMyBatisRepository
public interface DetrTslDao extends Mapper<DetrTsl>{
	@Select(value="select * from detr_tsl where datestr=#{param1} and OFFICEID=#{param2} and AGENT=#{param3} and PRINTNO=#{param4} and shbh=#{param5}")
	List<Map<String, Object>> searchDetrTsl(String datestr,String officeid,String agent,String printno,String shbh);
}