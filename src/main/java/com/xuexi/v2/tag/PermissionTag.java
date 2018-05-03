package com.xuexi.v2.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.xuexi.v2.domain.Right;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.security.SecurityUser;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.PermissionTag}
 * </p>
 */
public abstract class PermissionTag extends SecureTag {

	String getName(@SuppressWarnings("rawtypes") Map params) {
		return getParam(params, "name");
	}

	@Override
	protected void verifyParameters(@SuppressWarnings("rawtypes") Map params) throws TemplateModelException {
		String permission = getName(params);
		if (permission == null || permission.length() == 0) {
			throw new TemplateModelException("The 'name' tag attribute must be set.");
		}
	}

	@Override
	public void render(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		String p = getName(params);
		boolean show = showTagBody(p);
		if (show) {
			renderBody(env, body);
		}
	}

	protected boolean isPermitted(String p) {
		SecurityUser user = getSecurityUser();
		if (!CollectionUtils.isEmpty(user.getRoles())) {
			List<Role> roles = user.getRoles();
			for (Role role : roles) {
				List<Right> rights = role.getRights();
				for (Right right : rights) {
					if (p.equals(right.getRightUrl()))
						return true;
				}
			}
		}

		return false;
	}

	protected abstract boolean showTagBody(String p);
}
