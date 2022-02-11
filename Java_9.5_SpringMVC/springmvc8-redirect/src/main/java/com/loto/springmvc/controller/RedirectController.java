package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-06 0:11</p>
 * <p>PageName：RedirectController.java</p>
 * <p>Function：基于 Flash 属性的跨重定向请求数据传递（SpringMVC 重定向时参数传递丢失值的问题）</p>
 */

@Controller
@RequestMapping("redirect")
public class RedirectController {
    // 转发：url不会变，参数也不会丢失，一个请求
    // A 找 B 借钱400，B没有钱但是悄悄的找到C借了400块钱给A

    // 重定向：url会变，参数会丢失需要重新携带参数，两个请求
    // A 找 B 借钱400，B 说我没有钱，你找别人借去，那么A 又带着400块的借钱需求找到C

    /**
     * http://localhost:8080/redirect/handle?name=td
     */
    @RequestMapping("/handle")
    public String handleRedirect(String name, RedirectAttributes redirectAttributes) {
        // 重定向后，name 的值丢失，为空
        // return "redirect:handler01";

        // 方式1：拼接参数（安全性、参数长度都有局限）
        //return "redirect:handler01?name=" + name;

        // 方式2：addFlashAttribute 方法设置了一个 flash 类型属性，该属性会被暂存到 session 中，在跳转到页面之后该属性销毁
        redirectAttributes.addFlashAttribute("name", name);
        return "redirect:handler01";
    }

    /**
     * http://localhost:8080/redirect/handler01
     */
    @RequestMapping("/handler01")
    public ModelAndView Handler01(@ModelAttribute("name") String name) {
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
