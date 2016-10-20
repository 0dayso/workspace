package cn.vetech.vedsb.jp.service.jpddsz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jpddsz.JpQzDao;
import cn.vetech.vedsb.jp.entity.jpddsz.JpQz;

@Service
public class JpQzServiceImpl extends MBaseService<JpQz, JpQzDao> {

	/**
	 * <行程单中在待打印里,点击签注时.根据订单号(业务单号)查询签注>
	 * 
	 * @param ddbh
	 * @return [参数说明]
	 * 
	 * @return List<JpQz> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	public List<JpQz> queryListByYwdh(String ddbh) {
		return this.getMyBatisDao().queryListByYwdh(ddbh);
	}

}
