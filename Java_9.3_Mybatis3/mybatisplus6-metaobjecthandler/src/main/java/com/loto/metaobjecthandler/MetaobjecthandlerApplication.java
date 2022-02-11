package com.loto.metaobjecthandler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loto.metaobjecthandler.mapper")
@SpringBootApplication
public class MetaobjecthandlerApplication {
	public static void main(String[] args) {
		SpringApplication.run(MetaobjecthandlerApplication.class, args);
	}
}
