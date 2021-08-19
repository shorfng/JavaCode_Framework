package com.loto.project.config;

import com.loto.project.service.impl.MyAuthenticationService;
import com.loto.project.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Author：蓝田_Loto
 * Date：2021-08-18 13:56
 * PageName：SecurityConfig.java
 * Function：
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    MyAuthenticationService myAuthenticationService;

    /**
     * 身份安全管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 解决静态资源被拦截的问题
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/code/**");
    }

    /**
     * HTTP 请求方法
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启 httpBasic 认证：http.httpBasic()
        // 所有请求都需要认证之后访问：and().authorizeRequests().anyRequest().authenticated()
        //http.httpBasic().and().authorizeRequests().anyRequest().authenticated();

        // 开启表单认证
        http.formLogin()
                .loginPage("/toLoginPage")    // 自定义登录页面
                .loginProcessingUrl("/login") // 表单提交的路径
                .usernameParameter("username")
                .passwordParameter("password") // 自定义 input 的 name 值
                .successForwardUrl("/")        // 登录成功之后跳转的路径
                .successHandler(myAuthenticationService)  // 登录成功的处理
                .failureHandler(myAuthenticationService)  // 登录失败的处理
                .and().rememberMe()            // 开启记住我功能
                .tokenValiditySeconds(1209600) // token失效时间 默认2周
                .rememberMeParameter("remember-me") // 自定义表单“记住我”按钮的 input 值
                .tokenRepository(getPersistentTokenRepository()) // 持久化的 Token 生成
                .and().authorizeRequests().antMatchers("/toLoginPage").permitAll() // 放行登录页面
                .anyRequest().authenticated();

        // 关闭 csrf 防护
        http.csrf().disable();

        // 加载同源域名下 iframe 页面
        http.headers().frameOptions().sameOrigin();
    }

    @Autowired
    DataSource dataSource;

    /**
     * 负责token与数据库之间的操作（持久化的 Token 生成）
     */
    @Bean
    public PersistentTokenRepository getPersistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();

        // 设置数据源
        tokenRepository.setDataSource(dataSource);

        // 启动时帮助我们自动创建一张表
        // 第一次启动项目设置true，第二次启动项目设置false或者注释掉
        //tokenRepository.setCreateTableOnStartup(true);

        return tokenRepository;
    }

}
