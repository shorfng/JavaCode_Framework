package com.loto.mybatis.config;

import com.loto.mybatis.io.Resources;
import com.loto.mybatis.pojo.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:22</p>
 * <p>PageName：XMLConfigBuilder.java</p>
 * Function：解析 sqlMapConfig.xml 和 mapper.xml 的内容
 */

public class XMLConfigBuilder {
	private Configuration configuration;

	public XMLConfigBuilder() {
		this.configuration = new Configuration();
	}

	/**
	 * 使用 dom4j 对配置文件进行解析，封装成 Configuration
	 */
	public Configuration parseConfig(InputStream inputStream) throws DocumentException, PropertyVetoException {
		Document document = new SAXReader().read(inputStream);

		// 取出 xml 文件中根结点 configuration 中的元素
		Element rootElement = document.getRootElement();

        // ========================= 对 sqlMapConfig.xml 进行解析 ============================

        // 取出 xml 文件中的节点为 property 的，封装到 list
		List<Element> list = rootElement.selectNodes("//property");

		// 遍历后封装到 properties
		Properties properties = new Properties();
		for (Element element : list) {
			String name = element.attributeValue("name");
			String value = element.attributeValue("value");
			properties.setProperty(name, value);
		}

		// 将 properties 的内容转存到 C3P0 数据库连接池 comboPooledDataSource 对象中
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setDriverClass(properties.getProperty("driverClass"));
		comboPooledDataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
		comboPooledDataSource.setUser(properties.getProperty("username"));
		comboPooledDataSource.setPassword(properties.getProperty("password"));

		// 赋值
		configuration.setDataSource(comboPooledDataSource);

		// ========================= 对 sqlMapConfig.xml 中的数据库配置信息封装完成  ============================

        // ========================= 对 mapper.xml 进行解析 ==================================================

        // 拿到路径
		List<Element> mapperList = rootElement.selectNodes("//mapper");
		for (Element element : mapperList) {
			String mapperPath = element.attributeValue("resource");

			// 获取字节输入流
			InputStream resourceAsSteam = Resources.getResourceAsSteam(mapperPath);

			// 使用 dom4j 进行解析
			XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
			// 解析 mapper.xml 中的 sql 语句和参数（在 XMLMapperBuilder.java 中进行）
			xmlMapperBuilder.parse(resourceAsSteam);
		}

		return configuration;
	}
}
