package com.loto.mybatis.sqlsession;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:21</p>
 * <p>PageName：SqlSessionFactory.java<p>
 * Function：
 */

public interface SqlSessionFactory {
	public SqlSession openSession();
}
