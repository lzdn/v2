package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Resource;
import com.xuexi.v2.domain.dto.ResourceDto;

public interface IResourceService {

	int delete(Integer id);

	List<Resource> findAll();

	int add(ResourceDto resourceDto);

	Page<Resource> findSplitPage(ResourceDto resourceDto);

}
