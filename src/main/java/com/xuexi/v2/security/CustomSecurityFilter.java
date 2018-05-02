package com.xuexi.v2.security;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

@Component
public class CustomSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	@Autowired
	private CustomInvocationSecurityMetadataSourceService mySecurityMetadataSource;

	@Autowired
	private CustomAccessDecisionManager myAccessDecisionManager;

	@Autowired
	private AuthenticationManager authenticationManager;

	private Logger logger = LoggerFactory.getLogger(CustomSecurityFilter.class);

	@PostConstruct
	public void init() {
		super.setAuthenticationManager(authenticationManager);
		super.setAccessDecisionManager(myAccessDecisionManager);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}

	public Class<? extends Object> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		logger.info("执行 CustomSecurityFilter："+fi.getRequestUrl());
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}

	}

	@Override
	public AccessDecisionManager getAccessDecisionManager() {
		return this.myAccessDecisionManager;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.mySecurityMetadataSource;
	}

	public void destroy() {

	}

	public void init(FilterConfig filterconfig) throws ServletException {
	}
}
