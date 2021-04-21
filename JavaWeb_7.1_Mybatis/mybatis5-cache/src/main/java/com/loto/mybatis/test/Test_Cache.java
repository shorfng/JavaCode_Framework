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
	private IUserMapper userMapper;
	private SqlSession sqlSession;
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 一级缓存
	 */
	@Test
	public void test_firstCache() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(IUserMapper.class);

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

	/**
	 * 二级缓存
	 */
	@Test
	public void test_SecondCache() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		sqlSession = sqlSessionFactory.openSession();
		userMapper = sqlSession.getMapper(IUserMapper.class);

		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		IUserMapper mapper1 = sqlSession1.getMapper(IUserMapper.class);
		IUserMapper mapper2 = sqlSession2.getMapper(IUserMapper.class);
		IUserMapper mapper3 = sqlSession3.getMapper(IUserMapper.class);

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
