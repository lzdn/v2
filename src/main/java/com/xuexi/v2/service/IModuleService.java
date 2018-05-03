package com.xuexi.v2.service;

import java.util.List;

import com.xuexi.v2.domain.Module;

public interface IModuleService {

	List<Module> findByUserModules(Integer userId);
}
