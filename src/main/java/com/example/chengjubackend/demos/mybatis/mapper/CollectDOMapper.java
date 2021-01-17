package com.example.chengjubackend.demos.mybatis.mapper;

import com.example.chengjubackend.demos.mybatis.entity.CollectDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 活动收藏Mapper类
 * @author Jilin He
 * @date 2020.01.17
 */

@Mapper
public interface CollectDOMapper {

    /**
     * 根据学号/用户序号获取该用户收藏的所有活动
     * 只显示活动名称、活动时间、活动地点
     * @param userId 学号/用户序号
     * @return 该用户收藏的所有活动
     */
    List<EventDO> getCollectedEvents(Integer userId);

    /**
     * 收藏活动，需要向 活动收藏表 中加入对应的活动序号和学号
     * @param collectDO 活动收藏类
     * @return 被影响的行数
     */
    Integer insertCollect(CollectDO collectDO);

    /**
     * 取消活动收藏
     * @param eventId 活动序号
     * @param userId 学号/用户序号
     * @return 被影响的行数
     */
    Integer deleteCollected(@Param("eventId") Integer eventId, @Param("userId") Integer userId);

}
