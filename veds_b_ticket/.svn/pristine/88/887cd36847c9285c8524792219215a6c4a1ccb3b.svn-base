package cn.vetech.vedsb.jp.service.jptpsz;

import java.util.List;

import org.springframework.stereotype.Service;
import org.vetech.core.modules.service.MBaseService;

import cn.vetech.vedsb.jp.dao.jptpsz.JpTpszDao;
import cn.vetech.vedsb.jp.entity.jptpsz.JpTpsz;


@Service
public class JpTpszServiceImpl extends MBaseService<JpTpsz,JpTpszDao>{
	/**
	 * 批量删除
	 * @param list
	 * @throws Exception
	 */
	public void batchDel(List<JpTpsz> list) throws Exception {
		for (JpTpsz t : list) {
			JpTpsz deleteBean = this.getEntityById(t);
			this.deleteById(deleteBean);

		}
	}

}
