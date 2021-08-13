package com.loto.springboot;

import com.loto.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 测试启动器，并加载 Spring Boot 测试注解
@RunWith(SpringRunner.class)
// 标记为 Spring Boot 单元测试类，并加载项目的 ApplicationContext 上下文环境
@SpringBootTest
class Springboot1QuickstartApplicationTests {
    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
