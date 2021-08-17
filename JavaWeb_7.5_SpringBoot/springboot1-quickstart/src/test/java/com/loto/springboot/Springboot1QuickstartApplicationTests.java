package com.loto.springboot;

import com.loto.springboot.config.JdbcConfiguration;
import com.loto.springboot.config.JdbcConfiguration_singleproperty;
import com.loto.springboot.pojo.AnotherComponent;
import com.loto.springboot.pojo.OwnerProperties;
import com.loto.springboot.pojo.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

// 测试启动器，并加载 Spring Boot 测试注解
@RunWith(SpringRunner.class)
// 标记为 Spring Boot 单元测试类，并加载项目的 ApplicationContext 上下文环境
@SpringBootTest
class Springboot1QuickstartApplicationTests {
    @Autowired
    private Person person;

    /**
     * 测试
     */
    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Autowired
    private DataSource dataSource_singleproperty;

    @Autowired
    private JdbcConfiguration_singleproperty jdbcConfigurationSingleproperty;

    /**
     * 测试：单属性注入
     */
    @Test
    public void test_Singleproperty(){
        System.out.println(jdbcConfigurationSingleproperty);
        System.out.println(dataSource_singleproperty);
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcConfiguration jdbcConfiguration;

    /**
     * 测试：多属性批量注入
     */
    @Test
    public void test_property(){
        System.out.println(jdbcConfiguration);
        System.out.println(dataSource);
    }

    @Autowired
    private AnotherComponent anotherComponent;

    /**
     * 将属性绑定到控件之外的第三方组件
     */
    @Test
    public void test_anotherComponent(){
        System.out.println(anotherComponent);
    }

    @Autowired
    private OwnerProperties ownerProperties;

    @Test
    public void test_ownerProperties(){
        System.out.println(ownerProperties);
    }

    /**
     * 测试日志输出
     */
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * SLF4J 日志级别（从小到大）：trace < debug < info < warn < error 只会输出比当前级别更高的级别日志
     */
    @Test
    public void testLog() {
        logger.trace("trace日志....");
        logger.debug("debug日志....");
        // springboot 中，日志的默认级别是 info级别/root级别
        logger.info("info日志....");
        logger.warn("warn日志....");
        logger.error("error日志....");
    }
}
