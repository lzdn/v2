package com.xuexi.v2.service;


import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Menu;
import com.xuexi.v2.domain.dto.MenuDto;

public interface IMenuService {

	Page<Menu> findSplitPage(MenuDto menuDto);
	
	int add(MenuDto menuDto);
}
