package com.loto.mybatis.test;

import com.loto.mybatis.io.Resources;
import com.loto.mybatis.pojo.User;
import com.loto.mybatis.sqlsession.SqlSession;
import com.loto.mybatis.sqlsession.SqlSessionFactory;
import com.loto.mybatis.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Author：蓝田_Loto<p>
 * Date：2021-03-12 14:50<p>
 * PageName：Test_sqlSession<p>
 * Function：<p>
 * 缺点1，存在重复的代码：创建 sqlsession、调用 sqlsession 方法、关闭 sqlsession<p>
 * 缺点2，存在硬编码：调用 sqlsession 的方法时，参数 statement 的 id 硬编码
 */

public class Test_sqlSession {
	/**
	 * 查询所有
	 */
	@Test
	public void test_findAll() throws Exception {
		InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		List<User> users = sqlSession.selectList("com.loto.mybatis.dao.IUserDao.findAll");
		for (User user1 : users) {
			System.out.println(user1);
		}
	}

	/**
	 * 根据条件查询
	 */
	@Test
	public void test_findByCondition() throws Exception {
		InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		// 调用
		User user = new User();
		user.setId(1);
		user.setUsername("TD");

		// sql的唯一标识 statementId：namespace.id
		User user2 = sqlSession.selectOne("com.loto.mybatis.dao.IUserDao.findByCondition", user);
		System.out.println(user2);
	}

}
