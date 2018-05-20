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
import com.xuexi.v2.domain.Module;
import com.xuexi.v2.domain.dto.ModuleDto;
import com.xuexi.v2.service.IModuleService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "module")
public class ModuleController extends BaseController {

	@Autowired
	private IModuleService moduleService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, ModuleDto moduleDto) {
		Page<Module> splitPage = moduleService.findSplitPage(moduleDto);
		model.addAttribute("moduleSplitPages", splitPage.toPageInfo());
		model.addAttribute("moduleDto", moduleDto);
		return "admin/module/main";
	}

	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, ModuleDto moduleDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", moduleService.add(moduleDto));
		return map;
	}
}
