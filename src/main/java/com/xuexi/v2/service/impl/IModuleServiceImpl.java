package com.xuexi.v2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuexi.v2.domain.Module;
import com.xuexi.v2.mapper.ModuleMapper;
import com.xuexi.v2.service.IModuleService;

@Service
public class IModuleServiceImpl implements IModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> findByUserModules(Integer userId) {

		List<Module> modules = moduleMapper.findByUserModules(userId);
		/*
		 * for (Module module : modules) { List<Menu> menus = module.getMenus(); for
		 * (Menu menu : menus) { menu.setChildren(menus); } }
		 */

		return modules;
	}

}
