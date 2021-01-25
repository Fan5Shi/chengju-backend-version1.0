package com.example.chengjubackend.demos.mybatis.entity;

import com.example.chengjubackend.demos.mybatis.entity.base.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户类，记录用户的基本信息等
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户实体类", description = "记录用户的基本信息和密码等")
public class UserDO extends BaseDO implements Serializable {

    /**
     * 学号/用户序号
     */
    @ApiModelProperty(value = "学号/用户序号", dataType = "int", example = "20170001", required = true)
    private Integer userId;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名", dataType = "String", example = "Tommy", required = true)
    private String userName;

    /**
     * 用户生日，格式为"yyyy-MM-dd"
     */
    @ApiModelProperty(value = "用户生日", dataType = "Date", example = "2021-01-01", required = true)
    private Date userBirth;

    /**
     * 用户联系方式
     */
    @ApiModelProperty(value = "用户联系方式", dataType = "String", example = "10101010", required = true)
    private String userPhone;

    /**
     * 用户联系方式 email
     */
    @ApiModelProperty(value = "用户联系方式email", dataType = "String", example = "10101010", required = true)
    private String userEmail;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
