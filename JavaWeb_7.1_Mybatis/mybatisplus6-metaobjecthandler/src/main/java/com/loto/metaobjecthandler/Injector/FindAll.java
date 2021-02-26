package com.loto.metaobjecthandler.Injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 15:42</p>
 * <p>PageName：FindAll.java</p>
 * Function：
 */

public class FindAll extends AbstractMethod {
	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		String sql = "select * from " + tableInfo.getTableName();
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return this.addSelectMappedStatement(mapperClass, "findAll", sqlSource, modelClass, tableInfo);
	}
}
