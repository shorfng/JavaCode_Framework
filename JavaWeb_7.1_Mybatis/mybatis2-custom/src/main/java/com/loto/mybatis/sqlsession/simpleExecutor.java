package com.loto.mybatis.sqlsession;

import com.loto.mybatis.config.BoundSql;
import com.loto.mybatis.pojo.Configuration;
import com.loto.mybatis.pojo.MappedStatement;
import com.loto.mybatis.utils.GenericTokenParser;
import com.loto.mybatis.utils.ParameterMapping;
import com.loto.mybatis.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:24</p>
 * <p>PageName：simpleExecutor.java</p>
 * Function：转换 sql 语句，执行sql，封装结果集
 */

public class simpleExecutor implements Executor {
	@Override
	public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
		// 1. 注册驱动，获取连接
		Connection connection = configuration.getDataSource().getConnection();

		// 2. 获取sql语句 : select * from user where id = #{id} and username = #{username}
		// 转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
		String sql = mappedStatement.getSql();
		BoundSql boundSql = getBoundSql(sql);

		// 3. 获取预处理对象：preparedStatement
		PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

		// 4. 设置参数
		// 获取到了参数的全路径
		String parameterType = mappedStatement.getParameterType();
		Class<?> parameterTypeClass = getClassType(parameterType);

		List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
		for (int i = 0; i < parameterMappingList.size(); i++) {
			ParameterMapping parameterMapping = parameterMappingList.get(i);
			String content = parameterMapping.getContent();

			// 反射
			Field declaredField = parameterTypeClass.getDeclaredField(content);

			// 暴力访问
			declaredField.setAccessible(true);
			Object o = declaredField.get(params[0]);

			preparedStatement.setObject(i + 1, o);
		}

		// 5. 执行sql
		ResultSet resultSet = preparedStatement.executeQuery();
		String resultType = mappedStatement.getResultType();
		Class<?> resultTypeClass = getClassType(resultType);

		// 6. 封装返回结果集
		ArrayList<Object> objects = new ArrayList<>();
		while (resultSet.next()) {
			Object o = resultTypeClass.newInstance();

			// 取出元数据
			ResultSetMetaData metaData = resultSet.getMetaData();

			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				// 取出字段名
				String columnName = metaData.getColumnName(i);

				// 取出字段的值
				Object value = resultSet.getObject(columnName);

				// 使用反射或者内省，根据数据库表和实体的对应关系，完成封装
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
				Method writeMethod = propertyDescriptor.getWriteMethod();
				writeMethod.invoke(o, value);
			}
			objects.add(o);
		}
		return (List<E>) objects;
	}

	private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
		if (paramterType != null) {
			Class<?> aClass = Class.forName(paramterType);
			return aClass;
		}
		return null;
	}

	/**
	 * 完成对 #{} 的解析工作
	 */
	private BoundSql getBoundSql(String sql) {
		// ParameterMappingTokenHandler 标记处理类：配合标记解析器来完成对占位符的解析处理工作
		ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();

        // 将 #{} 使用 ？ 进行代替
        // GenericTokenParser 标记解析器
		GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);

		// 解析出来的sql
		String parseSql = genericTokenParser.parse(sql);

		// #{}里面解析出来的参数名称
		List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        // 将解析出 #{} 里面的值进行存储
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
		return boundSql;
	}
}
