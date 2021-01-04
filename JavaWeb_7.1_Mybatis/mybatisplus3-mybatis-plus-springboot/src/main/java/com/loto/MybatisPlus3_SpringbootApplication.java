package com.loto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loto.mapper")
@SpringBootApplication
public class MybatisPlus3_SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus3_SpringbootApplication.class, args);
    }
}
