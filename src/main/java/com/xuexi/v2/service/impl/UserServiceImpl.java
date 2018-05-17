package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.domain.dto.UserDto;
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

	@Override
	@Transactional(value = "myTransactionManager")
	public int register(User user) {
		return userMapper.insertSelective(user);
	}

	@Override
	public Page<User> findSplitPage(UserDto userDto) {
		
		Map<String,Object> map = new HashMap<String,Object>();

		if(userDto!=null) {
			if(userDto.getUserId()!=null) {
				map.put("userId", userDto.getUserId());
			}
			if(StringUtils.isNotEmpty(userDto.getUsername())) {
				map.put("username", userDto.getUsername());
			}
			if(StringUtils.isNotEmpty(userDto.getAccount())) {
				map.put("account", userDto.getAccount());
			}
		}
		
		PageHelper.startPage(userDto.getPageNo(), userDto.getPageSize());
		
		Page<User> pageInfo = userMapper.findPage(map);
		
		return pageInfo;
	}

}
