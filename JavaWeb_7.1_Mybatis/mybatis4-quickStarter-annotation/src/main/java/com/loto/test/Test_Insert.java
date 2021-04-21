package com.loto.test;

import com.loto.mapper.IUserMapper;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-19 16:10</p>
 * <p>PageName：Test_Insert.java</p>
 * Function：
 */

public class Test_Insert {
	private IUserMapper userMapper;

	/**
	 * 添加一条用户数据
	 */
	@Test
	public void testAdd() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setUsername("测试用户名");
		user.setPassword("123");

		userMapper.addUser(user);
		System.out.println("插入数据的id为："+user.getId());
	}

	/**
	 * 添加多条用户数据
	 */
	@Test
	public void test_batchAddUser() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		List<User> users = new ArrayList<>() ;

		User user = new User();
		user.setUsername("测试用户名");
		user.setPassword("123");
		users.add(user);

		User user1 = new User();
		user1.setUsername("测试用户名1");
		user1.setPassword("123");
		users.add(user1);

		userMapper.batchAddUser(users);
	}

}
