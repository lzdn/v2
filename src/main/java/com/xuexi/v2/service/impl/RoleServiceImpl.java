package com.xuexi.v2.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.dto.GrantDto;
import com.xuexi.v2.domain.dto.RoleDto;
import com.xuexi.v2.mapper.RoleMapper;
import com.xuexi.v2.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	@Transactional(value = "myTransactionManager")
	public int add(Role role) {
		
		Role tmp = findRoleByRoleKey(role.getRoleKey());
		if (tmp != null)
			return 0;
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
	public Role findRoleByRoleKey(String roleKey) {
		Role role = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleKey", roleKey);
		Page<Role> page = roleMapper.findPage(map);
		PageInfo<Role> info = page.toPageInfo();
		if (!CollectionUtils.isEmpty(info.getList())) {
			role = info.getList().get(0);
		}
		return role;
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

	@Override
	@Transactional(value = "myTransactionManager")
	public void grant(Integer[] resouresId, Integer roleId) {
		roleMapper.deleteRightRoleId(roleId);
		if (resouresId != null && resouresId.length > 0) {
			List<GrantDto> dtos = new ArrayList<GrantDto>();
			for (Integer integer : resouresId) {
				GrantDto dto = new GrantDto();
				dto.setResourceId(integer);
				dto.setRoleId(roleId);
				dtos.add(dto);
			}
			roleMapper.insertRightBatch(dtos);
		}

	}

}
