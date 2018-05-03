package com.xuexi.v2.mapper;

import com.xuexi.v2.domain.Menu;

public interface MenuMapper {
	int deleteByPrimaryKey(Integer menuId);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Integer menuId);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	// List<Menu> findSubMenus();

}