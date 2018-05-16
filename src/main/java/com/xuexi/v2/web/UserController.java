package com.xuexi.v2.web;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.domain.dto.UserDto;
import com.xuexi.v2.service.IUserService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

	@Autowired
	private IUserService iUserService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String auth(HttpServletResponse response, Model model, UserDto userDto) {
		Page<User> splitPage = iUserService.findSplitPage(userDto);

		return "admin/user/main";
	}
}
