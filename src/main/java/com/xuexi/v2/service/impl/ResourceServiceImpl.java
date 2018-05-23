package com.xuexi.v2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xuexi.v2.domain.Resource;
import com.xuexi.v2.mapper.ResourceMapper;
import com.xuexi.v2.service.IResourceService;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<Resource> findUserResources(Integer uid) {
		List<Resource> list = resourceMapper.findUserResources(uid, 0);// 一级
		if (!CollectionUtils.isEmpty(list)) {
			for (Resource resource : list) {
				List<Resource> subs = resourceMapper.findUserResources(uid, resource.getId());
				if (!CollectionUtils.isEmpty(subs)) {
					for (Resource resource2 : subs) {
						List<Resource> subss = resourceMapper.findUserResources(uid, resource2.getId());
						resource2.setChildren(subss);// 三级
					}
				}
				resource.setChildren(subs);// 二级
			}
		}
		return list;
	}

}
