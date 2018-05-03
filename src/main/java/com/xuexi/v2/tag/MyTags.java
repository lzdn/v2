package com.xuexi.v2.tag;

import freemarker.template.SimpleHash;

/**
 * Shortcut for injecting the tags into Freemarker
 *
 * <p>
 * Usage: cfg.setSharedVeriable("shiro", new ShiroTags());
 * </p>
 */
@SuppressWarnings("serial")
public class MyTags extends SimpleHash {
	
	@SuppressWarnings("deprecation")
	public MyTags() {
		put("hasPermission", new HasPermissionTag());
		put("hasRole", new HasRoleTag());
		put("userLogin", new UserLoginTag());
	}
}
