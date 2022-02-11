package com.loto.mybatis.sqlsession;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:21</p>
 * <p>PageName：SqlSession.java<p>
 * Function：
 */

public interface SqlSession {
	/**
	 * 查询所有
	 */
	public <E> List<E> selectList(String statementid, Object... params) throws Exception;

	/**
	 * 根据条件查询单个
	 */
	public <T> T selectOne(String statementid, Object... params) throws Exception;

	/**
	 * 为 Dao 接口生成代理实现类
	 */
	public <T> T getMapper(Class<?> mapperClass);
}
