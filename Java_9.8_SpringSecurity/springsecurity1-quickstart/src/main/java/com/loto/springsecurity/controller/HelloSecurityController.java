package com.loto.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author：蓝田_Loto
 * Date：2021-08-18 10:37
 * PageName：HelloSecurityController.java
 * Function：security入门案例
 */

@RestController
public class HelloSecurityController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello security";
    }
}
