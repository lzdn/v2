package com.xuexi.v2.service;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Resource;
import com.xuexi.v2.domain.dto.ResourceDto;

public interface IResourceService {

	Page<Resource> findSplitPage(ResourceDto resourceDto);
}
