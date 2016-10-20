package cn.vetech.vedsb.jp.service.procedures;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.modules.utils.VeStr;

import cn.vetech.vedsb.jp.dao.procedures.ProcPkgPnrDao;
import cn.vetech.vedsb.jp.entity.cgptsz.JpPtLog;
import cn.vetech.vedsb.jp.entity.jpddsz.BookOrder;

@Service
public class ProcPkgPnrServiceImpl extends ParamMap {
	private static Logger logger = LoggerFactory.getLogger(ProcPkgPnrServiceImpl.class);
	@Autowired
	private ProcPkgPnrDao procPkgPnrDao;

	/**
	 * 订单入库
	 * 
	 * @param bean
	 */
	public void insertJpKhdd(BookOrder bean, JpPtLog ptlb) {
		long t = System.currentTimeMillis();
		Map<String, Object> map = bean.getParam();
		super.xmlToJson(map);
		bean.setOrderXml(VeStr.getValue(map, "p_xml"));
		long t1 = System.currentTimeMillis();
		procPkgPnrDao.insertJpKhdd(bean);
		String info = bean.getDdbh() + "订单入库,转换JSON耗时" + (t1 - t) + ",调用过程耗时" + (System.currentTimeMillis() - t1);
		logger.error(info);
		if (ptlb != null) {
			ptlb.add(info);
		}
	}
}
