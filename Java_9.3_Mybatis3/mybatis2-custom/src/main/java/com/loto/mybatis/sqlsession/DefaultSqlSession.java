package com.loto.mybatis.sqlsession;

import com.loto.mybatis.pojo.Configuration;
import com.loto.mybatis.pojo.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-12 11:24</p>
 * <p>PageName：DefaultSqlSession.java</p>
 * Function：创建 SqlSession 接口及实现类 DefaultSqlSession，定义对数据库的 CRUD 操作
 */

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {
        // 将要去完成对 simpleExecutor 里的query方法的调用
        simpleExecutor simpleExecutor = new simpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    /**
     * 使用 JDK 动态代理来为 Dao 接口生成代理对象，并返回
     */
    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            // 底层还是去执行JDBC代码
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 准备参数1：statementid，sql语句的唯一标识，namespace.id = 接口全限定名.方法名
                String methodName = method.getName();                      // 方法名
                String className = method.getDeclaringClass().getName();   // 接口全限定名
                String statementId = className + "." + methodName;         // 组装

                // 准备参数2：params:args
                // 获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();

                // 根据不同情况，来调用 selctList 或者 selectOne
                // 判断是否进行了 泛型类型参数化（如果有泛型，返回值的类型是集合，否则是实体）
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }

                return selectOne(statementId, args);
            }
        });
        return (T) proxyInstance;
    }
}
