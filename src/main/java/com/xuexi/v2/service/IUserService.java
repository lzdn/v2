package com.xuexi.v2.service;

import com.xuexi.v2.domain.User;

public interface IUserService {

	User findByAccount(String account);
	
	int register(User user);
}
