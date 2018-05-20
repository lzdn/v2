package com.xuexi.v2.mapper;

import com.xuexi.v2.domain.JobLog;

public interface JobLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLog record);

    int insertSelective(JobLog record);

    JobLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLog record);

    int updateByPrimaryKey(JobLog record);
}