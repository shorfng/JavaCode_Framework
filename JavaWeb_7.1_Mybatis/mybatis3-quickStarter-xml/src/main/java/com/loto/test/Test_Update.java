package com.loto.test;

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
 * <p>Date：2021-03-17 15:15</p>
 * <p>PageName：Test_Update.java</p>
 * Function：更新
 */

public class Test_Update {

	@Test
	public void test_Update() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();

		User user = new User();
		user.setId(3);
		user.setUsername("loto");
		sqlSession.update("IUserDao.updateUser", user);
		sqlSession.commit();

		sqlSession.close();
	}
}
