package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.service.ParticipateDOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RestController
public class ParticipateDOController {

    private final static Logger logger = LoggerFactory.getLogger(ParticipateDOController.class);

    @Autowired
    private ParticipateDOService participateDOService;

    @RequestMapping(value="/events/participate", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addParticipateEvent(@RequestBody ParticipateDO participateDO) {
//        System.out.println(participateDO.toString());
        try {
            return participateDOService.insertParticipate(participateDO);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/participate", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getParticipateEvents(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            return participateDOService.getParticipatedEvents(userId);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/participate/delete", method= RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteParticipateEvent(@RequestParam("eventId") Integer eventId,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            return participateDOService.deleteParticipated(eventId, userId);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

}
