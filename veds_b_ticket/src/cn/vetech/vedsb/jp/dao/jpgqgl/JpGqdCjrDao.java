package cn.vetech.vedsb.jp.dao.jpgqgl;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCjr;

@JpMyBatisRepository
public interface JpGqdCjrDao extends Mapper<JpGqdCjr>{
	
	public int updateByIdSelective(JpGqdCjr jpGqdCjr);
	
}
