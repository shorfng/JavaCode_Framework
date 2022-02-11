package com.loto.springmvc.controller;

import com.loto.springmvc.pojo.QueryVo;
import com.loto.springmvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Author：蓝田_Loto <p>
 * Date：2021-08-05 14:49 <p>
 * PageName：ParamBindingController.java <p>
 * Function：参数绑定
 */

@Controller
@RequestMapping("/parambinding")
public class ParamBindingController {
    /**
     * SpringMVC 对原生 servlet api 的支持 <p>
     * url：/parambinding/handleServlet?id=1 <p>
     * <p>
     * 如果要在 SpringMVC 中使用 servlet原生对象 <p>
     * 比如 HttpServletRequest、HttpServletResponse、HttpSession <p>
     * 直接在 Handler 方法形参中声明使用即可
     */
    @RequestMapping("/handleServlet")
    public ModelAndView handleServlet(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        String id = request.getParameter("id");

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMVC 接收简单数据类型参数 <p>
     * url：/parambinding/simpledatatype?id=1 <p>
     * <p>
     * 接收简单数据类型参数，直接在 handler 方法的形参中声明即可，框架会取出参数值然后绑定到对应参数上<p>
     * 要求：传递的参数名和声明的形参名称保持一致，如果不一致，使用 @RequestParam("实际访问的参数名")<p>
     * url：/parambinding/simpledatatype?ids=1
     */
    @RequestMapping("/simpledatatype")
    public ModelAndView handleSimpleDataType(@RequestParam("ids") Integer id) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMVC 接收 pojo 类型参数 <p>
     * url：/parambinding/handlepojo?id=1&username=TD <p>
     * <p>
     * 接收 pojo 类型参数，直接形参声明即可，类型就是Pojo的类型，形参名无所谓 <p>
     * 要求：传递的参数名必须和Pojo的属性名保持一致
     */
    @RequestMapping("/handlepojo")
    public ModelAndView handlePojo(User user) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * SpringMVC 接收 pojo 包装类型参数 <p>
     * url：/parambinding/handlepackagetype?user.id=1&user.username=TD <p>
     * <p>
     * 不管包装 Pojo 与否，它首先是一个pojo，那么就可以按照上述pojo的要求来 <p>
     * 1、绑定时候直接形参声明即可 <p>
     * 2、传参参数名和pojo属性保持一致，如果不能够定位数据项，那么通过属性名 + "." 的方式进一步锁定数据
     */
    @RequestMapping("/handlepackagetype")
    public ModelAndView handlePackageType(QueryVo queryVo) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 绑定日期类型参数 <p>
     * url：/parambinding/handledatetype?birthday=2019-10-08
     * 定义一个SpringMVC的类型转换器  接口，扩展实现接口接口，注册你的实现
     */
    @RequestMapping("/handledatetype")
    public ModelAndView handleDateType(Date birthday) {
        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date", date);
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
