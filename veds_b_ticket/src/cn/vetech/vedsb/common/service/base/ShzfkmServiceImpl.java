package cn.vetech.vedsb.common.service.base;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.dao.base.ShzfkmDao;
import cn.vetech.vedsb.common.entity.Shyhb;
import cn.vetech.vedsb.common.entity.base.Shzfkm;
import cn.vetech.web.vedsb.SessionUtils;
@Service
public class ShzfkmServiceImpl extends MBaseService<Shzfkm, ShzfkmDao> implements AttachInf{
	/**
	 * 通过商户编号获取支付科目
	 * @param shbh
	 * @return
	 */
	public List<Shzfkm> getShzfkmList(Shzfkm shzfkm){
		return this.getMyBatisDao().getShzfkmList(shzfkm);
	}

	@Override
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		Shyhb yhb = SessionUtils.getShshbSession();
		String kmbh = (String) VeStr.getArray(attrObject, 0);
		if(StringUtils.isBlank(kmbh)){
			return new Shzfkm();
		}
		Shzfkm shzfkm1 = this.getMyBatisDao().getshzfkm(kmbh, yhb.getShbh());
		if(null!=shzfkm1){
			return shzfkm1;
		}
		return new Shzfkm();
	}
}
