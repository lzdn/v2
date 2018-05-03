package com.xuexi.v2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuexi.v2.domain.Role;
import com.xuexi.v2.mapper.RoleMapper;
import com.xuexi.v2.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Override
	public List<Role> findByUserRole(Integer userId) {
		return roleMapper.findByUserRole(userId);
	}

}
