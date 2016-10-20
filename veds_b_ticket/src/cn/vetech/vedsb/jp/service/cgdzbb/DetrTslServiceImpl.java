package cn.vetech.vedsb.jp.service.cgdzbb;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgdzbb.DetrTslDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.DetrTsl;
@Service
public class DetrTslServiceImpl extends MBaseService<DetrTsl,DetrTslDao>{
	public List<Map<String, Object>> searchDetrTsl(String datestr,String officeid,String agent,String printno,String shbh){
		return this.getMyBatisDao().searchDetrTsl(datestr, officeid, agent, printno, shbh);
	}
}