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
 * Function：解析配置文件
 */

public class SqlSessionFactoryBuilder {
	public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
		// 使用 dom4j 解析配置文件，将解析出来的内容封装到 Configuration 中
		XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
		Configuration configuration = xmlConfigBuilder.parseConfig(in);

		// 创建 sqlSessionFactory 对象，该对象是一个工厂类
        // 主要作用：生产 sqlSession（会话对象），即获取 sqlSession 接口的实现类实例对象（工厂模式）
		DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
		return defaultSqlSessionFactory;
	}
}
