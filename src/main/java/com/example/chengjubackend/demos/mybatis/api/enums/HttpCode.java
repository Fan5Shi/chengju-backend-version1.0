package com.example.chengjubackend.demos.mybatis.api.enums;

/**
 * Http响应码-枚举类
 * @author Jilin He
 * @date 2020.01.17
 */

public enum HttpCode {

    /**
     * 成功且有数据
     */
    SUCCESS(1, "成功"),

    /**
     * 无数据
     */
    FAIL(-1, "失败"),

    /**
     * 系统内异常
     */
    EXCEPTION(500, "系统异常");

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
