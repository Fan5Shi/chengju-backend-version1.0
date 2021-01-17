package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;

/**
 * 活动Service类
 * @author Jilin He
 * @date 2020.01.17
 */

public interface EventDOService {

    /**
     * 获取数据库里全部的活动
     * @return 结果类，包括响应码、附带信息、附带实体等
     */
    ResultDO getAllEvents();

    /**
     * 按照活动发生的时间顺序获取数据库里的活动
     * @return 结果类
     */
    ResultDO getEventsByTime();

    /**
     * 根据提供的字符串搜索相关的活动
     * @param name 搜索的字符串
     * @return 结果类
     */
    ResultDO searchEventByName(String name);

    /**
     * 根据学号获取该用户发布的活动
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO getEventByUserId(Integer userId);

    /**
     * 根据活动序号获取该活动的详细情况
     * @param eventId 活动序号
     * @return 结果类
     */
    ResultDO getEventByEventId(Integer eventId);

    /**
     * 发布活动
     * @param eventDO 活动实体
     * @return 结果类
     */
    ResultDO insert(EventDO eventDO);

    /**
     * 取消发布的活动
     * @param eventId 活动序号
     * @return 结果类
     */
    ResultDO delete(Integer eventId);

    /**
     * 更新已发布的活动
     * @param eventDO 活动实体
     * @return 结果类
     */
    ResultDO update(EventDO eventDO);
}
