package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.CollectDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.service.CollectDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class CollectDOController{

    @Autowired
    private CollectDOService collectDOService;

    @RequestMapping(value="/events/collect", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addCollectEvent(@RequestBody CollectDO collectDO) {
        try {
            return collectDOService.insertCollect(collectDO);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/collect", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getCollectEvents(@RequestParam("userId") Integer userId) {
        try {
            return collectDOService.getCollectedEvents(userId);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/collect/delete", method= RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteParticipateEvent(@RequestParam("eventId") Integer eventId,
                                           @RequestParam("userId") Integer userId) {
        try {
            return collectDOService.deleteCollected(eventId, userId);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
