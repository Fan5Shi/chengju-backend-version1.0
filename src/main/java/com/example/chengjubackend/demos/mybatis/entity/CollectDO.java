package com.example.chengjubackend.demos.mybatis.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 活动收藏类，记录用户和活动之间的收藏关系
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "活动收藏类", description = "记录用户和活动之间的收藏关系")
public class CollectDO implements Serializable {

    /**
     * ID，主键
     */
    private Integer id;

    /**
     * 活动序号
     */
    @ApiModelProperty(value = "活动序号", dataType = "int", example = "01", required = true)
    private Integer eventId;

    /**
     * 学号/用户序号
     */
    @ApiModelProperty(value = "学号/用户序号", dataType = "int", example = "20170001", required = true)
    private Integer userId;

    /**
     * 标志数据是否可删
     * 默认为ENABLE，表示数据可见
     * DISABLE，表示数据不可见，为可删除状态
     */
    private String isDelete = "ENABLE";

    public CollectDO(Integer eventId, Integer userId) {
        this.eventId = eventId;
        this.userId = userId;
    }
}
