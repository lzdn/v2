package com.xuexi.v2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xuexi.v2.domain.Resource;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
    
    List<Resource> findUserResources(@Param("userId") Integer userId,@Param("parentId") Integer parentId);
    
}