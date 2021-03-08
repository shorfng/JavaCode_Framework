package com.loto.logicallydelete;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.loto.logicallydelete.mapper")
@SpringBootApplication
public class LogicallydeleteApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogicallydeleteApplication.class, args);
	}
}
