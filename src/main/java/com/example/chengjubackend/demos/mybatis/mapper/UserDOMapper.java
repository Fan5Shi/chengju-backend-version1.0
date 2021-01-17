package com.example.chengjubackend.demos.mybatis.mapper;

import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper类
 * @author Jilin He
 * @date 2020.01.17
 */

@Mapper
public interface UserDOMapper {

    /**
     * 用户登录
     * @param userId 学号/用户序号
     * @param password 密码
     * @return 用户类
     */
    UserDO login(Integer userId, String password);

    /**
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 用户的详细信息
     */
    UserDO findUserById(Integer userId);

    /**
     * 新增用户
     * @param userDO 用户实体
     * @return 被影响的行数
     */
    int insert(UserDO userDO);

    /**
     * 更新用户
     * @param userDO 用户实体
     * @return 被影响的行数
     */
    int update(UserDO userDO);

}
