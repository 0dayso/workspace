package cn.vetech.vedsb.jp.dao.jpddsz;

import java.util.HashMap;
import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpddsz.JpKhddJplb;

@JpMyBatisRepository
public interface JpKhddJplbDao extends Mapper<JpKhddJplb>{
	List<JpKhddJplb> selectByJp(HashMap<String,String> param);
	
	int updateJplbByBean(JpKhddJplb jplb);
}
