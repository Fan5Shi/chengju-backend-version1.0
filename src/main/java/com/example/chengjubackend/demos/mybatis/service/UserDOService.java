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
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO findUserById(Integer userId);

    /**
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO findUserByIdAdmin(Integer userId);

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

    /**
     * 删除用户
     * @param userDO 用户实体
     * @return 结果类
     */
    ResultDO delete(Integer userId);
}
