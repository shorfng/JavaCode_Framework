package com.loto.project.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author：蓝田_Loto
 * Date：2021-08-19 09:48
 * PageName：MyAuthenticationService.java
 * Function：自定义登录成功处理器、失败处理器
 */

@Service
public class MyAuthenticationService implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 登录成功后处理逻辑
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登录成功后继续处理......");

        // 重定向到index页面
        //redirectStrategy.sendRedirect(request, response, "/");

        Map result = new HashMap();
        result.put("code", HttpStatus.OK.value()); // 返回状态码 200 成功
        result.put("message", "登录成功");

        // 响应
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));  // writeValueAsString 转成 json
    }

    /**
     * 登录失败后处理逻辑
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("登录失败后继续处理......");

        // 重定向到login页面
        //redirectStrategy.sendRedirect(request, response, "/toLoginPage");

        Map result = new HashMap();
        result.put("code", HttpStatus.UNAUTHORIZED.value());  // 返回状态码 401
        result.put("message", exception.getMessage());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
