package cn.vetech.vedsb.common.service.base;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.RepJkListDao;
import cn.vetech.vedsb.common.entity.base.RepJkList;

@Service
public class RepJkListServiceImpl extends MBaseService<RepJkList, RepJkListDao>{
	/**
	 * <查询平台采购账号>
	 * 
	 * @param shbh
	 * @return [参数说明]
	 * @author heqiwen
	 * @return List<Shyhb> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<RepJkList> getRepJkList() {
		return this.getMyBatisDao().getRepJkList();
	}
}
