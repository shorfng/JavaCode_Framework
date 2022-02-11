package com.loto.mybatis.sqlsession;

import com.loto.mybatis.pojo.Configuration;
import com.loto.mybatis.pojo.MappedStatement;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:32</p>
 * <p>PageName：Executor.java<p>
 * Function：
 */

public interface Executor {
	public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;
}
