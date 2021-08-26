package com.loto.springboot.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP
 */
@Aspect
@Component
public class RoutingAspect {
    /**
     * 环绕通知（对 @annotation(routingWith) 注解拦截增强）
     */
    @Around("@annotation(routingWith)")
    public Object routingWithDataSource(ProceedingJoinPoint joinPoint, RoutingWith routingWith) throws Throwable {
        String key = routingWith.value();
        RoutingDataSourceContext ctx = new RoutingDataSourceContext(key);
        return joinPoint.proceed();
    }
}
