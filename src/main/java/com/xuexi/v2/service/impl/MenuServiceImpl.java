package com.xuexi.v2.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuexi.v2.domain.Menu;
import com.xuexi.v2.domain.dto.MenuDto;
import com.xuexi.v2.mapper.MenuMapper;
import com.xuexi.v2.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public Page<Menu> findSplitPage(MenuDto menuDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (menuDto != null) {
			if (StringUtils.isNotEmpty(menuDto.getMenuName())) {
				map.put("menuName", menuDto.getMenuName());
			}
		}
		PageHelper.startPage(menuDto.getPageNo(), menuDto.getPageSize());
		Page<Menu> page = menuMapper.findPage(map);
		return page;
	}

	@Override
	public int add(MenuDto menuDto) {
		return menuMapper.insertSelective(menuDto);
	}

}
