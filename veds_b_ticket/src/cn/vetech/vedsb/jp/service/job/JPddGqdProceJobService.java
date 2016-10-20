package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpddsz.JpddWork_gqd;
/**
 * 网店改签单导单
 * @author zhanglei
 *
 */
@Service
public class JPddGqdProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JPddGqdProceJobService.class);
	
	@Autowired
	private JpddWork_gqd jpddWork_gqd;
	@Override
	public int dataProce(JobBean jobBean) {
		String wdid = jobBean.getData();
		int rtn = jpddWork_gqd.queryorder_gq(wdid);
		logger.error(jobBean.getGroup());
		return rtn;
	}

}
