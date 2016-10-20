package cn.vetech.vedsb.jp.service.job;

import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vetech.vedsb.jp.service.procedures.ProcedureServiceImpl;

/**
 * 获取航班异动信息
 * 
 * @author wangshengliang
 */
@Service
public class GetQinfoFsdxJobService {
	private static Logger logger = LoggerFactory.getLogger(GetQinfoFsdxJobService.class);
	@Autowired
	private ProcedureServiceImpl procedureServiceImpl;

	public void doJob() throws JobExecutionException {
		long t = System.currentTimeMillis();
		try {
			procedureServiceImpl.proc_get_qinfo_fsdx();
		} catch (Exception e) {
			logger.error("获取Q信息异常", e);
		}
		logger.error("获取Q信息结束,耗时" + (System.currentTimeMillis() - t));
	}
}