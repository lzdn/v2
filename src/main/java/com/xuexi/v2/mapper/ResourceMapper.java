package com.xuexi.v2.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Resource;

public interface ResourceMapper {
	
	int deleteByPrimaryKey(Integer id);

	int insert(Resource record);

	int insertSelective(Resource record);

	Resource selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Resource record);

	int updateByPrimaryKey(Resource record);
	
	Page<Resource> findPage(Map<String,Object> map);

}