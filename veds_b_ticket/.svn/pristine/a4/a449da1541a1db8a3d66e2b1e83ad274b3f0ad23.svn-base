package org.vetech.core.business.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * job底层操作
 * @author zhanglei
 *
 */
@Component
public class DataWorkContext {

	private static Logger logger = LoggerFactory.getLogger(DataWorkContext.class);

	public static enum DOJOB {
		ADDUPDATE, STATE, PAUSE, RESUME, EXEC, DEL, ADD
	}

	/**
	 * 调用job接口执行
	 * 
	 * @param scheduler
	 * @param job
	 * @param what
	 * @return
	 * @throws Exception
	 */
	public String doWhat(Scheduler scheduler, JobBean job,Class<? extends Job> jobClass, DOJOB dojob) throws Exception {
		logger.error("JOB操作" + job.getData() + "，执行的动作=" + dojob.name());
		if (dojob.equals(DOJOB.ADDUPDATE)) {// 如果存在先删除，然后重新添加
			addUpdate(scheduler, job,jobClass);
		} else if (dojob.equals(DOJOB.ADD)) {// 只添加，如果存在则不做任务操作
			add(scheduler, job,jobClass);
		} else if (dojob.equals(DOJOB.STATE)) {
			String state = jobState(scheduler, job);
			return state;
		} else if (dojob.equals(DOJOB.PAUSE)) {
			jobPause(scheduler, job);
		} else if (dojob.equals(DOJOB.RESUME)) {
			jobresume(scheduler, job);
		} else if (dojob.equals(DOJOB.EXEC)) {
			jobExec(scheduler, job);
		} else if (dojob.equals(DOJOB.DEL)) {
			jobDel(scheduler, job);
		}
		return "0";
	}

	/**
	 * 添加或更细job和trigger
	 * 
	 * @param scheduler
	 * @param job
	 * @param tb
	 * @throws Exception
	 */
	private void addUpdate(Scheduler scheduler, JobBean job,Class<? extends Job> jobClass) throws Exception {
		if ("1".equals(job.getType())) {
			addUpdateSimple(scheduler, job, jobClass,true);
		} else {
			addUpdateCron(scheduler, job, jobClass,true);
		}
	}

	/**
	 * 添加或更细job和trigger
	 * 
	 * @param scheduler
	 * @param job
	 * @param tb
	 * @throws Exception
	 */
	private void add(Scheduler scheduler, JobBean job,Class<? extends Job> jobClass) throws Exception {
		if ("1".equals(job.getType())) {
			addUpdateSimple(scheduler, job,jobClass, false);
		} else {
			addUpdateCron(scheduler, job,jobClass, false);
		}
	}

	/**
	 * 如果update为true 表示 如果存在则先删除然后，添加
	 * 
	 * @param scheduler
	 * @param job
	 * @param update
	 * @throws Exception
	 */
	private void addUpdateSimple(Scheduler scheduler, JobBean job,Class<? extends Job> jobClass, boolean update) throws Exception {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		if (jobDetail != null) {
			if (update) {
				scheduler.deleteJob(jobKey);
			} else {
				return;
			}
		}
		// 得到具体的和指定作业相关的 JobDetail 对象
		jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getName(), job.getGroup()).build();// 要调用建造器的 build()方法，才能实例当前作业的具体作业对象

		// 得到作业的参数对象
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		jobDataMap.put(JobBean.JobDataName, job);

		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());

		Date startdate = job.getStartdate();
		if (startdate == null) {
			startdate = new Date();
		}

		int second = 0;
		if (job.getMintime() > 0 && job.getSectime() > 0) {
			throw new Exception("设置的间隔时间 分钟和秒钟 不能同时有值");
		}
		if (job.getMintime() > 0) {
			second = job.getMintime() * 60;
		} else {
			second = job.getSectime();
		}

		SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(// 实例化触发器对象（定义执行的时间和执行的周期）
				SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(second)).startAt(startdate).build();

		// 添加调度作业（将具体的作业和触发器添加到作业中）
		scheduler.scheduleJob(jobDetail, simpleTrigger);
	}

	/**
	 * update 为true 表示 如果存在则先删除 再添加
	 * 
	 * @param scheduler
	 * @param job
	 * @param update
	 * @throws Exception
	 */
	private void addUpdateCron(Scheduler scheduler, JobBean job, Class<? extends Job> jobClass,boolean update) throws Exception {
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		// 修改时间无效，先删除再创建
		if (trigger != null) {
			if (update) {
				trigger = null;
				jobDel(scheduler, job);
			} else {
				return;
			}
		}

		// 不存在，创建一个
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(job.getName(), job.getGroup()).withDescription(job.getDesc()).build();
		jobDetail.getJobDataMap().put(JobBean.JobDataName, job);
		// 表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getExpression());
		// 按新的cronExpression表达式构建一个新的trigger
		trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
	}

	/**
	 * 获得job的状态
	 * 
	 * @param scheduler
	 * @param job
	 * @return
	 * @throws Exception
	 */
	private String jobState(Scheduler scheduler, JobBean job) throws Exception {
		VeTrigger t = new VeTrigger();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getName(), job.getGroup());
		Trigger trigger = scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			return null;
		} else {
			Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
			t.setTriggerState(triggerState.name());
			t.setNextFireTime(trigger.getNextFireTime());
			return t.toString();
		}
	}

	/**
	 * 暂停job
	 * 
	 * @param scheduler
	 * @param job
	 * @throws Exception
	 */
	private void jobPause(Scheduler scheduler, JobBean job) throws Exception {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复执行job
	 * 
	 * @param scheduler
	 * @param job
	 * @throws Exception
	 */
	private void jobresume(Scheduler scheduler, JobBean job) throws Exception {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 立即执行job
	 * 
	 * @param scheduler
	 * @param job
	 * @throws Exception
	 */
	private void jobExec(Scheduler scheduler, JobBean job) throws Exception {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 删除job
	 * 
	 * @param scheduler
	 * @param job
	 * @throws Exception
	 */
	private void jobDel(Scheduler scheduler, JobBean job) throws Exception {
		JobKey jobKey = JobKey.jobKey(job.getName(), job.getGroup());
		scheduler.deleteJob(jobKey);
	}

	/**
	 * 查找有多少Job 在运行
	 * 
	 * @param scheduler
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public List<JobBean> getAllJobBean(Scheduler scheduler, JobBean job) throws Exception {
		GroupMatcher<JobKey> groupMatcher = GroupMatcher.groupEquals(job.getGroup());
		Set<JobKey> jobKeys = scheduler.getJobKeys(groupMatcher);
		if (jobKeys == null || jobKeys.size() < 1) {
			return null;
		}
		List<JobBean> list = new ArrayList<JobBean>();
		for (JobKey jobKey : jobKeys) {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			JobBean jobBean = (JobBean) jobDetail.getJobDataMap().get(JobBean.JobDataName);
			list.add(jobBean);
		}
		return list;
	}
}
