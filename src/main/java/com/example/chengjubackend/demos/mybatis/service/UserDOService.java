package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;

/**
 * 用户Service类
 * @author Jilin He
 * @date 2020.01.17
 */

public interface UserDOService {

    /**
     * 用户登录
     * @param userId 学号/用户序号
     * @param password 密码
     * @return 结果类，包括响应码，附带信息，附带对象等
     */
    ResultDO login(Integer userId, String password);

    /**
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO findUserById(Integer userId);

    /**
     * 新增用户
     * @param userDO 用户实体
     * @return 结果类
     */
    ResultDO insert(UserDO userDO);

    /**
     * 更新用户
     * @param userDO 用户实体
     * @return 结果类
     */
    ResultDO update(UserDO userDO);
}
