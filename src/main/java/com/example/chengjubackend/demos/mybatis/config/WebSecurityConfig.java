package com.example.chengjubackend.demos.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 会拦截注解@PreAuthoriz注解的配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        // 基于内存的方式，创建管理员账号：admin/ 12345678
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("123456")
//                .roles("admin");
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login", "/events").permitAll()
                .antMatchers("/participant/**").hasRole("USER")
                .antMatchers("/launcher/**").hasRole("LAUNCHER")
                .antMatchers("/reviewer/**").hasRole("REVIEWER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().csrf().disable();

        http.formLogin();

        http.logout();
    }

    @Bean
    PasswordEncoder password() {
        return new BCryptPasswordEncoder();
    }

}
