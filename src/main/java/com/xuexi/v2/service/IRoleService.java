package com.xuexi.v2.service;

import java.util.List;

import com.xuexi.v2.domain.Role;

public interface IRoleService {

	List<Role> findByUserRole(Integer userId);

}
