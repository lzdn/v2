package com.xuexi.v2.mapper;

import org.apache.ibatis.annotations.Param;

import com.xuexi.v2.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findByAccount(@Param(value="account") String account);
}