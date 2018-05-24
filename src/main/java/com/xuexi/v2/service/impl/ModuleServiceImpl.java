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
import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.dto.ModuleDto;
import com.xuexi.v2.mapper.ModuleMapper;
import com.xuexi.v2.service.IModuleService;

@Service
public class ModuleServiceImpl implements IModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> getModules() {
		return moduleMapper.getModules();
	}

	@Override
	@Transactional(value = "myTransactionManager")
	public int add(ModuleDto moduleDto) {
		return moduleMapper.insertSelective(moduleDto);
	}

	@Override
	public List<Module> findModuleRoleResource(Integer roleId) {
		return moduleMapper.findModuleRoleResource(roleId);
	}

	@Override
	public Page<Module> findSplitPage(ModuleDto moduleDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (moduleDto != null) {
			if (StringUtils.isNotEmpty(moduleDto.getModuleName())) {
				map.put("moduleName", moduleDto.getModuleName());
			}
		}
		PageHelper.startPage(moduleDto.getPageNo(), moduleDto.getPageSize());
		Page<Module> page = moduleMapper.findPage(map);
		return page;
	}

}
