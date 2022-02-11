package com.loto.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.loto.springboot.mapper")
public class Springboot3DynamicdatasourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot3DynamicdatasourceApplication.class, args);
    }
}
