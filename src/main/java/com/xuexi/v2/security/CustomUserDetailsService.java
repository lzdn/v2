package com.xuexi.v2.security;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.service.IModuleService;
import com.xuexi.v2.service.IRoleService;
import com.xuexi.v2.service.IUserService;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired 
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IModuleService moduleService;
	
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		// SysUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
		// 本例使用SysUser中的name作为用户名:
		User user = userService.findByAccount(account);
		if (user == null) {
			throw new UsernameNotFoundException("帐户： " + account + " 不存在");
		}
		Role role = roleService.findRoleResource(user.getUserId());
		List<Module> modules = moduleService.findModuleRoleResource(role.getRoleId());
		user.setModules(modules);
		user.setRole(role);
		SecurityUser seu = new SecurityUser(user);
		return seu;
	}
}
