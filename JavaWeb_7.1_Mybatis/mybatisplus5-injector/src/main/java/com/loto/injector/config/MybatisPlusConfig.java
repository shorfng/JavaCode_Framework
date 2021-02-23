package com.loto.injector.config;

import com.loto.injector.Injector.MySqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 15:46</p>
 * <p>PageName：MybatisPlusConfig.java</p>
 * Function：插件配置
 */

@Configuration
public class MybatisPlusConfig {
	/**
	 * 自定义的sql注入器
	 */
	@Bean
	public MySqlInjector mySqlInjector(){
		return new MySqlInjector();
	}
}
