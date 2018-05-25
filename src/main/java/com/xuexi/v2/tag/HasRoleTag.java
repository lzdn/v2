package com.xuexi.v2.tag;

import com.xuexi.v2.security.SecurityUser;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.HasRoleTag}
 * </p>
 */
public class HasRoleTag extends RoleTag {

	protected boolean showTagBody(String roleKey) {
		SecurityUser user = getSecurityUser();
		if (user.getRole() != null && roleKey.equals(user.getRole().getRoleKey()))
			return true;
		return false;
	}
}
