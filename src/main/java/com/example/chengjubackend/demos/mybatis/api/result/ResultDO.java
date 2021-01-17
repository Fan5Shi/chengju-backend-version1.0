package com.example.chengjubackend.demos.mybatis.api.result;

import lombok.Data;

/**
 * 响应结果类
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
public class ResultDO {

    /**
     * 系统名称
     */
    private String system;

    /**
     * 响应码
     */
    private int code;

    /**
     * 附带信息
     */
    private String msg;

    /**
     * 附带对象
     */
    private Object data;

    public ResultDO() {
    }

    public ResultDO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDO(String system, int code, String msg, Object data) {
        this.system = system;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultDO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
