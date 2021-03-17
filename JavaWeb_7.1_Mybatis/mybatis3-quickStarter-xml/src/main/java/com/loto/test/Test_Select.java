package com.loto.test;

import com.loto.dao.IUserDao;
import com.loto.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-03-17 10:41</p>
 * <p>PageName：Test_Select.java</p>
 * Function：查询
 */

public class Test_Select {
	/**
	 * 查询全部：通过 namespace + id
	 */
	@Test
	public void test_findAll_XML() throws IOException {
		// 1.Resources工具类，配置文件的加载，把配置文件加载成字节输入流
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

		// 2.解析了配置文件，并创建了sqlSessionFactory工厂
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

		// 3.生产sqlSession
		// 默认开启一个事务，但是该事务不会自动提交，在进行增删改操作时，要手动提交事务
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 4.sqlSession调用方法：查询所有selectList
		// 查询单个：selectOne 添加：insert  修改：update 删除：delete
		List<User> users = sqlSession.selectList("IUserDao.findAll");
		for (User user : users) {
			System.out.println(user);
		}

		sqlSession.close();
	}

	/**
	 * 查询全部：通过方法
	 */
	@Test
	public void test_findAll_mapper() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserDao mapper = sqlSession.getMapper(IUserDao.class);
		List<User> all = mapper.findAll();
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 多条件组合查询：if
	 */
	@Test
	public void test_findByCondition() throws IOException {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("TD");

		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserDao mapper = sqlSession.getMapper(IUserDao.class);

		List<User> all = mapper.findByCondition(user1);
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 多值查询：foreach
	 */
	@Test
	public void test_findByIds() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserDao mapper = sqlSession.getMapper(IUserDao.class);

		int[] arr = {1, 2};

		List<User> all = mapper.findByIds(arr);
		for (User user : all) {
			System.out.println(user);
		}
	}

}
