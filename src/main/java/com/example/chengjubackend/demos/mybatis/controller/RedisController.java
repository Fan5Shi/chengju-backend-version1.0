package com.example.chengjubackend.demos.mybatis.controller;


import com.example.chengjubackend.demos.mybatis.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * redis控制类
 * @author Jilin He
 * @date 2020.01.19
 */

@Controller
@Api(tags = "redis控制类")
public class RedisController {

    @Resource
    private RedisService redisService;

    @RequestMapping("/redis")
    public String redis() throws ParseException {
        redisService.set("name", "Oven");
        System.out.println((String) redisService.get("name"));
        redisService.remove("name");
        System.out.println((String) redisService.get("name"));
        redisService.set("age", 18);
        System.out.println((Integer) redisService.get("age"));

//        UserDO user = new UserDO();
//        user.setUserId(20178888);
//        user.setPassword("wswswswsws");
//        user.setUserName("Kevin");
//        user.setUserBirth(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
//                .parse("2020-06-29"));
//        user.setUserPhone("876876876");
//
//        redisService.set("user", user);
//        System.out.println((UserDO) redisService.get("user"));

        return "测试完毕";
    }

}
