package com.example.chengjubackend.demos.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类，记录用户的基本信息和密码等
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO implements Serializable {

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
