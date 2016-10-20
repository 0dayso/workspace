package cn.vetech.vedsb.jp.dao.cgptsz;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
@JpMyBatisRepository
public interface JpPtLogDao extends Mapper<JpPtLog>{
	@Select(value="select * from jp_pt_log where log_date>=#{logDate} and ywlx=#{ywlx} and wdid=#{wdid} and shbh=#{shbh} and rownum=1")
	List<JpPtLog> getLastDayLog(JpPtLog tddlog);
}