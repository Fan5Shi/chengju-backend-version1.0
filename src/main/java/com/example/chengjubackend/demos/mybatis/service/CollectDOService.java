package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.CollectDO;

/**
 * 活动收藏Service类
 * @author Jilin He
 * @date 2020.01.17
 */

public interface CollectDOService {

    /**
     * 根据学号/用户序号获取该用户收藏的活动，返回结果类
     * @param userId 学号/用户序号
     * @return 结果类，包括响应码，附带信息，附带对象等
     */
    ResultDO getCollectedEvents(Integer userId);

    /**
     * 收藏活动，新增收藏关系
     * @param collectDO 活动收藏实体
     * @return 结果类
     */
    ResultDO insertCollect(CollectDO collectDO);

    /**
     * 取消活动收藏
     * @param eventId 活动序号
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO deleteCollected(Integer eventId, Integer userId);

}
