package com.loto.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.loto.project.mapper"}) // mybatis包扫描

public class Springsecurity2ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springsecurity2ProjectApplication.class, args);
    }
}
