package cn.vetech.vedsb.jp.service.cgdzbb;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cgdzbb.JpCgdzCybDao;
import cn.vetech.vedsb.jp.entity.cgdzbb.JpCgdzCyb;
@Service
public class JpCgdzCybServiceImpl extends MBaseService<JpCgdzCyb,JpCgdzCybDao>{
	public JpCgdzCyb searchcgdzObject(String ddbh,String shbh){
		return this.getMyBatisDao().searchcgdzObject(ddbh, shbh);
	}
}