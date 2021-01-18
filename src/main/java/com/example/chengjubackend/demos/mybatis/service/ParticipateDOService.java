package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;

/**
 * 活动参与Service类
 * @author Jilin He
 * @date 2020.01.17
 */

public interface ParticipateDOService {

    /**
     * 根据学号/用户序号获取该用户参与的活动，返回结果类
     * @param userId 学号/用户序号
     * @return 结果类，包括响应码，附带信息，附带对象等
     */
    ResultDO getParticipatedEvents(Integer userId);

    /**
     * 参与活动，新增参与关系
     * @param participateDO 活动收参与体
     * @return 结果类
     */
    ResultDO insertParticipate(ParticipateDO participateDO);

    /**
     * 取消活动参与
     * @param eventId 活动序号
     * @param userId 学号/用户序号
     * @return 结果类
     */
    ResultDO deleteParticipated(Integer eventId, Integer userId);

    /**
     * 批量取消活动参与
     * 主要发生的场景是活动表活动被取消，活动参与表需要级联删除对应活动
     * @param eventId 活动序号
     * @return 结果类
     */
    ResultDO deleteCascadeParticipated(Integer eventId);

}
