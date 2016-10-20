package org.vetech.core.business.job;

/**
 * job需要继承的类
 * 
 * @author zhanglei
 *
 */
public interface ProceClusterableJob {
	/**
	 * 返回 -1 表示这个JOB 对应的业务已经失效，可以在JOB集群中删掉这个job
	 * 
	 * 删掉后将不再触发
	 * 
	 * @param jobBean
	 * @return
	 */
	int dataProce(JobBean jobBean);
}
