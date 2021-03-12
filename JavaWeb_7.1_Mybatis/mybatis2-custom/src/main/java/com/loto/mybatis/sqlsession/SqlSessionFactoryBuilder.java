package com.loto.mybatis.sqlsession;

import com.loto.mybatis.config.XMLConfigBuilder;
import com.loto.mybatis.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:19</p>
 * <p>PageName：SqlSessionFactoryBuilder.java</p>
 * Function：
 */

public class SqlSessionFactoryBuilder {
	public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
		// 第一：使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
		XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
		Configuration configuration = xmlConfigBuilder.parseConfig(in);

		// 第二：创建sqlSessionFactory对象：工厂类：生产sqlSession:会话对象
		DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

		return defaultSqlSessionFactory;
	}
}
