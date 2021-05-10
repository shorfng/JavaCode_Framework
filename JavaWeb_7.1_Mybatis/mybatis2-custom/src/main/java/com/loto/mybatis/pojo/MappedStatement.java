package com.loto.mybatis.pojo;

import lombok.Data;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:07</p>
 * <p>PageName：MappedStatement.java</p>
 * Function：
 */

@Data
public class MappedStatement {
	/**
	 * id标识
	 */
	private String id;

	/**
	 * 返回值类型
	 */
	private String resultType;

	/**
	 * 参数值类型
	 */
	private String paramterType;

	/**
	 * sql语句
	 */
	private String sql;
}
