package com.xuexi.v2.tag;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.xuexi.v2.domain.Role;
import com.xuexi.v2.security.SecurityUser;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.HasRoleTag}
 * </p>
 */
public class HasRoleTag extends RoleTag {

	protected boolean showTagBody(String roleName) {
		SecurityUser user = getSecurityUser();
		if (!CollectionUtils.isEmpty(user.getRoles())) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				if (roleName.equals(role.getRoleName()))
					return true;
			}
		}
		return false;
	}
}
