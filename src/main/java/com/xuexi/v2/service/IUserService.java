package com.xuexi.v2.service;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.domain.dto.UserDto;

public interface IUserService {

	User findByAccount(String account);

	int register(User user);

	Page<User> findSplitPage(UserDto userDto);
}
