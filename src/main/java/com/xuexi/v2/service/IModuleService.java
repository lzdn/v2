package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.dto.ModuleDto;

public interface IModuleService {

	List<Module> findByUserModules(Integer userId);

	Page<Module> findSplitPage(ModuleDto moduleDto);

	int add(ModuleDto moduleDto);
}
