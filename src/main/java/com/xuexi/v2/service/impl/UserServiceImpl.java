package com.xuexi.v2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuexi.v2.domain.User;
import com.xuexi.v2.mapper.UserMapper;
import com.xuexi.v2.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByAccount(String account) {
		return userMapper.findByAccount(account);
	}

}
