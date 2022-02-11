package com.loto.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 多属性批量注入
 */
@Configuration
// Spring Boot 提供的一个注解，用于启用应用对另外一个注解 @ConfigurationProperties 的支持
// 用于设置一组使用了注解 @ConfigurationProperties 的类，用于作为 bean 定义注册到容器中
@EnableConfigurationProperties(JdbcConfiguration.class)
// 定义在 application 文件中属性值的前缀信息
@ConfigurationProperties(prefix = "jdbc")
@Data
public class JdbcConfiguration {
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        return druidDataSource;
    }

    @Override
    public String toString() {
        return "JdbcConfiguration{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
