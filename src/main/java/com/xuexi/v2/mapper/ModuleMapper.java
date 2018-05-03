package com.xuexi.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuexi.v2.domain.Module;

public interface ModuleMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Integer moduleId);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
    
    List<Module> findByUserModules(@Param("userId") Integer userId);
}