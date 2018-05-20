package com.xuexi.v2.mapper;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Job;

public interface JobMapper {

	Page<Job> findPage();
}
