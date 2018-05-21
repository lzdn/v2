package com.xuexi.v2.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.dto.RoleDto;

public interface IRoleService {

	List<Role> findByUserRole(Integer userId);
	
	Page<Role> findSplitPage(RoleDto roleDto);
	
	int add(Role role);
	
	List<Role> list();

}
