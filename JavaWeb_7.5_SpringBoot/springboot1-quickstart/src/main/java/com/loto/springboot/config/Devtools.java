package com.loto.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class Devtools implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(Devtools.class);

    /**
     *  验证热部署：对类加载采用了两种类加载器
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("guava-jar ClassLoader: " + DispatcherServlet.class.getClassLoader().toString());
        log.info("Devtools ClassLoader : " + this.getClass().getClassLoader().toString());
    }
}
