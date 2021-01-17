package com.example.chengjubackend.demos.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户类，记录用户的基本信息和密码等
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
public class UserDO {

    /**
     * 学号/用户序号
     */
    private Integer userId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户生日，格式为"yyyy-MM-dd"
     */
    private Date userBirth;

    /**
     * 用户联系方式
     */
    private String userPhone;

    public UserDO() {
    }

    public UserDO(Integer userId, String password, String userName, Date userBirth, String userPhone) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.userBirth = userBirth;
        this.userPhone = userPhone;
    }
}
