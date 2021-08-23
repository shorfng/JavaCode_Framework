package com.loto.project.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 自定义bean授权
 */
@Component
public class MyAuthorizationService {
    /**
     * 检查用户是否有权限
     *
     * @param authentication 认证信息
     * @param request        请求对象
     * @return
     */
    public boolean check(Authentication authentication, HttpServletRequest request) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();

        // 获取登录用户名
        String username = principal.getUsername();

        // 获取用户权限的集合
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) principal.getAuthorities();

        // 如果用户名为 admin 直接返回true
        if ("admin".equalsIgnoreCase(username)) {
            return true;
        } else {
            // 获取请求路径
            String requestURI = request.getRequestURI();

            // 循环判断用户的权限集合是否包含 ROLE_ADMIN
            if (requestURI.contains("/user")) {
                for (GrantedAuthority authority : authorities) {
                    if ("ROLE_ADMIN".equals(authority.getAuthority())) {
                        return true;
                    }
                }
            }

            // 循环判断用户的权限集合是否包含 ROLE_PRODUCT
            if (requestURI.contains("/product")) {
                for (GrantedAuthority authority : authorities) {
                    if ("ROLE_PRODUCT".equals(authority.getAuthority())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 检查ID是否大于10
     *
     * @param authentication 认证信息
     * @param request        请求对象
     * @return
     */
    public boolean check(Authentication authentication, HttpServletRequest request, Integer id) {
        if (id > 10) {
            return false;
        }
        return true;
    }
}
