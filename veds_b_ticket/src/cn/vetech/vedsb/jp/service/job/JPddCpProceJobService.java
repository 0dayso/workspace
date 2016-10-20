package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_zdcp;
/**
 * 网店自动出票
 * @author zhanglei
 *
 */
@Service
public class JPddCpProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JPddCpProceJobService.class);
	@Autowired
	private  JpddWork_zdcp jpddWork_zdcp;
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		//传入wdid 扫描本网店的 待出票订单 并出票
		int rtn = jpddWork_zdcp.queryorder(wdid);
		logger.error(jobBean.getGroup());
		return rtn;
	}

}
