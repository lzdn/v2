package com.xuexi.v2.web.base;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xuexi.v2.security.SecurityUser;

public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {// "yyyy-MM-dd HH:mm:ss"
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	protected void writeJSON(HttpServletResponse response, String json) throws IOException {
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(json);
	}

	protected void writeJSON(HttpServletResponse response, Object obj) throws IOException {
		response.reset();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue));
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String page403(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return "/403";
	}

	protected SecurityUser getLoginUser() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		Authentication auth = ctx.getAuthentication();
		SecurityUser user = (SecurityUser) auth.getPrincipal();
		return user;
	}

}
