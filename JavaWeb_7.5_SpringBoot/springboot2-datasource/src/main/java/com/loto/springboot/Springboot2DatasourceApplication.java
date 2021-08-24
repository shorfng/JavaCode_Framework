package com.loto.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.loto.springboot.mapper")
public class Springboot2DatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot2DatasourceApplication.class, args);
    }
}
