package com.loto.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：蓝田_Loto
 * <p>Date：2021-01-11 21:29</p>
 * <p>PageName：MybatisPlusConfig.java</p>
 * <p>Function：MybatisPlus 插件配置类</p>
 */

@Configuration
public class MybatisPlusConfig {
	/**
	 * 执行分析插件：对SQL执行的分析的插件，可用作阻断全表更新、删除的操作<p>
	 * 注意：该插件仅适用于开发环境，不适用于生产环境
	 */
	@Bean
	public SqlExplainInterceptor sqlExplainInterceptor() {
		SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();

		// 攻击 SQL 阻断解析器、加入解析链
		List<ISqlParser> sqlParserList = new ArrayList<>();
		sqlParserList.add(new BlockAttackSqlParser());
		sqlExplainInterceptor.setSqlParserList(sqlParserList);
		return sqlExplainInterceptor;
	}

	/**
	 * 性能分析插件
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

		// 设置 sql 语句的最大执行时间
		// 如果超过100ms，则报错 The SQL execution time is too large, please optimize !
		performanceInterceptor.setMaxTime(100);

		// 设置打印到控制台的 sql 是否格式化显示
		performanceInterceptor.setFormat(true);

		return performanceInterceptor;
	}

	/**
	 * 乐观锁插件
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

}
