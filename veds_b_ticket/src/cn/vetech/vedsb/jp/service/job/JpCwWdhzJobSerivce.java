package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.service.procedures.PkgWdCwServiceImpl;

/**
 * 每天凌晨一点调用后台过程汇总网店数据
 * 
 * @author vetech
 *
 */
@Service
public class JpCwWdhzJobSerivce {
	private static Logger logger = LoggerFactory.getLogger(TaobaoGyOrderDetailSmJobService.class);
	@Autowired
	private PkgWdCwServiceImpl pkgWdCwServiceImpl;

	public void doJob() {
		long t = System.currentTimeMillis();
		logger.error("汇总网店数据");
		try {
			pkgWdCwServiceImpl.wdDataHz();
		} catch (Exception e) {
			logger.error("汇总网店数据异常", e);
		}
		logger.error("汇总网店数据完成,耗时" + (System.currentTimeMillis() - t));
	}
}
