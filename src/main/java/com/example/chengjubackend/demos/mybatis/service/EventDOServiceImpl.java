package com.example.chengjubackend.demos.mybatis.service;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.mapper.EventDOMapper;
import com.example.chengjubackend.demos.mybatis.mapper.UserDOMapper;
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

    @Autowired
    private EventDOMapper eventMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @Override
    public ResultDO getAllEvents() {
        List<EventDO> list = eventMapper.getAllEvents();
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "目前没有活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "已展示全部活动。", list);
    }

    @Override
    public ResultDO getEventsByTime() {
        List<EventDO> list = eventMapper.getEventsByTime();
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "目前没有活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "已按时间顺序展示全部活动。", list);
    }

    @Override
    public ResultDO searchEventByName(String name) {
        if (name == null) {
            return new EventDOServiceImpl().getAllEvents();
        }
        List<EventDO> list = eventMapper.searchEventByName(name);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "未搜索到相关活动。");
        }
        return new ResultDO((HttpCode.SUCCESS.getCode()), "以下是搜索到的相关活动。", list);
    }

    @Override
    public ResultDO getEventByUserId(Integer userId) {
        if (userId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的学号不能为空或0");
        }
        List<EventDO> list = eventMapper.getEventByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return new ResultDO(HttpCode.FAIL.getCode(), "该用户未发布活动。");
        }
        return new ResultDO((HttpCode.SUCCESS.getCode()), "以下是该用户发布的活动。", list);
    }

    @Override
    public ResultDO getEventByEventId(Integer eventId) {
        if (eventId == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "输入的活动序号不能为空或0");
        }
        EventDO eventDO = eventMapper.getEventByEventId(eventId);
        if (eventDO == null) {
            return new ResultDO(HttpCode.FAIL.getCode(), "无该活动。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "根据ID查找活动成功。", eventDO);
    }

    @Override
    public ResultDO insert(EventDO eventDO) {
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
        return new ResultDO(HttpCode.SUCCESS.getCode(), "添加成功", influenceLines);
    }

    @Override
    public ResultDO delete(Integer eventId) {
        int influenceLines = eventMapper.deleteEvent(eventId);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "删除失败。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "删除成功", influenceLines);
    }

    @Override
    public ResultDO update(EventDO eventDO) {
        int influenceLines = eventMapper.updateEvent(eventDO);
        if (influenceLines <= 0) {
            return new ResultDO(HttpCode.FAIL.getCode(), "更新失败。");
        }
        return new ResultDO(HttpCode.SUCCESS.getCode(), "更新成功", influenceLines);
    }
}
