package com.loto.mybatis.sqlsession;

import com.loto.mybatis.pojo.Configuration;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:23</p>
 * <p>PageName：DefaultSqlSessionFactory.java</p>
 * Function：
 */

public class DefaultSqlSessionFactory implements SqlSessionFactory {
	private Configuration configuration;

	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public SqlSession openSession() {
		return new DefaultSqlSession(configuration);
	}
}