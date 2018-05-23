package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.dto.RoleDto;
import com.xuexi.v2.mapper.RoleMapper;
import com.xuexi.v2.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public int add(Role role) {
		return roleMapper.insertSelective(role);
	}

	@Override
	public List<Role> findRoleResource(Integer userId) {
		return roleMapper.findRoleResource(userId);
	}

	@Override
	public List<Role> list() {
		Map<String, Object> map = new HashMap<String, Object>();
		return roleMapper.findPage(map);
	}

	@Override
	public Page<Role> findSplitPage(RoleDto roleDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (roleDto != null) {
			if (StringUtils.isNotEmpty(roleDto.getRoleName())) {
				map.put("roleName", roleDto.getRoleName());
			}
		}
		PageHelper.startPage(roleDto.getPageNo(), roleDto.getPageSize());
		Page<Role> page = roleMapper.findPage(map);
		return page;
	}



}
