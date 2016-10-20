package cn.vetech.vedsb.jp.service.cpsz;


import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.cpsz.JpCpymSzDao;
import cn.vetech.vedsb.jp.entity.cpsz.JpCpymSz;

/**
 * 出票页面设置
 * @author wangshengliang
 *
 */
@Service
public class JpCpymSzServiceImpl extends MBaseService<JpCpymSz, JpCpymSzDao>{
	
	public  JpCpymSz selectByShbh(String shbh,String cgly){
		return this.getMyBatisDao().selectByShbh(shbh,cgly);
	}
}
