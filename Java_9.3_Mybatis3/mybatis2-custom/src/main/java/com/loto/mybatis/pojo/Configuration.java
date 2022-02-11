package com.loto.mybatis.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:05</p>
 * <p>PageName：Configuration.java</p>
 * Function：存放 sqlMapConfig.xml 解析出来的内容
 */

@Data
public class Configuration {
	private DataSource dataSource;

	/**
	 * key：statementid <p>
	 * value：封装好的 mappedStatement 对象
	 */
	Map<String, MappedStatement> mappedStatementMap = new HashMap<>();
}

