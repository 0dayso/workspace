package org.vetech.core.business.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.vetech.core.business.job.DataWorkContext.DOJOB;

/**
 * 机票系统集群job执行
 * 
 * @author houya
 * 
 */
@Component
@DisallowConcurrentExecution
public class JpProceClusterableJob extends QuartzJobBean {
	private static Logger logger = LoggerFactory.getLogger(JpProceClusterableJob.class);

	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
		long t = System.currentTimeMillis();
		JobBean jobBean = null;
		try {
			jobBean = (JobBean) ctx.getJobDetail().getJobDataMap().get(JobBean.JobDataName);
			logger.error("集群job启动" + jobBean.toString());
			Class<?> clazz = Class.forName(jobBean.getClassname());
			ProceClusterableJob dataProceDataWork = (ProceClusterableJob) applicationContext.getBean(clazz);
			int rtn = dataProceDataWork.dataProce(jobBean);
			if (rtn == -1) {
				logger.error("集群job执行返回-1,删除" + jobBean.toString());
				DataWorkContext dataWorkContext = applicationContext.getBean(DataWorkContext.class);
				dataWorkContext.doWhat(ctx.getScheduler(), jobBean, null, DOJOB.DEL);
			}
		} catch (Exception e) {
			logger.error("集群job执行出错", e);
		}
		String info = jobBean != null ? jobBean.toString() : "";
		logger.error("集群job执行完成" + info + " ,耗时" + (System.currentTimeMillis() - t));
	}
}
