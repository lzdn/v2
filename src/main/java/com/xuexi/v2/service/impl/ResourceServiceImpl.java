package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuexi.v2.domain.Resource;
import com.xuexi.v2.domain.dto.ResourceDto;
import com.xuexi.v2.mapper.ResourceMapper;
import com.xuexi.v2.service.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Resource> findAll() {
		return resourceMapper.findAll();
	}

	@Override
	@Transactional(value = "myTransactionManager")
	public int delete(Integer id) {
		resourceMapper.deleteByResourceId(id);
		resourceMapper.deleteByPrimaryKey(id);
		return 1;
	}

	@Override
	@Transactional(value = "myTransactionManager")
	public int add(ResourceDto resourceDto) {
		resourceDto.setAvailable(true);
		return resourceMapper.insertSelective(resourceDto);
	}

	@Override
	public Page<Resource> findSplitPage(ResourceDto resourceDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (resourceDto != null) {
			if (StringUtils.isNotEmpty(resourceDto.getResourceKey())) {
				map.put("resourceKey", resourceDto.getResourceKey());
			}
			if (StringUtils.isNotEmpty(resourceDto.getResourceValue())) {
				map.put("resourceValue", resourceDto.getResourceValue());
			}
			if (resourceDto.getModuleId() != null) {
				map.put("moduleId", resourceDto.getModuleId());
			}
			if (resourceDto.getType() != null) {
				map.put("type", resourceDto.getType());
			}
			if (resourceDto.getAvailable() != null) {
				map.put("available", resourceDto.getAvailable());
			}
			if (resourceDto.getId() != null) {
				map.put("id", resourceDto.getId());
			}
		}

		PageHelper.startPage(resourceDto.getPageNo(), resourceDto.getPageSize());
		Page<Resource> page = resourceMapper.findPage(map);
		return page;
	}

}
