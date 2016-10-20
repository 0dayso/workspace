package cn.vetech.vedsb.jp.service.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.ProceClusterableJob;

import cn.vetech.vedsb.jp.service.jpzwgl.JpAutoZwWork;
import cn.vetech.vedsb.jp.service.jpzwgl.JpTjsqZwszServiceImpl;
/**
 * 追位单
 * @author zhanglei
 *
 */
@Service
public class JpzwProceJobService implements ProceClusterableJob {
	private static Logger logger = LoggerFactory.getLogger(JpzwProceJobService.class);
	
	@Autowired
	JpTjsqZwszServiceImpl jpTjsqZwszServiceImpl;
	
	@Autowired
	JpAutoZwWork jpAutoZwWork;
	
	@Override
	public int dataProce(JobBean jobBean) {
		String shbh = jobBean.getData();
		
		int count=jpTjsqZwszServiceImpl.getQygzCount(shbh);
		if(count==0){//开启了追位,但没有一条启用的追位规则，不处理任务
			return 0;
		}
		long t0=System.currentTimeMillis();
		jpAutoZwWork.startWork(shbh);
		long t1=System.currentTimeMillis();
		logger.error("自动追位job总耗时："+(t1-t0));
		logger.error(jobBean.getGroup());
		return 0;
	}

}
