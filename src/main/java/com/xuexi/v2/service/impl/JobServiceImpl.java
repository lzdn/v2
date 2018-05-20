package com.xuexi.v2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuexi.v2.domain.Job;
import com.xuexi.v2.domain.JobLog;
import com.xuexi.v2.domain.dto.JobDto;
import com.xuexi.v2.mapper.JobLogMapper;
import com.xuexi.v2.mapper.JobMapper;
import com.xuexi.v2.service.IJobService;

@Service("iJobService")
public class JobServiceImpl implements IJobService {

	@Autowired
	private JobMapper jobMapper;

	@Autowired
	private JobLogMapper jobLogMapper;

	@Override
	public PageInfo<Job> findAll(JobDto job) {
		PageHelper.startPage(job.getPageNo(), job.getPageSize());
		Page<Job> page = jobMapper.findPage();
		return page.toPageInfo();
	}

	@Override
	public void addLog(JobLog log) {
		jobLogMapper.insertSelective(log);
	}

	@Override
	public void printMsg() {
		System.out.println("Hello");

	}

}
