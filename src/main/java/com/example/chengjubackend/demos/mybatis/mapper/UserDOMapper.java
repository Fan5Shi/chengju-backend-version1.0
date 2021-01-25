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
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 用户的详细信息
     */
    UserDO findUserById(Integer userId);

    /**
     * 根据学号/用户序号获取用户的详细信息
     * @param userId 学号/用户序号
     * @return 用户的详细信息
     */
    UserDO findUserByIdAdmin(Integer userId);

    /**
     * 新增用户
     * @param userDO 用户实体
     * @return 被影响的行数
     */
    Integer insert(UserDO userDO);

    /**
     * 更新用户
     * @param userDO 用户实体
     * @return 被影响的行数
     */
     Integer update(UserDO userDO);

    /**
     * 删除用户
     * @param userDO 用户实体
     * @return 被影响的行数
     */
    Integer delete(Integer userId);

}
