package com.loto.project.config;

import com.loto.project.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
                .and().authorizeRequests().antMatchers("/toLoginPage").permitAll() // 放行登录页面
                .anyRequest().authenticated();

        // 关闭 csrf 防护
        http.csrf().disable();

        // 加载同源域名下 iframe 页面
        http.headers().frameOptions().sameOrigin();

    }
}
