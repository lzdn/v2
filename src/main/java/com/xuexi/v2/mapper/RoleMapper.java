package com.xuexi.v2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    List<Role> findAll();
    
    List<Role> findByUserRole(@Param("userId") Integer userId);
    
    Page<Role> findPage(Map<String,Object> map);
    
    List<Role> list();
}