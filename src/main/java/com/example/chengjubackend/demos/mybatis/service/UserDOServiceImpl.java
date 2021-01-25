package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.mapper.UserDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public ResultDO findUserById(Integer userId) {
        logger.info("入参：" + userId);
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0！");
        }
        UserDO userDO = userMapper.findUserById(userId);
        if (userDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "不存在此用户！");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 查找成功", userDO);
    }

    @Override
    public ResultDO findUserByIdAdmin(Integer userId) {
        logger.info("入参：" + userId);
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0！");
        }
        UserDO userDO = userMapper.findUserByIdAdmin(userId);
        if (userDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "不存在此用户！");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 查找成功", userDO);
    }

    @Override
    public ResultDO insert(UserDO userDO) {
        logger.info("入参：" + userDO.toString());
        UserDO user = userMapper.findUserById(userDO.getUserId());
        if (user != null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "已存在该用户！");
        }
        int influencedLines = userMapper.insert(userDO);
        if (influencedLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "插入失败");
        }
        return new ResultDO(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg() + " 插入成功", influencedLines);
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

    @Override
    public ResultDO delete(Integer userId) {
        logger.info("入参：" + userId);
        int influencedLines = userMapper.delete(userId);
        if (influencedLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "更新失败");
        }
        return new ResultDO(HttpCode.CREATED.getCode(), "更新成功", influencedLines);
    }
}
