package com.example.chengjubackend.demos.mybatis.api.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 响应结果类
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
@ApiModel(value = "响应结果类", description = "结果返回的接口")
public class ResultDO {

    /**
     * 响应码
     */
    @ApiModelProperty(value = "响应码", dataType = "int")
    private int code;

    /**
     * 附带信息
     */
    @ApiModelProperty(value = "附带信息", dataType = "String", notes = "当前返回类的附带信息")
    private String msg;

    /**
     * 附带对象
     */
    @ApiModelProperty(value = "附带对象", dataType = "Object")
    private Object data;

    public ResultDO() {
    }

    public ResultDO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultDO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
