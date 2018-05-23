package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.dto.ModuleDto;

public interface IModuleService {

	int add(ModuleDto moduleDto);

	List<Module> getModules();

	Page<Module> findSplitPage(ModuleDto moduleDto);

	List<Module> findModuleRoleResource(Integer roleId);

}
