package com.loto.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

/**
 * Author：蓝田_Loto <p>
 * Date：2021-08-05 14:10 <p>
 * PageName：ParamController.java <p>
 * Function：handler 方法上，分别传入 Map、Model和ModelMap 类型的形参，封装数据到 request 域中
 */

@Controller
@RequestMapping("/param")
public class ParamController {
    /**
     * 参数类型：ModelMap <p>
     * http://localhost:8080/param/modelmap
     */
    @RequestMapping("/modelmap")
    public String HandlerModelMap(ModelMap modelMap) {
        // 服务器时间
        Date date = new Date();

        // 向请求域添加属性值
        modelMap.addAttribute("date", date);

        // class org.springframework.validation.support.BindingAwareModelMap
        System.out.println("================= ModelMap:" + modelMap.getClass());

        // 返回到前端页面
        return "success";
    }

    /**
     * 参数类型：Model <p>
     * http://localhost:8080/param/model
     */
    @RequestMapping("/model")
    public String HandlerModel(Model model) {
        // 服务器时间
        Date date = new Date();

        // 向请求域添加属性值
        model.addAttribute("date", date);

        // class org.springframework.validation.support.BindingAwareModelMap
        System.out.println("================= Model:" + model.getClass());

        // 返回到前端页面
        return "success";
    }

    /**
     * 参数类型：map <p>
     * http://localhost:8080/param/map
     */
    @RequestMapping("/map")
    public String HandlerMap(Map<String,Object> map) {
        // 服务器时间
        Date date = new Date();

        // 向请求域添加属性值
        map.put("date",date);

        // class org.springframework.validation.support.BindingAwareModelMap
        System.out.println("================= Map:" + map.getClass());

        // 返回到前端页面
        return "success";
    }

    /* 总结：
     - ModelMap(spring中的 class 类型，实现了 Map 接口)
     - Model（spring中的接口）
     - Map(jdk中的接口)

     - 运行时的具体类型都是 BindingAwareModelMap，相当于给 BindingAwareModelMap 中保存的数据都会放在请求域中
     - BindingAwareModelMap 继承了 ExtendedModelMap
     - ExtendedModelMap 继承了 ModelMap 类，实现了 Model 接口
     */
}
