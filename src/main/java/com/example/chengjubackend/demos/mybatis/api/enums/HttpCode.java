package com.example.chengjubackend.demos.mybatis.api.enums;

/**
 * Http响应码-枚举类
 * @author Jilin He
 * @date 2020.01.17
 */

public enum HttpCode {

    /**
     * [GET] 服务器成功返回用户请求的数据
     */
    SUCCESS(200, "OK"),

    /**
     * [POST/PUT/PATCH] 用户新建或修改数据成功
     */
    CREATED(201, "CREATED"),

    /**
     * [DELETE] 用户删除数据成功
     */
    DELETE(204, "NO CONTENT"),

    /**
     * [*] 表示用户没有权限（令牌、用户名、密码错误）
     */
    UNAUTHORIZED(401, "UNAUTHORIZED"),

    /**
     * 无数据
     */
    FAIL(-1, "失败"),

    /**
     * [*] 服务器发生错误
     */
    EXCEPTION(500, "INTERNAL SERVER ERROR");

    /**
     * 响应码
     */
    private int code;

    /**
     * 附带信息
     */
    private String msg;

    HttpCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
