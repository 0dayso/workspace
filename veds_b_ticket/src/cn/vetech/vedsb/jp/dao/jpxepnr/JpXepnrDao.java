package cn.vetech.vedsb.jp.dao.jpxepnr;
import java.util.List;
import java.util.Map;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpxepnr.JpXepnr;
@JpMyBatisRepository
public interface JpXepnrDao extends Mapper<JpXepnr>{
	
	List<Map<String,Object>> selectAllJpXepnr(Map<String,Object> param);
	
	int selectAllJpXepnrCount(Map<String,Object> param);

}