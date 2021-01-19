package com.example.chengjubackend.demos.mybatis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动类，记录活动的基本信息
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "活动类", description = "记录活动的基本信息")
public class EventDO implements Serializable {

    /**
     * 活动序号
     */
    private Integer eventId;

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称", dataType = "String", example = "Hiking", required = true)
    private String eventName;

    /**
     * 活动地点
     */
    @ApiModelProperty(value = "活动地点", dataType = "String", example = "Mount EMei", required = true)
    private String eventAddr;

    /**
     * 活动时间，格式为"yyyy-MM-dd"
     */
    @ApiModelProperty(value = "活动时间", dataType = "Date", example = "2021-01-01", required = true)
    private Date eventTime;

    /**
     * 活动描述
     */
    @ApiModelProperty(value = "活动描述", dataType = "String", example = "This is an event to ...", required = true)
    private String eventDes;

    /**
     * 学号/用户序号
     */
    @ApiModelProperty(value = "学号/用户序号", dataType = "int", example = "20170001", required = true)
    private Integer userId;

}
