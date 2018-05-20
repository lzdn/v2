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
import com.xuexi.v2.domain.Dept;
import com.xuexi.v2.domain.dto.DeptDto;
import com.xuexi.v2.service.IDeptService;
import com.xuexi.v2.web.base.BaseController;

@Controller
@RequestMapping(value = "dept")
public class DeptController extends BaseController {

	@Autowired
	private IDeptService deptService;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletResponse response, Model model, DeptDto deptDto) {
		Page<Dept> splitPage = deptService.findSplitPage(deptDto);
		model.addAttribute("deptSplitPages", splitPage.toPageInfo());
		model.addAttribute("deptDto", deptDto);
		model.addAttribute("depts", deptService.list());
		return "admin/dept/main";
	}

	@RequestMapping("/add")
	public @ResponseBody Map<String, Object> add(HttpServletRequest request, DeptDto deptDto) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", deptService.add(deptDto));
		return map;
	}
}
