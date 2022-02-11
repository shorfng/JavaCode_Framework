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
 * Date：2021-04-02 16:04<p>
 * PageName：Test_Plugin.java<p>
 * Function：注解式
 */

public class Test_Plugin {
	private IUserMapper userMapper;

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

}
