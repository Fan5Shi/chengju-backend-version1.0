package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.service.EventDOService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 活动控制类
 * @author Jilin He
 * @date 2020.01.19
 */

@Component
@RestController
@Api(tags = "活动控制类")
public class EventDOController {

    private final static Logger logger = LoggerFactory.getLogger(EventDOController.class);

    @Autowired
    private EventDOService eventService;

    @ApiOperation(value="活动展示列表", notes="活动展示接口")
    @RequestMapping(value="/events", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getAllEvents() {
        try {
            ResultDO resultDO = eventService.getAllEvents();
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="活动按时间展示", notes="活动展示接口 - 按时间")
    @RequestMapping(value="/events/bytime", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getEventsByTime() {
        try {
            ResultDO resultDO = eventService.getEventsByTime();
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="活动搜索", notes="活动搜索接口")
    @RequestMapping(value="/events/search", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO searchByName(@RequestParam(value="name") String name) {
        try {
            ResultDO resultDO = eventService.searchEventByName(name);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="活动详情", notes="活动详情接口")
    @RequestMapping(value="/events/{eventId}", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getEventById(@PathVariable(value="eventId") Integer eventId) {
        System.out.println(eventId.toString());
        try {
            ResultDO resultDO = eventService.getEventByEventId(eventId);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="发布活动", notes="发布活动接口")
    @RequestMapping(value="/launch", method=RequestMethod.POST)
    @ResponseBody
    public ResultDO launchEvent(@RequestBody EventDO eventDO) {

        ResultDO resultDO = new ResultDO();
        try {
            resultDO = eventService.insert(eventDO);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常", resultDO);
        }
    }

    @ApiOperation(value="用户已发布活动", notes="用户已发布活动接口")
    @RequestMapping(value="/mine/launched", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getLaunchedEvents(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            ResultDO resultDO = eventService.getEventByUserId(userId);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="取消发布", notes="取消发布接口")
    @RequestMapping(value="/mine/launched", method=RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteLaunchedEvent(@RequestParam(value="eventId") Integer eventId,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            ResultDO resultDO = eventService.delete(eventId, userId);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="更新活动内容", notes="更新活动接口")
    @RequestMapping(value="/mine/launched/update", method=RequestMethod.PUT)
    @ResponseBody
    public ResultDO updateLaunchedEvent(@RequestBody EventDO eventDO) {
        try {
            ResultDO resultDO = eventService.update(eventDO);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
