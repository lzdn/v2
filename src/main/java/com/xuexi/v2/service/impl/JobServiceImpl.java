package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(value = "myTransactionManager")
	public void addLog(JobLog log) {
		jobLogMapper.insertSelective(log);
	}

	@Override
	public void printMsg() {
		System.out.println("Hello");
	}

	@Override
	public PageInfo<Job> findAll(JobDto job) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (job != null) {
			if (StringUtils.isNotEmpty(job.getJOB_NAME())) {
				map.put("JOB_NAME", job.getJOB_NAME());
			}
		}
		PageHelper.startPage(job.getPageNo(), job.getPageSize());
		Page<Job> page = jobMapper.findPage(map);
		return page.toPageInfo();
	}

}
