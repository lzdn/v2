package com.xuexi.v2.service;

import com.github.pagehelper.PageInfo;
import com.xuexi.v2.domain.Job;
import com.xuexi.v2.domain.JobLog;
import com.xuexi.v2.domain.dto.JobDto;

public interface IJobService {

	void printMsg();

	void addLog(JobLog log);

	PageInfo<Job> findAll(JobDto job);

}
