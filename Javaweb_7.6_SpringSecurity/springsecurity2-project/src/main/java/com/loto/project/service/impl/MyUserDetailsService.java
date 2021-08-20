package com.loto.project.service.impl;

import com.loto.project.domain.User;
import com.loto.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Author：蓝田_Loto
 * Date：2021-08-18 15:53
 * PageName：MyUserDetailsService.java
 * Function：基于数据库完成认证
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    /**
     * 根据用户名查询用户
     *
     * @param username 前端传入的用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        User user = userService.findByUsername(username);

        // 当表中没有对应用户
        if (user == null) {
            throw new UsernameNotFoundException("用户没有找到," + username);
        }

        // 当表中有对应用户
        // 权限的集合
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equalsIgnoreCase(user.getUsername())) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_PRODUCT"));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username,
                "{bcrypt}" + user.getPassword(), // noop不使用密码加密，bcrypt使用加密算法
                true,            // 用户是否启用（true表示启动）
                true,   // 用户是否过期（true表示未过期）
                true, // 用户凭证是否过期（true表示未过期）
                true,   // 用户是否锁定（true表示未锁定）
                authorities);           // 权限的集合

        return userDetails;
    }

    /**
     * 使用 bcrypt 加密算法
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println("加密后的密文：" + encode);
    }
}
