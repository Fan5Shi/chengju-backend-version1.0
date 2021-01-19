package com.example.chengjubackend.demos.mybatis.api.scheduling;

import com.example.chengjubackend.demos.mybatis.mapper.CollectDOMapper;
import com.example.chengjubackend.demos.mybatis.mapper.ParticipateDOMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务，负责清理 活动参与表和活动收藏表 中DISABLE的数据行
 * @author Jilin He
 * @date 2020.01.19
 */

@Component
public class TimedTask {

    @Autowired
    private CollectDOMapper collectDOMapper;

    @Autowired
    private ParticipateDOMapper participateDOMapper;

    /**
     * 清理 活动参与表和活动收藏表 中DISABLE的数据行
     * 每隔20秒启动定时任务
     */
    @Scheduled(cron = "0/20 * * * * ? ")
    public void cleanColPartTable() {
        LoggerFactory.getLogger("com.example.chengjubackend.demos.mybatis.api.scheduling.TimedTask.cleanColPartTable")
                .info("----启动定时任务：删除活动参与表和活动收藏表中被标记为disable的行----");
        collectDOMapper.patchDelete();
        participateDOMapper.patchDelete();
    }

}
