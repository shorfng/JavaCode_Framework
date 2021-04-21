package com.loto.test;

import com.loto.mapper.IOrderMapper;
import com.loto.mapper.IUserMapper;
import com.loto.pojo.Order;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-02 16:04</p>
 * <p>PageName：Test_Mybatis.java</p>
 * Function：注解式
 */

public class Test_Select {
	private IUserMapper userMapper;
	private IOrderMapper orderMapper;

	/**
	 * 查询用户
	 */
	@Test
	public void test_selectAll() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		List<User> allUser = userMapper.selectAllUser();
		for (User user : allUser) {
			System.out.println(user);
		}
	}

	/**
	 * 根据 id 查询用户
	 */
	@Test
	public void test_findUserById() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = userMapper.findUserById(1);
		System.out.println(user);
	}

	/**
	 * 一对一（查询订单的同时还查询该订单所属的用户）
	 */
	@Test
	public void test_oneToOne() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		orderMapper = sqlSession.getMapper(IOrderMapper.class);

		List<Order> orderAndUser = orderMapper.findOrderAndUser();
		for (Order order : orderAndUser) {
			System.out.println(order);
		}
	}

	/**
	 * 一对多（查询所有用户、同时查询每个用户关联的订单信息）
	 */
	@Test
	public void test_oneToMany() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		List<User> all = userMapper.findAll();
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 多对多（查询所有用户、同时查询每个用户关联的角色信息）
	 */
	@Test
	public void test_ManyToMany() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		List<User> all = userMapper.findAllUserAndRole();
		for (User user : all) {
			System.out.println(user);
		}
	}

}
