package com.loto.test;

import com.loto.mapper.IOrderMapper;
import com.loto.mapper.IUserMapper;
import com.loto.pojo.Order;
import com.loto.pojo.Role;
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
 * Function：查询（Mybatis的Dao层实现：传统开发方式、Mapper 代理开发方式）
 */

public class Test_Select {
	/**
	 * 【传统开发方式】查询全部：通过 namespace + id
	 */
	@Test
	public void test_findAll_XML() throws IOException {
		// 1、加载核心配置文件（使用 Resources 工具类把配置文件加载成字节输入流）
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

		// 2、解析了配置文件，并创建 sqlSessionFactory 工厂对象
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

		// 3、获得sqlSession对象
		// 默认开启一个事务，但是该事务不会自动提交，在进行增删改操作时，要手动提交事务
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 4、执行sql语句（使用 sqlSession 调用方法）
		// 查询单个：selectOne  查询所有selectList  添加：insert  修改：update  删除：delete
		List<User> users = sqlSession.selectList("com.loto.dao.IUserMapper.findAll");
		for (User user : users) {
			System.out.println(user);
		}

		// 5、释放资源
		sqlSession.close();
	}

	/**
	 * 【Mapper 代理开发方式】查询全部：通过方法
	 */
	@Test
	public void test_findAll_mapper() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		List<User> all = mapper.findAll();
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 【Mapper 代理开发方式】多条件组合查询：if
	 */
	@Test
	public void test_findByCondition() throws IOException {
		User user1 = new User();
		user1.setId(1);
		user1.setUsername("TD");

		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

		List<User> all = mapper.findByCondition(user1);
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 【Mapper 代理开发方式】多值查询：foreach
	 */
	@Test
	public void test_findByIds() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

		int[] arr = {1, 2};

		List<User> all = mapper.findByIds(arr);
		for (User user : all) {
			System.out.println(user);
		}
	}

	/**
	 * 一对一查询
	 */
	@Test
	public void oneToOne() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
		List<Order> all = mapper.findOrderAndUser();
		for (Order order : all) {
			System.out.println(order);
		}
	}

	/**
	 * 一对多查询（1个用户多条订单记录）
	 */
	@Test
	public void oneToMany() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		List<User> all = mapper.findAllUser();
		for (User user : all) {
			System.out.println(user.getUsername());

			List<Order> orderList = user.getOrderList();
			for (Order order : orderList) {
				System.out.println(order);
			}
			System.out.println("----------------------------------");
		}
	}

	/**
	 * 多对多查询（查询多个用户同时查询出每个用户的所有角色）
	 */
	@Test
	public void ManyToMany() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
		List<User> all = mapper.findAllUserAndRole();
		for (User user : all) {
			System.out.println(user.getUsername());
			List<Role> roleList = user.getRoleList();
			for (Role role : roleList) {
				System.out.println(role);
			}
			System.out.println("----------------------------------");
		}
	}
}
