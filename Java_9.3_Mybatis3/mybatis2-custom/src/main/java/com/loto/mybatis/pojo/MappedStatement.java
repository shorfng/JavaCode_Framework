package com.loto.mybatis.pojo;

import lombok.Data;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-03-12 11:07<p>
 * PageName：MappedStatement.java<p>
 * Function：存放 Mapper.xml 解析出来的内容
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
	private String parameterType;

	/**
	 * sql语句
	 */
	private String sql;
}
