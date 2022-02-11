package com.loto.mybatis.test;

import com.loto.mybatis.mapper.IUserMapper;
import com.loto.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-04-26 11:01<p>
 * PageName：Test_TKMapper_Insert.java<p>
 * Function：通用mapper - 插入
 */

public class Test_TKMapper_Insert {
	private IUserMapper userMapper;

	/**
	 * 插入
	 */
	@Test
	public void test_tkmapper_insert() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setUsername("tkmapper_insert");

		// 插入数据（实体类的属性值，如果为null，会被插入数据库）
		//int insert1 = userMapper.insert(user);

		// 插入数据（实体类的属性值，如果为null，不会被插入数据库，使用数据库默认值）
		int insert2 = userMapper.insertSelective(user);
	}
}
