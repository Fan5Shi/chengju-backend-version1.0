package com.example.chengjubackend.demos.mybatis.mapper;

import com.example.chengjubackend.demos.mybatis.entity.LoginUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginUserMapper {

    LoginUser findByUserId(Integer userId);

    Integer addLoginUser(LoginUser loginUser);

}
