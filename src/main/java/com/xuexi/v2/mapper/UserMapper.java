package com.xuexi.v2.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User findByAccount(@Param(value = "account") String account);

	Page<User> findPage(Map<String, Object> map);

	User selectUserRoleByPk(@Param(value = "userId") Integer userId);
	
	int addUserRole(@Param(value = "userId") Integer userId,@Param(value = "roleId") Integer roleId);
	
	int deleteUserRole(@Param(value = "userId") Integer userId);
}