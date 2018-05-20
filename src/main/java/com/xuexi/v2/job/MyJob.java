package com.xuexi.v2.job;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuexi.v2.job.base.BaseJob;
import com.xuexi.v2.service.IJobService;

@Component
public class MyJob extends BaseJob {

	@Autowired
	private IJobService iJobService;

	@Override
	protected String getJobKey() {
		return "MyJob";
	}

	@Override
	protected boolean run(JobExecutionContext context) throws Exception {
		iJobService.printMsg();
		return true;
	}

}
