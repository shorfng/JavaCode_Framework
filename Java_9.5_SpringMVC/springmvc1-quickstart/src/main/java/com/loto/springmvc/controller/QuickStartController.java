package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-04 22:32</p>
 * <p>PageName：QuickStartController.java</p>
 * <p>Function：快速搭建</p>
 */

@Controller
@RequestMapping("/quickstart")
public class QuickStartController {
    /**
     * 快速搭建<p>
     * http://localhost:8080/quickstart/handler
     */
    @RequestMapping("/handler")
    public ModelAndView Handler() {
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
