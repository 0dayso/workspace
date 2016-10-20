package cn.vetech.vedsb.jp.service.job;

import java.util.Date;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.vetech.core.business.job.DataWorkContext;
import org.vetech.core.business.job.DataWorkContext.DOJOB;
import org.vetech.core.business.job.JobBean;
import org.vetech.core.business.job.JpProceClusterableJob;
import org.vetech.core.modules.service.MBaseService;
import org.vetech.core.modules.service.ServiceException;

import cn.vetech.vedsb.jp.dao.job.QrtzJobDao;
import cn.vetech.vedsb.jp.entity.job.QrtzJob;

/**
 * 机票系统job系统服务
 * 
 * @author zhanglei
 *
 */
@Service
public class QrtzServiceImpl extends MBaseService<QrtzJob, QrtzJobDao> {
	@Autowired
	private DataWorkContext dataWorkContext;
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * 添加一个Job，
	 * 
	 * @param bh
	 *            在Qrtz_JOB表中登记的编号
	 * @param data
	 *            需要传给执行Job的数据，一般是业务表的Id,商户编号
	 * @throws Exception
	 */
	public void add(String bh, String data) throws Exception {
		JobBean j1 = getQrtzJob(bh, data);
		Object o = applicationContext.getBean("clusterQuartzScheduler");
		Scheduler scheduler = (Scheduler) o;
		dataWorkContext.doWhat(scheduler, j1, JpProceClusterableJob.class, DOJOB.ADDUPDATE);
	}

	/**
	 * 删除一个job
	 * 
	 * @param bh
	 * @param data
	 * @throws Exception
	 */
	public void del(String bh, String data) throws Exception {
		JobBean j1 = getQrtzJob(bh, data);
		Object o = applicationContext.getBean("clusterQuartzScheduler");
		Scheduler scheduler = (Scheduler) o;
		dataWorkContext.doWhat(scheduler, j1, JpProceClusterableJob.class, DOJOB.DEL);
	}

	private JobBean getQrtzJob(String bh, String data) {
		QrtzJob t = new QrtzJob();
		t.setBh(bh);
		QrtzJob qrtzjob = getEntityById(t);
		if (qrtzjob == null) {
			throw new ServiceException("编号为" + bh + "没有找到对应的job对象");
		}
		JobBean j1 = new JobBean();
		j1.setGroup(qrtzjob.getJobgroup());
		j1.setName(bh + "_" + qrtzjob.getJobname() + "_" + data);// 名字中添加了主键
		j1.setDesc(qrtzjob.getJobdesc());
		j1.setType(qrtzjob.getType());
		j1.setData(data);
		j1.setClassname(qrtzjob.getClassname());
		j1.setSectime(qrtzjob.getRepeatinterval());
		j1.setExpression(qrtzjob.getExpression());
		j1.setStartdate(new Date());// JOB 的创建时间
		return j1;
	}
}
