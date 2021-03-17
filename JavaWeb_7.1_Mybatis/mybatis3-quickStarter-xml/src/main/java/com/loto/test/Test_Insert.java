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
 * <p>Date：2021-03-17 11:21</p>
 * <p>PageName：Test_Insert.java</p>
 * Function：插入
 */

public class Test_Insert {
	@Test
	public void test_Insert() throws IOException {
		InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		//事务自动提交
		SqlSession sqlSession = sqlSessionFactory.openSession(true);

		User user = new User();
		user.setId(3);
		user.setUsername("Jan");
		sqlSession.insert("IUserDao.saveUser", user);

		sqlSession.close();
	}
}
