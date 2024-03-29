package com.loto.aop.utils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogUtils {

    @Pointcut("execution(* com.loto.aop.service.impl.TransferServiceImpl.*(..))")
    public void pt1() {
    }

    /**
     * 业务逻辑 开始之前 执行
     */
    @Before("pt1()")
    public void beforeMethod(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            System.out.println(arg);
        }
        System.out.println("业务逻辑开始执行之前执行.......");
    }

    /**
     * 业务逻辑 结束时 执行（无论异常与否）
     */
    @After("pt1()")
    public void afterMethod() {
        System.out.println("业务逻辑结束时执行，无论异常与否都执行.......");
    }

    /**
     * 异常时执行
     */
    @AfterThrowing("pt1()")
    public void exceptionMethod() {
        System.out.println("异常时执行.......");
    }

    /**
     * 业务逻辑 正常时 执行
     */
    @AfterReturning(value = "pt1()", returning = "retVal")
    public void successMethod(Object retVal) {
        System.out.println("业务逻辑正常时执行.......");
    }

    /**
     * 环绕通知
     */
    //@Around("pt1()")
    public Object aroundMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕通知中的before method....");

        Object result = null;
        try {
            // 控制原有业务逻辑是否执行
            result = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        } catch (Exception e) {
            System.out.println("环绕通知中的exception method....");
        } finally {
            System.out.println("环绕通知中的after method....");
        }
        return result;
    }
}
