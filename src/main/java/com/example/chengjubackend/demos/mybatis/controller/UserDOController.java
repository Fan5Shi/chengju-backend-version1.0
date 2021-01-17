package com.example.chengjubackend.demos.mybatis.controller;

import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
import com.example.chengjubackend.demos.mybatis.entity.UserDO;
import com.example.chengjubackend.demos.mybatis.service.UserDOService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class UserDOController {

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
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    @ResponseBody
    public ResultDO login(@RequestParam("userId") Integer userId,
                             @RequestParam("password") String password) {
        try {
            ResultDO resultDO = userService.login(userId, password);
            return resultDO;
        } catch (Exception e) {
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }

    @RequestMapping(value="/mine/{userId:\\d+}", method= RequestMethod.GET)
    @ResponseBody
    public ResultDO getUserInfo(@PathVariable("userId") Integer userId) {
        try {
            ResultDO resultDO = userService.findUserById(userId);
            return resultDO;
        } catch (Exception e) {
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
            return new ResultDO(HttpCode.EXCEPTION.getCode(), "系统异常");
        }
    }
}
