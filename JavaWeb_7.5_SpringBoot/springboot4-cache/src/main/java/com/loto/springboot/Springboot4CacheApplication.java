package com.loto.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.loto.springboot.mapper")
@EnableCaching
public class Springboot4CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springboot4CacheApplication.class, args);
    }

}
