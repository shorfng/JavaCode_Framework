package com.loto.project.filter;

import com.loto.project.controller.ValidateCodeController;
import com.loto.project.exception.ValidateCodeException;
import com.loto.project.service.impl.MyAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author：蓝田_Loto
 * Date：2021-08-19 15:37
 * PageName：ValidateCodeFilter.java
 * Function：
 */

@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Autowired
    MyAuthenticationService myAuthenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 判断是否是登录请求
        if (request.getRequestURI().equals("/login") && request.getMethod().equalsIgnoreCase("post")) {
            String imageCode = request.getParameter("imageCode");
            System.out.println("填入的验证码：" + imageCode);

            // 验证流程
            try {
                validate(request, imageCode);
            } catch (ValidateCodeException e) {
                // 验证码填写错误，则走登录失败的逻辑
                myAuthenticationService.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 如果不是登录请求直接放行
        filterChain.doFilter(request, response);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 验证码的验证流程
     */
    private void validate(HttpServletRequest request, String imageCode) {
        // 从redis中获取验证码
        String redisKey = ValidateCodeController.REDIS_KEY_IMAGE_CODE + "-" + request.getRemoteAddr();
        String redisImageCode = stringRedisTemplate.boundValueOps(redisKey).get();

        // 验证码的判断
        if (!StringUtils.hasText(imageCode)) {
            throw new ValidateCodeException("验证码的值不能为空!");
        }

        if (redisImageCode == null) {
            throw new ValidateCodeException("验证码已过期!");
        }

        if (!redisImageCode.equals(imageCode)) {
            throw new ValidateCodeException("验证码不正确!");
        }

        // 从redis中删除验证码
        stringRedisTemplate.delete(redisKey);
    }
}
