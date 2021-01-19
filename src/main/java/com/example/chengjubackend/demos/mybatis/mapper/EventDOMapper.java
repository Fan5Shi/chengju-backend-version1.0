package com.example.chengjubackend.demos.mybatis.mapper;

import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 活动Mapper类
 * @author Jilin He
 * @date 2020.01.17
 */

@Mapper
public interface EventDOMapper {

    /**
     * 获取数据库里全部的活动用于活动列表处的展示
     * 后期可以改进为展示部分活动，收到加载信息后重
     * 获取
     * @return 一系列活动信息
     */
    List<EventDO> getAllEvents();

    /**
     * 按照活动发生的时间顺序获取数据库里的活动
     * @return 一系列按照时间顺序排列的活动信息
     */
    List<EventDO> getEventsByTime();

    /**
     * 根据提供的字符串搜索相关的活动
     * @param name 搜索的字符串
     * @return 一系列搜索相关的活动
     */
    List<EventDO> searchEventByName(String name);

    /**
     * 根据学号获取该用户发布的活动
     * @param userId 学号/用户序号
     * @return 一系列活动信息
     */
    List<EventDO> getEventByUserId(Integer userId);

    /**
     * 根据活动序号获取该活动的详细情况
     * @param eventId 活动序号
     * @return 该活动的详细信息
     */
    EventDO getEventByEventId(Integer eventId);

    /**
     * 发布活动
     * @param eventDO 活动实体
     * @return 被影响的行数
     */
    int insertEvent(EventDO eventDO);

    /**
     * 取消发布的活动
     * @param eventId 活动序号
     * @return 被影响的行数
     */
    int deleteEvent(@Param("eventId") Integer eventId, @Param("userId") Integer userId);

    /**
     * 更新已发布的活动
     * @param eventDO 活动实体
     * @return 被影响的行数
     */
    int updateEvent(EventDO eventDO);
}
