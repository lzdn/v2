package com.xuexi.v2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.xuexi.v2.domain.Role;
import com.xuexi.v2.domain.dto.GrantDto;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Page<Role> findPage(Map<String,Object> map);
    
    int deleteRightRoleId(@Param("roleId") Integer roleId);
    
    int insertRightBatch(List<GrantDto> grants);
    
    List<Role> findRoleResource(@Param("userId") Integer userId);
    
}