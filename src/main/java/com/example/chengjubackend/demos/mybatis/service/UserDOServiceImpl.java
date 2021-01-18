package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.mapper.UserDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户Service的实现类
 * @author Jilin He
 * @date 2020.01.17
 */

@Service
public class UserDOServiceImpl implements UserDOService{

    private final static Logger logger = LoggerFactory.getLogger(UserDOServiceImpl.class);

    @Autowired
    private UserDOMapper userMapper;

    @Override
    public ResultDO login(Integer userId, String password) {
        logger.info("入参：userId - " + userId + "；password - " + password);
        if (userId == 0 || password == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入参数不能为0或空!");
        }
        UserDO userDO = userMapper.findUserById(userId);
        System.out.println(userDO.toString());
        if (userDO.getUserId() == null) {
            return new ResultDO(HttpCode.UNAUTHORIZED.getCode(), "不存在该用户！");
        }
        if (!userDO.getPassword().equals(password)) {
            return new ResultDO(HttpCode.UNAUTHORIZED.getCode(), "用户密码错误！");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "登录成功", userDO);
    }

    @Override
    public ResultDO findUserById(Integer userId) {
        logger.info("入参：" + userId);
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0");
        }
        UserDO userDO = userMapper.findUserById(userId);
        if (userDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "不存在此用户");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "查找成功", userDO);
    }

    @Override
    public ResultDO insert(UserDO userDO) {
        logger.info("入参：" + userDO.toString());
        int influencedLines = userMapper.insert(userDO);
        if (influencedLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "插入失败");
        }
        return new ResultDO(HttpCode.CREATED.getCode(), "插入成功", influencedLines);
    }

    @Override
    public ResultDO update(UserDO userDO) {
        logger.info("入参：" + userDO.toString());
        int influencedLines = userMapper.update(userDO);
        if (influencedLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "更新失败");
        }
        return new ResultDO(HttpCode.CREATED.getCode(), "更新成功", influencedLines);
    }
}
