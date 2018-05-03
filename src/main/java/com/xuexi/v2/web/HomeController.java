package com.xuexi.v2.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xuexi.v2.security.SecurityUser;
import com.xuexi.v2.web.base.BaseController;

@Controller
public class HomeController extends BaseController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		logger.debug("enter index!");
		return "/welcome";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) throws Exception {
		logger.info("enter login!");
		return "/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(HttpServletRequest request, Model model) throws Exception {
		logger.info("enter register!");
		return "/register";
	}
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		SecurityUser user = super.getLoginUser();
//		model.addAttribute("user", user);
		if(user.getUserId()==null) {
			super.redirect(response,"/login"); 
			return null;
		} 
		return "/home";
	}

}
