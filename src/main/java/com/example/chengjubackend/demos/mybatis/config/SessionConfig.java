//package com.example.chengjubackend.demos.mybatis.config;
//
//import com.example.chengjubackend.demos.mybatis.api.enums.HttpCode;
//import com.example.chengjubackend.demos.mybatis.api.result.ResultDO;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * session设置类
// * @author Jilin He
// * @date 2020.01.19
// */
//
//@Configuration
//public class SessionConfig implements WebMvcConfigurer{
//
//    /**
//     * 设置需要拦截的网页 - 除了登录页、登出页、swagger页面
//     * @param registry
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        List<String> list = new ArrayList<>();
////        list.add("/register");
//        list.add("/mine");
//        list.add("/mine/**");
//        list.add("/events");
//        list.add("/events/**");
//        list.add("/launch");
//        registry.addInterceptor(new SecurityInterceptor())
//                .addPathPatterns(list);
//    }
//
//
//    /**
//     * 拦截类
//     * 引入Jackson，将结果类封装成JSON对象
//     */
//    @Configuration
//    public class SecurityInterceptor implements HandlerInterceptor{
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//            HttpSession session = request.getSession();
//            if (session.getAttribute(session.getId()) != null){
//                return true;
//            }
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode jsonNode = objectMapper.valueToTree(new ResultDO(HttpCode.UNAUTHORIZED.getCode(), "Please Login First!"));
//            response.getWriter().write(jsonNode.toString());
//            return false;
//        }
//    }
//}
