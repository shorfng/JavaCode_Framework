package com.loto.mybatis.config;

import com.loto.mybatis.pojo.Configuration;
import com.loto.mybatis.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:23</p>
 * <p>PageName：XMLMapperBuilder.java</p>
 * Function：
 */

public class XMLMapperBuilder {
	private Configuration configuration;

	public XMLMapperBuilder(Configuration configuration) {
		this.configuration = configuration;
	}

	public void parse(InputStream inputStream) throws DocumentException {
		Document document = new SAXReader().read(inputStream);
		Element rootElement = document.getRootElement();
		String namespace = rootElement.attributeValue("namespace");

		List<Element> list = rootElement.selectNodes("//select");
		for (Element element : list) {
			String id = element.attributeValue("id");
			String resultType = element.attributeValue("resultType");
			String parameterType = element.attributeValue("parameterType");
			String sqlText = element.getTextTrim();

			MappedStatement mappedStatement = new MappedStatement();
			mappedStatement.setId(id);
			mappedStatement.setResultType(resultType);
			mappedStatement.setParamterType(parameterType);
			mappedStatement.setSql(sqlText);
			String key = namespace + "." + id;
			configuration.getMappedStatementMap().put(key, mappedStatement);
		}
	}
}

