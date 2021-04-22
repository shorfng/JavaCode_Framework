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
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-02 16:04</p>
 * <p>PageName：Test_Mybatis.java</p>
 * Function：注解式
 */

public class Test_Cache {
	/**
	 * 一级缓存
	 */
	@Test
	public void test_firstCache() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);

		// 第一次查询id为1的用户
		// 第⼀次发起查询⽤户id为1的⽤户信息，先去找缓存中是否有id为1的⽤户信息
		// 如果没有，从数据库查询⽤户信息,得到⽤户信息，将⽤户信息存储到⼀级缓存中
		// 如果中间 sqlSession 去执⾏commit操作（执⾏插⼊、更新、删除），则会清空SqlSession中的⼀级缓存，这样做的⽬的为了让缓存中存储的是最新的信息，避免脏读
		User user1 = userMapper.findUserById(1);
		System.out.println(user1);

		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
		System.out.println(user1 == user2);

		// 更新用户
		User user = new User();
		user.setId(1);
		user.setUsername("tom");
		userMapper.updateUser(user);
		sqlSession.commit();
		sqlSession.clearCache();

		// 第⼆次发起查询⽤户id为1的⽤户信息，先去找缓存中是否有id为1的⽤户信息，缓存中有，直接从缓存中获取⽤户信息
		User user3 = userMapper.findUserById(1);
		System.out.println(user1 == user3);
	}


	@Test
	public void testTwoCache() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

		// 根据 sqlSessionFactory 产⽣ session
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();

		IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
		IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);

		// 第⼀次查询，发出sql语句，并将查询的结果放⼊缓存中
		User u1 = userMapper1.findUserById(1);
		System.out.println(u1);
		sqlSession1.close(); //第⼀次查询完后关闭 sqlSession

		// 第⼆次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
		User u2 = userMapper2.findUserById(1);
		System.out.println(u2);
		sqlSession2.close();
	}


	/**
	 * 二级缓存
	 */
	@Test
	public void test_SecondCache() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);


		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();


		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		IUserMapper mapper1 = sqlSession1.getMapper(IUserMapper.class);
		IUserMapper mapper2 = sqlSession2.getMapper(IUserMapper.class);
		IUserMapper mapper3 = sqlSession3.getMapper(IUserMapper.class);

		// 第⼀次查询，会将数据放⼊缓存中，然后第⼆次查询则会直接去缓存中取
		// ⼀级缓存是基于sqlSession的，⽽⼆级缓存是基于mapper⽂件的namespace的，也就是说多个sqlSession可以共享⼀个mapper中的⼆级缓存区域，
		// 并且如果两个mapper的namespace 相同，即使是两个mapper,那么这两个mapper中执⾏sql查询到的数据也将存在相同的⼆级缓存区域 中
		User user1 = mapper1.findUserById(1);
		sqlSession1.close(); //清空一级缓存

		User user = new User();
		user.setId(1);
		user.setUsername("lisi");
		mapper3.updateUser(user);
		sqlSession3.commit();

		User user2 = mapper2.findUserById(1);
		System.out.println(user1 == user2);
	}

}
