package com.xuexi.v2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.User;
import com.xuexi.v2.domain.dto.UserDto;
import com.xuexi.v2.service.IRoleService;
import com.xuexi.v2.service.IUserService;
import com.xuexi.v2.utils.MD5Util;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, UserDto userDto) {
		Page<User> splitPage = userService.findSplitPage(userDto);
		model.addAttribute("userSplitPages", splitPage.toPageInfo());
		model.addAttribute("userDto", userDto);
		return "admin/user/main";
	}

	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest httpRequest, UserDto userDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		User account = userService.findByAccount(userDto.getAccount());
		if(account!=null) {map.put("success", false);return map;}
		userDto.setSalt(MD5Util.SALT);
		userDto.setPassword(MD5Util.encode(userDto.getPassword()));
		int i = userService.register(userDto);
		map.put("success", i > 0);
		return map;
	}

	@RequestMapping(value = "/grant/{userId}", method = RequestMethod.GET)
	public String allot(HttpServletResponse response, Model model, @PathVariable("userId") Integer userId) {
		User user = userService.selectUserRoleByPk(userId);
		model.addAttribute("user", user);
		model.addAttribute("roles", roleService.list());
		return "admin/user/grant";
	}

	@RequestMapping("/addRole")
	public @ResponseBody Map<String, Object> addRole(HttpServletRequest httpRequest, Integer userId, Integer roleId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", userService.addUserRole(userId, roleId) > 0);
		return map;
	}
}
