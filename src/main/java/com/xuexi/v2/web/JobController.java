package com.xuexi.v2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.xuexi.v2.domain.Job;
import com.xuexi.v2.domain.dto.JobDto;
import com.xuexi.v2.job.MyJob;
import com.xuexi.v2.job.base.BaseJob;
import com.xuexi.v2.service.IJobService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "job")
public class JobController extends BaseController {

	// 加入Qulifier注解，通过名称注入bean
	@Autowired
	@Qualifier("Scheduler")
	private Scheduler scheduler;

	@Autowired
	private IJobService iJobService;

	private static Logger log = LoggerFactory.getLogger(JobController.class);

	@PostMapping(value = "/add")
	public @ResponseBody Map<String, Object> add(@RequestParam(value = "jobName") String jobName,
			@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			createNewJob(jobName, jobClassName, jobGroupName, cronExpression);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
		}
		return map;
	}

	public void createNewJob(String jobName, String jobClassName, String jobGroupName, String cronExpression)
			throws Exception {
		scheduler.start();
		// 构建job信息
		JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobName, jobGroupName)
				.build();

		// 表达式调度构建器(即任务执行的时间)
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

		// 按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
				.withSchedule(scheduleBuilder).build();

		scheduler.scheduleJob(jobDetail, trigger);
	}

	@PostMapping(value = "/update")
	public @ResponseBody Map<String, Object> update(@RequestParam(value = "jobName") String jobName,
			@RequestParam(value = "jobClassName") String jobClassName,
			@RequestParam(value = "jobGroupName") String jobGroupName,
			@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 方法一 如果只是修改触发时间可以使用此方法
		// return modifyTime(jobName, jobGroupName, cronExpression);
		try {
			// 方法二 删除掉重新创建
			deleteJob(jobName, jobGroupName);
			createNewJob(jobName, jobClassName, jobGroupName, cronExpression);
			map.put("success", true);
		} catch (Exception e) {
			map.put("success", false);
		}

		return map;

	}

	public Map<String, Object> modifyTime(String jobName, String jobGroupName, String cronExpression) {

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);

			map.put("success", true);
		} catch (SchedulerException e) {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = { "main" }, method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model, JobDto job) {
		PageInfo<Job> jobSplitPage = iJobService.findAll(job);
		model.addAttribute("jobSplitPage", jobSplitPage);
		model.addAttribute("job", job);
		return "/admin/job/main";
	}

	@RequestMapping(value = { "runonce" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> runOnce(HttpServletRequest request,
			@RequestParam(value = "name") String name, @RequestParam(value = "group") String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			scheduler.triggerJob(JobKey.jobKey(name, group));
			map.put("success", true);
		} catch (SchedulerException e) {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = { "resume" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> resume(HttpServletRequest request,
			@RequestParam(value = "name") String name, @RequestParam(value = "group") String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			scheduler.resumeJob(JobKey.jobKey(name, group));
			map.put("success", true);
		} catch (SchedulerException e) {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = { "pause" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> pause(HttpServletRequest request,
			@RequestParam(value = "name") String name, @RequestParam(value = "group") String group) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			scheduler.pauseJob(JobKey.jobKey(name, group));
			map.put("success", true);
		} catch (SchedulerException e) {
			map.put("success", false);
		}
		return map;
	}

	@RequestMapping(value = { "delete" }, method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,
			@RequestParam(value = "name") String jobName, @RequestParam(value = "group") String jobGroupName) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			deleteJob(jobName, jobGroupName);
			map.put("success", true);
		} catch (SchedulerException e) {
			map.put("success", false);
		}

		return map;
	}

	public void deleteJob(String jobName, String jobGroupName) throws SchedulerException {
		scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
		scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
		scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
	}

	public static BaseJob getClass(String classname) throws Exception {
		Class<?> class1 = Class.forName(classname);
		return (BaseJob) class1.newInstance();
	}

}
