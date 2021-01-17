package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.EventDO;
import com.example.chengjubackend.demos.mybatis.service.EventDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@Component
@RestController
public class EventDOController {

    @Autowired
    private EventDOService eventService;

    @RequestMapping(value="/events", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getAllEvents() {
        try {
            ResultDO resultDO = eventService.getAllEvents();
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/events/bytime", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getEventsByTime() {
        try {
            ResultDO resultDO = eventService.getEventsByTime();
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/events/search", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO searchByName(@RequestParam(value="name") String name) {
        try {
            ResultDO resultDO = eventService.searchEventByName(name);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/events/specific", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getEventById(@RequestParam(value="eventId") Integer eventId) {
        System.out.println(eventId.toString());
        try {
            ResultDO resultDO = eventService.getEventByEventId(eventId);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/launch", method=RequestMethod.POST)
    @ResponseBody
    public ResultDO launchEvent(@RequestBody EventDO eventDO) {

        ResultDO resultDO = new ResultDO();
        try {
            resultDO = eventService.insert(eventDO);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常", resultDO);
        }
    }

    @RequestMapping(value="/mine/launched", method=RequestMethod.GET)
    @ResponseBody
    public ResultDO getLaunchedEvents(@RequestParam(value="userId") Integer userId) {
        try {
            ResultDO resultDO = eventService.getEventByUserId(userId);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/launched/delete", method=RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteLaunchedEvent(@RequestParam(value="eventId") Integer eventId) {
        try {
            ResultDO resultDO = eventService.delete(eventId);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/launched/update", method=RequestMethod.PUT)
    @ResponseBody
    public ResultDO updateLaunchedEvent(@RequestBody EventDO eventDO) {
        try {
            ResultDO resultDO = eventService.update(eventDO);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
