package com.xuexi.v2.mapper;

import com.xuexi.v2.domain.Dept;

public interface DeptMapper {
	
	int deleteByPrimaryKey(Integer deptId);

	int insert(Dept record);

	int insertSelective(Dept record);

	Dept selectByPrimaryKey(Integer deptId);

	int updateByPrimaryKeySelective(Dept record);

	int updateByPrimaryKey(Dept record);
}