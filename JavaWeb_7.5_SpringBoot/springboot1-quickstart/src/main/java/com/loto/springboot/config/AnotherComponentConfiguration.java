package com.loto.springboot.config;

import com.loto.springboot.pojo.AnotherComponent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnotherComponentConfiguration {
    // 将属性绑定到控件之外的第三方组件
    @ConfigurationProperties(prefix = "another")
    @Bean
    public AnotherComponent anotherComponent() {
        return new AnotherComponent();
    }
}
