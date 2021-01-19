package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.CollectDO;
import com.example.chengjubackend.demos.mybatis.service.CollectDOService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 活动收藏控制类
 * @author Jilin He
 * @date 2020.01.19
 */

@Component
@RestController
@Api(tags = "活动收藏控制类")
public class CollectDOController{

    private final static Logger logger = LoggerFactory.getLogger(CollectDOController.class);

    @Autowired
    private CollectDOService collectDOService;

    @RequestMapping(value="/events/{eventId}/collect", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addCollecter(@PathVariable(value="eventId") int eventId,
                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        CollectDO collectDO = new CollectDO(eventId, userId);
        try {
            return collectDOService.insertCollect(collectDO);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/collect", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getCollectEvents(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            return collectDOService.getCollectedEvents(userId);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/collect", method= RequestMethod.DELETE)
    @ResponseBody
    public ResultDO deleteParticipateEvent(@RequestParam("eventId") Integer eventId,
                                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            return collectDOService.deleteCollected(eventId, userId);
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
