package cn.vetech.vedsb.common.service.base;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.common.dao.base.VecsbDao;
import cn.vetech.vedsb.common.entity.base.Vecsb;

@Service
public class VecsbServiceImpl extends MBaseService<Vecsb,VecsbDao>{
	/**
	 * <根据参数编号和商户编号查找参数对象>
	 * 
	 * @param csb
	 * @return [参数说明]
	 * @author heqiwen
	 * @return Vecsb [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public Vecsb getVecsbByBh(Vecsb csb) {
		return this.getMyBatisDao().getVecsbByBh(csb);
	}

	/**
	 * <修改默认模板 >
	 * 
	 * @param csb [参数说明]
	 * @author heqiwen
	 * @return void [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public void updateVecsbMb(Vecsb csb) {
		this.getMyBatisDao().updateVecsbMb(csb);
	}

}
