package com.loto.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-11 21:29</p>
 * <p>PageName：MybatisPlus_PageConfig.java</p>
 * <p>Function：MybatisPlus 分页插件</p>
 */

@Configuration
public class MybatisPlus_PageConfig {
    /**
     * 分页插件配置
     * <p>方式1：写在类中
     * <p>方式2：写在 sqlMapConfig.xml 中
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
