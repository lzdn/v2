package com.xuexi.v2.web;

import java.util.HashMap;
import java.util.List;
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
import com.xuexi.v2.domain.Resource;
import com.xuexi.v2.domain.dto.ResourceDto;
import com.xuexi.v2.service.IModuleService;
import com.xuexi.v2.service.IResourceService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "resource")
public class ResourceController extends BaseController {

	@Autowired
	private IResourceService resourceService;
	
	@Autowired
	private IModuleService moduleService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, ResourceDto resourceDto) {
		Page<Resource> splitPage = resourceService.findSplitPage(resourceDto);
		model.addAttribute("resourceSplitPages", splitPage.toPageInfo());
		List<Module> modules = moduleService.getModules();
		model.addAttribute("modules", modules);
		model.addAttribute("resources", resourceService.findAll());
		model.addAttribute("resourceDto", resourceDto);
		return "admin/resource/main";
	}
	
	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, ResourceDto resourceDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", resourceService.add(resourceDto)>0);
		return map;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, Integer id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", resourceService.delete(id)>0);
		return map;
	}

}
