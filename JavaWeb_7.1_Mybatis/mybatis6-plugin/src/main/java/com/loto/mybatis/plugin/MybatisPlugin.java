package com.loto.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-23 10:40</p>
 * <p>PageName：MybatisPlugin.java</p>
 * Function：自定义插件
 */

/**
 * - @Signature 可以定义多个 @Signature 对多个地方拦截<p>
 * - type 指拦截哪个接口<p>
 * - method 被拦截的接口中的方法名<p>
 * - args 拦截方法的入参（顺序写入，如果方法重载，可通过方法名和入参来确定唯一）
 */
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class MybatisPlugin implements Interceptor {
	/**
	 * 获取配置文件的参数
	 */
	@Override
	public void setProperties(Properties properties) {
		System.out.println("获取到的配置文件的参数是：" + properties);
	}

	/**
	 * 把当前的拦截器生成代理存到拦截器链中
	 */
	@Override
	public Object plugin(Object target) {
		Object wrap = Plugin.wrap(target, this);
		System.out.println(wrap);
		return wrap;
	}

	/**
	 * 拦截方法：只要被拦截的目标对象的目标方法被执行时，每次都会执行intercept方法
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		System.out.println("执行 intercept 方法......");
		//原方法执行
		return invocation.proceed();
	}
}