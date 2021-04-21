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

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-04-19 16:10</p>
 * <p>PageName：Test_Update.java</p>
 * Function：
 */

public class Test_Update {
	private IUserMapper userMapper;

	/**
	 * 根据 id 更新用户
	 */
	@Test
	public void test_Update() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		// 事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		userMapper = sqlSession.getMapper(IUserMapper.class);

		User user = new User();
		user.setId(1);
		user.setUsername("TD");

		userMapper.updateUser(user);
	}



}
