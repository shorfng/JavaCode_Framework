package com.loto.mybatis.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:05</p>
 * <p>PageName：Configuration.java</p>
 * Function：
 */

public class Configuration {
	private DataSource dataSource;

	/**
	 * key：statementid <p>
	 * value：封装好的mappedStatement对象
	 */
	Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Map<String, MappedStatement> getMappedStatementMap() {
		return mappedStatementMap;
	}

	public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
		this.mappedStatementMap = mappedStatementMap;
	}
}

