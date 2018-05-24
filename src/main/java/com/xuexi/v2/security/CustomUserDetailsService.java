package com.xuexi.v2.security;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.Resource;
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
		List<Role> roles = roleService.findRoleResource(user.getUserId());
		if(!CollectionUtils.isEmpty(roles)) {
			Role role = roles.get(0);
			List<Module> modules = moduleService.findModuleRoleResource(role.getRoleId());
			for (Module module : modules) {
				 List<Resource> resources = module.getResources();
				 if (!CollectionUtils.isEmpty(resources)) {
					 check(resources);
				 }
			}
			user.setModules(modules);
			user.setRole(role);
		}
		SecurityUser seu = new SecurityUser(user);
		return seu;
	}

	public void check(List<Resource> resources) {
		Iterator<Resource> it = resources.iterator();
		while (it.hasNext()) {
			Resource resource = it.next();
			if(resource==null)continue;
			if(resource.getType()!=1) {it.remove();continue;}
			if(resource.getIsCheck() == null) {it.remove();} 
			if(!CollectionUtils.isEmpty(resource.getChildren())){check(resource.getChildren());}
		}
	}
}
