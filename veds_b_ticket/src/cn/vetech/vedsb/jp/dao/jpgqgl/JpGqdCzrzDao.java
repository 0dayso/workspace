package cn.vetech.vedsb.jp.dao.jpgqgl;

import java.util.List;

import org.vetech.core.modules.mybatis.mapper.Mapper;

import cn.vetech.vedsb.jp.dao.JpMyBatisRepository;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCzrz;

@JpMyBatisRepository
public interface JpGqdCzrzDao extends Mapper<JpGqdCzrz> {
	List<JpGqdCzrz> getCzRzByEntity(JpGqdCzrz jpGqdCzrz);
}
