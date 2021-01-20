package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.CollectDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.mapper.CollectDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 活动收藏Service的实现类
 * @author Jilin He
 * @date 2020.01.17
 */

@Service
public class CollectDOServiceImpl implements CollectDOService {

    private final static Logger logger = LoggerFactory.getLogger(CollectDOServiceImpl.class);

    @Autowired
    private CollectDOMapper collectDOMapper;

    @Override
    public ResultDO getCollectedEvents(Integer userId) {
        logger.info("入参：" + userId);
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0");
        }
        List<EventDO> list = collectDOMapper.getCollectedEvents(userId);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "该用户未收藏任何活动。");
        }
        return new ResultDO((HttpCode.SUCCESS.getCode()), HttpCode.SUCCESS.getMsg() + " 以下是该用户收藏的活动。", list);
    }

    @Override
    public ResultDO insertCollect(CollectDO collectDO) {
        logger.info("入参：" + collectDO.toString());
        int influenceLines = collectDOMapper.insertCollect(collectDO);
        System.out.println(influenceLines);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "收藏添加失败。", influenceLines);
        }
        return new ResultDO(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg() + " 收藏添加成功", influenceLines);
    }

    @Override
    public ResultDO deleteCollected(Integer eventId, Integer userId) {
        logger.info("入参：userId - " + userId + "；eventId - " + eventId);
        int influenceLines = collectDOMapper.deleteCollected(eventId, userId);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "收藏删除失败。");
        }
        return new ResultDO(HttpCode.DELETE.getCode(), HttpCode.DELETE.getMsg() + " 收藏删除成功", influenceLines);
    }

    @Override
    public ResultDO deleteCascadeCollected(Integer eventId) {
        logger.info("入参：" + eventId);
        int influenceLines = collectDOMapper.deleteCascadeCollected(eventId);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "级联删除失败。");
        }
        return new ResultDO(HttpCode.DELETE.getCode(), HttpCode.DELETE.getMsg() + " 级联删除成功", influenceLines);
    }
}
