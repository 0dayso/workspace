package cn.vetech.vedsb.common.service.base;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.ShshbDao;
import cn.vetech.vedsb.common.entity.Shshb;
@Service
public class ShshbServiceImpl extends MBaseService<Shshb, ShshbDao>{
	
	/**
	 * 根据商户编号查找
	 * @param shbh
	 * @return
	 */
	public Shshb getShbhByBh(String shbh){
		return this.getMyBatisDao().selectByBh(shbh);
	}
	
}
