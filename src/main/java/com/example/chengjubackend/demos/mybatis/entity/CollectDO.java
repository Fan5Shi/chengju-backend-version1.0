package com.example.chengjubackend.demos.mybatis.entity;

import lombok.Data;

/**
 * 活动收藏类，记录用户和活动之间的收藏关系
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
public class CollectDO {

    /**
     * ID，主键
     */
    private Integer id;

    /**
     * 活动序号
     */
    private Integer eventId;

    /**
     * 学号/用户序号
     */
    private Integer userId;

}
