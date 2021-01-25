package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.LoginUser;

public interface LoginUserService {

    ResultDO findByUserId(Integer userId);

    ResultDO addLoginUser(LoginUser loginUser);

}
