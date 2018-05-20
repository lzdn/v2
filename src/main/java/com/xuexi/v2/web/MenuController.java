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
import com.xuexi.v2.domain.Menu;
import com.xuexi.v2.domain.dto.MenuDto;
import com.xuexi.v2.service.IMenuService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "menu")
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, MenuDto menuDto) {
		Page<Menu> splitPage = menuService.findSplitPage(menuDto);
		model.addAttribute("menuSplitPages", splitPage.toPageInfo());
		model.addAttribute("menuDto", menuDto);
		return "admin/menu/main";
	}

	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, MenuDto menuDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", menuService.add(menuDto));
		return map;
	}
}
