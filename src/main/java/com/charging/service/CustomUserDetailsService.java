package com.charging.service;

import cn.hutool.core.util.ObjectUtil;
import com.charging.entity.UserinfoEntity;
import com.charging.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserinfoMapper userinfoMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserinfoEntity userinfoEntity = userinfoMapper.selectById(userId);
        if (ObjectUtil.isNull(userinfoEntity)) {
            return null;
        } else {
            return new User(userId, "", new HashSet<>());
        }
    }
}