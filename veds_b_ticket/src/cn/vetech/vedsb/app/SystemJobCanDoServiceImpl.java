package cn.vetech.vedsb.app;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import cn.vetech.vedsb.utils.BTomcat;
import cn.vetech.vedsb.utils.Constants;

/**
 * 系统启动时需要定义的加载项目
 * 
 * @author zhanglei
 *
 */
@Component
public class SystemJobCanDoServiceImpl {
	private static Logger logger = LoggerFactory.getLogger(SystemJobCanDoServiceImpl.class);
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 系统启动时加载
	 */
	public void systemLoad() {
		logger.error("加载系统自定义启动项开始");
		// 启动脚本
		try {
			if (BTomcat.thisIsJob(Constants.BTOMCAT)) {
				Scheduler schedulerplat = (Scheduler) applicationContext.getBean("clusterQuartzScheduler");
				if (schedulerplat != null) {
					schedulerplat.startDelayed(20);
					logger.error("导单等Cluster20秒后启动");
				}
			}
		} catch (Exception e) {
			logger.error("启动脚本错误", e);
		}
	}

}
