package com.xuexi.v2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.dto.RoleDto;
import com.xuexi.v2.service.IRoleService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, RoleDto roleDto) {
		Page<Role> splitPage = roleService.findSplitPage(roleDto);
		model.addAttribute("roleSplitPages", splitPage.toPageInfo());
		model.addAttribute("roleDto", roleDto);
		return "admin/role/main";
	}

	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, RoleDto roleDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", roleService.add(roleDto));
		return map;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> list(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roles", roleService.list());
		map.put("success", true);
		return map;
	}
}
