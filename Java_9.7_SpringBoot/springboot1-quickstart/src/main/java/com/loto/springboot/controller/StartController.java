package com.loto.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：蓝田_Loto <p>
 * Date：2021-08-12 14:18 <p>
 * PageName：StartController.java <p>
 * Function：
 */

// 组合注解，等同于 Spring 中 @Controller + @ResponseBody
@RestController
public class StartController {
    // http://localhost:8081/springboot/hello
    @RequestMapping("/hello")
    public String demo(){
        return "热部署 springboot";
    }

}
