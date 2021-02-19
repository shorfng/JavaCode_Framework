package com.loto;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loto.mapper")
@SpringBootApplication
public class Mybatisplus4PluginApplication {
	public static void main(String[] args) {
		SpringApplication.run(Mybatisplus4PluginApplication.class, args);
	}
}
