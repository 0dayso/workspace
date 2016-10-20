package org.vetech.core.business.job;

import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.TriggerKey;
import org.vetech.core.modules.utils.VeDate;

public class VeTrigger {
	private TriggerKey key;

	private JobKey jobkey;

	private String description;

	private String calendarName;

	private JobDataMap jobDataMap;

	private int priority;

	private boolean mayFireAgain;

	private Date startTime;

	private Date endTime;

	private Date nextFireTime;

	private Date previousFireTime;

	private Date finalFireTime;

	private int misfireInstruction;

	// None：Trigger已经完成，且不会在执行，或者找不到该触发器，或者Trigger已经被删除
	// NORMAL:正常状态
	// PAUSED：暂停状态
	// COMPLETE：触发器完成，但是任务可能还正在执行中
	// BLOCKED：线程阻塞状态
	// ERROR：出现错误
	private String triggerState;

	@Override
	public String toString() {
		return "{'triggerState':'" + getTriggerState() + "','nextFireTime':'" + VeDate.dateToStrLong(getNextFireTime()) + "'}";
	}

	public String getTriggerState() {
		return triggerState;
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public TriggerKey getKey() {
		return key;
	}

	public void setKey(TriggerKey key) {
		this.key = key;
	}

	public JobKey getJobkey() {
		return jobkey;
	}

	public void setJobkey(JobKey jobkey) {
		this.jobkey = jobkey;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCalendarName() {
		return calendarName;
	}

	public void setCalendarName(String calendarName) {
		this.calendarName = calendarName;
	}

	public JobDataMap getJobDataMap() {
		return jobDataMap;
	}

	public void setJobDataMap(JobDataMap jobDataMap) {
		this.jobDataMap = jobDataMap;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isMayFireAgain() {
		return mayFireAgain;
	}

	public void setMayFireAgain(boolean mayFireAgain) {
		this.mayFireAgain = mayFireAgain;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public Date getFinalFireTime() {
		return finalFireTime;
	}

	public void setFinalFireTime(Date finalFireTime) {
		this.finalFireTime = finalFireTime;
	}

	public int getMisfireInstruction() {
		return misfireInstruction;
	}

	public void setMisfireInstruction(int misfireInstruction) {
		this.misfireInstruction = misfireInstruction;
	}
}
