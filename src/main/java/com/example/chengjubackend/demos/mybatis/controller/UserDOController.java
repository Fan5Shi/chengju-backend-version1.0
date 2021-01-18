package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.service.UserDOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@RestController
public class UserDOController {

    private final static Logger logger = LoggerFactory.getLogger(UserDOController.class);

    @Autowired
    private UserDOService userService;

    /**
     * 必须使用JSON格式
     * @param userDO
     * @return
     */
    @RequestMapping(value="/register", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO addUser(@RequestBody UserDO userDO) {
        System.out.println(userDO.toString());
        try {
            ResultDO resultDO = userService.insert(userDO);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO login(@RequestParam("userId") Integer userId,
                          @RequestParam("password") String password,
                          HttpServletRequest request) {
        try {
            ResultDO resultDO = userService.login(userId, password);

            if (resultDO.getCode() == HttpCode.SUCCESS.getCode()) {
                HttpSession session = request.getSession();
                session.setAttribute(session.getId(), userId);
            }

            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute(session.getId());
        return "user logout success";
    }

    @RequestMapping(value="/mine", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getUserInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute(session.getId());
        try {
            ResultDO resultDO = userService.findUserById(userId);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/update", method= RequestMethod.PUT)
    @ResponseBody
    public ResultDO updateUser(@RequestBody UserDO userDO) {
        try {
            ResultDO resultDO = userService.update(userDO);
            return resultDO;
        } catch (Exception e) {
            logger.error("系统异常" + e);
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
