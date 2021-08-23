package com.loto.project.config;

import com.loto.project.filter.ValidateCodeFilter;
import com.loto.project.handle.MyAccessDeniedHandler;
import com.loto.project.service.impl.MyAuthenticationService;
import com.loto.project.service.impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

/**
 * Author：蓝田_Loto
 * Date：2021-08-18 13:56
 * PageName：SecurityConfig.java
 * Function：
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启注解支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    MyAuthenticationService myAuthenticationService;

    @Autowired
    ValidateCodeFilter validateCodeFilter;

    @Autowired
    MyAccessDeniedHandler myAccessDeniedHandler;

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
        // /code/** 为图形验证码
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

        // 将验证码过滤器 加到 用户名密码验证过滤器 的前面
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);

        // 方式 1：使用 spring security 内置权限表达式
        // 设置 /user 开头的请求需要 ADMIN 权限
        http.authorizeRequests().antMatchers("/user/**").hasRole("ADMIN");
        // 设置 /product 开头的请求需要 ADMIN 或者 PRODUCT 权限 并且访问的IP是127.0.0.1
        http.authorizeRequests().antMatchers("/product/**").access("hasAnyRole('ADMIN','PRODUCT') and hasIpAddress('127.0.0.1')");

        // 方式 2：基于 url 控制权限（设置权限不足的信息）
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);

        // 使用自定义bean完成授权
        //（1）一级路径限制
        http.authorizeRequests()
                .antMatchers("/user/**")
                .access("@myAuthorizationService.check(authentication,request)");
        //（2）二级路径限制
        http.authorizeRequests()
                .antMatchers("/user/{id}")
                .access("@myAuthorizationService.check(authentication,request,#id)");


        // 开启表单认证
        http.formLogin()
                .loginPage("/toLoginPage")    // 自定义登录页面
                .loginProcessingUrl("/login") // 表单提交的路径
                .usernameParameter("username")
                .passwordParameter("password") // 自定义 input 的 name 值
                .successForwardUrl("/")        // 自定义登录成功之后跳转的路径
                .successHandler(myAuthenticationService)  // 自定义登录成功的处理
                .failureHandler(myAuthenticationService)  // 自定义登录失败的处理
                .and().logout().logoutUrl("/logout")      // 自定义退出登录 URL
                .logoutSuccessHandler(myAuthenticationService) // 自定义退出登录的处理
                // 开启 csrf 后，需要使得 logout 从 post 请求改为 get才能使用登出功能
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .and().rememberMe()            // 开启记住我功能
                .tokenValiditySeconds(1209600) // token失效时间 默认2周
                .rememberMeParameter("remember-me") // 自定义表单“记住我”按钮的 input 值
                .tokenRepository(getPersistentTokenRepository()) // 持久化的 Token 生成
                .and().authorizeRequests().antMatchers("/toLoginPage").permitAll() // 放行登录页面
                .anyRequest().authenticated();

        // 关闭 csrf 防护（默认 spring security 开启 csrf）
        //http.csrf().disable();

        // 开启csrf防护（定义哪些路径不需要防护）
        // http.csrf().ignoringAntMatchers("/logout");
        http.csrf().ignoringAntMatchers();

        // 加载同源域名下 iframe 页面
        http.headers().frameOptions().sameOrigin();

        // 自定义 session 管理（并发控制）
        http.sessionManagement()
                .invalidSessionUrl("/toLoginPage")  // session失效之后跳转的路径
                .maximumSessions(1)                 // session最大会话数量（1 表示同一时间只能有一个用户可以登录，互踢）
                .maxSessionsPreventsLogin(true)     // 如果达到最大会话数量，就阻止登录
                .expiredUrl("/toLoginPage");        // session过期之后跳转的路径

        // 开启跨域支持
        http.cors().configurationSource(corsConfigurationSource());
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

    /**
     * 跨域配置信息源
     */
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允许跨域的站点
        corsConfiguration.addAllowedOrigin("*");

        // 允许跨域的http方法
        corsConfiguration.addAllowedMethod("*");

        // 允许跨域的请求头
        corsConfiguration.addAllowedHeader("*");

        // 允许带凭证
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

        // 对所有url都生效
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
