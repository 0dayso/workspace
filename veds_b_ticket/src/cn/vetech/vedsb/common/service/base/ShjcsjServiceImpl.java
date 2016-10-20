package cn.vetech.vedsb.common.service.base;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.dao.base.ShjcsjDao;
import cn.vetech.vedsb.common.entity.base.Shjcsj;

/**
 * 商户基础数据
 * 
 * @author zhanglei
 *
 */
@Service
public class ShjcsjServiceImpl extends MBaseService<Shjcsj, ShjcsjDao> implements AttachInf {
	/**
	 * 通过商户编号和上级编号获取数据
	 * 
	 * @param shbh
	 * @param sjbh
	 * @return
	 */
	public List<Shjcsj> getShjcsjList(String shbh, String sjbh) {
		return this.getMyBatisDao().getShjcsjList(shbh, sjbh);
	}

	/**
	 * 通过商户编号和用户编号获取用户对象
	 */
	@Override
	@Cacheable("ServiceCache")
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		Object shbh = "";
		Object bh = "";
		shbh = VeStr.getArray(fixedvalue, 0);
		if (shbh != null) {
			bh = VeStr.getArray(attrObject, 0);
		} else {
			bh = VeStr.getArray(attrObject, 0);
			shbh = VeStr.getArray(attrObject, 1);
		}
		if (shbh == null || bh == null) {
			return new Shjcsj();
		}
		Shjcsj s = this.getMyBatisDao().getShjcsj(shbh.toString(), bh.toString());
		if (s == null) {
			return new Shjcsj();
		} else {
			return s;
		}
	}

}
