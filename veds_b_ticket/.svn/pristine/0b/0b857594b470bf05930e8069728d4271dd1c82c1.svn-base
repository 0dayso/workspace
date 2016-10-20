package cn.vetech.vedsb.jp.service.jpddgl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddgl.JpKhddCzrzDao;
import cn.vetech.vedsb.jp.entity.jpddgl.JpKhddCzrz;

@Service
public class JpKhddCzrzServiceImpl extends MBaseService<JpKhddCzrz, JpKhddCzrzDao>{
	/**
	 * 根据实体Bean获取操作日志列表
	 * @param jpKhddCzrz
	 * @return
	 */
	public List<Map<String,Object>> getCzRzByEntity(Map<String,Object> param){
		return this.getMyBatisDao().getCzRzByEntity(param);
	}
}
