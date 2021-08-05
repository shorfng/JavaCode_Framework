package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-08-05 20:13</p>
 * <p>PageName：RestfulController.java</p>
 * <p>Function：</p>
 */

@Controller
@RequestMapping("restful")
public class RestfulController {
    /**
     * get 查询请求：/restful/handle/15
     */
    @RequestMapping(value = "/handle/{id}", method = {RequestMethod.GET})
    public ModelAndView handleGet(@PathVariable("id") Integer id) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * post 新增请求：/restful/handle
     */
    @RequestMapping(value = "/handle", method = {RequestMethod.POST})
    public ModelAndView handlePost(String username) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * put 修改请求：/restful/handle/15/td
     */
    @RequestMapping(value = "/handle/{id}/{name}", method = {RequestMethod.PUT})
    public ModelAndView handlePut(@PathVariable("id") Integer id, @PathVariable("name") String username) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * delete 删除请求：/restful/handle/15
     */
    @RequestMapping(value = "/handle/{id}", method = {RequestMethod.DELETE})
    public ModelAndView handleDelete(@PathVariable("id") Integer id) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

}
