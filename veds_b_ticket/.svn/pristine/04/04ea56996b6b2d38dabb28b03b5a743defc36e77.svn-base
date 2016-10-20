package cn.vetech.vedsb.common.service.base;

import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.VeEmailSzDao;
import cn.vetech.vedsb.common.entity.base.VeEmailSz;

@Service
public class VeEmailSzServiceImpl extends MBaseService<VeEmailSz,VeEmailSzDao> implements AttachInf{

	@Override
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		return null;
	}

	public VeEmailSz getVeEmailSzByShbh(String shbh){
		return this.getMyBatisDao().getVeEmailSzByShbh(shbh);
	}

}
