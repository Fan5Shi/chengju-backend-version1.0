package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.LoginUser;
import com.example.chengjubackend.demos.mybatis.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public ResultDO findByUserId(Integer userId) {
        return null;
    }

    @Override
    public ResultDO addLoginUser(LoginUser loginUser) {
        return null;
    }

}
