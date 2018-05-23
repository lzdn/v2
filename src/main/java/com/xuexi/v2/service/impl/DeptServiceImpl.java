package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuexi.v2.domain.Dept;
import com.xuexi.v2.domain.dto.DeptDto;
import com.xuexi.v2.mapper.DeptMapper;
import com.xuexi.v2.service.IDeptService;

@Service
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Dept> list() {
		return deptMapper.list();
	}

	@Override
	public int add(Dept dept) {
		return deptMapper.insertSelective(dept);
	}

	@Override
	public Page<Dept> findSplitPage(DeptDto deptDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (deptDto != null) {
			if (StringUtils.isNotEmpty(deptDto.getDeptFullName())) {
				map.put("deptFullName", deptDto.getDeptFullName());
			}
		}
		PageHelper.startPage(deptDto.getPageNo(), deptDto.getPageSize());
		Page<Dept> page = deptMapper.findPage(map);
		return page;
	}

}
