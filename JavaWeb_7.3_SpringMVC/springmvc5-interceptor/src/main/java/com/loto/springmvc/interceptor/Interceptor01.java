package com.loto.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-05 22:43</p>
 * <p>PageName：Interceptor01.java</p>
 * <p>Function：自定义 springmvc 拦截器 </p>
 */

public class Interceptor01 implements HandlerInterceptor {
    /**
     * 在 handler 方法业务逻辑执行之前执行（可做权限校验）
     * @return 返回值boolean代表是否放行，true代表放行，false代表中止
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle 01 ......");
        return true;
    }

    /**
     * 在 handler 方法业务逻辑执行之后尚未跳转页面时执行
     * @param modelAndView 封装了视图和数据，此时尚未跳转页面，可以在这里针对返回的数据和视图信息进行修改
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle 01 ......");
    }

    /**
     * 页面已经跳转渲染完毕之后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 01 ......");
    }
}

