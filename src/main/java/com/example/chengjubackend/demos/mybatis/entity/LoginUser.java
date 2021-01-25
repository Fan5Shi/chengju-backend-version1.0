package com.example.chengjubackend.demos.mybatis.entity;

import lombok.Data;

@Data
public class LoginUser {

    private int id;

    private int userId;

    private String password;

    private String ROLE = "USER";
}
