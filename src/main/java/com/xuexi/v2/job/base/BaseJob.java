package com.xuexi.v2.job.base;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuexi.v2.domain.JobLog;
import com.xuexi.v2.service.IJobService;

@Component
public abstract class BaseJob implements Job {

	private static final Logger logger = LoggerFactory.getLogger(BaseJob.class);

	@Autowired
	private IJobService iJobService;
 
	@Override
	public void execute(JobExecutionContext context) {

		boolean status = true;
		try {
			status = run(context);
		} catch (Exception e) {
			logger.info("任务执行出错！");
			status = false;
		} finally {
			writeLog(status);
		}
	}

	protected void writeLog(boolean status) {
		try {
			JobLog log = new JobLog();
			log.setJobName(getJobKey());
			log.setExcuteTime(new Date());
			log.setDescription("执行任务：" + getJobKey() + " " + (status ? "成功" : "失败"));
			iJobService.addLog(log);
		} catch (Exception e) {
			logger.info("任务执行日志！" + e);
		}
	}

	protected abstract String getJobKey();

	protected abstract boolean run(JobExecutionContext context) throws Exception;
}
