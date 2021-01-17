package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.service.ParticipateDOService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class ParticipateDOController {

    @Autowired
    private ParticipateDOService participateDOService;

    @RequestMapping(value="/events/participate", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addParticipateEvent(@RequestBody ParticipateDO participateDO) {
//        System.out.println(participateDO.toString());
        try {
            return participateDOService.insertParticipate(participateDO);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/participate", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getParticipateEvents(@RequestParam("userId") Integer userId) {
        try {
            return participateDOService.getParticipatedEvents(userId);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/participate/delete", method= RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteParticipateEvent(@RequestParam("eventId") Integer eventId,
                                           @RequestParam("userId") Integer userId) {
        try {
            return participateDOService.deleteParticipated(eventId, userId);
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

}
