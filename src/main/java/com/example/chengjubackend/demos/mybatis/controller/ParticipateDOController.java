package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.ParticipateDO;
import com.example.chengjubackend.demos.mybatis.service.ParticipateDOService;
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
 * 活动参与控制类
 * @author Jilin He
 * @date 2020.01.19
 */

@Component
@RestController
@Api(tags = "活动参与控制类")
public class ParticipateDOController {

    private final static Logger logger = LoggerFactory.getLogger(ParticipateDOController.class);

    @Autowired
    private ParticipateDOService participateDOService;

    @ApiOperation(value="报名活动", notes="报名活动接口")
    @RequestMapping(value="/events/{eventId}/participate", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addParticipant(@PathVariable(value="eventId") int eventId,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        ParticipateDO participateDO = new ParticipateDO(eventId, userId);
        try {
            return participateDOService.insertParticipate(participateDO);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @ApiOperation(value="用户已报名的活动", notes="用户已报名活动接口")
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

    @ApiOperation(value="取消报名", notes="取消报名接口")
    @RequestMapping(value="/mine/participate", method= RequestMethod.DELETE)
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
