package com.example.chengjubackend.demos.mybatis.config;

import com.example.chengjubackend.demos.mybatis.entity.LoginUser;
import com.example.chengjubackend.demos.mybatis.mapper.LoginUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginUserMapper loginUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUser user = loginUserMapper.findByUserId(Integer.valueOf(username));

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }

        List<GrantedAuthority> auths = new ArrayList<>();

        auths.add(new SimpleGrantedAuthority("ROLE_" + user.getROLE()));

        return new User(String.valueOf(user.getUserId()), user.getPassword(), auths);
    }
}
