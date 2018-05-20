package com.xuexi.v2.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Dept;

public interface DeptMapper {
	
	int deleteByPrimaryKey(Integer deptId);

	int insert(Dept record);

	int insertSelective(Dept record);

	Dept selectByPrimaryKey(Integer deptId);

	int updateByPrimaryKeySelective(Dept record);

	int updateByPrimaryKey(Dept record);
	
	Page<Dept> findPage(Map<String,Object> map);
	
	List<Dept> list();
}