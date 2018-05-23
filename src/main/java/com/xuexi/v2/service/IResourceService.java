package com.xuexi.v2.service;

import java.util.List;

import com.xuexi.v2.domain.Resource;

public interface IResourceService {

	List<Resource> findUserResources(Integer uid);
}
