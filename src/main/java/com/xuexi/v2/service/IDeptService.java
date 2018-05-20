package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Dept;
import com.xuexi.v2.domain.dto.DeptDto;

public interface IDeptService {
	
	Page<Dept> findSplitPage(DeptDto deptDto);
	
	List<Dept> list();
	
	int add(Dept dept);

}
