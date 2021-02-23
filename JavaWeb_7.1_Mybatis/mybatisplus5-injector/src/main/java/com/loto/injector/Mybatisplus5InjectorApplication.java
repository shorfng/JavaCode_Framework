package com.loto.injector;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loto.injector.mapper")
@SpringBootApplication
public class Mybatisplus5InjectorApplication {
	public static void main(String[] args) {
		SpringApplication.run(Mybatisplus5InjectorApplication.class, args);
	}

}
