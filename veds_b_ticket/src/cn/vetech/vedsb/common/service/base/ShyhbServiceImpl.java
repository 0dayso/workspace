package cn.vetech.vedsb.common.service.base;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.vetech.core.business.cache.AttachInf;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.common.dao.base.ShyhbDao;
import cn.vetech.vedsb.common.entity.Shyhb;

@Service
public class ShyhbServiceImpl extends MBaseService<Shyhb, ShyhbDao> implements AttachInf {

	/**
	 * 通过商户编号和用户编号获取用户对象
	 */
	@Override
	@Cacheable("ServiceCache")
	public Object getBean(Object[] fixedvalue, Object[] attrObject) {
		Object shbh = "";
		Object yhbh = "";
		shbh = VeStr.getArray(fixedvalue, 0);
		if (shbh != null) {
			yhbh = VeStr.getArray(attrObject, 0);
		} else {
			yhbh = VeStr.getArray(attrObject, 0);
			shbh = VeStr.getArray(attrObject, 1);
		}
		if (shbh == null || yhbh == null) {
			return new Shyhb();
		}
		Shyhb shyhb = new Shyhb();
		shyhb.setShbh(shbh.toString());
		shyhb.setBh(yhbh.toString());
		List<Shyhb> list = this.getMyBatisDao().select(shyhb);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return new Shyhb();
	}

	/**
	 * <根据商户编号查找用户>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Shyhb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<Shyhb> queryShyhbListBySh(String shbh){
		
		return this.getMyBatisDao().queryShyhbListBySh(shbh);
	}
}
