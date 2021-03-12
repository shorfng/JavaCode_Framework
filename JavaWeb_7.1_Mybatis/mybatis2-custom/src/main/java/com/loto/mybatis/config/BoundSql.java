package com.loto.mybatis.config;

import com.loto.mybatis.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:25</p>
 * <p>PageName：BoundSql.java</p>
 * Function：
 */

public class BoundSql {
	/**
	 * 解析过后的sql
	 */
	private String sqlText;

	private List<ParameterMapping> parameterMappingList = new ArrayList<>();

	public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
		this.sqlText = sqlText;
		this.parameterMappingList = parameterMappingList;
	}

	public String getSqlText() {
		return sqlText;
	}

	public void setSqlText(String sqlText) {
		this.sqlText = sqlText;
	}

	public List<ParameterMapping> getParameterMappingList() {
		return parameterMappingList;
	}

	public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
		this.parameterMappingList = parameterMappingList;
	}

}
