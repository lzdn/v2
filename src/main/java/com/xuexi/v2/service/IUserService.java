package com.xuexi.v2.service;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.domain.dto.UserDto;

public interface IUserService {

	int register(User user);
	
	int addUserRole(Integer userId,Integer roleId);

	User selectUserRoleByPk(Integer userId);

	User findByAccount(String account);

	Page<User> findSplitPage(UserDto userDto);
}
