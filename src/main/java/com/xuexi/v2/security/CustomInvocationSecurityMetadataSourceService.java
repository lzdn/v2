package com.xuexi.v2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.xuexi.v2.domain.Right;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.mapper.RoleMapper;

@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private RoleMapper  roleMapper;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;
	// 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
	@PostConstruct 
	private void loadResourceDefine() { // 一定要加上@PostConstruct注解
		// 在Web服务器启动时，提取系统中的所有权限。
		/*
		 * 应当是资源为key， 权限为value。 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。 sparta
		 */
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		
		/*ConfigAttribute defaultRole = new SecurityConfig("ROLE_NO_RIGHT");//当没有权限时,给的默认权限
		Collection<ConfigAttribute> defaultAtts = new ArrayList<ConfigAttribute>();
		defaultAtts.add(defaultRole);
		resourceMap.put("default_error", defaultAtts);*/
		
		List<Role> roles = roleMapper.findAll();// 查询出所有角色
		if (!CollectionUtils.isEmpty(roles)) {
			for (Role role : roles) {
				ConfigAttribute ca = new SecurityConfig(role.getRoleName());
				if (!CollectionUtils.isEmpty(role.getRights())) {
					for (Right right : role.getRights()) {
						/*
						 * 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中。
						 */
						if (resourceMap.containsKey(right.getRightUrl())) {
							Collection<ConfigAttribute> value = resourceMap.get(right.getRightUrl());
							value.add(ca);
							resourceMap.put(right.getRightUrl(), value);
						} else {
							Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
							atts.add(ca);
							resourceMap.put(right.getRightUrl(), atts);
						}
					}
				}
			}
		}

	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	// 根据URL，找到相关的权限配置。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		FilterInvocation filterInvocation = (FilterInvocation) object;
		if (resourceMap == null) {
			loadResourceDefine();
		}
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(filterInvocation.getHttpRequest())) {
				return resourceMap.get(resURL);//如果多个角色拥有同一个权限 好像有个bug
			}
		}
		return null;
		//如果没有在权限表维护的，就直接放行，就不加默认权限了
		//此处如果返回为空 ，那么com.xuexi.security.CustomAccessDecisionManager.decide 不会被执行， 所以加了默认的权限
		//return resourceMap.get("default_error");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}
	
	public void defaultSecurity() {
		
	}

}
