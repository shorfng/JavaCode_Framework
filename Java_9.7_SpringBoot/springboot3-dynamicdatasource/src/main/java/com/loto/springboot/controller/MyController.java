package com.loto.springboot.controller;

import com.loto.springboot.config.RoutingWith;
import com.loto.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    private ProductService productService;

    /**
     * http://localhost:8080/findAllProductM
     */
    @RoutingWith("masterDataSource")
    @GetMapping("/findAllProductM")
    public String findAllProductM() {
        productService.findAllProductM();
        return "findAllProductM";
    }

    /**
     * http://localhost:8080/findAllProductS
     */
    @RoutingWith("slaveDataSource")
    @GetMapping("/findAllProductS")
    public String findAllProductS() {
        productService.findAllProductS();
        return "findAllProductS";
    }

}
