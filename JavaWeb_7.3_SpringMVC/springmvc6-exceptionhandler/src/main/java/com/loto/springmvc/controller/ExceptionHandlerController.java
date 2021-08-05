package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-05 23:57</p>
 * <p>PageName：ExceptionHandlerController.java</p>
 * <p>Function：</p>
 */

@Controller
@RequestMapping("exceptionhandle")
public class ExceptionHandlerController {
    /**
     * SpringMVC 异常处理器（在这里只会对当前 controller 类生效）
     */
    //@ExceptionHandler(ArithmeticException.class)
    //public void handleException(ArithmeticException exception, HttpServletResponse response) {
    //    // 异常处理逻辑
    //    try {
    //        response.getWriter().write(exception.getMessage());
    //    } catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    /**
     * 快速搭建<p>
     * http://localhost:8080/exceptionhandle/handler
     */
    @RequestMapping("/handler")
    public ModelAndView Handler() {
        // 模拟异常
        int i = 1 / 0;

        // 服务器时间
        Date date = new Date();

        // ModelAndView：封装了数据和页面信息的模型
        ModelAndView modelAndView = new ModelAndView();

        // addObject：向请求域添加属性值，相当于 request.setAttribute("date",date);
        modelAndView.addObject("date", date);

        // 视图信息：封装跳转的页面信息
        modelAndView.setViewName("success");  // 逻辑视图名
        //modelAndView.setViewName("/WEB-INF/jsp/success.jsp");  // 物理视图名

        // 返回服务器时间到前端页面
        return modelAndView;
    }

}
