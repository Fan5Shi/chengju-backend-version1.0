package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.service.UserDOService;
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
 * 用户控制类
 * @author Jilin He
 * @date 2020.01.19
 */

@Component
@RestController
@Api(tags = "用户控制类")
public class UserDOController {

    private final static Logger logger = LoggerFactory.getLogger(UserDOController.class);

    @Autowired
    private UserDOService userService;

    /**
     * 必须使用JSON格式
     * @param userDO
     * @return
     */
    @ApiOperation(value="注册用户", notes="注册用户接口")
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

    @ApiOperation(value="登录", notes="登录接口")
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

    @ApiOperation(value="登出", notes="登出接口")
    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute(session.getId());
        return "user logout success";
    }

    @ApiOperation(value="个人主页", notes="个人主页接口")
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

    @ApiOperation(value="更新用户个人信息", notes="更新用户个人信息接口")
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
