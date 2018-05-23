package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Dept;
import com.xuexi.v2.domain.dto.DeptDto;

public interface IDeptService {

	int add(Dept dept);

	List<Dept> list();

	Page<Dept> findSplitPage(DeptDto deptDto);

}
