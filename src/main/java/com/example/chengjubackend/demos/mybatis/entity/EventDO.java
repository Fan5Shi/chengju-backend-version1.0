package com.example.chengjubackend.demos.mybatis.entity;

import lombok.Data;
import java.util.Date;

/**
 * 活动类，记录活动的基本信息
 * @author Jilin He
 * @date 2020.01.17
 */

@Data
public class EventDO {

    /**
     * 活动序号
     */
    private Integer eventId;

    /**
     * 活动名称
     */
    private String eventName;

    /**
     * 活动地点
     */
    private String eventAddr;

    /**
     * 活动时间，格式为"yyyy-MM-dd"
     */
    private Date eventTime;

    /**
     * 活动描述
     */
    private String eventDes;

    /**
     * 学号/用户序号
     */
    private Integer userId;

    public EventDO() {
    }

    public EventDO(Integer eventId, String eventName, String eventAddr, Date eventTime, String eventDes, Integer userId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventAddr = eventAddr;
        this.eventTime = eventTime;
        this.eventDes = eventDes;
        this.userId = userId;
    }
}
