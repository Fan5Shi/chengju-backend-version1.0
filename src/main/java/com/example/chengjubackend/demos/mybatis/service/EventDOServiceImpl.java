package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.CollectDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.mapper.CollectDOMapper;
import com.example.chengjubackend.demos.mybatis.mapper.EventDOMapper;
import com.example.chengjubackend.demos.mybatis.mapper.ParticipateDOMapper;
import com.example.chengjubackend.demos.mybatis.mapper.UserDOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 活动Service的实现类
 * @author Jilin He
 * @date 2020.01.17
 */

@Service
public class EventDOServiceImpl implements EventDOService{

    private final static Logger logger = LoggerFactory.getLogger(EventDOServiceImpl.class);

    @Autowired
    private EventDOMapper eventMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private CollectDOMapper collectDOMapper;

    @Autowired
    private ParticipateDOMapper participateDOMapper;

    @Override
    public ResultDO getAllEvents() {
        List<EventDO> list = eventMapper.getAllEvents();
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "目前没有活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 已展示全部活动。", list);
    }

    @Override
    public ResultDO getEventsByTime() {
        List<EventDO> list = eventMapper.getEventsByTime();
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "目前没有活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 按时间顺序展示全部活动如下。", list);
    }

    @Override
    public ResultDO searchEventByName(String name) {
        logger.info("入参：" + name);
        if (name == null) {
            return new EventDOServiceImpl().getAllEvents();
        }
        List<EventDO> list = eventMapper.searchEventByName(name);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "未搜索到相关活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 以下是搜索到的相关活动。", list);
    }

    @Override
    public ResultDO getEventByUserId(Integer userId) {
        logger.info("入参：" + userId);
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0");
        }
        List<EventDO> list = eventMapper.getEventByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "该用户未发布活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 以下是该用户发布的活动。", list);
    }

    @Override
    public ResultDO getEventByEventId(Integer eventId) {
        logger.info("入参：" + eventId);
        if (eventId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的活动序号不能为空或0");
        }
        EventDO eventDO = eventMapper.getEventByEventId(eventId);
        if (eventDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "无该活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), HttpCode.SUCCESS.getMsg() + " 根据ID查找活动成功。", eventDO);
    }

    @Override
    public ResultDO insert(EventDO eventDO) {
        logger.info("入参：" + eventDO.toString());
        if (StringUtils.isEmpty(eventDO.getEventName())) {
            return new ResultDO(HttpCode.FAIL.getCode(), "名字不能为空。");
        }
        UserDO userDO = userDOMapper.findUserById(eventDO.getUserId());
        if (userDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "不存在该用户！");
        }
        int influenceLines = eventMapper.insertEvent(eventDO);
        System.out.println(influenceLines);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "添加失败。", influenceLines);
        }
        return new ResultDO(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg() + " 添加成功", influenceLines);
    }

    /**
     * 删除活动
     * 涉及到级联删除 活动收藏表 和 活动参与表
     * 先删除子表，后删除父表
     * 若子表删除成功，父表删除失败，需要对子表进行重新更新
     * 子表删除为标记删除
     * @param eventId 活动序号
     * @return 结果类
     */
    @Override
    public ResultDO delete(Integer eventId, Integer userId) {
        logger.info("入参：" + eventId);
        List<UserDO> listCol = collectDOMapper.getCollectedByEventID(eventId);
        List<UserDO> listPart = participateDOMapper.getParticipatedByEventID(eventId);
        if (!CollectionUtils.isEmpty(listCol) && !CollectionUtils.isEmpty(listPart)) {
            int partInfluenceLines = participateDOMapper.deleteCascadeParticipated(eventId);
            int colInfluenceLines = collectDOMapper.deleteCascadeCollected(eventId);
            if (partInfluenceLines <= 0 && colInfluenceLines <= 0) {
                return new ResultDO(HttpCode.FAIL.getCode(), "子表级联删除失败，活动删除失败。");
            }
        } else if (CollectionUtils.isEmpty(listCol)) {
            int partInfluenceLines = participateDOMapper.deleteCascadeParticipated(eventId);
            if (partInfluenceLines <= 0) {
                return new ResultDO(HttpCode.FAIL.getCode(), "参与表级联删除失败，活动删除失败。");
            }
        } else if (CollectionUtils.isEmpty(listPart)) {
            int colInfluenceLines = collectDOMapper.deleteCascadeCollected(eventId);
            if (colInfluenceLines <= 0) {
                return new ResultDO(HttpCode.FAIL.getCode(), "收藏表表级联删除失败，活动删除失败。");
            }
        }
        int influenceLines = eventMapper.deleteEvent(eventId, userId);
        if (influenceLines <= 0) {
            for(UserDO userDO: listCol) {
                collectDOMapper.insertCollect(new CollectDO(eventId, userDO.getUserId()));
            }
            for (UserDO userDO: listPart) {
                participateDOMapper.insertParticipate(new ParticipateDO(eventId, userDO.getUserId()));
            }
            return new ResultDO(HttpCode.FAIL.getCode(), "父表删除失败。");
        }
        return new ResultDO(HttpCode.DELETE.getCode(), HttpCode.DELETE.getMsg() + " 父表删除成功", influenceLines);
    }

    @Override
    public ResultDO update(EventDO eventDO) {
        logger.info("入参：" + eventDO);
        int influenceLines = eventMapper.updateEvent(eventDO);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "更新失败。");
        }
        return new ResultDO(HttpCode.CREATED.getCode(), HttpCode.CREATED.getMsg() + " 更新成功", influenceLines);
    }
}
