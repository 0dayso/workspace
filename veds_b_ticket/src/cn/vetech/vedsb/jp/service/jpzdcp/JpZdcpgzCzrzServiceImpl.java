package cn.vetech.vedsb.jp.service.jpzdcp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpzdcp.JpzdcpgzCzrzDao;
import cn.vetech.vedsb.jp.entity.jpzdcp.JpZdcpgzCzrz;
@Service
public class JpZdcpgzCzrzServiceImpl extends MBaseService<JpZdcpgzCzrz, JpzdcpgzCzrzDao>{
	
	public List<Map<String, Object>> getCzRzByEntity(Map<String, Object> map){
		return this.getMyBatisDao().getCzRzByEntity(map);
	}
	
}
