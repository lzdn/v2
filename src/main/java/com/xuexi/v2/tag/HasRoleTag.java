package com.xuexi.v2.tag;

import com.xuexi.v2.security.SecurityUser;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.HasRoleTag}
 * </p>
 */
public class HasRoleTag extends RoleTag {

	protected boolean showTagBody(String roleName) {
		SecurityUser user = getSecurityUser();
		if (user.getRole() != null && roleName.equals(user.getRole().getRoleName()))
			return true;
		return false;
	}
}
