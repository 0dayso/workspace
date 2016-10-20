package cn.vetech.vedsb.jp.service.jpgqgl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpgqgl.JpGqdCzrzDao;
import cn.vetech.vedsb.jp.entity.jpgqgl.JpGqdCzrz;

@Service
public class JpGqdCzrzServiceImpl extends MBaseService<JpGqdCzrz, JpGqdCzrzDao>{
	
	public List<JpGqdCzrz> getCzRzByEntity(JpGqdCzrz jpGqdCzrz) {
		return this.getMyBatisDao().getCzRzByEntity(jpGqdCzrz);
	}
}
