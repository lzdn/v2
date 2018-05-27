package com.xuexi.v2.tag;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.xuexi.v2.security.SecurityUser;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 * Equivalent to {@link org.apache.shiro.web.tags.SecureTag}
 * </p>
 */
public abstract class SecureTag implements TemplateDirectiveModel {

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		verifyParameters(params);
		render(env, params, body);
	}

	public abstract void render(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateDirectiveBody body)
			throws IOException, TemplateException;

	protected String getParam(@SuppressWarnings("rawtypes") Map params, String name) {
		Object value = params.get(name);

		if (value instanceof SimpleScalar) {
			return ((SimpleScalar) value).getAsString();
		}

		return null;
	}

	protected SecurityUser getSecurityUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		return user;
	}

	protected void verifyParameters(@SuppressWarnings("rawtypes") Map params) throws TemplateModelException {
	}

	protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
		if (body != null) body.render(env.getOut());
	}
}
